package org.foodhub.user.service.internal.impl;

import java.util.Collection;
import java.util.Optional;

import org.foodhub.user.database.dao.CartDAO;
import org.foodhub.user.database.dao.internal.impl.CartDAOImpl;
import org.foodhub.user.model.cart.Cart;
import org.foodhub.user.service.CartService;
import org.foodhub.common.hibernate.HibernateEntityValidator;
import org.foodhub.common.json.JsonArray;
import org.foodhub.common.json.JsonFactory;
import org.foodhub.common.json.JsonObject;
import org.foodhub.common.hibernate.impl.HibernateEntityValidatorImpl;
import org.foodhub.common.hibernate.validatorgroup.cart.ClearCartValidator;
import org.foodhub.common.hibernate.validatorgroup.cart.DeleteCartValidator;
import org.foodhub.common.hibernate.validatorgroup.cart.GetCartValidator;
import org.foodhub.common.hibernate.validatorgroup.cart.PostCartValidator;

/**
 * <p>
 * Implements the service of the user cart related operation.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class CartServiceImpl implements CartService {

    private static final String STATUS = "status";
    private final JsonFactory jsonFactory;
    private final HibernateEntityValidator validatorFactory;
    private final CartDAO cartDAO;

    private CartServiceImpl() {
        cartDAO = CartDAOImpl.getInstance();
        jsonFactory = JsonFactory.getInstance();
        validatorFactory = HibernateEntityValidatorImpl.getInstance();
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final CartService CART_SERVICE = new CartServiceImpl();
    }

    /**
     * <p>
     * Gets the cart service implementation class object.
     * </p>
     *
     * @return The cart service implementation object
     */
    public static CartService getInstance() {
        return InstanceHolder.CART_SERVICE;
    }

    /**
     * {@inheritDoc}
     *
     * @param cart Represents the cart of the user
     * @return The Response of the food adding to the cart
     */
    @Override
    public byte[] addFood(final Cart cart) {
        final JsonObject jsonObject = validatorFactory.validate(cart, PostCartValidator.class);

        if (jsonObject.isEmpty()) {
            return cartDAO.addFood(cart) ? jsonObject.put(STATUS, "Successful cart item added").asBytes() :
                    jsonObject.put(STATUS, "Unsuccessful adding cart item failed enter a valid id").asBytes();
        }

        return jsonObject.asBytes();
    }

    /**
     * {@inheritDoc}
     *
     * @param userId Represents the id of the user
     * @return The list of all foods from the user cart
     */
    @Override
    public byte[] getCart(final long userId) {
        final Cart cart = new Cart.CartBuilder().setUserId(userId).build();
        final JsonArray jsonArray = jsonFactory.createArrayNode();
        final JsonObject jsonObject = validatorFactory.validate(cart, GetCartValidator.class);

        if (jsonObject.isEmpty()) {
            final Optional<Collection<Cart>> cartList = cartDAO.getCart(userId);

            return cartList.isPresent() ? jsonArray.build(cartList.get()).asBytes() :
                    jsonArray.add(jsonFactory.createObjectNode()
                            .put(STATUS, "Your cart is empty or user id is invalid")).asBytes();
        }

        return jsonArray.add(jsonObject).asBytes();
    }

    /**
     * {@inheritDoc}
     *
     * @param cartId Represents the id of the user cart
     * @return The response for removing the food from the cart
     */
    @Override
    public byte[] removeFood(final long cartId) {
        final Cart cart = new Cart.CartBuilder().setId(cartId).build();
        final JsonObject jsonObject = validatorFactory.validate(cart, DeleteCartValidator.class);

        if (jsonObject.isEmpty()) {
            return cartDAO.removeFood(cartId) ?
                    jsonObject.put(STATUS, "Successful food was removed").asBytes() :
                    jsonObject.put(STATUS, "Unsuccessful removing food was failed enter a valid id").asBytes();
        }

        return jsonObject.asBytes();
    }

    /**
     * {@inheritDoc}
     *
     * @param userId Represents the id of the user
     * @return The response for clearing the user cart
     */
    @Override
    public byte[] clearCart(final long userId) {
        final Cart cart = new Cart.CartBuilder().setUserId(userId).build();
        final JsonObject jsonObject = validatorFactory.validate(cart, ClearCartValidator.class);

        if (jsonObject.isEmpty()) {
            return cartDAO.clearCart(userId) ?
                    jsonObject.put(STATUS, "Successful cart was cleared").asBytes() :
                    jsonObject.put(STATUS, "Unsuccessful clearing cart was failed enter a valid id").asBytes();
        }

        return jsonObject.asBytes();
    }
}