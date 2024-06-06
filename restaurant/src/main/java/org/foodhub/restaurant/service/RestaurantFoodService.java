package org.foodhub.restaurant.service;

import org.foodhub.restaurant.model.food.Food;

/**
 * <p>
 * Implements the service of the restaurant related operation.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public interface RestaurantFoodService {

    /**
     * <p>
     * Adds food to the restaurant.
     * </p>
     *
     * @param food         Represents the current food added by the restaurant
     * @param restaurantId Represents the id of the restaurant
     * @return The response of adding the food in the menucard
     */
    byte[] addFood(final Food food, final long restaurantId);

    /**
     * <p>
     * Gets the available food quantity.
     * </p>
     *
     * @param foodId Represents the id of the food
     * @return Available quantity of food from the restaurant
     */
    byte[] getFoodQuantity(final long foodId);

    /**
     * <p>
     * Removes the food from the restaurant menucard.
     * </p>
     *
     * @param foodId Represents the id of the food
     * @return The response of removing food from the menucard
     */
    byte[] removeFood(final long foodId);

    /**
     * <p>
     * Gets the menucard of the selected restaurant.
     * </p>
     *
     * @param restaurantId Represents the id of the restaurant
     * @param menucardId   Represents the id of the food type.
     * @return The list of menucard having foods
     */
    byte[] getMenuCard(final long restaurantId, final int menucardId);
}