package org.foodhub.user.model.order;

import java.util.Objects;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.foodhub.common.hibernate.validatorgroup.order.GetOrderValidator;
import org.foodhub.common.hibernate.validatorgroup.order.PostOrderValdiator;
import org.foodhub.restaurant.model.food.Food;
import org.foodhub.restaurant.model.restaurant.Restaurant;

/**
 * <p>
 * Represents order entity with properties and methods.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class Order {

    private Long id;
    @NotNull(message = "UserId can't be null", groups = {PostOrderValdiator.class, GetOrderValidator.class})
    @Positive(message = "User id can't be negative or zero", groups = {PostOrderValdiator.class, GetOrderValidator.class})
    private Long userId;
    @NotNull(message = "CartId can't be null", groups = {PostOrderValdiator.class})
    @Positive(message = "Cart id can't be negative", groups = {PostOrderValdiator.class})
    private Long cartId;
    @Valid
    @NotNull(message = "Restaurant can't be null", groups = {PostOrderValdiator.class})
    private Restaurant restaurant;
    @Valid
    @NotNull(message = "Food can't be null", groups = {PostOrderValdiator.class})
    private Food food;
    @NotNull(message = "Quantity can't be null", groups = {PostOrderValdiator.class})
    @Positive(message = "Quantity can't be negative or zero", groups = {PostOrderValdiator.class})
    private Integer quantity;
    @NotNull(message = "Amount can't be null", groups = {PostOrderValdiator.class})
    @Positive(message = "Amount can't be negative or zero", groups = {PostOrderValdiator.class})
    private Float amount;
    @NotNull(message = "Address id can't be null", groups = {PostOrderValdiator.class})
    @Positive(message = "Address id can't be negative or zero", groups = {PostOrderValdiator.class})
    private Long addressId;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public Long getCartId() {
        return cartId;
    }

    public Food getFood() {
        return food;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Float getAmount() {
        return amount;
    }

    public Long getAddressId() {
        return addressId;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }

        if (object instanceof Order) {
            return this.hashCode() == object.hashCode();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class OrderBuilder {

        private final Order order;

        public OrderBuilder() {
            order = new Order();
        }

        public OrderBuilder setId(final Long id) {
            order.id = id;

            return this;
        }

        public OrderBuilder setUserId(final Long userId) {
            order.userId = userId;

            return this;
        }

        public OrderBuilder setCartId(final Long cartId) {
            order.cartId = cartId;

            return this;
        }

        public OrderBuilder setRestaurant(final Restaurant restaurant) {
            order.restaurant = restaurant;

            return this;
        }

        public OrderBuilder setFood(final Food food) {
            order.food = food;

            return this;
        }

        public OrderBuilder setQuantity(final Integer quantity) {
            order.quantity = quantity;

            return this;
        }

        public OrderBuilder setAmount(final Float amount) {
            order.amount = amount;

            return this;
        }

        public OrderBuilder setAddressId(final Long addressId) {
            order.addressId = addressId;

            return this;
        }

        public Order build() {
            return order;
        }
    }
}