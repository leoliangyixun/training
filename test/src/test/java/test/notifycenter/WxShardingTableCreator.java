package test.notifycenter;

import org.junit.Test;

/**
 * @author yangkai
 * @date 2021/12/7
 * @email yangkai@hujiang.com
 * @description
 */
public class WxShardingTableCreator {

    @Test
    public void AppletTemplateMessageSendHistory() {
        String[][] shardings = new String[][]{
            {
                "AppletTemplateMessageSendHistory_202301",
                "AppletTemplateMessageSendHistory_202302",
                "AppletTemplateMessageSendHistory_202303",
                "AppletTemplateMessageSendHistory_202304",
                "AppletTemplateMessageSendHistory_202305",
                "AppletTemplateMessageSendHistory_202306",
                "AppletTemplateMessageSendHistory_202307",
                "AppletTemplateMessageSendHistory_202308",
                "AppletTemplateMessageSendHistory_202309",
                "AppletTemplateMessageSendHistory_202310",
                "AppletTemplateMessageSendHistory_202311",
                "AppletTemplateMessageSendHistory_202312"
            }
        };

        String schema = "CREATE TABLE `%s` (\n"
            + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
            + "  `Topic` varchar(50) NOT NULL,\n"
            + "  `TemplateId` varchar(500) NOT NULL,\n"
            + "  `ToUser` varchar(500) NOT NULL,\n"
            + "  `MsgId` bigint(20) DEFAULT NULL,\n"
            + "  `Result` bit(1) DEFAULT NULL,\n"
            + "  `ErrCode` int(11) DEFAULT NULL,\n"
            + "  `ErrMsg` varchar(500) DEFAULT NULL,\n"
            + "  `PushDateTime` datetime DEFAULT NULL,\n"
            + "  `BatchID` varchar(100) DEFAULT NULL,\n"
            + "  `WechatAppID` varchar(50) NOT NULL DEFAULT '',\n"
            + "  `InitiatorID` int(11) DEFAULT NULL,\n"
            + "  `Initiator` varchar(50) NOT NULL DEFAULT '',\n"
            + "  PRIMARY KEY (`Id`),\n"
            + "  KEY `idx_wid` (`WechatAppID`),\n"
            + "  KEY `idx_bid_pdt` (`BatchID`,`PushDateTime`),\n"
            + "  KEY `idx_pdt` (`PushDateTime`)\n"
            + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;";
        for (int i = 0; i < shardings.length; i++) {
            for (int j = 0; j < shardings[i].length; j++) {
                System.out.println(String.format(schema, shardings[i][j]));
            }
        }
    }

    @Test
    public void CustomMessageSendHistory() {
        String[][] shardings = new String[][]{
            {
                "CustomMessageSendHistory_202301",
                "CustomMessageSendHistory_202302",
                "CustomMessageSendHistory_202303",
                "CustomMessageSendHistory_202304",
                "CustomMessageSendHistory_202305",
                "CustomMessageSendHistory_202306",
                "CustomMessageSendHistory_202307",
                "CustomMessageSendHistory_202308",
                "CustomMessageSendHistory_202309",
                "CustomMessageSendHistory_202310",
                "CustomMessageSendHistory_202311",
                "CustomMessageSendHistory_202312"
            }
        };

        String schema = "CREATE TABLE `%s` (\n"
            + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
            + "  `Topic` varchar(50) NOT NULL,\n"
            + "  `TemplateId` varchar(500) NOT NULL,\n"
            + "  `ToUser` varchar(500) NOT NULL,\n"
            + "  `MsgId` bigint(20) DEFAULT NULL,\n"
            + "  `Result` bit(1) DEFAULT NULL,\n"
            + "  `ErrCode` int(11) DEFAULT NULL,\n"
            + "  `ErrMsg` varchar(500) DEFAULT NULL,\n"
            + "  `PushDateTime` datetime DEFAULT NULL,\n"
            + "  `BatchID` varchar(100) DEFAULT NULL,\n"
            + "  `WechatAppID` varchar(50) NOT NULL DEFAULT '',\n"
            + "  `InitiatorID` int(11) DEFAULT NULL,\n"
            + "  `Initiator` varchar(50) NOT NULL DEFAULT '',\n"
            + "  PRIMARY KEY (`Id`),\n"
            + "  KEY `idx_wid` (`WechatAppID`),\n"
            + "  KEY `idx_bid_pdt` (`BatchID`,`PushDateTime`),\n"
            + "  KEY `idx_pdt` (`PushDateTime`)\n"
            + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;";
        for (int i = 0; i < shardings.length; i++) {
            for (int j = 0; j < shardings[i].length; j++) {
                System.out.println(String.format(schema, shardings[i][j]));
            }
        }
    }

