package org.foodhub.user.database.dao;

import org.foodhub.user.model.address.Address;
import org.foodhub.user.model.user.User;

import java.util.Collection;
import java.util.Optional;

/**
 * <p>
 * Provides data base service for the user
 * </p>
 *
 * @author Muthu kumar v
 * @version 1.1
 */
public interface UserDAO {

    /**
     * <p>
     * Creates the new user profile.
     * </p>
     *
     * @param user Represents the user
     * @return True if user is created, false otherwise
     */
    boolean createUserProfile(final User user);

    /**
     * <p>
     * Checks for the existing user.
     * </p>
     *
     * @return True if user is exist, false otherwise
     */
    boolean isUserExist(final String phoneNumber, final String emailId);

    /**
     * <p>
     * Gets the user profile if the phone_number and password matches.
     * </p>
     *
     * @param userDataType Represents the data type of the user
     * @param userData     Represents the data of the user
     * @param password     Represents the password of the user
     * @return The user object
     */
    Optional<User> getUser(final String userDataType, final String userData, final String password);

    /**
     * <p>
     * Gets the user profile if the id matches.
     * </p>
     *
     * @param userId Represents the password of the user
     * @return The user object
     */
    Optional<User> getUserById(final long userId);

    /**
     * <p>
     * Adds the address of the user.
     * </p>
     *
     * @param address Represents the address of the user
     * @return True if the address is added, false otherwise
     */
    boolean addAddress(final Address address);

    /**
     * <p>
     * Displays all the address of the user.
     * </p>
     *
     * @param userId Represents the id of the user
     * @return List of addresses of the user
     */
    Optional<Collection<Address>> getAddress(final long userId);

    /**
     * <p>
     * Updates the user profile data.
     * </p>
     *
     * @param userId       Represents the id of user
     * @param userData     Represents the data to be updated
     * @param userDataType Represents the type of data to be updated
     * @return True if user data is updated, false otherwise
     */
    boolean updateUserProfile(final long userId, final String userDataType, final String userData);
}
