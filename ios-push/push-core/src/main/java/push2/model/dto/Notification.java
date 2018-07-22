package push2.model.dto;

import com.hujiang.basic.framework.core.util.JsonUtil;

import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class Notification implements Serializable {

    private Object alert;
    private String sound;
    private String badge;
    private boolean contentAvailable;
    private String category;
    private boolean mutableContent;
    private Map<String, Object> extras;

    @Override
    public String toString() {
        return JsonUtil.object2JSON(this);
    }
}
