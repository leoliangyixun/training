package push.resolver;

import push.model.dto.Payload;

/**
 * @author: yangkai
 * @date: 2018/4/15
 * @email: yangkai@hujiang.com
 * @version:
 * @description
 */
public abstract class AbstractPushResolver<T> implements PushResolver<T> {

    protected T t;
    

    AbstractPushResolver<T> set(T t) {
        this.t = t;
        return this;
    }
}
