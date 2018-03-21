package com.hujiang.push.apns.connection;

import java.io.Closeable;
import java.io.IOException;

/**
 * APNs connection pool management
 */
public interface Connection extends Closeable {
    void connect() throws IOException;
}
