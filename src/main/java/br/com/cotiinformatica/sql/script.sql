CREATE DATABASE bd_apiclientes;
USE bd_apiclientes;

CREATE TABLE cliente(
	id			CHAR(36)		PRIMARY KEY,
	nome		VARCHAR(150)	NOT NULL,
	cpf			VARCHAR(14)		NOT NULL UNIQUE,
	telefone	VARCHAR(20)		NOT NULL,
	email		VARCHAR(50)		NOT NULL
);

DESC cliente;