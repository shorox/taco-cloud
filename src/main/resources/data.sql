delete from taco_orders_tacos;
delete from tacos_ingredients;
delete from tacos;
delete from taco_orders;

delete from ingredients;
insert into ingredients (id, name, type) values ('FLTO', 'Flour Tortilla', 'WRAP');
insert into ingredients (id, name, type) values ('COTO', 'Corn Tortilla', 'WRAP');
insert into ingredients (id, name, type) values ('GRBF', 'Ground Beef', 'PROTEIN');
insert into ingredients (id, name, type) values ('CARN', 'Carnitas', 'PROTEIN');
insert into ingredients (id, name, type) values ('TMTO', 'Diced Tomatoes', 'VEGGIES');
insert into ingredients (id, name, type) values ('LETC', 'Lettuce', 'VEGGIES');
insert into ingredients (id, name, type) values ('CHED', 'Cheddar', 'CHEESE');
insert into ingredients (id, name, type) values ('JACK', 'Monterrey Jack', 'CHEESE');
insert into ingredients (id, name, type) values ('SLSA', 'Salsa', 'SAUCE');
insert into ingredients (id, name, type) values ('SRCR', 'Sour Cream', 'SAUCE');

delete from taco_users;
insert into taco_users (id, username, password, fullname, street, city, state, zip, phone_number)
    values (1, 'user', '9903e7a2778e2d6e74f6e1a4d2d3f45078aa5e9771fabd2ff3185c5b47e88116',
    'user user', 'noname street', 'noname city', 'NO', '000000', '+7(900) 000 00 00');
