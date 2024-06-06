package org.foodhub.restaurant.exception.restaurant;

import org.foodhub.exception.customexception.DefaultException;

/**
 * <p>
 * Handles the exception when the restaurant profile update action fails
 * </p>
 */
public class RestaurantProfileUpdateException extends DefaultException {

    public RestaurantProfileUpdateException(final String message) {
        super(message);
    }
}
