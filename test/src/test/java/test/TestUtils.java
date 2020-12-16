package test;

import com.hujiang.basic.framework.core.sercurity.MD5;
import com.hujiang.basic.framework.core.util.JsonUtil;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import com.training.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestUtils {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User {
        private String name;
        private int age;

        public String toString() {
            return JsonUtil.object2JSON(this);
        }
    }

    @Test
    public void testMapMD5() {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();
        Map<String, Object> map3 = new HashMap<>();

        map1.put("school", "wuhan");
        map1.put("user", new User("gwj", 18));

        map2.put("school", "xiamen");
        map2.put("user", new User("yk", 20));

        map3.put("school", "wuhan");
        map3.put("user", new User("gwj", 18));

        System.out.println(JsonUtil.object2JSON(map1));
        System.out.println(JsonUtil.object2JSON(map2));
        System.out.println(JsonUtil.object2JSON(map3));

        System.out.println(Utils.md5(JsonUtil.object2JSON(map1)));
        System.out.println(Utils.md5(JsonUtil.object2JSON(map3)));
        System.out.println(Objects.equals(Utils.md5(JsonUtil.object2JSON(map1)), Utils.md5(JsonUtil.object2JSON(map3))));

    }


    @Test
    public void testDesEncrypt() {
        String des = Utils.desEncrypt(Utils.generateFixedStr(32));
        System.out.println(des);
    }

    @Test
    public void testMD5For() {
        Set<String> ids = Sets.newHashSet();


        for (int i = 0; i < 100000; i++) {
            String id = Utils.generateFixedStr(6);
            ids.add(id);
        }

        System.out.println(ids);
        long start = System.currentTimeMillis();
        String md5 = Utils.md5(ids.toString());
        long end = System.currentTimeMillis();
        System.out.println(md5);
        System.out.println((end - start) + " ms");
    }

    @Test
    public void testMD5ForPhone() {
        System.out.println(Utils.md5("18687014122"));
    }

    @Test
    public void testDESFor() {
        Set<String> ids = Sets.newHashSet();

        for (int i = 0; i < 100000; i++) {
            String id = Utils.generateFixedStr(6);
            ids.add(id);
        }
        System.out.println(ids);
        long start = System.currentTimeMillis();
        String des = Utils.desEncrypt(ids.toString());
        long end = System.currentTimeMillis();
        System.out.println(des);
        System.out.println((end - start) + " ms");
    }

    @Test
    public void testSet() {
        Set<String> ids = Sets.newHashSet();
        System.out.println(ids.toString());
        System.out.println(Utils.md5(ids.toString()));

    }

    @Test
    public void testGenHJAuthSchema() {
        for (int i = 0; i < 64; i++) {
            String sql = "DROP TABLE IF EXISTS `user_union_" + i + "`;\n" +
                    "CREATE TABLE `user_union_" + i + "` (\n" +
                    "  `id` int unsigned NOT NULL AUTO_INCREMENT,\n" +
                    "  `union_id` bigint NOT NULL,\n" +
                    "  `user_id` int(10) NOT NULL,\n" +
                    "  `user_domain` varchar(10) NOT NULL,\n" +
                    "  `create_at` datetime NOT NULL ,\n" +
                    "  `update_at` datetime NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE KEY `uidx_user` (`user_id`,`user_domain`) USING BTREE,\n" +
                    "  UNIQUE KEY `uidx_union` (`union_id`,`user_domain`) USING BTREE\n" +
                    ") ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;";
            System.out.println(sql);
            System.out.println();
        }

        for (int i = 0; i < 64; i++) {
            String sql = "DROP TABLE IF EXISTS `union_user_" + i + "`;\n" +
                    "CREATE TABLE `union_user_" + i + "` (\n" +
                    "  `id` int unsigned NOT NULL AUTO_INCREMENT,\n" +
                    "  `union_id` bigint NOT NULL,\n" +
                    "  `user_id` int(10) NOT NULL,\n" +
                    "  `user_domain` varchar(10) NOT NULL,\n" +
                    "  `create_at` datetime NOT NULL ,\n" +
                    "  `update_at` datetime NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE KEY `uidx_user` (`user_id`,`user_domain`) USING BTREE,\n" +
                    "  UNIQUE KEY `uidx_union` (`union_id`,`user_domain`) USING BTREE\n" +
                    ") ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;";
            System.out.println(sql);
            System.out.println();
        }
    }

    @Test
    public void testGenHJAuthSchemaV2() {
        for (int i = 0; i < 64; i++) {
            String sql = "DROP TABLE IF EXISTS `user_union_" + i + "`;\n" +
                    "CREATE TABLE `user_union_" + i + "` (\n" +
                    "  `id` int unsigned NOT NULL AUTO_INCREMENT,\n" +
                    "  `union_id` bigint NOT NULL,\n" +
                    "  `user_id` bigint NOT NULL,\n" +
                    "  `user_domain` varchar(10) NOT NULL,\n" +
                    "  `union_domain` varchar(10) NOT NULL,\n" +
                    "  `create_at` datetime NOT NULL,\n" +
                    "  `update_at` datetime NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE KEY `uidx_user` (`user_id`,`user_domain`,`union_domain`) USING BTREE,\n" +
                    "  UNIQUE KEY `uidx_union` (`union_id`,`user_domain`,`union_domain`) USING BTREE\n" +
                    ") ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;";
            System.out.println(sql);
            System.out.println();
        }

        for (int i = 0; i < 64; i++) {
            String sql = "DROP TABLE IF EXISTS `union_user_" + i + "`;\n" +
                    "CREATE TABLE `union_user_" + i + "` (\n" +
                    "  `id` int unsigned NOT NULL AUTO_INCREMENT,\n" +
                    "  `union_id` bigint NOT NULL,\n" +
                    "  `user_id` bigint NOT NULL,\n" +
                    "  `user_domain` varchar(10) NOT NULL,\n" +
                    "  `union_domain` varchar(10) NOT NULL,\n" +
                    "  `create_at` datetime NOT NULL,\n" +
                    "  `update_at` datetime NOT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE KEY `uidx_user` (`user_id`,`user_domain`,`union_domain`) USING BTREE,\n" +
                    "  UNIQUE KEY `uidx_union` (`union_id`,`user_domain`,`union_domain`) USING BTREE\n" +
                    ") ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;";
            System.out.println(sql);
            System.out.println();
        }
    }

    @Test
    public void testGenHJAuthSchemaV2_H2() {
        for (int i = 0; i < 64; i++) {
            String sql = "DROP TABLE IF EXISTS `user_union_" + i + "`;\n" +
                    "CREATE TABLE `user_union_" + i + "` (\n" +
                    "  `id` int unsigned NOT NULL AUTO_INCREMENT,\n" +
                    "  `union_id` bigint NOT NULL,\n" +
                    "  `user_id` bigint NOT NULL,\n" +
                    "  `user_domain` varchar(10) NOT NULL,\n" +
                    "  `union_domain` varchar(10) NOT NULL,\n" +
                    "  `create_at` datetime NOT NULL,\n" +
                    "  `update_at` datetime NOT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";
            System.out.println(sql);
            System.out.println();
        }

        for (int i = 0; i < 64; i++) {
            String sql = "DROP TABLE IF EXISTS `union_user_" + i + "`;\n" +
                    "CREATE TABLE `union_user_" + i + "` (\n" +
                    "  `id` int unsigned NOT NULL AUTO_INCREMENT,\n" +
                    "  `union_id` bigint NOT NULL,\n" +
                    "  `user_id` bigint NOT NULL,\n" +
                    "  `user_domain` varchar(10) NOT NULL,\n" +
                    "  `union_domain` varchar(10) NOT NULL,\n" +
                    "  `create_at` datetime NOT NULL,\n" +
                    "  `update_at` datetime NOT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;";
            System.out.println(sql);
            System.out.println();
        }
    }

    @Test
    public void testMD5_null() {
        String str = null;
        System.out.println(Utils.md5(""));
    }

    @Test
    public void testMD5() {
        System.out.println(Utils.md5("hj-app-id=6TA4ErRfqDF578032&hj-random-str=xxx&hj-app-sign=$apr1$SCBk5y$5uwhv.kNQJq.OHsdoJ8mq1"));
    }


    public static void testGenFixexStr(String[] args) {
        String str = Utils.generateFixedStr(64);
        System.out.println(str.toUpperCase());

        System.out.println(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        System.out.println(Utils.md5(uuid));
        System.out.println(Utils.desEncrypt(uuid));
    }

    @Test
    public void testGenDeviceInfo_Schema() {
        String[] years = new String[] {"2020", "2021", "2022"};
        for (String year : years) {
            for (int i = 1; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "";
                System.out.println(sql);
                System.out.println();
            }

            for (int i = 1; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "";
                System.out.println(sql);
                System.out.println();
            }
        }
    }

    @Test
    public void testGenAPNs_Schema() {
        String[] years = new String[] {"2020", "2021", "2022"};
        for (String year : years) {
            for (int i = 1; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "CREATE TABLE `push_task_ccschool_"+year + month+"` (\n"
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
                    + "  KEY `idx_msg_id` (`msg_id`) USING BTREE,\n"
                    + "  KEY `idx_app_id` (`app_id`) USING BTREE\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
                System.out.println(sql);
                System.out.println();
            }

            for (int i = 1; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "CREATE TABLE `push_task_cctalk_"+year + month+"` (\n"
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
                    + "  KEY `idx_msg_id` (`msg_id`) USING BTREE,\n"
                    + "  KEY `idx_app_id` (`app_id`) USING BTREE\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
                System.out.println(sql);
                System.out.println();
            }

            for (int i = 1; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "CREATE TABLE `push_task_class_"+year + month+"` (\n"
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
                    + "  KEY `idx_msg_id` (`msg_id`) USING BTREE,\n"
                    + "  KEY `idx_app_id` (`app_id`) USING BTREE\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
                System.out.println(sql);
                System.out.println();
            }

            for (int i = 1; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "CREATE TABLE `push_task_hjcichang_"+year + month+"` (\n"
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
                    + "  KEY `idx_msg_id` (`msg_id`) USING BTREE,\n"
                    + "  KEY `idx_app_id` (`app_id`) USING BTREE\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
                System.out.println(sql);
                System.out.println();
            }

            for (int i = 1; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "CREATE TABLE `push_task_hjdict_"+year + month+"` (\n"
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
                    + "  KEY `idx_msg_id` (`msg_id`) USING BTREE,\n"
                    + "  KEY `idx_app_id` (`app_id`) USING BTREE\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
                System.out.println(sql);
                System.out.println();
            }

        }
    }

    @Test
    public void testGenWeChat_Schema() {
        String[] years = new String[] {"2020", "2021", "2022"};
        for (String year : years) {
            for (int i = 1; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "CREATE TABLE `send_record_"+year+month+"` (\n"
                    + "  `id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `batch_id` varchar(100) NOT NULL  COMMENT '批次号',\n"
                    + "  `send_type` varchar(16) NOT NULL COMMENT 'tmpl - 微信模板消息, custom - 微信客服消息, group - 微信群发消息, applet - 微信小程序模板消息',\n"
                    + "  `msg_type` varchar(16) NOT NULL COMMENT 'mb - 微信模板消息, kf - 微信客服消息, mass - 微信群发消息, applet - 微信小程序模板消息',\n"
                    + "  `wechat_app_id` varchar(50) NOT NULL  COMMENT '服务号ID',\n"
                    + "  `data` json NOT NULL COMMENT '推送内容',\n"
                    + "  `status` int(5) NOT NULL DEFAULT '0' COMMENT '发送状态',\n"
                    + "  `app_key` varchar(128) NULL DEFAULT NULL COMMENT '调用方应用key（适用于2.0）',\n"
                    + "  `app_id` varchar(128) NULL DEFAULT NULL COMMENT '调用方应用id（适用于1.0）',\n"
                    + "  `receive_time` timestamp NULL DEFAULT NULL COMMENT '消息接收时间',\n"
                    + "  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,\n"
                    + "  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n"
                    + "  PRIMARY KEY (`id`),\n"
                    + "  KEY `idx_bid` (`batch_id`),\n"
                    + "  KEY `idx_ct` (`create_time`)\n"
                    + ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;";
                System.out.println(sql);
                System.out.println();
            }
            for (int i = 1; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "CREATE TABLE `AppletTemplateMessageSendHistory_"+year + month+"` (\n"
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
                    + "  KEY `idx_wid` (`WechatAppID`) USING BTREE,\n"
                    + "  KEY `idx_bid_pdt` (`BatchID`,`PushDateTime`) USING BTREE,\n"
                    + "  KEY `idx_pdt` (`PushDateTime`) USING BTREE\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
                System.out.println(sql);
                System.out.println();
            }

            for (int i = 1; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "CREATE TABLE `CustomMessageSendHistory_"+year + month+"` (\n"
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
                    + "  KEY `idx_wid` (`WechatAppID`) USING BTREE,\n"
                    + "  KEY `idx_bid_pdt` (`BatchID`,`PushDateTime`) USING BTREE,\n"
                    + "  KEY `idx_pdt` (`PushDateTime`) USING BTREE\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
                System.out.println(sql);
                System.out.println();
            }

            for (int i = 1; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "CREATE TABLE `GroupMessageSendHistory_"+year+month+"` (\n"
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
                    + "  KEY `idx_wid` (`WechatAppID`) USING BTREE,\n"
                    + "  KEY `idx_bid_pdt` (`BatchID`,`PushDateTime`) USING BTREE,\n"
                    + "  KEY `idx_pdt` (`PushDateTime`) USING BTREE\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
                System.out.println(sql);
                System.out.println();
            }

            for (int i = 1; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "CREATE TABLE `SendHistory_"+year + month+"` (\n"
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
                    + "  KEY `idx_wid` (`WechatAppID`) USING BTREE,\n"
                    + "  KEY `idx_bid_pdt` (`BatchID`,`PushDateTime`) USING BTREE,\n"
                    + "  KEY `idx_pdt` (`PushDateTime`) USING BTREE\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
                System.out.println(sql);
                System.out.println();
            }
        }
    }

    @Test
    public void testGenAppPush_Schema() {
        String[] years = new String[] {"2020", "2021", "2022"};
        for (String year : years) {
            for (int i = 1; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "CREATE TABLE `sendhistory_"+year + month +"` (\n"
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
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
                System.out.println(sql);
                System.out.println();
            }

            for (int i = 1; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "CREATE TABLE `sendresult_"+year + month+"` (\n"
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
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
                System.out.println(sql);
                System.out.println();
            }
        }
    }

    @Test
    public void testGenTemplate_V1_Schema() {
        String[] years = new String[] {"2020", "2021", "2022"};
        for (String year : years) {
            for (int i = 1; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "CREATE TABLE `sendhistory_" + year + month + "` (\n"
                    + "  `id` int(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `AppName` varchar(64) NOT NULL,\n"
                    + "  `ToUser` text,\n"
                    + "  `SendType` tinyint(2) NOT NULL,\n"
                    + "  `Phone` text,\n"
                    + "  `Mail` varchar(64) DEFAULT NULL,\n"
                    + "  `TemplateId` int(11) NOT NULL,\n"
                    + "  `ParaAction` varchar(1024) NOT NULL,\n"
                    + "  `ExpireTime` datetime NOT NULL,\n"
                    + "  `ScheduledTime` datetime NOT NULL,\n"
                    + "  `BatchId` varchar(64) NOT NULL,\n"
                    + "  `CreateTime` datetime NOT NULL,\n"
                    + "  `UpdateTime` datetime NOT NULL,\n"
                    + "  `CreateUser` varchar(64) DEFAULT NULL,\n"
                    + "  `UpdateUser` varchar(64) DEFAULT NULL,\n"
                    + "  `Status` tinyint(2) NOT NULL,\n"
                    + "  `StatusChange` varchar(4096) NOT NULL,\n"
                    + "  PRIMARY KEY (`id`),\n"
                    + "  KEY `idx_ct` (`CreateTime`) USING BTREE,\n"
                    + "  KEY `idx_bid` (`BatchId`) USING BTREE\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
                System.out.println(sql);
                System.out.println();
            }
        }
    }

    @Test
    public void testGenMail_Schema() {
        String[] years = new String[] {"2018"};
        for (String year : years) {
            for (int i = 1; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "";
                System.out.println(sql);
                System.out.println();
            }

        }
    }
    @Test
    public void testGenWechat_Schema() {
        String[] years = new String[] {"2018"};
        for (String year : years) {
            for (int i = 1; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "DROP TABLE IF EXISTS `AppletTemplateMessageSendHistory_" + year + month + "`;\n" +
                    "CREATE TABLE `AppletTemplateMessageSendHistory_" + year + month + "` (\n" +
                    "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `Topic` varchar(50) NOT NULL,\n" +
                    "  `TemplateId` varchar(500) NOT NULL,\n" +
                    "  `ToUser` varchar(500) NOT NULL,\n" +
                    "  `MsgId` bigint(20) DEFAULT NULL,\n" +
                    "  `Result` bit(1) DEFAULT NULL,\n" +
                    "  `ErrCode` int(11) DEFAULT NULL,\n" +
                    "  `ErrMsg` varchar(500) DEFAULT NULL,\n" +
                    "  `PushDateTime` datetime DEFAULT NULL,\n" +
                    "  `BatchID` varchar(100) DEFAULT NULL,\n" +
                    "  `WechatAppID` varchar(50) NOT NULL DEFAULT '',\n" +
                    "  `InitiatorID` int(11) DEFAULT NULL,\n" +
                    "  `Initiator` varchar(50) NOT NULL DEFAULT '',\n" +
                    "  PRIMARY KEY (`Id`),\n" +
                    "   KEY `IX_AppletTemplateMessageSendHistory_wid` (`WechatAppID`) USING BTREE,\n" +
                    "   KEY `IX_AppletTemplateMessageSendHistory_bid_pdt` (`BatchID`,`PushDateTime`) USING BTREE,\n" +
                    "   KEY `IX_AppletTemplateMessageSendHistory_pdt` (`PushDateTime`) USING BTREE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
                System.out.println(sql);
                System.out.println();
            }


            for (int i = 1; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "DROP TABLE IF EXISTS `GroupMessageSendHistory_" + year + month + "`;\n" +
                    "CREATE TABLE `GroupMessageSendHistory_" + year + month + "` (\n" +
                    "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `Topic` varchar(50) NOT NULL,\n" +
                    "  `TemplateId` varchar(500) NOT NULL,\n" +
                    "  `ToUser` varchar(500) NOT NULL,\n" +
                    "  `MsgId` bigint(20) DEFAULT NULL,\n" +
                    "  `Result` bit(1) DEFAULT NULL,\n" +
                    "  `ErrCode` int(11) DEFAULT NULL,\n" +
                    "  `ErrMsg` varchar(500) DEFAULT NULL,\n" +
                    "  `PushDateTime` datetime DEFAULT NULL,\n" +
                    "  `BatchID` varchar(100) DEFAULT NULL,\n" +
                    "  `WechatAppID` varchar(50) NOT NULL DEFAULT '',\n" +
                    "  `InitiatorID` int(11) DEFAULT NULL,\n" +
                    "  `Initiator` varchar(50) NOT NULL DEFAULT '',\n" +
                    "  PRIMARY KEY (`Id`),\n" +
                    "   KEY `IX_GroupMessageSendHistory_wid` (`WechatAppID`) USING BTREE,\n" +
                    "   KEY `IX_GroupMessageSendHistory_bid_pdt` (`BatchID`,`PushDateTime`) USING BTREE,\n" +
                    "   KEY `IX_GroupMessageSendHistory_pdt` (`PushDateTime`) USING BTREE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
                System.out.println(sql);
                System.out.println();
            }

            for (int i = 1; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "DROP TABLE IF EXISTS `SendHistory_" + year + month + "`;\n" +
                    "CREATE TABLE `SendHistory_" + year + month + "` (\n" +
                    "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `Topic` varchar(50) NOT NULL,\n" +
                    "  `TemplateId` varchar(500) NOT NULL,\n" +
                    "  `ToUser` varchar(500) NOT NULL,\n" +
                    "  `MsgId` bigint(20) DEFAULT NULL,\n" +
                    "  `Result` bit(1) DEFAULT NULL,\n" +
                    "  `ErrCode` int(11) DEFAULT NULL,\n" +
                    "  `ErrMsg` varchar(500) DEFAULT NULL,\n" +
                    "  `PushDateTime` datetime DEFAULT NULL,\n" +
                    "  `BatchID` varchar(100) DEFAULT NULL,\n" +
                    "  `WechatAppID` varchar(50) NOT NULL DEFAULT '',\n" +
                    "  `InitiatorID` int(11) DEFAULT NULL,\n" +
                    "  `Initiator` varchar(50) NOT NULL DEFAULT '',\n" +
                    "  PRIMARY KEY (`Id`),\n" +
                    "   KEY `IX_SendHistory_wid` (`WechatAppID`) USING BTREE,\n" +
                    "   KEY `IX_SendHistory_bid_pdt` (`BatchID`,`PushDateTime`) USING BTREE,\n" +
                    "   KEY `IX_SendHistory_pdt` (`PushDateTime`) USING BTREE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
                System.out.println(sql);
                System.out.println();
            }

            for (int i = 1; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "DROP TABLE IF EXISTS `CustomMessageSendHistory_" + year + month + "`;\n" +
                    "CREATE TABLE `CustomMessageSendHistory_" + year + month + "` (\n" +
                    "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `Topic` varchar(50) NOT NULL,\n" +
                    "  `TemplateId` varchar(500) NOT NULL,\n" +
                    "  `ToUser` varchar(500) NOT NULL,\n" +
                    "  `MsgId` bigint(20) DEFAULT NULL,\n" +
                    "  `Result` bit(1) DEFAULT NULL,\n" +
                    "  `ErrCode` int(11) DEFAULT NULL,\n" +
                    "  `ErrMsg` varchar(500) DEFAULT NULL,\n" +
                    "  `PushDateTime` datetime DEFAULT NULL,\n" +
                    "  `BatchID` varchar(100) DEFAULT NULL,\n" +
                    "  `WechatAppID` varchar(50) NOT NULL DEFAULT '',\n" +
                    "  `InitiatorID` int(11) DEFAULT NULL,\n" +
                    "  `Initiator` varchar(50) NOT NULL DEFAULT '',\n" +
                    "  PRIMARY KEY (`Id`),\n" +
                    "   KEY `IX_CustomMessageSendHistory_wid` (`WechatAppID`) USING BTREE,\n" +
                    "   KEY `IX_CustomMessageSendHistory_bid_pdt` (`BatchID`,`PushDateTime`) USING BTREE,\n" +
                    "   KEY `IX_CustomMessageSendHistory_pdt` (`PushDateTime`) USING BTREE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
                System.out.println(sql);
                System.out.println();
            }
        }
    }

    @Test
    public void testSplitCollection() {
       // Collection<Collection<Long>> list = Utils.split(Lists.newArrayList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L), 3);
        Collection<Collection<Long>> list = Utils.split(null, 3);
        System.out.println(list);
    }

    @Test
    public void testGenerateAPNsAppKeyAndAppSecret() {
        System.out.println("测试环境");
        System.out.println("appid: " + Utils.generateFixedStr(32));
        System.out.println("appkey: " + Utils.generateFixedStr(32));
        System.out.println("appsecret: " + Utils.generateFixedStr(32));
/*        System.out.println("验证环境");
        System.out.println("appid: " + Utils.generateFixedStr(32));
        System.out.println("appkey: " + Utils.generateFixedStr(32));
        System.out.println("appsecret: " + Utils.generateFixedStr(32));*/
        System.out.println("生产环境");
        System.out.println("appid: " + Utils.generateFixedStr(32));
        System.out.println("appkey: " + Utils.generateFixedStr(32));
        System.out.println("appsecret: " + Utils.generateFixedStr(32));
    }

    @Test
    public void testMD5_password() {
        System.out.println(Utils.md5("123465@123465"));
    }


    @Test
    public void testAppSign() {
        String appKey = "b8a02a6c74b9440ea80b04e436fa2d72";
        String appSecret = "0d2f6b56f31e4d4c92ae203d796a3b5b";
        Long timestamp = org.joda.time.Instant.now().getMillis();
        String appSign = Utils.md5(appKey + timestamp + appSecret);
        System.out.println("hjauth-appkey: " + appKey);
        System.out.println("hjauth-timestamp: " + timestamp);
        System.out.println("hjauth-appsign: " + appSign);
    }

    @Test
    public void testAppSign2() {
        String appKey = "b8a02a6c74b9440ea80b04e436fa2d72";
        String appSecret = "0d2f6b56f31e4d4c92ae203d796a3b5b";
        Long timestamp = 1L;
        String appSign = Utils.md5(appKey + timestamp + appSecret);
        System.out.println("hjauth-appkey: " + appKey);
        System.out.println("hjauth-timestamp: " + timestamp);
        System.out.println("hjauth-appsign: " + appSign);
    }

    @Test
    public void testGenHJAuth() {
        System.out.println("测试环境");
        System.out.println("appkey: " + Utils.generateFixedStr(32));
        System.out.println("appsecret: " + Utils.generateFixedStr(32));
        System.out.println("验证环境");
        System.out.println("appkey: " + Utils.generateFixedStr(32));
        System.out.println("appsecret: " + Utils.generateFixedStr(32));
        System.out.println("生产环境");
        System.out.println("appkey: " + Utils.generateFixedStr(32));
        System.out.println("appsecret: " + Utils.generateFixedStr(32));
    }

    @Test
    public void testDeviceInfoSign() {
        String appKey = "0978c0545c6d8f6bfccde1b5aa063884";
        String nonce="3724";
        Long ts= new Date().getTime();
        String appSecret="36b1b6c624cb421c87da2dd8b6fe7e34";
        System.out.println(ts);
        System.out.println(Utils.md5(appKey + "&" + nonce + "&" + ts  + "&" + appSecret));
    }

    @Test
    public void testGenUUID2File() throws Exception {
        //String str = StreamUtils.copyToString(new ClassPathResource("uuid.txt").getInputStream(), StandardCharsets.UTF_8);

        File file = new File("uuid.txt");
        OutputStream output = new FileOutputStream(file, true);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            builder.append("\"").append(UUID.randomUUID().toString()).append("\",").append("\n");
        }

        IOUtils.write(builder.toString(), output, StandardCharsets.UTF_8);
    }

    @Test
    public void testGenOpenId2File() throws Exception {
        File file = new File("openid.json");

        OutputStream output = new FileOutputStream(file, true);
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < 2000000; i++) {
           builder.append("\"").append(Utils.generateFixedStr(28)).append("\",").append("\n");
           // builder.append("\"").append(Utils.generateFixedStr(28)).append("\",");
        }
        builder.append("]");

        IOUtils.write(builder.toString(), output, StandardCharsets.UTF_8);
    }

    @Test
    public void testReadFile2Str()  throws Exception {
        String str = StreamUtils.copyToString(new ClassPathResource("openid.json").getInputStream(), StandardCharsets.UTF_8);
        System.out.println(str);
    }
    @Test
    public void testReadFile2Str_2()  throws Exception {
        File file = new File("src/test/resources/data/wx_message_hjid.json");
        String str = FileUtils.readFileToString(file, "UTF-8");
        System.out.println(str);
    }

    @Test
    public void testWriteStr2File()  throws Exception {
        FileUtils.writeStringToFile(new File(""), "", false);
    }

    @Test
    public void testPasswordDecode() {
        System.out.println(Utils.passwordDecode("Hk4QNQxjKLGwuC0xaloRBhmfzTjubPfai9gYO0wTjK0vJH9SJcSFwHRi115oSPg3V1FHF9uVNjA1vPNbkcj88Q=="));
//        System.out.println(Utils.passwordDecode("LyzYAGUV44HCI+fBTy5CqCkM7w5Sv/YiRgHfxVHkKwtuL5s2e/I3SRy2O7chx15YBLJtqp0+JSdQQsyJlB7JzA=="));
//        System.out.println(Utils.passwordDecode("JpXerYTqhOlmvoB8Y6ShF53QBdwSNLFaFseUewOnDFK9yEX4C7nb9QzQHK9EJvSdLMgZ0gA7Dytcvc9OgUD9Cw=="));
        System.out.println(Utils.passwordDecode("fAX2nugHop5Mf6ki4KMXBMITnHLKHsODTxYS4lJC1JXE1q5S3yfvkE43+J2E0OuxfVWUmkQpDXF7T49UvBtzuw=="));
    }

    @Test
    public void testGenWXAppSecret()  throws Exception {
        System.out.println(UUID.randomUUID().toString().toUpperCase());
    }

    @Test
    public void testGenPush_Schema() {
        String[] apps = new String[]{"hjdict", "hjcichang"};
        String year = "2018";
        for (String app : apps) {
            for (int i = 5; i <= 12; i++) {
                String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                String sql = "DROP TABLE IF EXISTS `push_task_" + app + "_" + year + month + "`;\n"
                    + "CREATE TABLE `push_task_" + app + "_" + year + month + "` (\n"
                    + "  `id` bigint unsigned NOT NULL AUTO_INCREMENT,\n"
                    + "  `msg_id` bigint NOT NULL COMMENT '消息ID',\n"
                    + "  `app_id` varchar(32) NOT NULL COMMENT '应用ID',\n"
                    +"  `audience_type` varchar(32) DEFAULT NULL COMMENT '推送类型(all, tag, alisa, user, device, token)',\n"
                    + "  `payload` json NOT NULL COMMENT '请求体',\n"
                    + "  `audience` json NOT NULL COMMENT '接收者',\n"
                    + "  `notification` json NOT NULL COMMENT '通知体',\n"
                    + "  `platform` varchar(200) NOT NULL COMMENT '平台',\n"
                    + "  `options` json DEFAULT NULL COMMENT '可选参数',\n"
                    + "  `status` int NOT NULL COMMENT '消息状态(1创建，2处理中，3失败，4结束且部分成功，5成功)',\n"
                    + "  `total_count` int NOT NULL COMMENT '消息总量',\n"
                    + "  `stage` varchar(1000) DEFAULT NULL COMMENT '消息状态详情',\n"
                    + "  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间，字段变化时自动更新时间',\n"
                    + "  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，记录插入时自动插入时间',\n"
                    + "  PRIMARY KEY (`id`),\n"
                    + "  KEY `idx_msg_id` (`msg_id`) USING BTREE\n"
                    + ") ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;";
                System.out.println(sql);
                System.out.println();
            }
        }
    }

    @Test
    public void testGenUUID() {
        System.out.println(Utils.generateFixedStr(10));
        System.out.println(new Date().getTime() + ""+new Random().nextInt(9999));
        System.out.println(System.currentTimeMillis());
    }


    public enum SendType {
        ALL(SendType.TYPE_ALL),
        TAG(SendType.TYPE_TAG),
        ALIAS(SendType.TYPE_ALIAS),
        USER(SendType.TYPE_USER),
        DEVICE(SendType.TYPE_DEVICE),
        TOKEN(SendType.TYPE_TOKEN);

        public static final String TYPE_ALL = "all";
        public static final String TYPE_TAG = "tag";
        public static final String TYPE_ALIAS = "alias";
        public static final String TYPE_USER = "user";
        public static final String TYPE_DEVICE =  "device";
        public static final String TYPE_TOKEN = "token";

        private String value;

        SendType(String value) {
            this.value = value;
        }

        public String value() {
            return this.value;
        }

        public static boolean contains(String value) {
            return value != null && Arrays.stream(values()).anyMatch(s -> Objects.equals(s.value, value));
        }

        public static SendType convert(String value) {
            return Stream.of(values()).filter(e -> Objects.equals(e.value, value)).findAny().orElse(null);
        }

    }

    @Test
    public void testEnumSerializable() {
        System.out.println(JsonUtil.object2JSON(SendType.values()));
    }

    @Test
    public void testMd5() {
        System.out.println(Utils.md5("0cbf23ace99a4956b6bd5c057fe3ab16" + "1" + "385280f04c954a8582bd451822b78719"));
    }

    @Test
    public void testJsonUtil() {
        String[] platform = {"ios"};
        System.out.println(JsonUtil.object2JSON(platform));
    }

    @Test
    public void testGenAPNsSign_qa_depressed() {
       // System.out.println(Utils.md5("0cbf23ace99a4956b6bd5c057fe3ab16" + "&" +1L + "&" + "385280f04c954a8582bd451822b78719"));
       // System.out.println(Utils.md5("cde5ca2b01e44802bcc14071c39c0efe" + "&" +1L + "&" + "dbec5cb6f0ab46d6be4affc30a66a6dc"));
       // System.out.println(Utils.md5("0978c0545c6d8f6bfccde1b5aa063884" + "&" +1L + "&" + "36b1b6c624cb421c87da2dd8b6fe7e34"));

       // HashFunction md5 = Hashing.md5();

        //System.out.println(md5.hashString("0978c0545c6d8f6bfccde1b5aa063884" + "&" +1L + "&" + "36b1b6c624cb421c87da2dd8b6fe7e34", Charsets.UTF_8).toString());

        String appKey = "cde5ca2b01e44802bcc14071c39c0efe";
        String appSecret = "dbec5cb6f0ab46d6be4affc30a66a6dc";
       // Long ts = new Date().getTime();
        Long ts = 1528256482941L;
        System.out.println(ts);
        System.out.println(Utils.md5(appKey + "&" + ts + "&" + appSecret));
    }

    @Test
    public void testGenAPNsSign_QA() {
        //cctalk
        String appKey = "71c8b4d984e8406387d7dc7adab37384";
        String appSecret = "8de4277c0d93405aa8b01c3e82036864";
        System.out.println(Utils.md5(appKey + "&" +1L + "&" + appSecret));
    }

    @Test
    public void testGenAPNsSign_PROD() {
        //五十音图
        String appKey = "497f3192a317dce8fd1faaa94f223012";
        String appSecret = "8a8ee9facd0e71aba472013db43a558b";
        System.out.println(Utils.md5(appKey + "&" +1L + "&" + appSecret));
    }

    @Test
    public void test_device_info_group_by() {
        for (int i = 0; i < 64; i++) {
            System.out.println("select max(count) from (select count(user_id) as count, user_id from device_info_by_uid_" + i + " group by user_id) as a;");
        }
    }


    @Test
    public void testGenSQL() {
        StringBuilder sql = new StringBuilder();

        for (int i = 0; i < 64; i ++) {
            sql.append("(select user_id, ',' from device_info_by_uid_" + i + " limit 20)");
            sql.append(" union all ");
        }

        System.out.println(sql.toString());
    }


    @Test
    public void test() throws Exception{
        //com.hujiang.cctalk
        byte[] bytes = ("1f840803f4fc7f31d670d05e" + ":" + "c03a28b4f86b564f29c7cb67").getBytes("UTF-8");
        System.out.println(Base64.encodeBase64String(bytes));
    }

    @Test
    public void test2() throws Exception{
        //com.com.hujiang.hjm.wordplus
        byte[] bytes = ("808f08d194e98fb2de439a25" + ":" + "8b708a85ad61f25f88f7b398").getBytes("UTF-8");
        System.out.println(Base64.encodeBase64String(bytes));
    }

    @Test
    public void test3() throws Exception{
        //com.hjclass.hujiangclass
        byte[] bytes = ("672a073c7ab9e9de75527201" + ":" + "4c42de0dd949aa59002d24a6").getBytes("UTF-8");
        System.out.println(Base64.encodeBase64String(bytes));
    }

    @Test
    public void test4() throws Exception{
        //com.hujiang.hjm.cctalk
        byte[] bytes = ("84e031d3bf7523509fdd6dd4" + ":" + "1766bf48183481e82c501ccd").getBytes("UTF-8");
        System.out.println(Base64.encodeBase64String(bytes));
    }

    @Test
    public void testGenBeaconSign() {
        //System.out.println(Utils.md5("hj-nc-app-key=7FF5R9ZaN4J711938&hj-nc-random-str=yangkai&hj-nc-app-secret=$apr1$klyxTo$vBeZaeTgxVNFaOEoYE5h8/"));
        //System.out.println(Utils.md5("hj-nc-app-key=18AYY21cXL8E974298&hj-nc-random-str=yangkai&hj-nc-app-secret=$apr1$nCDV7a$HHXxJtHAtf6NwMFo9h10pd"));
        //System.out.println(Utils.md5("hj-nc-app-key=20YxSeoXD4oA016266&hj-nc-random-str=yangkai&hj-nc-app-secret=$apr1$eMIVA8$beIO4k3FZ5ctN7Y7atqqE1"));
        //System.out.println(Utils.md5("hj-nc-app-key=8ivymWyUd12458844&hj-nc-random-str=yangkai&hj-nc-app-secret=$apr1$tfSJPT$5df2FisgBeo5tkDMLfHkv1"));
        System.out.println(Utils.md5("hj-nc-app-key=10nx62mh2XVc631923&hj-nc-random-str=yangkai&hj-nc-app-secret=$apr1$hXRqFS$kAjtUSz81fv9sAXLibkDUh"));
    }

    @Test
    public void testGenUid() {
        String prefix = "3346";
        StringBuilder builder = new StringBuilder();
        for (int i =1000; i <= 2000 ;i ++) {
            builder.append(prefix).append(i).append(",");
        }
        System.out.println(builder.toString());
    }

    @Test
    public void test_wx_ip_whitelist() {
        //String source = "invalid ip 172.20.10.2, not in whitelist hint: [IlO.Ba07451527]";
        String source = "xxx56.99.11.88, not in whitelist hint: [IlO.Ba07451527]";
        //String source = "invalid ip 172.20.10.2, ";
        //String source = "172.20.10.2";
        String regEx = "(2(5[0-5]{1}|[0-4]\\d{1})|[0-1]?\\d{1,2})(\\.(2(5[0-5]{1}|[0-4]\\d{1})|[0-1]?\\d{1,2})){3}";
       // String regEx = "invalid ip ((:?\\d{1,3}\\.){3}\\d{1,3})";
        //String regEx = "invalid ip ((?:\\d{1,3}\\.){3}\\d{1,3})";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regEx);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(source);
        // 字符串是否与正则表达式相匹配

        if(matcher.find()) {
            System.out.println(matcher.group(0));
        }
    }


    @Test
    public void test_gen_wilson_schema() {
        for (int i = 0; i < 128; i++) {
            System.out.println("DROP TABLE IF EXISTS `inbox_message_" + i + "`;\n"
                + "CREATE TABLE `inbox_message_" + i + "`\n"
                + "(\n"
                + "    `inbox_msg_id` bigint(22) unsigned NOT NULL,\n"
                + "    `msg_batch_id` bigint(18)          NOT NULL COMMENT '批次号',\n"
                + "    `msg_id`       bigint(22)          NOT NULL COMMENT 'message表主键',\n"
                + "    `user_id`      int(11)             NOT NULL,\n"
                + "    `status`       tinyint(1)          NOT NULL COMMENT '1. unread  2. read  3. deleted',\n"
                + "    `created_at`   datetime DEFAULT CURRENT_TIMESTAMP,\n"
                + "    `updated_at`   datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n"
                + "    PRIMARY KEY (`inbox_msg_id`),\n"
                + "    KEY `idx_a_b_id` (`msg_batch_id`) USING BTREE,\n"
                + "    KEY `idx_m_id` (`msg_id`) USING BTREE,\n"
                + "    KEY `idx_u_id` (`user_id`) USING BTREE\n"
                + ") ENGINE = InnoDB\n"
                + "    AUTO_INCREMENT = 1\n"
                + "    DEFAULT CHARSET = utf8mb4 COMMENT ='用户消息';");
        }
    public void test_JsonUtil_md5() {
        String s1 = "{\"keyword1\":{\"color\":\"#173177\",\"value\":\"巧克力\"},\"keyword2\":{\"color\":\"#173177\",\"value\":\"39.8元\"},\"remark\":{\"color\":\"#173177\",\"value\":\"20190225_172534_478360\"},\"first\":{\"color\":\"#173177\",\"value\":\"恭喜你购买成功！20190225_172534_478360.\"}}";
        String s2 = "{\"keyword1\":{\"color\":\"#173177\",\"value\":\"巧克力\"},\"keyword2\":{\"color\":\"#173177\",\"value\":\"39.8元\"},\"remark\":{\"color\":\"#173177\",\"value\":\"20190225_172425_525081\"},\"first\":{\"color\":\"#173177\",\"value\":\"恭喜你购买成功！20190225_172425_525081.\"}}";
        String s3 = "{\"keyword1\":{\"color\":\"#173177\",\"value\":\"巧克力\"},\"keyword2\":{\"color\":\"#173177\",\"value\":\"39.8元\"},\"remark\":{\"color\":\"#173177\",\"value\":\"20190225_172241_401753\"},\"first\":{\"color\":\"#173177\",\"value\":\"恭喜你购买成功！20190225_172241_401753.\"}}";
        System.out.println(MD5.encryptMD5(s1));
        System.out.println(MD5.encryptMD5(s2));
        System.out.println(MD5.encryptMD5(s3));
    }

    @Test
    public void testComparator() {
        List<Integer> list = Lists.newArrayList(1,3,5,2,4,9,6,7,8);
        List<Integer> list1 =list.stream().sorted(Comparator.comparingInt((o) -> o)).collect(Collectors.toList());
        List<Integer> list2 =list.stream().sorted((o1, o2) -> {
            if (o1 > o2) {
                return 1;
            } else if (o1 < o2) {
                return -1;
            } else {
                return 0;
            }
        }).collect(Collectors.toList());

        System.out.println(list1);
        System.out.println(list2);

    }

    @Data
    @AllArgsConstructor
    public static class Template {
        private String title;
        private Integer order;
        private Boolean isMust;

        @Override
        public String toString() {
            return JsonUtil.object2JSON(this);
        }
    }

    @Test
    public void testComparator2() {
        List<Template> list = Lists.newArrayList(
            new Template("必发", 1, true),
            new Template("必发", 4, true),
            new Template("补发", 2, false),
            new Template("补发", 3, false)
        );

        List<Template> list1 = list.stream()
            .sorted((o1, o2) -> Integer.compare(o1.getOrder(), o2.getOrder()))
            .sorted((o1, o2) -> Boolean.compare(o2.getIsMust(), o1.getIsMust()))
            .collect(Collectors.toList());
        System.out.println(list1);
    }

    @Test
    public void testGenNotifyCenterV2Sign_Class_PROD() {
        System.out.println(Utils.md5("hj-nc-app-key=10D7btwYC1XB473323&hj-nc-random-str=yangkai&hj-nc-app-secret=$apr1$chDUXf$KrH7YUzRp0Fh2eeqL7zN5m"));

        System.out.println(Utils.md5("hj-nc-app-key=10r28oprn2UV519684&hj-nc-random-str=yangkai&hj-nc-app-secret=$apr1$i7A0cz$QV936nX7glEYezbT1olmM0"));
    }

    @Test
    public void testGenNotifyCenterV2Sign_Class_QA() {
        //网校
        System.out.println(Utils.md5("hj-nc-app-key=20YxSeoXD4oA016266&hj-nc-random-str=yangkai&hj-nc-app-secret=$apr1$eMIVA8$beIO4k3FZ5ctN7Y7atqqE1"));
    }


    @Test
    public void testGenCCTalkYearAllDays_Schema() throws Exception {
        String start = "2020_01_01";
        String end = "2021_01_01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        Date dBegin = sdf.parse(start);
        Date dEnd = sdf.parse(end);
        List<Date> lDate = findDates(dBegin, dEnd);
        for (Date date : lDate) {
            String prefix = sdf.format(date);
            System.out.println("CREATE TABLE `push_task_send_history_cctalk_" + prefix + "` (\n"
                + "  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,\n"
                + "  `msg_id` bigint(20) NOT NULL COMMENT '消息ID',\n"
                + "  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',\n"
                + "  `device_id` varchar(64) DEFAULT NULL COMMENT '设备ID',\n"
                + "  `device_token` varchar(128) DEFAULT NULL COMMENT '设备推送token',\n"
                + "  `app_id` varchar(32) DEFAULT NULL COMMENT '应用ID',\n"
                + "  `app_category` varchar(32) DEFAULT NULL COMMENT '产线',\n"
                + "  `app_name` varchar(32) DEFAULT NULL COMMENT '应用名称',\n"
                + "  `audience_type` varchar(32) DEFAULT NULL COMMENT '推送类型(all, tag, alisa, user, device, token)',\n"
                + "  `content` json DEFAULT NULL COMMENT '推送内容',\n"
                + "  `success` tinyint(1) DEFAULT NULL COMMENT '发送结果（成功: 0, 失败: 1）',\n"
                + "  `code` int(11) DEFAULT NULL COMMENT '返回码',\n"
                + "  `msg` varchar(100) DEFAULT NULL COMMENT '返回码信息',\n"
                + "  `result` json DEFAULT NULL COMMENT 'APNs返回信息',\n"
                + "  `gmt_update` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间，字段变化时自动更新时间',\n"
                + "  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '记录创建时间，记录插入时自动插入时间',\n"
                + "  PRIMARY KEY (`id`),\n"
                + "  KEY `idx_msg_id` (`msg_id`) USING BTREE,\n"
                + "  KEY `idx_user_id` (`user_id`) USING BTREE,\n"
                + "  KEY `idx_device_id` (`device_id`) USING BTREE,\n"
                + "  KEY `idx_ct` (`gmt_create`)\n"
                + ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;\n");
        }

    }

    public List<Date> findDates(Date dBegin, Date dEnd) {
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }

    @Test
    public void genTtsRequest4WX() {
        String appId = "VIP";
        String appSecret = "91DF02CD9BA8404E811B2D5AB2120AAC";
        Long timestamp = new Date().getTime();
        Long expireTime = timestamp + 60 * 60 * 1000L;
        String batchId = appId + "-" + timestamp + "-" + "00001";
        String sign = MD5.encryptMD5(appId + appSecret + batchId);

        JSONObject params = new JSONObject();
        params
            .fluentPut("appId", appId)
            .fluentPut("appSecret", appSecret)
            .fluentPut("batchId", batchId)
            .fluentPut("sign", sign)
            .fluentPut("expireTime", expireTime)
            .fluentPut("tmpParams", new JSONObject().fluentPut("phone", "13166016298"))
            .fluentPut("voiceTmpId", "1");
        System.out.println(params);
    }

    @Test
    public void genTtsRequest4CC_QA() {
        String appId = "cctalk";
        String appSecret = "3B93AD70-68FC-48C0-876C-8F2BA7BE85E9";
        Long timestamp = new Date().getTime();
        Long expireTime = timestamp + 60 * 60 * 1000L;
        String batchId = appId + "-" + timestamp + "-" + "00001";
        String sign = MD5.encryptMD5(appId + appSecret + batchId);

        JSONObject params = new JSONObject();
        params
            .fluentPut("appId", appId)
            .fluentPut("appSecret", appSecret)
            .fluentPut("batchId", batchId)
            .fluentPut("sign", sign)
            .fluentPut("expireTime", expireTime)
            .fluentPut("tmpParams", new JSONObject().fluentPut("phone", "13166016298"))
            .fluentPut("voiceTmpId", "1");
        System.out.println(params);
    }

    @Test
    public void genTtsRequest4CC_PROD() {
        String appId = "cctalk";
        String appSecret = "3B93AD70-68FC-48C0-876C-8F2BA7BE85E9";
        Long timestamp = new Date().getTime();
        Long expireTime = timestamp + 60 * 60 * 1000L;
        String batchId = appId + "-" + timestamp + "-" + "00001";
        String sign = MD5.encryptMD5(appId + appSecret + batchId);

        JSONObject params = new JSONObject();
        params
            .fluentPut("appId", appId)
            .fluentPut("appSecret", appSecret)
            .fluentPut("batchId", batchId)
            .fluentPut("sign", sign)
            .fluentPut("expireTime", expireTime)
            .fluentPut("tmpParams", new JSONObject().fluentPut("phone", "13166016298"))
            .fluentPut("voiceTmpId", "3");
        System.out.println(params);
    }

    @Test
    public void genTtsRequest4CC_with_patams() {
        String appId = "cctalk";
        String appSecret = "3B93AD70-68FC-48C0-876C-8F2BA7BE85E9";
        Long timestamp = new Date().getTime();
        Long expireTime = timestamp + 60 * 60 * 1000L;
        String batchId = appId + "-" + timestamp + "-" + "00001";
        String sign = MD5.encryptMD5(appId + appSecret + batchId);

        JSONObject params = new JSONObject();
        params
            .fluentPut("appId", appId)
            .fluentPut("appSecret", appSecret)
            .fluentPut("batchId", batchId)
            .fluentPut("sign", sign)
            .fluentPut("expireTime", expireTime)
            .fluentPut("tmpParams", new JSONObject().fluentPut("phone", "13166016298").fluentPut("moduleParams", new JSONObject().fluentPut("name", "yangkai")))
            .fluentPut("voiceTmpId", "1");
        System.out.println(params);
    }



    @Test
    public void test_md5() {
        HashFunction md5 = Hashing.md5();
        System.out.println(md5.hashString("yk", Charsets.UTF_8).toString());
        System.out.println(MD5.encryptMD5("yk"));


    }

    @Test
    public void testGenApnsPushTaskShardingTable() {
        String[] apps = new String[] {"hjwushiyin"};
        String[] years = new String[] {"2020", "2021", "2022"};
        for (String app : apps) {
            for (String year : years) {
                for (int i = 1; i <= 12; i++) {
                    String month = i <= 9 ? "0" + String.valueOf(i) : String.valueOf(i);
                    String sql = "CREATE TABLE `push_task_"+app+"_" + year + month + "` (\n"
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
                        + "  KEY `idx_msg_id` (`msg_id`) USING BTREE,\n"
                        + "  KEY `idx_app_id` (`app_id`) USING BTREE\n"
                        + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
                    System.out.println(sql);
                    System.out.println();
                }
            }
        }

    }


    @Test
    public void testGenWilsonDML() {
        for (int i = 0 ;i< 128;i++) {
            //System.out.println("ALTER TABLE `hj_wilson`.`inbox_message_"+i+"` \n" + "ADD COLUMN `abs_msg_app_id` int(11) NOT NULL COMMENT '应用ID' AFTER `msg_batch_id`;");

            //System.out.println("update `hj_wilson`.`inbox_message` set abs_msg_app_id = 1024;");
            System.out.println("delete from `hj_wilson`.`inbox_message_"+i+"` where inbox_msg_id > 0;");

        }
    }

    @Test
    public void testGenWilsonDDL() {
        for (int i = 0 ;i< 128;i++) {
            System.out.println("DROP TABLE IF EXISTS `inbox_message_"+i+"`;\n"
                + "CREATE TABLE `inbox_message_"+i+"`\n"
                + "(\n"
                + "    `inbox_msg_id`   bigint(22) unsigned NOT NULL,\n"
                + "    `msg_batch_id`   bigint(18)          NOT NULL COMMENT '批次号',\n"
                + "    `abs_msg_app_id` int(11)  NOT NULL COMMENT 'message app id',\n"
                + "    `msg_id`         bigint(22)          NOT NULL COMMENT 'message表主键',\n"
                + "    `user_id`        int(11)             NOT NULL,\n"
                + "    `status`         tinyint(1)          NOT NULL COMMENT '1. unread  2. read  3. deleted',\n"
                + "    `created_at`     datetime DEFAULT CURRENT_TIMESTAMP,\n"
                + "    `updated_at`     datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n"
                + "    PRIMARY KEY (`inbox_msg_id`),\n"
                + "    KEY `idx_m_id` (`msg_id`) USING BTREE,\n"
                + "    KEY `idx_u_id` (`user_id`) USING BTREE\n"
                + ") ENGINE = InnoDB\n"
                + "  DEFAULT CHARSET = utf8mb4 COMMENT ='用户inbox消息';\n");
        }
    }


    @Test
    public void testWilsonDDL() {
        for (int i = 0; i<128; i++) {
            //System.out.println("ALTER TABLE `hj_wilson`.`inbox_message_"+i+"` \nADD COLUMN `sent_at` datetime(0) NULL COMMENT 'refer to message.sent_at' AFTER `status`;");
            //System.out.println("delete from inbox_message_"+i+";");
            System.out.println("ALTER TABLE `hj_wilson`.`inbox_message_"+i +"` \n"
                + "ADD COLUMN `deliver_type` varchar(16) NOT NULL COMMENT '发送的消息类型，direct:单点，topic:批量发送，broadcast:全量发送' AFTER `abs_msg_app_id`;");
        }
    }

    @Test
    public void testWilsonSchema() {
        for (int i = 0; i<128; i++) {
            System.out.println("DROP TABLE IF EXISTS `inbox_message_"+i+"`;\n"
                + "CREATE TABLE `inbox_message_"+i+"`\n"
                + "(\n"
                + "    `inbox_msg_id`   bigint(22) unsigned NOT NULL,\n"
                + "    `msg_batch_id`   bigint(18)          NOT NULL COMMENT '批次号',\n"
                + "    `abs_msg_app_id` int(11)             NOT NULL COMMENT 'message app id',\n"
                + "    `deliver_type`   varchar(16) DEFAULT NULL COMMENT '发送的消息类型，direct:单点，topic:批量发送，broadcast:全量发送',\n"
                + "    `msg_id`         bigint(22)          NOT NULL COMMENT 'message表主键',\n"
                + "    `user_id`        int(11)             NOT NULL,\n"
                + "    `status`         tinyint(1)          NOT NULL COMMENT '1. unread  2. read  3. deleted',\n"
                + "    `sent_at`        datetime            NOT NULL COMMENT 'refer to message.sent_at',\n"
                + "    `created_at`     datetime    DEFAULT CURRENT_TIMESTAMP,\n"
                + "    `updated_at`     datetime    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n"
                + "    PRIMARY KEY (`inbox_msg_id`),\n"
                + "    KEY `idx_m_id` (`msg_id`) USING BTREE,\n"
                + "    KEY `idx_u_id` (`user_id`) USING BTREE,\n"
                + "    KEY `idx_s_a` (`sent_at`) USING BTREE\n"
                + ") ENGINE = InnoDB\n"
                + "  DEFAULT CHARSET = utf8mb4 COMMENT ='用户inbox消息';");
        }

>>>>>>> 18a1a0b96de68c2a872ed43e827b7bb0b05a6359
    }


}
