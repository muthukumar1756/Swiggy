package org.foodhub.user.controller;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;

import org.foodhub.user.model.address.Address;
import org.foodhub.user.model.user.User;
import org.foodhub.user.model.user.UserLoginDetails;
import org.foodhub.user.model.user.UserProfileUpdateDetails;
import org.foodhub.user.service.UserService;
import org.foodhub.user.service.internal.impl.UserServiceImpl;

/**
 * <p>
 * Handles the user related operation and responsible for processing user input through rest api
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
@Path("/user")
public final class UserController {

    private UserService userService;

    private UserController() {
        userService = UserServiceImpl.getInstance();
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final UserController USER_CONTROLLER = new UserController();
    }

    /**
     * <p>
     * Gets the instance of user service.
     * </p>
     *
     * @return The user service object
     */
    public UserService getUserService() {
        return userService;
    }

    /**
     * <p>
     * Gets the object of the user controller class.
     * </p>
     *
     * @return The user controller response
     */
    public static UserController getInstance() {
        return InstanceHolder.USER_CONTROLLER;
    }

    /**
     * <p>
     * Creates the new user.
     * </p>
     *
     * @param user Represents the user
     * @return byte array of json response
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public byte[] createUserProfile(final User user) {
        return userService.createUserProfile(user);
    }

    /**
     * <p>
     * Gets the user if the phone_number and password matches.
     * </p>
     *
     * @param userLoginDetails Represents the instance of user login dto
     * @return byte array of json response
     */
    @Path("/login")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public byte[] getUser(final UserLoginDetails userLoginDetails) {
        return userService.getUser(userLoginDetails);
    }

    /**
     * <p>
     * Gets the user if the id matches.
     * </p>
     *
     * @param userId Represents the password of the current user
     * @return byte array of json response
     */
    @Path("/{userId}")
    @GET
    @Produces("application/json")
    public byte[] getUserById(@PathParam("userId") final long userId) {
        return userService.getUserById(userId);
    }

    /**
     * <p>
     * Stores the address of the user.
     * </p>
     *
     * @param address Represents the address of the user
     * @return byte array of json response
     */
    @Path("/address")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public byte[] addAddress(final Address address) {
        return userService.addAddress(address);
    }

    /**
     * <p>
     * Displays all the addresses of the user.
     * </p>
     *
     * @param userId Represents the id of the user
     * @return byte array of json response
     */
    @Path("/address/{userId}")
    @GET
    @Produces("application/json")
    public byte[] getAddress(@PathParam("userId") final long userId) {
        return userService.getAddress(userId);
    }

    /**
     * <p>
     * Updates the data of the current user.
     * </p>
     *
     * @param userProfileUpdateDetails Represents the instance of user profile update dto
     * @return byte array of json response
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public byte[] updateUserProfile(final UserProfileUpdateDetails userProfileUpdateDetails) {
        return userService.updateUserProfile(userProfileUpdateDetails);
    }

    /**
     * <p>
     * Injects the mocked instance of user service.
     * </p>
     *
     * @param userService The instance of user service
     */
    public void setUserService(final UserService userService) {
        this.userService = userService;
    }
}