package org.foodhub.user.database.dao.internal.impl;

import java.util.Collection;
import java.util.Optional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.foodhub.database.connection.DataBaseConnection;
import org.foodhub.user.database.persistenceservice.CartPersistenceService;
import org.foodhub.user.exception.cart.CartDataNotFoundException;
import org.foodhub.user.exception.cart.CartUpdateFailureException;
import org.foodhub.user.database.resultsetextractor.CartResultSetExtractor;
import org.foodhub.user.database.dao.CartDAO;
import org.foodhub.user.model.cart.Cart;

/**
 * <p>
 * Implements the data base service for cart related operation.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class CartDAOImpl implements CartDAO {

    private final CartPersistenceService cartPersistenceService;
    private final CartResultSetExtractor cartResultSetExtractor;
    private final Connection connection;

    private CartDAOImpl() {
        cartPersistenceService = CartPersistenceService.getInstance();
        cartResultSetExtractor = CartResultSetExtractor.getInstance();
        connection = DataBaseConnection.get();
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final CartDAO CART_DAO = new CartDAOImpl();
    }

    /**
     * <p>
     * Gets the instance of the cart database implementation class.
     * </p>
     *
     * @return The cart database service implementation object
     */
    public static CartDAO getInstance() {
        return InstanceHolder.CART_DAO;
    }

    /**
     * {@inheritDoc}
     *
     * @param cart Represents the cart of the user
     * @return True if the food is added to the user cart, false otherwise
     */
    @Override
    public boolean addFood(final Cart cart) {
        final String query = cartPersistenceService.getAddFoodQuery(cart);

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return 0 < preparedStatement.executeUpdate();
        } catch (SQLException message) {
            throw new CartUpdateFailureException(message.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param userId Represents the id 0f the current user
     * @return The list of all foods from the user cart
     */
    @Override
    public Optional<Collection<Cart>> getCart(final long userId) {
        final String query = cartPersistenceService.getCartQuery(userId);

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return cartResultSetExtractor.getCart(preparedStatement.executeQuery());
        } catch (SQLException message) {
            throw new CartDataNotFoundException(message.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param cartId Represents the id 0f the user cart
     * @return True if the food is removed,false otherwise
     */
    @Override
    public boolean removeFood(final long cartId) {
        final String query = cartPersistenceService.getRemoveFoodQuery(cartId);

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return 0 < preparedStatement.executeUpdate();
        } catch (SQLException message) {
            throw new CartUpdateFailureException(message.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param userId Represents the id of the current user
     * @return The true if the cart is cleared, false otherwise
     */
    @Override
    public boolean clearCart(final long userId) {
        final String query = cartPersistenceService.getClearCartQuery(userId);

        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            return 0 < preparedStatement.executeUpdate();
        } catch (SQLException message) {
            throw new CartUpdateFailureException(message.getMessage());
        }
    }
}