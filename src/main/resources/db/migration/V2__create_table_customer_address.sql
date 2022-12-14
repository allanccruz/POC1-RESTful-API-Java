create table tb_customer_address (
	id varchar(255) primary key,
	city varchar(255) not null,
	neighborhood varchar(255) not null,
	address varchar(255) not null,
	number varchar(255) not null,
	complement varchar(255),
	zipcode varchar(255) not null,
	customer_id varchar(255) not null,
	main_address boolean not null,
	foreign key (customer_id) references tb_customer(id)
);