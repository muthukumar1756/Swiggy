package org.foodhub.user.service.internal.impl;

import java.util.Collection;
import java.util.Optional;

import org.foodhub.user.database.dao.UserDAO;
import org.foodhub.user.database.dao.internal.impl.UserDAOImpl;
import org.foodhub.user.model.address.Address;
import org.foodhub.user.model.user.User;
import org.foodhub.user.model.user.UserLoginDetails;
import org.foodhub.user.model.user.UserProfileField;
import org.foodhub.user.model.user.UserProfileUpdateDetails;
import org.foodhub.user.service.UserService;
import org.foodhub.common.hibernate.HibernateEntityValidator;
import org.foodhub.common.hashgenerator.PasswordHashGenerator;
import org.foodhub.common.json.JsonArray;
import org.foodhub.common.json.JsonFactory;
import org.foodhub.common.json.JsonObject;
import org.foodhub.common.hibernate.impl.HibernateEntityValidatorImpl;
import org.foodhub.common.hibernate.validatorgroup.address.GetAddressValidator;
import org.foodhub.common.hibernate.validatorgroup.address.PostAddressValidator;
import org.foodhub.common.hibernate.validatorgroup.user.GetUserValidator;
import org.foodhub.common.hibernate.validatorgroup.user.LoginUserValidator;
import org.foodhub.common.hibernate.validatorgroup.user.CreateUserValidator;
import org.foodhub.common.hibernate.validatorgroup.user.UpdateUserValidator;

