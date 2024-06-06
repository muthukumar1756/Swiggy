package org.foodhub.restaurant.exception.food;

import org.foodhub.exception.customexception.DefaultException;

/**
 * <p>
 * Handles the exception when unable to access the food count.
 * </p>
 */
public class FoodDataNotFoundException extends DefaultException {

    public FoodDataNotFoundException(final String message) {
        super(message);
    }
}
