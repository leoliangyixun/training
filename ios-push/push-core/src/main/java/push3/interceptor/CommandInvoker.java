package push3.interceptor;

import com.hujiang.notifycenter.push.core.executor.Command;

/**
 * @author yangkai
 * @date 2018/4/19
 * @email yangkai@hujiang.com
 * @description
 */
public class CommandInvoker<T> extends AbstractCommandInterceptor<T> {

    @Override
    public void execute(T target, Command<T> command) {
        if (this.next != null) {
            this.next.execute(target, command);
        }
    }

}
