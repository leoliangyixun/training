/**
 * 
 */
package push.model.po;

import com.hujiang.basic.framework.core.util.JsonUtil;

/**
 * @author yangkai
 *
 */
public class PushBreakTaskPo extends BasePo implements ShardingPo {

    private String batchId;
    private Integer taskId;
    private String appId;
    private String payload;
    private String audience;
    private String notification;
    private String platform;
    private Integer status;

    @Override
    public String toString() {
        return JsonUtil.object2JSON(this);
    }

}
