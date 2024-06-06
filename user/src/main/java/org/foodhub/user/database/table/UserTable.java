package org.foodhub.user.database.table;

/**
 * <p>
 *  Holds the table and column names of the user table
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class UserTable {

    public static final String TABLE_NAME = "users";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    public static final String PHONE_NUMBER_COLUMN = "phone_number";
    public static final String EMAIL_ID_COLUMN = "email_id";
    public static final String PASSWORD_COLUMN = "password";
    public static final String TABLE_NAME_WITH_ALIAS = "users u";
    public static final String ID_COLUMN_WITH_ALIAS = "u.id";
    public static final String NAME_COLUMN_WITH_ALIAS = "u.name";
    public static final String PHONE_NUMBER_COLUMN_WITH_ALIAS = "u.phone_number";
    public static final String EMAIL_ID_COLUMN_WITH_ALIAS = "u.email_id";
    public static final String PASSWORD_COLUMN_WITH_ALIAS = "u.password";
}
