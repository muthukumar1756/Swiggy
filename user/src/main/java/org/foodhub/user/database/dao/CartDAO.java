package org.foodhub.user.database.dao;

import java.util.Collection;
import java.util.Optional;

import org.foodhub.user.model.cart.Cart;

/**
 * <p>
 * Provides data base service for the user cart.
 * </p>
 *
 * @author Muthu kumar v
 * @version 1.1
 */
public interface CartDAO {

    /**
     * <p>
     * Adds the selected food to the user cart.
     * </p>
     *
     * @param cart Represents the cart of the user
     * @return True if the food is added to the user cart, false otherwise
     */
    boolean addFood(final Cart cart);

    /**
     * <p>
     * Gets all the cart items of the user.
     * </p>
     *
     * @param userId Represents the id 0f the current user
     * @return The list of all foods from the user cart
     */
    Optional<Collection<Cart>> getCart(final long userId);

    /**
     * <p>
     * Removes the selected food from the user cart.
     * </p>
     *
     * @param cartId Represents the id 0f the user cart
     * @return True if the food is removed,false otherwise
     */
    boolean removeFood(final long cartId);

    /**
     * <p>
     * Remove all the foods from the user cart.
     * </p>
     *
     * @param userId Represents the id of the current user
     * @return The true if the cart is cleared, false otherwise
     */
    boolean clearCart(final long userId);
}
