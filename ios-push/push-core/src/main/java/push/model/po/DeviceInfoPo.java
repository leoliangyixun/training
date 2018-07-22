package push.model.po;

import lombok.Data;

import java.util.Map;

@Data
public class DeviceInfoPo extends BasePo implements ShardingPo {

    private Long Id;
    /**
     * 沪江下所有应用设备唯一值
     */
    private String deviceId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 应用名称
     */
    private String appName;
    /**
     * 推送使用的token
     */
    private String pushToken;
    /**
     * 推送服务商
     */
    private String pushType;
    /**
     * 设备型号
     */
    private String deviceModel;
    /**
     * 设备类型, Android, iPhone, iPad
     */
    private String deviceType;
    /**
     * 设备名称, iphone X, xiaomi 5
     */
    private String deviceName;
    /**
     * 系统平台, iOS, Android
     */
    private String osPlatform;
    /**
     * 系统版本, iOS 11.2.6
     */
    private String osVersion;
    /**
     * 应用版本
     */
    private String appVersion;
    /**
     * 应用渠道
     */
    private String channel;
    /**
     * 系统语言
     */
    private String lang;
    /**
     * 通知开关状态
     */
    private Integer notifySwitch;
    /**
     * 额外的信息
     */
    private Map<String, Object> ext;
    /**
     * 记录状态
     */
    private Integer status;

    @Override
    public String getSharding() {
        return null;
    }
}
