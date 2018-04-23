/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.232（开发环境）
Source Server Version : 50720
Source Host           : 192.168.1.232:3306
Source Database       : seckill

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-04-23 17:15:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for kill_item
-- ----------------------------
DROP TABLE IF EXISTS `kill_item`;
CREATE TABLE `kill_item` (
  `id` varchar(36) NOT NULL,
  `kill_product_id` varchar(36) NOT NULL,
  `mobile` varchar(11) NOT NULL,
  `kill_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `kill_product_mobile_idx` (`kill_product_id`,`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
