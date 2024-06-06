package org.foodhub.restaurant.database.table;

/**
 * <p>
 *  Holds the table and column names of the food table
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class FoodTable {

    public static final String TABLE_NAME = "food";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String RATE_COLUMN = "rate";
    public static final String TYPE_COLUMN = "type";
    public static final String QUANTITY_COLUMN = "quantity";
    public static final String TABLE_NAME_WITH_ALIAS = "food f";
    public static final String ID_COLUMN_WITH_ALIAS = "f.id";
    public static final String NAME_COLUMN_WITH_ALIAS = "f.name";
    public static final String RATE_COLUMN_WITH_ALIAS = "f.rate";
    public static final String TYPE_COLUMN_WITH_ALIAS = "f.type";
    public static final String QUANTITY_COLUMN_WITH_ALIAS = "f.quantity";
}
