DROP TABLE IF EXISTS `tbs_user`;
CREATE TABLE `tbs_user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `username` varchar(50) NOT NULL COMMENT '名称',
  `password` char(32) NOT NULL COMMENT '密码',
  `state` tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态, 1:可用, 0:不可用',
  `description` varchar(50) DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- ----------------------------
-- Records of tbs_user
-- ----------------------------
INSERT INTO `tbs_user` VALUES ('1', 'admin', '$2a$10$GyQmkfAAOf52Kb5YcNDRY.14YK9P7LB30wIq79LzgvVVU7p0UEGe2',  '1', 'bing，作者自己');
INSERT INTO `tbs_user` VALUES ('2', 'brucelee', '$2a$10$GyQmkfAAOf52Kb5YcNDRY.14YK9P7LB30wIq79LzgvVVU7p0UEGe2', '1', '龙的传人');
INSERT INTO `tbs_user` VALUES ('3', 'zhangsan', '$2a$10$GyQmkfAAOf52Kb5YcNDRY.14YK9P7LB30wIq79LzgvVVU7p0UEGe2',  '1', '张三');
INSERT INTO `tbs_user` VALUES ('4', 'lisi', '$2a$10$GyQmkfAAOf52Kb5YcNDRY.14YK9P7LB30wIq79LzgvVVU7p0UEGe2',  '1', '李四');
INSERT INTO `tbs_user` VALUES ('5', 'jiraya', '$2a$10$GyQmkfAAOf52Kb5YcNDRY.14YK9P7LB30wIq79LzgvVVU7p0UEGe2', '1', '自来也');

-- ----------------------------
-- Table structure for `tbs_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `tbs_user_role`;
CREATE TABLE `tbs_user_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户id',
  `role_id` int(10) unsigned NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

-- ----------------------------
-- Records of tbs_user_role
-- ----------------------------
INSERT INTO `tbs_user_role` VALUES ('1', '1', '1');
INSERT INTO `tbs_user_role` VALUES ('2', '2', '4');

-- ----------------------------
-- Table structure for `tbs_permission`
-- ----------------------------
DROP TABLE IF EXISTS `tbs_permission`;
CREATE TABLE `tbs_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `permission` varchar(50) NOT NULL COMMENT '权限',
  `url` varchar(50) NOT NULL COMMENT 'url',
  `description` varchar(50) DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

-- ----------------------------
-- Records of tbs_permission
-- ----------------------------
INSERT INTO `tbs_permission` VALUES ('1', '用户列表', 'user:view', 'user/userList', '用户列表');
INSERT INTO `tbs_permission` VALUES ('2', '用户添加', 'user:add', 'user/userAdd', '用户添加');
INSERT INTO `tbs_permission` VALUES ('3', '用户删除', 'user:del', 'user/userDel', '用户删除');

-- ----------------------------
-- Table structure for `tbs_role`
-- ----------------------------
DROP TABLE IF EXISTS `tbs_role`;
CREATE TABLE `tbs_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `description` varchar(50) DEFAULT '' COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- ----------------------------
-- Records of tbs_role
-- ----------------------------
INSERT INTO `tbs_role` VALUES ('1', '超级管理员', '拥有全部权限');
INSERT INTO `tbs_role` VALUES ('2', '角色管理员', '拥有全部查看权限，以及角色的增删改权限');
INSERT INTO `tbs_role` VALUES ('3', '权限管理员', '拥有全部查看权限，以及权限的增删改权限');
INSERT INTO `tbs_role` VALUES ('4', '用户管理员', '拥有全部查看权限，以及用户的增删改权限');
INSERT INTO `tbs_role` VALUES ('5', '审核管理员', '拥有全部查看权限，以及审核的权限');

-- ----------------------------
-- Table structure for `tbs_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `tbs_role_permission`;
CREATE TABLE `tbs_role_permission` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `role_id` int(10) unsigned NOT NULL COMMENT '角色id',
  `permission_id` int(10) unsigned NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限表';

-- ----------------------------
-- Records of tbs_role_permission
-- ----------------------------
INSERT INTO `tbs_role_permission` VALUES ('1', '1', '1');
INSERT INTO `tbs_role_permission` VALUES ('2', '1', '2');
INSERT INTO `tbs_role_permission` VALUES ('3', '1', '3');
INSERT INTO `tbs_role_permission` VALUES ('4', '4', '1');
INSERT INTO `tbs_role_permission` VALUES ('5', '4', '2');
INSERT INTO `tbs_role_permission` VALUES ('6', '4', '3');