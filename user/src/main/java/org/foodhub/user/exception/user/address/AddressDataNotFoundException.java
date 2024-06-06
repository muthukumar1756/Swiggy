package org.foodhub.user.exception.user.address;

import org.foodhub.exception.customexception.DefaultException;

/**
 * <p>
 * Handles the exception when the address data is not found.
 * </p>
 */
public class AddressDataNotFoundException extends DefaultException {

    public AddressDataNotFoundException(final String message) {
        super(message);
    }
}