/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50622
Source Host           : 127.0.0.1:3306
Source Database       : plat

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2016-01-08 11:27:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for app_config
-- ----------------------------
DROP TABLE IF EXISTS `app_config`;
CREATE TABLE `app_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '应用名称',
  `app_key` varchar(15) DEFAULT NULL COMMENT 'appKey',
  `app_secret` varchar(40) DEFAULT NULL COMMENT 'appSecret',
  `bind_ip` varchar(15) DEFAULT NULL COMMENT '绑定IP',
  `app_limit_type` int(11) DEFAULT '0' COMMENT '应用限制方式（0-不受限制，1-对所有的接口，2-针对单个接口）',
  `valid_date` varchar(10) DEFAULT NULL COMMENT '有效期（格式：2015-10-30）',
  `is_lock` int(11) DEFAULT '0' COMMENT '是否被锁定（0-否，1-是）',
  `total_visits_times` int(11) DEFAULT NULL COMMENT '总共访问次数',
  `total_limit_times` int(11) DEFAULT '-1' COMMENT '总共限制次数（-1-不受限制，其他-限制次数）',
  `date_visits_times` int(11) DEFAULT NULL COMMENT '日访问总次数',
  `date_limit_times` int(11) DEFAULT '-1' COMMENT '日限制总次数（-1-不受限制，其他-限制次数）',
  `limit_rate` int(11) DEFAULT NULL COMMENT '限制访问频率（单位/秒，-1-不受限制，其他-限制频率）',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `insert_time` timestamp NULL DEFAULT NULL COMMENT '插入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_config
-- ----------------------------
INSERT INTO `app_config` VALUES ('1', '开放接口应用', 'SP0000001', '123456', '192.168.21.252', '0', null, '0', '0', '-1', null, null, null, '2015-11-11 16:04:15', '2015-11-11 16:09:05');

-- ----------------------------
-- Table structure for app_limit_for_interface
-- ----------------------------
DROP TABLE IF EXISTS `app_limit_for_interface`;
CREATE TABLE `app_limit_for_interface` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `app_id` int(11) DEFAULT NULL COMMENT '应用ID',
  `method_id` int(11) DEFAULT NULL COMMENT '接口方法ID',
  `total_visits_times` int(11) DEFAULT NULL COMMENT '总共访问次数',
  `total_limit_times` int(11) DEFAULT NULL COMMENT '总共限制次数',
  `date_visits_times` int(11) DEFAULT NULL COMMENT '日访问总次数',
  `date_limit_times` int(11) DEFAULT NULL COMMENT '日限制总次数',
  `limit_rate` int(11) DEFAULT NULL COMMENT '频率限制（单位/秒）',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `insert_time` timestamp NULL DEFAULT NULL COMMENT '插入时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_limit_for_interface
-- ----------------------------

-- ----------------------------
-- Table structure for interface_group
-- ----------------------------
DROP TABLE IF EXISTS `interface_group`;
CREATE TABLE `interface_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(20) DEFAULT NULL COMMENT '组名',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `insert_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '插入时间',
  `is_del` int(11) DEFAULT NULL COMMENT '是否被删除（0-否，1-是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of interface_group
-- ----------------------------

-- ----------------------------
-- Table structure for method_config
-- ----------------------------
DROP TABLE IF EXISTS `method_config`;
CREATE TABLE `method_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(50) DEFAULT NULL COMMENT '方法名称',
  `alias_name` varchar(50) DEFAULT NULL COMMENT '别名',
  `description` varchar(100) DEFAULT NULL COMMENT '方法描述',
  `group_id` int(11) DEFAULT NULL COMMENT '方法所属组',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `insert_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '插入时间',
  `is_del` int(11) DEFAULT NULL COMMENT '是否被删除（0-否，1-是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of method_config
-- ----------------------------
