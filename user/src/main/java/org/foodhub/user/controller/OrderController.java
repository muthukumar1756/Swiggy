package org.foodhub.user.controller;

import java.util.Collection;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;

import org.foodhub.user.model.order.Order;
import org.foodhub.user.service.OrderService;
import org.foodhub.user.service.internal.impl.OrderServiceImpl;

/**
 * <p>
 * Manages order related operations and is responsible for receiving user input through a REST API and processing it.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
@Path("/order")
public final class OrderController {

    private final OrderService orderService;

    private OrderController() {
        orderService = OrderServiceImpl.getInstance();
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final OrderController ORDER_CONTROLLER = new OrderController();
    }

    /**
     * <p>
     * Gets the object of the cart controller class.
     * </p>
     *
     * @return The cart controller object
     */
    public static OrderController getInstance() {
        return InstanceHolder.ORDER_CONTROLLER;
    }

    /**
     * <p>
     * places the user orders.
     * </p>
     *
     * @param orderList Represents the list of order items
     * @return byte array of json response
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public byte[] placeOrder(final Collection<Order> orderList) {
        return orderService.placeOrder(orderList);
    }

    /**
     * <p>
     * Gets the orders placed by the user.
     * </p>
     *
     * @param userId Represents the id of the user
     * @return byte array of json response
     */
    @Path("/{userId}")
    @GET
    @Produces("application/json")
    public byte[] getOrders(@PathParam("userId") final long userId) {
        return orderService.getOrders(userId);
    }
}