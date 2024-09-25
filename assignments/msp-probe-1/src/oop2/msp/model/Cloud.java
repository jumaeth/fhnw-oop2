package oop2.msp.model;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

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
        return "Total CPUs: " + servers.stream().mapToInt(Server::getCpuCount).sum() + "\n"
                + "Total memory: " + servers.stream().mapToInt(Server::getMemoryGB).sum() + " GB\n"
                + "Total storage: " + servers.stream().mapToInt(Server::getStorageGB).sum() + " GB";
    }

    /**
     * Gibt alle Apps zurück, welche zurzeit auf keinem Server deployt sind,
     * aufsteigend alphabetisch sortiert, zuerst nach Name und als zweites
     * Kriterium nach Version.
     */
    public List<App> undeployedApps() {
        List<App> deployedApps = deployments.stream().map(Deployment::getApp).toList();
        List<App> tttt = apps.stream()
                .filter(app -> !deployedApps.contains(app))
                .sorted(Comparator.comparing(App::getName).thenComparing(App::getVersion)).toList();
        return tttt;
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
        // TODO
        return null;
    }
}
