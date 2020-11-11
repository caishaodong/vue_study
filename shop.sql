/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : localhost:3306
 Source Schema         : shop

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : 65001

 Date: 11/11/2020 22:45:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base
-- ----------------------------
DROP TABLE IF EXISTS `base`;
CREATE TABLE `base`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除（0：未删除，1：删除）',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '基础表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for c_sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `c_sys_menu`;
CREATE TABLE `c_sys_menu`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '地址',
  `pid` bigint(11) UNSIGNED NULL DEFAULT NULL COMMENT '父菜单id',
  `menu_type` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '类型（1：菜单，2：按钮）',
  `level` tinyint(1) UNSIGNED NOT NULL COMMENT '级别（1：一级，2：二级，3：三级）',
  `sort` int(8) UNSIGNED NOT NULL DEFAULT 999 COMMENT '排序',
  `is_enable` tinyint(1) NOT NULL DEFAULT 1 COMMENT '是否启用（0：禁用，1：启用）',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除（0：未删除，1：删除）',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统菜单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for c_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `c_sys_role`;
CREATE TABLE `c_sys_role`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '角色备注',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除（0：未删除，1：删除）',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户角色表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for c_sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `c_sys_role_menu`;
CREATE TABLE `c_sys_role_menu`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `sys_role_id` bigint(11) UNSIGNED NOT NULL COMMENT '系统角色id',
  `sys_menu_id` bigint(11) UNSIGNED NOT NULL COMMENT '系统菜单id',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除（0：未删除，1：删除）',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_menu`(`sys_role_id`, `sys_menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统角色和菜单关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for c_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `c_sys_user`;
CREATE TABLE `c_sys_user`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '盐值',
  `mobile` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '邮箱',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除（0：未删除，1：删除）',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_username`(`username`) USING BTREE,
  INDEX `idx_mobile`(`mobile`) USING BTREE,
  INDEX `idx_email`(`email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for c_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `c_sys_user_role`;
CREATE TABLE `c_sys_user_role`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `sys_user_id` bigint(11) UNSIGNED NOT NULL COMMENT '系统用户id',
  `sys_role_id` bigint(11) UNSIGNED NOT NULL COMMENT '系统角色id',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除（0：未删除，1：删除）',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_role`(`sys_user_id`, `sys_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户和角色关联表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
