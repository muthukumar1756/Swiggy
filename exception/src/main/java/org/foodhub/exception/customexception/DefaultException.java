package org.foodhub.exception.customexception;

/**
 * <p>
 * Handles the exception when the any action in the swiggy application fails.
 * </p>
 */
public class DefaultException extends RuntimeException {

    public DefaultException(final String message) {
        super(message);
    }
}