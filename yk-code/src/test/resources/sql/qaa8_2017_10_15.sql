/*
 Navicat Premium Data Transfer

 Source Server         : DSP(QA)
 Source Server Type    : MySQL
 Source Server Version : 50633
 Source Host           : 192.168.36.92
 Source Database       : qaa8

 Target Server Type    : MySQL
 Target Server Version : 50633
 File Encoding         : utf-8

 Date: 10/15/2017 21:32:35 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `ad_campaign_area_report`
-- ----------------------------
DROP TABLE IF EXISTS `ad_campaign_area_report`;
CREATE TABLE `ad_campaign_area_report` (
  `day` date DEFAULT NULL COMMENT '日期 yyyy-MM-dd',
  `campaign_id` int(11) DEFAULT NULL COMMENT '活动ID',
  `strategy_id` int(11) DEFAULT NULL COMMENT '策略ID',
  `creative_id` int(11) DEFAULT NULL COMMENT '创意ID',
  `requests` bigint(20) DEFAULT NULL COMMENT '请求数',
  `impressions` bigint(20) DEFAULT NULL COMMENT '展示数',
  `clicks` bigint(20) DEFAULT NULL COMMENT '点击数',
  `cost` decimal(10,5) DEFAULT NULL COMMENT '花费',
  `uv` bigint(20) DEFAULT NULL COMMENT '访客数',
  `pv` bigint(20) DEFAULT NULL COMMENT '网站浏览量',
  `session_cnt` bigint(20) DEFAULT NULL COMMENT '访问数',
  `no_bounce_session_cnt` bigint(20) DEFAULT NULL COMMENT '二跳访问数',
  `duration` bigint(20) DEFAULT NULL COMMENT '总的页面停留时长',
  `order_cnt` bigint(20) DEFAULT NULL COMMENT '实付人次',
  `order_income` decimal(10,5) DEFAULT NULL COMMENT '实付金额',
  `reg_cnt` bigint(20) DEFAULT NULL COMMENT '注册量',
  `install_app_cnt` bigint(20) DEFAULT NULL COMMENT 'APP激活打开',
  `province` varchar(50) DEFAULT NULL COMMENT '省',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  KEY `index_day` (`day`),
  KEY `index_campaign_id` (`campaign_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_campaign_freq_report`
-- ----------------------------
DROP TABLE IF EXISTS `ad_campaign_freq_report`;
CREATE TABLE `ad_campaign_freq_report` (
  `day` date DEFAULT NULL COMMENT '日期 yyyy-MM-dd',
  `campaign_id` int(11) DEFAULT NULL COMMENT '活动ID',
  `freq` int(11) DEFAULT NULL COMMENT '频次',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  KEY `index_day` (`day`),
  KEY `index_campaign_id` (`campaign_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_campaign_frequency`
-- ----------------------------
DROP TABLE IF EXISTS `ad_campaign_frequency`;
CREATE TABLE `ad_campaign_frequency` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_campaign_id` int(11) DEFAULT NULL COMMENT '广告活动id',
  `freq_unit` tinyint(4) DEFAULT NULL COMMENT '频率单位,1日、2周、3月、4活动周期',
  `max_times` int(11) DEFAULT NULL COMMENT '每个用户最多展示次数',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_campaign_freq` (`ad_campaign_id`,`freq_unit`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=586 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告活动频控表';

-- ----------------------------
--  Table structure for `ad_campaign_info`
-- ----------------------------
DROP TABLE IF EXISTS `ad_campaign_info`;
CREATE TABLE `ad_campaign_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_campaign_name` varchar(50) DEFAULT NULL COMMENT '广告活动名称',
  `customer_id` int(11) DEFAULT NULL COMMENT '客户id，来自客户表',
  `customer_name` varchar(50) DEFAULT NULL COMMENT '客户名称，来自客户表',
  `start_date` datetime DEFAULT NULL COMMENT '活动开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '活动结束时间',
  `memo` varchar(200) DEFAULT NULL COMMENT '客户名称，来自客户表',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  KEY `idx_ad_campaign` (`ad_campaign_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1231 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告活动表';

-- ----------------------------
--  Table structure for `ad_campaign_limit_flow`
-- ----------------------------
DROP TABLE IF EXISTS `ad_campaign_limit_flow`;
CREATE TABLE `ad_campaign_limit_flow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_campaign_id` int(11) DEFAULT NULL,
  `limit_type` tinyint(4) DEFAULT NULL COMMENT '1 预算 2 展示 3 点击',
  `limit_total_count` double(20,5) DEFAULT NULL COMMENT '控制总量',
  `limit_day_count` double(20,5) DEFAULT NULL COMMENT '每日控制的量',
  `creator` int(11) DEFAULT NULL COMMENT '创建人，沪江数字id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `status` int(11) DEFAULT '1' COMMENT '1: 启用, 默认1,2: 删除,3: 禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=238 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_campaign_report`
-- ----------------------------
DROP TABLE IF EXISTS `ad_campaign_report`;
CREATE TABLE `ad_campaign_report` (
  `day` date DEFAULT NULL COMMENT '日期 yyyy-MM-dd',
  `hour` int(11) DEFAULT NULL COMMENT '小时',
  `campaign_id` int(11) DEFAULT NULL COMMENT '活动ID',
  `strategy_id` int(11) DEFAULT NULL COMMENT '策略ID',
  `creative_id` int(11) DEFAULT NULL COMMENT '创意ID',
  `ad_type_id` int(11) DEFAULT NULL COMMENT '广告类型',
  `size` varchar(255) DEFAULT NULL COMMENT '广告位尺寸',
  `site_id` int(11) DEFAULT NULL COMMENT '网站ID',
  `channel_id` int(11) DEFAULT NULL COMMENT '频道ID',
  `adspace_id` int(11) DEFAULT NULL COMMENT '广告位ID',
  `requests` bigint(20) DEFAULT '0' COMMENT '请求数',
  `impressions` bigint(20) DEFAULT '0' COMMENT '展示数',
  `clicks` bigint(20) DEFAULT '0' COMMENT '点击数',
  `cost` decimal(10,5) DEFAULT NULL COMMENT '花费',
  `uv` bigint(20) DEFAULT NULL COMMENT '访客数',
  `pv` bigint(20) DEFAULT NULL COMMENT '网站浏览量',
  `session_cnt` bigint(20) DEFAULT NULL COMMENT '访问数',
  `no_bounce_session_cnt` bigint(20) DEFAULT NULL COMMENT '二跳访问数',
  `duration` bigint(20) DEFAULT NULL COMMENT '总的页面停留时长',
  `order_cnt` bigint(20) DEFAULT NULL COMMENT '实付人次',
  `order_income` decimal(10,5) DEFAULT NULL COMMENT '实付金额',
  `reg_cnt` bigint(20) DEFAULT NULL COMMENT '注册量',
  `install_app_cnt` bigint(20) DEFAULT NULL COMMENT 'APP激活打开',
  KEY `index_day` (`day`),
  KEY `index_campaign_id` (`campaign_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_campaign_superscript`
-- ----------------------------
DROP TABLE IF EXISTS `ad_campaign_superscript`;
CREATE TABLE `ad_campaign_superscript` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_campaign_id` int(11) DEFAULT NULL COMMENT '广告活动id',
  `superscript` tinyint(4) DEFAULT NULL COMMENT '广告角标标识：0 显示 1 不显示',
  `alignment` tinyint(4) DEFAULT NULL COMMENT '位置,1=左上 2=中上 3=右上 4=左中 5=居中 6=右中 7=左下 8=中下 9=右下',
  `creator` int(11) DEFAULT NULL COMMENT '创建人，沪江数字id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `status` int(11) DEFAULT '1' COMMENT '1: 启用, 默认1,2: 删除,3: 禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=117 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_content_standard`
-- ----------------------------
DROP TABLE IF EXISTS `ad_content_standard`;
CREATE TABLE `ad_content_standard` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_content_standard_name` varchar(255) DEFAULT NULL COMMENT '内容规范名称',
  `ad_content_template_id` int(11) DEFAULT NULL COMMENT '内容模板ID',
  `status` int(11) DEFAULT NULL COMMENT '1: 启用, 默认1,2: 删除,,3: 禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人，沪江数字id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1163 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_content_standard_element`
-- ----------------------------
DROP TABLE IF EXISTS `ad_content_standard_element`;
CREATE TABLE `ad_content_standard_element` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_content_standard_id` int(11) DEFAULT NULL COMMENT '内容规范id',
  `element_id` int(11) DEFAULT NULL COMMENT '元素id',
  `require` tinyint(4) DEFAULT NULL COMMENT '是否必须',
  `widthAndheight` varchar(255) DEFAULT NULL COMMENT '尺寸',
  `size` varchar(255) DEFAULT NULL COMMENT '大小',
  `length` int(11) DEFAULT NULL COMMENT '长度',
  `format` varchar(255) DEFAULT NULL COMMENT '格式，多格式用逗号分开',
  `creator` int(11) DEFAULT NULL COMMENT '创建人，沪江数字id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `status` int(11) DEFAULT '1' COMMENT '1: 启用, 默认1,2: 删除,,3: 禁用',
  `global` tinyint(1) DEFAULT NULL COMMENT '是否全局',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2760 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_content_template`
-- ----------------------------
DROP TABLE IF EXISTS `ad_content_template`;
CREATE TABLE `ad_content_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_content_template_name` varchar(255) DEFAULT NULL COMMENT '内容模板名称',
  `status` int(11) DEFAULT NULL COMMENT '1: 启用, 默认1,2: 删除,3: 禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人，沪江数字id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2674 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_content_template_element`
-- ----------------------------
DROP TABLE IF EXISTS `ad_content_template_element`;
CREATE TABLE `ad_content_template_element` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_content_template_id` int(11) DEFAULT NULL COMMENT '内容模板id',
  `element_name` varchar(255) DEFAULT NULL COMMENT '元素名称',
  `element_sign` varchar(255) DEFAULT NULL COMMENT '元素标识',
  `element_type` int(11) DEFAULT NULL COMMENT '元素类型',
  `creator` int(11) DEFAULT NULL COMMENT '创建人，沪江数字id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建人，沪江数字id',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `status` int(11) DEFAULT '1' COMMENT '1: 启用, 默认1,2: 删除,,3: 禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5632 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_creative_config`
-- ----------------------------
DROP TABLE IF EXISTS `ad_creative_config`;
CREATE TABLE `ad_creative_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_strategy_id` int(11) DEFAULT NULL COMMENT '广告策略id',
  `content_type` int(11) DEFAULT NULL COMMENT '元素id',
  `classification` varchar(255) DEFAULT NULL COMMENT '统一分类',
  `official_lable` varchar(255) DEFAULT NULL COMMENT '官方标签',
  `keyword` varchar(255) DEFAULT NULL COMMENT '大小',
  `creator` int(11) DEFAULT NULL COMMENT '创建人，沪江数字id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=568 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_creative_dynamic_source`
-- ----------------------------
DROP TABLE IF EXISTS `ad_creative_dynamic_source`;
CREATE TABLE `ad_creative_dynamic_source` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_creative_id` int(11) DEFAULT NULL COMMENT '自增长id',
  `ad_creative_template_id` int(11) DEFAULT NULL COMMENT '创意模板ID',
  `order` int(11) DEFAULT NULL COMMENT '槽位排序',
  `element_id` int(11) DEFAULT NULL COMMENT '内容元素ID',
  `value` varchar(3000) DEFAULT NULL COMMENT '元素内容',
  `status` int(11) DEFAULT NULL COMMENT '1: 启用, 默认1,2: 删除,3: 禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建者,沪江数字id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1434 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_creative_dynamic_template`
-- ----------------------------
DROP TABLE IF EXISTS `ad_creative_dynamic_template`;
CREATE TABLE `ad_creative_dynamic_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_creative_template_name` varchar(255) DEFAULT NULL COMMENT '创意模板名称',
  `terminal_type_id` int(11) DEFAULT NULL COMMENT '终端类型',
  `ad_space_style_id` int(11) DEFAULT NULL COMMENT '1 固定 2 浮层',
  `ad_space_type_id` int(11) DEFAULT NULL COMMENT '广告形式',
  `size_id` varchar(255) DEFAULT NULL COMMENT '尺寸',
  `size_name` varchar(255) DEFAULT NULL,
  `ad_content_template_id` int(11) DEFAULT NULL COMMENT '内容模板ID',
  `ad_content_standard_id` int(11) DEFAULT NULL COMMENT '内容规范ID',
  `max_slot` int(11) DEFAULT NULL COMMENT '最大槽位数',
  `min_slot` int(11) DEFAULT NULL COMMENT '最小槽位数',
  `template_code` text COMMENT '模板代码',
  `template_img_url` varchar(255) DEFAULT NULL COMMENT '模板图片预览地址',
  `creator` int(11) DEFAULT NULL COMMENT '创建人，沪江数字id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改者, 沪江数字id',
  `modifier` int(11) DEFAULT NULL,
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `status` int(11) DEFAULT NULL COMMENT '1: 启用, 默认1,2: 删除,3: 禁用',
  `template_content` text COMMENT '模板代码内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1004 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_creative_image`
-- ----------------------------
DROP TABLE IF EXISTS `ad_creative_image`;
CREATE TABLE `ad_creative_image` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_creative_id` int(11) DEFAULT NULL COMMENT '广告创意id',
  `url` varchar(3000) DEFAULT NULL COMMENT '素材url',
  `is_dock` tinyint(4) DEFAULT NULL COMMENT '是否dock素材,0=不是，默认0，主图,1=是，dock',
  `size_name` varchar(255) DEFAULT NULL COMMENT '尺寸文本',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `cbms_id` int(11) DEFAULT NULL COMMENT 'CBMS中的图片ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2812 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告创意素材资源表';

-- ----------------------------
--  Table structure for `ad_creative_image_copy`
-- ----------------------------
DROP TABLE IF EXISTS `ad_creative_image_copy`;
CREATE TABLE `ad_creative_image_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_creative_id` int(11) DEFAULT NULL COMMENT '广告创意id',
  `url` varchar(3000) DEFAULT NULL COMMENT '素材url',
  `is_dock` tinyint(4) DEFAULT NULL COMMENT '是否dock素材,0=不是，默认0，主图,1=是，dock',
  `size_name` varchar(255) DEFAULT NULL COMMENT '尺寸文本',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1986 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告创意素材资源表';

-- ----------------------------
--  Table structure for `ad_creative_info`
-- ----------------------------
DROP TABLE IF EXISTS `ad_creative_info`;
CREATE TABLE `ad_creative_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_creative_name` varchar(50) DEFAULT NULL COMMENT '广告位名称',
  `ad_type_id` int(11) DEFAULT NULL COMMENT '广告形式id',
  `ad_campaign_id` int(11) DEFAULT NULL COMMENT '广告活动id,-1=默认广告创意',
  `size_id` int(11) DEFAULT NULL COMMENT '尺寸id',
  `size_name` varchar(255) DEFAULT NULL COMMENT '尺寸名，用于文字链',
  `click` tinyint(4) DEFAULT NULL COMMENT '点击动作，dim_creative_click表的click_id',
  `target_url` varchar(3000) DEFAULT NULL COMMENT '广告点击url',
  `creative_type` tinyint(4) DEFAULT NULL COMMENT '创意类型,1居中、2全屏',
  `color` varchar(50) DEFAULT NULL COMMENT '填充色，用于闪屏',
  `time` tinyint(4) DEFAULT NULL COMMENT '展示时长，用于闪屏',
  `row_number` tinyint(4) DEFAULT NULL COMMENT '一排个数，用于文字链',
  `text_type` tinyint(4) DEFAULT NULL COMMENT '文字链排版格式,1无、2圆点',
  `and_package_name` varchar(50) DEFAULT NULL COMMENT 'android包名',
  `and_download_url` varchar(3000) DEFAULT NULL COMMENT 'android下载地址',
  `ios_bundle_id` varchar(100) DEFAULT NULL COMMENT 'iOS BundleID',
  `app_store_url` varchar(3000) DEFAULT NULL COMMENT 'App Store地址',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `ad_creative_template_id` int(11) DEFAULT NULL COMMENT '创意模板id',
  PRIMARY KEY (`id`),
  KEY `idx_creative_name` (`ad_creative_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3188 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告创意表';

-- ----------------------------
--  Table structure for `ad_creative_template`
-- ----------------------------
DROP TABLE IF EXISTS `ad_creative_template`;
CREATE TABLE `ad_creative_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `creative_temp_name` varchar(50) DEFAULT NULL COMMENT '创意模板名字',
  `player_pos` tinyint(4) DEFAULT NULL COMMENT '播放器位置,1左上、2中上、3右上、4左中、5居中、6右中、7左下、8中下、9右下',
  `player_mode` tinyint(4) DEFAULT NULL COMMENT '播放方式,1先播放素材、2先dock',
  `stop_pos` tinyint(4) DEFAULT NULL COMMENT '停靠位置,1左上、2中上、3右上、4左中、5居中、6右中、7左下、8中下、9右下、10无',
  `replay_mode` tinyint(4) DEFAULT NULL COMMENT '停靠重播方式,1点击、2鼠标移动',
  `close_pos` tinyint(4) DEFAULT NULL COMMENT '关闭按钮位置,1左上、3右上、4左中、6右中、10无',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  KEY `idx_creative_temp` (`creative_temp_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告创意模板表';

-- ----------------------------
--  Table structure for `ad_creative_text`
-- ----------------------------
DROP TABLE IF EXISTS `ad_creative_text`;
CREATE TABLE `ad_creative_text` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_creative_id` int(11) DEFAULT NULL COMMENT '广告创意id',
  `text_name` varchar(50) DEFAULT NULL COMMENT '文字内容',
  `target_url` varchar(3000) DEFAULT NULL COMMENT '广告点击url',
  `font_color` varchar(50) DEFAULT NULL COMMENT '字体颜色',
  `hover_color` varchar(50) DEFAULT NULL COMMENT '悬停颜色',
  `font_size` varchar(50) DEFAULT NULL COMMENT '字体大小，10-40的双数',
  `alignment` tinyint(4) DEFAULT '1' COMMENT '对齐方式 1:左对齐 2:居中对齐 3:右对齐',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_creative_text` (`ad_creative_id`,`text_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=420 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告创意素材文字链表';

-- ----------------------------
--  Table structure for `ad_creative_text_bak`
-- ----------------------------
DROP TABLE IF EXISTS `ad_creative_text_bak`;
CREATE TABLE `ad_creative_text_bak` (
  `id` int(11) NOT NULL DEFAULT '0' COMMENT '自增长id',
  `ad_creative_id` int(11) DEFAULT NULL COMMENT '广告创意id',
  `text_name` varchar(50) DEFAULT NULL COMMENT '文字内容',
  `target_url` varchar(3000) DEFAULT NULL COMMENT '广告点击url',
  `font_color` varchar(50) DEFAULT NULL COMMENT '字体颜色',
  `hover_color` varchar(50) DEFAULT NULL COMMENT '悬停颜色',
  `font_size` varchar(50) DEFAULT NULL COMMENT '字体大小，10-40的双数',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_media_area_report`
-- ----------------------------
DROP TABLE IF EXISTS `ad_media_area_report`;
CREATE TABLE `ad_media_area_report` (
  `day` date DEFAULT NULL COMMENT '日期 yyyy-MM-dd',
  `site_id` int(11) DEFAULT NULL COMMENT '网站ID',
  `channel_id` int(11) DEFAULT NULL COMMENT '频道ID',
  `adspace_id` int(11) DEFAULT NULL COMMENT '广告位ID',
  `requests` bigint(20) DEFAULT NULL COMMENT '请求数',
  `impressions` bigint(20) DEFAULT NULL COMMENT '展示数',
  `clicks` bigint(20) DEFAULT NULL COMMENT '点击数',
  `cost` decimal(10,5) DEFAULT NULL COMMENT '花费',
  `uv` bigint(20) DEFAULT NULL COMMENT '访客数',
  `pv` bigint(20) DEFAULT NULL COMMENT '网站浏览量',
  `session_cnt` bigint(20) DEFAULT NULL COMMENT '访问数',
  `no_bounce_session_cnt` bigint(20) DEFAULT NULL COMMENT '二跳访问数',
  `duration` bigint(20) DEFAULT NULL COMMENT '总的页面停留时长',
  `order_cnt` bigint(20) DEFAULT NULL COMMENT '实付人次',
  `order_income` double(10,5) DEFAULT NULL COMMENT '实付金额',
  `reg_cnt` bigint(20) DEFAULT NULL COMMENT '注册量',
  `install_app_cnt` bigint(20) DEFAULT NULL COMMENT 'APP激活打开',
  `province` varchar(50) DEFAULT NULL COMMENT '省',
  `city` varchar(50) DEFAULT NULL COMMENT '市',
  KEY `index_day` (`day`),
  KEY `index_adspace_id` (`adspace_id`),
  KEY `index_channel_id` (`channel_id`),
  KEY `index_site_id` (`site_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_media_report`
-- ----------------------------
DROP TABLE IF EXISTS `ad_media_report`;
CREATE TABLE `ad_media_report` (
  `day` date DEFAULT NULL COMMENT '日期 yyyy-MM-dd',
  `hour` int(11) DEFAULT NULL COMMENT '小时',
  `site_id` int(11) DEFAULT NULL COMMENT '网站ID',
  `channel_id` int(11) DEFAULT NULL COMMENT '频道ID',
  `adspace_id` int(11) DEFAULT NULL COMMENT '广告位ID',
  `requests` bigint(20) DEFAULT NULL COMMENT '请求数',
  `impressions` bigint(20) DEFAULT NULL COMMENT '展示数',
  `clicks` bigint(20) DEFAULT NULL COMMENT '点击数',
  `cost` decimal(10,5) DEFAULT NULL COMMENT '花费',
  `uv` bigint(20) DEFAULT NULL COMMENT '访客数',
  `pv` bigint(20) DEFAULT NULL COMMENT '网站浏览量',
  `session_cnt` bigint(20) DEFAULT NULL COMMENT '访问数',
  `no_bounce_session_cnt` bigint(20) DEFAULT NULL COMMENT '二跳访问数',
  `duration` bigint(20) DEFAULT NULL COMMENT '总的页面停留时长',
  `order_cnt` bigint(20) DEFAULT NULL COMMENT '实付人次',
  `order_income` double(10,5) DEFAULT NULL COMMENT '实付金额',
  `reg_cnt` bigint(20) DEFAULT NULL COMMENT '注册量',
  `install_app_cnt` bigint(20) DEFAULT NULL COMMENT 'APP激活打开',
  KEY `index_day` (`day`),
  KEY `index_adspace_id` (`adspace_id`),
  KEY `index_channel_id` (`channel_id`),
  KEY `index_site_id` (`site_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_rt_report`
-- ----------------------------
DROP TABLE IF EXISTS `ad_rt_report`;
CREATE TABLE `ad_rt_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_date` timestamp NULL DEFAULT NULL COMMENT '时间 2017-05-20 14',
  `adspace_id` int(11) DEFAULT NULL COMMENT '广告位ID',
  `campaign_id` int(11) DEFAULT NULL COMMENT '活动ID',
  `strategy_id` int(11) DEFAULT NULL COMMENT '策略ID',
  `requests` bigint(20) DEFAULT NULL COMMENT '请求数',
  `impressions` bigint(20) DEFAULT NULL COMMENT '展示数',
  `clicks` bigint(20) DEFAULT NULL COMMENT '点击数',
  `cost` decimal(20,5) DEFAULT NULL COMMENT '花费',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_key` (`ad_date`,`adspace_id`,`campaign_id`,`strategy_id`),
  KEY `strategy_idx` (`strategy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19242 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_space`
-- ----------------------------
DROP TABLE IF EXISTS `ad_space`;
CREATE TABLE `ad_space` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_space_name` varchar(50) DEFAULT NULL COMMENT '广告位名称',
  `site_id` int(11) DEFAULT NULL COMMENT '所属站点id',
  `channel_id` int(11) DEFAULT NULL COMMENT '所属频道id',
  `ad_type_id` varchar(255) DEFAULT NULL COMMENT '广告形式id,支持多种',
  `size_id` varchar(255) DEFAULT NULL COMMENT '尺寸id',
  `size_name` varchar(255) DEFAULT NULL COMMENT '尺寸文本，有尺寸(1920*768)和比例(3:5)，如果多个，逗号分隔',
  `base_price` decimal(8,2) DEFAULT NULL COMMENT '底价',
  `default_creative_id` int(11) DEFAULT NULL COMMENT '初始创意id',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `ad_style_id` int(11) DEFAULT NULL COMMENT '广告类型,1:固定 2：浮层',
  PRIMARY KEY (`id`),
  KEY `idx_ad_space` (`ad_space_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=850 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告位表';

-- ----------------------------
--  Table structure for `ad_space_flow`
-- ----------------------------
DROP TABLE IF EXISTS `ad_space_flow`;
CREATE TABLE `ad_space_flow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_space_id` int(11) DEFAULT NULL COMMENT '广告位id',
  `customer_id` int(11) DEFAULT NULL COMMENT '客户id',
  `flow_scale` int(11) DEFAULT NULL COMMENT '流量占比，存乘以100以后的整数',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_ad_space_customer` (`ad_space_id`,`customer_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告位流量控制表';

-- ----------------------------
--  Table structure for `ad_space_limit`
-- ----------------------------
DROP TABLE IF EXISTS `ad_space_limit`;
CREATE TABLE `ad_space_limit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_space_id` int(11) DEFAULT NULL COMMENT '广告位id',
  `ad_type_id` int(11) DEFAULT NULL COMMENT '广告类型id',
  `ad_creative_template_id` int(11) DEFAULT NULL COMMENT '动态广告物料模板',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `status` int(11) DEFAULT '1' COMMENT '1: 启用, 默认1,2: 删除,3: 禁用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_ad_space_customer` (`ad_space_id`,`ad_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_strategy_adspace`
-- ----------------------------
DROP TABLE IF EXISTS `ad_strategy_adspace`;
CREATE TABLE `ad_strategy_adspace` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_strategy_id` int(11) DEFAULT NULL COMMENT '广告策略id',
  `ad_space_id` int(11) DEFAULT NULL COMMENT '广告位id',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_adstrategy_adspace` (`ad_strategy_id`,`ad_space_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4012 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告策略广告位关系表';

-- ----------------------------
--  Table structure for `ad_strategy_apps_version`
-- ----------------------------
DROP TABLE IF EXISTS `ad_strategy_apps_version`;
CREATE TABLE `ad_strategy_apps_version` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_strategy_id` int(11) DEFAULT NULL COMMENT '广告策略id',
  `app_ename` varchar(50) DEFAULT NULL COMMENT 'app英文名称',
  `app_version` varchar(30) DEFAULT NULL COMMENT 'app版本',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_adstrategy_appversion` (`ad_strategy_id`,`app_ename`,`app_version`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1862 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告策略渠道定向表';

-- ----------------------------
--  Table structure for `ad_strategy_area`
-- ----------------------------
DROP TABLE IF EXISTS `ad_strategy_area`;
CREATE TABLE `ad_strategy_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_strategy_id` int(11) DEFAULT NULL COMMENT '广告策略id',
  `area_name` varchar(50) DEFAULT NULL COMMENT '地域名称',
  `is_oversea` tinyint(4) DEFAULT '0' COMMENT '是否是国外国家，0=国内，1=国外',
  `level` tinyint(4) DEFAULT NULL COMMENT '级别，1=中国/国外，2=省/国家，3=市/无',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_strategy_area` (`ad_strategy_id`,`area_name`,`is_oversea`,`level`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=780 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告策略地域定向表';

-- ----------------------------
--  Table structure for `ad_strategy_channel`
-- ----------------------------
DROP TABLE IF EXISTS `ad_strategy_channel`;
CREATE TABLE `ad_strategy_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_strategy_id` int(11) DEFAULT NULL COMMENT '广告策略id',
  `channel_ename` varchar(50) DEFAULT NULL COMMENT 'app渠道英文名称',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_adstrategy_channel` (`ad_strategy_id`,`channel_ename`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5547 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告策略渠道定向表';

-- ----------------------------
--  Table structure for `ad_strategy_creative`
-- ----------------------------
DROP TABLE IF EXISTS `ad_strategy_creative`;
CREATE TABLE `ad_strategy_creative` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_strategy_id` int(11) DEFAULT NULL COMMENT '广告策略id',
  `ad_creative_id` int(11) DEFAULT NULL COMMENT '广告创意id',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_adstrategy_adcreative` (`ad_strategy_id`,`ad_creative_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2885 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告策略创意关系表';

-- ----------------------------
--  Table structure for `ad_strategy_crowd`
-- ----------------------------
DROP TABLE IF EXISTS `ad_strategy_crowd`;
CREATE TABLE `ad_strategy_crowd` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `crowd_id` int(11) DEFAULT NULL COMMENT '人群id',
  `ad_strategy_id` int(11) DEFAULT NULL COMMENT '广告策略id',
  `creator` int(11) DEFAULT NULL COMMENT '创建人，沪江数字id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `status` int(11) DEFAULT '1' COMMENT '1: 启用, 默认1,2: 删除,3: 禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=179 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_strategy_frequency`
-- ----------------------------
DROP TABLE IF EXISTS `ad_strategy_frequency`;
CREATE TABLE `ad_strategy_frequency` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_strategy_id` int(11) DEFAULT NULL COMMENT '广告策略id',
  `freq_unit` tinyint(4) DEFAULT NULL COMMENT '频率单位,1日、2周、3月、4活动周期',
  `max_times` int(11) DEFAULT NULL COMMENT '每个用户最多展示次数',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_adstrategy_freq` (`ad_strategy_id`,`freq_unit`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=925 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告策略频控表';

-- ----------------------------
--  Table structure for `ad_strategy_info`
-- ----------------------------
DROP TABLE IF EXISTS `ad_strategy_info`;
CREATE TABLE `ad_strategy_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_strategy_name` varchar(50) DEFAULT NULL COMMENT '广告策略名称',
  `site_type` tinyint(4) DEFAULT NULL COMMENT '网站类型,1PC、2触屏、3APP',
  `ad_campaign_id` int(11) DEFAULT NULL COMMENT '广告活动id',
  `price` decimal(8,2) DEFAULT NULL COMMENT '出价',
  `priority` int(11) DEFAULT NULL COMMENT '优先级',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `ad_strategy_type` int(11) DEFAULT NULL COMMENT '策略类型:1.普通策略 2.智能策略',
  `buy_slots` int(11) DEFAULT NULL COMMENT '购买槽位数',
  `price_type` int(11) DEFAULT NULL COMMENT '价格类型，1CPM，2CPC',
  PRIMARY KEY (`id`),
  KEY `idx_ad_strategy` (`ad_strategy_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1975 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告策略表';

-- ----------------------------
--  Table structure for `ad_strategy_limit_flow`
-- ----------------------------
DROP TABLE IF EXISTS `ad_strategy_limit_flow`;
CREATE TABLE `ad_strategy_limit_flow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_strategy_id` int(11) DEFAULT NULL,
  `limit_type` tinyint(4) DEFAULT NULL COMMENT '1 预算 2 展示 3 点击',
  `limit_total_count` double(20,5) DEFAULT NULL COMMENT '控制总量',
  `limit_day_count` double(20,5) DEFAULT NULL COMMENT '每日控制的量',
  `creator` int(11) DEFAULT NULL COMMENT '创建人，沪江数字id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `status` int(11) DEFAULT '1' COMMENT '1: 启用, 默认1,2: 删除,3: 禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1131 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_strategy_limit_flow_rtreport`
-- ----------------------------
DROP TABLE IF EXISTS `ad_strategy_limit_flow_rtreport`;
CREATE TABLE `ad_strategy_limit_flow_rtreport` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `date` timestamp NULL DEFAULT NULL,
  `ad_strategy_id` int(11) DEFAULT NULL,
  `real_show_count` int(11) DEFAULT '0' COMMENT '当日实时量',
  `real_budget_count` double(20,5) DEFAULT '0.00000' COMMENT '当日实时量',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `creator` int(11) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `date_strategy_id` (`date`,`ad_strategy_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4189 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_strategy_media`
-- ----------------------------
DROP TABLE IF EXISTS `ad_strategy_media`;
CREATE TABLE `ad_strategy_media` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_strategy_id` int(11) DEFAULT NULL COMMENT '广告策略id',
  `media_label_id` int(11) DEFAULT NULL COMMENT '媒体标签Id',
  `creator` int(11) DEFAULT NULL COMMENT '创建人，沪江数字id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `status` int(11) DEFAULT '1' COMMENT '1: 启用, 默认1,2: 删除,3: 禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=332 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_strategy_period`
-- ----------------------------
DROP TABLE IF EXISTS `ad_strategy_period`;
CREATE TABLE `ad_strategy_period` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_strategy_id` int(11) DEFAULT NULL COMMENT '广告策略id',
  `start_date` datetime DEFAULT NULL COMMENT '活动开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '活动结束时间',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_ad_strategy_date` (`ad_strategy_id`,`start_date`,`end_date`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3871 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告策略投放日期表';

-- ----------------------------
--  Table structure for `ad_strategy_report`
-- ----------------------------
DROP TABLE IF EXISTS `ad_strategy_report`;
CREATE TABLE `ad_strategy_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_strategy_id` int(11) DEFAULT NULL COMMENT '广告策略id',
  `impressions` int(11) DEFAULT NULL COMMENT '展示次数',
  `clicks` int(11) DEFAULT NULL COMMENT '点击次数',
  `total_cost` bigint(20) DEFAULT NULL COMMENT '总花费*1000',
  `field_ds` date DEFAULT NULL COMMENT '统计日期，yyyy-MM-dd',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_adstrategy_fieldds` (`ad_strategy_id`,`field_ds`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_strategy_times`
-- ----------------------------
DROP TABLE IF EXISTS `ad_strategy_times`;
CREATE TABLE `ad_strategy_times` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `week` int(11) DEFAULT NULL COMMENT '星期,0-6',
  `hours` varchar(100) DEFAULT NULL COMMENT '小时段',
  `ad_strategy_id` int(11) DEFAULT NULL COMMENT '广告策略id',
  `creator` int(11) DEFAULT NULL COMMENT '创建人，沪江数字id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `status` int(11) DEFAULT '1' COMMENT '1: 启用, 默认1,2: 删除,3: 禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=230 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `ad_type`
-- ----------------------------
DROP TABLE IF EXISTS `ad_type`;
CREATE TABLE `ad_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_type_name` varchar(50) DEFAULT NULL COMMENT '广告形式名称',
  `site_type` tinyint(4) DEFAULT NULL COMMENT '网站类型,1PC、2触屏、3APP',
  `type` tinyint(4) DEFAULT NULL COMMENT '广告形式类型,1固定、2浮层',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_ad_type` (`ad_type_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告形式表';

-- ----------------------------
--  Table structure for `ad_type_creative_template`
-- ----------------------------
DROP TABLE IF EXISTS `ad_type_creative_template`;
CREATE TABLE `ad_type_creative_template` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_type_id` int(11) DEFAULT NULL COMMENT '广告形式id',
  `creative_temp_id` int(11) DEFAULT NULL COMMENT '创意模板id',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_adtype_creative_temp` (`ad_type_id`,`creative_temp_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告形式创意模板关系表';

-- ----------------------------
--  Table structure for `ad_type_size`
-- ----------------------------
DROP TABLE IF EXISTS `ad_type_size`;
CREATE TABLE `ad_type_size` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `size_id` int(11) DEFAULT NULL COMMENT '尺寸id',
  `ad_type_id` varchar(255) DEFAULT NULL COMMENT '广告形式id',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=180 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告形式尺寸关系表';

-- ----------------------------
--  Table structure for `channel_info`
-- ----------------------------
DROP TABLE IF EXISTS `channel_info`;
CREATE TABLE `channel_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `channel_name` varchar(50) DEFAULT NULL COMMENT '频道名称',
  `site_id` int(11) DEFAULT NULL COMMENT '所属站点id',
  `url` varchar(3000) DEFAULT NULL COMMENT '频道url',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  KEY `idx_channel_site` (`channel_name`,`site_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=280 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='频道表';

-- ----------------------------
--  Table structure for `ctr_model`
-- ----------------------------
DROP TABLE IF EXISTS `ctr_model`;
CREATE TABLE `ctr_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `day` varchar(255) DEFAULT NULL COMMENT '天，yyyy-MM-dd',
  `type` varchar(255) DEFAULT NULL COMMENT 'app或者web',
  `site_id` varchar(11) DEFAULT NULL COMMENT '网站ID',
  `ad_type_id` varchar(11) DEFAULT NULL COMMENT '广告形式ID',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `week` varchar(11) DEFAULT NULL COMMENT '周，0-7',
  `hour` varchar(11) DEFAULT NULL COMMENT '小时，0,1,2...23',
  `app` varchar(255) DEFAULT NULL COMMENT 'app专有',
  `brand` varchar(255) DEFAULT NULL COMMENT 'app专有，品牌',
  `ctr` decimal(20,15) DEFAULT NULL COMMENT 'ctr值',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=820743 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `customer_name` varchar(50) NOT NULL COMMENT '客户名',
  `customer_type` tinyint(4) DEFAULT NULL COMMENT '客户类型，1自营、2机构',
  `customer_url` varchar(3000) DEFAULT NULL COMMENT '客户主域',
  `contact` varchar(100) DEFAULT NULL COMMENT '联系人',
  `phone` varchar(100) DEFAULT NULL COMMENT '电话',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱地址',
  `desc` varchar(100) DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建者,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改人,沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  KEY `idx_customer` (`customer_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='客户表';

-- ----------------------------
--  Table structure for `dim_apps`
-- ----------------------------
DROP TABLE IF EXISTS `dim_apps`;
CREATE TABLE `dim_apps` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `app_ename` varchar(50) DEFAULT NULL COMMENT 'app英文名,ex:and_class_client',
  `app_name` varchar(50) DEFAULT NULL COMMENT 'app中文名',
  `app_platform` tinyint(4) DEFAULT NULL COMMENT 'APP平台,1ios、2android',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_ename` (`app_ename`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='移动应用表';

-- ----------------------------
--  Table structure for `dim_apps_channel`
-- ----------------------------
DROP TABLE IF EXISTS `dim_apps_channel`;
CREATE TABLE `dim_apps_channel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `channel_ename` varchar(50) DEFAULT NULL COMMENT '渠道英文名',
  `channel_name` varchar(50) DEFAULT NULL COMMENT '渠道中文名',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_channel_ename` (`channel_ename`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=400 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='移动渠道表';

-- ----------------------------
--  Table structure for `dim_apps_version`
-- ----------------------------
DROP TABLE IF EXISTS `dim_apps_version`;
CREATE TABLE `dim_apps_version` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `app_ename` varchar(50) DEFAULT NULL COMMENT 'app英文名,ex:and_class_client',
  `version` varchar(50) DEFAULT NULL COMMENT '版本号',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_app_version` (`app_ename`,`version`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2049 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='移动版本表';

-- ----------------------------
--  Table structure for `dim_area`
-- ----------------------------
DROP TABLE IF EXISTS `dim_area`;
CREATE TABLE `dim_area` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `post_code` int(11) DEFAULT NULL COMMENT '地域编号',
  `parent_postcode` int(11) DEFAULT NULL COMMENT '上一级地域编号',
  `area_name` varchar(50) DEFAULT NULL COMMENT '地域名称',
  `is_oversea` tinyint(4) DEFAULT '0' COMMENT '是否是国外国家，0=国内，1=国外',
  `level` tinyint(4) DEFAULT NULL COMMENT '级别，1=中国/国外，2=省/国家，3=市/无',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_dim_area` (`area_name`,`is_oversea`,`level`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=657 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='地域字典表';

-- ----------------------------
--  Table structure for `dim_cam_stra_status`
-- ----------------------------
DROP TABLE IF EXISTS `dim_cam_stra_status`;
CREATE TABLE `dim_cam_stra_status` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `code` tinyint(4) DEFAULT NULL COMMENT '后端code',
  `name` varchar(20) DEFAULT NULL COMMENT '后端状态名称',
  `fe_code` tinyint(4) DEFAULT NULL COMMENT '前端展现code',
  `fe_name` varchar(20) DEFAULT NULL COMMENT '前端展现状态名称',
  `next_code` tinyint(4) DEFAULT NULL COMMENT '点击的下拉code',
  `next_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告活动策略状态字典';

-- ----------------------------
--  Table structure for `dim_creative_click`
-- ----------------------------
DROP TABLE IF EXISTS `dim_creative_click`;
CREATE TABLE `dim_creative_click` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `ad_type_id` int(11) DEFAULT NULL COMMENT '广告形式id',
  `click_id` int(11) DEFAULT NULL COMMENT '点击id',
  `click_text` varchar(50) DEFAULT NULL COMMENT '点击动作名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_adtype_click` (`ad_type_id`,`click_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='广告创意点击类型字典表';

-- ----------------------------
--  Table structure for `dim_size`
-- ----------------------------
DROP TABLE IF EXISTS `dim_size`;
CREATE TABLE `dim_size` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `size_type` tinyint(4) DEFAULT NULL COMMENT '尺寸类型,1尺寸、2比例',
  `size_name` varchar(255) DEFAULT NULL COMMENT '尺寸文本, 如果是尺寸,格式为1920*768,如果是比例，格式为5:3',
  `size_text` varchar(255) DEFAULT NULL COMMENT '尺寸文本，仅size_type=2比例有数据',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='尺寸字典表';

-- ----------------------------
--  Table structure for `dim_type_code`
-- ----------------------------
DROP TABLE IF EXISTS `dim_type_code`;
CREATE TABLE `dim_type_code` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `type` varchar(50) DEFAULT NULL COMMENT '代码类型',
  `code` tinyint(4) DEFAULT NULL COMMENT '代码',
  `name` varchar(20) DEFAULT NULL COMMENT '名称',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_type_code` (`type`,`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='枚举代码表';

-- ----------------------------
--  Table structure for `operation_log`
-- ----------------------------
DROP TABLE IF EXISTS `operation_log`;
CREATE TABLE `operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `log_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '日志时间',
  `table_name` varchar(50) DEFAULT NULL COMMENT '表名',
  `table_id` int(11) DEFAULT NULL COMMENT '源表自增长id',
  `operation_type` enum('insert','delete','update') DEFAULT NULL COMMENT '操作类型',
  `editor` int(11) DEFAULT NULL COMMENT '编辑者,沪江数字id',
  `before` text COMMENT '修改前，JSON格式存放修改前信息',
  `after` text COMMENT '修改后，JSON格式存放修改后信息',
  PRIMARY KEY (`id`),
  KEY `idx_log_time` (`log_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据变化日志表';

-- ----------------------------
--  Table structure for `site_info`
-- ----------------------------
DROP TABLE IF EXISTS `site_info`;
CREATE TABLE `site_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `site_name` varchar(50) DEFAULT NULL COMMENT '网站名称',
  `site_type` tinyint(4) DEFAULT NULL COMMENT '网站类型,1PC、2触屏、3APP',
  `app_platform` tinyint(4) DEFAULT NULL COMMENT 'APP平台,1ios、2android',
  `url` varchar(3000) DEFAULT NULL COMMENT '网站url',
  `memo` varchar(50) DEFAULT NULL COMMENT '备注',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  `customer_id` int(11) DEFAULT NULL COMMENT '客户ID',
  PRIMARY KEY (`id`),
  KEY `idx_site_name` (`site_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=335 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='网站表';

-- ----------------------------
--  Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增长id',
  `user_id` int(11) DEFAULT NULL COMMENT '沪江数字ID',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名,沪江id',
  `real_name` varchar(50) DEFAULT NULL COMMENT '真实名称',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `user_desc` varchar(100) DEFAULT NULL COMMENT '用户描述',
  `is_superuser` tinyint(4) DEFAULT '0' COMMENT '是否超级用户,0=不是，1=是',
  `status` tinyint(4) DEFAULT '1' COMMENT '状态，默认1，1启用、2删除、3禁用',
  `creator` int(11) DEFAULT NULL COMMENT '创建人,沪江数字id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` int(11) DEFAULT NULL COMMENT '最后修改者, 沪江数字id',
  `modify_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `delete_time` timestamp NULL DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_uid` (`user_id`) USING BTREE,
  KEY `idx_uname` (`user_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2890417 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表';

SET FOREIGN_KEY_CHECKS = 1;
