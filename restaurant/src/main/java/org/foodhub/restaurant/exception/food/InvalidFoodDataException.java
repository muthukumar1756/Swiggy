package org.foodhub.restaurant.exception.food;

import org.foodhub.exception.customexception.DefaultException;

/**
 * <p>
 * Handles the exception when unable to load the food.
 * </p>
 */
public class InvalidFoodDataException extends DefaultException {

    public InvalidFoodDataException(final String message) {
        super(message);
    }
}
