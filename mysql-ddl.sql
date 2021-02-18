create database training;

CREATE USER 'training'@'%' IDENTIFIED WITH mysql_native_password BY 'training';
GRANT ALL ON training.* TO 'training'@'%' WITH GRANT OPTION;
FLUSH PRIVILEGES;

use training;

CREATE TABLE article (
    article_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    article_title VARCHAR(128) NOT NULL,
    article_content VARCHAR(512),
    nice_count INT DEFAULT 0,
    picture_url VARCHAR(256),
    created_by VARCHAR(128),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE replies (
    reply_id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    article_id INT NOT NULL,
    reply_content VARCHAR(512),
    picture_url VARCHAR(256),
    created_by VARCHAR(128),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);