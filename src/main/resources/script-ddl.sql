CREATE DATABASE IF NOT EXISTS app;

CREATE TABLE IF NOT EXISTS cliente (
	codigo int not null primary key auto_increment,
    nome varchar(255)
);

INSERT INTO cliente (nome) VALUES ("Maria Roberta");
INSERT INTO cliente (nome) VALUES ("Diogo Mascarenhas");

CREATE TABLE IF NOT EXISTS produto (
	codigo int not null primary key auto_increment,
	descricao varchar(255),
	preco double
);

INSERT INTO produto (descricao, preco) VALUES ('Hambúrguer de Siri', 25.5);
INSERT INTO produto (descricao, preco) VALUES ('Milkshake de Bacon', 15.75);
INSERT INTO produto (descricao, preco) VALUES ('Coca-cola de Cereja', 9.2);

CREATE TABLE IF NOT EXISTS pedido (
	codigo int not null primary key auto_increment,
	data date,
	cliente_cod int,
	foreign key (cliente_cod) references cliente(codigo)
);

INSERT INTO pedido (data, cliente_cod) VALUES ('2022-11-01', 1);
INSERT INTO pedido (data, cliente_cod) VALUES ('2022-10-31', 1);
INSERT INTO pedido (data, cliente_cod) VALUES ('2022-10-30', 2);

CREATE TABLE IF NOT EXISTS produto_pedido (
	codigo int not null primary key auto_increment,
	codigo_produto int not null,
	codigo_pedido int not null,
	foreign key (codigo_produto) references produto(codigo),
	foreign key (codigo_pedido) references pedido(codigo)
);

INSERT INTO produto_pedido (codigo_produto, codigo_pedido) VALUES (1, 1);
INSERT INTO produto_pedido (codigo_produto, codigo_pedido) VALUES (2, 1);
INSERT INTO produto_pedido (codigo_produto, codigo_pedido) VALUES (3, 1);
INSERT INTO produto_pedido (codigo_produto, codigo_pedido) VALUES (1, 2);
INSERT INTO produto_pedido (codigo_produto, codigo_pedido) VALUES (1, 3);
INSERT INTO produto_pedido (codigo_produto, codigo_pedido) VALUES (2, 3);