package push.resolver;

import push.model.dto.Payload;

/**
 * @author: yangkai
 * @date: 2018/4/15
 * @email: yangkai@hujiang.com
 * @version:
 * @description
 */
public class PushSingleDeviceResolver extends AbstractPushResolver<Payload>{

    @Override
    public void resolve() {
        System.out.println(this.t.getAppId());
    }
}
