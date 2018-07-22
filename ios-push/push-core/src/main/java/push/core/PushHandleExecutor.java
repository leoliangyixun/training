package push.core;


import java.util.EnumMap;

/**
 * @author: yangkai
 * @date: 2018/4/15
 * @email: yangkai@hujiang.com
 * @version:
 * @description
 */
public class PushHandleExecutor extends AbstractPushExecutor<Payload> {

    private EnumMap<SendType, PushHandler<Payload>> handlers = new EnumMap<>(SendType.class);


    @Override
    public void execute(Payload payload) {
        //TODO

        PushHandler<Payload> handler = handlers.get(SendType.convert(payload.getSendType()));

        handler.execute(payload);

        if (this.next != null) {
            this.next.execute(payload);
        }
    }

    public PushHandleExecutor regist(SendType sendType, PushHandler<Payload> handler) {
            this.handlers.put(sendType, handler);
            return this;
    }
}
