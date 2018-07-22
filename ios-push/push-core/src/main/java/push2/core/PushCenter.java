package push2.core;

import com.training.push.model.dto.Payload;

public class PushCenter {

    private PushExecutor executor;

    public PushCenter executor(PushExecutor executor) {
        this.executor = executor;
        return this;
    }

    public  void execute(Payload t) {

        //TODO do validate
        //TODO do execute
        executor.execute(t);
    }



}
