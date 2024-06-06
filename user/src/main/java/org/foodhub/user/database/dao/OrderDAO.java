package org.foodhub.user.database.dao;

import org.foodhub.user.model.order.Order;

import java.util.Collection;
import java.util.Optional;

/**
 * <p>
 * Provides data base service for the user order.
 * </p>
 *
 * @author Muthu kumar v
 * @version 1.1
 */
public interface OrderDAO {

    /**
     * <p>
     * places the user orders.
     * </p>
     *
     * @return True if the order is placed, false otherwise
     */
    boolean placeOrder(final Collection<Order> orderList);

    /**
     * <p>
     * Gets the orders placed by the user.
     * </p>
     *
     * @return List having all the orders placed by the user
     */
    Optional<Collection<Order>> getOrders(final long userId);
}
