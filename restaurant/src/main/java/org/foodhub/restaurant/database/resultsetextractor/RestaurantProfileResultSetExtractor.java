package org.foodhub.restaurant.database.resultsetextractor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.foodhub.restaurant.exception.food.InvalidFoodDataException;
import org.foodhub.restaurant.exception.restaurant.RestaurantDataNotFoundException;
import org.foodhub.restaurant.model.restaurant.Restaurant;

/**
 * <p>
 * Methods to extract the result set data of restaurant profile info.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class RestaurantProfileResultSetExtractor {

    private RestaurantProfileResultSetExtractor() {
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final RestaurantProfileResultSetExtractor RESTAURANT_PROFILE_RESULT_SET_HANDLER = new RestaurantProfileResultSetExtractor();
    }

    /**
     * <p>
     * Gets the instance of the restaurant profile result set handler class.
     * </p>
     *
     * @return The restaurant result set handler instance
     */
    public static RestaurantProfileResultSetExtractor getInstance() {
        return InstanceHolder.RESTAURANT_PROFILE_RESULT_SET_HANDLER;
    }

    /**
     * <p>
     * Check for the restaurant is exist
     * </p>
     *
     * @param resultSet Represents the result set data from the executed query.
     * @return True if the restaurant is exist, false otherwise
     */
    public boolean isRestaurantExist(final ResultSet resultSet) {
        try {
            resultSet.next();

            return 0 < resultSet.getInt(1);
        } catch (SQLException message) {
            throw new RestaurantDataNotFoundException(message.getMessage());
        }
    }

    /**
     * <p>
     * Gets the restaurant data from the result set.
     * </p>
     *
     * @param resultSet Represents the result set data from the executed query.
     * @return The restaurant object
     */
    public Optional<Restaurant> getRestaurant(final ResultSet resultSet) {
        try {

            if (resultSet.next()) {
                final Restaurant restaurant = new Restaurant.RestaurantBuilder().setId(resultSet.getLong(1))
                        .setName(resultSet.getString(2)).setPhoneNumber(resultSet.getString(3))
                        .setEmailId(resultSet.getString(4)).setPassword(resultSet.getString(5))
                        .build();

                return Optional.of(restaurant);
            }

            return Optional.empty();
        } catch (SQLException message) {
            throw new RestaurantDataNotFoundException(message.getMessage());
        }
    }

    /**
     * <p>
     * Gets the restaurants list from the result set.
     * </p>
     *
     * @param resultSet Represents the result set data from the executed query.
     * @return The list of restaurants
     */
    public Optional<Collection<Restaurant>> getAllRestaurants(final ResultSet resultSet) {
        try {

            if (resultSet.isBeforeFirst()) {
                final Collection<Restaurant> restaurants = new ArrayList<>();

                while (resultSet.next()) {
                    final Restaurant restaurant = new Restaurant.RestaurantBuilder()
                            .setId(resultSet.getLong(1)).setName(resultSet.getString(2)).build();

                    restaurants.add(restaurant);
                }

                return Optional.of(Collections.unmodifiableCollection(restaurants));
            }

            return Optional.empty();
        } catch (SQLException message) {
            throw new InvalidFoodDataException(message.getMessage());
        }
    }
}