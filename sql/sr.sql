CREATE DATABASE sr;

# common properties for table
# `id`        BIGINT UNSIGNED AUTO_INCREMENT,
# `create_at` DATETIME NOT NULL,
# `update_at` DATETIME NOT NULL,
# `delete_at` DATETIME        DEFAULT NULL,
# PRIMARY KEY (id)

CREATE TABLE core_user (
  `id`              BIGINT UNSIGNED AUTO_INCREMENT,
  `create_at`       DATETIME     NOT NULL,
  `update_at`       DATETIME     NOT NULL,
  `delete_at`       DATETIME        DEFAULT NULL,
  `username`        VARCHAR(30)  NOT NULL,
  `name`            VARCHAR(30)     DEFAULT NULL,
  `password`        VARCHAR(255) NOT NULL,
  `salt`            VARCHAR(255) NOT NULL,
  `level`           TINYINT UNSIGNED COMMENT '类别 1系统管理员 2教师 3学生',
  `last_login_time` DATETIME        DEFAULT NULL
  COMMENT '上次登录时间',
  PRIMARY KEY (id),
  UNIQUE KEY (username)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='用户表';


CREATE TABLE core_knowledge (
  `id`          BIGINT UNSIGNED AUTO_INCREMENT,
  `create_at`   DATETIME        NOT NULL,
  `update_at`   DATETIME        NOT NULL,
  `delete_at`   DATETIME        DEFAULT NULL,
  `name`        VARCHAR(255)    DEFAULT NOT NULL
  COMMENT '知识点名称',
  `sort`        INT UNSIGNED    DEFAULT 0
  COMMENT '排序',
  `parent_id`   BIGINT UNSIGNED DEFAULT NULL
  COMMENT '直接父类资源id',
  `path`        TEXT            DEFAULT NULL
  COMMENT '所有父类id,以/分隔 如:12/13/14',
  `level`       TINYINT UNSIGNED COMMENT '类别 1.基础知识点  2.进阶知识点',
  `depth`       INT UNSIGNED COMMENT '深度',
  `uploader_id` BIGINT UNSIGNED NOT NULL
  COMMENT '上传者id',
  `topic_id`    BIGINT UNSIGNED DEFAULT NULL
  COMMENT '所属专题id',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='知识点表';

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
  COMMENT '上传者id',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='拓展知识表';


CREATE TABLE core_topic (
  `id`          BIGINT UNSIGNED AUTO_INCREMENT,
  `create_at`   DATETIME        NOT NULL,
  `update_at`   DATETIME        NOT NULL,
  `delete_at`   DATETIME        DEFAULT NULL,
  `name`        VARCHAR(255
                )               NOT NULL
  COMMENT '专题名称',
  `uploader_id` BIGINT UNSIGNED NOT NULL
  COMMENT '上传者id',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='专题表';

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
  COMMENT '上传者id',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='实例教程';


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
  COMMENT '上传者id',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='学生作品';

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
  COMMENT '上传者id',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='资源文档表';

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
  COMMENT '上传者id',
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COMMENT ='附件表';


