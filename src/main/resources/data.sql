-- DROP TABLE IF EXISTS dishes;
-- DROP TABLE IF EXISTS votes;
-- DROP TABLE IF EXISTS restaurant;
-- DROP TABLE IF EXISTS user_roles;
-- DROP TABLE IF EXISTS users;

-- CREATE TABLE users
-- (
--     id       BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
--     username VARCHAR            NOT NULL,
--     password VARCHAR            NOT NULL,
--     email    VARCHAR            NOT NULL
-- );

-- CREATE TABLE user_roles
-- (
--     user_id BIGINT NOT NULL,
--     role    VARCHAR,
--     CONSTRAINT user_roles_idx UNIQUE (user_id, role),
--     FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
-- );
--
-- CREATE TABLE restaurant
-- (
--     id   BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
--     name VARCHAR            NOT NULL
-- );
--
-- CREATE TABLE dishes
-- (
--     id            BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
--     name          VARCHAR            NOT NULL,
--     price         INTEGER            NOT NULL,
--     date          DATE               NOT NULL,
--     restaurant_id BIGINT             NOT NULL,
--     FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
-- );
--
-- CREATE TABLE votes
-- (
--     id            BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
--     restaurant_id BIGINT             NOT NULL,
--     user_id       BIGINT             NOT NULL,
--     date          DATE DEFAULT now() NOT NULL,
--
--     FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE,
--     FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
--     CONSTRAINT unique_user_id_per_date_idx UNIQUE (user_id, date)
-- );
--
INSERT INTO USERS(name, password, email)
VALUES ('admin', '{noop}admin', 'admin@gmail.com'),
       ('ye', '{noop}kanye', 'ye@gmail.com'),
       ('superuser', '{noop}super', 'super@gmail.com'),
       ('guest', '{noop}guest', 'guest@gmail.com');

INSERT INTO USER_ROLES(role, user_id)
VALUES ('ADMIN', 1),
       ('USER', 2),
       ('USER', 3),
       ('USER', 4);

INSERT INTO RESTAURANT(name)
VALUES ('Macdonalds'),
       ('Fridays'),
       ('Kroger'),
       ('Wingstop');

INSERT INTO VOTES(restaurant_id, user_id, date)
VALUES (1, 1, '2022-03-10'),
       (4, 2, '2022-03-10'),
       (3, 3, '2022-03-10'),
       (2, 4, '2022-03-10'),
       (3, 1, '2022-03-11'),
       (2, 2, '2022-03-11'),
       (2, 3, '2022-03-11'),
       (4, 4, '2022-03-11'),
       (1, 1, '2022-03-12'),
       (1, 2, '2022-03-12'),
       (4, 3, '2022-03-12'),
       (3, 4, '2022-03-12'),
       (2, 1, CURRENT_DATE),
       (3, 2, CURRENT_DATE),
       (4, 3, CURRENT_DATE),
       (3, 4, CURRENT_DATE);

INSERT INTO DISHES(name, price, date, restaurant_id)
VALUES ('Блинчики', 7443, '2022-03-10', 1),
       ('Оладушки', 4312, '2022-03-10', 3),
       ('Чашечка чая', 1432, '2022-03-10', 4),
       ('Ту капс оф пёрпл', 1337, '2022-03-10', 2),
       ('Куриные крылышки', 1000, '2022-03-10', 2),
       ('Чашечка кофе', 2500, '2022-03-10', 4),
       ('Оладушки с блинчиками', 500, '2022-03-11', 3),
       ('Блинчики с оладушками', 300, '2022-03-11', 3),
       ('test123', 2789, '2022-03-11', 2),
       ('Best dish', 3785, '2022-03-11', 2),
       ('Best dish1', 250, '2022-03-11', 4),
       ('Блинчики', 750, '2022-03-11', 1),
       ('Чашечка чая', 1984, '2022-03-11', 3),
       ('Чашечка ромашкового чая', 1337, '2022-03-12', 2),
       ('Оладушки', 1000, '2022-03-12', 2),
       ('Я устал придумывать блюда', 2500, '2022-03-12', 4),
       ('Кока кола', 500, '2022-03-12', 3),
       ('Чашечка фруктового чая', 300, '2022-03-12', 3),
       ('Мандариновый сок', 2789, '2022-03-12', 2),
       ('Шашлык', 3785, CURRENT_DATE, 2),
       ('Хлебушек с блинчиками', 250, CURRENT_DATE, 4),
       ('Блинчики с лепешкой', 750, CURRENT_DATE, 1),
       ('Чашечка чая', 1984, CURRENT_DATE, 3),
       ('null', 1324, CURRENT_DATE, 4),
       ('undefined', 101, CURRENT_DATE, 4);