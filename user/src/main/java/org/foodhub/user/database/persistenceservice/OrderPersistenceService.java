package org.foodhub.user.database.persistenceservice;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

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
import org.foodhub.user.database.table.OrderTable;

/**
 * <p>
 * Manages the mapping of objects related to the order entity.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class OrderPersistenceService {

    private final QueryWriter queryWriter;

    private OrderPersistenceService() {
        queryWriter = QueryWriterImpl.getInstance();
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final OrderPersistenceService ORDER_PERSISTENCE_SERVICE = new OrderPersistenceService();
    }

    /**
     * <p>
     * Gets the instance of the order persistence service class.
     * </p>
     *
     * @return The order persistence service instance
     */
    public static OrderPersistenceService getInstance() {
        return InstanceHolder.ORDER_PERSISTENCE_SERVICE;
    }

    /**
     * <p>
     * places the user orders.
     * </p>
     *
     * @return The query to place the user order
     */
    public String getPlaceOrderQuery() {
        final String tableName = OrderTable.TABLE_NAME;
        final String param = "?";
        final Map<String, String> insertFields = new HashMap<>();

        insertFields.put(OrderTable.USER_ID_COLUMN, param);
        insertFields.put(OrderTable.CART_ID_COLUMN, param);
        insertFields.put(OrderTable.ADDRESS_ID_COLUMN, param);
        final Query query = new Query.QueryBuilder().setTableName(tableName).setInsertFields(insertFields).buildQuery();

        return queryWriter.writeQuery(query);
    }

    /**
     * <p>
     * Updates the cart status after the order is placed.
     * </p>
     *
     * @return The query to update the user cart status
     */
    public String updateCartStatus() {
        final String tableName = CartTable.TABLE_NAME;
        final Map<String, String> updateFields = new HashMap<>();

        updateFields.put(CartTable.STATUS_COLUMN, String.valueOf(CartStatus.getId(CartStatus.ORDER_PLACED)));
        final Collection<WhereClause> whereClauses = new ArrayList<>();

        whereClauses.add(new WhereClause().setColumn(CartTable.ID_COLUMN)
                .setConditionalOperator(ConditionalOperator.EQUAL).setValue("?"));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setUpdateFields(updateFields)
                .setWhereClauses(whereClauses).buildQuery();

        return queryWriter.writeQuery(query);
    }

    /**
     * <p>
     * Gets the orders placed by the user.
     * </p>
     *
     * @param userId Represents the id of the user
     * @return The query to get all the user orders
     */
    public String getOrders(final long userId) {
        final String tableName = OrderTable.TABLE_NAME_WITH_ALIAS;
        final Collection<String> selectFields = new ArrayList<>();

        selectFields.add(OrderTable.ID_COLUMN_WITH_ALIAS);
        selectFields.add(OrderTable.USER_ID_COLUMN_WITH_ALIAS);
        selectFields.add(CartTable.ID_COLUMN_WITH_ALIAS);
        selectFields.add(FoodTable.ID_COLUMN_WITH_ALIAS);
        selectFields.add(FoodTable.NAME_COLUMN_WITH_ALIAS);
        selectFields.add(RestaurantTable.ID_COLUMN_WITH_ALIAS);
        selectFields.add(RestaurantTable.NAME_COLUMN_WITH_ALIAS);
        selectFields.add(CartTable.QUANTITY_COLUMN_WITH_ALIAS);
        selectFields.add(CartTable.TOTAL_AMOUNT_COLUMN_WITH_ALIAS);
        selectFields.add(OrderTable.ADDRESS_ID_COLUMN_WITH_ALIAS);
        final Collection<JoinClause> joins = new ArrayList<>();

        joins.add(new JoinClause().setJoinType(JoinType.JOIN).setTableName(CartTable.TABLE_NAME_WITH_ALIAS)
                .setJoinConditions(OrderTable.CART_ID_COLUMN_WITH_ALIAS, CartTable.ID_COLUMN_WITH_ALIAS));
        joins.add(new JoinClause().setJoinType(JoinType.JOIN).setTableName(FoodTable.TABLE_NAME_WITH_ALIAS)
                .setJoinConditions(CartTable.FOOD_ID_COLUMN_WITH_ALIAS, FoodTable.ID_COLUMN_WITH_ALIAS));
        joins.add(new JoinClause().setJoinType(JoinType.JOIN).setTableName(RestaurantTable.TABLE_NAME_WITH_ALIAS)
                .setJoinConditions(CartTable.RESTAURANT_ID_COLUMN_WITH_ALIAS, RestaurantTable.ID_COLUMN_WITH_ALIAS));
        final Collection<WhereClause> whereClauses = new ArrayList<>();

        whereClauses.add(new WhereClause().setColumn(OrderTable.USER_ID_COLUMN_WITH_ALIAS)
                .setConditionalOperator(ConditionalOperator.EQUAL).setValue(String.valueOf(userId)));
        whereClauses.add(new WhereClause().setLogicalOperator(LogicalOperator.AND)
                .setColumn(CartTable.STATUS_COLUMN_WITH_ALIAS).setConditionalOperator(ConditionalOperator.EQUAL)
                .setValue(String.valueOf(CartStatus.getId(CartStatus.ORDER_PLACED))));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setSelectFields(selectFields)
                .setJoins(joins).setWhereClauses(whereClauses).buildQuery();

        return queryWriter.writeQuery(query);
    }
}