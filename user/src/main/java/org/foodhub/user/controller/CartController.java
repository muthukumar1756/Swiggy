package org.foodhub.user.controller;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;

import org.foodhub.user.model.cart.Cart;
import org.foodhub.user.service.internal.impl.CartServiceImpl;
import org.foodhub.user.service.CartService;

/**
 * <p>
 * Manages user cart-related operations and is responsible for receiving user input through a REST API and processing it.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
@Path("/cart")
public final class CartController {

    private final CartService cartService;

    private CartController() {
        cartService = CartServiceImpl.getInstance();
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final CartController CART_CONTROLLER = new CartController();
    }

    /**
     * <p>
     * Gets the instance of the cart controller class.
     * </p>
     *
     * @return The cart controller object
     */
    public static CartController getInstance() {
        return InstanceHolder.CART_CONTROLLER;
    }

    /**
     * <p>
     * Adds the selected food to the user cart.
     * </p>
     *
     * @param cart Represents the cart of the user
     * @return byte array of json object
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public byte[] addFood(final Cart cart) {
        return cartService.addFood(cart);
    }

    /**
     * <p>
     * Gets the cart of the user.
     * </p>
     *
     * @param userId Represents the id of the user
     * @return byte array of json object
     */
    @Path("/{userId}")
    @GET
    @Produces("application/json")
    public byte[] getCart(@PathParam("userId") final long userId) {
        return cartService.getCart(userId);
    }

    /**
     * <p>
     * Removes the food selected by the user.
     * </p>
     *
     * @param cartId Represents the id of the user cart
     * @return byte array of json object
     */
    @Path("/{cartId}")
    @DELETE
    @Produces("application/json")
    public byte[] removeFood(@PathParam("cartId") final long cartId) {
        return cartService.removeFood(cartId);
    }

    /**
     * <p>
     * Remove all the foods from the user cart.
     * </p>
     *
     * @param userId Represents the id of the user
     * @return byte array of json object
     */
    @Path("clear/{userId}")
    @DELETE
    @Produces("application/json")
    public byte[] clearCart(@PathParam("userId") final long userId) {
        return cartService.clearCart(userId);
    }
}