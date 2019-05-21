drop database if exists lib;
create database lib;
use lib;

create table counter (
	name varchar(50),
	cnt integer
);

create table users (
	id integer primary key,
	librn integer,
	name varchar(50),
	surname varchar(50),
	login varchar(50),
	password varchar(50)
);

create table books (
	id integer primary key,
	isbn varchar(50),
	title varchar(50),
	author varchar(50),
	genre varchar(50)
);

create table requests (
	id integer primary key,
	user_id integer,
	book_id integer,
	take_date date,
	return_date date,
	foreign key (user_id) references users(id),
	foreign key (book_id) references books(id)
);
