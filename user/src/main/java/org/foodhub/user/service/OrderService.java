package org.foodhub.user.service;

import java.util.Collection;

import org.foodhub.user.model.order.Order;

/**
 * <p>
 * Provides the service methods for orders.
 * </p>
 *
 * @author Muthu kumar v
 * @version 1.0
 */
public interface OrderService {

    /**
     * <p>
     * places the user orders.
     * </p>
     *
     * @param orderList Represents the list of order items
     * @return The response for placing order
     */
    byte[] placeOrder(final Collection<Order> orderList);

    /**
     * <p>
     * Gets the orders placed by the user.
     * </p>
     *
     * @param userId Represents the id of the user
     * @return The list having all the orders placed by the user
     */
    byte[] getOrders(final long userId);
}