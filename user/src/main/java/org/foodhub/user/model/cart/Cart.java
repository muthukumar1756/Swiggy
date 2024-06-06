package org.foodhub.user.model.cart;

import java.util.Objects;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.foodhub.common.hibernate.validatorgroup.address.GetAddressValidator;
import org.foodhub.common.hibernate.validatorgroup.address.PostAddressValidator;
import org.foodhub.common.hibernate.validatorgroup.cart.PostCartValidator;
import org.foodhub.restaurant.model.food.Food;
import org.foodhub.restaurant.model.restaurant.Restaurant;

/**
 * <p>
 * Represents cart entity with properties and methods.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class Cart {

    @NotNull(message = "Id can't be null", groups = {PostAddressValidator.class, GetAddressValidator.class})
    @Positive(message = "Id can't be negative or zero", groups = {PostAddressValidator.class, GetAddressValidator.class})
    private Long id;
    @NotNull(message = "UserId can't be null", groups = {PostAddressValidator.class, GetAddressValidator.class})
    @Positive(message = "User id can't be negative or zero", groups = {PostAddressValidator.class, GetAddressValidator.class})
    private Long userId;
    @Valid
    @NotNull(message = "Restaurant can't be null", groups = {PostCartValidator.class})
    private Restaurant restaurant;
    @Valid
    @NotNull(message = "Food can't be null", groups = {PostCartValidator.class})
    private Food food;
    @NotNull(message = "Quantity can't be null", groups = {PostAddressValidator.class, GetAddressValidator.class})
    @Positive(message = "Quantity id can't be negative or zero", groups = {PostAddressValidator.class, GetAddressValidator.class})
    private Integer quantity;
    @NotNull(message = "Amount can't be null or zero", groups = {PostAddressValidator.class, GetAddressValidator.class})
    @Positive(message = "Amount id can't be negative or zero", groups = {PostAddressValidator.class, GetAddressValidator.class})
    private Float amount;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public Restaurant getRestaurant() {
        return restaurant;
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

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }

        if (object instanceof Cart) {
            return this.hashCode() == object.hashCode();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class CartBuilder {

        private final Cart cart;

        public CartBuilder() {
            cart = new Cart();
        }

        public CartBuilder setId(final Long id) {
            cart.id = id;

            return this;
        }

        public CartBuilder setUserId(final Long userId) {
            cart.userId = userId;

            return this;
        }

        public CartBuilder setRestaurant(final Restaurant restaurant) {
            cart.restaurant = restaurant;

            return this;
        }

        public CartBuilder setFood(final Food food) {
            cart.food = food;

            return this;
        }

        public CartBuilder setQuantity(final Integer quantity) {
            cart.quantity = quantity;

            return this;
        }

        public CartBuilder setAmount(final Float amount) {
            cart.amount = amount;

            return this;
        }

        public Cart build() {
            return cart;
        }
    }
}
