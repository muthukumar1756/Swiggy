package org.foodhub.common.exception;

import org.foodhub.exception.customexception.DefaultException;

/**
 * <p>
 * Handles the exception of algorithm not found while hashing the password.
 * </p>
 */
public final class HashAlgorithmNotFoundException extends DefaultException {

    public HashAlgorithmNotFoundException(final String message) {
        super(message);
    }
}
