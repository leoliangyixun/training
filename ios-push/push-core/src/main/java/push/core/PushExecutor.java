package push.core;

/**
 * @author: yangkai
 * @date: 2018/4/15
 * @email: yangkai@hujiang.com
 * @version:
 * @description
 */
public interface PushExecutor<T> {
     void execute(T t);
    PushExecutor<T> next(PushExecutor<T> next);
}
