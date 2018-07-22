package push3.executor;

import com.hujiang.notifycenter.push.core.processor.Processor;

import java.util.List;

/**
 * @author yangkai
 * @date 2018/4/21
 * @email yangkai@hujiang.com
 * @description
 */
public class SerialCommandExecutor<T> implements Command<T> {
    List<Processor<T>> processors;

    @Override
    public T execute(T target) {
        return null;
    }
}
