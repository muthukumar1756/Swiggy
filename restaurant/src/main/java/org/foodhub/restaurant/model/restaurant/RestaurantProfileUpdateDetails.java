package org.foodhub.restaurant.model.restaurant;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import org.foodhub.common.hibernate.validatorgroup.Restaurant.UpdateRestaurantValidator;

/**
 * Handles the data for the restaurant profile update.
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public record RestaurantProfileUpdateDetails(

        @Positive(message = "Restaurant id can't be negative or zero", groups = UpdateRestaurantValidator.class)
        Long id,
        @NotNull(message = "Update data type can't be null", groups = UpdateRestaurantValidator.class)
        RestaurantProfileField updateDataType,
        @Pattern(message = "Enter a valid name", regexp = "^[A-Za-z][A-Za-z\\s]{3,20}$", groups = UpdateRestaurantValidator.class)
        String name,
        @Pattern(message = "Enter a valid phone number", regexp = "^(0/91)?[6789]\\d{9}$", groups = UpdateRestaurantValidator.class)
        String phoneNumber,
        @Pattern(message = "Enter a valid password", regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$", groups = UpdateRestaurantValidator.class)
        String password,
        @Pattern(message = "Enter a valid email id", regexp = "^[a-z][a-z\\d._]+@[a-z]{5,20}.[a-z]{2,3}$", groups = UpdateRestaurantValidator.class)
        String emailId) {
}
