# common properties for table
# `id`        BIGINT UNSIGNED AUTO_INCREMENT,
# `create_at` DATETIME NOT NULL,
# `update_at` DATETIME NOT NULL,
# `delete_at` DATETIME        DEFAULT NULL,
# PRIMARY KEY (id)
#简单起见 1.一个用户只有一个角色 默认账号admin为系统管理员 2.类别都写死,不用自定义

DROP TABLE IF EXISTS `core_user`;
CREATE TABLE core_user (
  `id`              BIGINT UNSIGNED  AUTO_INCREMENT,
  `create_at`       DATETIME     NOT NULL,
  `update_at`       DATETIME     NOT NULL,
  `delete_at`       DATETIME         DEFAULT NULL,
  `username`        VARCHAR(30)  NOT NULL,
  `name`            VARCHAR(30)      DEFAULT NULL,
  `password`        VARCHAR(255) NOT NULL,
  `salt`            VARCHAR(255) NOT NULL,
  `last_login_time` DATETIME         DEFAULT NULL
  COMMENT '上次登录时间',
  `role_id`         BIGINT UNSIGNED  DEFAULT NULL,
  `level`           TINYINT UNSIGNED DEFAULT 0,
  PRIMARY KEY (id),
  UNIQUE KEY (username)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='用户表';

DROP TABLE IF EXISTS `core_role`;
CREATE TABLE core_role (
  `id`        BIGINT UNSIGNED AUTO_INCREMENT,
  `create_at` DATETIME    NOT NULL,
  `update_at` DATETIME    NOT NULL,
  `delete_at` DATETIME        DEFAULT NULL,
  `name`      VARCHAR(30) NOT NULL,
  `summary`   VARCHAR(255)    DEFAULT NULL,
  PRIMARY KEY (id)

)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='角色表';

DROP TABLE IF EXISTS `core_permission`;
CREATE TABLE core_permission (
  `id`        BIGINT UNSIGNED  AUTO_INCREMENT,
  `create_at` DATETIME     NOT NULL,
  `update_at` DATETIME     NOT NULL,
  `delete_at` DATETIME         DEFAULT NULL,
  `name`      VARCHAR(255) NOT NULL,
  `summary`   VARCHAR(255)     DEFAULT NULL,
  `rest_url`  VARCHAR(255)     DEFAULT NULL
  COMMENT '数据url',
  `page_url`  VARCHAR(255)     DEFAULT NULL
  COMMENT '页面url',
  `parent_id` BIGINT UNSIGNED  DEFAULT NULL,
  `depth`     INT UNSIGNED     DEFAULT 0,
  `path`      INT UNSIGNED     DEFAULT NULL
  COMMENT '权限路径',
  `category`  TINYINT UNSIGNED DEFAULT NULL DEFAULT 0
  COMMENT '权限类型 0.菜单 1.按钮',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='权限表';

DROP TABLE IF EXISTS `core_role_permission`;
CREATE TABLE core_role_permission (
  `id`            BIGINT UNSIGNED AUTO_INCREMENT,
  `create_at`     DATETIME        NOT NULL,
  `update_at`     DATETIME        NOT NULL,
  `delete_at`     DATETIME        DEFAULT NULL,
  `role_id`       BIGINT UNSIGNED NOT NULL,
  `permission_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='角色权限中间表';

DROP TABLE IF EXISTS `core_knowledge`;
CREATE TABLE core_knowledge (
  `id`                   BIGINT UNSIGNED                                AUTO_INCREMENT,
  `create_at`            DATETIME               NOT NULL,
  `update_at`            DATETIME               NOT NULL,
  `delete_at`            DATETIME                                       DEFAULT NULL,
  `name`                 VARCHAR(255)                                   DEFAULT  NULL
  COMMENT '知识点名称',
  `sort`                 INT UNSIGNED DEFAULT 0 NOT NULL
  COMMENT '排序',
  `level`                TINYINT UNSIGNED COMMENT '类别 1.基础知识点  2.进阶知识点' DEFAULT 1,
  `parent_id`            BIGINT UNSIGNED DEFAULT NULL,
  `uploader_id`          BIGINT UNSIGNED        NOT NULL
  COMMENT '上传者编号',
  `topic_id`             BIGINT UNSIGNED                                DEFAULT NULL
  COMMENT '所属专题编号',
  `resource_document_id` BIGINT UNSIGNED        NOT NULL
  COMMENT '知识点文档',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='知识点表';

DROP TABLE IF EXISTS `core_expand_knowledge`;
CREATE TABLE core_expand_knowledge (
  `id`          BIGINT UNSIGNED AUTO_INCREMENT,
  `create_at`   DATETIME        NOT NULL,
  `update_at`   DATETIME        NOT NULL,
  `delete_at`   DATETIME        DEFAULT NULL,
  `name`        VARCHAR(255)    NOT NULL
  COMMENT '名称',
  `summary`     VARCHAR(255)    DEFAULT NULL
  COMMENT '摘要',
  `cover_url`   VARCHAR(255)    DEFAULT NULL
  COMMENT '背景图url',
  `url`         VARCHAR(255)    DEFAULT NULL
  COMMENT '知识地址',
  `uploader_id` BIGINT UNSIGNED NOT NULL
  COMMENT '上传者编号',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='拓展知识表';

DROP TABLE IF EXISTS `core_topic`;
CREATE TABLE core_topic (
  `id`          BIGINT UNSIGNED AUTO_INCREMENT,
  `create_at`   DATETIME        NOT NULL,
  `update_at`   DATETIME        NOT NULL,
  `delete_at`   DATETIME        DEFAULT NULL,
  `name`        VARCHAR(255
                )               NOT NULL
  COMMENT '专题名称',
  `uploader_id` BIGINT UNSIGNED NOT NULL
  COMMENT '上传者编号',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='专题表';

DROP TABLE IF EXISTS `core_example`;
CREATE TABLE core_example (
  `id`                  BIGINT UNSIGNED AUTO_INCREMENT,
  `create_at`           DATETIME        NOT NULL,
  `update_at`           DATETIME        NOT NULL,
  `delete_at`           DATETIME        DEFAULT NULL,
  `title`               VARCHAR(255)    NOT NULL
  COMMENT '实例标题',
  `content`             TEXT            NOT NULL
  COMMENT '实例内容',
  `explain`             TEXT            DEFAULT NULL
  COMMENT '实例讲解',
  `runtime_result`      TEXT            DEFAULT NULL
  COMMENT '运行结果',
  `code_attachment_url` VARCHAR(255)    DEFAULT NULL
  COMMENT '代码附件',
  `topic_id`            BIGINT UNSIGNED DEFAULT NULL
  COMMENT '所属专题',
  `knowledge_id`        BIGINT UNSIGNED DEFAULT NULL
  COMMENT '所属知识点',
  `uploader_id`         BIGINT UNSIGNED NOT NULL
  COMMENT '上传者编号',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='实例教程';

DROP TABLE IF EXISTS `core_student_work`;
CREATE TABLE core_student_work (
  `id`          BIGINT UNSIGNED AUTO_INCREMENT,
  `create_at`   DATETIME         NOT NULL,
  `update_at`   DATETIME         NOT NULL,
  `delete_at`   DATETIME        DEFAULT NULL,
  `title`       VARCHAR(255)     NOT NULL
  COMMENT '标题',
  `author`      VARCHAR(30)      NOT NULL
  COMMENT '作者名',
  `summary`     VARCHAR(255)     NOT NULL
  COMMENT '内容介绍',
  `content`     TEXT             NOT NULL
  COMMENT '效果展示',
  `category`    TINYINT UNSIGNED NOT NULL
  COMMENT '1.课程设计 2.项目实训 3.大赛作品',
  `uploader_id` BIGINT UNSIGNED  NOT NULL
  COMMENT '上传者编号',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='学生作品';

DROP TABLE IF EXISTS `core_resource_document`;
CREATE TABLE core_resource_document (
  `id`          BIGINT UNSIGNED AUTO_INCREMENT,
  `create_at`   DATETIME        NOT NULL,
  `update_at`   DATETIME        NOT NULL,
  `delete_at`   DATETIME        DEFAULT NULL,
  `cover_url`   VARCHAR(255)    DEFAULT NULL
  COMMENT '资源文档封面图路径',
  `content`     TEXT            DEFAULT NULL
  COMMENT '图片内容',
  `uploader_id` BIGINT UNSIGNED NOT NULL
  COMMENT '上传者编号',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='资源文档表';

DROP TABLE IF EXISTS `core_resource`;
CREATE TABLE core_resource (
  `id`          BIGINT UNSIGNED AUTO_INCREMENT,
  `create_at`   DATETIME         NOT NULL,
  `update_at`   DATETIME         NOT NULL,
  `delete_at`   DATETIME        DEFAULT NULL,
  `category`    TINYINT UNSIGNED NOT NULL
  COMMENT '1.电子书 2.优秀网站 3.软件资源',
  `uploader_id` BIGINT UNSIGNED  NOT NULL
  COMMENT '上传者编号',
  PRIMARY KEY (id),
  url           VARCHAR(255)     NOT NULL
  COMMENT '资源地址'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='相关资源表';


DROP TABLE IF EXISTS `core_attachment`;
CREATE TABLE core_attachment (
  `id`              BIGINT UNSIGNED           AUTO_INCREMENT,
  `create_at`       DATETIME         NOT NULL,
  `update_at`       DATETIME         NOT NULL,
  `delete_at`       DATETIME                  DEFAULT NULL,
  `name`            VARCHAR(255)     NOT NULL,
  `module_category` TINYINT UNSIGNED NOT NULL
  COMMENT '所属模块类型 1.教学资源文档 2.学生作品 3.实例作品',
  `url`             VARCHAR(255)     NOT NULL
  COMMENT '📎url',
  `sort`            TINYINT UNSIGNED NOT NULL DEFAULT 0
  COMMENT '排序',
  `module_id`       BIGINT UNSIGNED  NOT NULL
  COMMENT '所属模块编号',
  `uploader_id`     BIGINT UNSIGNED  NOT NULL
  COMMENT '上传者编号',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='附件表';

DROP TABLE IF EXISTS `core_question`;
CREATE TABLE core_question (
  `id`                BIGINT UNSIGNED AUTO_INCREMENT,
  `create_at`         DATETIME     NOT NULL,
  `update_at`         DATETIME     NOT NULL,
  `delete_at`         DATETIME        DEFAULT NULL,
  `title`             VARCHAR(255) NOT NULL
  COMMENT '题目标题',
  `possible_answer_1` VARCHAR(255) NOT NULL
  COMMENT 'A',
  `possible_answer_2` VARCHAR(255) NOT NULL
  COMMENT 'B',
  `possible_answer_3` VARCHAR(255) NOT NULL
  COMMENT 'C',
  `possible_answer_4` VARCHAR(255) NOT NULL
  COMMENT 'D',
  `possible_answer_5` VARCHAR(255) NOT NULL
  COMMENT 'E',
  `possible_answer_6` VARCHAR(255) NOT NULL
  COMMENT 'F',
  `answer_category`   TINYINT UNSIGNED COMMENT '1.单选 2.多选',
  `question_category` TINYINT UNSIGNED COMMENT '1.基础知识 2.进阶知识 3.综合练习',
  `answer`            VARCHAR(255) NOT NULL
  COMMENT '回答结果 1-6',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='题目表';


DROP TABLE IF EXISTS `core_student_test_record`;
CREATE TABLE core_student_test_record (
  `id`                 BIGINT UNSIGNED  AUTO_INCREMENT,
  `create_at`          DATETIME NOT NULL,
  `update_at`          DATETIME NOT NULL,
  `delete_at`          DATETIME         DEFAULT NULL,
  `category`           TINYINT UNSIGNED COMMENT '1.基础知识 2.进阶知识 3.综合练习',
  `question_path`      TEXT     NOT NULL
  COMMENT '问题编号路径 如1/2/3 -1表示未回答的问题',
  `answer_path`        TEXT     NOT NULL
  COMMENT '回答路径1/1,2/1 -1表示未回答',
  `result_path`        TEXT     NOT NULL
  COMMENT '结果路径 1.正确 2.错误 1/2/1/2',
  `score`              TINYINT UNSIGNED DEFAULT 0
  COMMENT '分数',
  `status`             TINYINT UNSIGNED DEFAULT 0
  COMMENT '0.未完成 1.已完成',
  `total_time`         DATETIME         DEFAULT NULL,
  `beyond_per`         TINYINT UNSIGNED DEFAULT 0
  COMMENT '超人数百分比',
  `question_num`       TINYINT UNSIGNED DEFAULT 0
  COMMENT '问题总数',
  `question_right_num` TINYINT UNSIGNED DEFAULT 0
  COMMENT '问题正确数',
  `student_id`         BIGINT UNSIGNED COMMENT '学生编号',
  `uploader_id`        BIGINT UNSIGNED COMMENT '上传者编号',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='测试记录表';

# CREATE TABLE 'core_student_work_category' (
#   `id`        BIGINT UNSIGNED AUTO_INCREMENT,
#   `create_at` DATETIME     NOT NULL,
#   `update_at` DATETIME     NOT NULL,
#   `delete_at` DATETIME        DEFAULT NULL,
#   `name`      VARCHAR(255) NOT NULL
#   COMMENT '名称',
#   `summary`   VARCHAR(255)    DEFAULT NULL
#   COMMENT '摘要'
# )
#   ENGINE = InnoDB
#   DEFAULT CHARSET = utf8
#   COMMENT ='学生作品类别';