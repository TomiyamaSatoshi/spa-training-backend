create database training;

CREATE USER 'training'@'%' IDENTIFIED WITH mysql_native_password BY 'training';
GRANT ALL ON training.* TO 'training'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

use training;

CREATE TABLE articles (
    article_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    article_title VARCHAR(128) NOT NULL,
    article_content VARCHAR(512),
    nice_count INT DEFAULT 0,
    picture_url VARCHAR(256),
    created_by VARCHAR(128),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO articles (article_title,article_content,picture_url,created_by) VALUES ('test1','テストコンテンツ1','http://','TEST');
INSERT INTO articles (article_title,article_content,picture_url,created_by) VALUES ('test2','テストコンテンツ2','http://','TEST');

CREATE TABLE replies (
    reply_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    article_id INT NOT NULL,
    reply_content VARCHAR(512),
    picture_url VARCHAR(256),
    created_by VARCHAR(128),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
INSERT INTO replies (article_id,reply_content,picture_url,created_by) VALUES ('1','リプライコンテンツ1','http://','TEST');
INSERT INTO replies (article_id,reply_content,picture_url,created_by) VALUES ('1','リプライコンテンツ2','http://','TEST');
INSERT INTO replies (article_id,reply_content,picture_url,created_by) VALUES ('2','リプライコンテンツ3','http://','TEST');
INSERT INTO replies (article_id,reply_content,picture_url,created_by) VALUES ('2','リプライコンテンツ4','http://','TEST');
