package org.foodhub.user.database.persistenceservice;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.foodhub.common.hashgenerator.PasswordHashGenerator;
import org.foodhub.database.querybuilder.Query;
import org.foodhub.database.querybuilder.operator.ConditionalOperator;
import org.foodhub.database.querybuilder.operator.LogicalOperator;
import org.foodhub.database.querywriter.QueryWriter;
import org.foodhub.database.querybuilder.clauses.WhereClause;
import org.foodhub.database.querywriter.impl.QueryWriterImpl;
import org.foodhub.user.model.address.Address;
import org.foodhub.user.model.address.AddressType;
import org.foodhub.user.database.table.AddressTable;
import org.foodhub.user.database.table.UserTable;
import org.foodhub.user.model.user.User;

/**
 * <p>
 * Manages the mapping of objects related to the user.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class UserPersistenceService {

    private final QueryWriter queryWriter;

    private UserPersistenceService() {
        queryWriter = QueryWriterImpl.getInstance();
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final UserPersistenceService USER_PERSISTENCE_SERVICE = new UserPersistenceService();
    }

    /**
     * <p>
     * Gets the instance of the user persistence service class.
     * </p>
     *
     * @return The user persistence service instance
     */
    public static UserPersistenceService getInstance() {
        return InstanceHolder.USER_PERSISTENCE_SERVICE;
    }

    /**
     * <p>
     * Creates the new user profile.
     * </p>
     *
     * @param user Represents the user
     * @return The query to create user profile
     */
    public String createUserProfile(final User user) {
        final String tableName = UserTable.TABLE_NAME;
        final Map<String, String> insertFields = new HashMap<>();

        insertFields.put(UserTable.NAME_COLUMN, user.getName());
        insertFields.put(UserTable.PHONE_NUMBER_COLUMN, user.getPhoneNumber());
        insertFields.put(UserTable.EMAIL_ID_COLUMN, user.getEmailId());
        insertFields.put(UserTable.PASSWORD_COLUMN, PasswordHashGenerator.getInstance().hashPassword(user.getPassword()));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setInsertFields(insertFields).buildQuery();

        return queryWriter.writeQuery(query);
    }

    /**
     * <p>
     * Checks for the existing user.
     * </p>
     *
     * @return The query to check the user if already exist
     */
    public String isUserExist(final String phoneNumber, final String emailId) {
        final String tableName = UserTable.TABLE_NAME;
        final Collection<String> selectFields = new ArrayList<>();

        selectFields.add("count(*)");
        final Collection<WhereClause> whereClauses = new ArrayList<>();

        whereClauses.add(new WhereClause().setColumn(UserTable.PHONE_NUMBER_COLUMN)
                .setConditionalOperator(ConditionalOperator.EQUAL).setValue(phoneNumber));
        whereClauses.add(new WhereClause().setLogicalOperator(LogicalOperator.OR).setColumn(UserTable.EMAIL_ID_COLUMN)
                .setConditionalOperator(ConditionalOperator.EQUAL).setValue(emailId));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setSelectFields(selectFields)
                .setWhereClauses(whereClauses).buildQuery();

        return queryWriter.writeQuery(query);
    }

    /**
     * <p>
     * Gets the user profile if the phone_number and password matches.
     * </p>
     *
     * @param userDataType Represents the data type of the user
     * @param userData     Represents the data of the user
     * @param password     Represents the password of the user
     * @return The query for initiating user login
     */
    public String getUser(final String userDataType, final String userData, final String password) {
        final String tableName = UserTable.TABLE_NAME;
        final Collection<String> selectFields = new ArrayList<>();

        selectFields.add(UserTable.ID_COLUMN);
        selectFields.add(UserTable.NAME_COLUMN);
        selectFields.add(UserTable.PHONE_NUMBER_COLUMN);
        selectFields.add(UserTable.EMAIL_ID_COLUMN);
        selectFields.add(UserTable.PASSWORD_COLUMN);
        final Collection<WhereClause> whereClauses = new ArrayList<>();

        whereClauses.add(new WhereClause().setColumn(userDataType).setConditionalOperator(ConditionalOperator.EQUAL)
                .setValue(userData));
        whereClauses.add(new WhereClause().setLogicalOperator(LogicalOperator.AND).setColumn(UserTable.PASSWORD_COLUMN)
                .setConditionalOperator(ConditionalOperator.EQUAL).setValue(password));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setSelectFields(selectFields)
                .setWhereClauses(whereClauses).buildQuery();

        return queryWriter.writeQuery(query);
    }

    /**
     * <p>
     * Gets the user profile if the id matches.
     * </p>
     *
     * @param userId Represents the password of the user
     * @return The query to get the user by id
     */
    public String getUserById(final long userId) {
        final String tableName = UserTable.TABLE_NAME;
        final Collection<String> selectFields = new ArrayList<>();

        selectFields.add(UserTable.ID_COLUMN);
        selectFields.add(UserTable.NAME_COLUMN);
        selectFields.add(UserTable.PHONE_NUMBER_COLUMN);
        selectFields.add(UserTable.EMAIL_ID_COLUMN);
        selectFields.add(UserTable.PASSWORD_COLUMN);
        final Collection<WhereClause> whereClauses = new ArrayList<>();

        whereClauses.add(new WhereClause().setColumn(UserTable.ID_COLUMN)
                .setConditionalOperator(ConditionalOperator.EQUAL)
                .setValue(String.valueOf(userId)));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setSelectFields(selectFields)
                .setWhereClauses(whereClauses).buildQuery();

        return queryWriter.writeQuery(query);
    }

    /**
     * <p>
     * Adds the address of the user.
     * </p>
     *
     * @param address Represents the address of the user
     * @return The query to add user address
     */
    public String addAddress(final Address address) {
        final String tableName = AddressTable.TABLE_NAME;
        final Map<String, String> insertFields = new HashMap<>();

        insertFields.put(AddressTable.USER_ID_COLUMN, String.valueOf(address.getUserId()));
        insertFields.put(AddressTable.HOUSE_NUMBER_COLUMN, address.getHouseNumber());
        insertFields.put(AddressTable.STREET_NAME_COLUMN, address.getStreetName());
        insertFields.put(AddressTable.AREA_NAME_COLUMN, address.getAreaName());
        insertFields.put(AddressTable.CITY_NAME_COLUMN, address.getCityName());
        insertFields.put(AddressTable.PINCODE_COLUMN, address.getPincode());
        insertFields.put(AddressTable.ADDRESS_TYPE_COLUMN, String.valueOf(AddressType.getId(address.getAddressType())));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setInsertFields(insertFields).buildQuery();

        return queryWriter.writeQuery(query);
    }

    /**
     * <p>
     * Displays all the address of the user.
     * </p>
     *
     * @param userId Represents the id of the user
     * @return The query to get all address of the user
     */
    public String getAddress(final long userId) {
        final String tableName = AddressTable.TABLE_NAME;
        final Collection<String> selectFields = new ArrayList<>();

        selectFields.add("*");
        final Collection<WhereClause> whereClauses = new ArrayList<>();

        whereClauses.add(new WhereClause().setColumn(AddressTable.USER_ID_COLUMN)
                .setConditionalOperator(ConditionalOperator.EQUAL)
                .setValue(String.valueOf(userId)));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setSelectFields(selectFields)
                .setWhereClauses(whereClauses).buildQuery();

        return queryWriter.writeQuery(query);
    }

    /**
     * <p>
     * Updates the user profile data.
     * </p>
     *
     * @param userId       Represents the id of user
     * @param userData     Represents the data to be updated
     * @param userDataType Represents the type of data to be updated
     * @return The query to update the user profile
     */
    public String updateUserProfile(final long userId, final String userDataType, final String userData) {
        final String tableName = UserTable.TABLE_NAME;
        final Map<String, String> updateFields = new HashMap<>();

        updateFields.put(userDataType, userData);
        final Collection<WhereClause> whereClauses = new ArrayList<>();

        whereClauses.add(new WhereClause().setColumn(UserTable.ID_COLUMN)
                .setConditionalOperator(ConditionalOperator.EQUAL)
                .setValue(String.valueOf(userId)));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setUpdateFields(updateFields)
                .setWhereClauses(whereClauses).buildQuery();

        return queryWriter.writeQuery(query);
    }
}