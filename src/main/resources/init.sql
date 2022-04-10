DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
    id       BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username VARCHAR            NOT NULL,
    password VARCHAR            NOT NULL,
    email    VARCHAR            NOT NULL
);

CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_roles_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id   BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR            NOT NULL
);

CREATE TABLE dishes
(
    id            BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name          VARCHAR            NOT NULL,
    price         INTEGER            NOT NULL,
    date          DATE               NOT NULL,
    restaurant_id BIGINT             NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE votes
(
    id            BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    restaurant_id BIGINT             NOT NULL,
    user_id       BIGINT             NOT NULL,
    date          DATE DEFAULT now() NOT NULL,

    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT unique_user_id_per_date_idx UNIQUE (user_id, date)
);

INSERT INTO users(username, password, email)
VALUES ('admin', '$2a$10$geT6B49hxUWBzilJbr/O6OETJv3jb/BFr1EebD1P9k.74lsfQ8bze', 'admin@gmail.com'),
       ('ye', '$2a$10$14RDmSZ5IC1QH8NWhTgea.JppG.KCtYxyet9yQVZf3n0edPlBoJgC', 'ye@gmail.com');

INSERT INTO user_roles(role, user_id)
VALUES ('ADMIN', 1),
       ('USER', 2);

INSERT INTO restaurant(name)
VALUES ('Macdonalds'),
       ('Fridays'),
       ('Kroger'),
       ('Wingstop');

INSERT INTO votes(restaurant_id, user_id, date)
VALUES (1, 1, CURRENT_DATE),
       (1, 2, '2020-02-13'),
       (4, 2, '2022-03-12'),
       (3, 1, '2021-07-24'),
       (3, 2, CURRENT_DATE),
       (2, 1, '2022-04-06');

INSERT INTO dishes(name, price, date, restaurant_id)
VALUES ('Чашечка кофе', 37443, CURRENT_DATE, 1),
        ('Чашечка чая', 777, CURRENT_DATE, 1);