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
        // TODO
    }
}
