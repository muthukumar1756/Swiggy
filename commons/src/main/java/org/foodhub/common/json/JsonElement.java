package org.foodhub.common.json;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * <p>
 * Handles the methods to work with json node.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class JsonElement {

    private final JsonNode jsonNode;

    public JsonElement(final JsonNode jsonNode) {
        this.jsonNode = jsonNode;
    }

    /**
     * <p>
     * Method for accessing value of the specified field of an object node.
     * </p>
     *
     * @return Value if this node is an object and has value for the specified field. Null otherwise.
     */
    public String getValue(final String fieldName) {
        return jsonNode.get(fieldName).asText();
    }

    /**
     * <p>
     * Method that allows checking whether this node is JSON Object node.
     * </p>
     *
     * @return True if this node is a JSON Object node, false otherwise
     */
    public boolean hasElement(final String fieldName) {
        return jsonNode.has(fieldName);
    }
}
