package push.handler;

public interface PushHandler<T> {
    void execute(T t);
}
