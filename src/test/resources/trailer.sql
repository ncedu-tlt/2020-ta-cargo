TRUNCATE TABLE cg_box CASCADE;

INSERT INTO cg_clients (id, lastname, firstname, middlename, phone, email, drive_category, password, role)
VALUES (1, 'Golev', 'Kirill', 'Aleksandrovich', '112233', 'kirill@mail.ru', 'A', '11', 'USER');

INSERT INTO cg_car (id, name, volume, lifting_capacity, user_id)
VALUES (2, 'lambo', 1.1, 1, 1);
INSERT INTO cg_car (id, name, volume, lifting_capacity, user_id)
VALUES (3, 'lambo', 1.1, 1, 1);
INSERT INTO cg_car (id, name, volume, lifting_capacity, user_id)
VALUES (4, 'lambo', 1.1, 1, 1);
INSERT INTO cg_car (id, name, volume, lifting_capacity, user_id)
VALUES (5, 'lambo', 1.1, 1, 1);

INSERT INTO cg_trailer (id, name, number, volume, lifting_capacity, car_id)
VALUES (1, 'buk', '10', 1.1, 1, 2);

INSERT INTO cg_trailer (id, name, number, volume, lifting_capacity, car_id)
VALUES (2, 'buk2', '100', 1.2, 1, 3);

INSERT INTO cg_trailer (id, name, number, volume, lifting_capacity, car_id)
VALUES (3, 'buk3', '1000', 1.3, 1, 4);

INSERT INTO cg_trailer (id, name, number, volume, lifting_capacity, car_id)
VALUES (4, 'buk4', '10000', 1.4, 1, 5);



