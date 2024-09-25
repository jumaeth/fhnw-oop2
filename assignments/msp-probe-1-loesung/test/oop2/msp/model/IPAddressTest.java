package oop2.msp.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IPAddressTest {

    @Test
    public void testParseFormat() {
        var addresses = List.of("192.168.1.1", "255.255.255.255", "8.8.8.8",
                "0.0.0.0", "51.52.53.54", "127.0.128.0", "1.2.3.4");
        for (var formatted : addresses) {
            var addr = new IPAddress(formatted);
            assertEquals(formatted, addr.formatted());
        }
    }

    @Test
    public void testParseInvalid() {
        var addresses = List.of("256.1.1.1", "1.-1.1.1", "1.1.1000.1", "1.1.1.abc");
        for (var formatted : addresses) {
            assertThrows(IllegalArgumentException.class, () -> new IPAddress(formatted));
        }
    }
}
