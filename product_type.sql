/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : eshop

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-12-14 15:32:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `product_type`
-- ----------------------------
DROP TABLE IF EXISTS `product_type`;
CREATE TABLE `product_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) DEFAULT NULL COMMENT '商品分类名称',
  `parent_id` int(11) DEFAULT NULL COMMENT '父分类id',
  `sort` int(11) DEFAULT NULL COMMENT '序号',
  `intro` text COMMENT '简介',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_type
-- ----------------------------
INSERT INTO `product_type` VALUES ('13', '裙装', '11', '15', '各式裙装wewewew', '2017-07-02 14:43:03');
INSERT INTO `product_type` VALUES ('11', '时尚女装', '0', '1', '女士服装', '2017-07-02 14:42:03');
INSERT INTO `product_type` VALUES ('12', '潮流男装', '0', '2', '男士服装', '2017-07-02 14:42:38');
INSERT INTO `product_type` VALUES ('14', '上装', '11', '12', '上装', '2017-07-02 14:43:32');
INSERT INTO `product_type` VALUES ('15', '裤装', '11', '13', '下装', '2017-07-02 14:44:05');
INSERT INTO `product_type` VALUES ('23', '内裤', '12', '18', '各式各样', '2017-12-14 11:03:51');
INSERT INTO `product_type` VALUES ('24', '三角内裤', '23', '19', '超性感', '2017-12-14 11:04:11');
INSERT INTO `product_type` VALUES ('25', '平角内裤', '23', '22', '安全', '2017-12-14 11:07:22');
INSERT INTO `product_type` VALUES ('26', '丁字裤', '24', '22', '一起玩耍吧！浪漫！', '2017-12-14 11:13:31');
INSERT INTO `product_type` VALUES ('27', '123421', '11', '14', '2134', '2017-12-14 11:13:56');
