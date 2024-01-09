CREATE TABLE IF NOT EXISTS "member"
(
    username character varying(50)  NOT NULL,
    realname character varying(300) NOT NULL,
    password character varying(300) NOT NULL,
    email character varying(200) NOT NULL,
    enabled character varying(1) NOT NULL DEFAULT 1,
    CONSTRAINT member PRIMARY KEY (username)
    );


CREATE TABLE IF NOT EXISTS "product_record"
(
    record_seq bigint NOT NULL,
    product_name character varying(50) NOT NULL,
    product_price bigint NOT NULL,
    amount bigint NOT NULL,
    total bigint NOT NULL,
    CONSTRAINT product_record_pkey PRIMARY KEY (record_seq)
);

CREATE TABLE announcement (
    announcement_id SERIAL PRIMARY KEY,
    username VARCHAR(50),
    AType VARCHAR(50),
    ATitle VARCHAR(100),
    AContent TEXT,
    FOREIGN KEY (username) REFERENCES member(username)
);