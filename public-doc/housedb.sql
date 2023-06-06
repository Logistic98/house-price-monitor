SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for spider_new_house
-- ----------------------------
DROP TABLE IF EXISTS `spider_new_house`;
CREATE TABLE `spider_new_house`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  `city` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '城市（见code）',
  `property` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '楼盘名称',
  `price` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '单价（元/m2）',
  `total` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '总价（万/套)',
  `time` varchar(14) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '时间',
  `source` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '数据来源（见code）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23863 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '新楼盘信息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for spider_rent
-- ----------------------------
DROP TABLE IF EXISTS `spider_rent`;
CREATE TABLE `spider_rent`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  `city` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '城市（见code）',
  `area` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '区域',
  `descs` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `layout` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '布局',
  `size` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '大小（平米）',
  `price` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '价格（元/月）',
  `district` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '区县',
  `time` varchar(14) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '时间',
  `source` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '数据来源（见code）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 135461 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '租房信息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for spider_second_hand
-- ----------------------------
DROP TABLE IF EXISTS `spider_second_hand`;
CREATE TABLE `spider_second_hand`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  `city` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '城市（见code）',
  `area` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '区域',
  `descs` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '房源描述',
  `layout` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '布局',
  `size` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '大小',
  `direction` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '朝向',
  `furnish` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '装修',
  `floor` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '楼层',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '房屋类型',
  `price` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '价格（万/套）',
  `district` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '区县',
  `time` varchar(14) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '时间',
  `source` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '数据来源（见code）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 684198 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '二手房信息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for spider_community
