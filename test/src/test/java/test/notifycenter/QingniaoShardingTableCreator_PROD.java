package test.notifycenter;

import org.junit.Test;

/**
 * @author yangkai
 * @date 2021/12/7
 * @email yangkai@hujiang.com
 * @description
 */
public class QingniaoShardingTableCreator_PROD {

    @Test
    public void async_third_status() {
        String[][] shardings = new String[][]{
            {
                "async_third_status_W20221",
                "async_third_status_W202210",
                "async_third_status_W202211",
                "async_third_status_W202212",
                "async_third_status_W202213",
                "async_third_status_W202214",
                "async_third_status_W202215",
                "async_third_status_W202216",
                "async_third_status_W202217",
                "async_third_status_W202218",
                "async_third_status_W202219",
                "async_third_status_W20222",
                "async_third_status_W202220",
                "async_third_status_W202221",
                "async_third_status_W202222",
                "async_third_status_W202223",
                "async_third_status_W202224",
                "async_third_status_W202225",
                "async_third_status_W202226",
                "async_third_status_W202227",
                "async_third_status_W202228",
                "async_third_status_W202229",
                "async_third_status_W20223",
                "async_third_status_W202230",
                "async_third_status_W202231",
                "async_third_status_W202232",
                "async_third_status_W202233",
                "async_third_status_W202234",
                "async_third_status_W202235",
                "async_third_status_W202236",
                "async_third_status_W202237",
                "async_third_status_W202238",
                "async_third_status_W202239",
                "async_third_status_W20224",
                "async_third_status_W202240",
                "async_third_status_W202241",
                "async_third_status_W202242",
                "async_third_status_W202243",
                "async_third_status_W202244",
                "async_third_status_W202245",
                "async_third_status_W202246",
                "async_third_status_W202247",
                "async_third_status_W202248",
                "async_third_status_W202249",
                "async_third_status_W20225",
                "async_third_status_W202250",
                "async_third_status_W202251",
                "async_third_status_W202252",
                "async_third_status_W202253",
                "async_third_status_W20226",
                "async_third_status_W20227",
                "async_third_status_W20228",
                "async_third_status_W20229"
            },
            {
                "async_third_status_W20231",
                "async_third_status_W202310",
                "async_third_status_W202311",
                "async_third_status_W202312",
                "async_third_status_W202313",
                "async_third_status_W202314",
                "async_third_status_W202315",
                "async_third_status_W202316",
                "async_third_status_W202317",
                "async_third_status_W202318",
                "async_third_status_W202319",
                "async_third_status_W20232",
                "async_third_status_W202320",
                "async_third_status_W202321",
                "async_third_status_W202322",
                "async_third_status_W202323",
                "async_third_status_W202324",
                "async_third_status_W202325",
                "async_third_status_W202326",
                "async_third_status_W202327",
                "async_third_status_W202328",
                "async_third_status_W202329",
                "async_third_status_W20233",
                "async_third_status_W202330",
                "async_third_status_W202331",
                "async_third_status_W202332",
                "async_third_status_W202333",
                "async_third_status_W202334",
                "async_third_status_W202335",
                "async_third_status_W202336",
                "async_third_status_W202337",
                "async_third_status_W202338",
                "async_third_status_W202339",
                "async_third_status_W20234",
                "async_third_status_W202340",
                "async_third_status_W202341",
                "async_third_status_W202342",
                "async_third_status_W202343",
                "async_third_status_W202344",
                "async_third_status_W202345",
                "async_third_status_W202346",
                "async_third_status_W202347",
                "async_third_status_W202348",
                "async_third_status_W202349",
                "async_third_status_W20235",
                "async_third_status_W202350",
                "async_third_status_W202351",
                "async_third_status_W202352",
                "async_third_status_W202353",
                "async_third_status_W20236",
                "async_third_status_W20237",
                "async_third_status_W20238",
                "async_third_status_W20239"
            }
        };

        String schema = "CREATE TABLE `%s` (\n"
            + "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,\n"
            + "  `third_msg_id` varchar(64) DEFAULT '' COMMENT '第三方回执流水号',\n"
            + "  `msg_type` varchar(16) DEFAULT NULL COMMENT 'sms - 短信, wechat - 微信, push - 推送, mail - 邮件, TTS - 文字转语音',\n"
            + "  `platform` varchar(16) DEFAULT NULL COMMENT '平台：android, ios',\n"
            + "  `user_index_for_channel` varchar(64) DEFAULT '' COMMENT '手机号, openID, 邮件地址, did等',\n"
            + "  `async_third_status` tinyint(1) DEFAULT '0' COMMENT '异步回执结果',\n"
            + "  `async_third_error_code` varchar(32) DEFAULT '' COMMENT '异步回执错误码',\n"
            + "  `async_third_error_msg` varchar(128) DEFAULT '' COMMENT '异步回执信息',\n"
            + "  `arrive_time` timestamp NULL DEFAULT NULL COMMENT '消息到达时间',\n"
            + "  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
            + "  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n"
            + "  PRIMARY KEY (`id`),\n"
            + "  KEY `idx_tmid_ui` (`third_msg_id`,`user_index_for_channel`)\n"
            + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;";
        for (int i = 0; i < shardings.length; i++) {
            for (int j = 0; j < shardings[i].length; j++) {
                System.out.println(String.format(schema, shardings[i][j]));
            }
        }
    }

