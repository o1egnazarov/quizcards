CREATE TYPE e_user_role AS ENUM ('ADMIN', 'USER');

CREATE TABLE t_user
(
    c_id                      SERIAL PRIMARY KEY,
    c_username                VARCHAR(40)  NOT NULL UNIQUE,
    c_email                   VARCHAR(40)  NOT NULL UNIQUE,
    c_password                VARCHAR(255) NOT NULL,
    c_date                    DATE,
    c_is_enabled              BOOLEAN,
    c_role                    e_user_role  NOT NULL DEFAULT 'USER',
    c_verification_code       VARCHAR(16),
    c_verification_expiration TIMESTAMP(6)
);

CREATE TABLE t_module
(
    c_id          SERIAL PRIMARY KEY,
    c_description VARCHAR(255),
    c_title       VARCHAR(50) NOT NULL,
    c_user_id     INT REFERENCES t_user (c_id)
);

CREATE TABLE t_card
(
    c_id         SERIAL PRIMARY KEY,
    c_front_side VARCHAR(255),
    c_back_side  VARCHAR(255),
    c_module_id  INT REFERENCES t_module (c_id)
);
