package org.foodhub.user.exception.cart;

import org.foodhub.exception.customexception.DefaultException;

/**
 * <p>
 * Handles the exception when unable to update the user cart.
 * </p>
 */
public final class CartUpdateFailureException extends DefaultException {

    public CartUpdateFailureException(final String message) {
        super(message);
    }
}
