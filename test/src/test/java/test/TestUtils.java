package test;

import com.hujiang.basic.framework.core.util.JsonUtil;

import com.google.common.collect.Sets;
import com.training.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
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
        System.out.println(Utils.md5("123465@123465"));
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
    public void testGenWechat_Schema() {
        String year = "2018";
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

    @Test
    public void testSplitCollection() {
       // Collection<Collection<Long>> list = Utils.split(Lists.newArrayList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L), 3);
        Collection<Collection<Long>> list = Utils.split(null, 3);
        System.out.println(list);
    }

    @Test
    public void testGenAppKeyAndAppSecret() {
        System.out.println("测试环境");
        System.out.println("appid: " + Utils.generateFixedStr(32));
        System.out.println("appkey: " + Utils.generateFixedStr(32));
        System.out.println("appsecret: " + Utils.generateFixedStr(32));
        System.out.println("验证&生产环境");
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
        System.out.println(Utils.passwordDecode("V8SzZve8kt8DN1GCsSvme35G4+tMq65zL8NBkfCs0ZiLOM+lvTNCVpWk2/l82Fp5KqHtQ/yF2QUujWu/HBURKQ=="));
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
    public void testIosPushSign() {
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
    public void testIosPushSign2() {
         System.out.println(Utils.md5("0cbf23ace99a4956b6bd5c057fe3ab16" + "&" +1L + "&" + "385280f04c954a8582bd451822b78719"));
         System.out.println(Utils.md5("cde5ca2b01e44802bcc14071c39c0efe" + "&" +1L + "&" + "dbec5cb6f0ab46d6be4affc30a66a6dc"));
         System.out.println(Utils.md5("0978c0545c6d8f6bfccde1b5aa063884" + "&" +1L + "&" + "36b1b6c624cb421c87da2dd8b6fe7e34"));

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

}
