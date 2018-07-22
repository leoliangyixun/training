/**
 * 
 */
package push.model.po;

import com.hujiang.basic.framework.core.util.JsonUtil;

import lombok.Data;

/**
 * @author yangkai
 */
@Data
public class PushTaskPo extends BasePo implements ShardingPo {

    private Long msgId;
    private String appId;
    private String payload;
    private String audience;
    private String notification;
    private String platform;
    private Integer status;
    private Integer totalCount;
    private String stage;

    @Override
    public String toString() {
        return JsonUtil.object2JSON(this);
    }

}
