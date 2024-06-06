package org.foodhub.user.service.internal.impl;

import java.util.Collection;
import java.util.Optional;

import org.foodhub.common.hibernate.HibernateEntityValidator;
import org.foodhub.user.database.dao.OrderDAO;
import org.foodhub.user.database.dao.internal.impl.OrderDAOImpl;
import org.foodhub.user.model.order.Order;
import org.foodhub.user.service.OrderService;
import org.foodhub.common.json.JsonArray;
import org.foodhub.common.json.JsonFactory;
import org.foodhub.common.json.JsonObject;
import org.foodhub.common.hibernate.impl.HibernateEntityValidatorImpl;
import org.foodhub.common.hibernate.validatorgroup.order.GetOrderValidator;
import org.foodhub.common.hibernate.validatorgroup.order.PostOrderValdiator;

/**
 * <p>
 * Implements the service of the user order related operation.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class OrderServiceImpl implements OrderService {

    private static final String STATUS = "status";
    private final JsonFactory jsonFactory;
    private final HibernateEntityValidator validatorFactory;
    private final OrderDAO orderDAO;

    private OrderServiceImpl() {
        orderDAO = OrderDAOImpl.getInstance();
        jsonFactory = JsonFactory.getInstance();
        validatorFactory = HibernateEntityValidatorImpl.getInstance();
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final OrderService ORDER_SERVICE = new OrderServiceImpl();
    }

    /**
     * <p>
     * Gets the instance of cart service implementation class.
     * </p>
     *
     * @return The cart service implementation object
     */
    public static OrderService getInstance() {
        return InstanceHolder.ORDER_SERVICE;
    }

    /**
     * {@inheritDoc}
     *
     * @param orderList Represents the list of order items
     * @return The response for placing order
     */
    @Override
    public byte[] placeOrder(final Collection<Order> orderList) {
        final JsonObject jsonObject = validatorFactory.validate(orderList, PostOrderValdiator.class);

        if (jsonObject.isEmpty()) {
            return orderDAO.placeOrder(orderList) ?
                    jsonObject.put(STATUS, "Successful order was placed").asBytes() :
                    jsonObject.put(STATUS, "Unsuccessful order placing failed").asBytes();
        }

        return jsonObject.asBytes();
    }

    /**
     * {@inheritDoc}
     *
     * @param userId Represents the id of the user
     * @return The list having all the orders placed by the user
     */
    @Override
    public byte[] getOrders(final long userId) {
        final Order order = new Order.OrderBuilder().setUserId(userId).build();
        final JsonArray jsonArray = jsonFactory.createArrayNode();
        final JsonObject jsonObject = validatorFactory.validate(order, GetOrderValidator.class);

        if (jsonObject.isEmpty()) {
            final Optional<Collection<Order>> orderList = orderDAO.getOrders(userId);

            return orderList.isPresent() ? jsonArray.build(orderList.get()).asBytes() :
                    jsonArray.add(jsonFactory.createObjectNode()
                            .put(STATUS, "Unsuccessful order list is empty or user id is invalid")).asBytes();
        }

        return jsonArray.add(jsonObject).asBytes();
    }
}
