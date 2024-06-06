package org.foodhub.restaurant.controller;

import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;

import org.foodhub.restaurant.model.restaurant.Restaurant;
import org.foodhub.restaurant.model.restaurant.RestaurantLoginDetails;
import org.foodhub.restaurant.model.restaurant.RestaurantProfileUpdateDetails;
import org.foodhub.restaurant.service.internal.impl.RestaurantProfileServiceImpl;
import org.foodhub.restaurant.service.RestaurantProfileService;

/**
 * <p>
 * Manages restaurant profile related operations and is responsible for receiving input through a REST API and processing it.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
@Path("/restaurant")
public final class RestaurantProfileController {

    private final RestaurantProfileService restaurantProfileService;

    private RestaurantProfileController() {
        restaurantProfileService = RestaurantProfileServiceImpl.getInstance();

    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final RestaurantProfileController RESTAURANT_PROFILE_CONTROLLER = new RestaurantProfileController();
    }

    /**
     * <p>
     * Gets the instance of restaurant profile controller object.
     * </p>
     *
     * @return The restaurant profile controller object
     */
    public static RestaurantProfileController getInstance() {
        return InstanceHolder.RESTAURANT_PROFILE_CONTROLLER;
    }

    /**
     * <p>
     * Creates the new restaurant profile.
     * </p>
     *
     * @param restaurant represents the restaurant
     * @return A byte array containing the JSON response.
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public byte[] createRestaurantProfile(final Restaurant restaurant) {
        return restaurantProfileService.createRestaurantProfile(restaurant);
    }

    /**
     * <p>
     * Gets the restaurant if the id matches.
     * </p>
     *
     * @param restaurantId Represents the id of the restaurant
     * @return A byte array containing the JSON response.
     */
    @Path("/{restaurantId}")
    @GET
    @Produces("application/json")
    public byte[] getRestaurantById(@PathParam("restaurantId") final long restaurantId) {
        return restaurantProfileService.getRestaurantById(restaurantId);
    }

    /**
     * <p>
     * Retrieves the restaurant profile if the phone_number and password matches.
     * </p>
     *
     * @param restaurantLoginDetails Represents the instance of restaurant login details
     * @return A byte array containing the JSON response.
     */
    @Path("/login")
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public byte[] getRestaurant(final RestaurantLoginDetails restaurantLoginDetails) {
        return restaurantProfileService.getRestaurant(restaurantLoginDetails);
    }

    /**
     * <p>
     * Updates the profile of the restaurant.
     * </p>
     *
     * @param restaurantProfileUpdateDetails Represents the instance of restaurant profile update details
     * @return A byte array containing the JSON response.
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public byte[] updateRestaurantProfile(final RestaurantProfileUpdateDetails restaurantProfileUpdateDetails) {
        return restaurantProfileService.updateRestaurantData(restaurantProfileUpdateDetails);
    }

    /**
     * <p>
     * Gets all the restaurants.
     * </p>
     *
     * @return A byte array containing the JSON response.
     */
    @GET
    @Produces("application/json")
    public byte[] getAllRestaurants() {
        return restaurantProfileService.getAllRestaurants();
    }
}