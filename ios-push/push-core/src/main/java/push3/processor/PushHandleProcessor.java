package push3.processor;

import com.hujiang.notifycenter.gundam.common.util.Preconditions;
import com.hujiang.notifycenter.push.core.executor.Command;
import com.hujiang.notifycenter.push.core.handler.Handler;
import com.hujiang.notifycenter.push.model.bo.Payload;
import com.hujiang.notifycenter.push.model.common.SendType;
import com.hujiang.notifycenter.push.service.PushService;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.EnumMap;

/**
 * @author yangkai
 * @date 2018/4/15
 * @email yangkai@hujiang.com
 * @version
 * @description
 */
public class PushHandleProcessor implements Processor<Payload>, Command<Payload> {

    @Autowired
    private PushService pushService;

    private EnumMap<SendType, Handler<Payload>> handlers = new EnumMap<>(SendType.class);

    @Override
    public Payload execute(Payload payload) {
        Handler<Payload> handler = handlers.get(SendType.convert(payload.getSendType()));
        Preconditions.notNull(handler,  String
            .format("[logic error]:there should be at least one handler for send type: [%s]", payload.getSendType()));
        handler.handle(payload);
        System.out.println("PushHandleProcessor executed");
        return payload;
    }

    @Override
    public void process(Payload payload) {

    }

    public PushHandleProcessor register(SendType sendType, Handler<Payload> handler) {
            this.handlers.put(sendType, handler);
            return this;
    }
}
