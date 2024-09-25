package webtraffic;

import java.util.Arrays;

public class IPv4Address {

    public static final String PATTERN = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";

    private final byte[] bytes;

    public IPv4Address(byte[] bytes) {
        if (bytes.length != 4) {
            throw new IllegalArgumentException();
        }
        this.bytes = bytes;
    }

    public IPv4Address(String formatted) {
        if (!formatted.matches(PATTERN)) {
            throw new IllegalArgumentException("invalid address format");
        }
        var parts = formatted.split("\\.");
        bytes = new byte[4];
        for (int i = 0; i < 4; i++) {
            int n = Integer.parseInt(parts[i]);
            if (n > 255) {
                throw new IllegalArgumentException("invalid IPv4 address");
            }
            bytes[i] = (byte) n;
        }
    }

    public byte[] getBytes() {
        return bytes;
    }

    @Override
    public String toString() {
        var formatted = "";
        for (int i = 0; i < 4; i++) {
            formatted += "." + (bytes[i] & 0xFF); // convert to unsigned
        }
        return formatted.substring(1); // remove first '.'
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof IPv4Address other
                && Arrays.equals(bytes, other.bytes);
    }
}
