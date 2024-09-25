package apartments;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {

    private final ArrayList<Address> addresses = new ArrayList<>();

    public AddressBook(List<Address> addresses) {
        this.addresses.addAll(addresses);
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void sort() {
        addresses.sort((a1, a2) -> {
            if (a1.getZipCode() != a2.getZipCode()) {
                return Integer.compare(a1.getZipCode(), a2.getZipCode());
            } else if (!a1.getCity().equals(a2.getCity())) {
                return a1.getCity().compareTo(a2.getCity());
            } else {
                return a1.getStreetAndNumber().compareTo(a2.getStreetAndNumber());
            }
        });
    }
}
