package push3.interceptor;

/**
 * @author yangkai
 * @date 2018/4/15
 * @email yangkai@hujiang.com
 * @version
 * @description
 */
public abstract class AbstractCommandInterceptor<T> implements CommandInterceptor<T> {
    protected CommandInterceptor<T> next;

    @Override
    public CommandInterceptor<T> next(CommandInterceptor<T> next) {
        this.next = next;
        return this.next;
    }
}
