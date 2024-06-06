package org.foodhub.user.database.persistenceservice;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.foodhub.database.querybuilder.Query;
import org.foodhub.database.querybuilder.clauses.JoinClause;
import org.foodhub.database.querybuilder.clauses.JoinType;
import org.foodhub.database.querybuilder.clauses.WhereClause;
import org.foodhub.database.querybuilder.operator.ConditionalOperator;
import org.foodhub.database.querybuilder.operator.LogicalOperator;
import org.foodhub.database.querywriter.QueryWriter;
import org.foodhub.database.querywriter.impl.QueryWriterImpl;
import org.foodhub.restaurant.database.table.FoodTable;
import org.foodhub.restaurant.database.table.RestaurantTable;
import org.foodhub.user.model.cart.CartStatus;
import org.foodhub.user.database.table.CartTable;
import org.foodhub.user.database.table.UserTable;
import org.foodhub.user.model.cart.Cart;

/**
 * <p>
 * Manages the mapping of objects related to the cart.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class CartPersistenceService {

    private final QueryWriter queryWriter;

    private CartPersistenceService() {
        queryWriter = QueryWriterImpl.getInstance();
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final CartPersistenceService CART_PERSISTENCE_SERVICE = new CartPersistenceService();
    }

    /**
     * <p>
     * Gets the instance of the cart persistence service class.
     * </p>
     *
     * @return The cart persistence service instance
     */
    public static CartPersistenceService getInstance() {
        return InstanceHolder.CART_PERSISTENCE_SERVICE;
    }

    /**
     * <p>
     * Adds the selected food to the user cart.
     * </p>
     *
     * @param cart Represents the cart of the user
     * @return The query to add the food to cart
     */
    public String getAddFoodQuery(final Cart cart) {
        final String tableName = CartTable.TABLE_NAME;
        final Map<String, String> insertFields = new HashMap<>();

        insertFields.put(CartTable.USER_ID_COLUMN, String.valueOf(cart.getUserId()));
        insertFields.put(CartTable.RESTAURANT_ID_COLUMN, String.valueOf(cart.getRestaurant().getId()));
        insertFields.put(CartTable.FOOD_ID_COLUMN, String.valueOf(cart.getFood().getId()));
        insertFields.put(CartTable.QUANTITY_COLUMN, String.valueOf(cart.getQuantity()));
        insertFields.put(CartTable.TOTAL_AMOUNT_COLUMN, String.valueOf(cart.getAmount()));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setInsertFields(insertFields).buildQuery();

        return queryWriter.writeQuery(query);
    }

    /**
     * <p>
     * Gets all the cart items of the user.
     * </p>
     *
     * @param userId Represents the id 0f the current user
     * @return The query to get all user cart items
     */
    public String getCartQuery(final long userId) {
        final String tableName = FoodTable.TABLE_NAME_WITH_ALIAS;
        final Collection<String> selectFields = new ArrayList<>();

        selectFields.add(CartTable.ID_COLUMN_WITH_ALIAS);
        selectFields.add(UserTable.ID_COLUMN_WITH_ALIAS);
        selectFields.add(FoodTable.ID_COLUMN_WITH_ALIAS);
        selectFields.add(FoodTable.NAME_COLUMN_WITH_ALIAS);
        selectFields.add(RestaurantTable.ID_COLUMN_WITH_ALIAS);
        selectFields.add(RestaurantTable.NAME_COLUMN_WITH_ALIAS);
        selectFields.add(CartTable.QUANTITY_COLUMN_WITH_ALIAS);
        selectFields.add(CartTable.TOTAL_AMOUNT_COLUMN_WITH_ALIAS);
        final Collection<JoinClause> joins = new ArrayList<>();

        joins.add(new JoinClause().setJoinType(JoinType.JOIN).setTableName(CartTable.TABLE_NAME_WITH_ALIAS)
                .setJoinConditions(FoodTable.ID_COLUMN_WITH_ALIAS, CartTable.FOOD_ID_COLUMN_WITH_ALIAS));
        joins.add(new JoinClause().setJoinType(JoinType.JOIN).setTableName(RestaurantTable.TABLE_NAME_WITH_ALIAS)
                .setJoinConditions(CartTable.RESTAURANT_ID_COLUMN_WITH_ALIAS, RestaurantTable.ID_COLUMN_WITH_ALIAS));
        joins.add(new JoinClause().setJoinType(JoinType.JOIN).setTableName(UserTable.TABLE_NAME_WITH_ALIAS)
                .setJoinConditions(CartTable.USER_ID_COLUMN_WITH_ALIAS, UserTable.ID_COLUMN_WITH_ALIAS));
        final Collection<WhereClause> whereClauses = new ArrayList<>();

        whereClauses.add(new WhereClause().setColumn(UserTable.ID_COLUMN_WITH_ALIAS)
                .setConditionalOperator(ConditionalOperator.EQUAL).setValue(String.valueOf(userId)));
        whereClauses.add(new WhereClause().setLogicalOperator(LogicalOperator.AND)
                .setColumn(CartTable.STATUS_COLUMN_WITH_ALIAS).setConditionalOperator(ConditionalOperator.EQUAL)
                .setValue(String.valueOf(CartStatus.getId(CartStatus.IN_CART))));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setSelectFields(selectFields)
                .setJoins(joins).setWhereClauses(whereClauses).buildQuery();

        return queryWriter.writeQuery(query);
    }

    /**
     * <p>
     * Removes the selected food from the user cart.
     * </p>
     *
     * @param cartId Represents the id 0f the user cart
     * @return The query to remove the food from the cart
     */
    public String getRemoveFoodQuery(final long cartId) {
        final String tableName = CartTable.TABLE_NAME;
        final Collection<WhereClause> whereClauses = new ArrayList<>();

        whereClauses.add(new WhereClause().setColumn(CartTable.ID_COLUMN)
                .setConditionalOperator(ConditionalOperator.EQUAL)
                .setValue(String.valueOf(cartId)));
        whereClauses.add(new WhereClause().setLogicalOperator(LogicalOperator.AND).setColumn(CartTable.STATUS_COLUMN)
                .setConditionalOperator(ConditionalOperator.EQUAL)
                .setValue(String.valueOf(CartStatus.getId(CartStatus.IN_CART))));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setDeleteStatement(true)
                .setWhereClauses(whereClauses).buildQuery();

        return queryWriter.writeQuery(query);
    }

    /**
     * <p>
     * Remove all the foods from the user cart.
     * </p>
     *
     * @param userId Represents the id of the current user
     * @return The query to clear all the items in the user cart
     */
    public String getClearCartQuery(final long userId) {
        final String tableName = CartTable.TABLE_NAME;
        final Collection<WhereClause> whereClauses = new ArrayList<>();

        whereClauses.add(new WhereClause().setColumn(CartTable.USER_ID_COLUMN)
                .setConditionalOperator(ConditionalOperator.EQUAL)
                .setValue(String.valueOf(userId)));
        whereClauses.add(new WhereClause().setLogicalOperator(LogicalOperator.AND).setColumn(CartTable.STATUS_COLUMN)
                .setConditionalOperator(ConditionalOperator.EQUAL)
                .setValue(String.valueOf(CartStatus.getId(CartStatus.IN_CART))));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setDeleteStatement(true)
                .setWhereClauses(whereClauses).buildQuery();

        return queryWriter.writeQuery(query);
    }
}