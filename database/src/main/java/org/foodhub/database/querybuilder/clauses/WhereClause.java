package org.foodhub.database.querybuilder.clauses;

import org.foodhub.database.querybuilder.operator.ConditionalOperator;
import org.foodhub.database.querybuilder.operator.LogicalOperator;

import java.util.List;

/**
 * <p>
 * Methods to set and get the where clause parameters.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class WhereClause {

    private String column;
    private ConditionalOperator conditionalOperator;
    private String value;
    private List<String> values;
    private LogicalOperator logicalOperator;

    public WhereClause setColumn(final String column) {
        this.column = column;

        return this;
    }

    public WhereClause setLogicalOperator(final LogicalOperator logicalOperator) {
        this.logicalOperator = logicalOperator;

        return this;
    }

    public WhereClause setValue(final String value) {
        this.value = value;

        return this;
    }

    public WhereClause setValues(final List<String> values) {
        this.values = values;

        return this;
    }

    public WhereClause setConditionalOperator(final ConditionalOperator conditionalOperator) {
        this.conditionalOperator = conditionalOperator;

        return this;
    }

    public String getColumn() {
        return column;
    }

    public LogicalOperator getLogicalOperator() {
        return logicalOperator;
    }

    public String getValue() {
        return value;
    }

    public List<String> getValues() {
        return values;
    }

    public ConditionalOperator getConditionalOperator() {
        return conditionalOperator;
    }
}
