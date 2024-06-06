package org.foodhub.common.hibernate.impl;

import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import org.hibernate.validator.HibernateValidator;

import org.foodhub.common.hibernate.HibernateEntityValidator;
import org.foodhub.common.json.JsonFactory;
import org.foodhub.common.json.JsonObject;

/**
 * <p>
 * Wraps the Validator and the methods of hibernate validator can be used from this class.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class HibernateEntityValidatorImpl implements HibernateEntityValidator {

    private final JsonFactory jsonFactory;

    private HibernateEntityValidatorImpl() {
        jsonFactory = JsonFactory.getInstance();
    }

    /**
     * <p>
     *  Creates the instance of the class
     * </p>
     */
    private static class SingletonHolder {

        private static final HibernateEntityValidatorImpl VALIDATOR_FACTORY = new HibernateEntityValidatorImpl();
        private static final Validator validator = Validation.byProvider(HibernateValidator.class).configure()
                .buildValidatorFactory().getValidator();
    }

    /**
     * <p>
     * Gets the instance of validator factory object.
     * </p>
     *
     * @return The object of validator factory class
     */
    public static HibernateEntityValidatorImpl getInstance() {
        return SingletonHolder.VALIDATOR_FACTORY;
    }

    /**
     * <p>
     * Gets the hibernate validator.
     * </p>
     *
     * @return The instance of hibernate validator
     */
    private Validator getValidator() {
        return SingletonHolder.validator;
    }

    /**
     * {@inheritDoc}
     *
     * @return The json object containing violations if exists
     */
    public <T> JsonObject validate(final T object, final Class<?> groups) {
        final Set<ConstraintViolation<T>> violationSet = getValidator().validate(object, groups);
        final JsonObject jsonObject = jsonFactory.createObjectNode();

        if (violationSet.isEmpty()) {
            return jsonObject;
        }

        for (final ConstraintViolation<T> violation : violationSet) {
            jsonObject.put(violation.getPropertyPath().toString(), violation.getMessage());
        }

        return jsonObject;
    }
}