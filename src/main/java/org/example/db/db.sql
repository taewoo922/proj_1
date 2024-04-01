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

INSERT INTO articleReply
SET regDate = NOW(),
updateDate = NOW(),
`body` = '댓글1',
memberId = 1,
articleId = 1;

INSERT INTO articleReply
SET regDate = NOW(),
updateDate = NOW(),
`body` = '댓글2',
memberId = 2,
articleId = 2;

SELECT * FROM articleReply;

CREATE TABLE `member` (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	loginId CHAR(100) NOT NULL UNIQUE,
	loginPassword CHAR(100) NOT NULL,
	`name` CHAR(100) NOT NULL
);
INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'admin',
loginPassword = 'admin',
`name` = '관리자';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user1',
loginPassword = 'user1',
`name` = '홍길동';

INSERT INTO `member`
SET regDate = NOW(),
updateDate = NOW(),
loginId = 'user2',
loginPassword = 'user2',
`name` = '홍길순';


CREATE TABLE board (
	id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
	regDate DATETIME NOT NULL,
	updateDate DATETIME NOT NULL,
	`code` CHAR(100) NOT NULL UNIQUE,#free/notice
	`name` CHAR(100) NOT NULL#자유/공지
);

INSERT INTO `board`
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'notice',
`name` = '공지';

INSERT INTO `board`
SET regDate = NOW(),
updateDate = NOW(),
`code` = 'free',
`name` = '자유';

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목',
`body` = '내용1',
memberId = 1,
boardId = 1;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목2',
`body` = '내용2',
memberId = 2,
boardId = 2;

INSERT INTO article
SET regDate = NOW(),
updateDate = NOW(),
title = '제목3',
`body` = '내용3',
memberId = 3,
boardId = 3;

SELECT * FROM article;