    @Test
    public void GroupMessageSendHistory() {
        String[][] shardings = new String[][]{
            {
                "GroupMessageSendHistory_202301",
                "GroupMessageSendHistory_202302",
                "GroupMessageSendHistory_202303",
                "GroupMessageSendHistory_202304",
                "GroupMessageSendHistory_202305",
                "GroupMessageSendHistory_202306",
                "GroupMessageSendHistory_202307",
                "GroupMessageSendHistory_202308",
                "GroupMessageSendHistory_202309",
                "GroupMessageSendHistory_202310",
                "GroupMessageSendHistory_202311",
                "GroupMessageSendHistory_202312"
            }
        };
        String schema = "CREATE TABLE `%s` (\n"
            + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
            + "  `Topic` varchar(50) NOT NULL,\n"
            + "  `ToUser` varchar(500) DEFAULT NULL,\n"
            + "  `TagId` int(11) DEFAULT NULL,\n"
            + "  `MsgId` bigint(20) DEFAULT NULL,\n"
            + "  `MsgDataId` bigint(20) DEFAULT NULL,\n"
            + "  `MsgType` varchar(50) DEFAULT NULL,\n"
            + "  `SendType` varchar(50) DEFAULT NULL,\n"
            + "  `Result` bit(1) DEFAULT NULL,\n"
            + "  `ErrCode` int(11) DEFAULT NULL,\n"
            + "  `ErrMsg` varchar(500) DEFAULT NULL,\n"
            + "  `PushDateTime` datetime DEFAULT NULL,\n"
            + "  `BatchID` varchar(100) DEFAULT NULL,\n"
            + "  `WechatAppID` varchar(50) NOT NULL DEFAULT '',\n"
            + "  `InitiatorID` int(11) DEFAULT NULL,\n"
            + "  `Initiator` varchar(50) NOT NULL DEFAULT '',\n"
            + "  PRIMARY KEY (`Id`),\n"
            + "  KEY `idx_wid` (`WechatAppID`),\n"
            + "  KEY `idx_bid_pdt` (`BatchID`,`PushDateTime`),\n"
            + "  KEY `idx_pdt` (`PushDateTime`)\n"
            + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;";
        for (int i = 0; i < shardings.length; i++) {
            for (int j = 0; j < shardings[i].length; j++) {
                System.out.println(String.format(schema, shardings[i][j]));
            }
        }
    }

    @Test
    public void SendHistory() {
        String[][] shardings = new String[][]{
            {
                "SendHistory_202301",
                "SendHistory_202302",
                "SendHistory_202303",
                "SendHistory_202304",
                "SendHistory_202305",
                "SendHistory_202306",
                "SendHistory_202307",
                "SendHistory_202308",
                "SendHistory_202309",
                "SendHistory_202310",
                "SendHistory_202311",
                "SendHistory_202312"
            }
        };

        String schema = "CREATE TABLE `%s` (\n"
            + "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n"
            + "  `Topic` varchar(50) NOT NULL,\n"
            + "  `TemplateId` varchar(500) NOT NULL,\n"
            + "  `ToUser` varchar(500) NOT NULL,\n"
            + "  `MsgId` bigint(20) DEFAULT NULL,\n"
            + "  `Result` bit(1) DEFAULT NULL,\n"
            + "  `ErrCode` int(11) DEFAULT NULL,\n"
            + "  `ErrMsg` varchar(500) DEFAULT NULL,\n"
            + "  `PushDateTime` datetime DEFAULT NULL,\n"
            + "  `BatchID` varchar(100) DEFAULT NULL,\n"
            + "  `WechatAppID` varchar(50) NOT NULL DEFAULT '',\n"
            + "  `InitiatorID` int(11) DEFAULT NULL,\n"
            + "  `Initiator` varchar(50) NOT NULL DEFAULT '',\n"
            + "  `OpenId` varchar(64) DEFAULT NULL,\n"
            + "  `Data` varchar(3000) DEFAULT NULL,\n"
            + "  PRIMARY KEY (`Id`),\n"
            + "  KEY `idx_wid` (`WechatAppID`),\n"
            + "  KEY `idx_bid_pdt` (`BatchID`,`PushDateTime`),\n"
            + "  KEY `idx_pdt` (`PushDateTime`)\n"
            + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;";
        for (int i = 0; i < shardings.length; i++) {
            for (int j = 0; j < shardings[i].length; j++) {
                System.out.println(String.format(schema, shardings[i][j]));
            }
        }
    }

}
