truncate table cg_clients cascade;
truncate table cg_car cascade ;
truncate table cg_trailer cascade ;
TRUNCATE TABLE cg_box CASCADE;

INSERT INTO cg_clients (id, lastname, firstname, middlename, phone, email, drive_category, password, role)
VALUES (1, 'Golev', 'Kirill', 'Aleksandrovich', '112233', 'kirill@mail.ru', 'A', '11', 'USER');

INSERT INTO cg_car (id, name, volume, lifting_capacity, user_id)
VALUES (1, 'lambo', 1.1, 1, 1);

INSERT INTO cg_trailer (id, name, number, volume, lifting_capacity, car_id)
VALUES (1, 'buk', '10', 1.1, 1, 1);



