package org.foodhub.database.querybuilder.operator;

/**
 * <p>
 * Type of conditional operator that can be used in query building
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public enum ConditionalOperator {

    EQUAL(" = "),
    NOT_EQUAL_TO(" != "),
    LESS_THAN(" < "),
    GREATER_THAN(" > "),
    GREATER_THAN_EQUAL(" >= "),
    LESSER_THAN_EQUAL(" >= "),
    IN(" IN "),
    BETWEEN(" BETWEEN "),
    ISNULL(" IS NULL "),
    LIKE(" LIKE ");

    private final String operator;

    ConditionalOperator(final String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
