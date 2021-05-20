truncate table cg_car cascade;
truncate table cg_address cascade;
truncate table cg_status cascade;
truncate table cg_type_cargo cascade;
truncate table cg_order cascade;
truncate table cg_clients cascade;
truncate table cg_trailer cascade;
truncate table cg_box cascade;


INSERT INTO cg_clients (id, lastname, firstname, middlename, phone, email,drive_category, password, role ) VALUES
(1, 'Golev', 'Kirill', 'Aleksandrovich', '112233', 'kirill@mail.ru', 'A', '11', 'USER' );

INSERT INTO cg_car (id, name, volume, lifting_capacity, user_id) VALUES
(1, 'tesla', 2.5, 256, 1);




INSERT INTO cg_clients (id, lastname, firstname, middlename, phone, email,drive_category, password, role ) VALUES
(2, 'Smirnov', 'Andrey', 'Dmitrievich', '223344', 'andrey@mail.ru', 'B', '22', 'USER' );



INSERT INTO cg_clients (id, lastname, firstname, middlename, phone, email,drive_category, password, role ) VALUES
(3, 'Smirnov', 'Alex', 'Alexeevich', '11111111', 'alexGod@mail.ru', 'B', '12', 'USER' );








INSERT INTO cg_address (id, country, city, street, home, apartment) VALUES
(1, 'RF', 'Tolyatti', 'Tsvetnoy', '35', '126');

INSERT INTO cg_address (id, country, city, street, home, apartment) VALUES
(2, 'RF', 'Tolyatti', 'Zhukova', '14', '91');

INSERT INTO cg_address (id, country, city, street, home, apartment) VALUES
(3, 'RF', 'Samara', 'Frunze', '11', '12');



INSERT INTO cg_type_cargo (type_id, name, description)
VALUES (1, 'Посылка', 'мелкий покет');

INSERT INTO cg_box (box_id, name, height, width, volume, client_id, type_id, weight)
VALUES (1, 'box', 10, 11, 12.2, 3, 1, 13.3);

INSERT INTO cg_box (box_id, name, height, width, volume, client_id, type_id, weight)
VALUES (2, 'box', 10, 11, 12.2, 2, 1, 13.3);


INSERT INTO cg_status (status_id, name, description)
VALUES (1, 'open', 'статус открыт');

INSERT INTO cg_status (status_id, name, description)
VALUES (2, 'work', 'статус в работе');


INSERT INTO cg_order (order_id, name, destination_id, location_id, driver_id, box_id, price, receiver_id, status_id)
VALUES (4, 'order', 1, 2, 1, 1, 3800 ,2 ,1);

INSERT INTO cg_order (order_id, name, destination_id, location_id, driver_id, box_id, price, receiver_id, status_id)
VALUES (5, 'order', 2, 3, 1, 1, 3800 ,2 ,2);

INSERT INTO cg_order (order_id, name, destination_id, location_id, driver_id, box_id, price, receiver_id, status_id)
VALUES (6, 'order', 2, 3, 1, 2, 3800 ,3 ,1);

INSERT INTO cg_order (order_id, name, destination_id, location_id, driver_id, box_id, price, receiver_id, status_id)
VALUES (7, 'order', 2, 3, 1, 2, 3800 ,3 ,2);




