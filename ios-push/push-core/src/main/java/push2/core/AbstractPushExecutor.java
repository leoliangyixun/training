package push2.core;

/**
 * @author: yangkai
 * @date: 2018/4/15
 * @email: yangkai@hujiang.com
 * @version:
 * @description
 */
public abstract class AbstractPushExecutor implements PushExecutor {
    protected PushExecutor next;

    @Override
    public PushExecutor next(PushExecutor next) {
        this.next = next;
        return this;
    }
}
