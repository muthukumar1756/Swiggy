package org.foodhub.user.database.dao.internal.impl;

import java.util.Collection;
import java.util.Optional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.foodhub.database.connection.DataBaseConnection;
import org.foodhub.user.database.persistenceservice.OrderPersistenceService;
import org.foodhub.user.exception.cart.CartUpdateFailureException;
import org.foodhub.user.database.resultsetextractor.OrderResultSetExtractor;
import org.foodhub.user.database.dao.OrderDAO;
import org.foodhub.user.exception.order.OrderDataNotFoundException;
import org.foodhub.user.exception.order.OrderPlacementFailureException;
import org.foodhub.user.model.order.Order;

/**
 * <p>
 * Implements the data base service of the order related operation
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class OrderDAOImpl implements OrderDAO {

    private final OrderPersistenceService orderpersistenceService;
    final OrderResultSetExtractor orderResultSetExtractor;
    private final Connection connection;

    private OrderDAOImpl() {
        orderpersistenceService = OrderPersistenceService.getInstance();
        orderResultSetExtractor = OrderResultSetExtractor.getInstance();
        connection = DataBaseConnection.get();
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final OrderDAO ORDER_DAO = new OrderDAOImpl();
    }

    /**
     * <p>
     * Gets the instance of the order database implementation class.
     * </p>
     *
     * @return The order database service implementation object
     */
    public static OrderDAO getInstance() {
        return InstanceHolder.ORDER_DAO;
    }

    /**
     * {@inheritDoc}
     *
     * @param orderList Represents the list of order items
     * @return True if the order is placed, false otherwise
     */
    @Override
    public boolean placeOrder(final Collection<Order> orderList) {
        try {
            connection.setAutoCommit(false);
            final String query = orderpersistenceService.getPlaceOrderQuery();

            try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

                for (final Order order : orderList) {
                    preparedStatement.setLong(1, order.getCartId());
                    preparedStatement.setLong(2, order.getUserId());
                    preparedStatement.setLong(3, order.getAddressId());
                    preparedStatement.addBatch();
                }
                preparedStatement.executeBatch();
                updateCartStatus(orderList);
                connection.commit();

                return true;
            } catch (SQLException message) {
                connection.rollback();
                throw new OrderPlacementFailureException(message.getMessage());
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException message) {
            throw new OrderPlacementFailureException(message.getMessage());
        }
    }

    /**
     * <p>
     * Updates the status of the cart.
     * </p>
     *
     * @param orderList Represents the list of order items
     */
    private void updateCartStatus(final Collection<Order> orderList) {
        final String query = orderpersistenceService.updateCartStatus();

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            for (final Order order : orderList) {
                preparedStatement.setLong(1, order.getCartId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException message) {
            throw new CartUpdateFailureException(message.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return The list of user orders
     */
    @Override
    public Optional<Collection<Order>> getOrders(final long userId) {
        final String query = orderpersistenceService.getOrders(userId);

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return orderResultSetExtractor.getOrders(preparedStatement.executeQuery());
        } catch (SQLException message) {
            throw new OrderDataNotFoundException(message.getMessage());
        }
    }
}