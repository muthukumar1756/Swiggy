package org.foodhub.common.exception;

import org.foodhub.exception.customexception.DefaultException;

/**
 * <p>
 * Handles the exception when unable to process the data with jackson json provider.
 * </p>
 */
public final class JacksonDataConversionException extends DefaultException {

    public JacksonDataConversionException(final String message) {
        super(message);
    }
}
