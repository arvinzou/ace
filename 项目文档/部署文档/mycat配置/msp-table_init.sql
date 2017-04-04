/*
 Navicat Premium Data Transfer

 Source Server         : 10.1.5.35
 Source Server Type    : MySQL
 Source Server Version : 50621
 Source Host           : 10.1.5.35
 Source Database       : msp

 Target Server Type    : MySQL
 Target Server Version : 50621
 File Encoding         : utf-8

 Date: 12/27/2016 11:19:51 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `attach`
-- ----------------------------
DROP TABLE IF EXISTS `attach`;
CREATE TABLE `attach` (
  `attach_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '序号',
  `category` varchar(50) NOT NULL COMMENT '类别',
  `notice_id` varchar(50) NOT NULL COMMENT '公告编号',
  `file_url` varchar(200) NOT NULL COMMENT '地址',
  `file_name` varchar(50) NOT NULL COMMENT '文件名称',
  `file_size` varchar(50) NOT NULL COMMENT '附件大小',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '入库时间',
  PRIMARY KEY (`attach_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='附件';

-- ----------------------------
--  Table structure for `clinic_hosp_audit_info`
-- ----------------------------
DROP TABLE IF EXISTS `clinic_hosp_audit_info`;
CREATE TABLE `clinic_hosp_audit_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `settleDate` date DEFAULT NULL COMMENT '结算日期',
  `number` bigint(20) DEFAULT NULL COMMENT '违规数量',
  `totalCost` decimal(14,4) DEFAULT NULL COMMENT '总费用',
  PRIMARY KEY (`id`),
  KEY `index_areaCode` (`areaCode`),
  KEY `index_settleDate` (`settleDate`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='门诊主信息主审核中间表';

-- ----------------------------
--  Table structure for `clinic_hosp_audit_num`
-- ----------------------------
DROP TABLE IF EXISTS `clinic_hosp_audit_num`;
CREATE TABLE `clinic_hosp_audit_num` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `hospNum` bigint(20) DEFAULT NULL COMMENT '接入医院数',
  `autoAuditNum` bigint(20) DEFAULT NULL COMMENT '待机审数',
  `firstAuditNum` bigint(20) DEFAULT NULL COMMENT '待初审数',
  `reAuditNum` bigint(20) DEFAULT NULL COMMENT '待复审数',
  `clinicIllegalNum` bigint(20) DEFAULT NULL COMMENT '门诊违规数',
  `hospIllegalNum` bigint(20) DEFAULT NULL COMMENT '住院违规数',
  `cumulativeNum` bigint(20) DEFAULT NULL COMMENT '累计业务数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `clinic_hosp_rule_result`
-- ----------------------------
DROP TABLE IF EXISTS `clinic_hosp_rule_result`;
CREATE TABLE `clinic_hosp_rule_result` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `settleDate` date DEFAULT NULL COMMENT '结算日期',
  `name` varchar(100) DEFAULT NULL COMMENT '规则名称',
  `number` bigint(20) DEFAULT NULL COMMENT '违规规则数量',
  PRIMARY KEY (`id`),
  KEY `index_areaCode` (`areaCode`),
  KEY `index_settleDate` (`settleDate`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='门诊住院违规规则数量中间表';

-- ----------------------------
--  Table structure for `clinic_mid_audit_hosp`
-- ----------------------------
DROP TABLE IF EXISTS `clinic_mid_audit_hosp`;
CREATE TABLE `clinic_mid_audit_hosp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `areaCode` varchar(6) DEFAULT NULL COMMENT '地区编码（统计到县）',
  `settleDate` date DEFAULT NULL COMMENT 'settleDate',
  `hospLevel` varchar(20) DEFAULT NULL COMMENT '医院等级',
  `hospId` bigint(20) DEFAULT NULL COMMENT '医院ID',
  `hospName` varchar(255) DEFAULT NULL COMMENT '医院名称',
  `ruleLevelId` char(1) DEFAULT NULL COMMENT '违规警示级别',
  `totalNumber` bigint(20) DEFAULT NULL COMMENT '总业务数',
  `totalCost` decimal(14,4) DEFAULT NULL COMMENT '总费用',
  `doubtNumber` bigint(20) DEFAULT NULL COMMENT '违规业务数',
  `doubtCost` decimal(14,4) DEFAULT NULL COMMENT '违规费用',
  `balanceCost` decimal(14,4) DEFAULT NULL COMMENT '医保基金支付总金额',
  `doubtBalCost` decimal(14,4) DEFAULT NULL COMMENT '违规医保基金支付总金额',
  `doubtMoney` decimal(14,4) DEFAULT NULL COMMENT '可疑费用',
  `insuredType` char(3) DEFAULT NULL COMMENT '社保类型',
  `deductMoney` decimal(14,4) DEFAULT NULL COMMENT '扣款金额',
  `totalId` bigint(20) DEFAULT NULL COMMENT '复审状态下的总人次',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `clinic_mid_audit_rule`
-- ----------------------------
DROP TABLE IF EXISTS `clinic_mid_audit_rule`;
CREATE TABLE `clinic_mid_audit_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `areaCode` varchar(6) DEFAULT NULL COMMENT '地区编码',
  `insuredType` char(3) DEFAULT NULL COMMENT '医保类型',
  `settleDate` datetime DEFAULT NULL COMMENT '结算时间',
  `ruleType` char(1) DEFAULT NULL COMMENT '规则类型（A类、B类、C类）',
  `ruleLevelId` char(1) DEFAULT NULL COMMENT '违规警示级别',
  `ruleID` varchar(20) DEFAULT NULL COMMENT '规则id',
  `ruleName` varchar(100) NOT NULL COMMENT '规则名称',
  `doubtNumber` bigint(20) DEFAULT NULL COMMENT '违规业务数',
  `doubtCost` decimal(14,4) DEFAULT NULL COMMENT '违规费用',
  `doubtFreq` bigint(20) DEFAULT NULL COMMENT '违规次数',
  `totalNumber` bigint(20) DEFAULT NULL,
  `totalCost` decimal(14,4) DEFAULT NULL,
  `balanceCost` decimal(14,4) DEFAULT NULL,
  `doubtBalCost` decimal(14,4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `config`
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `config_key` varchar(50) NOT NULL COMMENT ' 配置KEY',
  `category` varchar(50) NOT NULL,
  `config_name` varchar(50) NOT NULL COMMENT ' 名称',
  `config_value` varchar(500) NOT NULL COMMENT '数值',
  `remark` varchar(50) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入库时间',
  PRIMARY KEY (`config_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置';

-- ----------------------------
--  Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `DEPARTMENT_ID` varchar(50) NOT NULL,
  `PARENT_DEPARTMENT_ID` varchar(50) NOT NULL,
  `DEPARTMENT_NAME` varchar(50) NOT NULL,
  `DESCRIBTION` varchar(500) DEFAULT NULL COMMENT '部门描述',
  `DEPARTMENT_LEVEL` int(5) NOT NULL DEFAULT '3',
  `USER_ID` bigint(20) DEFAULT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `CREATE_USER_ID` bigint(20) NOT NULL,
  `STAUTS` varchar(1) NOT NULL,
  `HOURCE_NUMBER` varchar(50) DEFAULT NULL,
  `area_code` varchar(50) DEFAULT NULL COMMENT '所属地区',
  PRIMARY KEY (`DEPARTMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dict`
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORY_ID` varchar(2) NOT NULL,
  `CODE` varchar(50) NOT NULL,
  `PCODE` varchar(50) DEFAULT '0' COMMENT '拼音码',
  `NAME` varchar(50) NOT NULL,
  `SPELL` varchar(50) DEFAULT NULL COMMENT '拼音码',
  `REMARK` varchar(500) DEFAULT NULL,
  `CREATE_TIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2304 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `dict_category`
-- ----------------------------
DROP TABLE IF EXISTS `dict_category`;
CREATE TABLE `dict_category` (
  `category_id` varchar(2) NOT NULL,
  `name` varchar(50) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `files`
-- ----------------------------
DROP TABLE IF EXISTS `files`;
CREATE TABLE `files` (
  `file_path` varchar(200) NOT NULL,
  `status` varchar(50) DEFAULT NULL,
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  PRIMARY KEY (`file_path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `hosp_mid_audit_hosp`
-- ----------------------------
DROP TABLE IF EXISTS `hosp_mid_audit_hosp`;
CREATE TABLE `hosp_mid_audit_hosp` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `areaCode` varchar(6) DEFAULT NULL COMMENT '地区编码（统计到县）',
  `settleDate` date DEFAULT NULL COMMENT '时间（统计到天）',
  `hospLevel` varchar(20) DEFAULT NULL COMMENT '医院等级',
  `hospId` bigint(20) DEFAULT NULL COMMENT '医院ID',
  `hospName` varchar(255) DEFAULT NULL COMMENT '医院名称',
  `ruleLevelId` char(1) DEFAULT NULL COMMENT '违规警示级别',
  `totalNumber` bigint(20) DEFAULT NULL COMMENT '总业务数',
  `totalCost` decimal(14,4) DEFAULT NULL COMMENT '总费用',
  `doubtNumber` bigint(20) DEFAULT NULL COMMENT '违规业务数',
  `doubtCost` decimal(14,4) DEFAULT NULL COMMENT '违规费用',
  `balanceCost` decimal(14,4) DEFAULT NULL COMMENT '医保基金支付总金额',
  `doubtBalCost` decimal(14,4) DEFAULT NULL COMMENT '违规医保基金支付总金额',
  `insuredType` char(3) DEFAULT NULL COMMENT '医保类型',
  `doubtMoney` decimal(14,4) DEFAULT NULL COMMENT '可疑费用',
  `totalId` int(11) DEFAULT NULL COMMENT '复审状态下的总人次',
  `deductMoney` decimal(14,4) DEFAULT NULL COMMENT '扣款金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `hosp_mid_audit_rule`
-- ----------------------------
DROP TABLE IF EXISTS `hosp_mid_audit_rule`;
CREATE TABLE `hosp_mid_audit_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `areaCode` varchar(6) DEFAULT NULL COMMENT '地区编码',
  `insuredType` char(3) DEFAULT NULL COMMENT '医保类型',
  `settleDate` datetime DEFAULT NULL COMMENT '审核时间',
  `ruleType` char(1) DEFAULT NULL COMMENT '规则类型',
  `ruleLevelId` char(1) DEFAULT NULL COMMENT '规则警示级别',
  `ruleID` varchar(20) DEFAULT NULL COMMENT '规则id',
  `ruleName` varchar(100) DEFAULT NULL COMMENT '规则名称',
  `doubtNumber` bigint(20) DEFAULT '0' COMMENT '违规业务数',
  `doubtCost` decimal(14,4) DEFAULT NULL COMMENT '违规费用',
  `doubtFreq` bigint(20) DEFAULT NULL COMMENT '违规次数',
  `totalNumber` bigint(20) DEFAULT NULL,
  `totalCost` decimal(14,4) DEFAULT NULL,
  `balanceCost` decimal(14,4) DEFAULT NULL,
  `doubtBalCost` decimal(14,4) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `logs`
-- ----------------------------
DROP TABLE IF EXISTS `logs`;
CREATE TABLE `logs` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOG` text,
  `USER_ID` varchar(50) DEFAULT NULL,
  `USER_NAME` varchar(50) DEFAULT NULL,
  `CREATE_TIME` datetime DEFAULT NULL,
  `OBJECT_VALUE` longtext,
  `OLD` longtext,
  `NEWS` longtext,
  `NAME` longtext,
  `IP` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4410 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `msp_audit_folder`
-- ----------------------------
DROP TABLE IF EXISTS `msp_audit_folder`;
CREATE TABLE `msp_audit_folder` (
  `code` varchar(30) NOT NULL COMMENT '编码',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `pcode` varchar(30) NOT NULL COMMENT '父编码',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态:1-正常，0-禁用',
  `userId` bigint(20) NOT NULL COMMENT '操作人',
  `indate` datetime NOT NULL COMMENT '入库日期',
  `updateUserId` bigint(20) DEFAULT NULL COMMENT '最后变更人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则文件夹';

-- ----------------------------
--  Table structure for `msp_audit_item`
-- ----------------------------
DROP TABLE IF EXISTS `msp_audit_item`;
CREATE TABLE `msp_audit_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `cureType` char(1) NOT NULL DEFAULT '0' COMMENT '治疗类型:0-公共1-门诊2-住院,3-遍历属性',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `valueType` char(1) NOT NULL COMMENT '计算项类型：0-单个VO属性值，1-表达式计算，2-单个查询，3-集合查询,4-集合属性',
  `valueProp` varchar(500) NOT NULL COMMENT '取值关键字/查询sql/计算公式',
  `pcode` varchar(50) NOT NULL COMMENT '拼音码',
  `render` varchar(100) NOT NULL COMMENT '渲染对象',
  `editor` varchar(200) NOT NULL COMMENT '编辑对象',
  `remark` varchar(300) NOT NULL COMMENT '描述',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态:1-正常，0-禁用',
  `cacheType` char(1) DEFAULT '1' COMMENT '缓冲类型：0-缓存，1-不缓存',
  `userId` bigint(20) NOT NULL COMMENT '操作人',
  `indate` datetime NOT NULL COMMENT '入库日期',
  `updateUserId` bigint(20) DEFAULT NULL COMMENT '最后变更人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7077 DEFAULT CHARSET=utf8 COMMENT='计算项目';

-- ----------------------------
--  Table structure for `msp_audit_item_param`
-- ----------------------------
DROP TABLE IF EXISTS `msp_audit_item_param`;
CREATE TABLE `msp_audit_item_param` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '计算项标识',
  `itemId` bigint(20) NOT NULL COMMENT '所属计算项',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `paramItemId` bigint(20) NOT NULL COMMENT '参数计算项Id',
  `seqNo` smallint(6) NOT NULL COMMENT '序号',
  `userId` bigint(20) NOT NULL COMMENT '操作人',
  `indate` datetime NOT NULL COMMENT '入库日期',
  `updateUserId` bigint(20) DEFAULT NULL COMMENT '最后变更人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7103 DEFAULT CHARSET=utf8 COMMENT='计算项目参数';

-- ----------------------------
--  Table structure for `msp_audit_level`
-- ----------------------------
DROP TABLE IF EXISTS `msp_audit_level`;
CREATE TABLE `msp_audit_level` (
  `code` varchar(50) NOT NULL COMMENT '编码',
  `name` varchar(20) DEFAULT NULL COMMENT '警示级别',
  `min` int(11) DEFAULT NULL COMMENT '分值最小值',
  `max` int(11) DEFAULT NULL COMMENT '分值最大值',
  `remark` varchar(20) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='警示级别';

-- ----------------------------
--  Table structure for `msp_audit_rule`
-- ----------------------------
DROP TABLE IF EXISTS `msp_audit_rule`;
CREATE TABLE `msp_audit_rule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `ruleType` char(1) DEFAULT NULL COMMENT '规则分类（A类、B类、C类）',
  `score` smallint(6) DEFAULT '0' COMMENT '分值【1分~99分】',
  `areaCode` varchar(6) NOT NULL DEFAULT '000000' COMMENT '所属地区:000000为默认方案',
  `folderCode` varchar(60) NOT NULL COMMENT '所属文件夹',
  `cureType` char(1) NOT NULL COMMENT '治疗类型:0-公共1-门诊2-住院',
  `descri` varchar(300) NOT NULL COMMENT '????',
  `deductRatio` decimal(6,2) DEFAULT '0.00' COMMENT '扣款比例建议（0.00-99.99）',
  `repositoryId` bigint(20) NOT NULL COMMENT '关联知识库Id',
  `priority` int(11) NOT NULL DEFAULT '0' COMMENT '优先级',
  `activeStatus` char(1) NOT NULL COMMENT '启用状态：1-已启用，0-未启用',
  `status` char(1) CHARACTER SET latin1 DEFAULT '1' COMMENT '状态:1-正常，0-禁用，2-删除',
  `type` char(1) DEFAULT NULL COMMENT '1整体审核2医嘱审核 ',
  `userId` bigint(20) NOT NULL COMMENT '操作人',
  `indate` datetime NOT NULL COMMENT '入库日期',
  `updateUserId` bigint(20) DEFAULT NULL COMMENT '最后变更人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 COMMENT='审核规则表';

-- ----------------------------
--  Table structure for `msp_audit_rule_activelog`
-- ----------------------------
DROP TABLE IF EXISTS `msp_audit_rule_activelog`;
CREATE TABLE `msp_audit_rule_activelog` (
  `id` bigint(20) NOT NULL COMMENT '标识',
  `ruleId` bigint(20) NOT NULL COMMENT '规则标识',
  `versionId` bigint(20) NOT NULL COMMENT '版本标识',
  `activeStatus` char(1) NOT NULL COMMENT '激活状态：1-已激活，0-未激活',
  `remark` varchar(300) DEFAULT NULL COMMENT '激活描述',
  `userId` bigint(20) DEFAULT NULL COMMENT '操作人',
  `indate` datetime DEFAULT NULL COMMENT '入库日期',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_13` (`versionId`),
  KEY `FK_Reference_14` (`ruleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则版本激活日志';

-- ----------------------------
--  Table structure for `msp_audit_rule_area`
-- ----------------------------
DROP TABLE IF EXISTS `msp_audit_rule_area`;
CREATE TABLE `msp_audit_rule_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `areaCode` varchar(6) NOT NULL COMMENT '所属地区',
  `ruleId` bigint(20) NOT NULL COMMENT '规则标识',
  `insuredType` char(1) DEFAULT NULL COMMENT '1、城镇职工 2、城镇居民 3、新农合 4、工伤 5、生育 6、子女统筹 0、其它',
  `descri` varchar(200) DEFAULT NULL COMMENT '说明',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态:1-正常，0-禁用',
  `userId` bigint(20) NOT NULL COMMENT '操作人',
  `indate` datetime NOT NULL COMMENT '入库日期',
  `updateUserId` bigint(20) DEFAULT NULL COMMENT '最后变更人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_3` (`ruleId`)
) ENGINE=InnoDB AUTO_INCREMENT=293 DEFAULT CHARSET=utf8 COMMENT='地区规则库';

-- ----------------------------
--  Table structure for `msp_audit_rule_attr`
-- ----------------------------
DROP TABLE IF EXISTS `msp_audit_rule_attr`;
CREATE TABLE `msp_audit_rule_attr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `ruleId` bigint(20) NOT NULL COMMENT '规则标识',
  `versionId` bigint(20) DEFAULT NULL,
  `nodeId` bigint(20) NOT NULL COMMENT '版本标识',
  `propName` varchar(30) NOT NULL COMMENT '属性名称',
  `propValue` varchar(100) NOT NULL COMMENT '名称属性值',
  `indate` datetime NOT NULL COMMENT '入库日期',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_19` (`nodeId`)
) ENGINE=InnoDB AUTO_INCREMENT=115 DEFAULT CHARSET=utf8 COMMENT='节点属性表';

-- ----------------------------
--  Table structure for `msp_audit_rule_auditlog`
-- ----------------------------
DROP TABLE IF EXISTS `msp_audit_rule_auditlog`;
CREATE TABLE `msp_audit_rule_auditlog` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `ruleId` bigint(20) NOT NULL COMMENT '规则标识',
  `versionId` bigint(20) NOT NULL COMMENT '版本标识',
  `auditResult` char(1) NOT NULL COMMENT '审核结果：审核状态：0-未审核、1-审核通过、2-审核退回',
  `remark` varchar(300) DEFAULT NULL COMMENT '审核描述',
  `userId` bigint(20) NOT NULL COMMENT '操作人',
  `indate` datetime NOT NULL COMMENT '入库日期',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_15` (`ruleId`),
  KEY `FK_Reference_9` (`versionId`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8 COMMENT='规则审核日志';

-- ----------------------------
--  Table structure for `msp_audit_rule_branch`
-- ----------------------------
DROP TABLE IF EXISTS `msp_audit_rule_branch`;
CREATE TABLE `msp_audit_rule_branch` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `ruleId` bigint(20) NOT NULL COMMENT '所属规则',
  `versionId` bigint(20) DEFAULT NULL,
  `nodeCode` varchar(20) NOT NULL COMMENT '所属节点',
  `code` varchar(20) NOT NULL COMMENT '编码',
  `name` varchar(300) NOT NULL COMMENT '名称',
  `targetNodeCode` varchar(20) NOT NULL COMMENT '结束节点',
  `indate` datetime DEFAULT NULL COMMENT '入库日期',
  `lastModifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `remark` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=286 DEFAULT CHARSET=utf8 COMMENT='节点分支';

-- ----------------------------
--  Table structure for `msp_audit_rule_condition`
-- ----------------------------
DROP TABLE IF EXISTS `msp_audit_rule_condition`;
CREATE TABLE `msp_audit_rule_condition` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `ruleId` bigint(20) NOT NULL COMMENT '所属规则',
  `versionId` bigint(20) DEFAULT NULL,
  `elementType` char(1) DEFAULT NULL COMMENT '所属元素类型：0-节点，1-分支',
  `elementId` bigint(20) NOT NULL COMMENT '所属规则节点',
  `calcItemId` varchar(100) NOT NULL COMMENT '计算项',
  `comparator` char(2) NOT NULL COMMENT '比较运算符：\r\n            01 相等      \r\n            02 不等      \r\n            03 大于      \r\n            04 大于等于  \r\n            05 小于      \r\n            06 小于等于',
  `indate` datetime DEFAULT NULL COMMENT '入库日期',
  `lastModifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=194 DEFAULT CHARSET=utf8 COMMENT='节点分支条件';

-- ----------------------------
--  Table structure for `msp_audit_rule_condition_values`
-- ----------------------------
DROP TABLE IF EXISTS `msp_audit_rule_condition_values`;
CREATE TABLE `msp_audit_rule_condition_values` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `conditionId` bigint(20) NOT NULL COMMENT '所属条件',
  `ruleId` bigint(20) DEFAULT NULL,
  `versionId` bigint(20) DEFAULT NULL,
  `compValueType` char(1) NOT NULL COMMENT '对比值类型:0-固定值,1-计算项\r\n            ',
  `compValue` varchar(50) NOT NULL COMMENT '对比值',
  `status` char(1) NOT NULL COMMENT '状态:0-正常,1-已删除',
  `userId` bigint(20) NOT NULL COMMENT '操作人',
  `indate` datetime NOT NULL COMMENT '入库日期',
  `lastModifiedUserId` bigint(20) DEFAULT NULL COMMENT '最后变更人',
  `lastModifiedTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`conditionId`)
) ENGINE=InnoDB AUTO_INCREMENT=1239 DEFAULT CHARSET=utf8 COMMENT='对比值';

-- ----------------------------
--  Table structure for `msp_audit_rule_log`
-- ----------------------------
DROP TABLE IF EXISTS `msp_audit_rule_log`;
CREATE TABLE `msp_audit_rule_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `ruleId` bigint(20) NOT NULL COMMENT '规则标识',
  `versionId` bigint(20) NOT NULL COMMENT '版本标识',
  `operType` char(3) NOT NULL COMMENT '操作类型:ADD-新增，MOD-修改，DEL-删除',
  `remark` varchar(300) DEFAULT NULL COMMENT '操作说明',
  `userId` bigint(20) NOT NULL COMMENT '操作人',
  `indate` datetime NOT NULL COMMENT '入库日期',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_17` (`versionId`),
  KEY `FK_Reference_6` (`ruleId`)
) ENGINE=InnoDB AUTO_INCREMENT=215 DEFAULT CHARSET=utf8 COMMENT='规则修改日志表';

-- ----------------------------
--  Table structure for `msp_audit_rule_node`
-- ----------------------------
DROP TABLE IF EXISTS `msp_audit_rule_node`;
CREATE TABLE `msp_audit_rule_node` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `ruleId` bigint(20) NOT NULL COMMENT '规则标识',
  `versionId` bigint(20) NOT NULL COMMENT '版本标识',
  `code` varchar(20) NOT NULL COMMENT '编码',
  `type` char(1) NOT NULL COMMENT '节点类型：0-开始事件，1-结束事件，2-检查任务，3-遍历任务，4-结果节点',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `indate` datetime NOT NULL COMMENT '入库日期',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_10` (`ruleId`),
  KEY `Idx_ruleid_code` (`code`),
  KEY `FK_Reference_11` (`versionId`)
) ENGINE=InnoDB AUTO_INCREMENT=519 DEFAULT CHARSET=utf8 COMMENT='规则节点';

-- ----------------------------
--  Table structure for `msp_audit_rule_right`
-- ----------------------------
DROP TABLE IF EXISTS `msp_audit_rule_right`;
CREATE TABLE `msp_audit_rule_right` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `ruleId` bigint(20) NOT NULL COMMENT '规则标识',
  `editorId` bigint(20) NOT NULL COMMENT '编辑用户Id(角色id)',
  `ruleRight` char(1) DEFAULT '0' COMMENT '规则权限类型:0-只读,1-编辑',
  `versionRight` char(1) DEFAULT '0' COMMENT '版本权限类型:0-只读，1-编辑，2-激活',
  `status` char(1) NOT NULL DEFAULT '1' COMMENT '状态:1-正常，0-禁用',
  `userId` bigint(20) NOT NULL COMMENT '操作人',
  `indate` datetime NOT NULL COMMENT '入库日期',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_18` (`ruleId`)
) ENGINE=InnoDB AUTO_INCREMENT=183 DEFAULT CHARSET=utf8 COMMENT='规则管理权限';

-- ----------------------------
--  Table structure for `msp_audit_rule_testlog`
-- ----------------------------
DROP TABLE IF EXISTS `msp_audit_rule_testlog`;
CREATE TABLE `msp_audit_rule_testlog` (
  `id` bigint(20) NOT NULL COMMENT '标识',
  `ruleId` bigint(20) NOT NULL COMMENT '规则标识',
  `versionId` bigint(20) NOT NULL COMMENT '版本标识',
  `dataId` bigint(20) NOT NULL COMMENT '测试数据标识',
  `result` text NOT NULL COMMENT '测试结果',
  `userId` bigint(20) NOT NULL COMMENT '操作人',
  `indate` datetime NOT NULL COMMENT '入库日期',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_12` (`versionId`),
  KEY `FK_Reference_16` (`ruleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='版本测试记录';

-- ----------------------------
--  Table structure for `msp_audit_rule_version`
-- ----------------------------
DROP TABLE IF EXISTS `msp_audit_rule_version`;
CREATE TABLE `msp_audit_rule_version` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '标识',
  `ruleId` bigint(20) NOT NULL COMMENT '规则标识',
  `designView` text NOT NULL COMMENT '规则定义',
  `version` varchar(20) NOT NULL COMMENT '当前版本号',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态:0-草稿,1-已发布,2-暂停',
  `activeStatus` char(1) NOT NULL DEFAULT '0' COMMENT '启用状态：1-已启用，0-未启用',
  `auditStatus` char(1) NOT NULL DEFAULT '0' COMMENT '审核状态：0-未审核、1-审核通过、2-审核退回',
  `parseStatus` char(1) NOT NULL DEFAULT '0' COMMENT '解析状态：0-未解析，1-已解析',
  `remark` varchar(300) DEFAULT NULL COMMENT '规则描述',
  `userId` bigint(20) NOT NULL COMMENT '操作人',
  `indate` datetime NOT NULL COMMENT '入库日期',
  `updateUserId` bigint(20) DEFAULT NULL COMMENT '最后变更人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_8` (`ruleId`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8 COMMENT='规则版本表';

-- ----------------------------
--  Table structure for `msp_clinic_audit`
-- ----------------------------
DROP TABLE IF EXISTS `msp_clinic_audit`;
CREATE TABLE `msp_clinic_audit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `treatId` bigint(20) DEFAULT NULL COMMENT '关联到门诊信息主表 ',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `insuredType` char(3) DEFAULT NULL COMMENT '1、城镇职工 2、城镇居民 3、新农合 4、工伤 5、生育 6、子女统筹 0、其它',
  `status` char(1) DEFAULT NULL COMMENT '1已机审2已初审3已复审4归档',
  `result` char(1) DEFAULT NULL COMMENT '1正常2违规3挂起 ',
  `excepScore` int(11) DEFAULT NULL COMMENT 'Sum(明细异常分) ',
  `cautionLevel` char(1) DEFAULT NULL COMMENT '根据异常分计算警示级别 ',
  `description` longtext COMMENT '综合审核明细生成综述 ',
  `doubtMoney` decimal(14,4) DEFAULT NULL COMMENT 'Sum(可疑明细金额) ',
  `deductMoney` decimal(14,4) DEFAULT NULL COMMENT '扣款金额',
  `writeBackStatus` char(1) DEFAULT NULL COMMENT '1未回写2初审已回写3复审已回写 ',
  `clarifyType` char(1) DEFAULT NULL COMMENT '1无需澄清2待澄清3已澄清 ',
  `firstAuditId` bigint(20) DEFAULT NULL COMMENT '初审人',
  `finalAuditId` bigint(20) DEFAULT NULL COMMENT '终审人',
  `indate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateDate` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=338 DEFAULT CHARSET=utf8 COMMENT='门诊审核主表';

-- ----------------------------
--  Table structure for `msp_clinic_audit_detail`
-- ----------------------------
DROP TABLE IF EXISTS `msp_clinic_audit_detail`;
CREATE TABLE `msp_clinic_audit_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `detailId` bigint(20) DEFAULT NULL COMMENT '关联到门诊信息明细表',
  `clinicAuditId` bigint(20) DEFAULT NULL COMMENT '审核主表id',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `type` char(1) DEFAULT NULL COMMENT '1整体审核2医嘱审核 ',
  `ruleId` bigint(20) DEFAULT NULL COMMENT '规则ID',
  `ruleDescri` varchar(100) DEFAULT NULL COMMENT '规则描述',
  `auditResult` varchar(100) DEFAULT NULL COMMENT '审核输出',
  `knowledgeId` bigint(20) DEFAULT NULL COMMENT '对应知识库',
  `excepScore` int(11) DEFAULT NULL COMMENT '异常分',
  `doubtMoney` decimal(14,4) DEFAULT NULL COMMENT '可疑金额',
  `status` char(1) DEFAULT NULL COMMENT '1接受2忽略3增补 ',
  `auditorId` bigint(20) DEFAULT NULL COMMENT '审核人',
  `auditDate` datetime DEFAULT NULL COMMENT '审核日期',
  `indate` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_clinic2` (`clinicAuditId`)
) ENGINE=InnoDB AUTO_INCREMENT=846 DEFAULT CHARSET=utf8 COMMENT='门诊审核明细表';

-- ----------------------------
--  Table structure for `msp_clinic_autoaudit`
-- ----------------------------
DROP TABLE IF EXISTS `msp_clinic_autoaudit`;
CREATE TABLE `msp_clinic_autoaudit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `treatId` bigint(20) DEFAULT NULL COMMENT '关联到门诊信息主表 ',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `result` char(1) DEFAULT NULL COMMENT '1无问题2有异常3挂起 ',
  `excepScore` int(11) DEFAULT NULL COMMENT 'Sum(明细异常分) ',
  `cautionLevel` char(1) DEFAULT NULL COMMENT '根据异常分计算警示级别 ',
  `description` longtext COMMENT '综合审核明细生成综述 ',
  `doubtMoney` decimal(14,4) DEFAULT NULL COMMENT 'Sum(可疑明细金额) ',
  `revisionType` char(1) DEFAULT NULL COMMENT '0无修正1已修正 ',
  `auditId` bigint(20) DEFAULT NULL COMMENT 'system ',
  `auditDate` datetime DEFAULT NULL COMMENT '初审时间 ',
  `indate` datetime DEFAULT NULL COMMENT '生成记录时间 ',
  PRIMARY KEY (`id`),
  KEY `treatId_index` (`treatId`)
) ENGINE=InnoDB AUTO_INCREMENT=317 DEFAULT CHARSET=utf8 COMMENT='门诊机审主表';

-- ----------------------------
--  Table structure for `msp_clinic_autoaudit_detail`
-- ----------------------------
DROP TABLE IF EXISTS `msp_clinic_autoaudit_detail`;
CREATE TABLE `msp_clinic_autoaudit_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `clinicAuditId` bigint(20) DEFAULT NULL COMMENT '机审主id',
  `detailId` bigint(20) DEFAULT NULL COMMENT '关联到门诊信息明细表',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `type` char(1) DEFAULT NULL COMMENT '1整体审核2医嘱审核 ',
  `ruleId` bigint(20) DEFAULT NULL COMMENT '规则ID',
  `ruleDescri` varchar(100) DEFAULT NULL COMMENT '规则描述',
  `auditResult` varchar(100) DEFAULT NULL COMMENT '审核输出',
  `knowledgeId` bigint(20) DEFAULT NULL COMMENT '对应知识库',
  `excepScore` int(11) DEFAULT NULL COMMENT '异常分',
  `doubtMoney` decimal(14,4) DEFAULT NULL COMMENT '可疑金额',
  `status` char(1) DEFAULT NULL COMMENT '1接受2忽略3增补 ',
  `auditorId` bigint(20) DEFAULT NULL COMMENT '审核人',
  `auditDate` datetime DEFAULT NULL COMMENT '审核日期',
  `indate` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_clinic4` (`clinicAuditId`)
) ENGINE=InnoDB AUTO_INCREMENT=634 DEFAULT CHARSET=utf8 COMMENT='门诊机审明细表';

-- ----------------------------
--  Table structure for `msp_clinic_clarify`
-- ----------------------------
DROP TABLE IF EXISTS `msp_clinic_clarify`;
CREATE TABLE `msp_clinic_clarify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `treatId` bigint(20) DEFAULT NULL COMMENT '门诊就诊ID',
  `detailId` bigint(20) DEFAULT NULL COMMENT '门诊审核明细ID ',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `clarifyContent` longtext COMMENT '澄清内容',
  `clarifyPersonId` bigint(20) DEFAULT NULL COMMENT '澄清人',
  `clarifyDate` datetime DEFAULT NULL COMMENT '澄清时间',
  `acceptType` char(1) DEFAULT '0' COMMENT '0待复审1接受2不接受 ',
  `reAuditId` bigint(20) DEFAULT NULL COMMENT '复审人',
  `reAuditDate` datetime DEFAULT NULL COMMENT '复审时间',
  `indate` datetime DEFAULT NULL COMMENT '入库时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='门诊澄清表';

-- ----------------------------
--  Table structure for `msp_clinic_firstaudit`
-- ----------------------------
DROP TABLE IF EXISTS `msp_clinic_firstaudit`;
CREATE TABLE `msp_clinic_firstaudit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `treatId` bigint(20) DEFAULT NULL COMMENT '关联到门诊信息主表 ',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `result` char(1) DEFAULT NULL COMMENT '1无问题2有异常3挂起 ',
  `excepScore` int(11) DEFAULT NULL COMMENT 'Sum(明细异常分) ',
  `cautionLevel` char(1) DEFAULT NULL COMMENT '根据异常分计算警示级别 ',
  `description` longtext COMMENT '初审审核说明',
  `doubtMoney` decimal(14,4) DEFAULT NULL COMMENT 'Sum(可疑明细金额) ',
  `revisionType` char(1) DEFAULT NULL COMMENT '0无修正1已修正 ',
  `auditId` bigint(20) DEFAULT NULL COMMENT '初审人',
  `auditDate` datetime DEFAULT NULL COMMENT '初审时间 ',
  `indate` datetime DEFAULT NULL COMMENT '生成记录时间 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=207 DEFAULT CHARSET=utf8 COMMENT='门诊初审主表';

-- ----------------------------
--  Table structure for `msp_clinic_firstaudit_detail`
-- ----------------------------
DROP TABLE IF EXISTS `msp_clinic_firstaudit_detail`;
CREATE TABLE `msp_clinic_firstaudit_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `clinicAuditId` bigint(20) DEFAULT NULL COMMENT '初审主id',
  `detailId` bigint(20) DEFAULT NULL COMMENT '关联到门诊信息明细表',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `type` char(1) DEFAULT NULL COMMENT '1整体审核2医嘱审核 ',
  `ruleId` bigint(20) DEFAULT NULL COMMENT '规则ID',
  `ruleDescri` varchar(100) DEFAULT NULL COMMENT '规则描述',
  `auditResult` varchar(100) DEFAULT NULL COMMENT '审核输出',
  `knowledgeId` bigint(20) DEFAULT NULL COMMENT '对应知识库',
  `excepScore` int(11) DEFAULT NULL COMMENT '异常分',
  `doubtMoney` decimal(14,4) DEFAULT NULL COMMENT '可疑金额',
  `status` char(1) DEFAULT NULL COMMENT '1接受2忽略3增补 ',
  `auditorId` bigint(20) DEFAULT NULL COMMENT '审核人',
  `auditDate` datetime DEFAULT NULL COMMENT '审核日期',
  `indate` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_clinic8` (`clinicAuditId`)
) ENGINE=InnoDB AUTO_INCREMENT=295 DEFAULT CHARSET=utf8 COMMENT='门诊初审明细表';

-- ----------------------------
--  Table structure for `msp_clinic_info`
-- ----------------------------
DROP TABLE IF EXISTS `msp_clinic_info`;
CREATE TABLE `msp_clinic_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `originalId` varchar(50) DEFAULT NULL COMMENT '原业务数据的主键',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `areaName` varchar(100) DEFAULT NULL COMMENT '地区名称',
  `medicineCode` varchar(20) DEFAULT NULL COMMENT '医保证号码',
  `patientId` bigint(20) DEFAULT NULL COMMENT '病人id',
  `identifyId` varchar(20) DEFAULT NULL COMMENT '病人身份证号',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `sex` char(1) DEFAULT NULL COMMENT '1、男 2、女 3、其他',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `national` varchar(20) DEFAULT NULL COMMENT '民族',
  `newBornStatus` char(1) DEFAULT NULL COMMENT '1、是 0、否（默认） 新生儿状态',
  `height` varchar(20) DEFAULT NULL COMMENT '身高',
  `weight` varchar(20) DEFAULT NULL COMMENT '体重',
  `bornWeight` varchar(20) DEFAULT NULL COMMENT '出生体重',
  `pregStatus` char(1) DEFAULT NULL COMMENT '1、是 0、否（默认） 怀孕状态',
  `pregTime` int(11) DEFAULT NULL COMMENT '孕期',
  `momChildBind` char(1) DEFAULT NULL COMMENT '1、是 0、否（默认） 母婴绑定',
  `insuredType` char(3) DEFAULT NULL COMMENT '1、城镇职工 2、城镇居民 3、新农合 4、工伤 5、生育 6、子女统筹 0、其它',
  `clinicType` char(1) DEFAULT NULL COMMENT '1、门诊 2、急诊',
  `allergyDrugs` longtext COMMENT '过敏药物列表',
  `hospId` bigint(20) DEFAULT NULL COMMENT '医院ID',
  `hospName` varchar(100) DEFAULT NULL COMMENT '医院名称',
  `hospLevel` varchar(20) DEFAULT NULL COMMENT '医院等级',
  `doctorId` bigint(20) DEFAULT NULL COMMENT '医生ID',
  `doctorName` varchar(20) DEFAULT NULL COMMENT '医生姓名',
  `diagnoseAreaCode` varchar(30) DEFAULT NULL COMMENT '省级诊断编码',
  `diagnoseArea` varchar(30) DEFAULT NULL COMMENT '省级诊断名',
  `diagnoseStdCode` varchar(30) DEFAULT NULL COMMENT '基础诊断编码',
  `diagnoseStd` varchar(30) DEFAULT NULL COMMENT '基础诊断名',
  `isSingleDisease` char(1) DEFAULT NULL COMMENT '是否单病种   1、是  2、不是  3、其他',
  `payType` char(1) DEFAULT NULL COMMENT '报销类型（1门诊统筹、2门诊大病、3家庭账户）',
  `recipeDate` datetime DEFAULT NULL COMMENT '处方日期',
  `settleDate` datetime DEFAULT NULL COMMENT '结算日期',
  `totalCost` decimal(14,4) DEFAULT NULL COMMENT '总费用',
  `medicalCost` decimal(14,4) DEFAULT NULL COMMENT '医保总费用',
  `drugCost` decimal(14,4) DEFAULT NULL COMMENT '药品总费用',
  `drugInsuredCost` decimal(14,4) DEFAULT NULL COMMENT '药品保内费用',
  `serCost` decimal(14,4) DEFAULT NULL COMMENT '服务总费用',
  `serInsuredCost` decimal(14,4) DEFAULT NULL COMMENT '服务保内费用',
  `matCost` decimal(14,4) DEFAULT NULL COMMENT '材料总费用',
  `matInsuredCost` decimal(14,4) DEFAULT NULL COMMENT '材料保内总费用',
  `balanceCost` decimal(14,4) DEFAULT NULL COMMENT '统筹支付费用',
  `startingLine` decimal(14,4) DEFAULT NULL COMMENT '起付线',
  `balanceCostSum` decimal(14,4) DEFAULT NULL COMMENT '统筹支付累计',
  `disableType` char(1) DEFAULT NULL COMMENT '作废标记  1、正常 2、作废 0、已机审',
  `uploadDate` datetime DEFAULT NULL COMMENT '上传日期',
  `uploadUnit` varchar(100) DEFAULT NULL COMMENT '接入单位',
  `indate` datetime DEFAULT NULL COMMENT '入库日期',
  PRIMARY KEY (`id`),
  KEY `originalId_areaCode` (`originalId`,`areaCode`)
) ENGINE=InnoDB AUTO_INCREMENT=32278087 DEFAULT CHARSET=utf8 COMMENT='门诊信息主表';

-- ----------------------------
--  Table structure for `msp_clinic_info_detail`
-- ----------------------------
DROP TABLE IF EXISTS `msp_clinic_info_detail`;
CREATE TABLE `msp_clinic_info_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `originalId` varchar(50) DEFAULT NULL COMMENT '原始业务数据主键',
  `clinicInfoId` bigint(20) DEFAULT NULL COMMENT '主诊断id',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `hospItemCode` varchar(30) DEFAULT NULL COMMENT '医院项目编号',
  `hospItemName` varchar(100) DEFAULT NULL COMMENT '医院项目名称',
  `areaItemCode` varchar(30) DEFAULT NULL COMMENT '省级项目编码',
  `areaItemName` varchar(100) DEFAULT NULL COMMENT '省级项目名称',
  `coreItemCode` varchar(30) DEFAULT NULL COMMENT '基础项目编码',
  `coreItemName` varchar(100) DEFAULT NULL COMMENT '基础项目名称',
  `goodsName` varchar(100) DEFAULT NULL COMMENT '商品名',
  `dosage` char(1) DEFAULT NULL COMMENT '剂型（1、片剂，2、注射液)',
  `spec` varchar(50) DEFAULT NULL COMMENT '规格',
  `countNum` decimal(14,4) DEFAULT NULL COMMENT '含量数值',
  `countUnit` varchar(20) DEFAULT NULL COMMENT '含量计量单位',
  `packNum` int(11) DEFAULT NULL COMMENT '包装数值',
  `packUnit` varchar(20) DEFAULT NULL COMMENT '包装计量单位',
  `saleUnit` varchar(20) DEFAULT NULL COMMENT '销售单位',
  `unit` decimal(14,4) DEFAULT NULL COMMENT '单价',
  `count` double DEFAULT NULL COMMENT '数量',
  `cost` decimal(14,4) DEFAULT NULL COMMENT '小计',
  `drugRoute` char(2) DEFAULT NULL COMMENT '1、口服，2、静脉滴注',
  `drugDoseSingle` varchar(20) DEFAULT NULL COMMENT '单次给药剂量',
  `drugRate` varchar(20) DEFAULT NULL COMMENT '给药频率',
  `drugTime` varchar(20) DEFAULT NULL COMMENT '给药时机（饭前，饭后等）',
  `drugGoal` varchar(20) DEFAULT NULL COMMENT '用药目的（预防，治疗）',
  `groupNum` varchar(20) DEFAULT NULL COMMENT '组号',
  `type` char(1) DEFAULT NULL COMMENT '医嘱类型（1、长期 2、临时 0、其他）',
  `drugOrdersStart` datetime DEFAULT NULL COMMENT '药嘱开始时间',
  `drugOrdersEnd` datetime DEFAULT NULL COMMENT '药嘱结束时间',
  `exeDate` datetime DEFAULT NULL COMMENT '开始执行时间',
  `docterId` varchar(20) DEFAULT NULL COMMENT '医师工号',
  `doctorName` varchar(50) DEFAULT NULL COMMENT '医师姓名',
  `doctorTitle` varchar(50) DEFAULT NULL COMMENT '医师职称（主任医师，主治医师…)',
  `recipeDate` datetime DEFAULT NULL COMMENT '处方日期',
  `indate` datetime DEFAULT NULL COMMENT '入库日期',
  PRIMARY KEY (`id`),
  KEY `clinicInfoId_index` (`clinicInfoId`),
  KEY `originalId_areaCode` (`originalId`,`areaCode`)
) ENGINE=InnoDB AUTO_INCREMENT=183400492 DEFAULT CHARSET=utf8 COMMENT='门诊信息明细表';

-- ----------------------------
--  Table structure for `msp_clinic_reaudit`
-- ----------------------------
DROP TABLE IF EXISTS `msp_clinic_reaudit`;
CREATE TABLE `msp_clinic_reaudit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `treatId` bigint(20) DEFAULT NULL COMMENT '关联到门诊信息主表 ',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `result` char(1) DEFAULT NULL COMMENT '1无问题2有异常3挂起 ',
  `excepScore` int(11) DEFAULT NULL COMMENT 'Sum(明细异常分) ',
  `cautionLevel` char(1) DEFAULT NULL COMMENT '根据异常分计算警示级别 ',
  `description` longtext COMMENT '复审审核说明',
  `doubtMoney` decimal(14,4) DEFAULT NULL COMMENT 'Sum(可疑明细金额) ',
  `deductMoney` decimal(14,4) DEFAULT NULL COMMENT '扣款金额',
  `revisionType` char(1) DEFAULT NULL COMMENT '0无修正1已修正 ',
  `auditId` bigint(20) DEFAULT NULL COMMENT '复审人',
  `auditDate` datetime DEFAULT NULL COMMENT '复审时间 ',
  `indate` datetime DEFAULT NULL COMMENT '生成记录时间 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='门诊复审主表';

-- ----------------------------
--  Table structure for `msp_clinic_reaudit_detail`
-- ----------------------------
DROP TABLE IF EXISTS `msp_clinic_reaudit_detail`;
CREATE TABLE `msp_clinic_reaudit_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `clinicAuditId` bigint(20) DEFAULT NULL COMMENT '门诊审核主表id',
  `detailId` bigint(20) DEFAULT NULL COMMENT '关联到门诊信息明细表',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `type` char(1) DEFAULT NULL COMMENT '1整体审核2医嘱审核 ',
  `ruleId` bigint(20) DEFAULT NULL COMMENT '规则ID',
  `ruleDescri` varchar(100) DEFAULT NULL COMMENT '规则描述',
  `auditResult` varchar(100) DEFAULT NULL COMMENT '审核输出',
  `knowledgeId` bigint(20) DEFAULT NULL COMMENT '对应知识库',
  `excepScore` int(11) DEFAULT NULL COMMENT '异常分',
  `doubtMoney` decimal(14,4) DEFAULT NULL COMMENT '可疑金额',
  `status` char(1) DEFAULT NULL COMMENT '1接受2忽略3增补 ',
  `auditorId` bigint(20) DEFAULT NULL COMMENT '审核人',
  `auditDate` datetime DEFAULT NULL COMMENT '审核日期',
  `indate` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_clinic11` (`clinicAuditId`)
) ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8 COMMENT='门诊复审细表';

-- ----------------------------
--  Table structure for `msp_dict_catalog_area`
-- ----------------------------
DROP TABLE IF EXISTS `msp_dict_catalog_area`;
CREATE TABLE `msp_dict_catalog_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(30) NOT NULL COMMENT '编码',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `fcode` varchar(30) NOT NULL COMMENT '父编码',
  `dosageForms` varchar(30) DEFAULT NULL COMMENT '剂型',
  `pattern` varchar(10) NOT NULL COMMENT '1药品 2服务 3材料',
  `spec` varchar(30) DEFAULT NULL COMMENT '规格',
  `stdCode` varchar(30) DEFAULT NULL COMMENT '标准目录标识',
  `compareStatus` varchar(10) NOT NULL COMMENT '0 未对比 1 已对比',
  `isValidate` char(1) NOT NULL DEFAULT '1' COMMENT '0 未启用 1 启用 ',
  `areaCode` varchar(6) NOT NULL COMMENT '区划代码',
  `userId` bigint(20) NOT NULL COMMENT '创建人',
  `indate` datetime NOT NULL COMMENT '入库时间',
  `updateUserId` bigint(20) DEFAULT NULL COMMENT '最后变更人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='省级目录';

-- ----------------------------
--  Table structure for `msp_dict_catalog_std`
-- ----------------------------
DROP TABLE IF EXISTS `msp_dict_catalog_std`;
CREATE TABLE `msp_dict_catalog_std` (
  `code` varchar(30) NOT NULL COMMENT '编码',
  `fcode` varchar(30) NOT NULL COMMENT '父类编码',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `dosageForms` varchar(30) DEFAULT NULL COMMENT '类型为1药品时才有这个字段',
  `pattern` char(1) NOT NULL COMMENT '1药品，2服务，3材料',
  `spec` varchar(50) DEFAULT NULL COMMENT '规格',
  `pcode` varchar(50) DEFAULT NULL COMMENT '拼音码',
  `userId` bigint(20) NOT NULL COMMENT '创建人',
  `indate` datetime NOT NULL COMMENT '入库时间',
  `updateUserId` bigint(20) DEFAULT NULL COMMENT '最后变更人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `isValidate` char(1) NOT NULL DEFAULT '1' COMMENT '0 无效 1 有效 ',
  PRIMARY KEY (`code`),
  KEY `idx_msp_dict_catalog_std_fcode` (`fcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标准目录';

-- ----------------------------
--  Table structure for `msp_dict_drug_book`
-- ----------------------------
DROP TABLE IF EXISTS `msp_dict_drug_book`;
CREATE TABLE `msp_dict_drug_book` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `detailContentId` bigint(20) DEFAULT NULL COMMENT '对应详细说明书表ID',
  `code` varchar(30) NOT NULL COMMENT '编码',
  `manufacturer` varchar(50) DEFAULT NULL COMMENT '制造商',
  `approvalNumber` varchar(50) DEFAULT NULL COMMENT '批准文号',
  `maxPrice` decimal(14,4) DEFAULT NULL COMMENT '最高价',
  `minPrice` decimal(14,4) DEFAULT NULL COMMENT '最低价',
  `avgPrice` decimal(14,4) DEFAULT NULL COMMENT '平均价',
  `codeName` varchar(50) DEFAULT NULL COMMENT '代号',
  `userId` bigint(20) NOT NULL COMMENT '创建人',
  `indate` datetime NOT NULL COMMENT '入库时间',
  `updateUserId` bigint(20) DEFAULT NULL COMMENT '最后变更人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_4` (`detailContentId`),
  KEY `FK_Reference_5` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='药品说明书';

-- ----------------------------
--  Table structure for `msp_dict_drug_detail`
-- ----------------------------
DROP TABLE IF EXISTS `msp_dict_drug_detail`;
CREATE TABLE `msp_dict_drug_detail` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `detailContent` longtext COMMENT '详细内容',
  `approvalNumber` varchar(50) DEFAULT NULL COMMENT '批准文号',
  `userId` bigint(20) DEFAULT NULL COMMENT '创建人',
  `indate` datetime DEFAULT NULL COMMENT '入库时间',
  `updateUserId` bigint(20) DEFAULT NULL COMMENT '最后变更人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='药品说明书详细内容';

-- ----------------------------
--  Table structure for `msp_dict_icd_area`
-- ----------------------------
DROP TABLE IF EXISTS `msp_dict_icd_area`;
CREATE TABLE `msp_dict_icd_area` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(30) NOT NULL COMMENT '编码',
  `fcode` varchar(30) NOT NULL,
  `name` varchar(100) NOT NULL COMMENT '名称',
  `stdCode` varchar(30) DEFAULT NULL COMMENT '标准诊断标识',
  `compareStatus` smallint(6) NOT NULL DEFAULT '0' COMMENT '0 未对比 1 已对比',
  `areaCode` varchar(6) NOT NULL COMMENT '区划代码',
  `userId` bigint(20) NOT NULL COMMENT '创建人',
  `indate` datetime NOT NULL COMMENT '入库时间',
  `updateUserId` bigint(20) DEFAULT NULL COMMENT '最后变更人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `isValidate` char(1) NOT NULL DEFAULT '1' COMMENT '0 无效 1 有效 ',
  PRIMARY KEY (`id`),
  KEY `idx_msp_dict_icd_area_code` (`code`),
  KEY `idx_msp_dict_icd_area_fcode` (`fcode`),
  KEY `FK_Reference_2` (`stdCode`)
) ENGINE=InnoDB AUTO_INCREMENT=65539 DEFAULT CHARSET=utf8 COMMENT='省级诊断目录';

-- ----------------------------
--  Table structure for `msp_dict_icd_std`
-- ----------------------------
DROP TABLE IF EXISTS `msp_dict_icd_std`;
CREATE TABLE `msp_dict_icd_std` (
  `code` varchar(30) NOT NULL COMMENT '编码',
  `fcode` varchar(30) NOT NULL COMMENT '父类编码',
  `name` varchar(100) NOT NULL COMMENT '诊断名称',
  `rank` char(1) DEFAULT NULL COMMENT '等级',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `userId` bigint(20) NOT NULL COMMENT '创建人',
  `indate` datetime DEFAULT NULL COMMENT '入库时间',
  `updateUserId` bigint(20) DEFAULT NULL COMMENT '最后变更人',
  `updateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `isValidate` char(1) NOT NULL DEFAULT '1' COMMENT '0 无效 1 有效 ',
  PRIMARY KEY (`code`),
  KEY `idx_msp_dict_icd_std_fcode` (`fcode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='标准诊断目录';

-- ----------------------------
--  Table structure for `msp_dict_knowledge`
-- ----------------------------
DROP TABLE IF EXISTS `msp_dict_knowledge`;
CREATE TABLE `msp_dict_knowledge` (
  `code` varchar(30) NOT NULL COMMENT '编码',
  `fcode` varchar(30) NOT NULL COMMENT '父类编码',
  `name` varchar(100) DEFAULT NULL COMMENT '文件名',
  `remark` varchar(100) DEFAULT NULL COMMENT '文件说明',
  `unit` varchar(50) DEFAULT NULL COMMENT '发布单位',
  `releaseTime` datetime DEFAULT NULL COMMENT '发布日期',
  `content` varchar(100) DEFAULT NULL COMMENT '文件内容',
  `userId` bigint(20) DEFAULT NULL COMMENT '创建人',
  `indate` datetime DEFAULT NULL COMMENT '入库时间',
  `updateUserId` bigint(20) DEFAULT NULL COMMENT '最后变更人',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件知识库信息表';

-- ----------------------------
--  Table structure for `msp_dict_knowledge_abstract`
-- ----------------------------
DROP TABLE IF EXISTS `msp_dict_knowledge_abstract`;
CREATE TABLE `msp_dict_knowledge_abstract` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `code` varchar(30) NOT NULL COMMENT '编码',
  `title` varchar(30) DEFAULT NULL COMMENT '信息标题',
  `remark` varchar(200) DEFAULT NULL COMMENT '信息说明',
  `ruleType` char(1) DEFAULT NULL COMMENT '建议归属分类（A类/B类/C类）',
  `ruleScore` int(2) DEFAULT NULL COMMENT '建议规则分值',
  `userId` bigint(20) DEFAULT NULL COMMENT '创建人',
  `indate` datetime DEFAULT NULL COMMENT '入库时间',
  `updateUserId` bigint(20) DEFAULT NULL COMMENT '最后变更人',
  `updateTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `fcode` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_7` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 COMMENT='文件知识库信息提炼表 ';

-- ----------------------------
--  Table structure for `msp_hosp_audit`
-- ----------------------------
DROP TABLE IF EXISTS `msp_hosp_audit`;
CREATE TABLE `msp_hosp_audit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `treatId` bigint(20) DEFAULT NULL COMMENT '关联到住院信息主表 ',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `insuredType` char(1) DEFAULT NULL COMMENT '1、城镇职工 2、城镇居民 3、新农合 4、工伤 5、生育 6、子女统筹 0、其它',
  `status` char(1) DEFAULT NULL COMMENT '1已机审2已初审3已复审4归档',
  `result` char(1) DEFAULT NULL COMMENT '1正常2违规3挂起 4调查',
  `excepScore` int(11) DEFAULT NULL COMMENT 'Sum(明细异常分) ',
  `cautionLevel` char(1) DEFAULT NULL COMMENT '根据异常分计算警示级别 ',
  `description` longtext COMMENT '综合审核明细生成综述 ',
  `doubtMoney` decimal(14,4) DEFAULT NULL COMMENT 'Sum(可疑明细金额) ',
  `deductMoney` decimal(14,4) DEFAULT NULL COMMENT '扣款金额',
  `writeBackStatus` char(1) DEFAULT NULL COMMENT '1未回写2初审已回写3复审已回写 ',
  `clarifyType` char(1) DEFAULT NULL COMMENT '1无需澄清2待澄清3已澄清 ',
  `firstAuditId` bigint(20) DEFAULT NULL COMMENT '初审人',
  `finalAuditId` bigint(20) DEFAULT NULL COMMENT '终审人',
  `indate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateDate` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=257 DEFAULT CHARSET=utf8 COMMENT='住院审核主表';

-- ----------------------------
--  Table structure for `msp_hosp_audit_detail`
-- ----------------------------
DROP TABLE IF EXISTS `msp_hosp_audit_detail`;
CREATE TABLE `msp_hosp_audit_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `detailId` bigint(20) DEFAULT NULL COMMENT '关联到业务原始明细表(可空) ',
  `hospAuditId` bigint(20) DEFAULT NULL COMMENT '住院审核id',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `type` char(1) DEFAULT NULL COMMENT '1整体审核2医嘱审核 ',
  `ruleId` bigint(20) DEFAULT NULL COMMENT '规则ID',
  `ruleDescri` varchar(100) DEFAULT NULL COMMENT '规则描述',
  `auditResult` varchar(100) DEFAULT NULL COMMENT '审核输出',
  `knowledgeId` bigint(20) DEFAULT NULL COMMENT '对应知识库',
  `excepScore` int(11) DEFAULT NULL COMMENT '异常分',
  `doubtMoney` decimal(14,4) DEFAULT NULL COMMENT '可疑金额',
  `status` char(1) DEFAULT NULL COMMENT '1接受2忽略3增补 ',
  `auditorId` bigint(20) DEFAULT NULL COMMENT '审核人',
  `auditDate` datetime DEFAULT NULL COMMENT '审核日期',
  `indate` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45316 DEFAULT CHARSET=utf8 COMMENT='住院审核明细表';

-- ----------------------------
--  Table structure for `msp_hosp_autoaudit`
-- ----------------------------
DROP TABLE IF EXISTS `msp_hosp_autoaudit`;
CREATE TABLE `msp_hosp_autoaudit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `treatId` bigint(20) DEFAULT NULL COMMENT '关联到住院信息主表 主键',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `result` char(1) DEFAULT NULL COMMENT '1无问题2有异常3挂起 ',
  `excepScore` int(11) DEFAULT NULL COMMENT 'Sum(明细异常分) ',
  `cautionLevel` char(1) DEFAULT NULL COMMENT '根据异常分计算警示级别 ',
  `description` longtext COMMENT '综合审核明细生成综述 ',
  `doubtMoney` decimal(14,4) DEFAULT NULL COMMENT 'Sum(可疑明细金额) ',
  `revisionType` char(1) DEFAULT NULL COMMENT '0无修正1已修正 ',
  `auditId` bigint(20) DEFAULT NULL COMMENT 'system ',
  `auditDate` datetime DEFAULT NULL COMMENT '初审时间 ',
  `indate` datetime DEFAULT NULL COMMENT '生成记录时间 ',
  PRIMARY KEY (`id`),
  KEY `idx_ msp_hosp_autoaudit_treatid` (`treatId`)
) ENGINE=InnoDB AUTO_INCREMENT=376 DEFAULT CHARSET=utf8 COMMENT='住院机审主表 ';

-- ----------------------------
--  Table structure for `msp_hosp_autoaudit_detail`
-- ----------------------------
DROP TABLE IF EXISTS `msp_hosp_autoaudit_detail`;
CREATE TABLE `msp_hosp_autoaudit_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `hospAuditId` bigint(20) DEFAULT NULL COMMENT '机审主id',
  `detailId` bigint(20) DEFAULT NULL COMMENT '关联到住院信息明细表 主键',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `type` char(1) DEFAULT NULL COMMENT '1整体审核2医嘱审核 ',
  `ruleId` bigint(20) DEFAULT NULL COMMENT '规则ID',
  `ruleDescri` varchar(100) DEFAULT NULL COMMENT '规则描述',
  `auditResult` varchar(100) DEFAULT NULL COMMENT '审核输出',
  `knowledgeId` bigint(20) DEFAULT NULL COMMENT '对应知识库',
  `excepScore` int(11) DEFAULT NULL COMMENT '异常分',
  `doubtMoney` decimal(14,4) DEFAULT NULL COMMENT '可疑金额',
  `status` char(1) DEFAULT NULL COMMENT '1接受2忽略3增补 ',
  `auditorId` bigint(20) DEFAULT NULL COMMENT '审核人',
  `auditDate` datetime DEFAULT NULL COMMENT '审核日期',
  `indate` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_hosp4` (`hospAuditId`)
) ENGINE=InnoDB AUTO_INCREMENT=49371 DEFAULT CHARSET=utf8 COMMENT='住院机审明细表';

-- ----------------------------
--  Table structure for `msp_hosp_clarify`
-- ----------------------------
DROP TABLE IF EXISTS `msp_hosp_clarify`;
CREATE TABLE `msp_hosp_clarify` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `treatId` bigint(20) DEFAULT NULL COMMENT '住院就诊ID',
  `detailId` bigint(20) DEFAULT NULL COMMENT '住院审核明细ID ',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `clarifyContent` longtext COMMENT '澄清内容',
  `clarifyPersonId` bigint(20) DEFAULT NULL COMMENT '澄清人',
  `clarifyDate` datetime DEFAULT NULL COMMENT '澄清时间',
  `acceptType` char(1) DEFAULT '0' COMMENT '0待复审1接受2不接受 ',
  `reAuditId` bigint(20) DEFAULT NULL COMMENT '复审人',
  `reAuditDate` datetime DEFAULT NULL COMMENT '复审时间',
  `indate` datetime DEFAULT NULL COMMENT '入库时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=123 DEFAULT CHARSET=utf8 COMMENT='住院澄清表';

-- ----------------------------
--  Table structure for `msp_hosp_diagnose`
-- ----------------------------
DROP TABLE IF EXISTS `msp_hosp_diagnose`;
CREATE TABLE `msp_hosp_diagnose` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `treatId` bigint(20) DEFAULT NULL COMMENT '住院信息主表主键',
  `originalId` varchar(50) DEFAULT NULL COMMENT '原记录id',
  `diagnoseCode` varchar(30) DEFAULT NULL COMMENT '诊断编码 如：ICD9-CM3',
  `diagnoseName` varchar(30) DEFAULT NULL COMMENT '诊断名称',
  `diagnoseAreaCode` varchar(30) DEFAULT NULL COMMENT '诊断编码(省级)',
  `diagnoseAreaName` varchar(30) DEFAULT NULL COMMENT '诊断名称(省级)',
  `diagnoseOrder` varchar(30) DEFAULT NULL COMMENT '诊断顺序',
  `diagnoseType` char(1) DEFAULT NULL COMMENT '1、门诊2、急诊，3、入院，4、出院，0、其他',
  `outHospResult` char(1) DEFAULT NULL COMMENT '1、治愈，2、好转，3、未愈，4、死亡，0、其他',
  `diagnoseDate` datetime DEFAULT NULL COMMENT '诊断时间',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `indate` datetime DEFAULT NULL COMMENT '入库日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='住院诊断表';

-- ----------------------------
--  Table structure for `msp_hosp_firstaudit`
-- ----------------------------
DROP TABLE IF EXISTS `msp_hosp_firstaudit`;
CREATE TABLE `msp_hosp_firstaudit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `treatId` bigint(20) DEFAULT NULL COMMENT '关联到住院信息主表 主键',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `result` char(1) DEFAULT NULL COMMENT '1无问题2有异常3挂起 ',
  `excepScore` int(11) DEFAULT NULL COMMENT 'Sum(明细异常分) ',
  `cautionLevel` char(1) DEFAULT NULL COMMENT '根据异常分计算警示级别 ',
  `description` longtext COMMENT '初审审核说明',
  `doubtMoney` decimal(14,4) DEFAULT NULL COMMENT 'Sum(可疑明细金额) ',
  `revisionType` char(1) DEFAULT NULL COMMENT '0无修正1已修正 ',
  `auditId` bigint(20) DEFAULT NULL COMMENT '初审人',
  `auditDate` datetime DEFAULT NULL COMMENT '初审时间 ',
  `indate` datetime DEFAULT NULL COMMENT '生成记录时间 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 COMMENT='住院初审主表';

-- ----------------------------
--  Table structure for `msp_hosp_firstaudit_detail`
-- ----------------------------
DROP TABLE IF EXISTS `msp_hosp_firstaudit_detail`;
CREATE TABLE `msp_hosp_firstaudit_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `hospAuditId` bigint(20) DEFAULT NULL COMMENT '初审主id',
  `detailId` bigint(20) DEFAULT NULL COMMENT '关联到住院信息明细表 主键',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `type` char(1) DEFAULT NULL COMMENT '1整体审核2医嘱审核 ',
  `ruleId` bigint(20) DEFAULT NULL COMMENT '规则ID',
  `ruleDescri` varchar(100) DEFAULT NULL COMMENT '规则描述',
  `auditResult` varchar(100) DEFAULT NULL COMMENT '审核输出',
  `knowledgeId` bigint(20) DEFAULT NULL COMMENT '对应知识库',
  `excepScore` int(11) DEFAULT NULL COMMENT '异常分',
  `doubtMoney` decimal(14,4) DEFAULT NULL COMMENT '可疑金额',
  `status` char(1) DEFAULT NULL COMMENT '1接受2忽略3增补 ',
  `auditorId` bigint(20) DEFAULT NULL COMMENT '审核人',
  `auditDate` datetime DEFAULT NULL COMMENT '审核日期',
  `indate` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_hosp9` (`hospAuditId`)
) ENGINE=InnoDB AUTO_INCREMENT=3499 DEFAULT CHARSET=utf8 COMMENT='住院初审明细表';

-- ----------------------------
--  Table structure for `msp_hosp_info`
-- ----------------------------
DROP TABLE IF EXISTS `msp_hosp_info`;
CREATE TABLE `msp_hosp_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `originalId` varchar(50) DEFAULT NULL COMMENT '原业务数据的主键',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `areaName` varchar(100) DEFAULT NULL COMMENT '地区名称',
  `medicineCode` varchar(20) DEFAULT NULL COMMENT '医保证号码',
  `patientId` bigint(20) DEFAULT NULL COMMENT '病人id',
  `identifyId` varchar(20) DEFAULT NULL COMMENT '病人身份证号',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `sex` char(1) DEFAULT NULL COMMENT '1、男 2、女 3、其他',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `title` varchar(100) DEFAULT NULL COMMENT '职称',
  `national` varchar(20) DEFAULT NULL COMMENT '民族',
  `newBornStatus` char(1) DEFAULT NULL COMMENT '1、是 0、否（默认） 新生儿状态',
  `height` varchar(20) DEFAULT NULL COMMENT '身高',
  `weight` varchar(20) DEFAULT NULL COMMENT '体重',
  `bornWeight` varchar(20) DEFAULT NULL COMMENT '出生体重',
  `pregStatus` char(1) DEFAULT NULL COMMENT '1、是 0、否（默认） 怀孕状态',
  `pregTime` int(11) DEFAULT NULL COMMENT '孕期',
  `momChildBind` char(1) DEFAULT NULL COMMENT '1、是 0、否（默认） 母婴绑定',
  `insuredType` char(1) DEFAULT NULL COMMENT '1、城镇职工 2、城镇居民 3、新农合 4、工伤 5、生育 6、子女统筹 0、其它',
  `payType` char(1) DEFAULT NULL COMMENT '1、住院统筹 2、特殊病种',
  `allergyDrugs` longtext COMMENT '过敏药物列表',
  `hospId` bigint(20) DEFAULT NULL COMMENT '医院ID',
  `hospName` varchar(100) DEFAULT NULL COMMENT '医院名称',
  `hospLevel` varchar(20) DEFAULT NULL COMMENT '医院等级',
  `inHospDate` datetime DEFAULT NULL COMMENT '入院日期',
  `outHospDate` datetime DEFAULT NULL COMMENT '出院日期',
  `inHospDep` varchar(100) DEFAULT NULL COMMENT '入院科室',
  `outHospDep` varchar(100) DEFAULT NULL COMMENT '出院科室',
  `inHospMode` char(1) DEFAULT NULL COMMENT '1、急诊 2、门诊 3、转院 0、其他',
  `inHospStatus` char(1) DEFAULT NULL COMMENT '1、有 2、临床未确定  3、情况不明 4、无',
  `outHospMode` char(1) DEFAULT NULL COMMENT '出院方式',
  `outHospStatus` char(1) DEFAULT NULL COMMENT '出院状态',
  `outHospDiagMain` varchar(30) DEFAULT NULL COMMENT '出院主诊断',
  `outHospDiagOther` varchar(30) DEFAULT NULL COMMENT '出院其他诊断',
  `mainDoctorId` bigint(20) DEFAULT NULL COMMENT '主治医生ID',
  `mianDoctorName` varchar(20) DEFAULT NULL COMMENT '主治医生姓名',
  `medicTeam` longtext COMMENT '医疗组',
  `rescueSum` varchar(10) DEFAULT NULL COMMENT '抢救次数',
  `pathoDiagnoseAreaCode` varchar(30) DEFAULT NULL COMMENT '病理诊断编码（省级）',
  `pathoDiagnoseArea` varchar(30) DEFAULT NULL COMMENT '病理诊断（省级）',
  `pathoDiagnoseStdCode` varchar(30) DEFAULT NULL COMMENT '病理诊断编码（基础）',
  `pathoDiagnoseStd` varchar(30) DEFAULT NULL COMMENT '病理诊断（基础）',
  `mainDiagnoseAreaCode` varchar(30) DEFAULT NULL COMMENT '主诊断编码（省级）',
  `mainDiagnoseArea` varchar(30) DEFAULT NULL COMMENT '主诊断（省级）',
  `mainDiagnoseStdCode` varchar(30) DEFAULT NULL COMMENT '主诊断编码（基础）',
  `mainDiagnoseStd` varchar(30) DEFAULT NULL COMMENT '主诊断（基础）',
  `isSingleDisease` char(1) DEFAULT NULL COMMENT '是否单病种   1、是  2、不是  3、其他',
  `settleDate` datetime DEFAULT NULL COMMENT '结算日期',
  `totalCost` decimal(14,4) DEFAULT NULL COMMENT '总费用',
  `medicalCost` decimal(14,4) DEFAULT NULL COMMENT '医保总费用',
  `drugCost` decimal(14,4) DEFAULT NULL COMMENT '药品总费用',
  `drugInsuredCost` decimal(14,4) DEFAULT NULL COMMENT '药品保内费用',
  `serCost` decimal(14,4) DEFAULT NULL COMMENT '服务总费用',
  `serInsuredCost` decimal(14,4) DEFAULT NULL COMMENT '服务保内费用',
  `matCost` decimal(14,4) DEFAULT NULL COMMENT '材料总费用',
  `matInsuredCost` decimal(14,4) DEFAULT NULL COMMENT '材料保内总费用',
  `balanceCost` decimal(14,4) DEFAULT NULL COMMENT '统筹支付费用',
  `startingLine` decimal(14,4) DEFAULT NULL COMMENT '起付线',
  `balanceCostSum` decimal(14,4) DEFAULT NULL COMMENT '统筹支付累计',
  `disableType` char(1) DEFAULT NULL COMMENT '作废标记  0、上传完成 1、部分上传 2、仅主记录上传3、已机审',
  `uploadDate` datetime DEFAULT NULL COMMENT '上传日期',
  `uploadUnit` varchar(100) DEFAULT NULL COMMENT '接入单位',
  `indate` datetime DEFAULT NULL COMMENT '入库日期',
  PRIMARY KEY (`id`),
  KEY `originalId_areaCode` (`originalId`,`areaCode`)
) ENGINE=InnoDB AUTO_INCREMENT=639231378 DEFAULT CHARSET=utf8 COMMENT='住院信息主表';

-- ----------------------------
--  Table structure for `msp_hosp_info_detail`
-- ----------------------------
DROP TABLE IF EXISTS `msp_hosp_info_detail`;
CREATE TABLE `msp_hosp_info_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `originalId` varchar(50) DEFAULT NULL COMMENT '原始业务数据主键',
  `hospInfoId` bigint(20) DEFAULT NULL COMMENT '主诊断id',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `hospItemCode` varchar(30) DEFAULT NULL COMMENT '医院项目编号',
  `hospItemName` varchar(100) DEFAULT NULL COMMENT '医院项目名称',
  `areaItemCode` varchar(30) DEFAULT NULL COMMENT '省级项目编码',
  `areaItemName` varchar(100) DEFAULT NULL COMMENT '省级项目名称',
  `coreItemCode` varchar(30) DEFAULT NULL COMMENT '基础项目编码',
  `coreItemName` varchar(100) DEFAULT NULL COMMENT '基础项目名称',
  `goodsName` varchar(100) DEFAULT NULL COMMENT '商品名',
  `dosage` char(1) DEFAULT NULL COMMENT '1、片剂 2、注射液',
  `spec` varchar(50) DEFAULT NULL COMMENT '规格',
  `countNum` decimal(14,4) DEFAULT NULL COMMENT '含量数值',
  `countUnit` varchar(20) DEFAULT NULL COMMENT '含量计量单位',
  `packNum` int(11) DEFAULT NULL COMMENT '包装数值',
  `packUnit` varchar(20) DEFAULT NULL COMMENT '包装计量单位',
  `saleUnit` varchar(20) DEFAULT NULL COMMENT '销售单位',
  `unit` decimal(14,4) DEFAULT NULL COMMENT '单价',
  `count` double DEFAULT NULL COMMENT '数量',
  `cost` decimal(14,4) DEFAULT NULL COMMENT '小计',
  `drugRoute` char(2) DEFAULT NULL COMMENT '1、口服，2、静脉滴注',
  `drugDoseSingle` varchar(20) DEFAULT NULL COMMENT '单次给药剂量',
  `drugRate` varchar(20) DEFAULT NULL COMMENT '给药频率',
  `drugTime` varchar(20) DEFAULT NULL COMMENT '给药时机（饭前，饭后等）',
  `drugGoal` varchar(20) DEFAULT NULL COMMENT '用药目的（预防，治疗）',
  `groupNum` varchar(20) DEFAULT NULL COMMENT '组号',
  `type` char(1) DEFAULT NULL COMMENT '医嘱类型（1、长期 2、临时 0、其他）',
  `drugOrdersStart` datetime DEFAULT NULL COMMENT '药嘱开始时间',
  `drugOrdersEnd` datetime DEFAULT NULL COMMENT '药嘱结束时间',
  `exeDate` datetime DEFAULT NULL COMMENT '开始执行时间',
  `docterId` varchar(20) DEFAULT NULL COMMENT '医师工号',
  `doctorName` varchar(50) DEFAULT NULL COMMENT '医师姓名',
  `doctorTitle` varchar(50) DEFAULT NULL COMMENT '医师职称（主任医师，主治医师…)',
  `recipeDate` datetime DEFAULT NULL COMMENT '处方日期',
  `indate` datetime DEFAULT NULL COMMENT '入库日期',
  PRIMARY KEY (`id`),
  KEY `idx_clinic_autoaudit_hospinfoid` (`hospInfoId`),
  KEY `originalId_areaCode` (`originalId`,`areaCode`)
) ENGINE=InnoDB AUTO_INCREMENT=639239643 DEFAULT CHARSET=utf8 COMMENT='住院信息明细表';

-- ----------------------------
--  Table structure for `msp_hosp_operation`
-- ----------------------------
DROP TABLE IF EXISTS `msp_hosp_operation`;
CREATE TABLE `msp_hosp_operation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `treatId` bigint(20) DEFAULT NULL COMMENT '住院信息主表主键',
  `originalId` varchar(50) DEFAULT NULL COMMENT '原记录id',
  `operationCode` varchar(30) DEFAULT NULL COMMENT '手术编码 如：ICD9-CM3',
  `operationName` varchar(30) DEFAULT NULL COMMENT '手术名称',
  `operationAreaCode` varchar(30) DEFAULT NULL COMMENT '手术编码(省级)',
  `operationAreaName` varchar(30) DEFAULT NULL COMMENT '手术名称(省级)',
  `operationOrder` varchar(30) DEFAULT NULL COMMENT '手术顺序',
  `operationDate` datetime DEFAULT NULL COMMENT '手术单日期',
  `type` varchar(10) DEFAULT NULL COMMENT 'I，II，III类切口',
  `cureType` varchar(20) DEFAULT NULL COMMENT '愈合状态',
  `isImplantation` char(1) DEFAULT NULL COMMENT '1、有 0、否',
  `operationStart` datetime DEFAULT NULL COMMENT '开始时间',
  `operationEnd` datetime DEFAULT NULL COMMENT '手术结束时间',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '所属地区',
  `indate` datetime DEFAULT NULL COMMENT '入库时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='住院手术表';

-- ----------------------------
--  Table structure for `msp_hosp_reaudit`
-- ----------------------------
DROP TABLE IF EXISTS `msp_hosp_reaudit`;
CREATE TABLE `msp_hosp_reaudit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `treatId` bigint(20) DEFAULT NULL COMMENT '关联到住院信息主表 主键',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `result` char(1) DEFAULT NULL COMMENT '1无问题2有异常3挂起 ',
  `excepScore` int(11) DEFAULT NULL COMMENT 'Sum(明细异常分) ',
  `cautionLevel` char(1) DEFAULT NULL COMMENT '根据异常分计算警示级别 ',
  `description` longtext COMMENT '复审审核说明',
  `doubtMoney` decimal(14,4) DEFAULT NULL COMMENT 'Sum(可疑明细金额) ',
  `deductMoney` decimal(14,4) DEFAULT NULL COMMENT '扣款金额',
  `revisionType` char(1) DEFAULT NULL COMMENT '0无修正1已修正 ',
  `auditId` bigint(20) DEFAULT NULL COMMENT '复审人',
  `auditDate` datetime DEFAULT NULL COMMENT '复审时间 ',
  `indate` datetime DEFAULT NULL COMMENT '生成记录时间 ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COMMENT='住院复审主表';

-- ----------------------------
--  Table structure for `msp_hosp_reaudit_detail`
-- ----------------------------
DROP TABLE IF EXISTS `msp_hosp_reaudit_detail`;
CREATE TABLE `msp_hosp_reaudit_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `hospAuditId` bigint(20) DEFAULT NULL COMMENT '复审主id',
  `detailId` bigint(20) DEFAULT NULL COMMENT '关联到住院信息明细表 主键',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `type` char(1) DEFAULT NULL COMMENT '1整体审核2医嘱审核 ',
  `ruleId` bigint(20) DEFAULT NULL COMMENT '规则ID',
  `ruleDescri` varchar(100) DEFAULT NULL COMMENT '规则描述',
  `auditResult` varchar(100) DEFAULT NULL COMMENT '审核输出',
  `knowledgeId` bigint(20) DEFAULT NULL COMMENT '对应知识库',
  `excepScore` int(11) DEFAULT NULL COMMENT '异常分',
  `doubtMoney` decimal(14,4) DEFAULT NULL COMMENT '可疑金额',
  `status` char(1) DEFAULT NULL COMMENT '1接受2忽略3增补 ',
  `auditorId` bigint(20) DEFAULT NULL COMMENT '审核人',
  `auditDate` datetime DEFAULT NULL COMMENT '审核日期',
  `indate` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_hosp13` (`hospAuditId`)
) ENGINE=InnoDB AUTO_INCREMENT=627 DEFAULT CHARSET=utf8 COMMENT='住院复审细表 ';

-- ----------------------------
--  Table structure for `msp_report`
-- ----------------------------
DROP TABLE IF EXISTS `msp_report`;
CREATE TABLE `msp_report` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(100) DEFAULT NULL COMMENT '名称',
  `reportMonth` char(7) DEFAULT NULL COMMENT '报告月度',
  `reportPath` varchar(200) DEFAULT NULL COMMENT '报告存放地址',
  `areaCode` varchar(20) DEFAULT NULL COMMENT '地区编码',
  `insuredType` char(3) DEFAULT NULL COMMENT '1、城镇职工 2、城镇居民 3、新农合 4、工伤 5、生育 6、子女统筹 0、其它',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `userId` bigint(20) DEFAULT NULL COMMENT '创建人',
  `indate` datetime DEFAULT NULL COMMENT '入库时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `msp_sydept_cfg`
-- ----------------------------
DROP TABLE IF EXISTS `msp_sydept_cfg`;
CREATE TABLE `msp_sydept_cfg` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `companyName` varchar(100) DEFAULT NULL COMMENT '公司名称',
  `companyCode` varchar(50) DEFAULT NULL COMMENT '公司编码',
  `companyIp` varchar(50) DEFAULT NULL COMMENT '公司ip',
  `companyPort` varchar(10) DEFAULT NULL COMMENT '公司端口',
  `platformCode` varchar(100) DEFAULT NULL COMMENT '应用平台编码',
  `veritify` varchar(100) DEFAULT NULL COMMENT '访问秘钥',
  `writebackUrl` varchar(200) DEFAULT NULL COMMENT '回调函数',
  `status` char(2) DEFAULT NULL COMMENT '状态：0、未启用 1、启用',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `notice`
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `notice_id` varchar(50) NOT NULL COMMENT '编号',
  `category` char(1) DEFAULT NULL COMMENT '类别',
  `title` varchar(200) NOT NULL COMMENT '标题',
  `content` longtext NOT NULL COMMENT '内容',
  `publisher` bigint(20) NOT NULL COMMENT '发布者',
  `top` char(1) NOT NULL DEFAULT '0' COMMENT '置顶否',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '入库时间',
  `publish_time` datetime DEFAULT NULL COMMENT '发布时间',
  `deadline` datetime DEFAULT NULL COMMENT '过期时间',
  `department_id` varchar(50) DEFAULT NULL COMMENT '查看机构',
  `groups_id` varchar(50) DEFAULT NULL COMMENT '组编号',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告';

-- ----------------------------
--  Table structure for `persistent_logins`
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(50) NOT NULL,
  `series` varchar(50) NOT NULL,
  `token` varchar(50) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `province`
-- ----------------------------
DROP TABLE IF EXISTS `province`;
CREATE TABLE `province` (
  `CODE` varchar(12) NOT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `TYPE` varchar(50) DEFAULT NULL,
  `PARENT_CODE` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`CODE`),
  KEY `idx_province` (`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `resources`
-- ----------------------------
DROP TABLE IF EXISTS `resources`;
CREATE TABLE `resources` (
  `RESOURCES_ID` varchar(50) NOT NULL,
  `PARENT_RESOURCES_ID` varchar(50) NOT NULL,
  `RESOURCES_NAME` varchar(50) NOT NULL,
  `RESOURCES_URL` varchar(200) DEFAULT NULL,
  `RESOURCES_PATH` varchar(200) DEFAULT NULL,
  `RESOURCES_ICON` varchar(200) DEFAULT NULL,
  `RESOURCES_TYPE` varchar(1) NOT NULL,
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATE_USER_ID` varchar(50) NOT NULL,
  `REMARK` varchar(50) DEFAULT NULL,
  `STAUTS` varchar(1) DEFAULT NULL,
  `SEQUENCE` int(11) DEFAULT '1',
  PRIMARY KEY (`RESOURCES_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ROLE_ID` varchar(50) NOT NULL,
  `ROLE_NAME` varchar(50) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `CREATE_USER_ID` varchar(50) NOT NULL,
  `REMARK` varchar(200) NOT NULL,
  `TYPE` varchar(50) DEFAULT '1',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `role_resources`
-- ----------------------------
DROP TABLE IF EXISTS `role_resources`;
CREATE TABLE `role_resources` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ROLE_ID` bigint(20) NOT NULL,
  `RESOURCES_ID` varchar(50) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7999 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `task_id` varchar(50) NOT NULL COMMENT '任务编号',
  `category` varchar(50) NOT NULL COMMENT '类别',
  `title` varchar(50) NOT NULL COMMENT '标题',
  `user_id` varchar(50) NOT NULL COMMENT '领取人',
  `obj_id` varchar(50) NOT NULL COMMENT '目标编号',
  `url` varchar(200) DEFAULT NULL COMMENT '访问的地址',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '下发时间',
  PRIMARY KEY (`task_id`),
  KEY `ind_task_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务';

-- ----------------------------
--  Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `USER_ID` bigint(20) NOT NULL,
  `ACCOUNT` varchar(30) NOT NULL,
  `PASSWORD` varchar(100) NOT NULL,
  `SEX` varchar(1) NOT NULL,
  `ID_CARD` varchar(18) DEFAULT NULL,
  `NAME` varchar(50) NOT NULL,
  `DEPARTMENT_ID` varchar(50) DEFAULT NULL,
  `AREA_CODE` varchar(6) DEFAULT NULL,
  `BIRTHDAY` date DEFAULT NULL,
  `STAUTS` varchar(1) NOT NULL DEFAULT '1',
  `LAST_LOGIN_TIME` datetime DEFAULT NULL,
  `MOBILE` varchar(50) DEFAULT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `SEAT` varchar(50) DEFAULT NULL,
  `USER_LEVEL` varchar(2) NOT NULL DEFAULT '1',
  `CREATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`USER_ID`),
  KEY `poreignkey_idx` (`DEPARTMENT_ID`),
  KEY `idx_users_department_id` (`DEPARTMENT_ID`),
  KEY `idx_users_area` (`AREA_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `users_role`
-- ----------------------------
DROP TABLE IF EXISTS `users_role`;
CREATE TABLE `users_role` (
  `ID` bigint(20) NOT NULL,
  `USER_ID` bigint(20) NOT NULL,
  `ROLE_ID` varchar(50) NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `PK_USERS_ROLE` (`USER_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Function structure for `getpCode`
-- ----------------------------
DROP FUNCTION IF EXISTS `getpCode`;
delimiter ;;
CREATE DEFINER=`root`@`%` FUNCTION `getpCode`(f_original VARCHAR(255) CHARSET utf8) RETURNS varchar(255) CHARSET utf8
BEGIN  
    DECLARE tmp_str VARCHAR(255) CHARSET utf8 DEFAULT '';  
    DECLARE str_len SMALLINT DEFAULT 0;  
    DECLARE tmp_char VARCHAR(10) CHARSET utf8 DEFAULT '';  
    DECLARE tmp_len SMALLINT DEFAULT 0;  
    DECLARE tmp_rs VARCHAR(255) CHARSET utf8 DEFAULT '';  
    DECLARE tmp_cc VARCHAR(10) CHARSET utf8 DEFAULT '';  
    -- 替换左括号
    SET f_original = REPLACE(f_original,'（','(');
    -- 替换右括号
    SET f_original = REPLACE(f_original,'）',')');
    -- 替换中文空格
    SET f_original = REPLACE(f_original,'　',' ');
    SET tmp_str = f_original;  
    SET str_len = LENGTH(tmp_str);  
 
     WHILE str_len > 0 DO  
         SET tmp_char = LEFT(tmp_str,1);  
         SET tmp_cc = tmp_char;  
         IF LENGTH(tmp_char) > 1 THEN  
 	   SELECT ELT(INTERVAL(CONV(HEX(CONVERT(tmp_char USING gbk)),16,10),0xB0A1,0xB0C5,0xB2C1,0xB4EE,0xB6EA,0xB7A2,0xB8C1,0xB9FE,0xBBF7,0xBFA6,0xC0AC,0xC2E8,0xC4C3,0xC5B6,0xC5BE,0xC6DA,0xC8BB,0xC8F6,0xCBFA,0xCDDA,0xCEF4,0xD1B9,0xD4D1), 'A','B','C','D','E','F','G','H','J','K','L','M','N','O','P','Q','R','S','T','W','X','Y','Z') INTO tmp_cc;  
         END IF;  
         SET tmp_rs = CONCAT(tmp_rs,tmp_cc);  
         SET tmp_len = CHAR_LENGTH(tmp_char) + 1;  
         SET tmp_str = SUBSTRING(tmp_str,tmp_len);  
         SET str_len = LENGTH(tmp_str);  
     END WHILE;  
     RETURN tmp_rs;  
END
 ;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
