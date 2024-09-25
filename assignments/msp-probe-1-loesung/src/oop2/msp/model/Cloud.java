package oop2.msp.model;

import java.util.List;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

public class Cloud {

    private final List<Server> servers;
    private final List<App> apps;
    private final List<Deployment> deployments;

    public Cloud(List<Server> servers, List<App> apps, List<Deployment> deployments) {
        this.servers = servers;
        this.apps = apps;
        this.deployments = deployments;
    }

    /* ------------------- */

    /**
     * Gibt eine Zusammenfassung der Ressourcen aller Server in der Cloud zurück,
     * in folgendem Format:
     *
     * Total CPUs: 40
     * Total memory: 230 GB
     * Total storage: 12040 GB
     */
    public String resourceSummary() {
        int totalCpus = servers.stream().mapToInt(Server::getCpuCount).sum();
        int totalMem = servers.stream().mapToInt(Server::getMemoryGB).sum();
        int totalStorage = servers.stream().mapToInt(Server::getStorageGB).sum();
        return "Total CPUs: " + totalCpus + "\n"
                + "Total memory: " + totalMem + " GB\n"
                + "Total storage: " + totalStorage + " GB";
    }

    /**
     * Gibt alle Apps zurück, welche zurzeit auf keinem Server deployt sind,
     * aufsteigend alphabetisch sortiert, zuerst nach Name und als zweites
     * Kriterium nach Version.
     */
    public List<App> undeployedApps() {
        return apps.stream()
                .filter(not(this::isDeployed))
                .sorted(comparing(App::toString)) // includes name and version
                .collect(toList());
    }

    private boolean isDeployed(App app) {
        return deployments.stream()
                .filter(d -> d.getApp() == app)
                .anyMatch(d -> !d.getServers().isEmpty());
    }

    /**
     * Gibt sämtliche IP-Adressen (formatiert als Strings) zurück, unter
     * welchen Apps mit dem gegebenen Namen erreichbar sind (es könnte mehrere
     * Apps mit dem gleichen Namen aber mit unterschiedlicher Version geben).
     * Falls keine Apps mit diesem Namen existieren, wird eine
     * {@link IllegalArgumentException} geworfen; falls Apps mit diesem
     * Namen existieren, aber keine davon deployt ist, wird ein leeres
     * Set zurückgegeben.
     */
    public Set<String> ipAddressesForApps(String appName) {
        if (apps.stream().noneMatch(app -> app.getName().equals(appName))) {
            throw new IllegalArgumentException("no apps named '" + appName + "'");
        }
        return deployments.stream()
                .filter(d -> d.getApp().getName().equals(appName))
                .flatMap(d -> d.getServers().stream())
                .map(s -> s.getAddress().formatted())
                .collect(toSet());
    }
}
