package org.foodhub.restaurant.database.persistenceservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import org.foodhub.restaurant.database.table.RestaurantFoodTable;
import org.foodhub.restaurant.model.food.Food;
import org.foodhub.restaurant.model.food.FoodType;

/**
 * <p>
 * Manages the mapping of objects related to the restaurant.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class RestaurantFoodPersistenceService {

    private final QueryWriter queryWriter;

    private RestaurantFoodPersistenceService() {
        queryWriter = QueryWriterImpl.getInstance();
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final RestaurantFoodPersistenceService RESTAURANT_FOOD_PERSISTENCE_SERVICE =
                new RestaurantFoodPersistenceService();
    }

    /**
     * <p>
     * Gets the instance of the restaurant persistence service class.
     * </p>
     *
     * @return The restaurant persistence service instance
     */
    public static RestaurantFoodPersistenceService getInstance() {
        return InstanceHolder.RESTAURANT_FOOD_PERSISTENCE_SERVICE;
    }

    /**
     * <p>
     * Adds the food to the restaurant
     * </p>
     *
     * @param food Represents the current food added by the restaurant
     * @return The query for adding the food to the restaurant
     */
    public String addFood(final Food food) {
        final String tableName = FoodTable.TABLE_NAME;
        final Map<String, String> insertFields = new HashMap<>();

        insertFields.put(FoodTable.NAME_COLUMN, food.getName());
        insertFields.put(FoodTable.RATE_COLUMN, String.valueOf(food.getRate()));
        insertFields.put(FoodTable.TYPE_COLUMN, String.valueOf(food.getType().getId()));
        insertFields.put(FoodTable.QUANTITY_COLUMN, String.valueOf(food.getQuantity()));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setInsertFields(insertFields)
                .setReturningId(true).buildQuery();

        return queryWriter.writeQuery(query);
    }

    /**
     * <p>
     * Maps the food with restaurant.
     * </p>
     *
     * @param restaurantId Represents the id of the restaurant
     * @return The query for mapping the food with the restaurant
     */
    public String mapFoodsWithRestaurant(final long restaurantId) {
        final String tableName = RestaurantFoodTable.TABLE_NAME;
        final Map<String, String> insertFields = new HashMap<>();

        insertFields.put(RestaurantFoodTable.RESTAURANT_ID_COLUMN, String.valueOf(restaurantId));
        insertFields.put(RestaurantFoodTable.FOOD_ID_COLUMN, "?");
        final Query query = new Query.QueryBuilder().setTableName(tableName).setInsertFields(insertFields).buildQuery();

        return queryWriter.writeQuery(query);
    }

    /**
     * <p>
     * Removes the food from the restaurant menucard.
     * </p>
     *
     * @param foodId Represents the id of the food
     * @return The query for removing food from the menucard
     */
    public String removeFood(final long foodId) {
        final String tableName = FoodTable.TABLE_NAME;
        final Collection<WhereClause> whereClauses = new ArrayList<>();

        whereClauses.add(new WhereClause().setColumn(FoodTable.ID_COLUMN)
                .setConditionalOperator(ConditionalOperator.EQUAL)
                .setValue(String.valueOf(foodId)));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setDeleteStatement(true)
                .setWhereClauses(whereClauses).buildQuery();

        return queryWriter.writeQuery(query);
    }

    /**
     * <p>
     * Gets the available food quantity .
     * </p>
     *
     * @param foodId Represents the id of the food
     * @return The query for getting the quantity of the food
     */
    public String getFoodQuantity(final long foodId) {
        final String tableName = FoodTable.TABLE_NAME;
        final Collection<String> selectFields = new ArrayList<>();

        selectFields.add(FoodTable.QUANTITY_COLUMN);
        final Collection<WhereClause> whereClauses = new ArrayList<>();

        whereClauses.add(new WhereClause().setColumn(RestaurantFoodTable.FOOD_ID_COLUMN)
                .setConditionalOperator(ConditionalOperator.EQUAL)
                .setValue(String.valueOf(foodId)));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setSelectFields(selectFields)
                .setWhereClauses(whereClauses).buildQuery();

        return queryWriter.writeQuery(query);
    }

    /**
     * <p>
     * Gets the menucard of the selected restaurant.
     * </p>
     *
     * @param restaurantId Represents the id of the restaurant
     * @return The query for getting the menucard
     */
    public String getMenuCard(final long restaurantId, final int menucardId) {
        final String tableName = FoodTable.TABLE_NAME_WITH_ALIAS;
        final Collection<String> selectFields = new ArrayList<>();

        selectFields.add(FoodTable.ID_COLUMN_WITH_ALIAS);
        selectFields.add(FoodTable.NAME_COLUMN_WITH_ALIAS);
        selectFields.add(FoodTable.RATE_COLUMN_WITH_ALIAS);
        selectFields.add(FoodTable.TYPE_COLUMN_WITH_ALIAS);
        selectFields.add(FoodTable.QUANTITY_COLUMN_WITH_ALIAS);
        final Collection<JoinClause> joins = new ArrayList<>();

        joins.add(new JoinClause().setJoinType(JoinType.JOIN).setTableName(RestaurantFoodTable.TABLE_NAME_WITH_ALIAS)
                .setJoinConditions(FoodTable.ID_COLUMN_WITH_ALIAS, RestaurantFoodTable.FOOD_ID_COLUMN_WITH_ALIAS));
        joins.add(new JoinClause().setJoinType(JoinType.JOIN).setTableName(RestaurantTable.TABLE_NAME_WITH_ALIAS)
                .setJoinConditions(RestaurantFoodTable.RESTAURANT_ID_COLUMN_WITH_ALIAS, RestaurantTable.ID_COLUMN_WITH_ALIAS));
        final Collection<WhereClause> whereClauses = new ArrayList<>();

        whereClauses.add(new WhereClause().setColumn(RestaurantTable.ID_COLUMN_WITH_ALIAS)
                .setConditionalOperator(ConditionalOperator.EQUAL)
                .setValue(String.valueOf(restaurantId)));
        final List<String> values = new ArrayList<>();

        switch (menucardId) {
            case 1 -> values.add(String.valueOf(FoodType.VEG.getId()));
            case 2 -> values.add(String.valueOf(FoodType.NONVEG.getId()));
            case 3 -> {
                values.add(String.valueOf(FoodType.VEG.getId()));
                values.add(String.valueOf(FoodType.NONVEG.getId()));
            }
        }
        whereClauses.add(new WhereClause().setLogicalOperator(LogicalOperator.AND)
                .setColumn(FoodTable.TYPE_COLUMN_WITH_ALIAS)
                .setConditionalOperator(ConditionalOperator.IN).setValues(values));
        final Query query = new Query.QueryBuilder().setTableName(tableName).setSelectFields(selectFields)
                .setJoins(joins).setWhereClauses(whereClauses).buildQuery();

        return queryWriter.writeQuery(query);
    }
}