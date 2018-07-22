package push2.core;

import com.training.push.model.dto.Payload;

/**
 * @author: yangkai
 * @date: 2018/4/15
 * @email: yangkai@hujiang.com
 * @version:
 * @description
 */
public interface PushExecutor {
    <T extends Payload>void execute(T t);
    PushExecutor next(PushExecutor next);
}
