
create table if not exists ingredients (
    id varchar(4) not null,
    name varchar(25) not null,
    type varchar(10) not null
);

create table if not exists tacos (
    id identity,
    name varchar(50) not null,
    created_at timestamp not null
);

create table if not exists tacos_ingredients (
    taco bigint not null,
    ingredient varchar(4) not null
);

alter table tacos_ingredients add foreign key (taco) references tacos(id);

alter table tacos_ingredients add foreign key (ingredient) references ingredients(id);

create table if not exists taco_users (
    id identity,
    username varchar(50) not null,
    password varchar(250) not null,
    fullname varchar(50) not null,
    street varchar(50) not null,
    city varchar(50) not null,
    state varchar(2) not null,
    zip varchar(10) not null,
    phone_number varchar(20) not null
);

create table if not exists taco_orders (
    id identity,
    taco_user bigint not null,
    delivery_name varchar(50) not null,
    delivery_street varchar(50) not null,
    delivery_city varchar(50) not null,
    delivery_state varchar(2) not null,
    delivery_zip varchar(10) not null,
    cc_number varchar(16) not null,
    cc_expiration varchar(5) not null,
    cc_cvv varchar(3) not null,
    created_at timestamp not null
);

alter table taco_orders add foreign key (taco_user) references taco_users(id);

create table if not exists taco_orders_tacos (
    taco_order bigint not null,
    taco bigint not null
);

alter table taco_orders_tacos add foreign key (taco_order) references taco_orders(id);

alter table taco_orders_tacos add foreign key (taco) references tacos(id);
