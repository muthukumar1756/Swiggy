package org.foodhub.database.exception;

import org.foodhub.exception.customexception.DefaultException;

/**
 * <p>
 * Handles the exception when unable to get database connection.
 * </p>
 */
public final class DatabaseConnectionFailureException extends DefaultException {

    public DatabaseConnectionFailureException(final String message) {
        super(message);
    }
}

