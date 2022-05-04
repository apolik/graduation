INSERT INTO USERS(name, password, email)
VALUES ('admin', '{noop}admin', 'admin@gmail.com'),
       ('ye', '{noop}kanye', 'ye@gmail.com'),
       ('superuser', '{noop}super', 'super@gmail.com'),
       ('guest', '{noop}guest', 'guest@gmail.com'),
       ('test', '{noop}testuser', 'test@gmail.com');

INSERT INTO USER_ROLES(role, user_id)
VALUES ('ADMIN', 1),
       ('USER', 2),
       ('USER', 3),
       ('USER', 4),
       ('USER', 5);

INSERT INTO RESTAURANT(name)
VALUES ('Macdonalds'),
       ('Fridays'),
       ('Kroger'),
       ('Wingstop');

INSERT INTO VOTES(restaurant_id, user_id, vote_date)
VALUES (1, 1, '2022-03-10'),
       (4, 2, '2022-03-10'),
       (3, 3, '2022-03-10'),
       (2, 4, '2022-03-10'),
       (3, 1, '2022-03-11'),
       (2, 2, '2022-03-11'),
       (2, 3, '2022-03-11'),
       (4, 4, '2022-03-11'),
       (2, 1, CURRENT_DATE),
       (3, 2, CURRENT_DATE),
       (4, 3, CURRENT_DATE),
       (3, 4, CURRENT_DATE);



INSERT INTO DISHES(name, price, creation_date, restaurant_id)
VALUES ('Блинчики', 7443.0, '2022-03-10', 1),
       ('Оладушки', 4312.0, '2022-03-10', 3),
       ('Чашечка чая', 1432.0, '2022-03-10', 4),
       ('Ту капс оф пёрпл', 1337.0, '2022-03-10', 2),
       ('Куриные крылышки', 1000.0, '2022-03-10', 2),
       ('Чашечка кофе', 2500.0, '2022-03-10', 4),
       ('Оладушки с блинчиками', 500.0, '2022-03-11', 3),
       ('Блинчики с оладушками', 300.0, '2022-03-11', 3),
       ('test123', 2789.0, '2022-03-11', 2),
       ('Best dish', 3785.0, '2022-03-11', 2),
       ('Best dish1', 250.0, '2022-03-11', 4),
       ('Блинчики', 750.0, '2022-03-11', 1),
       ('Чашечка чая', 1984.0, '2022-03-11', 3),
       ('Шашлык', 3785.0, CURRENT_DATE, 2),
       ('Хлебушек с блинчиками', 250.0, CURRENT_DATE, 4),
       ('Блинчики с лепешкой', 750.0, CURRENT_DATE, 1),
       ('Чашечка чая', 1984.0, CURRENT_DATE, 3),
       ('null', 1324.0, CURRENT_DATE, 4),
       ('undefined', 101.0, CURRENT_DATE, 4);