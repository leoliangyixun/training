package push3.handler;

import com.hujiang.notifycenter.push.model.bo.Payload;

import org.springframework.stereotype.Component;

/**
 * 推送给所有用户
 */
@Component
public class PushAllHandler implements Handler<Payload> {

    @Override
    public void handle(Payload payload) {

    }
}