    @Test
    public void send_detail_10SlmF4BZgGT013635() {
        String[][] shardings = new String[][]{
            {
                "send_detail_10SlmF4BZgGT013635_M202201",
                "send_detail_10SlmF4BZgGT013635_M202202",
                "send_detail_10SlmF4BZgGT013635_M202203",
                "send_detail_10SlmF4BZgGT013635_M202204",
                "send_detail_10SlmF4BZgGT013635_M202205",
                "send_detail_10SlmF4BZgGT013635_M202206",
                "send_detail_10SlmF4BZgGT013635_M202207",
                "send_detail_10SlmF4BZgGT013635_M202208",
                "send_detail_10SlmF4BZgGT013635_M202209",
                "send_detail_10SlmF4BZgGT013635_M202210",
                "send_detail_10SlmF4BZgGT013635_M202211",
                "send_detail_10SlmF4BZgGT013635_M202212"
            },
            {
                "send_detail_10SlmF4BZgGT013635_M202301",
                "send_detail_10SlmF4BZgGT013635_M202302",
                "send_detail_10SlmF4BZgGT013635_M202303",
                "send_detail_10SlmF4BZgGT013635_M202304",
                "send_detail_10SlmF4BZgGT013635_M202305",
                "send_detail_10SlmF4BZgGT013635_M202306",
                "send_detail_10SlmF4BZgGT013635_M202307",
                "send_detail_10SlmF4BZgGT013635_M202308",
                "send_detail_10SlmF4BZgGT013635_M202309",
                "send_detail_10SlmF4BZgGT013635_M202310",
                "send_detail_10SlmF4BZgGT013635_M202311",
                "send_detail_10SlmF4BZgGT013635_M202312"
            }
        };

        String schema = "CREATE TABLE `%s` (\n"
            + "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,\n"
            + "  `batch_id` bigint(64) NOT NULL DEFAULT '0' COMMENT '批次号',\n"
            + "  `track_id` varchar(64) DEFAULT NULL COMMENT '其他系统的追踪id',\n"
            + "  `app_key` varchar(32) NOT NULL COMMENT '应用key',\n"
            + "  `serial_id` bigint(64) DEFAULT NULL COMMENT '序列号, 对应一次通道调用',\n"
            + "  `strategy_id` int(11) DEFAULT '0' COMMENT '组合ID，非组合推送不需记录',\n"
            + "  `template_id` int(11) DEFAULT '0' COMMENT '模板ID，定制推送不需记录',\n"
            + "  `msg_level` varchar(16) DEFAULT NULL COMMENT '消息级别：notice - 通知, ad - 营销, vcode - 验证码',\n"
            + "  `msg_type` varchar(16) DEFAULT NULL COMMENT 'sms - 短信, wechat - 微信, push - 推送, mail - 邮件, TTS - 文字转语音',\n"
            + "  `sub_msg_type` varchar(32) DEFAULT NULL COMMENT '子消息类型：wxTmpl - 微信模板消息, wxAppTmpl - 微信小程序模板消息, wxCustomer - 微信客服消息, wxGroup - 微信群发消息, pushIm - push IM消息',\n"
            + "  `platform` varchar(16) DEFAULT NULL COMMENT '平台：android, ios',\n"
            + "  `user_index_type` varchar(16) DEFAULT NULL COMMENT '用户标识类型：uid - userId, mobile - 手机号, openid - openId, did - deviceId, mail - 邮箱',\n"
            + "  `user_index` varchar(64) DEFAULT NULL COMMENT '用户标识，用以定位目标用户，通过user_index_type区分标识类型',\n"
            + "  `user_index_for_channel` varchar(64) DEFAULT NULL,\n"
            + "  `fail_reason` varchar(300) DEFAULT 'success' COMMENT '若发送失败，则记录失败原因',\n"
            + "  `channel_id` varchar(128) DEFAULT '' COMMENT '通道标识，短信供应商id，微信服务号id，小程序id，应用推送应用，邮件供应商id',\n"
            + "  `channel` varchar(32) DEFAULT NULL COMMENT '应用推送，邮件等通道',\n"
            + "  `input_raw` varchar(32) DEFAULT NULL COMMENT '未做拆解的手机号码',\n"
            + "  `national_number` varchar(32) DEFAULT NULL COMMENT '手机号码',\n"
            + "  `country_code` int(5) DEFAULT NULL COMMENT '国家码',\n"
            + "  `region` varchar(32) DEFAULT NULL COMMENT '区域如CN',\n"
            + "  `location` varchar(32) DEFAULT NULL COMMENT '地点',\n"
            + "  `carrier` varchar(32) DEFAULT NULL COMMENT '运营商',\n"
            + "  `third_msg_id` varchar(64) DEFAULT '' COMMENT '第三方回执流水号',\n"
            + "  `status` int(5) DEFAULT '0' COMMENT '发送结果：200 - 发送中, 300 - 发送失败, 500 - 已发送',\n"
            + "  `wx_template_id` varchar(128) DEFAULT '' COMMENT '微信推送时的微信模版id',\n"
            + "  `receive_time` timestamp NULL DEFAULT NULL COMMENT '消息接收时间',\n"
            + "  `send_time` timestamp NULL DEFAULT NULL COMMENT '发送执行时间',\n"
            + "  `content` varchar(1024) DEFAULT NULL COMMENT '推送内容',\n"
            + "  `attr1` varchar(256) DEFAULT '' COMMENT '扩展字段',\n"
            + "  `attr2` varchar(256) DEFAULT '' COMMENT '扩展字段',\n"
            + "  `mail_content` longtext COMMENT '邮件发送内容',\n"
            + "  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n"
            + "  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
            + "  PRIMARY KEY (`id`),\n"
            + "  KEY `idx_ct` (`create_time`),\n"
            + "  KEY `idx_sid_ct` (`strategy_id`),\n"
            + "  KEY `idx_tid_ct` (`template_id`),\n"
            + "  KEY `idx_ui` (`user_index`),\n"
            + "  KEY `idx_uifc` (`user_index_for_channel`),\n"
            + "  KEY `idx_bid` (`batch_id`)\n"
            + ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;";
        for (int i = 0; i < shardings.length; i++) {
            for (int j = 0; j < shardings[i].length; j++) {
                System.out.println(String.format(schema, shardings[i][j]));
            }
        }
    }

