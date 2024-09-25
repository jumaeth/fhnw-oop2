package oop2.msp.model;

import java.util.stream.Stream;

public class IPAddress {

    private final int bits;

    public IPAddress(int bits) {
        this.bits = bits;
    }

    public IPAddress(String formatted) {
        this(parse(formatted));
    }

    private static int parse(String ipAddress) {
        int[] parts = Stream.of(ipAddress.split("\\."))
                .mapToInt(Integer::parseInt)
                .peek(i -> { if (i < 0 || i > 255) throw new IllegalArgumentException(); })
                .toArray();
        return parts[0] << 24 | parts[1] << 16 | parts[2] << 8 | parts[3];
    }

    public int bits() {
        return bits;
    }

    public String formatted() {
        return (bits >> 24 & 0xff)
                + "." + (bits >> 16 & 0xff)
                + "." + (bits >> 8 & 0xff)
                + "." + (bits & 0xff);
    }

    @Override
    public String toString() {
        return formatted();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof IPAddress)) return false;
        return bits == ((IPAddress) o).bits;
    }

    @Override
    public int hashCode() {
        return bits;
    }
}
