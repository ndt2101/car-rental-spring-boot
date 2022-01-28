CREATE DATABASE project;
use project;

INSERT INTO `user` (`id`,`created_at`,`created_by`,`updated_at`,`updated_by`, `username`) VALUES (1, NULL, NULL, NULL, NULL, 'son.nguyen');

-- ROLE table
INSERT INTO `role` (`name`) VALUES ('ROLE_CUSTOMER');
INSERT INTO `role` (`name`) VALUES ('ROLE_ADMIN');

-- USER-ROLE table
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1, 2);
