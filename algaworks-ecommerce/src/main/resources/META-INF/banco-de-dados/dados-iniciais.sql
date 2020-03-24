insert into produto (nome, preco, descricao) values ('Kindle', 499.0, 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into produto (nome, preco, descricao) values ('goPro hero 7', 1500.0, 'Camera HD.');

insert into cliente (nome) values ('Michael Myers');
insert into cliente (nome) values ('Jack Torrance');
insert into cliente (nome) values ('Pinhead');

insert into pedido (cliente_id, data_pedido, total, status) values (1, sysdate(), 100.0, 'AGUARDANDO');

insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (1, 1, 5.0, 2);

insert into categoria (nome) values ("Eletrodomésticos");
insert into categoria (nome) values ("Casa");
insert into categoria (nome) values ("Cozinha");