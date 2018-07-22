package push3.handler;

import com.hujiang.notifycenter.push.model.bo.Payload;

import org.springframework.stereotype.Component;

@Component
public class PushByTagHandler implements Handler<Payload> {

    @Override
    public void handle(Payload payload) {
    }
}
