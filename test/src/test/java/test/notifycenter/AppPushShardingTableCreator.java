package test.notifycenter;

import org.junit.Test;

/**
 * @author yangkai
 * @date 2021/12/7
 * @email yangkai@hujiang.com
 * @description
 */
public class AppPushShardingTableCreator {

    @Test
    public void sendhistory() {
        String[][] shardings = new String[][]{
            {
                "sendhistory_202301",
                "sendhistory_202302",
                "sendhistory_202303",
                "sendhistory_202304",
                "sendhistory_202305",
                "sendhistory_202306",
                "sendhistory_202307",
                "sendhistory_202308",
                "sendhistory_202309",
                "sendhistory_202310",
                "sendhistory_202311",
                "sendhistory_202312"
            }
        };
        String schema = "CREATE TABLE `%s` (\n"
            + "  `ID` int(11) NOT NULL AUTO_INCREMENT,\n"
            + "  `BatchID` varchar(50) DEFAULT NULL,\n"
            + "  `SerialNumber` varchar(50) DEFAULT NULL,\n"
            + "  `Topic` varchar(20) DEFAULT NULL,\n"
            + "  `Platform` varchar(20) DEFAULT NULL,\n"
            + "  `Payload` varchar(200) DEFAULT NULL,\n"
            + "  `Audience` longtext,\n"
            + "  `PostTime` datetime NOT NULL,\n"
            + "  `State` varchar(4096) DEFAULT NULL,\n"
            + "  `AppName` varchar(50) DEFAULT NULL,\n"
            + "  PRIMARY KEY (`ID`),\n"
            + "  KEY `idx_bid` (`BatchID`),\n"
            + "  KEY `idx_pt` (`PostTime`)\n"
            + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;";
        for (int i = 0; i < shardings.length; i++) {
            for (int j = 0; j < shardings[i].length; j++) {
                System.out.println(String.format(schema, shardings[i][j]));
            }
        }
    }

    @Test
    public void sendresult() {
        String[][] shardings = new String[][]{
            {
                "sendresult_202301",
                "sendresult_202302",
                "sendresult_202303",
                "sendresult_202304",
                "sendresult_202305",
                "sendresult_202306",
                "sendresult_202307",
                "sendresult_202308",
                "sendresult_202309",
                "sendresult_202310",
                "sendresult_202311",
                "sendresult_202312"
            }
        };

        String schema = "CREATE TABLE `%s` (\n"
            + "  `ID` int(11) NOT NULL AUTO_INCREMENT,\n"
            + "  `BatchID` varchar(100) DEFAULT NULL,\n"
            + "  `SerialNumber` varchar(100) DEFAULT NULL,\n"
            + "  `SendChannel` varchar(100) DEFAULT NULL,\n"
            + "  `SendTime` datetime DEFAULT NULL,\n"
            + "  `IsSuccess` bit(1) DEFAULT NULL,\n"
            + "  `ReturnID` varchar(50) DEFAULT NULL,\n"
            + "  `ReturnMessage` varchar(200) DEFAULT NULL,\n"
            + "  `AppName` varchar(50) DEFAULT NULL,\n"
            + "  `SendHistoryID` int(11) DEFAULT NULL,\n"
            + "  `AudienceSum` int(11) DEFAULT NULL,\n"
            + "  `Platform` varchar(20) DEFAULT NULL,\n"
            + "  PRIMARY KEY (`ID`),\n"
            + "  KEY `idx_bid` (`BatchID`),\n"
            + "  KEY `idx_st` (`SendTime`)\n"
            + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;";
        for (int i = 0; i < shardings.length; i++) {
            for (int j = 0; j < shardings[i].length; j++) {
                System.out.println(String.format(schema, shardings[i][j]));
            }
        }
    }

}
