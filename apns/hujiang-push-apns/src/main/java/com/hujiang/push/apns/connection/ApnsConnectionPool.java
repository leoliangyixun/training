/**
 * 
 */
package com.hujiang.push.apns.connection;

import lombok.NoArgsConstructor;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * @author yangkai
 *
 */
@NoArgsConstructor
public class ApnsConnectionPool {
    private ApnsConnectionFactory factory;
    private ApnsConnectionConfig config;

    private GenericObjectPool<ApnsConnection> pool;

    public ApnsConnectionPool(ApnsConnectionFactory factory, ApnsConnectionConfig config) {
        this.factory = factory;
        this.config = config;

        GenericObjectPoolConfig _config = new GenericObjectPoolConfig();
        _config.setMaxIdle(config.getMaxIdleConnections());
        _config.setMinIdle(config.getMinIdleConnections());
        _config.setMaxTotal(config.getMaxTotalConnections());
        _config.setMaxWaitMillis(-1);

        pool = new GenericObjectPool<>(factory, _config);
    }
}
