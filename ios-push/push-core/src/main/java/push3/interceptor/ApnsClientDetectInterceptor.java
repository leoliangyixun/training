package push3.interceptor;

import com.hujiang.notifycenter.push.core.executor.Command;
import com.hujiang.notifycenter.push.model.bo.Payload;

import org.springframework.stereotype.Component;

/**
 * @author yangkai
 * @date 2018/4/16
 * @email yangkai@hujiang.com
 * @version
 * @description
 */
@Component
public class ApnsClientDetectInterceptor extends CommandInvoker<Payload> {

    @Override
    public void execute(Payload payload, Command<Payload> command) {
        //TODO
        System.out.println("ApnsClientDetectInterceptor before");
        super.execute(payload, command);
        System.out.println("ApnsClientDetectInterceptor after");
    }

}
