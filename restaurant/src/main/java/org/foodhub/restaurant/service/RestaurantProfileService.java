package org.foodhub.restaurant.service;

import org.foodhub.restaurant.model.restaurant.Restaurant;
import org.foodhub.restaurant.model.restaurant.RestaurantLoginDetails;
import org.foodhub.restaurant.model.restaurant.RestaurantProfileUpdateDetails;

/**
 * <p>
 * Implements the service of the restaurant related operation.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public interface RestaurantProfileService {

    /**
     * <p>
     * Creates the new restaurant profile.
     * </p>
     *
     * @param restaurant Represents the restaurant
     * @return The response for the restaurant profile creation
     */
    byte[] createRestaurantProfile(final Restaurant restaurant);

    /**
     * <p>
     * Gets the restaurant if the phone_number and password matches.
     * </p>
     *
     * @param restaurantLoginDetails Represents the instance of restaurant login dto
     * @return The restaurant object
     */
    byte[] getRestaurant(final RestaurantLoginDetails restaurantLoginDetails);

    /**
     * <p>
     * Gets the restaurant if the id matches.
     * </p>
     *
     * @param restaurantId Represents the id of the restaurant
     * @return The restaurant object
     */
    byte[] getRestaurantById(final long restaurantId);

    /**
     * <p>
     * Updates the restaurant profile data.
     * </p>
     *
     * @param restaurantProfileUpdateDetails Represents the instance of restaurant profile update dto
     * @return The response for the restaurant profile updation
     */
    byte[] updateRestaurantData(final RestaurantProfileUpdateDetails restaurantProfileUpdateDetails);

    /**
     * <p>
     * Gets all the restaurants
     * </p>
     *
     * @return The list of all restaurants
     */
    byte[] getAllRestaurants();
}
