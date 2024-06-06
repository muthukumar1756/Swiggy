package org.foodhub.restaurant.database.resultsetextractor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.foodhub.restaurant.exception.food.FoodDataNotFoundException;
import org.foodhub.restaurant.exception.food.InvalidFoodDataException;
import org.foodhub.restaurant.exception.food.MenuCardNotFoundException;
import org.foodhub.restaurant.model.food.Food;
import org.foodhub.restaurant.model.food.FoodType;

/**
 * <p>
 * Methods to extract the result set data of restaurant food.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class RestaurantFoodResultSetExtractor {

    private RestaurantFoodResultSetExtractor() {
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final RestaurantFoodResultSetExtractor RESTAURANT_FOOD_RESULT_SET_EXTRACTOR = new RestaurantFoodResultSetExtractor();
    }

    /**
     * <p>
     * Gets the instance of the restaurant result set handler class.
     * </p>
     *
     * @return The restaurant result set handler instance
     */
    public static RestaurantFoodResultSetExtractor getInstance() {
        return InstanceHolder.RESTAURANT_FOOD_RESULT_SET_EXTRACTOR;
    }

    /**
     * <p>
     * Gets the food id from the result set.
     * </p>
     *
     * @param resultSet Represents the result set data from the executed query.
     * @return The id of the food
     */
    public Optional<Long> getFoodId(final ResultSet resultSet) {
        try {
            resultSet.next();

            return Optional.of(resultSet.getLong(1));
        } catch (SQLException message) {
            throw new InvalidFoodDataException(message.getMessage());
        }
    }

    /**
     * <p>
     * Gets the food quantity from the result set.
     * </p>
     *
     * @param resultSet Represents the result set data from the executed query.
     * @return The quantity of the food
     */
    public Optional<Integer> getFoodQuantity(final ResultSet resultSet) {
        try {
            resultSet.next();

            return Optional.of(resultSet.getInt(1));
        } catch (SQLException message) {
            throw new FoodDataNotFoundException(message.getMessage());
        }
    }

    /**
     * <p>
     * Gets the list of foods from the result set.
     * </p>
     *
     * @param resultSet Represents the result set data from the executed query.
     * @return The list of foods
     */
    public Optional<Collection<Food>> getMenuCard(final ResultSet resultSet) {
        try {

            if (resultSet.isBeforeFirst()) {
                final Collection<Food> menucard = new ArrayList<>();

                while (resultSet.next()) {
                    final Food food = new Food.FoodBuilder().setId(resultSet.getLong(1))
                            .setName(resultSet.getString(2)).setRate(resultSet.getFloat(3))
                            .setType(FoodType.getTypeById(resultSet.getInt(4)).get())
                            .setQuantity(resultSet.getInt(5)).build();

                    menucard.add(food);
                }

                return Optional.of(Collections.unmodifiableCollection(menucard));
            }

            return Optional.empty();
        } catch (SQLException message) {
            throw new MenuCardNotFoundException(message.getMessage());
        }
    }
}