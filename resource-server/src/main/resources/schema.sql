CREATE TABLE BLOG_POST
(
    ID int auto_increment primary key,
    TITLE varchar(50)  not null,
    CONTENT varchar(500) not null
);

CREATE TABLE BLOG_POST_COMMENT
(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    CONTENT VARCHAR(500) NOT NULL,
    BLOG_POST_ID INT NOT NULL, -- 외래 키
    CONSTRAINT FK_BLOG_POST FOREIGN KEY (BLOG_POST_ID) REFERENCES BLOG_POST(ID)
);
