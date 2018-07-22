/**
 * 
 */
package push.model.po;

import com.hujiang.basic.framework.core.util.JsonUtil;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author yangkai
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PushTaskAnalysisPo extends BasePo {
    private String batchId;
    private Long msgId;
    private String appName;
    private Integer status;
    private Integer analysisStatus;

    @Override
    public String toString() {
        return JsonUtil.object2JSON(this);
    }

}
