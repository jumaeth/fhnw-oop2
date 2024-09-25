package oop2.msp.io;

import oop2.msp.model.Server;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static java.lang.System.lineSeparator;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(OrderAnnotation.class)
public class ServerIOTest {

    static final String CSV = """
            Name;IP Address;CPU count;Memory;Storage
            mastorafushi;192.168.50.100;64;512 GB;4000 GB
            dellafushi;192.168.50.101;48;256 GB;2000 GB
            remifushi;192.168.50.110;64;512 GB;8000 GB
            mafushi;192.168.45.100;16;64 GB;500 GB
            kanifushi;192.168.45.103;32;64 GB;500 GB
            creek;192.168.40.100;8;32 GB;200 GB
            river;192.168.40.101;8;64 GB;500 GB
            """;

    static final String CVS_MB_TB = """
            Name;IP Address;CPU count;Memory;Storage
            mastorafushi;192.168.50.100;64;512 GB;4 TB
            dellafushi;192.168.50.101;48;256 GB;2 TB
            remifushi;192.168.50.110;64;512 GB;8 TB
            mafushi;192.168.45.100;16;64 GB;500 GB
            kanifushi;192.168.45.103;32;64 GB;500 GB
            creek;192.168.40.100;8;32000 MB;200 GB
            river;192.168.40.101;8;64000 MB;500 GB
            """;

    List<Server> servers = List.of(
            new Server("mastorafushi", "192.168.50.100", 64, 512, 4000),
            new Server("dellafushi", "192.168.50.101", 48, 256, 2000),
            new Server("remifushi", "192.168.50.110", 64, 512, 8000),
            new Server("mafushi", "192.168.45.100", 16, 64, 500),
            new Server("kanifushi", "192.168.45.103", 32, 64, 500),
            new Server("creek", "192.168.40.100", 8, 32, 200),
            new Server("river", "192.168.40.101", 8, 64, 500));

    @Test
    @Order(1)
    public void testReadCsv() throws IOException {
        var in = new ByteArrayInputStream(CSV.getBytes(UTF_8));
        var serversRead = ServerIO.readCsv(in);
        assertEquals(servers, serversRead);
    }

    @Test
    @Order(2)
    public void testReadCsvMBTB() throws IOException {
        var in = new ByteArrayInputStream(CVS_MB_TB.getBytes(UTF_8));
        var serversRead = ServerIO.readCsv(in);
        assertEquals(servers, serversRead);
    }

    @Test
    @Order(3)
    public void testReadCsvClose() throws IOException {
        var closed = new int[1];
        var in = new ByteArrayInputStream(CSV.getBytes(UTF_8)) {
            public void close() {
                closed[0]++;
            }
        };
        ServerIO.readCsv(in);
        assertEquals(1, closed[0]);
    }

    @Test
    @Order(4)
    public void testWriteCsv() throws IOException {
        var out = new ByteArrayOutputStream();
        ServerIO.writeCsv(servers, out);
        var normalized = out.toString(UTF_8).replaceAll(lineSeparator(), "\n");
        assertEquals(CSV, normalized);
    }

    @Test
    @Order(5)
    public void testWriteCsvClose() throws IOException {
        var closed = new int[1];
        var out = new ByteArrayOutputStream() {
            public void close() {
                closed[0]++;
            }
        };
        ServerIO.writeCsv(servers, out);
        assertEquals(1, closed[0]);
    }
}
