package org.foodhub.database.querywriter.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.foodhub.database.querybuilder.operator.LogicalOperator;
import org.foodhub.database.querywriter.QueryWriter;
import org.foodhub.database.querybuilder.Query;
import org.foodhub.database.querybuilder.clauses.JoinClause;
import org.foodhub.database.querybuilder.clauses.WhereClause;

/**
 * <p>
 * Handles methods to build the query statement
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class QueryWriterImpl implements QueryWriter {

    private final StringBuilder queryStatement;

    private QueryWriterImpl() {
        queryStatement = new StringBuilder();
    }

    /**
     * <p>
     * Creates the instance of the class
     * </p>
     */
    private static class InstanceHolder {

        private static final QueryWriter QUERY_WRITER = new QueryWriterImpl();
    }

    /**
     * <p>
     * Gets the instance of the query builder class.
     * </p>
     *
     * @return The query builder instance
     */
    public static QueryWriter getInstance() {
        return InstanceHolder.QUERY_WRITER;
    }

    /**
     * <p>
     * Writes the query statement using the given parameters.
     * </p>
     *
     * @param query Represent the query instance with properties to build query statement
     * @return The select query
     */
    public String writeQuery(final Query query) {
        queryStatement.setLength(0);

        if (Objects.nonNull(query.getTableName()) && !query.getTableName().isBlank()) {

            if (!query.getSelectFields().isEmpty()) {
                queryStatement.append("SELECT ");
                appendSelectFields(query.getSelectFields());
                queryStatement.append(" FROM ").append(query.getTableName());
            }

            if (!query.getInsertFields().isEmpty()) {
                queryStatement.append("INSERT INTO ").append(query.getTableName());
                appendInsertFields(query.getInsertFields().keySet().stream().toList());
                queryStatement.append(" VALUES ");
                appendValues(query.getInsertFields().values().stream().toList());
            }

            if (!query.getUpdateFields().isEmpty()) {
                queryStatement.append("UPDATE ").append(query.getTableName()).append(" SET ");
                appendUpdateFields(query.getUpdateFields());
            }

            if (query.isDeleteStatement()) {
                queryStatement.append("DELETE FROM ").append(query.getTableName());
            }

            if (!query.getJoins().isEmpty()) {

                for (final JoinClause joinClause : query.getJoins()) {
                    queryStatement.append(joinClause.getJoinType().getJoin())
                            .append(joinClause.getTableName()).append(" ON ");
                    appendJoinConditions(joinClause.getJoinConditions());
                }
            }

            if (!query.getWhereConditions().isEmpty()) {
                queryStatement.append(" WHERE ");
                appendWhereConditions(query.getWhereConditions());
            }

            if (query.isReturningId()) {
                queryStatement.append(" RETURNING ID ");
            }
        }

        return queryStatement.toString();
    }

    /**
     * <p>
     * Appends the insert fields in the query.
     * </p>
     *
     * @param fields Represents the insert fields of the query
     */
    private void appendInsertFields(final Collection<String> fields) {
        queryStatement.append(" (");
        boolean isFirstField = false;

        for (final String field : fields) {

            if (isFirstField) {
                queryStatement.append(", ");
            } else {
                isFirstField = true;
            }
            queryStatement.append(field);
        }
        queryStatement.append(") ");
    }

    /**
     * <p>
     * Appends the select fields in the query.
     * </p>
     *
     * @param fields Represents the select fields of the query
     */
    private void appendSelectFields(final Collection<String> fields) {
        boolean isFirstField = false;

        for (final String field : fields) {

            if (isFirstField) {
                queryStatement.append(", ");
            } else {
                isFirstField = true;
            }
            queryStatement.append(field);
        }
    }

    /**
     * <p>
     * Appends the update fields to the update query.
     * </p>
     *
     * @param updateFields Represents the update fields of the query
     */
    private void appendUpdateFields(final Map<String, String> updateFields) {
        boolean isFirstField = false;

        for (final Map.Entry<String, String> entry : updateFields.entrySet()) {

            if (isFirstField) {
                queryStatement.append(", ");
            } else {
                isFirstField = true;
            }

            if ("?".equals(entry.getValue())) {
                queryStatement.append(entry.getKey()).append(" = ").append(entry.getValue());
            } else {
                queryStatement.append(entry.getKey()).append(" = '").append(entry.getValue()).append("'");
            }
        }
    }

    /**
     * <p>
     * Appends the join condition in the query.
     * </p>
     *
     * @param joinConditions Represents the join conditions of the query
     */
    private void appendJoinConditions(final Map<String, String> joinConditions) {
        boolean isFirstField = false;

        for (final Map.Entry<String, String> entry : joinConditions.entrySet()) {

            if (isFirstField) {
                queryStatement.append(" AND ");
            } else {
                isFirstField = true;
            }
            queryStatement.append(entry.getKey()).append(" = ").append(entry.getValue());
        }
    }

    /**
     * <p>
     * Appends the where condition in the query
     * </p>
     *
     * @param whereClauses Represents the where conditions of the query
     */
    private void appendWhereConditions(final Collection<WhereClause> whereClauses) {
        boolean isFirstField = false;

        for (final WhereClause whereClause : whereClauses) {

            if (isFirstField) {
                queryStatement.append(whereClause.getLogicalOperator().getOperator());
            } else {
                isFirstField = true;
            }
            queryStatement.append(whereClause.getColumn());

            switch (whereClause.getConditionalOperator()) {
                case EQUAL, NOT_EQUAL_TO, LESS_THAN, LESSER_THAN_EQUAL, GREATER_THAN, GREATER_THAN_EQUAL, LIKE -> {
                    if ("?".equals(whereClause.getValue())) {
                        queryStatement.append(whereClause.getConditionalOperator().getOperator())
                                .append(whereClause.getValue());
                    } else {
                        queryStatement.append(whereClause.getConditionalOperator().getOperator()).append("'")
                                .append(whereClause.getValue()).append("'");
                    }
                }
                case IN -> {
                    queryStatement.append(whereClause.getConditionalOperator().getOperator());
                    appendValues(whereClause.getValues());
                }
                case BETWEEN -> {
                    final List<String> values = whereClause.getValues();

                    queryStatement.append(whereClause.getConditionalOperator().getOperator()).append("'")
                            .append(values.get(0)).append("'").append(LogicalOperator.AND.getOperator()).append("'")
                            .append(values.get(1)).append("'");
                }
                case ISNULL -> queryStatement.append(whereClause.getConditionalOperator().getOperator());
            }
        }
    }

    /**
     * <p>
     * Appends the field values in the query
     * </p>
     *
     * @param fieldValues Represents the field values of the query
     */
    private void appendValues(final Collection<String> fieldValues) {
        queryStatement.append(" (");
        boolean isFirstField = false;

        for (final String fieldValue : fieldValues) {

            if (isFirstField) {
                queryStatement.append(", ");
            } else {
                isFirstField = true;
            }

            if ("?".equals(fieldValue)) {
                queryStatement.append(fieldValue);
            } else {
                queryStatement.append("'").append(fieldValue).append("'");
            }
        }
        queryStatement.append(") ");
    }
}