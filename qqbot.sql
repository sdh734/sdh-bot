/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : qqbot

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 31/01/2022 22:50:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_blacklist
-- ----------------------------
DROP TABLE IF EXISTS `t_blacklist`;
CREATE TABLE `t_blacklist`
(
    `id`           int                                                     NOT NULL AUTO_INCREMENT COMMENT '黑名单id',
    `blackuser_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '黑名单用户qq',
    `black_time`   datetime                                                NOT NULL DEFAULT '(now() + interval 1 day)' COMMENT '黑名单时间,默认1天',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_drawprize
-- ----------------------------
DROP TABLE IF EXISTS `t_drawprize`;
CREATE TABLE `t_drawprize`
(
    `id`        int                                                     NOT NULL AUTO_INCREMENT COMMENT '参与抽奖id',
    `prize_id`  int                                                     NOT NULL COMMENT '奖品id',
    `player_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '参与人qq号',
    `addtime`   datetime                                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '参与时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 26
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_prize
-- ----------------------------
DROP TABLE IF EXISTS `t_prize`;
CREATE TABLE `t_prize`
(
    `id`             int                                                     NOT NULL AUTO_INCREMENT COMMENT '奖品id',
    `prize_name`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '奖品名称',
    `prize_quantity` int                                                     NOT NULL DEFAULT 1 COMMENT '奖品数量,默认1',
    `prize_drawtime` datetime                                                NULL     DEFAULT NULL COMMENT '抽取时间,默认不设置,不设置则为手动抽取',
    `prize_addtime`  datetime                                                NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '奖品添加时间',
    `prize_isdraw`   int                                                     NOT NULL DEFAULT 0 COMMENT '奖品是否抽取,默认0(未抽取)',
    `prize_from`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '奖品提供者,默认为添加奖品的用户',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 17
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sexpicture
-- ----------------------------
DROP TABLE IF EXISTS `t_sexpicture`;
CREATE TABLE `t_sexpicture`
(
    `id`            int                                                     NOT NULL AUTO_INCREMENT COMMENT '色图本地数据库ID',
    `picture_id`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '色图p站id',
    `picture_url`   varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '色图url',
    `picture_size`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '色图图像大小（original，regular，small，thumb，mini）',
    `picture_cache` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '色图缓存图像绝对地址',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3616
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`         int                                                           NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `user_id`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户QQ号',
    `user_name`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL     DEFAULT NULL COMMENT '用户昵称',
    `user_level` int                                                           NOT NULL DEFAULT 1 COMMENT '用户权限等级',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 27
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_winners
-- ----------------------------
DROP TABLE IF EXISTS `t_winners`;
CREATE TABLE `t_winners`
(
    `id`        int      NOT NULL AUTO_INCREMENT,
    `prize_id`  int      NOT NULL COMMENT '奖品ID',
    `user_id`   int      NOT NULL COMMENT '中奖者ID',
    `draw_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '中奖时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Event structure for event_removePivtureDuplicate
-- ----------------------------
DROP EVENT IF EXISTS `event_removePivtureDuplicate`;
delimiter ;;
CREATE EVENT `event_removePivtureDuplicate`
    ON SCHEDULE
        EVERY '1' DAY STARTS '2022-01-10 03:00:00'
    DO DELETE
       FROM t_sexpicture
       WHERE (picture_id, picture_url) IN (SELECT picture_id, picture_url
                                           FROM (SELECT picture_id, picture_url
                                                 FROM t_sexpicture
                                                 GROUP BY picture_id, picture_url
                                                 HAVING count(1) > 1) t)
         AND id NOT IN (
           SELECT dt.mindeptno
           FROM (SELECT min(id) AS mindeptno FROM t_sexpicture GROUP BY picture_id, picture_url HAVING count(1) > 1) dt
       )
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
