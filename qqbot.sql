/*
 Navicat Premium Data Transfer

 Source Server         : 159.75.70.13
 Source Server Type    : MySQL
 Source Server Version : 50650
 Source Host           : 159.75.70.13:3306
 Source Schema         : qqbot

 Target Server Type    : MySQL
 Target Server Version : 50650
 File Encoding         : 65001

 Date: 06/07/2022 01:16:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blacklist
-- ----------------------------
DROP TABLE IF EXISTS `t_blacklist`;
CREATE TABLE `t_blacklist`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `blackuser_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `black_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_drawprize
-- ----------------------------
DROP TABLE IF EXISTS `t_drawprize`;
CREATE TABLE `t_drawprize`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '参与抽奖id',
  `prize_id` int(11) NOT NULL COMMENT '奖品id',
  `player_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参与人qq号',
  `addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '参与时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_prize
-- ----------------------------
DROP TABLE IF EXISTS `t_prize`;
CREATE TABLE `t_prize`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '奖品id',
  `prize_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '奖品名称',
  `prize_quantity` int(11) NOT NULL DEFAULT 1 COMMENT '奖品数量,默认1',
  `prize_drawtime` datetime NULL DEFAULT NULL COMMENT '抽取时间,默认不设置,不设置则为手动抽取',
  `prize_addtime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '奖品添加时间',
  `prize_isdraw` int(11) NOT NULL DEFAULT 0 COMMENT '奖品是否抽取,默认0(未抽取)',
  `prize_from` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '奖品提供者,默认为添加奖品的用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_sexpicture
-- ----------------------------
DROP TABLE IF EXISTS `t_sexpicture`;
CREATE TABLE `t_sexpicture`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '色图本地数据库ID',
  `picture_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '色图p站id',
  `picture_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '色图url',
  `picture_size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '色图图像大小（original，regular，small，thumb，mini）',
  `picture_cache` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '色图缓存图像绝对地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4573 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户QQ号',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户昵称',
  `user_level` int(11) NOT NULL DEFAULT 1 COMMENT '用户权限等级',
  `age` int(11) NULL DEFAULT NULL COMMENT '用户年龄',
  `area` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户地区',
  `card` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户群名片',
  `card_changeable` tinyint(1) NULL DEFAULT NULL COMMENT '是否允许修改群名片',
  `group_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '群号',
  `sex` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '性别',
  `join_time` bigint(20) NULL DEFAULT NULL COMMENT '加群时间戳',
  `last_sent_time` bigint(20) NULL DEFAULT NULL COMMENT '最后发言时间戳',
  `level` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '成员等级',
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '角色',
  `unfriendly` tinyint(1) NULL DEFAULT NULL COMMENT '是否不良记录成员',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '专属头衔',
  `title_expire_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '专属头衔过期时间戳',
  `shut_up_timestamp` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '禁言到期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 96 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for t_winners
-- ----------------------------
DROP TABLE IF EXISTS `t_winners`;
CREATE TABLE `t_winners`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `prize_id` int(11) NOT NULL COMMENT '奖品ID',
  `user_id` int(11) NOT NULL COMMENT '中奖者ID',
  `draw_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '中奖时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Event structure for event_removePivtureDuplicate
-- ----------------------------
DROP EVENT IF EXISTS `event_removePivtureDuplicate`;
delimiter ;;
CREATE EVENT `event_removePivtureDuplicate`
ON SCHEDULE
EVERY '1' DAY STARTS '2022-01-10 03:00:00'
DO DELETE 
FROM
	t_sexpicture 
WHERE
	( picture_id, picture_url ) IN ( SELECT picture_id, picture_url FROM ( SELECT picture_id, picture_url FROM t_sexpicture GROUP BY picture_id, picture_url HAVING count( 1 ) > 1 ) t ) 
	AND id NOT IN (
	SELECT
		dt.mindeptno 
	FROM
	( SELECT min( id ) AS mindeptno FROM t_sexpicture GROUP BY picture_id, picture_url HAVING count( 1 ) > 1 ) dt 
	)
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
