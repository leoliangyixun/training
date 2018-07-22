package push.model.po;

import lombok.Data;

@Data
public class PushByTagPo extends BasePo implements ShardingPo {

    private Long id;
    private String deviceId;
    private Long userId;
    private String pushToken;
    private String pushType;
    private String tag;
    private String tagAttr1;
    private String tagAttr2;
    private Integer status;

    @Override
    public String getSharding() {
        return null;
    }
}
