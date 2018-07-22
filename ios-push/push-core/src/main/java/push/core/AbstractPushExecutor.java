package push.core;

/**
 * @author: yangkai
 * @date: 2018/4/15
 * @email: yangkai@hujiang.com
 * @version:
 * @description
 */
public abstract class AbstractPushExecutor<T> implements PushExecutor<T> {
    protected PushExecutor<T> next;

    @Override
    public PushExecutor<T> next(PushExecutor<T> next) {
        this.next = next;
        return this;
    }
}