    @Test
    public void send_detail_6E4ClOqotm9014432() {
        String[][] shardings = new String[][]{
            {
                "send_detail_6E4ClOqotm9014432_M202201",
                "send_detail_6E4ClOqotm9014432_M202202",
                "send_detail_6E4ClOqotm9014432_M202203",
                "send_detail_6E4ClOqotm9014432_M202204",
                "send_detail_6E4ClOqotm9014432_M202205",
                "send_detail_6E4ClOqotm9014432_M202206",
                "send_detail_6E4ClOqotm9014432_M202207",
                "send_detail_6E4ClOqotm9014432_M202208",
                "send_detail_6E4ClOqotm9014432_M202209",
                "send_detail_6E4ClOqotm9014432_M202210",
                "send_detail_6E4ClOqotm9014432_M202211",
                "send_detail_6E4ClOqotm9014432_M202212"
            },
            {
                "send_detail_6E4ClOqotm9014432_M202301",
                "send_detail_6E4ClOqotm9014432_M202302",
                "send_detail_6E4ClOqotm9014432_M202303",
                "send_detail_6E4ClOqotm9014432_M202304",
                "send_detail_6E4ClOqotm9014432_M202305",
                "send_detail_6E4ClOqotm9014432_M202306",
                "send_detail_6E4ClOqotm9014432_M202307",
                "send_detail_6E4ClOqotm9014432_M202308",
                "send_detail_6E4ClOqotm9014432_M202309",
                "send_detail_6E4ClOqotm9014432_M202310",
                "send_detail_6E4ClOqotm9014432_M202311",
                "send_detail_6E4ClOqotm9014432_M202312"
            }
        };

        String schema = "CREATE TABLE `%s` (\n"
            + "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,\n"
            + "  `batch_id` bigint(64) NOT NULL DEFAULT '0' COMMENT '批次号',\n"
            + "  `track_id` varchar(64) DEFAULT NULL COMMENT '其他系统的追踪id',\n"
            + "  `app_key` varchar(32) NOT NULL COMMENT '应用key',\n"
            + "  `serial_id` bigint(64) DEFAULT NULL COMMENT '序列号, 对应一次通道调用',\n"
            + "  `strategy_id` int(11) DEFAULT '0' COMMENT '组合ID，非组合推送不需记录',\n"
            + "  `template_id` int(11) DEFAULT '0' COMMENT '模板ID，定制推送不需记录',\n"
            + "  `msg_level` varchar(16) DEFAULT NULL COMMENT '消息级别：notice - 通知, ad - 营销, vcode - 验证码',\n"
            + "  `msg_type` varchar(16) DEFAULT NULL COMMENT 'sms - 短信, wechat - 微信, push - 推送, mail - 邮件, TTS - 文字转语音',\n"
            + "  `sub_msg_type` varchar(32) DEFAULT NULL COMMENT '子消息类型：wxTmpl - 微信模板消息, wxAppTmpl - 微信小程序模板消息, wxCustomer - 微信客服消息, wxGroup - 微信群发消息, pushIm - push IM消息',\n"
            + "  `platform` varchar(16) DEFAULT NULL COMMENT '平台：android, ios',\n"
            + "  `user_index_type` varchar(16) DEFAULT NULL COMMENT '用户标识类型：uid - userId, mobile - 手机号, openid - openId, did - deviceId, mail - 邮箱',\n"
            + "  `user_index` varchar(64) DEFAULT NULL COMMENT '用户标识，用以定位目标用户，通过user_index_type区分标识类型',\n"
            + "  `user_index_for_channel` varchar(64) DEFAULT NULL,\n"
            + "  `fail_reason` varchar(300) DEFAULT 'success' COMMENT '若发送失败，则记录失败原因',\n"
            + "  `channel_id` varchar(128) DEFAULT '' COMMENT '通道标识，短信供应商id，微信服务号id，小程序id，应用推送应用，邮件供应商id',\n"
            + "  `channel` varchar(32) DEFAULT NULL COMMENT '应用推送，邮件等通道',\n"
            + "  `input_raw` varchar(32) DEFAULT NULL COMMENT '未做拆解的手机号码',\n"
            + "  `national_number` varchar(32) DEFAULT NULL COMMENT '手机号码',\n"
            + "  `country_code` int(5) DEFAULT NULL COMMENT '国家码',\n"
            + "  `region` varchar(32) DEFAULT NULL COMMENT '区域如CN',\n"
            + "  `location` varchar(32) DEFAULT NULL COMMENT '地点',\n"
            + "  `carrier` varchar(32) DEFAULT NULL COMMENT '运营商',\n"
            + "  `third_msg_id` varchar(64) DEFAULT '' COMMENT '第三方回执流水号',\n"
            + "  `status` int(5) DEFAULT '0' COMMENT '发送结果：200 - 发送中, 300 - 发送失败, 500 - 已发送',\n"
            + "  `wx_template_id` varchar(128) DEFAULT '' COMMENT '微信推送时的微信模版id',\n"
            + "  `receive_time` timestamp NULL DEFAULT NULL COMMENT '消息接收时间',\n"
            + "  `send_time` timestamp NULL DEFAULT NULL COMMENT '发送执行时间',\n"
            + "  `content` varchar(1024) DEFAULT NULL COMMENT '推送内容',\n"
            + "  `attr1` varchar(256) DEFAULT '' COMMENT '扩展字段',\n"
            + "  `attr2` varchar(256) DEFAULT '' COMMENT '扩展字段',\n"
            + "  `mail_content` longtext COMMENT '邮件发送内容',\n"
            + "  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n"
            + "  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
            + "  PRIMARY KEY (`id`),\n"
            + "  KEY `idx_ct` (`create_time`),\n"
            + "  KEY `idx_sid_ct` (`strategy_id`),\n"
            + "  KEY `idx_tid_ct` (`template_id`),\n"
            + "  KEY `idx_ui` (`user_index`),\n"
            + "  KEY `idx_uifc` (`user_index_for_channel`),\n"
            + "  KEY `idx_bid` (`batch_id`)\n"
            + ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;";
        for (int i = 0; i < shardings.length; i++) {
            for (int j = 0; j < shardings[i].length; j++) {
                System.out.println(String.format(schema, shardings[i][j]));
            }
        }
    }