-- ----------------------------
DROP TABLE IF EXISTS `spider_community`;
CREATE TABLE `spider_community`  (
  `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号',
  `city` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '城市（见code）',
  `district` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '区县',
  `area` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '区域',
  `community` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '小区',
  `price` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '价格（元/m2）',
  `sale` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '在售房源数量（套在售二手房）',
  `time` varchar(14) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '时间',
  `source` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '数据来源（见code）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 223105 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '小区房价信息表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for spider_runtime_summary
-- ----------------------------
DROP TABLE IF EXISTS `spider_runtime_summary`;
CREATE TABLE `spider_runtime_summary`  (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `method` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '方法名',
  `desc` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '方法描述',
  `data` blob NOT NULL COMMENT '结果数据',
  `city` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '城市',
  `update_time` varchar(14) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '更新时间',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '类型',
  `time_range` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '时限范围',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1860 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '运行数据缓存表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for sys_code
-- ----------------------------
DROP TABLE IF EXISTS `sys_code`;
CREATE TABLE `sys_code`  (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '代码编号',
  `desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NULL DEFAULT NULL COMMENT '代码编号说明',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '代码名称',
  `value` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL COMMENT '代码值',
  `number` int(20) NOT NULL COMMENT '序号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci COMMENT = '字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_code
-- ----------------------------
INSERT INTO `sys_code` VALUES (1, 'CityName', '城市名称', '北京', 'bj', 1);
INSERT INTO `sys_code` VALUES (2, 'CityName', '城市名称', '成都', 'cd', 2);
INSERT INTO `sys_code` VALUES (3, 'CityName', '城市名称', '重庆', 'cq', 3);
INSERT INTO `sys_code` VALUES (4, 'CityName', '城市名称', '长沙', 'cs', 4);
INSERT INTO `sys_code` VALUES (5, 'CityName', '城市名称', '东莞', 'dg', 5);
INSERT INTO `sys_code` VALUES (6, 'CityName', '城市名称', '大连', 'dl', 6);
INSERT INTO `sys_code` VALUES (7, 'CityName', '城市名称', '佛山', 'fs', 7);
INSERT INTO `sys_code` VALUES (8, 'CityName', '城市名称', '广州', 'gz', 8);
INSERT INTO `sys_code` VALUES (9, 'CityName', '城市名称', '杭州', 'hz', 9);
INSERT INTO `sys_code` VALUES (10, 'CityName', '城市名称', '合肥', 'hf', 10);
INSERT INTO `sys_code` VALUES (11, 'CityName', '城市名称', '济南', 'jn', 11);
INSERT INTO `sys_code` VALUES (12, 'CityName', '城市名称', '南京', 'nj', 12);
INSERT INTO `sys_code` VALUES (13, 'CityName', '城市名称', '青岛', 'qd', 13);
INSERT INTO `sys_code` VALUES (14, 'CityName', '城市名称', '上海', 'sh', 14);
INSERT INTO `sys_code` VALUES (15, 'CityName', '城市名称', '深圳', 'sz', 15);
INSERT INTO `sys_code` VALUES (16, 'CityName', '城市名称', '苏州', 'su', 16);
INSERT INTO `sys_code` VALUES (17, 'CityName', '城市名称', '沈阳', 'sy', 17);
INSERT INTO `sys_code` VALUES (18, 'CityName', '城市名称', '天津', 'tj', 18);
INSERT INTO `sys_code` VALUES (19, 'CityName', '城市名称', '武汉', 'wh', 19);
INSERT INTO `sys_code` VALUES (20, 'CityName', '城市名称', '厦门', 'xm', 20);
INSERT INTO `sys_code` VALUES (21, 'CityName', '城市名称', '烟台', 'yt', 21);
INSERT INTO `sys_code` VALUES (22, 'Deleted', '是否删除', '已删除', '0', 1);
INSERT INTO `sys_code` VALUES (23, 'Deleted', '是否删除', '未删除', '1', 2);
INSERT INTO `sys_code` VALUES (24, 'UserStatus', '账户状态', '正常', '1', 1);
INSERT INTO `sys_code` VALUES (25, 'UserStatus', '账户状态', '锁定', '2', 2);
INSERT INTO `sys_code` VALUES (26, 'RoleStatus', '角色状态', '弃用', '0', 1);
INSERT INTO `sys_code` VALUES (27, 'RoleStatus', '角色状态', '正常', '1', 2);
INSERT INTO `sys_code` VALUES (28, 'PermissionStatus', '权限状态', '弃用', '0', 1);
INSERT INTO `sys_code` VALUES (29, 'PermissionStatus', '权限状态', '正常', '1', 2);
INSERT INTO `sys_code` VALUES (30, 'PermissionType', '权限类型', '目录', '1', 1);
INSERT INTO `sys_code` VALUES (31, 'PermissionType', '权限类型', '菜单', '2', 2);
INSERT INTO `sys_code` VALUES (32, 'PermissionType', '权限类型', '按钮', '3', 3);
INSERT INTO `sys_code` VALUES (33, 'DataSource', '数据来源', '链家', 'lianjia', 1);
INSERT INTO `sys_code` VALUES (34, 'DataSource', '数据来源', '贝壳', 'ke', 2);
INSERT INTO `sys_code` VALUES (35, 'TaskStatus', '任务状态', '成功', '200', 1);
INSERT INTO `sys_code` VALUES (36, 'TaskStatus', '任务状态', '失败', '500', 2);
INSERT INTO `sys_code` VALUES (37, 'TaskStatus', '任务状态', '进行中', '0', 3);

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户操作',
  `time` int(11) NULL DEFAULT NULL COMMENT '响应时间',
  `method` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(5000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限编码',
  `title` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限名称',
  `icon` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '菜单图标',
  `perms` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '授权(如：sys:user:add)',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '访问地址URL',
  `method` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源请求类型',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT 'name与前端vue路由name约定一致',
  `pid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '父级菜单权限id',
  `order_num` int(11) NULL DEFAULT 0 COMMENT '排序',
  `type` tinyint(4) NULL DEFAULT 1 COMMENT '菜单权限类型(1:目录;2:菜单;3:按钮)',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态1:正常 0：禁用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(4) NULL DEFAULT 1 COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表（菜单）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1236916745927790556', 'btn-user-delete', '删除用户权限', '', 'sys:user:delete', '/api/user', 'DELETE', '', '1236916745927790575', 100, 3, 1, '2020-01-08 15:42:50', NULL, 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790557', 'btn-log-delete', '删除日志权限', '', 'sys:log:delete', '/api/log', 'DELETE', '', '1236916745927790589', 100, 3, 1, '2020-01-08 16:12:53', NULL, 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790558', '', '接口管理', 'el-icon-edit-outline', '', '/swagger', 'GET', 'swagger', '1236916745927790569', 2, 2, 1, '2020-01-08 14:28:56', '2021-08-05 22:26:23', 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790560', '', '菜单权限管理', 'el-icon-lock', '', '/menus', 'GET', 'menus', '1236916745927790564', 1, 2, 1, '2020-01-06 21:55:59', '2021-08-05 22:18:41', 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790561', 'btn-user-add', '新增用户权限', '', 'sys:user:add', '/api/user', 'POST', '', '1236916745927790575', 100, 3, 1, '2020-01-08 15:40:36', NULL, 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790562', 'btn-role-update', '更新角色权限', '', 'sys:role:update', '/api/role', 'PUT', '', '1236916745927790578', 100, 3, 1, '2020-01-08 16:09:55', '2020-04-04 22:31:22', 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790563', 'btn-permission-delete', '删除菜单权限', '', 'sys:permission:delete', '/api/permission', 'DELETE', '', '1236916745927790560', 100, 3, 1, '2020-01-08 15:48:37', NULL, 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790564', '', '组织管理', 'el-icon-menu', '', '/org', '', 'org', '0', 3, 1, 1, '2020-01-06 21:53:55', '2021-08-09 16:48:41', 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790565', 'btn-permission-list', '查询菜单权限列表权限', '', 'sys:permission:list', '/api/permissions', 'POST', '', '1236916745927790560', 100, 3, 1, '2020-01-08 15:46:36', NULL, 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790568', 'btn-user-list', '查询用户信息列表权限', '', 'sys:user:list', '/api/users', 'POST', '', '1236916745927790575', 100, 3, 1, '2020-01-08 15:39:55', NULL, 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790569', '', '系统管理', 'el-icon-s-tools', '', '/sys', '', 'sys', '0', 2, 1, 1, '2020-01-08 13:55:56', '2021-08-05 22:37:03', 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790570', 'btn-role-delete', '删除角色权限', '', 'sys:role:delete', '/api/role/*', 'DELETE', '', '1236916745927790578', 100, 3, 1, '2020-01-08 16:11:22', NULL, 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790571', '', 'SQL监控', 'el-icon-view', '', '/sql', 'GET', 'sql', '1236916745927790569', 1, 2, 1, '2020-01-08 14:30:01', '2021-08-05 22:19:53', 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790572', 'btn-role-add', '新增角色权限', '', 'sys:role:add', '/api/role', 'POST', '', '1236916745927790578', 100, 3, 1, '2020-01-08 15:50:00', '2020-03-12 05:15:46', 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790574', 'btn-role-detail', '角色详情权限', '', 'sys:role:detail', '/api/role/*', 'GET', '', '1236916745927790578', 100, 3, 1, '2020-01-08 16:10:32', NULL, 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790575', '', '用户管理', 'el-icon-user-solid', '', '/user', '', 'user', '1236916745927790564', 3, 2, 1, '2020-01-07 19:49:37', '2021-08-05 19:13:59', 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790577', 'btn-permission-update', '更新菜单权限', '', 'sys:permission:update', '/api/permission', 'PUT', '', '1236916745927790560', 100, 3, 1, '2020-01-08 15:47:56', NULL, 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790578', '', '角色管理', 'el-icon-user', '', '/roles', '', 'roles', '1236916745927790564', 2, 2, 1, '2020-01-06 22:33:55', '2021-08-05 19:13:51', 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790579', 'btn-user-update-role', '赋予用户角色权限', '', 'sys:user:role:update', '/api/user/roles', 'PUT', '', '1236916745927790575', 100, 3, 1, '2020-01-08 15:41:20', NULL, 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790580', 'btn-user-update', '更新用户信息权限', '', 'sys:user:update', '/api/user', 'PUT', '', '1236916745927790575', 100, 3, 1, '2020-01-08 15:42:06', '2020-04-09 13:14:01', 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790582', 'btn-permission-add', '新增菜单权限', '', 'sys:permission:add', '/api/permission', 'POST', '', '1236916745927790560', 100, 3, 1, '2020-01-08 15:47:16', NULL, 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790583', 'btn-role-list', '查询角色列表权限', '', 'sys:role:list', '/api/roles', 'POST', '', '1236916745927790578', 100, 3, 1, '2020-01-08 15:49:20', NULL, 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790589', '', '日志管理', 'el-icon-document', '', '/logs', '', 'logs', '1236916745927790569', 3, 2, 1, '2020-01-08 13:57:12', '2021-08-05 22:19:30', 1);
INSERT INTO `sys_permission` VALUES ('1236916745927790591', 'btn-log-list', '查询日志列表权限', '', 'sys:log:list', '/api/logs', 'POST', '', '1236916745927790589', 100, 3, 1, '2020-01-08 16:12:14', NULL, 1);
INSERT INTO `sys_permission` VALUES ('1423235542816002048', '', '主页', 'el-icon-s-home', '', '/home', '', 'home', '0', 9, 1, 1, '2021-08-05 18:52:51', '2021-08-14 15:55:53', 1);
INSERT INTO `sys_permission` VALUES ('1423238930911858688', '', '房价分析', 'el-icon-s-marketing', '', '/analysis', '', 'analysis', '0', 7, 1, 1, '2021-08-05 19:06:18', '2021-08-14 16:23:25', 1);
INSERT INTO `sys_permission` VALUES ('1423239178321268736', '', '房价查询', 'el-icon-search', '', '/inquiry', '', 'inquiry', '0', 6, 1, 1, '2021-08-05 19:07:17', '2021-08-14 15:55:27', 1);
INSERT INTO `sys_permission` VALUES ('1423239337742569472', '', '定时任务', 'el-icon-time', '', '/timeTask', '', 'timeTask', '1236916745927790569', 4, 1, 1, '2021-08-05 19:07:55', '2021-08-22 14:06:42', 0);
INSERT INTO `sys_permission` VALUES ('1423240531563122688', '', '消息通知', 'el-icon-message-solid', '', '/news', '', 'news', '0', 8, 1, 1, '2021-08-05 19:12:40', '2021-08-14 16:15:50', 1);
INSERT INTO `sys_permission` VALUES ('1423288863069179904', '', '新楼盘查询', 'el-icon-house', '', '/newHouse', '', 'newHouse', '1423239178321268736', 4, 2, 1, '2021-08-05 22:24:43', '2021-08-09 16:52:17', 1);
INSERT INTO `sys_permission` VALUES ('1423289606522146816', '', '二手房查询', 'el-icon-school', '', '/secondHand', '', 'secondHand', '1423239178321268736', 3, 2, 1, '2021-08-05 22:27:40', '2021-08-09 16:52:48', 1);
INSERT INTO `sys_permission` VALUES ('1423289959502188544', '', '租房查询', 'el-icon-bank-card', '', '/rent', '', 'rent', '1423239178321268736', 1, 2, 1, '2021-08-05 22:29:04', '2021-08-09 16:53:47', 1);
INSERT INTO `sys_permission` VALUES ('1423290963354324992', '', '小区查询', 'el-icon-office-building', '', '/community', '', 'community', '1423239178321268736', 2, 2, 1, '2021-08-05 22:33:04', '2021-08-09 16:53:30', 1);
INSERT INTO `sys_permission` VALUES ('1423292194902642688', '', '页面管理', 'el-icon-folder', '', '/', '', 'page', '0', 1, 1, 1, '2021-08-05 22:37:57', '2021-08-08 21:03:51', 1);
INSERT INTO `sys_permission` VALUES ('1423303847014699008', '', '401页面', 'el-icon-document-delete', '', '/401', '', '401', '1423292194902642688', 2, 2, 1, '2021-08-05 23:24:15', '2021-08-05 23:30:33', 1);
INSERT INTO `sys_permission` VALUES ('1423304168474546176', '', '404页面', 'el-icon-document-remove', '', '/404', '', '404', '1423292194902642688', 1, 2, 1, '2021-08-05 23:25:32', '2021-08-05 23:30:59', 1);
INSERT INTO `sys_permission` VALUES ('1424356108495097856', '', '登录页面', 'el-icon-key', '', '/login', '', 'login', '1423292194902642688', 4, 2, NULL, '2021-08-08 21:05:34', '2021-08-08 21:07:01', 1);
INSERT INTO `sys_permission` VALUES ('1424356317673426944', '', '注册页面', 'el-icon-s-check', '', '/register', '', 'register', '1423292194902642688', 3, 2, NULL, '2021-08-08 21:06:24', NULL, 1);
INSERT INTO `sys_permission` VALUES ('1424591866124439552', 'btn-user-list', '新楼盘查询权限', '', 'spider:newHouse:list', '/api/newHouse', 'POST', '', '1423288863069179904', 100, 3, NULL, '2021-08-09 12:42:23', '2021-08-09 12:43:24', 1);
INSERT INTO `sys_permission` VALUES ('1424595164533493760', 'btn-user-list', '二手房查询权限', '', 'spider:secondHand:list', '/api/secondHand', 'POST', '', '1423289606522146816', 100, 3, NULL, '2021-08-09 12:55:30', NULL, 1);
INSERT INTO `sys_permission` VALUES ('1424595599394738176', 'btn-user-list', '小区查询权限', '', 'spider:community:list', '/api/community', 'POST', '', '1423290963354324992', 100, 3, NULL, '2021-08-09 12:57:13', NULL, 1);
INSERT INTO `sys_permission` VALUES ('1424595980799578112', 'btn-user-list', '租房查询权限', '', 'spider:rent:list', '/api/rent', 'POST', '', '1423289959502188544', 100, 3, NULL, '2021-08-09 12:58:44', NULL, 1);
INSERT INTO `sys_permission` VALUES ('1426452332828299264', '', '购房评估', 'el-icon-notebook-1', '', '/evaluate', '', 'evaluate', '0', 5, 1, NULL, '2021-08-14 15:55:13', '2021-08-14 16:22:54', 1);
INSERT INTO `sys_permission` VALUES ('1426453148138082304', '', '购房基本常识', 'el-icon-notebook-2', '', '/commonSense', '', 'commonSense', '1426452332828299264', 2, 2, NULL, '2021-08-14 15:58:27', '2021-08-16 15:23:51', 1);
INSERT INTO `sys_permission` VALUES ('1426453970813063168', '', '标准房贷计算', 'el-icon-date', '', '/mortgageCal', '', 'mortgageCal', '1426452332828299264', 1, 2, NULL, '2021-08-14 16:01:44', '2021-08-15 12:19:31', 1);
INSERT INTO `sys_permission` VALUES ('1426454423479128064', '', '房价概况', 'el-icon-pie-chart', '', '/houseOverview', '', 'houseOverview', '1423238930911858688', 2, 2, NULL, '2021-08-14 16:03:31', '2021-08-14 16:24:48', 1);
INSERT INTO `sys_permission` VALUES ('1426455251845779456', '', '房价预测', 'el-icon-s-marketing', '', '/housePredict', '', 'housePredict', '1423238930911858688', 1, 2, NULL, '2021-08-14 16:06:49', '2021-08-14 16:27:25', 1);
INSERT INTO `sys_permission` VALUES ('1427912286428860416', '', '图标页面', 'el-icon-s-opportunity', '', '/icon', '', 'icon', '1423292194902642688', 5, 2, NULL, '2021-08-18 16:36:33', '2021-08-18 16:52:59', 0);
INSERT INTO `sys_permission` VALUES ('1432639245750767616', 'btn-user-list', '消息通知查询权限', '', 'sys:news:list', '/api/news', 'POST', '', '1426454423479128064', 100, 3, NULL, '2021-08-31 17:39:48', '2021-08-31 17:55:25', 0);

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `description` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '状态(1:正常0:弃用)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(4) NULL DEFAULT 1 COMMENT '是否删除(1未删除；0已删除)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1237258113002901512', '超级管理员', '超级管理员', 1, '2021-08-03 23:37:45', '2021-08-21 20:00:14', 1);
INSERT INTO `sys_role` VALUES ('1237258113002901513', '普通用户', '普通用户', 1, '2021-08-04 10:53:35', '2021-08-14 16:07:46', 1);

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单权限id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1424073203357192192', '1424073200496676864', '1423235542816002048', '2021-08-08 02:21:24');
INSERT INTO `sys_role_permission` VALUES ('1424073203357192193', '1424073200496676864', '1423240531563122688', '2021-08-08 02:21:24');
INSERT INTO `sys_role_permission` VALUES ('1424073203357192194', '1424073200496676864', '1423238930911858688', '2021-08-08 02:21:24');
INSERT INTO `sys_role_permission` VALUES ('1426455493701931008', '1237258113002901513', '1423235542816002048', '2021-08-14 16:07:47');
INSERT INTO `sys_role_permission` VALUES ('1426455493701931009', '1237258113002901513', '1423238930911858688', '2021-08-14 16:07:47');
INSERT INTO `sys_role_permission` VALUES ('1426455493701931010', '1237258113002901513', '1426454423479128064', '2021-08-14 16:07:47');
INSERT INTO `sys_role_permission` VALUES ('1426455493701931011', '1237258113002901513', '1426455251845779456', '2021-08-14 16:07:47');
INSERT INTO `sys_role_permission` VALUES ('1426455493701931012', '1237258113002901513', '1423240531563122688', '2021-08-14 16:07:47');
INSERT INTO `sys_role_permission` VALUES ('1426455493701931013', '1237258113002901513', '1423239178321268736', '2021-08-14 16:07:47');
INSERT INTO `sys_role_permission` VALUES ('1426455493701931014', '1237258113002901513', '1423288863069179904', '2021-08-14 16:07:47');
INSERT INTO `sys_role_permission` VALUES ('1426455493701931015', '1237258113002901513', '1424591866124439552', '2021-08-14 16:07:47');
INSERT INTO `sys_role_permission` VALUES ('1426455493701931016', '1237258113002901513', '1423289606522146816', '2021-08-14 16:07:47');
INSERT INTO `sys_role_permission` VALUES ('1426455493701931017', '1237258113002901513', '1424595164533493760', '2021-08-14 16:07:47');
INSERT INTO `sys_role_permission` VALUES ('1426455493701931018', '1237258113002901513', '1423290963354324992', '2021-08-14 16:07:47');
INSERT INTO `sys_role_permission` VALUES ('1426455493701931019', '1237258113002901513', '1424595599394738176', '2021-08-14 16:07:47');
INSERT INTO `sys_role_permission` VALUES ('1426455493701931020', '1237258113002901513', '1423289959502188544', '2021-08-14 16:07:47');
INSERT INTO `sys_role_permission` VALUES ('1426455493701931021', '1237258113002901513', '1424595980799578112', '2021-08-14 16:07:47');
INSERT INTO `sys_role_permission` VALUES ('1426455493701931022', '1237258113002901513', '1426452332828299264', '2021-08-14 16:07:47');
INSERT INTO `sys_role_permission` VALUES ('1426455493701931023', '1237258113002901513', '1426453148138082304', '2021-08-14 16:07:47');
INSERT INTO `sys_role_permission` VALUES ('1426455493701931024', '1237258113002901513', '1426453970813063168', '2021-08-14 16:07:47');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639488', '1237258113002901512', '1236916745927790580', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639489', '1237258113002901512', '1423289959502188544', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639490', '1237258113002901512', '1423235542816002048', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639491', '1237258113002901512', '1423239178321268736', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639492', '1237258113002901512', '1426454423479128064', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639493', '1237258113002901512', '1424595980799578112', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639494', '1237258113002901512', '1424356108495097856', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639495', '1237258113002901512', '1424595599394738176', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639497', '1237258113002901512', '1236916745927790558', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639498', '1237258113002901512', '1236916745927790556', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639499', '1237258113002901512', '1236916745927790578', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639500', '1237258113002901512', '1426455251845779456', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639501', '1237258113002901512', '1236916745927790579', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639502', '1237258113002901512', '1236916745927790557', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639503', '1237258113002901512', '1236916745927790577', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639504', '1237258113002901512', '1236916745927790574', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639505', '1237258113002901512', '1236916745927790575', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639506', '1237258113002901512', '1236916745927790572', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639507', '1237258113002901512', '1423289606522146816', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639508', '1237258113002901512', '1236916745927790570', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639509', '1237258113002901512', '1426453970813063168', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639510', '1237258113002901512', '1236916745927790571', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639511', '1237258113002901512', '1236916745927790591', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639512', '1237258113002901512', '1423288863069179904', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639513', '1237258113002901512', '1423303847014699008', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639514', '1237258113002901512', '1423290963354324992', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639515', '1237258113002901512', '1426452332828299264', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639516', '1237258113002901512', '1426453148138082304', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639517', '1237258113002901512', '1424356317673426944', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639518', '1237258113002901512', '1423304168474546176', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639519', '1237258113002901512', '1424595164533493760', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639520', '1237258113002901512', '1236916745927790569', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639521', '1237258113002901512', '1423292194902642688', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639522', '1237258113002901512', '1236916745927790589', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639523', '1237258113002901512', '1236916745927790568', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639524', '1237258113002901512', '1236916745927790565', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639525', '1237258113002901512', '1423240531563122688', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639526', '1237258113002901512', '1236916745927790563', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639527', '1237258113002901512', '1236916745927790564', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639528', '1237258113002901512', '1423238930911858688', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639529', '1237258113002901512', '1236916745927790561', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639530', '1237258113002901512', '1236916745927790583', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639531', '1237258113002901512', '1236916745927790562', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639532', '1237258113002901512', '1424591866124439552', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639533', '1237258113002901512', '1236916745927790560', '2021-08-21 20:00:15');
INSERT INTO `sys_role_permission` VALUES ('1429050711416639534', '1237258113002901512', '1236916745927790582', '2021-08-21 20:00:15');

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户',
  `password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码密文',
  `nick_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(4) NULL DEFAULT 1 COMMENT '账户状态(1.正常 2.锁定 )',
  `deleted` tinyint(4) NULL DEFAULT 1 COMMENT '是否删除(1未删除；0已删除)',
  `create_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1237361915165020161', 'admin', '$2a$10$Q6cLty/fAfDSQ74sYvKxH.JQLKt0Df012QYeDhqoyAlpNMJHYQFku', '超级管理员', '111@gmail.com', 1, 1, '1237361915165020161', NULL, '2021-08-08 11:04:45', NULL, '超级管理员');
INSERT INTO `sys_user` VALUES ('1424206080111677440', 'admin2', '$2a$10$5026wTuZgiXZj2tDZUJ1r.IlxN0hIl3K67oLicOw4CztS9joS6cQK', '测试管理员2', '222@gmail.com', 1, 1, '1237361915165020161', '1237361915165020161', '2021-08-08 11:09:25', '2021-08-08 11:09:45', '测试管理员2');
INSERT INTO `sys_user` VALUES ('1424207157317996544', 'test', '$2a$10$0B2UH99y8yDuY3APUEK4.uYNqysE5X8Af/gFaCdGI1AEgfCT3.9HO', '普通测试用户', '333@gmail.com', 1, 1, '1237361915165020161', '1237361915165020161', '2021-08-08 11:13:42', '2021-08-08 11:13:55', '普通测试用户');
INSERT INTO `sys_user` VALUES ('1424428435052302336', 'test2', '$2a$10$FtOYYO.WQYryjHvXXIiSeOc6sZB.M0mVyg2ZoMsFIZJRCC0jR4bC2', '测试普通用户2', '111@163.com', 1, 1, '1424428435052302336', '1237361915165020161', '2021-08-09 01:52:58', '2021-08-09 01:53:55', '来自注册页面');

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色id',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1239912670904324096', '1237361915165020161', '1237258113002901512', '2020-03-17 21:53:12');
INSERT INTO `sys_user_role` VALUES ('1423293469992030208', '1423293029523001344', '1237258113002901513', '2021-08-05 22:43:01');
INSERT INTO `sys_user_role` VALUES ('1423293843012456448', '1423293784657104896', '1237258113002901512', '2021-08-05 22:44:30');
INSERT INTO `sys_user_role` VALUES ('1424063797378813952', '1424063446890188800', '1237258113002901512', '2021-08-08 01:44:02');
INSERT INTO `sys_user_role` VALUES ('1424063797378813953', '1424063446890188800', '1237258113002901513', '2021-08-08 01:44:02');
INSERT INTO `sys_user_role` VALUES ('1424204406584381440', '1423597787899105280', '1237258113002901512', '2021-08-08 11:02:46');
INSERT INTO `sys_user_role` VALUES ('1424206213285023744', '1424206080111677440', '1237258113002901512', '2021-08-08 11:09:56');
INSERT INTO `sys_user_role` VALUES ('1424207285646921728', '1424207157317996544', '1237258113002901513', '2021-08-08 11:14:12');
INSERT INTO `sys_user_role` VALUES ('1424425950166257664', '1424425946840174592', '1237258113002901513', '2021-08-09 01:43:06');
INSERT INTO `sys_user_role` VALUES ('1424428438244167680', '1424428435052302336', '1237258113002901513', '2021-08-09 01:52:59');

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `xxl_job_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_desc` varchar(255) NOT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `author` varchar(64) DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) DEFAULT NULL COMMENT '报警邮件',
  `schedule_type` varchar(50) NOT NULL DEFAULT 'NONE' COMMENT '调度类型',
  `schedule_conf` varchar(128) DEFAULT NULL COMMENT '调度配置，值含义取决于调度类型',
  `misfire_strategy` varchar(50) NOT NULL DEFAULT 'DO_NOTHING' COMMENT '调度过期策略',
  `executor_route_strategy` varchar(50) DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int(11) NOT NULL DEFAULT '0' COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `glue_type` varchar(50) NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `trigger_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '调度状态：0-停止，1-运行',
  `trigger_last_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '上次调度时间',
  `trigger_next_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '下次调度时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `xxl_job_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `trigger_time` datetime DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int(11) NOT NULL COMMENT '调度-结果',
  `trigger_msg` text COMMENT '调度-日志',
  `handle_time` datetime DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int(11) NOT NULL COMMENT '执行-状态',
  `handle_msg` text COMMENT '执行-日志',
  `alarm_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`),
  KEY `I_trigger_time` (`trigger_time`),
  KEY `I_handle_code` (`handle_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `xxl_job_log_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trigger_day` datetime DEFAULT NULL COMMENT '调度-时间',
  `running_count` int(11) NOT NULL DEFAULT '0' COMMENT '运行中-日志数量',
  `suc_count` int(11) NOT NULL DEFAULT '0' COMMENT '执行成功-日志数量',
  `fail_count` int(11) NOT NULL DEFAULT '0' COMMENT '执行失败-日志数量',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_trigger_day` (`trigger_day`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `xxl_job_logglue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) NOT NULL COMMENT 'GLUE备注',
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `xxl_job_registry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(50) NOT NULL,
  `registry_key` varchar(255) NOT NULL,
  `registry_value` varchar(255) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `i_g_k_v` (`registry_group`,`registry_key`,`registry_value`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `xxl_job_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) NOT NULL COMMENT '执行器名称',
  `address_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` text COMMENT '执行器地址列表，多地址逗号分隔',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `xxl_job_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '账号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `role` tinyint(4) NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `xxl_job_lock` (
  `lock_name` varchar(50) NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `xxl_job_group`(`id`, `app_name`, `title`, `address_type`, `address_list`, `update_time`) VALUES (1, 'xxl-job-executor-sample', '示例执行器', 0, NULL, '2018-11-03 22:21:31' );
INSERT INTO `xxl_job_info`(`id`, `job_group`, `job_desc`, `add_time`, `update_time`, `author`, `alarm_email`, `schedule_type`, `schedule_conf`, `misfire_strategy`, `executor_route_strategy`, `executor_handler`, `executor_param`, `executor_block_strategy`, `executor_timeout`, `executor_fail_retry_count`, `glue_type`, `glue_source`, `glue_remark`, `glue_updatetime`, `child_jobid`) VALUES (1, 1, '测试任务1', '2018-11-03 22:21:31', '2018-11-03 22:21:31', 'XXL', '', 'CRON', '0 0 0 * * ? *', 'DO_NOTHING', 'FIRST', 'demoJobHandler', '', 'SERIAL_EXECUTION', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2018-11-03 22:21:31', '');
INSERT INTO `xxl_job_user`(`id`, `username`, `password`, `role`, `permission`) VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, NULL);
INSERT INTO `xxl_job_lock` ( `lock_name`) VALUES ( 'schedule_lock');

commit;



