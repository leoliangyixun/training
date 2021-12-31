package test.notifycenter;

import org.junit.Test;

/**
 * @author yangkai
 * @date 2021/12/7
 * @email yangkai@hujiang.com
 * @description
 */
public class SmsShardingTableCreator {

    @Test
    public void status_report() {
        String[][] shardings = new String[][]{
            {
                "status_report_jdcloud_202201",
                "status_report_jdcloud_202202",
                "status_report_jdcloud_202203",
                "status_report_jdcloud_202204",
                "status_report_jdcloud_202205",
                "status_report_jdcloud_202206",
                "status_report_jdcloud_202207",
                "status_report_jdcloud_202208",
                "status_report_jdcloud_202209",
                "status_report_jdcloud_202210",
                "status_report_jdcloud_202211",
                "status_report_jdcloud_202212"
            },
            {

                "status_report_ksyun_202201",
                "status_report_ksyun_202202",
                "status_report_ksyun_202203",
                "status_report_ksyun_202204",
                "status_report_ksyun_202205",
                "status_report_ksyun_202206",
                "status_report_ksyun_202207",
                "status_report_ksyun_202208",
                "status_report_ksyun_202209",
                "status_report_ksyun_202210",
                "status_report_ksyun_202211",
                "status_report_ksyun_202212",
            },
            {

                "status_report_wxxsxx_202201",
                "status_report_wxxsxx_202202",
                "status_report_wxxsxx_202203",
                "status_report_wxxsxx_202204",
                "status_report_wxxsxx_202205",
                "status_report_wxxsxx_202206",
                "status_report_wxxsxx_202207",
                "status_report_wxxsxx_202208",
                "status_report_wxxsxx_202209",
                "status_report_wxxsxx_202210",
                "status_report_wxxsxx_202211",
                "status_report_wxxsxx_202212",
            },
            {

                "status_report_zhutong_202201",
                "status_report_zhutong_202202",
                "status_report_zhutong_202203",
                "status_report_zhutong_202204",
                "status_report_zhutong_202205",
                "status_report_zhutong_202206",
                "status_report_zhutong_202207",
                "status_report_zhutong_202208",
                "status_report_zhutong_202209",
                "status_report_zhutong_202210",
                "status_report_zhutong_202211",
                "status_report_zhutong_202212",
            },
            {

                "status_report_aliyun_202001",
                "status_report_aliyun_202002",
                "status_report_aliyun_202003",
                "status_report_aliyun_202004",
                "status_report_aliyun_202005",
                "status_report_aliyun_202006",
                "status_report_aliyun_202007",
                "status_report_aliyun_202008",
                "status_report_aliyun_202009",
                "status_report_aliyun_202010",
                "status_report_aliyun_202011",
                "status_report_aliyun_202012",
            },
            {
                "status_report_ronglian_202201",
                "status_report_ronglian_202202",
                "status_report_ronglian_202203",
                "status_report_ronglian_202204",
                "status_report_ronglian_202205",
                "status_report_ronglian_202206",
                "status_report_ronglian_202207",
                "status_report_ronglian_202208",
                "status_report_ronglian_202209",
                "status_report_ronglian_202210",
                "status_report_ronglian_202211",
                "status_report_ronglian_202212",
            },
            {
                "status_report_jdcloud_202301",
                "status_report_jdcloud_202302",
                "status_report_jdcloud_202303",
                "status_report_jdcloud_202304",
                "status_report_jdcloud_202305",
                "status_report_jdcloud_202306",
                "status_report_jdcloud_202307",
                "status_report_jdcloud_202308",
                "status_report_jdcloud_202309",
                "status_report_jdcloud_202310",
                "status_report_jdcloud_202311",
                "status_report_jdcloud_202312"
            },
            {

                "status_report_ksyun_202301",
                "status_report_ksyun_202302",
                "status_report_ksyun_202303",
                "status_report_ksyun_202304",
                "status_report_ksyun_202305",
                "status_report_ksyun_202306",
                "status_report_ksyun_202307",
                "status_report_ksyun_202308",
                "status_report_ksyun_202309",
                "status_report_ksyun_202310",
                "status_report_ksyun_202311",
                "status_report_ksyun_202312",
            },
            {

                "status_report_wxxsxx_202301",
                "status_report_wxxsxx_202302",
                "status_report_wxxsxx_202303",
                "status_report_wxxsxx_202304",
                "status_report_wxxsxx_202305",
                "status_report_wxxsxx_202306",
                "status_report_wxxsxx_202307",
                "status_report_wxxsxx_202308",
                "status_report_wxxsxx_202309",
                "status_report_wxxsxx_202310",
                "status_report_wxxsxx_202311",
                "status_report_wxxsxx_202312",
            },
            {

                "status_report_zhutong_202301",
                "status_report_zhutong_202302",
                "status_report_zhutong_202303",
                "status_report_zhutong_202304",
                "status_report_zhutong_202305",
                "status_report_zhutong_202306",
                "status_report_zhutong_202307",
                "status_report_zhutong_202308",
                "status_report_zhutong_202309",
                "status_report_zhutong_202310",
                "status_report_zhutong_202311",
                "status_report_zhutong_202312",
            },
            {

                "status_report_aliyun_202301",
                "status_report_aliyun_202302",
                "status_report_aliyun_202303",
                "status_report_aliyun_202304",
                "status_report_aliyun_202305",
                "status_report_aliyun_202306",
                "status_report_aliyun_202307",
                "status_report_aliyun_202308",
                "status_report_aliyun_202309",
                "status_report_aliyun_202310",
                "status_report_aliyun_202311",
                "status_report_aliyun_202312",
            },
            {
                "status_report_ronglian_202301",
                "status_report_ronglian_202302",
                "status_report_ronglian_202303",
                "status_report_ronglian_202304",
                "status_report_ronglian_202305",
                "status_report_ronglian_202306",
                "status_report_ronglian_202307",
                "status_report_ronglian_202308",
                "status_report_ronglian_202309",
                "status_report_ronglian_202310",
                "status_report_ronglian_202311",
                "status_report_ronglian_202312",
            }
        };
        String schema = "CREATE TABLE `%s` (\n"
            + "  `id` int(11) NOT NULL AUTO_INCREMENT,\n"
            + "  `tel` varchar(20) DEFAULT '' COMMENT '手机号码',\n"
            + "  `msg_id` varchar(50) DEFAULT '',\n"
            + "  `status` int(11) DEFAULT '0' COMMENT '回执状态, 0失败, 1成功',\n"
            + "  `status_detail` varchar(50) DEFAULT '' COMMENT '回执的具体信息',\n"
            + "  `channel_name` varchar(50) DEFAULT '' COMMENT '通道id',\n"
            + "  `report_datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '触达用户的时间',\n"
            + "  `update_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间，字段变化时自动更新时间',\n"
            + "  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，记录插入时自动插入时间',\n"
            + "  PRIMARY KEY (`id`),\n"
            + "  KEY `index_create_time` (`created_at`),\n"
            + "  KEY `index_msg_id_tel` (`msg_id`,`tel`)\n"
            + ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;";
        for (int i = 0; i < shardings.length; i++) {
            for (int j = 0; j < shardings[i].length; j++) {
                System.out.println(String.format(schema, shardings[i][j]));
            }
        }
    }

}
