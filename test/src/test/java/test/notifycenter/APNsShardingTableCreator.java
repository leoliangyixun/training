package test.notifycenter;

import org.junit.Test;

/**
 * @author yangkai
 * @date 2021/12/7
 * @email yangkai@hujiang.com
 * @description
 */
public class APNsShardingTableCreator {

    @Test
    public void push_task() {
        String[][] shardings = new String[][]{
            {
                "push_task_ccschool_202301",
                "push_task_ccschool_202302",
                "push_task_ccschool_202303",
                "push_task_ccschool_202304",
                "push_task_ccschool_202305",
                "push_task_ccschool_202306",
                "push_task_ccschool_202307",
                "push_task_ccschool_202308",
                "push_task_ccschool_202309",
                "push_task_ccschool_202310",
                "push_task_ccschool_202311",
                "push_task_ccschool_202312"
            },
            {
                "push_task_class_202301",
                "push_task_class_202302",
                "push_task_class_202303",
                "push_task_class_202304",
                "push_task_class_202305",
                "push_task_class_202306",
                "push_task_class_202307",
                "push_task_class_202308",
                "push_task_class_202309",
                "push_task_class_202310",
                "push_task_class_202311",
                "push_task_class_202312"
            },
            {
                "push_task_hjcichang_202301",
                "push_task_hjcichang_202302",
                "push_task_hjcichang_202303",
                "push_task_hjcichang_202304",
                "push_task_hjcichang_202305",
                "push_task_hjcichang_202306",
                "push_task_hjcichang_202307",
                "push_task_hjcichang_202308",
                "push_task_hjcichang_202309",
                "push_task_hjcichang_202310",
                "push_task_hjcichang_202311",
                "push_task_hjcichang_202312"
            },
            {
                "push_task_hjdict_202301",
                "push_task_hjdict_202302",
                "push_task_hjdict_202303",
                "push_task_hjdict_202304",
                "push_task_hjdict_202305",
                "push_task_hjdict_202306",
                "push_task_hjdict_202307",
                "push_task_hjdict_202308",
                "push_task_hjdict_202309",
                "push_task_hjdict_202310",
                "push_task_hjdict_202311",
                "push_task_hjdict_202312"
            },
            {
                "push_task_hjwushiyin_202301",
                "push_task_hjwushiyin_202302",
                "push_task_hjwushiyin_202303",
                "push_task_hjwushiyin_202304",
                "push_task_hjwushiyin_202305",
                "push_task_hjwushiyin_202306",
                "push_task_hjwushiyin_202307",
                "push_task_hjwushiyin_202308",
                "push_task_hjwushiyin_202309",
                "push_task_hjwushiyin_202310",
                "push_task_hjwushiyin_202311",
                "push_task_hjwushiyin_202312"
            }
        };

        String schema = "CREATE TABLE `%s` (\n"
            + "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,\n"
            + "  `msg_id` bigint(20) NOT NULL COMMENT '消息ID',\n"
            + "  `app_id` varchar(32) NOT NULL COMMENT '应用ID',\n"
            + "  `audience_type` varchar(32) DEFAULT NULL COMMENT '推送类型(all, tag, alisa, user, device, token)',\n"
            + "  `payload` json NOT NULL COMMENT '请求体',\n"
            + "  `audience` json NOT NULL COMMENT '接收者',\n"
            + "  `notification` json NOT NULL COMMENT '通知体',\n"
            + "  `platform` varchar(200) NOT NULL COMMENT '平台',\n"
            + "  `options` json DEFAULT NULL COMMENT '可选参数',\n"
            + "  `status` int(11) NOT NULL COMMENT '消息状态(1创建，2处理中，3失败，4结束且部分成功，5成功)',\n"
            + "  `total_count` int(11) NOT NULL COMMENT '消息总量',\n"
            + "  `stage` varchar(1000) DEFAULT NULL COMMENT '消息状态详情',\n"
            + "  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间，字段变化时自动更新时间',\n"
            + "  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，记录插入时自动插入时间',\n"
            + "  PRIMARY KEY (`id`),\n"
            + "  KEY `idx_msg_id` (`msg_id`),\n"
            + "  KEY `idx_app_id` (`app_id`)\n"
            + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;";
        for (int i = 0; i < shardings.length; i++) {
            for (int j = 0; j < shardings[i].length; j++) {
                System.out.println(String.format(schema, shardings[i][j]));
            }
        }
    }

}
