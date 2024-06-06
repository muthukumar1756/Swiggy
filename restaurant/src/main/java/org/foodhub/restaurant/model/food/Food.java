package org.foodhub.restaurant.model.food;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import org.foodhub.common.hibernate.validatorgroup.cart.PostCartValidator;
import org.foodhub.common.hibernate.validatorgroup.food.DeleteFoodValidator;
import org.foodhub.common.hibernate.validatorgroup.food.GetFoodValidator;
import org.foodhub.common.hibernate.validatorgroup.food.PostFoodValidator;
import org.foodhub.common.hibernate.validatorgroup.order.PostOrderValdiator;

/**
 * <p>
 * Represents food entity with properties and methods.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class Food {

    @Positive(message = "Food id can't be negative or zero", groups = {GetFoodValidator.class, DeleteFoodValidator.class, PostCartValidator.class, PostOrderValdiator.class})
    private Long id;
    @NotNull(message = "Name can't be null", groups = {PostFoodValidator.class})
    @Pattern(message = "Enter a valid name", regexp = "^[A-Za-z][A-Za-z\\s]{3,20}$", groups = {PostFoodValidator.class})
    private String name;
    @NotNull(message = "Rate can't be null", groups = {PostFoodValidator.class})
    @Positive(message = "Rate can't be negative or zero", groups = {PostFoodValidator.class})
    private Float rate;
    @NotNull(message = "Type can't be null", groups = {PostFoodValidator.class})
    private FoodType type;
    @NotNull(message = "Quantity can't be null", groups = {PostFoodValidator.class})
    @Positive(message = "Quantity can't be negative or zero", groups = {PostFoodValidator.class})
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getRate() {
        return rate;
    }

    public FoodType getType() {
        return type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }

        if (object instanceof Food) {
            return this.hashCode() == object.hashCode();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class FoodBuilder {

        private final Food food;

        public FoodBuilder() {
            food = new Food();
        }

        public FoodBuilder setId(final Long id) {
            food.id = id;

            return this;
        }

        public FoodBuilder setName(final String name) {
            food.name = name;

            return this;
        }

        public FoodBuilder setRate(final Float rate) {
            food.rate = rate;

            return this;
        }

        public FoodBuilder setType(final FoodType type) {
            food.type = type;

            return this;
        }

        public FoodBuilder setQuantity(final Integer quantity) {
            food.quantity = quantity;

            return this;
        }

        public Food build() {
            return food;
        }
    }
}