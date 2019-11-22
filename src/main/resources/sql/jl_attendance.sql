/*
Navicat MySQL Data Transfer

Source Server         : MySQL5.7
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : bootdo

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2019-11-22 17:23:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for jl_attendance
-- ----------------------------
DROP TABLE IF EXISTS `jl_attendance`;
CREATE TABLE `jl_attendance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) NOT NULL COMMENT '人员名',
  `id_card` varchar(20) DEFAULT NULL COMMENT '身份证号',
  `project_id` int(11) NOT NULL COMMENT '项目编号',
  `company_name` varchar(200) DEFAULT NULL COMMENT '公司名称',
  `item_role` varchar(200) DEFAULT NULL COMMENT '人员资质',
  `period` varchar(2000) DEFAULT NULL COMMENT '参与项目周期',
  `effective_days` varchar(10) DEFAULT NULL COMMENT '有效天数',
  `man_mouth` varchar(20) DEFAULT NULL COMMENT '人月',
  `mouth` varchar(10) DEFAULT NULL COMMENT '月份',
  `onday` varchar(10) DEFAULT NULL COMMENT '在岗天数',
  `week_day` varchar(10) DEFAULT NULL COMMENT '周末加班天数',
  `mouth_day` varchar(10) DEFAULT NULL COMMENT '当月工作日',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='考勤明细表';

-- ----------------------------
-- Records of jl_attendance
-- ----------------------------
INSERT INTO `jl_attendance` VALUES ('56', '黄国成', '452502198505243414', '2', '北京联龙博通电子商务技术有限公司', '高级工程师', '企业手机银行日常需求优化项目-互联网-联龙20190001XNBH99', '1', '0.045454545454545456', null, '1', null, '21');
INSERT INTO `jl_attendance` VALUES ('57', '麦汉棠', '440681199309050211', '2', '北京联龙博通电子商务技术有限公司', '初级工程师', '企业手机银行日常需求优化项目-互联网-联龙20190001XNBH99', '4.5', '0.20454545454545456', null, '4.5', null, '21');
INSERT INTO `jl_attendance` VALUES ('58', '张中正', '211422199001046619', '2', '北京联龙博通电子商务技术有限公司', '工程师', '企业手机银行日常需求优化项目-互联网-联龙20190001XNBH99', '3', '0.13636363636363635', null, '3', null, '21');
INSERT INTO `jl_attendance` VALUES ('59', '陈颖怡', '44060219941221184X', '2', '北京联龙博通电子商务技术有限公司', '初级工程师', '企业手机银行日常需求优化项目-互联网-联龙20190001XNBH99', '0.5', '0.022727272727272728', null, '0.5', null, '21');
INSERT INTO `jl_attendance` VALUES ('60', '郑鸿钦', '440582198705190415', '2', '北京联龙博通电子商务技术有限公司', '高级工程师', '企业手机银行日常需求优化项目-互联网-联龙20190001XNBH99', '5', '0.22727272727272727', null, '5', null, '21');
INSERT INTO `jl_attendance` VALUES ('61', '杨富强', '452428199208181416', '2', '北京联龙博通电子商务技术有限公司', '工程师', '企业手机银行日常需求优化项目-互联网-联龙20190001XNBH99', '0.5', '0.022727272727272728', null, '0.5', null, '21');
INSERT INTO `jl_attendance` VALUES ('62', '薛维庆', '370921199008102438', '2', '北京联龙博通电子商务技术有限公司', '工程师', '企业手机银行日常需求优化项目-互联网-联龙20190001XNBH99', '5', '0.22727272727272727', null, '5', null, '21');
INSERT INTO `jl_attendance` VALUES ('63', '颜塘森', '430424199112185016', '2', '北京联龙博通电子商务技术有限公司', '初级工程师', '企业手机银行日常需求优化项目-互联网-联龙20190001XNBH99', '3', '0.13636363636363635', null, '3', null, '21');
INSERT INTO `jl_attendance` VALUES ('64', '张功昭', '450703198609062170', '2', '北京联龙博通电子商务技术有限公司', '工程师', '企业手机银行日常需求优化项目-互联网-联龙20190001XNBH99', '4', '0.18181818181818182', null, '4', null, '21');
INSERT INTO `jl_attendance` VALUES ('65', '杨灵超', '445321199309201915', '2', '北京联龙博通电子商务技术有限公司', '初级工程师', '企业手机银行日常需求优化项目-互联网-联龙20190001XNBH99', '0', '0', null, '0', null, '21');
INSERT INTO `jl_attendance` VALUES ('66', '齐昊', '210104198901034918', '2', '北京联龙博通电子商务技术有限公司', '工程师', '企业手机银行日常需求优化项目-互联网-联龙20190001XNBH99', '18', '0.8181818181818182', null, '18', null, '21');
