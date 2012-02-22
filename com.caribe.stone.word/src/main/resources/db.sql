CREATE
    TABLE PUBLIC.word
    (
        id BIGINT identity ,
        word VARCHAR(64),
        level BIGINT,
        createdTime TIMESTAMP NOT NULL,
        PRIMARY KEY (id)
    )