    @Test
    public void send_detail_8ivymWyUd12458844() {
        String[][] shardings = new String[][]{
            {
                "send_detail_8ivymWyUd12458844_M202201",
                "send_detail_8ivymWyUd12458844_M202202",
                "send_detail_8ivymWyUd12458844_M202203",
                "send_detail_8ivymWyUd12458844_M202204",
                "send_detail_8ivymWyUd12458844_M202205",
                "send_detail_8ivymWyUd12458844_M202206",
                "send_detail_8ivymWyUd12458844_M202207",
                "send_detail_8ivymWyUd12458844_M202208",
                "send_detail_8ivymWyUd12458844_M202209",
                "send_detail_8ivymWyUd12458844_M202210",
                "send_detail_8ivymWyUd12458844_M202211",
                "send_detail_8ivymWyUd12458844_M202212"
            },
            {
                "send_detail_8ivymWyUd12458844_M202301",
                "send_detail_8ivymWyUd12458844_M202302",
                "send_detail_8ivymWyUd12458844_M202303",
                "send_detail_8ivymWyUd12458844_M202304",
                "send_detail_8ivymWyUd12458844_M202305",
                "send_detail_8ivymWyUd12458844_M202306",
                "send_detail_8ivymWyUd12458844_M202307",
                "send_detail_8ivymWyUd12458844_M202308",
                "send_detail_8ivymWyUd12458844_M202309",
                "send_detail_8ivymWyUd12458844_M202310",
                "send_detail_8ivymWyUd12458844_M202311",
                "send_detail_8ivymWyUd12458844_M202312"
            }
        };

        String schema = "CREATE TABLE `%s` (\n"
            + "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,\n"
            + "  `batch_id` bigint(64) NOT NULL DEFAULT '0' COMMENT '批次号',\n"
            + "  `track_id` varchar(64) DEFAULT NULL COMMENT '其他系统的追踪id',\n"
            + "  `app_key` varchar(32) NOT NULL COMMENT '应用key',\n"
            + "  `serial_id` bigint(64) DEFAULT NULL COMMENT '序列号, 对应一次通道调用',\n"
            + "  `strategy_id` int(11) DEFAULT '0' COMMENT '组合ID，非组合推送不需记录',\n"
            + "  `template_id` int(11) DEFAULT '0' COMMENT '模板ID，定制推送不需记录',\n"
            + "  `msg_level` varchar(16) DEFAULT NULL COMMENT '消息级别：notice - 通知, ad - 营销, vcode - 验证码',\n"
            + "  `msg_type` varchar(16) DEFAULT NULL COMMENT 'sms - 短信, wechat - 微信, push - 推送, mail - 邮件, TTS - 文字转语音',\n"
            + "  `sub_msg_type` varchar(32) DEFAULT NULL COMMENT '子消息类型：wxTmpl - 微信模板消息, wxAppTmpl - 微信小程序模板消息, wxCustomer - 微信客服消息, wxGroup - 微信群发消息, pushIm - push IM消息',\n"
            + "  `platform` varchar(16) DEFAULT NULL COMMENT '平台：android, ios',\n"
            + "  `user_index_type` varchar(16) DEFAULT NULL COMMENT '用户标识类型：uid - userId, mobile - 手机号, openid - openId, did - deviceId, mail - 邮箱',\n"
            + "  `user_index` varchar(64) DEFAULT NULL COMMENT '用户标识，用以定位目标用户，通过user_index_type区分标识类型',\n"
            + "  `user_index_for_channel` varchar(64) DEFAULT NULL,\n"
            + "  `fail_reason` varchar(300) DEFAULT 'success' COMMENT '若发送失败，则记录失败原因',\n"
            + "  `channel_id` varchar(128) DEFAULT '' COMMENT '通道标识，短信供应商id，微信服务号id，小程序id，应用推送应用，邮件供应商id',\n"
            + "  `channel` varchar(32) DEFAULT NULL COMMENT '应用推送，邮件等通道',\n"
            + "  `input_raw` varchar(32) DEFAULT NULL COMMENT '未做拆解的手机号码',\n"
            + "  `national_number` varchar(32) DEFAULT NULL COMMENT '手机号码',\n"
            + "  `country_code` int(5) DEFAULT NULL COMMENT '国家码',\n"
            + "  `region` varchar(32) DEFAULT NULL COMMENT '区域如CN',\n"
            + "  `location` varchar(32) DEFAULT NULL COMMENT '地点',\n"
            + "  `carrier` varchar(32) DEFAULT NULL COMMENT '运营商',\n"
            + "  `third_msg_id` varchar(64) DEFAULT '' COMMENT '第三方回执流水号',\n"
            + "  `status` int(5) DEFAULT '0' COMMENT '发送结果：200 - 发送中, 300 - 发送失败, 500 - 已发送',\n"
            + "  `wx_template_id` varchar(128) DEFAULT '' COMMENT '微信推送时的微信模版id',\n"
            + "  `receive_time` timestamp NULL DEFAULT NULL COMMENT '消息接收时间',\n"
            + "  `send_time` timestamp NULL DEFAULT NULL COMMENT '发送执行时间',\n"
            + "  `content` varchar(1024) DEFAULT NULL COMMENT '推送内容',\n"
            + "  `attr1` varchar(256) DEFAULT '' COMMENT '扩展字段',\n"
            + "  `attr2` varchar(256) DEFAULT '' COMMENT '扩展字段',\n"
            + "  `mail_content` longtext COMMENT '邮件发送内容',\n"
            + "  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n"
            + "  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
            + "  PRIMARY KEY (`id`),\n"
            + "  KEY `idx_ct` (`create_time`),\n"
            + "  KEY `idx_sid_ct` (`strategy_id`),\n"
            + "  KEY `idx_tid_ct` (`template_id`),\n"
            + "  KEY `idx_ui` (`user_index`),\n"
            + "  KEY `idx_uifc` (`user_index_for_channel`),\n"
            + "  KEY `idx_bid` (`batch_id`)\n"
            + ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;";
        for (int i = 0; i < shardings.length; i++) {
            for (int j = 0; j < shardings[i].length; j++) {
                System.out.println(String.format(schema, shardings[i][j]));
            }
        }
    }

