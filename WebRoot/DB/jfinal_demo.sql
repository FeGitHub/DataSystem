/*
 Navicat Premium Data Transfer

 Source Server         : localMysql
 Source Server Type    : MySQL
 Source Server Version : 50713
 Source Host           : localhost:3306
 Source Schema         : jfinal_demo

 Target Server Type    : MySQL
 Target Server Version : 50713
 File Encoding         : 65001

 Date: 28/08/2018 19:26:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (6, 'rrr', 'rrrr');
INSERT INTO `blog` VALUES (7, 'hh', 'hh');
INSERT INTO `blog` VALUES (8, 'hh444', 'h444h4');
INSERT INTO `blog` VALUES (9, 'hhfd4', 'hh4fd');
INSERT INTO `blog` VALUES (10, 'h1212h4', 'h2121h4');
INSERT INTO `blog` VALUES (11, 'eee4', 'eeee');
INSERT INTO `blog` VALUES (12, '6666', '666');
INSERT INTO `blog` VALUES (13, 'hh', 'hh');
INSERT INTO `blog` VALUES (14, 'test1', 'test1');
INSERT INTO `blog` VALUES (15, 'Fe', 'Fe');
INSERT INTO `blog` VALUES (16, 'Fe', 'Fe');
INSERT INTO `blog` VALUES (17, 'test1', 'test1');
INSERT INTO `blog` VALUES (18, 'test1', 'test1');
INSERT INTO `blog` VALUES (19, 'Fe', 'Fe');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `account` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (2, '小明', 'admin', 'admin');
INSERT INTO `user` VALUES (3, '大明', 'user1', '123456');
INSERT INTO `user` VALUES (5, '李明', 'user1', '123456');

SET FOREIGN_KEY_CHECKS = 1;
