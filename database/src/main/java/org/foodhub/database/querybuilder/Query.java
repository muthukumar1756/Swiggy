package org.foodhub.database.querybuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.foodhub.database.querybuilder.clauses.JoinClause;
import org.foodhub.database.querybuilder.clauses.WhereClause;

/**
 * <p>
 * Handles the creation of query object and methods to get the query statements and conditions.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class Query {

    private Map<String, String> insertFields;
    private Collection<String> selectFields;
    private Map<String, String> updateFields;
    private boolean isDeleteStatement;
    private String tableName;
    private Collection<JoinClause> joins;
    private Collection<WhereClause> whereConditions;
    private boolean isReturningId;

    public Query() {
        insertFields = new HashMap<>();
        selectFields = new ArrayList<>();
        updateFields = new HashMap<>();
        joins = new ArrayList<>();
        whereConditions = new ArrayList<>();
    }

    public Map<String, String> getInsertFields() {
        return Collections.unmodifiableMap(insertFields);
    }

    public Collection<String> getSelectFields() {
        return Collections.unmodifiableCollection(selectFields);
    }

    public Map<String, String> getUpdateFields() {
        return Collections.unmodifiableMap(updateFields);
    }

    public boolean isDeleteStatement() {
        return isDeleteStatement;
    }

    public String getTableName() {
        return tableName;
    }

    public Collection<JoinClause> getJoins() {
        return Collections.unmodifiableCollection(joins);
    }

    public Collection<WhereClause> getWhereConditions() {
        return Collections.unmodifiableCollection(whereConditions);
    }

    public boolean isReturningId() {
        return isReturningId;
    }

    /**
     * <p>
     * Method to set the statements and conditions of the query and to build the query instance.
     * </p>
     *
     * @author Muthu kumar V
     * @version 1.0
     */
    public static class QueryBuilder {

        private final Query query;

        public QueryBuilder() {
            query = new Query();
        }

        public QueryBuilder setInsertFields(final Map<String, String> insertFields) {
            query.insertFields = insertFields;

            return this;
        }

        public QueryBuilder setSelectFields(final Collection<String> selectFields) {
            query.selectFields = selectFields;

            return this;
        }

        public QueryBuilder setUpdateFields(final Map<String, String> updateFields) {
            query.updateFields = updateFields;

            return this;
        }

        public QueryBuilder setDeleteStatement(final boolean isDeleteStatement) {
            query.isDeleteStatement = isDeleteStatement;

            return this;
        }

        public QueryBuilder setTableName(final String tableName) {
            query.tableName = tableName;

            return this;
        }

        public QueryBuilder setJoins(final Collection<JoinClause> joins) {
            query.joins = joins;

            return this;
        }

        public QueryBuilder setWhereClauses(final Collection<WhereClause> whereConditions) {
            query.whereConditions = whereConditions;

            return this;
        }

        public QueryBuilder setReturningId(final boolean returningId) {
            query.isReturningId = returningId;

            return this;
        }

        public Query buildQuery() {
            return query;
        }
    }
}
