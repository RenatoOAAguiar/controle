INSERT INTO estoque.cargo("id","nome") VALUES(1,'Administrador');
INSERT INTO estoque.cargo("id","nome") VALUES(2,'Recursos Humanos');
INSERT INTO estoque.cargo("id","nome") VALUES(3,'TI');
INSERT INTO estoque.cargo("id","nome") VALUES(4,'Diretor');
INSERT INTO estoque.cargo("id","nome") VALUES(5,'Gerente');
INSERT INTO estoque.cargo("id","nome") VALUES(6,'Controlador de Estoque');

INSERT INTO estoque.permissao("id","tipo") VALUES(1,'Administrador');
INSERT INTO estoque.permissao("id","tipo") VALUES(2,'TI');
INSERT INTO estoque.permissao("id","tipo") VALUES(3,'Geral');
INSERT INTO estoque.permissao("id","tipo") VALUES(4,'RH');
INSERT INTO estoque.permissao("id","tipo") VALUES(5,'Gerencia');
INSERT INTO estoque.permissao("id","tipo") VALUES(6,'Estoque');
INSERT INTO estoque.permissao("id","tipo") VALUES(7,'Cliente');

INSERT INTO estoque.fornecedor(id, cnpj, datacadastro, nome, razaosocial) VALUES (1, '1234567891011', '05-04-2001' , 'Fornecedor 1', 'Fornecedor 1');
INSERT INTO estoque.fornecedor(id, cnpj, datacadastro, nome, razaosocial) VALUES (2, '1234567891011', '05-04-2001' , 'Fornecedor 2', 'Fornecedor 2');

INSERT INTO estoque.produto(id, dataalteracao, nome, quantidade, valor, fornecedor_id) VALUES (1,'05-04-2001', 'Produto 1',30, 11.5, 1);
INSERT INTO estoque.produto(id, dataalteracao, nome, quantidade, valor, fornecedor_id) VALUES (2,'05-04-2001', 'Produto 2',100, 35.5, 2);

INSERT INTO estoque.funcionario("id","cpf","datanasc","logradouro","nome","numero","setor","sobrenome","datacontratacao","cep","login","senha","cargo_id","permissao_id") VALUES(1,'11111111111','05-04-2001','Rua A','administrador',111,'Setor B','admin','05-04-2001','75380000','admin','admin',1,1);
INSERT INTO estoque.funcionario("id","cpf","datanasc","logradouro","nome","numero","setor","sobrenome","datacontratacao","cep","login","senha","cargo_id","permissao_id") VALUES(2,'22222222222','05-04-2001','Rua A','estoque',111,'Setor B','admin','05-04-2001','75380000','estoque','admin',6,7);
INSERT INTO estoque.funcionario("id","cpf","datanasc","logradouro","nome","numero","setor","sobrenome","datacontratacao","cep","login","senha","cargo_id","permissao_id") VALUES(3,'33333333333','05-04-2010','Rua A','renato',111,'Setor B','renato','05-04-2001','75380000','renato','renato',1,1);
