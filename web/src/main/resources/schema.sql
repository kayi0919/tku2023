CREATE TABLE IF NOT EXISTS "member"
(
    username character varying(50)  NOT NULL,
    realname character varying(300) NOT NULL,
    password character varying(300) NOT NULL,
    email character varying(200) NOT NULL,
    enabled character varying(1) NOT NULL DEFAULT 1,
    CONSTRAINT member PRIMARY KEY (username)
);


CREATE TABLE IF NOT EXISTS "purchase_record"
(
    record_seq bigint NOT NULL,
    product_name character varying(50) NOT NULL,
    product_price bigint NOT NULL,
    amount bigint NOT NULL,
    total bigint NOT NULL,
    CONSTRAINT product_record_pkey PRIMARY KEY (record_seq)
);

CREATE TABLE IF NOT EXISTS "announcement" (
  announcement_id SERIAL PRIMARY KEY,
  username VARCHAR(50),
  AType VARCHAR(50),
  ATitle VARCHAR(100),
  AContent TEXT,
  FOREIGN KEY (username) REFERENCES "member"(username)
);


CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    product_name VARCHAR(255) NOT NULL,
    product_price bigint NOT NULL,
    amount bigint NOT NULL,
    total bigint NOT NULL,
    operation VARCHAR(255) NOT NULL,
    received_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (username) REFERENCES member(username)
);

CREATE TABLE public.products
(
    products_no serial NOT NULL,
    products_name character varying(50) NOT NULL,
    products_price bigint NOT NULL,
    products_amount bigint NOT NULL,
    products_type character varying(50) NOT NULL,
    products_byte bytea NOT NULL,
    PRIMARY KEY (products_no)
);


insert into purchase_record values ('1','糖果','500','9','4500');
insert into orders values ('1','kayi','床窩','500','9','4500','代付款');