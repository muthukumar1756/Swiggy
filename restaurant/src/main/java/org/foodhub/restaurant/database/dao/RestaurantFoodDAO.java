package org.foodhub.restaurant.database.dao;

import java.util.Collection;
import java.util.Optional;

import org.foodhub.restaurant.model.food.Food;

/**
 * <p>
 * Provides data base service for the restaurant food related operation.
 * </p>
 *
 * @author Muthu kumar v
 * @version 1.1
 */
public interface RestaurantFoodDAO {

    /**
     * <p>
     * Adds the food to the restaurant
     * </p>
     *
     * @param food         Represents the food item to be added by the restaurant
     * @param restaurantId Represents the id of the restaurant
     * @return True if food is successfully added, false otherwise
     */
    boolean addFood(final Food food, final long restaurantId);

    /**
     * <p>
     * Removes the food item from the restaurant menucard.
     * </p>
     *
     * @param foodId Represents the id of the food to be removed
     * @return True if food is successfully removed, false otherwise
     */
    boolean removeFood(final long foodId);

    /**
     * <p>
     * Retrieves the available quantity of a specific food item.
     * </p>
     *
     * @param foodId Represents the id of the food
     * @return Available quantity of food from the restaurant
     */
    Optional<Integer> getFoodQuantity(final long foodId);

    /**
     * <p>
     * Retrieves the menu card of the selected restaurant.
     * </p>
     *
     * @param restaurantId Represents the id of the restaurant
     * @return The list of menu items available at the restaurant.
     */
    Optional<Collection<Food>> getMenuCard(final long restaurantId, final int menucardId);
}