package org.foodhub.database.querywriter;

import org.foodhub.database.querybuilder.Query;

/**
 * <p>
 * Handles methods to build the update query statement
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public interface QueryWriter {

    /**
     * <p>
     *  Writes the query statement using the given parameters.
     * </p>
     *
     * @param query Represent the query instance with properties to build query statement
     * @return The select query
     */
    String writeQuery(final Query query);
}