DROP DATABASE IF EXISTS sbs_proj;
CREATE DATABASE sbs_proj;
USE sbs_proj;

CREATE TABLE article (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	title CHAR(100) NOT NULL,
	`body` TEXT NOT NULL,
	memberId INT(10)UNSIGNED NOT NULL,
	boardId INT(10) UNSIGNED NOT NULL,
	INDEX boardId(`boardId`)
	);

CREATE TABLE articleReply (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	`body` CHAR(100) NOT NULL,
	memberId INT(10) UNSIGNED NOT NULL,
	articleId INT(10) UNSIGNED NOT NULL,
	INDEX articleId(`articleId`)
);

CREATE TABLE `member` (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	loginId CHAR(100) NOT NULL UNIQUE,
	loginPassword CHAR(100) NOT NULL,
	`name` CHAR(100) NOT NULL
);

CREATE TABLE board (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	`code` CHAR(100) NOT NULL UNIQUE,#free/notice
	`name` CHAR(100) NOT NULL#자유/공지
);

