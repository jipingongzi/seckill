/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.232（开发环境）
Source Server Version : 50720
Source Host           : 192.168.1.232:3306
Source Database       : seckill

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2018-04-23 17:15:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for kill_product
-- ----------------------------
DROP TABLE IF EXISTS `kill_product`;
CREATE TABLE `kill_product` (
  `id` varchar(36) NOT NULL,
  `product_id` varchar(36) NOT NULL,
  `kill_description` varchar(50) DEFAULT NULL,
  `number` int(11) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
