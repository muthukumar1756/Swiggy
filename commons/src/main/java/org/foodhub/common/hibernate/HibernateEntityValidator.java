package org.foodhub.common.hibernate;

import org.foodhub.common.json.JsonObject;

/**
 * <p>
 * Handles the method for the hibernate validation.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public interface HibernateEntityValidator {

    /**
     * <p>
     * Validates the object and returns the violations if exits
     * </p>
     *
     * @return The json object containing violations if exists
     */
    <T> JsonObject validate(final T object, final Class<?> groups);
}

