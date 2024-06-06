package org.foodhub.user.service;

import org.foodhub.user.model.cart.Cart;

/**
 * <p>
 * Provides the services for the user cart.
 * </p>
 *
 * @author Muthu kumar v
 * @version 1.0
 */
public interface CartService {

    /**
     * <p>
     * Adds the selected food to the user cart.
     * </p>
     *
     * @param cart Represents the cart of the user
     * @return The Response of the food adding to the cart
     */
    byte[] addFood(final Cart cart);

    /**
     * <p>
     * Gets the cart of the current user.
     * </p>
     *
     * @param userId Represents the id 0f the current user
     * @return The list of all foods from the user cart
     */
    byte[] getCart(final long userId);

    /**
     * <p>
     * Removes the selected food from the user cart.
     * </p>
     *
     * @param cartId Represents the id 0f the user cart
     * @return The response for removing the food from the cart
     */
    byte[] removeFood(final long cartId);

    /**
     * <p>
     * Remove all the foods from the user cart.
     * </p>
     *
     * @param userId Represents the id of the current user
     * @return The response for clearing the user cart
     */
    byte[] clearCart(final long userId);
}
