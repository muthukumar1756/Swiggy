package org.foodhub.restaurant.database.dao;

import org.foodhub.restaurant.model.restaurant.Restaurant;

import java.util.Collection;
import java.util.Optional;

/**
 * <p>
 * Provides data base service for the restaurant profile related operation
 * </p>
 *
 * @author Muthu kumar v
 * @version 1.1
 */
public interface RestaurantProfileDAO {

    /**
     * <p>
     * Creates the new restaurant profile.
     * </p>
     *
     * @param restaurant Represents the restaurant details
     * @return True if restaurant profile is successfully created, false otherwise
     */
    boolean createRestaurantProfile(final Restaurant restaurant);

    /**
     * <p>
     * Checks if the restaurant exists.
     * </p>
     *
     * @return True if restaurant is exist, false otherwise
     */
    boolean isRestaurantExist(final String phoneNumber, final String emailId);

    /**
     * <p>
     * Gets the restaurant if the phone_number and password matches.
     * </p>
     *
     * @param restaurantDataType Represents the type of data of the restaurant
     * @param restaurantData     Represents the data of the restaurant
     * @param password           Represents the password of the restaurant
     * @return The restaurant object
     */
    Optional<Restaurant> getRestaurant(final String restaurantDataType, final String restaurantData,
                                       final String password);

    /**
     * <p>
     * Gets the restaurant if the id matches.
     * </p>
     *
     * @param restaurantId Represents the id of the restaurant
     * @return The restaurant object
     */
    Optional<Restaurant> getRestaurantById(final long restaurantId);

    /**
     * <p>
     * Updates the data of the current restaurant user.
     * </p>
     *
     * @param restaurantId   Represents the id of the restaurant
     * @param restaurantData Represents the data of the restaurant to be updated
     * @param type           Represents the type of data of the restaurant to be updated
     * @return True if data is updated, false otherwise
     */
    boolean updateRestaurantProfile(final long restaurantId, final String type, final String restaurantData);

    /**
     * <p>
     * Gets all the restaurants
     * </p>
     *
     * @return The list of all restaurants
     */
    Optional<Collection<Restaurant>> getAllRestaurants();

}
