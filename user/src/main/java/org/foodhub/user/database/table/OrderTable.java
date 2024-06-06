package org.foodhub.user.database.table;

/**
 * <p>
 *  Holds the table and column names of the order table
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class OrderTable {

    public static final String TABLE_NAME = "orders";
    public static final String ID_COLUMN = "id";
    public static final String USER_ID_COLUMN = "user_id";
    public static final String CART_ID_COLUMN = "cart_id";
    public static final String ADDRESS_ID_COLUMN = "address_id";
    public static final String TABLE_NAME_WITH_ALIAS = "orders o";
    public static final String ID_COLUMN_WITH_ALIAS = "o.id";
    public static final String USER_ID_COLUMN_WITH_ALIAS = "o.user_id";
    public static final String CART_ID_COLUMN_WITH_ALIAS = "o.cart_id";
    public static final String ADDRESS_ID_COLUMN_WITH_ALIAS = "o.address_id";
}
