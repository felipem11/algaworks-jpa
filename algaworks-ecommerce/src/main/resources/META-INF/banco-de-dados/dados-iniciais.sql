 insert into produto (id, nome, preco, data_criacao, descricao) values (1, 'Kindle', 499.0, date_sub(sysdate(), interval 1 day), 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into produto (id, nome, preco, data_criacao, descricao) values (2, 'cAMA', 499.0, date_sub(sysdate(), interval 1 day), 'Cama + macia');
insert into produto (id, nome, preco, data_criacao, descricao) values (3, 'Câmera GoPro Hero 7', 1400.0, date_sub(sysdate(), interval 1 day), 'Desempenho 2x melhor.');

insert into cliente (cpf, nome) values (191, 'Michael Myers');
insert into cliente (cpf, nome) values (272, 'Jack Torrance');
insert into cliente (cpf, nome) values (44904031008, 'Pinhead');
insert into cliente (cpf, nome) values (77310177029, 'Pennywise');
insert into cliente (cpf, nome) values (92120014086, 'GhostFace');

insert into cliente_detalhe (cliente_id, sexo, data_nascimento) values (1, 'MASCULINO', '2019-01-05');
insert into cliente_detalhe (cliente_id, sexo, data_nascimento) values (2, 'MASCULINO', '2019-01-05');
insert into cliente_detalhe (cliente_id, sexo, data_nascimento) values (3, 'MASCULINO', '2019-01-05');
insert into cliente_detalhe (cliente_id, sexo, data_nascimento) values (4, 'MASCULINO', '2019-01-05');


insert into pedido (id, cliente_id, data_criacao, total, status) values (1, 1, date_sub(sysdate(), interval 1 day), 998.0, 'AGUARDANDO');
insert into pedido (id, cliente_id, data_criacao, total, status) values (2, 2, date_sub(sysdate(), interval 1 day), 499.0, 'AGUARDANDO');
insert into pedido (id, cliente_id, data_criacao, total, status) values (3, 5, date_sub(sysdate(), interval 1 day), 499.0, 'AGUARDANDO');

insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (1, 1, 499, 2);
insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (2, 1, 499, 1);

insert into pagamento (tipo_pagamento, pedido_id, codigo_barras, numero_cartao, status) values ("cartao", 1, "", "5474241145349011", "PROCESSANDO");

insert into categoria (nome) values ('Eletrônicos');
insert into categoria (nome) values ("Eletrodomésticos");
insert into categoria (nome) values ("Casa");
insert into categoria (nome) values ("Cozinha");

insert into produto_categoria (produto_id, categoria_id) values (1, 1);
