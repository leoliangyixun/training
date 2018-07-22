package push3.executor;

import com.hujiang.notifycenter.push.model.bo.Payload;

import org.springframework.stereotype.Component;

/**
 * @author yangkai
 * @date 2018/4/19
 * @email yangkai@hujiang.com
 * @description
 */
@Component
public class PushCommandExecutor extends AbstractCommandExecutor<Payload> {

    @Override
    public void execute(Payload payload, Command<Payload> command) {
        interceptor.execute(payload, command);
    }

}
