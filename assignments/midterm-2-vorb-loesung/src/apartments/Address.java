package apartments;

import static java.util.Objects.requireNonNull;

/**
 * Eine Postadresse.
 * DIESE KLASSE DÜRFEN SIE NICHT ÄNDERN.
 */
public class Address {
    private final String streetAndNumber;
    private final int zipCode;
    private final String city;

    public Address(String streetAndNumber, int zipCode, String city) {
        this.streetAndNumber = requireNonNull(streetAndNumber);
        this.zipCode = zipCode;
        this.city = requireNonNull(city);
    }

    public String getStreetAndNumber() {
        return streetAndNumber;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Address address)) {
            return false;
        } else if (zipCode != address.zipCode) {
            return false;
        } else if (!streetAndNumber.equals(address.streetAndNumber)) {
            return false;
        } else {
            return city.equals(address.city);
        }
    }

    @Override
    public String toString() {
        return streetAndNumber + ", " + zipCode + " " + city;
    }
}
