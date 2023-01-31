DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
    `author` varchar(255) DEFAULT NULL COMMENT '图书作者',
    `isbn` varchar(255) DEFAULT NULL COMMENT '图书ISBN编码',
    `name` varchar(255) DEFAULT NULL COMMENT '图书名称',
    `pages` int(11) DEFAULT NULL COMMENT '图书页数',
    `price` double DEFAULT NULL COMMENT '单价',
    `publish` varchar(255) DEFAULT NULL COMMENT '出版社',
    `publish_time` datetime(6) DEFAULT NULL COMMENT '出版时间',
    `size` int(11) DEFAULT NULL COMMENT '库存',
    `translate` varchar(255) DEFAULT NULL COMMENT '翻译',
    `type` varchar(255) DEFAULT NULL COMMENT '分类',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `book` */

insert  into `book`(`id`,`author`,`isbn`,`name`,`pages`,`price`,`publish`,`publish_time`,`size`,`translate`,`type`) values (46,NULL,'1',' 红楼梦',NULL,NULL,'人民出版社',NULL,100,NULL,'3'),(47,NULL,'2','我是猫',NULL,NULL,'人民出版社',NULL,200,NULL,'3'),(48,NULL,'3','白痴',NULL,NULL,'人民出版社',NULL,100,NULL,'3'),(49,NULL,'4','契诃夫短篇小说选',NULL,NULL,'人民出版社',NULL,100,NULL,'3'),(50,NULL,'5','小妇人',NULL,NULL,'人民出版社',NULL,100,NULL,'3'),(51,NULL,'6','一九八四',NULL,NULL,'人民出版社',NULL,100,NULL,'3'),(52,NULL,'7','中国科幻基石丛书',NULL,NULL,'人民出版社',NULL,100,NULL,'2'),(53,NULL,'8','红岩',NULL,NULL,'人民出版社',NULL,100,NULL,'4'),(54,NULL,'9','平凡的世界',NULL,NULL,'人民出版社',NULL,100,NULL,'1'),(55,NULL,'10','基督山伯爵',NULL,NULL,'人民出版社',NULL,100,NULL,'2'),(56,NULL,'11','牛虻',NULL,NULL,'人民出版社',NULL,100,NULL,'2');

/*Table structure for table `borrow` */

DROP TABLE IF EXISTS `borrow`;

CREATE TABLE `borrow` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `book_id` int(11) DEFAULT NULL COMMENT '图书ID',
  `create_time` datetime(6) DEFAULT NULL COMMENT '借阅时间',
  `update_time` datetime(6) DEFAULT NULL COMMENT '实际归还时间',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `end_time` datetime(6) DEFAULT NULL COMMENT '归还时间',
  `ret` int(11) DEFAULT NULL COMMENT '是否归还（0 已归还 1 未归还）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `borrow` */

/*Table structure for table `notice` */

DROP TABLE IF EXISTS `notice`;

CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
  `title` varchar(255) DEFAULT NULL COMMENT '标题',
  `date` datetime(6) DEFAULT NULL COMMENT '发布时间',
  `content` text COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `notice` */

insert  into `notice`(`id`,`title`,`date`,`content`) values (20,'双旦活动','2022-12-29 08:00:00.000000','系统用户每人增加一本图书借阅'),(21,'测试公告','2023-01-30 08:00:00.000000','1');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
    `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
    `number` varchar(255) DEFAULT NULL COMMENT 'number',
    `name` varchar(255) DEFAULT NULL COMMENT 'name',
    `sex` varchar(255) DEFAULT NULL COMMENT 'sex',
    `college` varchar(255) DEFAULT NULL COMMENT 'college',
    `major` varchar(255) DEFAULT NULL COMMENT 'major',
    `year` varchar(255) DEFAULT NULL COMMENT 'year',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`number`,`name`,`sex`,`college`,`major`,`year`) values (2,'202309010872','刘强','男','艺术院','美术','2023');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
     `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID主键',
     `address` varchar(255) DEFAULT NULL COMMENT '地址',
     `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
     `birthday` datetime(6) DEFAULT NULL COMMENT '生日',
     `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
     `is_admin` int(11) DEFAULT NULL COMMENT '是否为管理员（0 管理员 1 普通用户）',
     `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
     `password` varchar(255) DEFAULT NULL COMMENT '密码',
     `size` int(11) DEFAULT NULL COMMENT '可借数量',
     `tel` varchar(255) DEFAULT NULL COMMENT '电话',
     `username` varchar(255) DEFAULT NULL COMMENT '用户名',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

/*Data for the table `users` */

insert  into `users`(`id`,`address`,`avatar`,`birthday`,`email`,`is_admin`,`nickname`,`password`,`size`,`tel`,`username`) values (4,'中国','','2022-09-30 08:00:00.000000','cya@163.com',0,'系统管理员','1',2,'18365467837','admin'),(51,NULL,NULL,NULL,NULL,1,'图书管理员','1',NULL,'','user');
