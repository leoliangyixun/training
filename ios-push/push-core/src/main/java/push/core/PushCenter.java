package push.core;

public class PushCenter<T> {

    private PushExecutor<T> executor;

    public PushCenter<T> executor(PushExecutor<T> executor) {
        this.executor = executor;
        return this;
    }

    public  void execute(T t) {

        //TODO do validate
        //TODO do execute
        executor.execute(t);
    }



}
