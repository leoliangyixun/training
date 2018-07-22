package push3.executor;

public interface Command<T> {
    T execute(T target);
}
