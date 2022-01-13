CREATE DATABASE project;
use project;

CREATE TABLE `users` (
    `id` int PRIMARY KEY AUTO_INCREMENT NOT NULL,
    `username` VARCHAR(100) NOT NULL,
    `created_at` datetime DEFAULT NULL,
    `updated_at` datetime DEFAULT NULL,
    `created_by` int DEFAULT NULL,
    `updated_by` int DEFAULT NULL
);

INSERT INTO `users` (`id`,`created_at`,`created_by`,`updated_at`,`updated_by`, `username`) VALUES (1, NULL, NULL, NULL, NULL, 'son.nguyen');