DROP TABLE IF EXISTS `cards`;
DROP TABLE IF EXISTS `users`;


-- 建用户表
CREATE TABLE `users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(50) NOT NULL UNIQUE COMMENT '帐号名称',
  `password` VARCHAR(100) NOT NULL COMMENT '密码',
  `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
  `phone` VARCHAR(20) DEFAULT NULL COMMENT '电话',
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;


-- 建个人名片表
CREATE TABLE `cards` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` INT UNSIGNED NOT NULL  COMMENT '所属用户ID',
  `card_name` VARCHAR(50) NOT NULL COMMENT '显示在名片上的姓名',
  `card_title` VARCHAR(50) NOT NULL COMMENT '名片标题',
   `position` VARCHAR(20) NOT NULL COMMENT '职位',
   `company` VARCHAR(30) COMMENT '公司名称',
   `contact_info` VARCHAR(50) COMMENT '联系方式',
   `is_active` INT DEFAULT 0 COMMENT '是否激活, 0表示未激活，1表示已激活',
  `create_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `update_time` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_cards_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;
