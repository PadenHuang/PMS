/*
Navicat MySQL Data Transfer

Source Server         : Test
Source Server Version : 50737
Source Host           : localhost:3306
Source Database       : st_company

Target Server Type    : MYSQL
Target Server Version : 50737
File Encoding         : 65001

Date: 2023-02-27 20:57:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', 'A', '销售部1', '8887014');
INSERT INTO `department` VALUES ('2', 'B', '开发部', '88870151');
INSERT INTO `department` VALUES ('8', '55', 'jklk', '136595959');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `pass` varchar(255) DEFAULT NULL,
  `code_dept` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `nameDept` varchar(255) DEFAULT '123456',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '2020035743011', 'admin', '123456', 'A', '', '123456');
INSERT INTO `employee` VALUES ('2', '2020035743012', 'test01', '123456', 'B', '', '123456');
INSERT INTO `employee` VALUES ('5', 'test02', 'hwq2', '4QrcOUm6Wau+VuBX8g+IPg==', null, '35038cd1-7b82-49a1-86f0-596e5bc0c27a.jpg', '123456');
INSERT INTO `employee` VALUES ('7', 'test03', 'hwq', '4QrcOUm6Wau+VuBX8g+IPg==', null, null, '123456');
INSERT INTO `employee` VALUES ('8', 'test08', 'hh', '4QrcOUm6Wau+VuBX8g+IPg==', null, '', '123456');
INSERT INTO `employee` VALUES ('9', 'test56', 'hwq2', '4QrcOUm6Wau+VuBX8g+IPg==', null, null, '123456');
INSERT INTO `employee` VALUES ('10', 'test898', 'hwq55', '4QrcOUm6Wau+VuBX8g+IPg==', null, null, '123456');

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `time1` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES ('1', '101', '销售单1', '2022-12-07 16:38:07');
INSERT INTO `project` VALUES ('2', '103', '销售单2', '2022-12-01 00:00:00');
INSERT INTO `project` VALUES ('4', 'A101', '销售单1', '2022-12-01 00:00:00');
INSERT INTO `project` VALUES ('5', 'ss', 'ss', '2022-12-02 00:00:00');
INSERT INTO `project` VALUES ('6', 'bjk', 'buih', '2022-12-01 00:00:00');
INSERT INTO `project` VALUES ('7', 'm,l', 'mk', '2022-12-02 00:00:00');
INSERT INTO `project` VALUES ('8', 'bbvbc', 'ukyu', '2022-12-04 00:00:00');
INSERT INTO `project` VALUES ('9', 'hy', 'hyyr', '2022-03-08 00:00:00');

-- ----------------------------
-- Table structure for score
-- ----------------------------
DROP TABLE IF EXISTS `score`;
CREATE TABLE `score` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code_emp` varchar(255) DEFAULT NULL,
  `code_pro` varchar(255) DEFAULT NULL,
  `score` varchar(255) DEFAULT NULL,
  `empName` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  `proName` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of score
-- ----------------------------
INSERT INTO `score` VALUES ('4', '2020035743011', '101', '55', null, null);
