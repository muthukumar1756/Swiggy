package org.foodhub.user.model.address;

import java.util.Objects;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import org.foodhub.common.hibernate.validatorgroup.address.GetAddressValidator;
import org.foodhub.common.hibernate.validatorgroup.address.PostAddressValidator;

/**
 * <p>
 * Represents address entity with properties and methods.
 * </p>
 *
 * @author Muthu kumar V
 * @version 1.0
 */
public final class Address {

    private Long id;
    @NotNull(message = "UserId can't be null", groups = {PostAddressValidator.class, GetAddressValidator.class})
    @Positive(message = "User id can't be negative or zero", groups = {PostAddressValidator.class, GetAddressValidator.class})
    private Long userId;
    @NotNull(message = "HouseNumber can't be null", groups = {PostAddressValidator.class})
    @Size(max = 10)
    private String houseNumber;
    @NotNull(message = "StreetName can't be null", groups = {PostAddressValidator.class})
    @Pattern(message = "Enter a valid street name", regexp = "^[A-Za-z][A-Za-z\\s]{3,20}$", groups = {PostAddressValidator.class})
    private String streetName;
    @NotNull(message = "AreaName can't be null", groups = {PostAddressValidator.class})
    @Pattern(message = "Enter a valid area name", regexp = "^[A-Za-z][A-Za-z\\s]{3,20}$", groups = {PostAddressValidator.class})
    private String areaName;
    @NotNull(message = "CityName can't be null", groups = {PostAddressValidator.class})
    @Pattern(message = "Enter a valid city name", regexp = "^[A-Za-z][A-Za-z\\s]{3,20}$", groups = {PostAddressValidator.class})
    private String cityName;
    @NotNull(message = "Pincode can't be null", groups = {PostAddressValidator.class})
    @Pattern(message = "Enter a valid pincode", regexp = "^[1-9]\\d{5}$", groups = {PostAddressValidator.class})
    private String pincode;
    @NotNull(message = "Address type can't be null", groups = {PostAddressValidator.class})
    private AddressType addressType;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getAreaName() {
        return areaName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getPincode() {
        return pincode;
    }

    public AddressType getAddressType() {
        return addressType;
    }

    @Override
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }

        if (object instanceof Address) {
            return this.hashCode() == object.hashCode();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class AddressBuilder {

        private final Address address;

        public AddressBuilder() {
            address = new Address();
        }

        public AddressBuilder setId(final Long id) {
            address.id = id;

            return this;
        }

        public AddressBuilder setUserId(final Long userId) {
            address.userId = userId;

            return this;
        }

        public AddressBuilder setHouseNumber(final String houseNumber) {
            address.houseNumber = houseNumber;

            return this;
        }

        public AddressBuilder setStreetName(final String streetName) {
            address.streetName = streetName;

            return this;
        }

        public AddressBuilder setAreaName(final String areaName) {
            address.areaName = areaName;

            return this;
        }

        public AddressBuilder setCityName(final String cityName) {
            address.cityName = cityName;

            return this;
        }

        public AddressBuilder setPincode(final String pincode) {
            address.pincode = pincode;

            return this;
        }

        public AddressBuilder setAddressType(final AddressType addressType) {
            address.addressType = addressType;

            return this;
        }

        public Address build() {
            return address;
        }
    }
}