/**
 * <p>
 * Implements the service of the user related operation.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class UserServiceImpl implements UserService {

    private static final String STATUS = "status";
    private final JsonFactory jsonFactory;
    private final HibernateEntityValidator validatorFactory;
    private UserDAO userDAO;

    private UserServiceImpl() {
        userDAO = UserDAOImpl.getInstance();
        jsonFactory = JsonFactory.getInstance();
        validatorFactory = HibernateEntityValidatorImpl.getInstance();
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final UserService USER_SERVICE = new UserServiceImpl();
    }

    /**
     * <p>
     * Gets the object of the user service implementation class.
     * </p>
     *
     * @return The user service implementation class object
     */
    public static UserService getInstance() {
        return InstanceHolder.USER_SERVICE;
    }

    /**
     * {@inheritDoc}
     *
     * @param user Represents the user
     * @return The response for the user profile creation
     */
    @Override
    public byte[] createUserProfile(final User user) {
        final JsonObject jsonObject = validatorFactory.validate(user, CreateUserValidator.class);

        if (jsonObject.isEmpty()) {

            if (userDAO.isUserExist(user.getPhoneNumber(), user.getEmailId())) {
                return jsonObject.put(STATUS, "User is already exist").asBytes();
            }

            return userDAO.createUserProfile(user) ?
                    jsonObject.put(STATUS, "User profile was created").asBytes() :
                    jsonObject.put(STATUS, "User profile creation failed").asBytes();
        }

        return jsonObject.asBytes();
    }

    /**
     * {@inheritDoc}
     *
     * @param userLoginDetails Represents the instance of user login dto
     * @return The user object
     */
    @Override
    public byte[] getUser(final UserLoginDetails userLoginDetails) {
        final JsonObject jsonObject = validatorFactory.validate(userLoginDetails, LoginUserValidator.class);

        if (jsonObject.isEmpty()) {
            final String hashPassword = PasswordHashGenerator.getInstance().hashPassword(userLoginDetails.password());

            final Optional<User> user = switch (userLoginDetails.loginType()) {
                case PHONE_NUMBER -> userDAO.getUser(UserProfileField.PHONE_NUMBER.name(),
                        userLoginDetails.phoneNumber(), hashPassword);
                case EMAIL_ID -> userDAO.getUser(UserProfileField.EMAIL_ID.name(),
                        userLoginDetails.emailId(), hashPassword);
                default -> Optional.empty();
            };

            return user.isPresent() ?
                    jsonObject.put(STATUS, String.join(" ", "User login successful welcome",
                            user.get().getName())).asBytes() :
                    jsonObject.put(STATUS, "User login failed").asBytes();
        }

        return jsonObject.asBytes();
    }

    /**
     * {@inheritDoc}
     *
     * @param userId Represents the password of the user
     * @return The user object
     */
    @Override
    public byte[] getUserById(final long userId) {
        final User userPojo = new User.UserBuilder().setId(userId).build();
        final JsonObject jsonObject = validatorFactory.validate(userPojo, GetUserValidator.class);

        if (jsonObject.isEmpty()) {
            final Optional<User> user = userDAO.getUserById(userId);

            return user.isPresent() ? jsonObject.build(user.get()).asBytes() :
                    jsonObject.put(STATUS, "User not found").asBytes();
        }

        return jsonObject.asBytes();
    }

    /**
     * {@inheritDoc}
     *
     * @param address Represents the address of the user
     * @return The response for the adding the address data
     */
    @Override
    public byte[] addAddress(final Address address) {
        final JsonObject jsonObject = validatorFactory.validate(address, PostAddressValidator.class);

        if (jsonObject.isEmpty()) {
            return userDAO.addAddress(address) ? jsonObject.put(STATUS, "Address was added").asBytes() :
                    jsonObject.put(STATUS, "Address adding failed").asBytes();
        }

        return jsonObject.asBytes();
    }

    /**
     * {@inheritDoc}
     *
     * @param userId Represents the id of the user
     * @return The list of addresses of the user
     */
    @Override
    public byte[] getAddress(final long userId) {
        final Address address = new Address.AddressBuilder().setUserId(userId).build();
        final JsonArray jsonArray = jsonFactory.createArrayNode();
        final JsonObject jsonObject = validatorFactory.validate(address, GetAddressValidator.class);

        if (jsonObject.isEmpty()) {
            final Optional<Collection<Address>> addressList = userDAO.getAddress(userId);

            return addressList.isPresent() ? jsonArray.build(addressList.get()).asBytes() :
                    jsonArray.add(jsonFactory.createObjectNode()
                            .put(STATUS, "Address list is empty or user id is invalid")).asBytes();
        }

        return jsonArray.add(jsonObject).asBytes();
    }

    /**
     * {@inheritDoc}
     *
     * @param userProfileUpdateDetails Represents the instance of user profile update dto
     * @return The response for the user profile updation
     */
    @Override
    public byte[] updateUserProfile(final UserProfileUpdateDetails userProfileUpdateDetails) {
        final JsonObject jsonObject = validatorFactory.validate(userProfileUpdateDetails, UpdateUserValidator.class);

        if (jsonObject.isEmpty()) {
            final boolean updateStatus = switch (userProfileUpdateDetails.updateDataType()) {
                case NAME -> userDAO.updateUserProfile(userProfileUpdateDetails.id(),
                        UserProfileField.NAME.name(), userProfileUpdateDetails.name());
                case PHONE_NUMBER -> userDAO.updateUserProfile(userProfileUpdateDetails.id(),
                        UserProfileField.PHONE_NUMBER.name(), userProfileUpdateDetails.phoneNumber());
                case EMAIL_ID -> userDAO.updateUserProfile(userProfileUpdateDetails.id(),
                        UserProfileField.EMAIL_ID.name(), userProfileUpdateDetails.emailId());
                case PASSWORD -> userDAO.updateUserProfile(userProfileUpdateDetails.id(),
                        UserProfileField.PASSWORD.name(), userProfileUpdateDetails.password());
            };

            return updateStatus ? jsonObject.put(STATUS, "User profile is updated").asBytes() :
                    jsonObject.put(STATUS, "User profile updation failed").asBytes();
        }

        return jsonObject.asBytes();
    }

    /**
     * {@inheritDoc}
     *
     * @param userDAO The instance of user dao
     */
    public void setUserDAO(final UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}