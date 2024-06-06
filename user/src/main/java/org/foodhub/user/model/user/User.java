package org.foodhub.user.model.user;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import jakarta.validation.constraints.Positive;
import org.foodhub.common.hibernate.validatorgroup.user.GetUserValidator;
import org.foodhub.common.hibernate.validatorgroup.user.LoginUserValidator;
import org.foodhub.common.hibernate.validatorgroup.user.CreateUserValidator;
import org.foodhub.common.hibernate.validatorgroup.user.UpdateUserValidator;

/**
 * <p>
 * Represents user entity with properties and methods.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class User {

    @Positive(message = "User id can't be negative or zero", groups = {UpdateUserValidator.class, GetUserValidator.class})
    private Long id;
    @NotNull(message = "Name can't be null", groups = {CreateUserValidator.class})
    @Pattern(message = "Enter a valid name", regexp = "^[A-Za-z][A-Za-z\\s]{3,20}$", groups = {UpdateUserValidator.class, CreateUserValidator.class})
    private String name;
    @NotNull(message = "PhoneNumber can't be null", groups = {CreateUserValidator.class})
    @Pattern(message = "Enter a valid phone number", regexp = "^(0/91)?[6789]\\d{9}$", groups = {UpdateUserValidator.class, CreateUserValidator.class, LoginUserValidator.class})
    private String phoneNumber;
    @NotNull(message = "Password can't be null", groups = {CreateUserValidator.class})
    @Pattern(message = "Enter a valid password", regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$", groups = {UpdateUserValidator.class, CreateUserValidator.class, LoginUserValidator.class})
    private String password;
    @NotNull(message = "EmailId can't be null", groups = {CreateUserValidator.class})
    @Pattern(message = "Enter a valid email id", regexp = "^[a-z][a-z\\d._]+@[a-z]{5,20}.[a-z]{2,3}$", groups = {UpdateUserValidator.class, CreateUserValidator.class, LoginUserValidator.class})
    private String emailId;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }

        if (object instanceof User) {
            return this.hashCode() == object.hashCode();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class UserBuilder {

        private final User user;

        public UserBuilder() {
            user = new User();
        }

        public UserBuilder setId(final Long id) {
            user.id = id;

            return this;
        }

        public UserBuilder setName(final String name) {
            user.name = name;

            return this;
        }

        public UserBuilder setPhoneNumber(final String phoneNumber) {
            user.phoneNumber = phoneNumber;

            return this;
        }

        public UserBuilder setPassword(final String password) {
            user.password = password;

            return this;
        }

        public UserBuilder setEmailId(final String emailId) {
            user.emailId = emailId;

            return this;
        }

        public User build() {
            return user;
        }
    }
}