    @Test
    public void send_detail_8ivymWyUd12458844_apppush() {
        String[][] shardings = new String[][]{
            {
                "send_detail_8ivymWyUd12458844_apppush_W20221",
                "send_detail_8ivymWyUd12458844_apppush_W202210",
                "send_detail_8ivymWyUd12458844_apppush_W202211",
                "send_detail_8ivymWyUd12458844_apppush_W202212",
                "send_detail_8ivymWyUd12458844_apppush_W202213",
                "send_detail_8ivymWyUd12458844_apppush_W202214",
                "send_detail_8ivymWyUd12458844_apppush_W202215",
                "send_detail_8ivymWyUd12458844_apppush_W202216",
                "send_detail_8ivymWyUd12458844_apppush_W202217",
                "send_detail_8ivymWyUd12458844_apppush_W202218",
                "send_detail_8ivymWyUd12458844_apppush_W202219",
                "send_detail_8ivymWyUd12458844_apppush_W20222",
                "send_detail_8ivymWyUd12458844_apppush_W202220",
                "send_detail_8ivymWyUd12458844_apppush_W202221",
                "send_detail_8ivymWyUd12458844_apppush_W202222",
                "send_detail_8ivymWyUd12458844_apppush_W202223",
                "send_detail_8ivymWyUd12458844_apppush_W202224",
                "send_detail_8ivymWyUd12458844_apppush_W202225",
                "send_detail_8ivymWyUd12458844_apppush_W202226",
                "send_detail_8ivymWyUd12458844_apppush_W202227",
                "send_detail_8ivymWyUd12458844_apppush_W202228",
                "send_detail_8ivymWyUd12458844_apppush_W202229",
                "send_detail_8ivymWyUd12458844_apppush_W20223",
                "send_detail_8ivymWyUd12458844_apppush_W202230",
                "send_detail_8ivymWyUd12458844_apppush_W202231",
                "send_detail_8ivymWyUd12458844_apppush_W202232",
                "send_detail_8ivymWyUd12458844_apppush_W202233",
                "send_detail_8ivymWyUd12458844_apppush_W202234",
                "send_detail_8ivymWyUd12458844_apppush_W202235",
                "send_detail_8ivymWyUd12458844_apppush_W202236",
                "send_detail_8ivymWyUd12458844_apppush_W202237",
                "send_detail_8ivymWyUd12458844_apppush_W202238",
                "send_detail_8ivymWyUd12458844_apppush_W202239",
                "send_detail_8ivymWyUd12458844_apppush_W20224",
                "send_detail_8ivymWyUd12458844_apppush_W202240",
                "send_detail_8ivymWyUd12458844_apppush_W202241",
                "send_detail_8ivymWyUd12458844_apppush_W202242",
                "send_detail_8ivymWyUd12458844_apppush_W202243",
                "send_detail_8ivymWyUd12458844_apppush_W202244",
                "send_detail_8ivymWyUd12458844_apppush_W202245",
                "send_detail_8ivymWyUd12458844_apppush_W202246",
                "send_detail_8ivymWyUd12458844_apppush_W202247",
                "send_detail_8ivymWyUd12458844_apppush_W202248",
                "send_detail_8ivymWyUd12458844_apppush_W202249",
                "send_detail_8ivymWyUd12458844_apppush_W20225",
                "send_detail_8ivymWyUd12458844_apppush_W202250",
                "send_detail_8ivymWyUd12458844_apppush_W202251",
                "send_detail_8ivymWyUd12458844_apppush_W202252",
                "send_detail_8ivymWyUd12458844_apppush_W202253",
                "send_detail_8ivymWyUd12458844_apppush_W20226",
                "send_detail_8ivymWyUd12458844_apppush_W20227",
                "send_detail_8ivymWyUd12458844_apppush_W20228",
                "send_detail_8ivymWyUd12458844_apppush_W20229"
            },
            {
                "send_detail_8ivymWyUd12458844_apppush_W20231",
                "send_detail_8ivymWyUd12458844_apppush_W202310",
                "send_detail_8ivymWyUd12458844_apppush_W202311",
                "send_detail_8ivymWyUd12458844_apppush_W202312",
                "send_detail_8ivymWyUd12458844_apppush_W202313",
                "send_detail_8ivymWyUd12458844_apppush_W202314",
                "send_detail_8ivymWyUd12458844_apppush_W202315",
                "send_detail_8ivymWyUd12458844_apppush_W202316",
                "send_detail_8ivymWyUd12458844_apppush_W202317",
                "send_detail_8ivymWyUd12458844_apppush_W202318",
                "send_detail_8ivymWyUd12458844_apppush_W202319",
                "send_detail_8ivymWyUd12458844_apppush_W20232",
                "send_detail_8ivymWyUd12458844_apppush_W202320",
                "send_detail_8ivymWyUd12458844_apppush_W202321",
                "send_detail_8ivymWyUd12458844_apppush_W202322",
                "send_detail_8ivymWyUd12458844_apppush_W202323",
                "send_detail_8ivymWyUd12458844_apppush_W202324",
                "send_detail_8ivymWyUd12458844_apppush_W202325",
                "send_detail_8ivymWyUd12458844_apppush_W202326",
                "send_detail_8ivymWyUd12458844_apppush_W202327",
                "send_detail_8ivymWyUd12458844_apppush_W202328",
                "send_detail_8ivymWyUd12458844_apppush_W202329",
                "send_detail_8ivymWyUd12458844_apppush_W20233",
                "send_detail_8ivymWyUd12458844_apppush_W202330",
                "send_detail_8ivymWyUd12458844_apppush_W202331",
                "send_detail_8ivymWyUd12458844_apppush_W202332",
                "send_detail_8ivymWyUd12458844_apppush_W202333",
                "send_detail_8ivymWyUd12458844_apppush_W202334",
                "send_detail_8ivymWyUd12458844_apppush_W202335",
                "send_detail_8ivymWyUd12458844_apppush_W202336",
                "send_detail_8ivymWyUd12458844_apppush_W202337",
                "send_detail_8ivymWyUd12458844_apppush_W202338",
                "send_detail_8ivymWyUd12458844_apppush_W202339",
                "send_detail_8ivymWyUd12458844_apppush_W20234",
                "send_detail_8ivymWyUd12458844_apppush_W202340",
                "send_detail_8ivymWyUd12458844_apppush_W202341",
                "send_detail_8ivymWyUd12458844_apppush_W202342",
                "send_detail_8ivymWyUd12458844_apppush_W202343",
                "send_detail_8ivymWyUd12458844_apppush_W202344",
                "send_detail_8ivymWyUd12458844_apppush_W202345",
                "send_detail_8ivymWyUd12458844_apppush_W202346",
                "send_detail_8ivymWyUd12458844_apppush_W202347",
                "send_detail_8ivymWyUd12458844_apppush_W202348",
                "send_detail_8ivymWyUd12458844_apppush_W202349",
                "send_detail_8ivymWyUd12458844_apppush_W20235",
                "send_detail_8ivymWyUd12458844_apppush_W202350",
                "send_detail_8ivymWyUd12458844_apppush_W202351",
                "send_detail_8ivymWyUd12458844_apppush_W202352",
                "send_detail_8ivymWyUd12458844_apppush_W202353",
                "send_detail_8ivymWyUd12458844_apppush_W20236",
                "send_detail_8ivymWyUd12458844_apppush_W20237",
                "send_detail_8ivymWyUd12458844_apppush_W20238",
                "send_detail_8ivymWyUd12458844_apppush_W20239"
            }
        };
        String schema = "CREATE TABLE `%s` (\n"
            + "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,\n"
            + "  `batch_id` bigint(64) NOT NULL DEFAULT '0' COMMENT '批次号',\n"
            + "  `track_id` varchar(64) DEFAULT NULL COMMENT '其他系统的追踪id',\n"
            + "  `app_key` varchar(32) NOT NULL COMMENT '应用key',\n"
            + "  `serial_id` bigint(64) DEFAULT NULL COMMENT '序列号, 对应一次通道调用',\n"
            + "  `strategy_id` int(11) DEFAULT '0' COMMENT '组合ID，非组合推送不需记录',\n"
            + "  `template_id` int(11) DEFAULT '0' COMMENT '模板ID，定制推送不需记录',\n"
            + "  `msg_level` varchar(16) DEFAULT NULL COMMENT '消息级别：notice - 通知, ad - 营销, vcode - 验证码',\n"
            + "  `msg_type` varchar(16) DEFAULT NULL COMMENT 'sms - 短信, wechat - 微信, push - 推送, mail - 邮件, TTS - 文字转语音',\n"
            + "  `sub_msg_type` varchar(32) DEFAULT NULL COMMENT '子消息类型：wxTmpl - 微信模板消息, wxAppTmpl - 微信小程序模板消息, wxCustomer - 微信客服消息, wxGroup - 微信群发消息, pushIm - push IM消息',\n"
            + "  `platform` varchar(16) DEFAULT NULL COMMENT '平台：android, ios',\n"
            + "  `user_index_type` varchar(16) DEFAULT NULL COMMENT '用户标识类型：uid - userId, mobile - 手机号, openid - openId, did - deviceId, mail - 邮箱',\n"
            + "  `user_index` varchar(64) DEFAULT NULL COMMENT '用户标识，用以定位目标用户，通过user_index_type区分标识类型',\n"
            + "  `user_index_for_channel` varchar(64) DEFAULT NULL,\n"
            + "  `fail_reason` varchar(300) DEFAULT 'success' COMMENT '若发送失败，则记录失败原因',\n"
            + "  `channel_id` varchar(128) DEFAULT '' COMMENT '通道标识，短信供应商id，微信服务号id，小程序id，应用推送应用，邮件供应商id',\n"
            + "  `channel` varchar(32) DEFAULT NULL COMMENT '应用推送，邮件等通道',\n"
            + "  `input_raw` varchar(32) DEFAULT NULL COMMENT '未做拆解的手机号码',\n"
            + "  `national_number` varchar(32) DEFAULT NULL COMMENT '手机号码',\n"
            + "  `country_code` int(5) DEFAULT NULL COMMENT '国家码',\n"
            + "  `region` varchar(32) DEFAULT NULL COMMENT '区域如CN',\n"
            + "  `location` varchar(32) DEFAULT NULL COMMENT '地点',\n"
            + "  `carrier` varchar(32) DEFAULT NULL COMMENT '运营商',\n"
            + "  `third_msg_id` varchar(64) DEFAULT '' COMMENT '第三方回执流水号',\n"
            + "  `status` int(5) DEFAULT '0' COMMENT '发送结果：200 - 发送中, 300 - 发送失败, 500 - 已发送',\n"
            + "  `wx_template_id` varchar(128) DEFAULT '' COMMENT '微信推送时的微信模版id',\n"
            + "  `receive_time` timestamp NULL DEFAULT NULL COMMENT '消息接收时间',\n"
            + "  `send_time` timestamp NULL DEFAULT NULL COMMENT '发送执行时间',\n"
            + "  `content` varchar(1024) DEFAULT NULL COMMENT '推送内容',\n"
            + "  `attr1` varchar(256) DEFAULT '' COMMENT '扩展字段',\n"
            + "  `attr2` varchar(256) DEFAULT '' COMMENT '扩展字段',\n"
            + "  `mail_content` longtext COMMENT '邮件发送内容',\n"
            + "  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n"
            + "  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
            + "  PRIMARY KEY (`id`),\n"
            + "  KEY `idx_ct` (`create_time`),\n"
            + "  KEY `idx_sid_ct` (`strategy_id`),\n"
            + "  KEY `idx_tid_ct` (`template_id`),\n"
            + "  KEY `idx_ui` (`user_index`),\n"
            + "  KEY `idx_uifc` (`user_index_for_channel`),\n"
            + "  KEY `idx_bid` (`batch_id`)\n"
            + ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;";
        for (int i = 0; i < shardings.length; i++) {
            for (int j = 0; j < shardings[i].length; j++) {
                System.out.println(String.format(schema, shardings[i][j]));
            }
        }
    }

