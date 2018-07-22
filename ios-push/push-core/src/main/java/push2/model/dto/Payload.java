package push2.model.dto;

import com.hujiang.basic.framework.core.util.JsonUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payload implements Serializable {

    private String sendType;
    private String sendStrategy;
    private String appId;
    private Object platform;
    private Object audience;
    private Notification notification;
    private Options options;

    @Override
    public String toString() {
        return JsonUtil.object2JSON(this);
    }

}
