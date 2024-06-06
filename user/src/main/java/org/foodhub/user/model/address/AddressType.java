package org.foodhub.user.model.address;

import java.util.Optional;

/**
 * <p>
 * Defines the type of address of the user.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public enum AddressType {

    HOME(1),
    OFFICE(2);

    private final Integer id;

    public static int getId(final AddressType addressType) {
        return addressType.id;
    }

    AddressType(final Integer id) {
        this.id = id;
    }

    /**
     * <p>
     * Gets the address type by using id
     * </p>
     *
     * @param id Represents the id of the enum type
     * @return The address type
     */
    public static Optional<AddressType> getTypeById(final Integer id) {
        return switch (id) {
            case 1 -> Optional.of(AddressType.HOME);
            case 2 -> Optional.of(AddressType.OFFICE);
            default -> Optional.empty();
        };
    }
}
