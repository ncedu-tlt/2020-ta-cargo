
truncate table cg_clients cascade;
truncate table cg_type_cargo cascade;
TRUNCATE TABLE cg_box CASCADE;

INSERT INTO cg_clients (id, lastname, firstname, middlename, phone, email, drive_category, password, role)
VALUES (1, 'Golev', 'Kirill', 'Aleksandrovich', '112233', 'kirill@mail.ru', 'A', '11', 'USER');

INSERT INTO cg_type_cargo (type_id, name, description)
VALUES (2, 'Bogdan', 'Marchenko');

INSERT INTO cg_box (box_id, name, height, width, volume, client_id, type_id, weight)
VALUES (1, 'box', 10, 11, 12.2, 1, 2, 13.3);

INSERT INTO cg_box (box_id, name, height, width, volume, client_id, type_id, weight)
VALUES (2, 'box2', 10, 11, 12.2, 1, 2, 13.3);

INSERT INTO cg_box (box_id, name, height, width, volume, client_id, type_id, weight)
VALUES (3, 'box3', 10, 11, 12.2, 1, 2, 13.3);

