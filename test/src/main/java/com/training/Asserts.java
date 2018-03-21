package com.training;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.Objects;

public class Asserts {

    public static void notNull(final Object object, final String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }

        if (object instanceof Collection && CollectionUtils.isEmpty((Collection) object)) {
            throw new IllegalArgumentException(message);
        }

        if (object instanceof String && StringUtils.isBlank((String) object)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isEqual(final Object source, final Object target, final String message) {
        if(!Objects.equals(source, target)) {
            throw new IllegalArgumentException(message);
        }
    }


    public static void isNotEqual(final Object source, final Object target, final String message) {
        if(Objects.equals(source, target)) {
            throw new IllegalArgumentException(message);
        }
    }


    public static void isOK(final boolean expression, final String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isOK(final boolean expression, final String message, final Object... args) {
        if (!expression) {
            throw new IllegalArgumentException(String.format(message, args));
        }
    }

}

