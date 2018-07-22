/**
 *
 */
package push.model.po;

import com.hujiang.basic.framework.core.util.JsonUtil;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@Data
public abstract class BasePo implements Serializable {

    private Long id;
    protected Date gmtCreate;
    protected Date gmtUpdate;

    @Override
    public String toString() {
        return JsonUtil.object2JSON(this);
    }
}