    @Test
    public void send_detail() {
        String[][] shardings = new String[][]{
            {
                "send_detail_M202201",
                "send_detail_M202202",
                "send_detail_M202203",
                "send_detail_M202204",
                "send_detail_M202205",
                "send_detail_M202206",
                "send_detail_M202207",
                "send_detail_M202208",
                "send_detail_M202209",
                "send_detail_M202210",
                "send_detail_M202211",
                "send_detail_M202212"
            },
            {
                "send_detail_M202301",
                "send_detail_M202302",
                "send_detail_M202303",
                "send_detail_M202304",
                "send_detail_M202305",
                "send_detail_M202306",
                "send_detail_M202307",
                "send_detail_M202308",
                "send_detail_M202309",
                "send_detail_M202310",
                "send_detail_M202311",
                "send_detail_M202312"
            }
        };
        String schema = "CREATE TABLE `%s` (\n"
            + "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,\n"
            + "  `batch_id` bigint(64) NOT NULL DEFAULT '0' COMMENT '批次号',\n"
            + "  `track_id` varchar(64) DEFAULT NULL COMMENT '其他系统的追踪id',\n"
            + "  `app_key` varchar(32) NOT NULL COMMENT '应用key',\n"
            + "  `serial_id` bigint(64) DEFAULT NULL COMMENT '序列号, 对应一次通道调用',\n"
            + "  `strategy_id` int(11) DEFAULT '0' COMMENT '组合ID，非组合推送不需记录',\n"
            + "  `template_id` int(11) DEFAULT '0' COMMENT '模板ID，定制推送不需记录',\n"
            + "  `msg_level` varchar(16) DEFAULT NULL COMMENT '消息级别：notice - 通知, ad - 营销, vcode - 验证码',\n"
            + "  `msg_type` varchar(16) DEFAULT NULL COMMENT 'sms - 短信, wechat - 微信, push - 推送, mail - 邮件, TTS - 文字转语音',\n"
            + "  `sub_msg_type` varchar(32) DEFAULT NULL COMMENT '子消息类型：wxTmpl - 微信模板消息, wxAppTmpl - 微信小程序模板消息, wxCustomer - 微信客服消息, wxGroup - 微信群发消息, pushIm - push IM消息',\n"
            + "  `platform` varchar(16) DEFAULT NULL COMMENT '平台：android, ios',\n"
            + "  `user_index_type` varchar(16) DEFAULT NULL COMMENT '用户标识类型：uid - userId, mobile - 手机号, openid - openId, did - deviceId, mail - 邮箱',\n"
            + "  `user_index` varchar(64) DEFAULT NULL COMMENT '用户标识，用以定位目标用户，通过user_index_type区分标识类型',\n"
            + "  `user_index_for_channel` varchar(64) DEFAULT NULL,\n"
            + "  `fail_reason` varchar(300) DEFAULT 'success' COMMENT '若发送失败，则记录失败原因',\n"
            + "  `channel_id` varchar(128) DEFAULT '' COMMENT '通道标识，短信供应商id，微信服务号id，小程序id，应用推送应用，邮件供应商id',\n"
            + "  `channel` varchar(32) DEFAULT NULL COMMENT '应用推送，邮件等通道',\n"
            + "  `input_raw` varchar(32) DEFAULT NULL COMMENT '未做拆解的手机号码',\n"
            + "  `national_number` varchar(32) DEFAULT NULL COMMENT '手机号码',\n"
            + "  `country_code` int(5) DEFAULT NULL COMMENT '国家码',\n"
            + "  `region` varchar(32) DEFAULT NULL COMMENT '区域如CN',\n"
            + "  `location` varchar(32) DEFAULT NULL COMMENT '地点',\n"
            + "  `carrier` varchar(32) DEFAULT NULL COMMENT '运营商',\n"
            + "  `third_msg_id` varchar(64) DEFAULT '' COMMENT '第三方回执流水号',\n"
            + "  `status` int(5) DEFAULT '0' COMMENT '发送结果：200 - 发送中, 300 - 发送失败, 500 - 已发送',\n"
            + "  `wx_template_id` varchar(128) DEFAULT '' COMMENT '微信推送时的微信模版id',\n"
            + "  `receive_time` timestamp NULL DEFAULT NULL COMMENT '消息接收时间',\n"
            + "  `send_time` timestamp NULL DEFAULT NULL COMMENT '发送执行时间',\n"
            + "  `content` varchar(1024) DEFAULT NULL COMMENT '推送内容',\n"
            + "  `attr1` varchar(256) DEFAULT '' COMMENT '扩展字段',\n"
            + "  `attr2` varchar(256) DEFAULT '' COMMENT '扩展字段',\n"
            + "  `mail_content` longtext COMMENT '邮件发送内容',\n"
            + "  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n"
            + "  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
            + "  PRIMARY KEY (`id`),\n"
            + "  KEY `idx_ct_ak` (`create_time`,`app_key`),\n"
            + "  KEY `idx_sid_ct` (`strategy_id`),\n"
            + "  KEY `idx_tid_ct` (`template_id`),\n"
            + "  KEY `idx_ui` (`user_index`),\n"
            + "  KEY `idx_uifc` (`user_index_for_channel`),\n"
            + "  KEY `idx_bid` (`batch_id`)\n"
            + ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;";
        for (int i = 0; i < shardings.length; i++) {
            for (int j = 0; j < shardings[i].length; j++) {
                System.out.println(String.format(schema, shardings[i][j]));
            }
        }
    }

