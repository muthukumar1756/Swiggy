package org.foodhub.user.database.table;

/**
 * <p>
 *  Holds the table and column names of the cart table
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class CartTable {

    public static final String TABLE_NAME = "cart";
    public static final String ID_COLUMN = "id";
    public static final String USER_ID_COLUMN = "user_id";
    public static final String RESTAURANT_ID_COLUMN = "restaurant_id";
    public static final String FOOD_ID_COLUMN = "food_id";
    public static final String QUANTITY_COLUMN = "quantity";
    public static final String TOTAL_AMOUNT_COLUMN = "total_amount";
    public static final String STATUS_COLUMN = "status";
    public static final String TABLE_NAME_WITH_ALIAS = "cart c";
    public static final String ID_COLUMN_WITH_ALIAS = "c.id";
    public static final String USER_ID_COLUMN_WITH_ALIAS = "c.user_id";
    public static final String RESTAURANT_ID_COLUMN_WITH_ALIAS = "c.restaurant_id";
    public static final String FOOD_ID_COLUMN_WITH_ALIAS = "c.food_id";
    public static final String QUANTITY_COLUMN_WITH_ALIAS = "c.quantity";
    public static final String TOTAL_AMOUNT_COLUMN_WITH_ALIAS = "c.total_amount";
    public static final String STATUS_COLUMN_WITH_ALIAS = "c.status";
}
