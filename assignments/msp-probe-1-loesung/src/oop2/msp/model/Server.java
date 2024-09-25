package oop2.msp.model;

import java.util.Objects;

public class Server {

    private static final int DEFAULT_CPUS = 4;
    private static final int DEFAULT_MEM = 8;
    private static final int DEFAULT_STORAGE = 100;

    private final String name;
    private final IPAddress address;

    private final int cpuCount;
    private final int memoryGB;
    private final int storageGB;

    public Server(String name, IPAddress address, int cpuCount, int memoryGB, int storageGB) {
        this.name = name;
        this.address = address;
        this.cpuCount = cpuCount;
        this.memoryGB = memoryGB;
        this.storageGB = storageGB;
    }

    public Server(String name, String address, int cpuCount, int memoryGB, int storageGB) {
        this(name, new IPAddress(address), cpuCount, memoryGB, storageGB);
    }

    public Server(String name, String address) {
        this(name, address, DEFAULT_CPUS, DEFAULT_MEM, DEFAULT_STORAGE);
    }

    public String getName() {
        return name;
    }

    public IPAddress getAddress() {
        return address;
    }

    public int getCpuCount() {
        return cpuCount;
    }

    public int getMemoryGB() {
        return memoryGB;
    }

    public int getStorageGB() {
        return storageGB;
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %d CPUs, %d GB memory, %d GB storage)",
                getName(), getAddress(), getCpuCount(), getMemoryGB(), getStorageGB());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Server server)) return false;
        return cpuCount == server.cpuCount
               && memoryGB == server.memoryGB
               && storageGB == server.storageGB
               && Objects.equals(name, server.name)
               && Objects.equals(address, server.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, cpuCount, memoryGB, storageGB);
    }
}
