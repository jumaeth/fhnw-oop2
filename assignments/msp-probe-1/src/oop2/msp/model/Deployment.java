package oop2.msp.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.joining;

public class Deployment {

    private final App app;
    private final Set<Server> servers = new HashSet<>();

    public Deployment(App app, Collection<Server> servers) {
        this.app = requireNonNull(app);
        this.servers.addAll(servers);
    }

    public Deployment(App app, Server... servers) {
        this(app, asList(servers));
    }

    public App getApp() {
        return app;
    }

    /**
     * Returns a "live" reference to the set of servers this
     * deployment applies to, meaning the set can be modified
     * through this reference.
     */
    public Set<Server> getServers() {
        return servers;
    }

    @Override
    public String toString() {
        if (servers.isEmpty()) {
            return app + " not deployed";
        } else {
            var serverString = this.servers.stream()
                    .map(Server::toString)
                    .sorted()
                    .collect(joining(", "));
            return app + " deployed on " + serverString;
        }
    }
}
