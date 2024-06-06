package org.foodhub.restaurant.database.persistenceservice;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.foodhub.common.hashgenerator.PasswordHashGenerator;
import org.foodhub.database.querybuilder.Query;
import org.foodhub.database.querybuilder.operator.ConditionalOperator;
import org.foodhub.database.querybuilder.operator.LogicalOperator;
import org.foodhub.database.querybuilder.clauses.WhereClause;
import org.foodhub.database.querywriter.QueryWriter;
import org.foodhub.database.querywriter.impl.QueryWriterImpl;
import org.foodhub.restaurant.database.table.RestaurantTable;
import org.foodhub.restaurant.model.restaurant.Restaurant;

/**
 * <p>
 * Manages the mapping of objects related to the restaurant.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class RestaurantProfilePersistenceService {

    private final QueryWriter queryWriter;

    private RestaurantProfilePersistenceService() {
        queryWriter = QueryWriterImpl.getInstance();
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final RestaurantProfilePersistenceService RESTAURANT_PROFILE_PERSISTENCE_SERVICE =
                new RestaurantProfilePersistenceService();
    }

    /**
     * <p>
     * Gets the instance of the restaurant persistence service class.
     * </p>
     *
     * @return The restaurant persistence service instance
     */
    public static RestaurantProfilePersistenceService getInstance() {
        return InstanceHolder.RESTAURANT_PROFILE_PERSISTENCE_SERVICE;
    }

    /**
     * <p>
     * Creates the new restaurant profile.
     * </p>
     *
     * @param restaurant Represents the restaurant
     * @return The query to create new restaurant profile
     */
    public String createRestaurantProfile(final Restaurant restaurant) {
        final String tableName = RestaurantTable.TABLE_NAME;
        final Map<String, String> insertFields = new HashMap<>();

        insertFields.put(RestaurantTable.NAME_COLUMN, restaurant.getName());
        insertFields.put(RestaurantTable.PHONE_NUMBER_COLUMN, restaurant.getPhoneNumber());
        insertFields.put(RestaurantTable.EMAIL_ID_COLUMN, restaurant.getEmailId());
        insertFields.put(RestaurantTable.PASSWORD_COLUMN, PasswordHashGenerator.getInstance().hashPassword(restaurant
                .getPassword()));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setInsertFields(insertFields).buildQuery();

        return queryWriter.writeQuery(query);
    }

    /**
     * <p>
     * Checks for the restaurant is exist.
     * </p>
     *
     * @return The query to check the restaurant profile is already exist
     */
    public String isRestaurantExist(final String phoneNumber, final String emailId) {
        final String tableName = RestaurantTable.TABLE_NAME;
        final Collection<String> selectFields = new ArrayList<>();

        selectFields.add("count(*)");
        final Collection<WhereClause> whereClauses = new ArrayList<>();

        whereClauses.add(new WhereClause().setColumn(RestaurantTable.PHONE_NUMBER_COLUMN)
                .setConditionalOperator(ConditionalOperator.EQUAL)
                .setValue(phoneNumber));
        whereClauses.add(new WhereClause().setLogicalOperator(LogicalOperator.OR)
                .setColumn(RestaurantTable.EMAIL_ID_COLUMN)
                .setConditionalOperator(ConditionalOperator.EQUAL)
                .setValue(emailId));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setSelectFields(selectFields)
                .setWhereClauses(whereClauses).buildQuery();

        return queryWriter.writeQuery(query);
    }

    /**
     * <p>
     * Gets the restaurant if the phone_number and password matches.
     * </p>
     *
     * @param restaurantDataType Represents the type of data of the restaurant
     * @param restaurantData     Represents the data of the restaurant
     * @param password           Represents the password of the restaurant
     * @return The query to check the restaurant login validation
     */
    public String restaurantLogin(final String restaurantDataType, final String restaurantData,
                                  final String password) {
        final String tableName = RestaurantTable.TABLE_NAME;
        final Collection<String> selectFields = new ArrayList<>();

        selectFields.add(RestaurantTable.ID_COLUMN);
        selectFields.add(RestaurantTable.NAME_COLUMN);
        selectFields.add(RestaurantTable.PHONE_NUMBER_COLUMN);
        selectFields.add(RestaurantTable.EMAIL_ID_COLUMN);
        selectFields.add(RestaurantTable.PASSWORD_COLUMN);
        final Collection<WhereClause> whereClauses = new ArrayList<>();

        whereClauses.add(new WhereClause().setColumn(restaurantDataType)
                .setConditionalOperator(ConditionalOperator.EQUAL).setValue(restaurantData));
        whereClauses.add(new WhereClause().setLogicalOperator(LogicalOperator.AND)
                .setColumn(RestaurantTable.PASSWORD_COLUMN)
                .setConditionalOperator(ConditionalOperator.EQUAL).setValue(password));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setSelectFields(selectFields)
                .setWhereClauses(whereClauses).buildQuery();

        return queryWriter.writeQuery(query);
    }

    /**
     * <p>
     * Gets the restaurant if the id matches.
     * </p>
     *
     * @param restaurantId Represents the id of the restaurant
     * @return The restaurant object
     */
    public String getRestaurantById(final long restaurantId) {
        final String tableName = RestaurantTable.TABLE_NAME;
        final Collection<String> selectFields = new ArrayList<>();

        selectFields.add(RestaurantTable.ID_COLUMN);
        selectFields.add(RestaurantTable.NAME_COLUMN);
        selectFields.add(RestaurantTable.PHONE_NUMBER_COLUMN);
        selectFields.add(RestaurantTable.EMAIL_ID_COLUMN);
        selectFields.add(RestaurantTable.PASSWORD_COLUMN);
        final Collection<WhereClause> whereClauses = new ArrayList<>();

        whereClauses.add(new WhereClause().setColumn(RestaurantTable.ID_COLUMN)
                .setConditionalOperator(ConditionalOperator.EQUAL)
                .setValue(String.valueOf(restaurantId)));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setSelectFields(selectFields)
                .setWhereClauses(whereClauses).buildQuery();

        return queryWriter.writeQuery(query);
    }

    /**
     * <p>
     * Updates the data of the current restaurant user.
     * </p>
     *
     * @param restaurantId   Represents the id of the restaurant
     * @param restaurantData Represents the data of the restaurant to be updated
     * @param type           Represents the type of data of the restaurant to be updated
     * @return The query for updating restaurant profile
     */
    public String updateRestaurantProfile(final long restaurantId, final String type, final String restaurantData) {
        final String tableName = RestaurantTable.TABLE_NAME;
        final Map<String, String> updateFields = new HashMap<>();

        updateFields.put(type, restaurantData);
        final Collection<WhereClause> whereClauses = new ArrayList<>();

        whereClauses.add(new WhereClause().setColumn(RestaurantTable.ID_COLUMN)
                .setConditionalOperator(ConditionalOperator.EQUAL)
                .setValue(String.valueOf(restaurantId)));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setUpdateFields(updateFields)
                .setWhereClauses(whereClauses).buildQuery();

        return queryWriter.writeQuery(query);
    }

    /**
     * <p>
     * Gets all the restaurants
     * </p>
     *
     * @return The query to get all restaurants
     */
    public String getAllRestaurants() {
        final String tableName = RestaurantTable.TABLE_NAME;
        final Collection<String> selectFields = new ArrayList<>();

        selectFields.add(RestaurantTable.ID_COLUMN);
        selectFields.add(RestaurantTable.NAME_COLUMN);
        final Query query = new Query.QueryBuilder().setTableName(tableName).setSelectFields(selectFields).buildQuery();

        return queryWriter.writeQuery(query);
    }
}
