package org.foodhub.user.exception.user.address;

import org.foodhub.exception.customexception.DefaultException;

/**
 * <p>
 * Handles the exception when the address cant uploaded.
 * </p>
 */
public final class AddressDataPersistenceException extends DefaultException {

    public AddressDataPersistenceException(final String message) {
        super(message);
    }
}
