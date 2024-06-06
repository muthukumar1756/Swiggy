package org.foodhub.database.querybuilder.clauses;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Methods to set and get the join clause parameters.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class JoinClause {

    private final Map<String, String> joinConditions;
    private String tableName;
    private JoinType joinType;

    public JoinClause() {
        this.joinConditions = new HashMap<>();
    }

    public JoinClause setTableName(final String tableName) {
        this.tableName = tableName;

        return this;
    }

    public JoinClause setJoinType(final JoinType joinType) {
        this.joinType = joinType;

        return this;
    }

    public JoinClause setJoinConditions(final String localColumn, final String remoteColumn) {
        joinConditions.put(localColumn, remoteColumn);

        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public Map<String, String> getJoinConditions() {
        return joinConditions;
    }

    public JoinType getJoinType() {
        return joinType;
    }
}