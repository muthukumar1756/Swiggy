package org.foodhub.database.querybuilder.clauses;

/**
 * <p>
 * Type of join that can be used in query building
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public enum JoinType {

    JOIN(" JOIN "),
    RIGHT_JOIN(" RIGHT JOIN "),
    LEFT_JOIN(" LEFT JOIN "),
    CROSS_JOIN(" CROSS JOIN "),
    NATURAL_JOIN(" NATURAL JOIN "),
    SELF_JOIN(" SELF JOIN "),
    FULL_OUTER_JOIN(" FULL JOIN ");

    private final String join;

    JoinType(final String join) {
        this.join = join;
    }

    public String getJoin() {
        return join;
    }
}
