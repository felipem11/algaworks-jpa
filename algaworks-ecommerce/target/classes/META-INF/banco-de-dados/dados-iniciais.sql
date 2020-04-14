

insert into produto (id, nome, preco, data_criacao, descricao) values (1, 'Kindle', 499.0, date_sub(sysdate(), interval 1 day), 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.');
insert into produto (id, nome, preco, data_criacao, descricao) values (2, 'Cama', 499.0, date_sub(sysdate(), interval 1 day), 'Cama + macia');
insert into produto (id, nome, preco, data_criacao, descricao) values (3, 'Câmera GoPro Hero 7', 1400.0, date_sub(sysdate(), interval 1 day), 'Desempenho 2x melhor.');
insert into produto (id, nome, preco, data_criacao, descricao) values (4, 'Camera Canon 80d', 3500.0, date_sub(sysdate(), interval 1 day), 'Melhor das Canon');

insert into cliente (cpf, nome) values (191, 'Michael Myers');
insert into cliente (cpf, nome) values (272, 'Jack Torrance');
insert into cliente (cpf, nome) values (44904031008, 'Pinhead');
insert into cliente (cpf, nome) values (77310177029, 'Pennywise');
insert into cliente (cpf, nome) values (92120014086, 'GhostFace');

insert into cliente_detalhe (cliente_id, sexo, data_nascimento) values (1, 'MASCULINO', '2019-01-05');
insert into cliente_detalhe (cliente_id, sexo, data_nascimento) values (2, 'MASCULINO', '2019-01-05');
insert into cliente_detalhe (cliente_id, sexo, data_nascimento) values (3, 'MASCULINO', '2019-01-05');
insert into cliente_detalhe (cliente_id, sexo, data_nascimento) values (4, 'MASCULINO', '2019-01-05');


insert into pedido (id, cliente_id, data_criacao, total, status) values (1, 1, date_sub(sysdate(), interval 1 day), 2898.0, 'PAGO');
insert into pedido (id, cliente_id, data_criacao, total, status) values (2, 2, date_sub(sysdate(), interval 1 day), 499.0, 'AGUARDANDO');
insert into pedido (id, cliente_id, data_criacao, total, status) values (3, 5, date_sub(sysdate(), interval 1 day), 499.0, 'AGUARDANDO');   
insert into pedido (id, cliente_id, data_criacao, total, status) values (4, 5, date_sub(sysdate(), interval 2 month), 3500.0, 'AGUARDANDO');
insert into pedido (id, cliente_id, data_criacao, total, status) values (5, 5, date_sub(sysdate(), interval 2 month), 499.0, 'AGUARDANDO');

insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (1, 1, 499, 2);   -- Eletrônicos
insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (1, 3, 1400, 1);  -- Camera
insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (2, 1, 499, 1);   -- Eletrônicos
insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (4, 4, 3500, 1);  -- Camera
insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values (5, 1, 499, 1);   -- Eletrônicos

insert into pagamento (tipo_pagamento, pedido_id, codigo_barras, numero_cartao, status) values ("cartao", 1, "", "5474241145349011", "PROCESSANDO");

insert into nota_fiscal (pedido_id, data_emissao, xml) values (1, sysdate(), "<xml/>")

insert into categoria (nome) values ('Eletrônicos');    -- 1
insert into categoria (nome) values ("Eletrodomésticos");   -- 2
insert into categoria (nome) values ("Casa");   -- 3
insert into categoria (nome) values ("Livros"); -- 4
insert into categoria (nome) values ("Esportes");   -- 5
insert into categoria (nome) values ("Cozinha");    -- 6
insert into categoria (nome) values ("Futebol");    -- 7
insert into categoria (nome) values ("Natacao");    -- 8
insert into categoria (nome) values ("Notebook");   -- 9
insert into categoria (nome) values ("Smartphone"); -- 10
insert into categoria (nome) values ("Camera");     -- 11

insert into produto_categoria (produto_id, categoria_id) values (1, 1);
insert into produto_categoria (produto_id, categoria_id) values (4, 11);
insert into produto_categoria (produto_id, categoria_id) values (3, 11);

