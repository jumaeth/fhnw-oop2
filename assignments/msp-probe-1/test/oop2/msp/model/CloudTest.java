package oop2.msp.model;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CloudTest {

    List<Server> servers = new ArrayList<>(List.of(
            new Server("Server 0", "192.168.1.100"),
            new Server("Server 1", "192.168.1.101"),
            new Server("Server 2", "192.168.1.102"),
            new Server("Server 3", "192.168.1.103"),
            new Server("Server 4", "192.168.1.104")));

    App visagebook = new App("VisageBook", "10");
    App piper = new App("Piper", "0.1.65");
    App xmail1 = new App("XMail", "1.2.0");
    App xmail2 = new App("XMail", "2.0.1");
    App metube = new App("MeTube", "5.3beta");

    List<App> apps = new ArrayList<>(List.of(xmail1, xmail2, visagebook, piper, metube));

    List<Deployment> deployments = new ArrayList<>(List.of(
            new Deployment(visagebook, servers.get(4)),
            new Deployment(piper, servers.get(2), servers.get(3), servers.get(4)),
            new Deployment(xmail1, servers.get(0), servers.get(1)),
            new Deployment(xmail2, servers.get(1), servers.get(2), servers.get(3))));

    @Nested
    @TestMethodOrder(OrderAnnotation.class)
    public class ResourceSummaryTest {

        @Test
        @Order(1)
        public void testBasic() {
            var cloud = new Cloud(servers, apps, deployments);
            var expected = """
                    Total CPUs: 20
                    Total memory: 40 GB
                    Total storage: 500 GB""";
            var summary = cloud.resourceSummary();
            assertEquals(expected, summary);

            var otherServers = List.of(
                    new Server("mastorafushi", "192.168.50.100", 64, 512, 4000),
                    new Server("dellafushi", "192.168.50.101", 48, 256, 2000),
                    new Server("remifushi", "192.168.50.110", 64, 512, 8000),
                    new Server("mafushi", "192.168.45.100", 16, 64, 500),
                    new Server("kanifushi", "192.168.45.103", 32, 64, 500),
                    new Server("creek", "192.168.40.100", 8, 32, 200),
                    new Server("river", "192.168.40.101", 8, 64, 500));
            cloud = new Cloud(otherServers, emptyList(), emptyList());
            expected = """
                    Total CPUs: 240
                    Total memory: 1504 GB
                    Total storage: 15700 GB""";
            summary = cloud.resourceSummary();
            assertEquals(expected, summary);
        }

        @Test
        @Order(2)
        public void testEmptyServers() {
            var cloud = new Cloud(emptyList(), emptyList(), emptyList());
            var expected = """
                    Total CPUs: 0
                    Total memory: 0 GB
                    Total storage: 0 GB""";
            var summary = cloud.resourceSummary();
            assertEquals(expected, summary);
        }
    }

    @Nested
    @TestMethodOrder(OrderAnnotation.class)
    public class UndeployedAppsTest {

        @Test
        @Order(3)
        public void testNoDeployment() {
            var cloud = new Cloud(servers, apps, deployments);
            var undeployed = cloud.undeployedApps();
            assertEquals(List.of(metube), undeployed);
        }

        @Test
        @Order(4)
        public void testEmptyServersDeployment() {
            deployments.add(new Deployment(metube, emptySet()));
            var cloud = new Cloud(servers, apps, deployments);
            var undeployed = cloud.undeployedApps();
            assertEquals(List.of(metube), undeployed);
        }

        @Test
        @Order(5)
        public void testOrder() {
            var cloud = new Cloud(servers, apps, deployments.subList(0, 3)); // VisageBook, Piper, XMail 1.2.0
            var undeployed = cloud.undeployedApps();
            assertEquals(List.of(metube, xmail2), undeployed);

            var apps = new ArrayList<>(List.of(xmail2, xmail1, visagebook, piper, metube)); // different order
            cloud = new Cloud(servers, apps, deployments.subList(0, 2)); // VisageBook, Piper
            undeployed = cloud.undeployedApps();
            assertEquals(List.of(metube, xmail1, xmail2), undeployed);
        }

        @Test
        @Order(6)
        public void testAllDeployed() {
            var cloud = new Cloud(servers, apps.subList(0, 4), deployments); // without MeTube
            var undeployed = cloud.undeployedApps();
            assertEquals(emptyList(), undeployed);
        }
    }

    @Nested
    @TestMethodOrder(OrderAnnotation.class)
    public class IPAddressesForAppsTest {

        @Test
        @Order(7)
        public void testSingleAppSingleServer() {
            var cloud = new Cloud(servers, apps, deployments);
            var addresses = cloud.ipAddressesForApps("VisageBook");
            assertEquals(Set.of("192.168.1.104"), addresses);
        }

        @Test
        @Order(8)
        public void testSingleAppMultipleServers() {
            var cloud = new Cloud(servers, apps, deployments);
            var addresses = cloud.ipAddressesForApps("Piper");
            assertEquals(Set.of("192.168.1.102", "192.168.1.103", "192.168.1.104"),
                    addresses);
        }

        @Test
        @Order(9)
        public void testMultipleAppsMultipleServers() {
            var cloud = new Cloud(servers, apps, deployments);
            var addresses = cloud.ipAddressesForApps("XMail");
            assertEquals(Set.of("192.168.1.100", "192.168.1.101", "192.168.1.102", "192.168.1.103"),
                    addresses);
        }

        @Test
        @Order(10)
        public void testUndeployed() {
            var cloud = new Cloud(servers, apps, deployments);
            var addresses = cloud.ipAddressesForApps("MeTube");
            assertEquals(emptySet(), addresses);
        }

        @Test
        @Order(11)
        public void testNonexistent() {
            var cloud = new Cloud(servers, apps, deployments);
            assertThrows(IllegalArgumentException.class, () -> cloud.ipAddressesForApps("TucTuc"));
        }
    }
}
