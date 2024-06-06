package org.foodhub.user.model.cart;

import java.util.Optional;

/**
 * <p>
 * Defines the status of the item in cart.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public enum CartStatus {

    IN_CART(1),
    ORDER_PLACED(2);

    private final Integer id;

    public static Integer getId(final CartStatus cartStatus) {
        return cartStatus.id;
    }

    CartStatus(final Integer id) {
        this.id = id;
    }

    /**
     * <p>
     * Gets the status type of cart by using id
     * </p>
     *
     * @param id Represents the id of the enum type
     * @return The status type of cart
     */
    public static Optional<CartStatus> getTypeById(final Integer id) {
        return switch (id) {
            case 1 -> Optional.of(CartStatus.IN_CART);
            case 2 -> Optional.of(CartStatus.ORDER_PLACED);
            default -> Optional.empty();
        };
    }
}