    @Test
    public void send_history() {
        String[][] shardings = new String[][]{
            {
                "send_history_M202201",
                "send_history_M202202",
                "send_history_M202203",
                "send_history_M202204",
                "send_history_M202205",
                "send_history_M202206",
                "send_history_M202207",
                "send_history_M202208",
                "send_history_M202209",
                "send_history_M202210",
                "send_history_M202211",
                "send_history_M202212"
            },
            {
                "send_history_M202301",
                "send_history_M202302",
                "send_history_M202303",
                "send_history_M202304",
                "send_history_M202305",
                "send_history_M202306",
                "send_history_M202307",
                "send_history_M202308",
                "send_history_M202309",
                "send_history_M202310",
                "send_history_M202311",
                "send_history_M202312"
            }
        };
        String schema = "CREATE TABLE `%s` (\n"
            + "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,\n"
            + "  `batch_id` bigint(64) NOT NULL DEFAULT '0' COMMENT '批次号',\n"
            + "  `track_id` varchar(64) DEFAULT NULL COMMENT '其他系统的追踪id',\n"
            + "  `app_key` varchar(32) NOT NULL DEFAULT '' COMMENT '调用方应用key',\n"
            + "  `send_type` varchar(16) NOT NULL DEFAULT '' COMMENT 'strategy - 组合推送, template - 模板推送, custom - 定制推送',\n"
            + "  `msg_level` varchar(16) DEFAULT NULL COMMENT '消息级别：notice - 通知, ad - 营销, vcode - 验证码',\n"
            + "  `msg_type` varchar(16) DEFAULT NULL COMMENT 'sms - 短信, wechat - 微信, push - 推送, mail - 邮件',\n"
            + "  `strategy_id` int(11) DEFAULT '0' COMMENT '组合ID, 对应组合推送',\n"
            + "  `template_id` int(11) DEFAULT '0' COMMENT '模板ID, 对应模板推送',\n"
            + "  `custom_content` text COMMENT '自定义推送和模板推送内容',\n"
            + "  `status` int(5) DEFAULT '0' COMMENT '发送状态',\n"
            + "  `track` varchar(128) DEFAULT NULL COMMENT '错误信息',\n"
            + "  `receive_time` timestamp NULL DEFAULT NULL COMMENT '消息接收时间',\n"
            + "  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
            + "  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n"
            + "  PRIMARY KEY (`id`),\n"
            + "  KEY `idx_ct_ak` (`create_time`,`app_key`),\n"
            + "  KEY `idx_sid_ct` (`strategy_id`),\n"
            + "  KEY `idx_tid_ct` (`template_id`),\n"
            + "  KEY `idx_bid` (`batch_id`)\n"
            + ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;";
        for (int i = 0; i < shardings.length; i++) {
            for (int j = 0; j < shardings[i].length; j++) {
                System.out.println(String.format(schema, shardings[i][j]));
            }
        }
    }

}
