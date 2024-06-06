package org.foodhub.restaurant.exception.restaurant;

import org.foodhub.exception.customexception.DefaultException;

/**
 * <p>
 * Handles the exception when unable to load the restaurant.
 * </p>
 */
public class RestaurantDataNotFoundException extends DefaultException {

    public RestaurantDataNotFoundException(final String message) {
        super(message);
    }
}