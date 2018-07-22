/**
 * 
 */
package push2.apns.connection;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;

/**
 * @author yangkai
 *
 */
public class ApnsConnectionFactory  extends BasePooledObjectFactory<ApnsConnection> {

    @Override
    public ApnsConnection create() throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PooledObject<ApnsConnection> wrap(ApnsConnection obj) {
        // TODO Auto-generated method stub
        return null;
    }

}
