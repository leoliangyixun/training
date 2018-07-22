package push3.executor;

import com.hujiang.notifycenter.push.core.interceptor.CommandInterceptor;

/**
 * @author yangkai
 * @date 2018/4/20
 * @email yangkai@hujiang.com
 * @description
 */
public abstract class AbstractCommandExecutor<T> implements CommandExecutor<T> {

    protected CommandInterceptor<T> interceptor;

    public CommandExecutor<T> setInterceptor(CommandInterceptor<T> interceptor) {
        this.interceptor = interceptor;
        return this;
    }

}
