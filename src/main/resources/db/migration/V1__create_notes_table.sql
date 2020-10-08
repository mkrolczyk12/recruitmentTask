DROP TABLE IF EXISTS notes;

CREATE TABLE notes (
    id LONG PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    content VARCHAR(10000) NOT NULL,
    note_id VARCHAR(100),
    version INT NULL,
    actual_version BOOLEAN,
    deleted BOOLEAN,
    created DATETIME NULL,
    modified DATETIME NULL,
    PRIMARY KEY(id)
);
