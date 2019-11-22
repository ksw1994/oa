/*
Navicat MySQL Data Transfer

Source Server         : MySQL5.7
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : bootdo

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-11-22 17:23:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for jl_project
-- ----------------------------
DROP TABLE IF EXISTS `jl_project`;
CREATE TABLE `jl_project` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(200) NOT NULL COMMENT '项目名称',
  `is_jh_item` varchar(10) NOT NULL COMMENT '是否建行项目',
  `frame` varchar(200) DEFAULT NULL COMMENT '使用建行新一代框架',
  `is_zngj_item` varchar(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '是否中农工交银行项目',
  `is_nozngj_item` varchar(10) NOT NULL COMMENT '是否中农工交以外其他银行',
  `item_type` varchar(200) NOT NULL COMMENT '项目业务类别',
  `sdate` varchar(50) NOT NULL COMMENT '开始日期',
  `edate` varchar(50) DEFAULT NULL COMMENT '结束日期',
  `witness` varchar(20) NOT NULL COMMENT '证明人',
  `telephone` varchar(20) NOT NULL COMMENT '证明人电话',
  `desc` varchar(2000) DEFAULT NULL COMMENT '项目简述',
  `company_name` varchar(200) DEFAULT NULL COMMENT '公司名称',
  `in_sdate` varchar(50) DEFAULT NULL COMMENT '进场日期',
  `out_sdate` varchar(50) DEFAULT NULL COMMENT '退场日期',
  `work_num` varchar(10) DEFAULT NULL COMMENT '需求总工作量',
  `c_work_num` varchar(10) DEFAULT NULL COMMENT '初级需求工作量',
  `z_work_num` varchar(10) DEFAULT NULL COMMENT '中级需求工作量',
  `g_work_num` varchar(10) DEFAULT NULL COMMENT '高级需求工作量',
  `demand` varchar(2000) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '维护要求',
  `proceed_id` varchar(200) DEFAULT NULL COMMENT '事项编号',
  `pact_id` varchar(200) DEFAULT NULL COMMENT '合同编号',
  `help_name` varchar(200) DEFAULT NULL COMMENT '协管员',
  `pact_num` varchar(10) DEFAULT NULL COMMENT '合同人数',
  `spot_num` varchar(10) DEFAULT NULL COMMENT '在场人数',
  `in_num` varchar(10) DEFAULT NULL COMMENT '按合同入场人数',
  `change_staff` varchar(500) DEFAULT NULL COMMENT '变更人员',
  `fail_staff` varchar(500) DEFAULT NULL COMMENT '不符合资质人员',
  `status` varchar(10) DEFAULT NULL COMMENT '项目状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='项目信息表';

-- ----------------------------
-- Records of jl_project
-- ----------------------------
INSERT INTO `jl_project` VALUES ('1', '1', '是', '是', '否', '否', '1', '2019-10-12', '2019-10-12', '1', '1', '', '', '2019-11-04', '2019-11-27', '', '', '', '', '', '', '', '', '', '', '', '', '', '1');
INSERT INTO `jl_project` VALUES ('2', 'zxcvzxcv', '否', '是', '是', '是', 'zxcvzxc', '2019-11-05', '2019-11-19', 'zxvczx', 'zxcvzx', '								    ', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '');
