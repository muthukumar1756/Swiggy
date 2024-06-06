package org.foodhub.user.database.resultsetextractor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.foodhub.restaurant.model.food.Food;
import org.foodhub.restaurant.model.restaurant.Restaurant;
import org.foodhub.user.model.order.Order;
import org.foodhub.user.exception.order.OrderDataNotFoundException;

/**
 * <p>
 * Methods to extract the result set data of order.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class OrderResultSetExtractor {

    private OrderResultSetExtractor() {
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final OrderResultSetExtractor ORDER_RESULT_SET_HANDLER = new OrderResultSetExtractor();
    }

    /**
     * <p>
     * Gets the instance of the order result set extractor class.
     * </p>
     *
     * @return The order result set extractor instance
     */
    public static OrderResultSetExtractor getInstance() {
        return InstanceHolder.ORDER_RESULT_SET_HANDLER;
    }

    /**
     * <p>
     * Gets the orders placed by the user.
     * </p>
     *
     * @param resultSet Represents the result set data from the executed query.
     * @return List having all the orders placed by the user
     */
    public Optional<Collection<Order>> getOrders(final ResultSet resultSet) {
        try {

            if (resultSet.isBeforeFirst()) {
                final Collection<Order> orderList = new ArrayList<>();

                while (resultSet.next()) {
                    final Food food = new Food.FoodBuilder().setId(resultSet.getLong(4))
                            .setName(resultSet.getString(5)).build();

                    final Restaurant restaurant = new Restaurant.RestaurantBuilder()
                            .setId(resultSet.getLong(6)).setName(resultSet.getString(7)).build();

                    final Order order = new Order.OrderBuilder().setId(resultSet.getLong(1))
                            .setUserId(resultSet.getLong(2)).setCartId(resultSet.getLong(3))
                            .setFood(food).setRestaurant(restaurant).setQuantity(resultSet.getInt(8))
                            .setAmount(resultSet.getFloat(9)).setAddressId(resultSet.getLong(10))
                            .build();

                    orderList.add(order);
                }

                return Optional.of(Collections.unmodifiableCollection(orderList));
            }

            return Optional.empty();
        } catch (SQLException message) {
            throw new OrderDataNotFoundException(message.getMessage());
        }
    }
}
