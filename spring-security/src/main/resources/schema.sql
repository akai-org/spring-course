CREATE TABLE book
(
    id                 INTEGER      NOT NULL AUTO_INCREMENT,
    title              VARCHAR(255) NOT NULL,
    author             VARCHAR(255) NOT NULL,
    last_modified_by   VARCHAR(255) NOT NULL,
    last_modified_date DATETIME     NOT NULL,
    created_by         VARCHAR(255) NOT NULL,
    created_date       DATETIME     NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user
(
    id       INTEGER      NOT NULL AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

