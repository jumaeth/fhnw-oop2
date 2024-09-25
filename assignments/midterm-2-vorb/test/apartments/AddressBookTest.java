package apartments;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddressBookTest {

    ArrayList<Address> variousAddresses = new ArrayList<>(List.of(
            new Address("Tellstrasse 22", 4053, "Basel"),
            new Address("Rte de la Pierre 10", 1024, "Ecublens"),
            new Address("Bahnhofstrasse 7", 3005, "Bern"),
            new Address("Baselweg 101", 4147, "Aesch"),
            new Address("Liebmattstrasse 4", 4147, "Duggingen"),
            new Address("Av. des Alpes 27", 2000, "Neuchâtel"),
            new Address("Baselweg 87", 4147, "Aesch"),
            new Address("Christoph Merian-Platz 8", 4052, "Basel")));

    ArrayList<Address> windischAddresses = new ArrayList<>(List.of(
            new Address("Bergstrasse 18", 5210, "Windisch"),
            new Address("Arenastrasse 17", 5210, "Windisch"),
            new Address("Bahnhofstrasse 6", 5210, "Windisch"),
            new Address("Habsburgerstrasse 13", 5210, "Windisch"),
            new Address("Bahnhofstrasse 5", 5210, "Windisch"),
            new Address("Fliederweg 9", 5210, "Windisch")));

    @Test
    void sortZipCode() {
        var book = new AddressBook(variousAddresses);

        book.sort();

        assertEquals(1024, book.getAddresses().get(0).getZipCode());
        assertEquals(2000, book.getAddresses().get(1).getZipCode());
        assertEquals(3005, book.getAddresses().get(2).getZipCode());
        assertEquals(4052, book.getAddresses().get(3).getZipCode());
        assertEquals(4053, book.getAddresses().get(4).getZipCode());
        assertEquals(4147, book.getAddresses().get(5).getZipCode());
        assertEquals(4147, book.getAddresses().get(6).getZipCode());
        assertEquals(4147, book.getAddresses().get(7).getZipCode());
    }

    @Test
    void sortZipCodeCity() {
        var book = new AddressBook(variousAddresses);

        book.sort();

        assertEquals(1024, book.getAddresses().get(0).getZipCode());
        assertEquals(2000, book.getAddresses().get(1).getZipCode());
        assertEquals(3005, book.getAddresses().get(2).getZipCode());
        assertEquals(4052, book.getAddresses().get(3).getZipCode());
        assertEquals(4053, book.getAddresses().get(4).getZipCode());
        assertEquals(4147, book.getAddresses().get(5).getZipCode());
        assertEquals("Aesch", book.getAddresses().get(5).getCity());
        assertEquals(4147, book.getAddresses().get(6).getZipCode());
        assertEquals("Aesch", book.getAddresses().get(6).getCity());
        assertEquals(4147, book.getAddresses().get(7).getZipCode());
        assertEquals("Duggingen", book.getAddresses().get(7).getCity());
    }

    @Test
    void sortStreetAndNumber() {
        var book = new AddressBook(windischAddresses);

        book.sort();

        assertEquals("Arenastrasse 17", book.getAddresses().get(0).getStreetAndNumber());
        assertEquals("Bahnhofstrasse 5", book.getAddresses().get(1).getStreetAndNumber());
        assertEquals("Bahnhofstrasse 6", book.getAddresses().get(2).getStreetAndNumber());
        assertEquals("Bergstrasse 18", book.getAddresses().get(3).getStreetAndNumber());
        assertEquals("Fliederweg 9", book.getAddresses().get(4).getStreetAndNumber());
        assertEquals("Habsburgerstrasse 13", book.getAddresses().get(5).getStreetAndNumber());
    }
}
