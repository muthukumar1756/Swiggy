package org.foodhub.user.database.resultsetextractor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.foodhub.user.model.address.Address;
import org.foodhub.user.model.address.AddressType;
import org.foodhub.user.exception.user.address.AddressDataPersistenceException;
import org.foodhub.user.exception.user.UserProfileNotFoundException;
import org.foodhub.user.model.user.User;

/**
 * <p>
 * Methods to extract the result set data's of user.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class UserResultSetExtractor {

    private UserResultSetExtractor() {
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final UserResultSetExtractor USER_RESULT_SET_HANDLER = new UserResultSetExtractor();
    }

    /**
     * <p>
     * Gets the instance of the user result set extractor class.
     * </p>
     *
     * @return The user result set extractor instance
     */
    public static UserResultSetExtractor getInstance() {
        return InstanceHolder.USER_RESULT_SET_HANDLER;
    }

    /**
     * <p>
     * Gets the user profile from the result set.
     * </p>
     *
     * @param resultSet Represents the result set data from the executed query.
     * @return The user object
     */
    public Optional<User> getUser(final ResultSet resultSet) {
        try {

            if (resultSet.next()) {
                final User user = new User.UserBuilder().setId(resultSet.getLong(1))
                        .setName(resultSet.getString(2)).setPhoneNumber(resultSet.getString(3))
                        .setEmailId(resultSet.getString(4)).setPassword(resultSet.getString(5))
                        .build();

                return Optional.of(user);
            }

            return Optional.empty();
        } catch (SQLException message) {
            throw new UserProfileNotFoundException(message.getMessage());
        }
    }

    /**
     * <p>
     * Check for the user is exist
     * </p>
     *
     * @param resultSet Represents the result set data from the executed query.
     * @return True if the user is exist, false otherwise
     */
    public boolean isUserExist(final ResultSet resultSet) {
        try {
            resultSet.next();

            return 0 < resultSet.getInt(1);
        } catch (SQLException message) {
            throw new UserProfileNotFoundException(message.getMessage());
        }
    }

    /**
     * <p>
     * Gets all the addresses of the user.
     * </p>
     *
     * @param resultSet Represents the result set data from the executed query.
     * @return List of addresses of the user
     */
    public Optional<Collection<Address>> getAddress(final ResultSet resultSet) {
        try {

            if (resultSet.isBeforeFirst()) {
                final Collection<Address> addressList = new ArrayList<>();

                while (resultSet.next()) {
                    final Address address = new Address.AddressBuilder().setId(resultSet.getLong(1))
                            .setUserId(resultSet.getLong(2)).setHouseNumber(resultSet.getString(3))
                            .setStreetName(resultSet.getString(4))
                            .setAreaName(resultSet.getString(5))
                            .setCityName(resultSet.getString(6))
                            .setPincode(resultSet.getString(7))
                            .setAddressType(AddressType.getTypeById(resultSet.getInt(8)).get()).build();

                    addressList.add(address);
                }

                return Optional.of(Collections.unmodifiableCollection(addressList));
            }

            return Optional.empty();
        } catch (SQLException message) {
            throw new AddressDataPersistenceException(message.getMessage());
        }
    }
}