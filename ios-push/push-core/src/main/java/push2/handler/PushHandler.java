package push2.handler;

import com.training.push.model.dto.Payload;

public interface PushHandler {
    <T extends Payload> void execute(T t);
}
