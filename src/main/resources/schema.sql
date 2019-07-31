
create table if not exists ingredient (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists taco (
    id identity,
    name varchar(50) not null,
    createdAt timestamp not null
);

create table if not exists taco_ingredients (
    taco bigint not null,
    ingredient varchar(4) not null
);

alter table taco_ingredients add foreign key (taco) references taco(id);

alter table taco_ingredients add foreign key (ingredient) references ingredient(id);

create table if not exists taco_order (
    id identity,
    delivery_name varchar(50) not null,
    delivery_street varchar(50) not null,
    delivery_city varchar(50) not null,
    delivery_state varchar(2) not null,
    delivery_zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    placeAt timestamp not null,
);

create table if not exists taco_order_tacos (
    taco_order bigint not null,
    taco bigint not null
);

alter table taco_order_tacos add foreign key (taco_order) references taco_order(id);

alter table taco_order_tacos add foreign key (taco) references taco(id);
