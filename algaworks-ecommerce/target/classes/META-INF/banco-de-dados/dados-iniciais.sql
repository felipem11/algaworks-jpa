insert into produto (nome, preco, data_criacao, descricao) values ('Kindle', 499.0, date_sub(sysdate(), interval 1 day), 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into produto (nome, preco, data_criacao, descricao) values ('goPro hero 7', 1500.0, date_sub(sysdate(), interval 1 day), 'Camera HD.');

insert into cliente (nome) values ('Michael Myers');
insert into cliente (nome) values ('Jack Torrance');
insert into cliente (nome) values ('Pinhead');

insert into pedido (cliente_id, data_criacao, total, status) values (1, date_sub(sysdate(), interval 1 day), 100.0, 'AGUARDANDO');

insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (1, 1, 5.0, 2);

insert into categoria (nome) values ("Eletrodomésticos");
insert into categoria (nome) values ("Casa");
insert into categoria (nome) values ("Cozinha");