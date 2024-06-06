package org.foodhub.restaurant.model.food;

import java.util.Optional;

/**
 * <p>
 * Defines the food type.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public enum FoodType {

    VEG(1),
    NONVEG(2);

    private final Integer id;

    FoodType(final Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    /**
     * <p>
     * Gets the food type by using id
     * </p>
     *
     * @param id Represents the id of the enum type
     * @return The food type
     */
    public static Optional<FoodType> getTypeById(final Integer id) {
        return switch (id) {
            case 1 -> Optional.of(FoodType.VEG);
            case 2 -> Optional.of(FoodType.NONVEG);
            default -> Optional.empty();
        };
    }
}