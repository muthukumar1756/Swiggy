package org.foodhub.restaurant.exception.food;

import org.foodhub.exception.customexception.DefaultException;

/**
 * <p>
 * Handles the exception when unable to found the menucard of the selected restaurant.
 * </p>
 */
public class MenuCardNotFoundException extends DefaultException {

    public MenuCardNotFoundException(final String message) {
        super(message);
    }
}
