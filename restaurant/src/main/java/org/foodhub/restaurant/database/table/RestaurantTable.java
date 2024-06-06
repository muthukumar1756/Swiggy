package org.foodhub.restaurant.database.table;

/**
 * <p>
 *  Holds the table and column names of the restaurant table
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class RestaurantTable {

    public static final String TABLE_NAME = "restaurant";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String PHONE_NUMBER_COLUMN = "phone_number";
    public static final String EMAIL_ID_COLUMN = "email_id";
    public static final String PASSWORD_COLUMN = "password";
    public static final String TABLE_NAME_WITH_ALIAS = "restaurant r";
    public static final String ID_COLUMN_WITH_ALIAS = "r.id";
    public static final String NAME_COLUMN_WITH_ALIAS = "r.name";
    public static final String PHONE_NUMBER_COLUMN_WITH_ALIAS = "r.phone_number";
    public static final String EMAIL_ID_COLUMN_WITH_ALIAS = "r.email_id";
    public static final String PASSWORD_COLUMN_WITH_ALIAS = "r.password";
}
