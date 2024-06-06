package org.foodhub.user.exception.order;

import org.foodhub.exception.customexception.DefaultException;

/**
 * <p>
 * Handles the exception when the user orders is not found.
 * </p>
 */
public final class OrderDataNotFoundException  extends DefaultException {

    public OrderDataNotFoundException(final String message) {
        super(message);
    }
}
