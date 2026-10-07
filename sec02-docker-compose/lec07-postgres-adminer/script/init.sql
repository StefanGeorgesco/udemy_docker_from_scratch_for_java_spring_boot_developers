create table product (
    id serial primary key,
    name varchar(50) not null,
    price numeric(10, 2) not null
);

insert into product (name, price) values
('iPhone', 1200.00),
('iPad', 800.00),
('macBook', 3000.00);