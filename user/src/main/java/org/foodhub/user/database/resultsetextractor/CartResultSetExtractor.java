package org.foodhub.user.database.resultsetextractor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.foodhub.restaurant.model.food.Food;
import org.foodhub.restaurant.model.restaurant.Restaurant;
import org.foodhub.user.exception.cart.CartDataNotFoundException;
import org.foodhub.user.model.cart.Cart;

/**
 * <p>
 * Methods to extract the result set data of user cart.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class CartResultSetExtractor {

    private CartResultSetExtractor() {
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final CartResultSetExtractor CART_RESULT_SET_EXTRACTOR = new CartResultSetExtractor();
    }

    /**
     * <p>
     * Gets the instance of the cart result set extractor class.
     * </p>
     *
     * @return The cart result set handler instance
     */
    public static CartResultSetExtractor getInstance() {
        return InstanceHolder.CART_RESULT_SET_EXTRACTOR;
    }

    /**
     * <p>
     * Gets the cart list of the current user.
     * </p>
     *
     * @param resultSet Represents the result set data from the executed query.
     * @return The list of all foods from the user cart
     */
    public Optional<Collection<Cart>> getCart(final ResultSet resultSet) {
        try {

            if (resultSet.isBeforeFirst()) {
                final Collection<Cart> cartList = new ArrayList<>();

                while (resultSet.next()) {
                    final Food food = new Food.FoodBuilder().setId(resultSet.getLong(3))
                            .setName(resultSet.getString(4)).build();

                    final Restaurant restaurant = new Restaurant.RestaurantBuilder()
                            .setId(resultSet.getLong(5)).setName(resultSet.getString(6)).build();

                    final Cart cart = new Cart.CartBuilder().setId(resultSet.getLong(1))
                            .setUserId(resultSet.getLong(2)).setFood(food).setRestaurant(restaurant)
                            .setQuantity(resultSet.getInt(7)).setAmount(resultSet.getFloat(8))
                            .build();

                    cartList.add(cart);
                }

                return Optional.of(Collections.unmodifiableCollection(cartList));
            }

            return Optional.empty();
        } catch (SQLException message) {
            throw new CartDataNotFoundException(message.getMessage());
        }
    }
}
