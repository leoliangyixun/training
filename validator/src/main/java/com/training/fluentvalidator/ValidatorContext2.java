package com.training.fluentvalidator;

import java.util.HashMap;
import java.util.Map;

public class ValidatorContext2<K,V> {
    private Map<K, V> attributes;

    public V getAttribute(K key) {
        if (attributes != null && !attributes.isEmpty()) {
            return attributes.get(key);
        }
        return null;
    }

    public void setAttribute(K key, V value) {
        if (attributes == null) {
            attributes = new HashMap<K,V>();
        }
        attributes.put(key, value);
    }
}
