package org.foodhub.restaurant.controller;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;

import org.foodhub.restaurant.model.food.Food;
import org.foodhub.restaurant.service.internal.impl.RestaurantFoodServiceImpl;
import org.foodhub.restaurant.service.RestaurantFoodService;

/**
 * <p>
 * Manages restaurant food related operations and is responsible for receiving input through a REST API and processing it.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
@Path("/restaurant/food")
public final class RestaurantFoodController {

    private final RestaurantFoodService restaurantFoodService;

    private RestaurantFoodController() {
        restaurantFoodService = RestaurantFoodServiceImpl.getInstance();
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final RestaurantFoodController RESTAURANT_FOOD_CONTROLLER = new RestaurantFoodController();
    }

    /**
     * <p>
     * Gets the instance of restaurant food controller.
     * </p>
     *
     * @return The restaurant food controller object
     */
    public static RestaurantFoodController getInstance() {
        return InstanceHolder.RESTAURANT_FOOD_CONTROLLER;
    }

    /**
     * <p>
     * Adds food to the restaurant.
     * </p>
     *
     * @param food         Represents the food item to be added.
     * @param restaurantId Represents the ID of the restaurant.
     * @return A byte array containing the JSON response.
     */
    @Path("/{restaurantId}")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public byte[] addFood(final Food food, @PathParam("restaurantId") final long restaurantId) {
        return restaurantFoodService.addFood(food, restaurantId);
    }

    /**
     * <p>
     * Removes the food from the restaurant.
     * </p>
     *
     * @param foodId Represents the ID of the food to be removed.
     * @return A byte array containing the JSON response.
     */
    @Path("/{foodId}")
    @DELETE
    @Produces("application/json")
    public byte[] removeFood(@PathParam("foodId") final long foodId) {
        return restaurantFoodService.removeFood(foodId);
    }

    /**
     * <p>
     * Retrieves the available quantity of the chosen food.
     * </p>
     *
     * @param foodId Represents the ID of the food.
     * @return A byte array containing the JSON response.
     */
    @Path("/{foodId}")
    @GET
    @Produces("application/json")
    public byte[] getFoodQuantity(@PathParam("foodId") final long foodId) {
        return restaurantFoodService.getFoodQuantity(foodId);
    }

    /**
     * <p>
     * Retrieves the menu card from the restaurant.
     * </p>
     *
     * @param restaurantId Represents the ID of the restaurant.
     * @param menucardId   Represents the ID of the menu card.
     * @return A byte array containing the JSON response.
     */
    @Path("/{restaurantId}/{foodTypeId}")
    @GET
    @Produces("application/json")
    public byte[] getMenuCard(@PathParam("restaurantId") final long restaurantId,
                              @PathParam("foodTypeId") final int menucardId) {
        return restaurantFoodService.getMenuCard(restaurantId, menucardId);
    }
}