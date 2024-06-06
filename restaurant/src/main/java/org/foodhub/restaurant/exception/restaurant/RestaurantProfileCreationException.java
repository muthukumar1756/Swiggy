package org.foodhub.restaurant.exception.restaurant;

import org.foodhub.exception.customexception.DefaultException;

/**
 * <p>
 * Handles the exception when unable to create restaurant profile.
 * </p>
 */
public class RestaurantProfileCreationException extends DefaultException {

    public RestaurantProfileCreationException(final String message) {
        super(message);
    }
}