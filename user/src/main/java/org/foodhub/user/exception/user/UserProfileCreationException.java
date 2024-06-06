package org.foodhub.user.exception.user;

import org.foodhub.exception.customexception.DefaultException;

/**
 * <p>
 * Handles the exception when unable to create user profile.
 * </p>
 */
public class UserProfileCreationException extends DefaultException {

    public UserProfileCreationException(final String message) {
        super(message);
    }
}