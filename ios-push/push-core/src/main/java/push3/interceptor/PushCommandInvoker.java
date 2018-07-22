package push3.interceptor;

import com.hujiang.notifycenter.push.core.executor.Command;
import com.hujiang.notifycenter.push.model.bo.Payload;

import org.springframework.stereotype.Component;

/**
 * @author yangkai
 * @date 2018/4/18
 * @email yangkai@hujiang.com
 * @description
 */
@Component
public class PushCommandInvoker extends CommandInvoker<Payload> {

    @Override
    public void execute(Payload payload, Command<Payload> command) {
        super.execute(payload, command);
        System.out.println("PushCommandInvoker before");
        command.execute(payload);
        System.out.println("PushCommandInvoker after");
    }

}
