package push.handler;

import com.hujiang.notifycenter.push.model.dto.Payload;

import org.springframework.stereotype.Component;

@Component
public class PushByUserHandler implements PushHandler<Payload> {

    @Override
    public void execute(Payload payload) {
        //TODO

    }


}
