package org.foodhub.database.querybuilder.operator;

/**
 * <p>
 * Type of logical operator that can be used in query building.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public enum LogicalOperator {

    AND(" AND "),
    OR(" OR "),
    NOT(" NOT ");

    private final String operator;

    LogicalOperator(final String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
