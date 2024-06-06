package org.foodhub.user.exception.order;

import org.foodhub.exception.customexception.DefaultException;

/**
 * <p>
 * Handles the exception when the user order placement is failed.
 * </p>
 */
public class OrderPlacementFailureException extends DefaultException {

    public OrderPlacementFailureException(final String message) {
        super(message);
    }
}
