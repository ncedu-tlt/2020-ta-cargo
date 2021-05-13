
truncate table cg_clients cascade;

INSERT INTO cg_clients (id, lastname, firstname, middlename, phone, email,drive_category, password, role ) VALUES
(1, 'Golev', 'Kirill', 'Aleksandrovich', '112233', 'kirill@mail.ru', 'A', '11', 'USER' );

INSERT INTO cg_clients (id, lastname, firstname, middlename, phone, email,drive_category, password, role ) VALUES
(2, 'Smirnov', 'Andrey', 'Dmitrievich', '223344', 'andrey@mail.ru', 'B', '22', 'USER' );
