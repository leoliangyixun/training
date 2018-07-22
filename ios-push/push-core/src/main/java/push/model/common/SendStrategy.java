package push.model.common;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public enum SendStrategy {
    //所有设备 | 指定设备|最近使用设备
    ALL("all"),
    PHONE("phone"),
    PAD("pad"),
    RECENT_USE("recent_use");

    private String value;

    SendStrategy(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    public static boolean contains(String value) {
        return value != null && Arrays.stream(values()).anyMatch(s -> Objects.equals(s.value, value));
    }

    public static SendStrategy convert(String value) {
        return Stream.of(values()).filter(e -> Objects.equals(e.value, value)).findAny().orElse(null);
    }

}
