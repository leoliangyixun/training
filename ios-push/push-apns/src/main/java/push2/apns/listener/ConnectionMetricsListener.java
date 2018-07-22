/**
 * 
 */
package push2.apns.listener;

/**
 * @author yangkai
 *
 */
public interface ConnectionMetricsListener {
    /**
     * Indicates that a new connection was added to the connection pool.
     */
    void onCreate();

    /**
     * Indicates that a connection was permanently removed from the connection pool.
     */
    void onRemove();

    /**
     * Indicates that an attempt to add a new connection to the connection pool failed.
     */
    void onFail();
    
    /**
     * Indicates that a connection is closed.
     */
    void onClose();

}
