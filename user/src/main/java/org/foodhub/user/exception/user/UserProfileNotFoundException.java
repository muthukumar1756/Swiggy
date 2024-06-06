package org.foodhub.user.exception.user;

import org.foodhub.exception.customexception.DefaultException;

/**
 * <p>
 * Handles the exception when the user data is not found.
 * </p>
 */
public class UserProfileNotFoundException extends DefaultException {

    public UserProfileNotFoundException(final String message) {
        super(message);
    }
}