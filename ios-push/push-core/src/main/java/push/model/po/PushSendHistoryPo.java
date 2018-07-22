/**
 * 
 */
package push.model.po;

import com.hujiang.basic.framework.core.util.JsonUtil;

/**
 * @author yangkai
 *
 */
public class PushSendHistoryPo extends BasePo implements ShardingPo {

    private String batchId;
    private Integer errCode;
    private String errMsg;

    @Override
    public String toString() {
        return JsonUtil.object2JSON(this);
    }

}
