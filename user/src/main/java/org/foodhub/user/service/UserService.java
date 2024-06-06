package org.foodhub.user.service;

import org.foodhub.user.database.dao.UserDAO;
import org.foodhub.user.model.address.Address;
import org.foodhub.user.model.user.User;
import org.foodhub.user.model.user.UserLoginDetails;
import org.foodhub.user.model.user.UserProfileUpdateDetails;

/**
 * <p>
 * Provides the services for the user.
 * </p>
 *
 * @author Muthu kumar v
 * @version 1.0
 */
public interface UserService {

    /**
     * <p>
     * Creates the new user profile.
     * </p>
     *
     * @param user Represents the user
     * @return The response for the user profile creation
     */
    byte[] createUserProfile(final User user);

    /**
     * <p>
     * Gets the user profile if the phone_number and password matches.
     * </p>
     *
     * @param userLoginDetails Represents the instance of user login dto
     * @return The user object
     */
    byte[] getUser(final UserLoginDetails userLoginDetails);

    /**
     * <p>
     * Gets the user profile if the id matches.
     * </p>
     *
     * @param userId Represents the password of the user
     * @return The user object
     */
    byte[] getUserById(final long userId);

    /**
     * <p>
     * Adds the address of the user.
     * </p>
     *
     * @param address Represents the address of the user
     * @return The response for the adding the address data
     */
    byte[] addAddress(final Address address);

    /**
     * <p>
     * Displays all the addresses of the user.
     * </p>
     *
     * @param userId Represents the id of the user
     * @return The list of addresses of the user
     */
    byte[] getAddress(final long userId);

    /**
     * <p>
     * Updates the data of the user.
     * </p>
     *
     * @param userProfileUpdateDetails Represents the instance of user profile update dto
     * @return The response for the user profile updation
     */
    byte[] updateUserProfile(final UserProfileUpdateDetails userProfileUpdateDetails);

    /**
     * <p>
     * Injects the mocked instance of user dao.
     * </p>
     *
     * @param userDAO The instance of user dao
     */
    void setUserDAO(final UserDAO userDAO);
}
