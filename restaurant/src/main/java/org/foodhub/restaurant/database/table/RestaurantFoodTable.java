package org.foodhub.restaurant.database.table;

/**
 * <p>
 *  Holds the table and column names of the restaurant food table
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class RestaurantFoodTable {

    public static final String TABLE_NAME = "restaurant_food";
    public static final String ID_COLUMN = "id";
    public static final String RESTAURANT_ID_COLUMN = "restaurant_id";
    public static final String FOOD_ID_COLUMN = "food_id";
    public static final String TABLE_NAME_WITH_ALIAS = "restaurant_food rf";
    public static final String ID_COLUMN_WITH_ALIAS = "rf.id";
    public static final String RESTAURANT_ID_COLUMN_WITH_ALIAS = "rf.restaurant_id";
    public static final String FOOD_ID_COLUMN_WITH_ALIAS = "rf.food_id";
}
