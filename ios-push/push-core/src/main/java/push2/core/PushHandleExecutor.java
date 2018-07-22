package push2.core;


import com.google.common.base.Preconditions;
import com.training.push.handler.PushHandler;
import com.training.push.model.dto.Payload;

import push2.handler.PushHandler;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: yangkai
 * @date: 2018/4/15
 * @email: yangkai@hujiang.com
 * @version:
 * @description
 */
public class PushHandleExecutor extends AbstractPushExecutor {

    private Map<String, PushHandler<Payload>> handlers = new HashMap<>();


    @Override
    public void execute(Payload payload) {
        //TODO

        PushHandler<Payload> handler = handlers.get(payload.getSendType());


        handler.execute(payload);

        if (this.next != null) {
            this.next.execute(payload);
        }
    }

    public PushHandleExecutor regist(String sendType, PushHandler<Payload> handler) {
            this.handlers.put(sendType, handler);
            return this;
    }

}
