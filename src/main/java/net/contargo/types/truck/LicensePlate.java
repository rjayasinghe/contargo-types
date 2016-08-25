package net.contargo.types.truck;

import net.contargo.types.util.Assert;


/**
 * Each {@link net.contargo.domain.Truck} can be identified by its license plate.
 *
 * @author  Aljona Murygina - murygina@synyx.de
 * @since  0.1.0
 */
public final class LicensePlate {

    private static final Country DEFAULT_COUNTRY = LicensePlateCountry.GERMANY;

    private final String value;
    private Country country;

    /**
     * Use {@link #forValue(String)} to build a new {@link LicensePlate} instance.
     *
     * @param  value  represents a license plate
     */
    private LicensePlate(String value) {

        this.value = value;
    }

    /**
     * Build a new {@link LicensePlate} with a {@link String} value.
     *
     * @param  value  represents a license plate
     *
     * @return  a {@link LicensePlate}, never {@code null}
     */
    public static LicensePlate forValue(String value) {

        Assert.notBlank(value, "Value for license plate must not be null or empty");

        LicensePlate licensePlate = new LicensePlate(value);
        licensePlate.country = DEFAULT_COUNTRY;

        return licensePlate;
    }


    /**
     * Set the {@link Country} for this {@link LicensePlate}.
     *
     * @param  country  never {@code null}
     *
     * @return  {@link LicensePlate}, never {@code null}
     *
     * @since  0.2.0
     */
    public LicensePlate withCountry(Country country) {

        Assert.notNull(country, "Country must not be null");

        this.country = country;

        return this;
    }


    /**
     * Return the {@link LicensePlate} in a formatted way.
     *
     * @return  formatted {@link LicensePlate}, never {@code null}
     */
    @Override
    public String toString() {

        return country.getLicensePlateHandler().normalize(value);
    }


    /**
     * Check if the {@link LicensePlate} is valid.
     *
     * @return  {@code true} if the {@link LicensePlate} is valid, else {@code false}
     */
    public boolean isValid() {

        return country.getLicensePlateHandler().validate(value);
    }


    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        String o1 = this.toString();
        String o2 = obj.toString();

        return o1.equals(o2);
    }


    @Override
    public int hashCode() {

        return this.toString().hashCode();
    }


    /**
     * Get the {@link Country} of this {@link LicensePlate}.
     *
     * @return  country, never {@code null}
     *
     * @since  0.2.0
     */
    public Country getCountry() {

        return country;
    }
}