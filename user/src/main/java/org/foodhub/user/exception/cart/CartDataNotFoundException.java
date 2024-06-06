package org.foodhub.user.exception.cart;

import org.foodhub.exception.customexception.DefaultException;

/**
 * <p>
 * Handles the exception when the user cart is not found.
 * </p>
 */
public final class CartDataNotFoundException extends DefaultException {

    public CartDataNotFoundException(final String message) {
        super(message);
    }
}
