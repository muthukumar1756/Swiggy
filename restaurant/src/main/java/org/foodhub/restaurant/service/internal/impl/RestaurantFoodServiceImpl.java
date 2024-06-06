package org.foodhub.restaurant.service.internal.impl;

import java.util.Collection;
import java.util.Optional;

import org.foodhub.common.hibernate.HibernateEntityValidator;
import org.foodhub.restaurant.database.dao.RestaurantFoodDAO;
import org.foodhub.restaurant.database.internal.impl.RestaurantFoodDAOImpl;
import org.foodhub.restaurant.model.food.Food;
import org.foodhub.restaurant.model.restaurant.Restaurant;
import org.foodhub.restaurant.service.RestaurantFoodService;
import org.foodhub.common.json.JsonObject;
import org.foodhub.common.json.JsonArray;
import org.foodhub.common.json.JsonFactory;
import org.foodhub.common.hibernate.impl.HibernateEntityValidatorImpl;
import org.foodhub.common.hibernate.validatorgroup.food.DeleteFoodValidator;
import org.foodhub.common.hibernate.validatorgroup.food.GetFoodValidator;
import org.foodhub.common.hibernate.validatorgroup.food.PostFoodValidator;

/**
 * <p>
 * Implements the service of the restaurant food related operation.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class RestaurantFoodServiceImpl implements RestaurantFoodService {

    private static final String STATUS = "status";
    private final JsonFactory jsonFactory;
    private final HibernateEntityValidator validatorFactory;
    private final RestaurantFoodDAO restaurantFoodDAO;

    private RestaurantFoodServiceImpl() {
        restaurantFoodDAO = RestaurantFoodDAOImpl.getInstance();
        jsonFactory = JsonFactory.getInstance();
        validatorFactory = HibernateEntityValidatorImpl.getInstance();
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final RestaurantFoodService RESTAURANT_FOOD_SERVICE = new RestaurantFoodServiceImpl();
    }

    /**
     * <p>
     * Gets the instance of restaurant food service implementation class.
     * </p>
     *
     * @return The restaurant food service implementation object
     */
    public static RestaurantFoodService getInstance() {
        return InstanceHolder.RESTAURANT_FOOD_SERVICE;
    }

    /**
     * {@inheritDoc}
     *
     * @param food         Represents the current food added by the restaurant
     * @param restaurantId Represents the id of the Restaurant
     * @return The response of adding the food in the menucard
     */
    @Override
    public byte[] addFood(final Food food, final long restaurantId) {
        final Restaurant restaurant = new Restaurant.RestaurantBuilder().setId(restaurantId).build();
        final JsonObject jsonObject = validatorFactory.validate(restaurant, PostFoodValidator.class);
        jsonObject.addAll(validatorFactory.validate(food, PostFoodValidator.class));

        if (jsonObject.isEmpty()) {
            return restaurantFoodDAO.addFood(food, restaurantId) ?
                    jsonObject.put(STATUS, "Successful food was added").asBytes() :
                    jsonObject.put(STATUS, "Unsuccessful adding food was failed").asBytes();
        }

        return jsonObject.asBytes();
    }

    /**
     * {@inheritDoc}
     *
     * @param foodId Represents the id of the food
     * @return Available quantity of food from the restaurant
     */
    @Override
    public byte[] getFoodQuantity(final long foodId) {
        final Food food = new Food.FoodBuilder().setId(foodId).build();
        final JsonObject jsonObject = validatorFactory.validate(food, GetFoodValidator.class);

        if (jsonObject.isEmpty()) {
            final Optional<Integer> foodQuantity = restaurantFoodDAO.getFoodQuantity(foodId);

            return foodQuantity.isPresent() ?
                    jsonObject.put("The available food quantity", String.valueOf(foodId)).asBytes() :
                    jsonObject.put(STATUS, "Enter a valid food id").asBytes();
        }

        return jsonObject.asBytes();
    }

    /**
     * {@inheritDoc}
     *
     * @param restaurantId Represents the id of the restaurant
     * @param menucardId   Represents the id of the food type.
     * @return The list of menucard having foods
     */
    @Override
    public byte[] getMenuCard(final long restaurantId, final int menucardId) {
        final Restaurant restaurant = new Restaurant.RestaurantBuilder().setId(restaurantId).build();
        final JsonArray jsonArray = jsonFactory.createArrayNode();
        final JsonObject jsonObject = validatorFactory.validate(restaurant, GetFoodValidator.class);

        if (jsonObject.isEmpty()) {
            final Optional<Collection<Food>> menuCard = restaurantFoodDAO.getMenuCard(restaurantId, menucardId);

            return menuCard.isPresent() ? jsonArray.build(menuCard.get()).asBytes() :
                    jsonArray.add(jsonObject.put(STATUS, "No available foods or enter valid restaurant id")).asBytes();
        }

        return jsonArray.add(jsonObject).asBytes();
    }

    /**
     * {@inheritDoc}
     *
     * @param foodId Represents the id of the food
     * @return The response of removing food from the menucard
     */
    @Override
    public byte[] removeFood(final long foodId) {
        final Food food = new Food.FoodBuilder().setId(foodId).build();
        final JsonObject jsonObject = validatorFactory.validate(food, DeleteFoodValidator.class);

        if (jsonObject.isEmpty()) {
            return restaurantFoodDAO.removeFood(foodId) ?
                    jsonObject.put(STATUS, "Successful food was removed").asBytes() :
                    jsonObject.put(STATUS, "Unsuccessful removing food was failed").asBytes();
        }

        return jsonObject.asBytes();
    }
}