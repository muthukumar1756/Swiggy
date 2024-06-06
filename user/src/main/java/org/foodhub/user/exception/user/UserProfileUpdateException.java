package org.foodhub.user.exception.user;

import org.foodhub.exception.customexception.DefaultException;

/**
 * <p>
 * Handles the exception when the user profile update action fails
 * </p>
 */
public class UserProfileUpdateException extends DefaultException {

    public UserProfileUpdateException(final String message) {
        super(message);
    }
}
