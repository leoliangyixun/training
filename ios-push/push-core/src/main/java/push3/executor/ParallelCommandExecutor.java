package push3.executor;

import com.hujiang.notifycenter.push.core.processor.Processor;

import push3.processor.Processor;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author yangkai
 * @date 2018/4/21
 * @email yangkai@hujiang.com
 * @description
 */
public class ParallelCommandExecutor<T> implements Command<T> {
    private List<Processor<T>> processors;
    private ExecutorService executorService;

    public ParallelCommandExecutor(List<Processor<T>> processors, ExecutorService executorService) {
        this.processors = processors;
        this.executorService = executorService;
    }

    @Override
    public T execute(T target) {
        return null;
    }
}