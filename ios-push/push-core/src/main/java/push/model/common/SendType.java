package push.model.common;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public enum SendType {
    ALL("all"),
    TAG("tag"),
    ALIAS("alias"),
    USER("user"),
    DEVICE("device"),
    TOKEN("token");

    private String value;

    SendType(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public static boolean contains(String value) {
        return value != null && Arrays.stream(values()).anyMatch(s -> Objects.equals(s.value, value));
    }

    public static SendType convert(String value) {
        return Stream.of(values()).filter(e -> Objects.equals(e.value, value)).findAny().orElse(null);
    }

}
