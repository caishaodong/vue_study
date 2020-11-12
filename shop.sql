/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : 127.0.0.1:3306
 Source Schema         : shop

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 12/11/2020 16:57:23
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
-- Table structure for c_goods_attr
-- ----------------------------
DROP TABLE IF EXISTS `c_goods_attr`;
CREATE TABLE `c_goods_attr`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cate_id` bigint(11) UNSIGNED NOT NULL COMMENT '类目参数所属类目id',
  `attr_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '类目参数名称',
  `attr_sel` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'only：输入框(唯一)，many：后台下拉列表/前台单选框',
  `attr_write` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT 'manual:手工录入，list:从列表选择',
  `attr_vals` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '如果 attr_write为list,那么有值，该值以逗号分隔',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除（0：未删除，1：删除）',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_cate_id`(`cate_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品类目参数表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for c_goods_cate
-- ----------------------------
DROP TABLE IF EXISTS `c_goods_cate`;
CREATE TABLE `c_goods_cate`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `cate_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '类目名称',
  `cate_level` tinyint(1) UNSIGNED NOT NULL DEFAULT 1 COMMENT '级别（1：一级，2：二级，3：三级）',
  `cate_pid` bigint(11) UNSIGNED NULL DEFAULT NULL COMMENT '父类目id',
  `is_enable` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否启用（0：不启用，1：启用）',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除（0：未删除，1：删除）',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_cate_pid`(`cate_pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品类目表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for c_goods_info
-- ----------------------------
DROP TABLE IF EXISTS `c_goods_info`;
CREATE TABLE `c_goods_info`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `goods_price` decimal(14, 4) UNSIGNED NOT NULL DEFAULT 0.0000 COMMENT '商品价格',
  `goods_number` int(8) UNSIGNED NOT NULL DEFAULT 0 COMMENT '商品数量',
  `goods_unit` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品单位',
  `goods_weight` decimal(10, 4) UNSIGNED NOT NULL DEFAULT 0.0000 COMMENT '商品重量',
  `goods_state` tinyint(1) NOT NULL DEFAULT 1 COMMENT '商品状态（0：未通过，1：审核中，2：上架，3：下架）',
  `is_promote` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否是热销品（0：否，1：是）',
  `pics` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '上传的图片临时路径',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除（0：未删除，1：删除）',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for c_goods_info_cate_rel
-- ----------------------------
DROP TABLE IF EXISTS `c_goods_info_cate_rel`;
CREATE TABLE `c_goods_info_cate_rel`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(11) NULL DEFAULT NULL COMMENT '商品id',
  `goods_cate_id` bigint(11) NULL DEFAULT NULL COMMENT '商品类目id',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除（0：未删除，1：删除）',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_cate_goods`(`goods_cate_id`, `goods_id`) USING BTREE,
  INDEX `idx_goods_id`(`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品和类目关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for c_order_detail_info
-- ----------------------------
DROP TABLE IF EXISTS `c_order_detail_info`;
CREATE TABLE `c_order_detail_info`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` bigint(11) UNSIGNED NOT NULL COMMENT '订单id',
  `goods_id` bigint(11) UNSIGNED NOT NULL COMMENT '商品id',
  `goods_price` decimal(14, 4) UNSIGNED NOT NULL DEFAULT 0.0000 COMMENT '商品单价',
  `goods_num` int(8) NOT NULL DEFAULT 0 COMMENT '商品数量',
  `goods_total_price` decimal(14, 4) UNSIGNED NOT NULL DEFAULT 0.0000 COMMENT '商品总价',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除（0：未删除，1：删除）',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单详情表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for c_order_info
-- ----------------------------
DROP TABLE IF EXISTS `c_order_info`;
CREATE TABLE `c_order_info`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` bigint(11) UNSIGNED NOT NULL COMMENT '用户id',
  `receive_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '收货人姓名',
  `receive_mobile` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户收货手机号',
  `receive_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '收货地址',
  `order_no` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '订单编号',
  `order_price` decimal(14, 4) UNSIGNED ZEROFILL NOT NULL DEFAULT 0000000000.0000 COMMENT '订单价格',
  `order_pay` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '支付方式( 0：未支付，1：支付宝，2：微信，3：银行卡）',
  `pay_status` tinyint(1) NOT NULL DEFAULT 0 COMMENT '支付状态（0：未支付，1：已支付）',
  `is_send` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否发货（0：未发货，1：已发货）',
  `trade_no` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '交易编号',
  `fapiao_title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '发票抬头（1：个人，2：公司）',
  `fapiao_company` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '发票公司名称',
  `fapiao_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '发票内容',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除（0：未删除，1：删除）',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE,
  INDEX `idx_order_no`(`order_no`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for c_order_logistics_info
-- ----------------------------
DROP TABLE IF EXISTS `c_order_logistics_info`;
CREATE TABLE `c_order_logistics_info`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `order_id` bigint(11) UNSIGNED NOT NULL COMMENT '订单id',
  `context` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '物流内容描述',
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '地址',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除（0：未删除，1：删除）',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_order_id`(`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单物流信息表' ROW_FORMAT = Compact;

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
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_pid`(`pid`) USING BTREE
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
-- Table structure for c_sys_role_menu_rel
-- ----------------------------
DROP TABLE IF EXISTS `c_sys_role_menu_rel`;
CREATE TABLE `c_sys_role_menu_rel`  (
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
-- Table structure for c_sys_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `c_sys_user_role_rel`;
CREATE TABLE `c_sys_user_role_rel`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `sys_user_id` bigint(11) UNSIGNED NOT NULL COMMENT '系统用户id',
  `sys_role_id` bigint(11) UNSIGNED NOT NULL COMMENT '系统角色id',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除（0：未删除，1：删除）',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_role`(`sys_user_id`, `sys_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统用户和角色关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for c_user_address
-- ----------------------------
DROP TABLE IF EXISTS `c_user_address`;
CREATE TABLE `c_user_address`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(11) UNSIGNED NOT NULL COMMENT '用户id',
  `receive_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '收货人姓名',
  `receive_mobile` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '收货人手机号',
  `receive_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '收货地址',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除（0：未删除，1：删除）',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户收货地址表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for c_user_info
-- ----------------------------
DROP TABLE IF EXISTS `c_user_info`;
CREATE TABLE `c_user_info`  (
  `id` bigint(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '盐值',
  `mobile` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '手机号',
  `sex` tinyint(1) NOT NULL DEFAULT 0 COMMENT '性别（0：未知，1：男，2：女）',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '状态（1：正常，2：冻结，3：注销）',
  `is_deleted` tinyint(1) UNSIGNED NOT NULL DEFAULT 0 COMMENT '是否删除（0：未删除，1：删除）',
  `gmt_create` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_username`(`username`) USING BTREE,
  INDEX `idx_mobile`(`mobile`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
