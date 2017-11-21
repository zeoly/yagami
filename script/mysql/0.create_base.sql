CREATE TABLE `bf_people` (
  `create_date` datetime NOT NULL,
  `create_by` varchar(32) NOT NULL,
  `update_date` datetime NOT NULL,
  `update_by` varchar(32) NOT NULL,
  `id_bf_people` varchar(36) NOT NULL COMMENT '主键',
  `code` varchar(32) NOT NULL COMMENT '用户代码',
  `name` varchar(32) NOT NULL COMMENT '用户姓名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `id_bf_department` varchar(36) NOT NULL COMMENT '机构编码',
  `status` varchar(2) NOT NULL COMMENT '人员状态（0-无效，1-正常，2-锁定，3-待审核）',
  `error_count` int(11) NOT NULL COMMENT '密码错误次数',
  PRIMARY KEY (`id_bf_people`),
  UNIQUE KEY `bf_people_co` (`code`),
  KEY `bf_people_de` (`id_bf_department`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员表';

CREATE TABLE `bf_department` (
  `create_by` varchar(32) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_by` varchar(32) NOT NULL,
  `update_date` datetime NOT NULL,
  `id_bf_department` varchar(36) NOT NULL COMMENT '主键',
  `code` varchar(32) NOT NULL COMMENT '机构代码',
  `name` varchar(32) NOT NULL COMMENT '机构名',
  `level` int(3) NOT NULL COMMENT '机构等级，root为0',
  `parent_dept_id` varchar(36) DEFAULT NULL COMMENT '父机构id',
  PRIMARY KEY (`id_bf_department`),
  UNIQUE KEY `bf_department_co` (`code`),
  KEY `bf_department_pi` (`parent_dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构表';

CREATE TABLE `bf_department_rel` (
  `create_by` varchar(32) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_by` varchar(32) NOT NULL,
  `update_date` datetime NOT NULL,
  `id_bf_department_rel` varchar(36) NOT NULL COMMENT '主键',
  `id_parent_department` varchar(36) NOT NULL COMMENT '父机构代码',
  `id_child_department` varchar(36) NOT NULL COMMENT '子机构代码',
  `parent_level` int(11) NOT NULL COMMENT '父机构等级',
  PRIMARY KEY (`id_bf_department_rel`),
  KEY `department_rel_pd` (`id_parent_department`),
  KEY `department_rel_cd` (`id_child_department`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='机构关联关系表';

CREATE TABLE `bf_role` (
  `create_by` varchar(32) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_by` varchar(32) NOT NULL,
  `update_date` datetime NOT NULL,
  `id_bf_role` varchar(36) NOT NULL COMMENT '主键',
  `name` varchar(32) NOT NULL COMMENT '角色名',
  `description` varchar(1024) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id_bf_role`),
  UNIQUE KEY `role_na` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

CREATE TABLE `bf_people_role_rel` (
  `create_by` varchar(32) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_by` varchar(32) NOT NULL,
  `update_date` datetime NOT NULL,
  `id_bf_people_role_rel` varchar(36) NOT NULL COMMENT '主键',
  `id_bf_people` varchar(36) NOT NULL COMMENT '人员id',
  `id_bf_role` varchar(36) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id_bf_people_role_rel`),
  KEY `people_role_rel_ro` (`id_bf_role`),
  KEY `people_role_rel_pe` (`id_bf_people`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='人员角色关联表';

CREATE TABLE `bf_document` (
  `create_by` varchar(32) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_by` varchar(32) NOT NULL,
  `update_date` datetime NOT NULL,
  `id_bf_document` varchar(36) NOT NULL COMMENT '主键',
  `name` varchar(128) NOT NULL COMMENT '文件夹名',
  `extension` varchar(16) NOT NULL COMMENT '扩展名',
  `url` varchar(1024) NOT NULL COMMENT '文件存储路径',
  `size` int(11) NOT NULL COMMENT '文件大小',
  `memo` varchar(1024) DEFAULT NULL COMMENT '备注',
  `download_count` int(11) NOT NULL COMMENT '下载次数',
  `md5` varchar(32) NOT NULL COMMENT '文件md5值',
  `status` varchar(2) NOT NULL COMMENT '文件状态（0-无效，1-正常）',
  PRIMARY KEY (`id_bf_document`),
  KEY `document_md` (`md5`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文档表';

CREATE TABLE `bf_doc_group` (
  `create_by` varchar(32) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_by` varchar(32) NOT NULL,
  `update_date` datetime NOT NULL,
  `id_bf_doc_group` varchar(36) NOT NULL COMMENT '主键',
  `group_no` varchar(36) NOT NULL COMMENT '文档组号',
  `id_bf_document` varchar(36) NOT NULL COMMENT '文档id',
  PRIMARY KEY (`id_bf_doc_group`),
  KEY `doc_group_gn` (`group_no`),
  KEY `doc_group_do` (`id_bf_document`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文档组表';

CREATE TABLE `bf_folder` (
  `create_by` varchar(32) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_by` varchar(32) NOT NULL,
  `update_date` datetime NOT NULL,
  `id_bf_folder` varchar(36) NOT NULL COMMENT '主键',
  `name` varchar(128) NOT NULL COMMENT '文件夹名',
  `parent_id` varchar(36) DEFAULT NULL COMMENT '父文件夹id',
  PRIMARY KEY (`id_bf_folder`),
  KEY `folder_pi` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件夹表';

CREATE TABLE `bf_folder_doc_rel` (
  `create_by` varchar(32) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_by` varchar(32) NOT NULL,
  `update_date` datetime NOT NULL,
  `id_bf_folder_doc_rel` varchar(36) NOT NULL COMMENT '主键',
  `id_bf_folder` varchar(36) NOT NULL COMMENT '文件夹id',
  `id_bf_document` varchar(36) NOT NULL COMMENT '文档id',
  PRIMARY KEY (`id_bf_folder_doc_rel`),
  KEY `folder_doc_rel_fo` (`id_bf_folder`),
  KEY `folder_doc_rel_do` (`id_bf_document`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件夹与文档关联关系表';

CREATE TABLE `bf_role_folder_auth` (
  `create_by` varchar(32) NOT NULL,
  `create_date` datetime NOT NULL,
  `update_by` varchar(32) NOT NULL,
  `update_date` datetime NOT NULL,
  `id_bf_role_folder_auth` varchar(36) NOT NULL COMMENT '主键',
  `id_bf_role` varchar(36) NOT NULL COMMENT '角色id',
  `id_bf_folder` varchar(36) NOT NULL COMMENT '文档id',
  `authority` varchar(4) DEFAULT 'r' COMMENT '权限，r-读，w-写',
  PRIMARY KEY (`id_bf_role_folder_auth`),
  KEY `role_folder_auth_ro` (`id_bf_role`),
  KEY `role_folder_auth_fo` (`id_bf_folder`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色文档关系表';
