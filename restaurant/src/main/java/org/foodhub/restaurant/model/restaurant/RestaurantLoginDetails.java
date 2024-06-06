package org.foodhub.restaurant.model.restaurant;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import org.foodhub.common.hibernate.validatorgroup.Restaurant.LoginRestaurantValidator;

/**
 * Handles the data for the restaurant profile login.
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public record RestaurantLoginDetails(

        @NotNull(message = "Login type can't be null", groups = LoginRestaurantValidator.class)
        RestaurantProfileField loginType,
        @Pattern(message = "Enter a valid phone number", regexp = "^(0/91)?[6789]\\d{9}$", groups = LoginRestaurantValidator.class)
        String phoneNumber,
        @Pattern(message = "Enter a valid email id", regexp = "^[a-z][a-z\\d._]+@[a-z]{5,20}.[a-z]{2,3}$", groups = LoginRestaurantValidator.class)
        String emailId,
        @NotNull(message = "Password can't be null", groups = {LoginRestaurantValidator.class})
        @Pattern(message = "Enter a valid password", regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$", groups = LoginRestaurantValidator.class)
        String password) {
}
