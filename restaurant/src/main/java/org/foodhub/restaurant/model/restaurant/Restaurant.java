package org.foodhub.restaurant.model.restaurant;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import jakarta.validation.constraints.Positive;
import org.foodhub.common.hibernate.validatorgroup.Restaurant.GetRestaurantValidator;
import org.foodhub.common.hibernate.validatorgroup.Restaurant.LoginRestaurantValidator;
import org.foodhub.common.hibernate.validatorgroup.Restaurant.CreateRestaurantValidator;
import org.foodhub.common.hibernate.validatorgroup.Restaurant.UpdateRestaurantValidator;
import org.foodhub.common.hibernate.validatorgroup.cart.PostCartValidator;
import org.foodhub.common.hibernate.validatorgroup.food.GetFoodValidator;
import org.foodhub.common.hibernate.validatorgroup.food.PostFoodValidator;
import org.foodhub.common.hibernate.validatorgroup.order.PostOrderValdiator;

/**
 * <p>
 * Represents restaurant entity with properties and methods.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class Restaurant {

    @Positive(message = "Restaurant id can't be negative or zero", groups = {GetRestaurantValidator.class, UpdateRestaurantValidator.class, GetFoodValidator.class, PostFoodValidator.class, PostCartValidator.class, PostOrderValdiator.class})
    private Long id;
    @NotNull(message = "Name can't be null", groups = {CreateRestaurantValidator.class})
    @Pattern(message = "Enter a valid name", regexp = "^[A-Za-z][A-Za-z\\s]{3,20}$", groups = {CreateRestaurantValidator.class, UpdateRestaurantValidator.class})
    private String name;
    @NotNull(message = "PhoneNumber can't be null", groups = {CreateRestaurantValidator.class})
    @Pattern(message = "Enter a valid phone number", regexp = "^(0/91)?[6789]\\d{9}$", groups = {CreateRestaurantValidator.class, UpdateRestaurantValidator.class, LoginRestaurantValidator.class})
    private String phoneNumber;
    @NotNull(message = "Password can't be null", groups = {CreateRestaurantValidator.class})
    @Pattern(message = "Enter a valid password", regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$", groups = {CreateRestaurantValidator.class, UpdateRestaurantValidator.class, LoginRestaurantValidator.class})
    private String password;
    @NotNull(message = "EmailId can't be null", groups = {CreateRestaurantValidator.class})
    @Pattern(message = "Enter a valid email id", regexp = "^[a-z][a-z\\d._]+@[a-z]{5,20}.[a-z]{2,3}$", groups = {CreateRestaurantValidator.class, UpdateRestaurantValidator.class, LoginRestaurantValidator.class})
    private String emailId;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailId() {
        return emailId;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }

        if (object instanceof Restaurant) {
            return this.hashCode() == object.hashCode();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class RestaurantBuilder {

        private final Restaurant restaurant;

        public RestaurantBuilder() {
            restaurant = new Restaurant();
        }

        public RestaurantBuilder setId(final Long id) {
            restaurant.id = id;

            return this;
        }

        public RestaurantBuilder setName(final String name) {
            restaurant.name = name;

            return this;
        }

        public RestaurantBuilder setPhoneNumber(final String phoneNumber) {
            restaurant.phoneNumber = phoneNumber;

            return this;
        }

        public RestaurantBuilder setPassword(final String password) {
            restaurant.password = password;

            return this;
        }

        public RestaurantBuilder setEmailId(final String emailId) {
            restaurant.emailId = emailId;

            return this;
        }

        public Restaurant build() {
            return restaurant;
        }
    }
}