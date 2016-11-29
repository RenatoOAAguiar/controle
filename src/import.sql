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

INSERT INTO estoque.funcionario("id","cpf","datanasc","logradouro","nome","numero","setor","sobrenome","datacontratacao","cep","login","senha","cargo_id","permissao_id") VALUES(1,'11111111111','05-04-2001','Rua A','administrador',111,'Setor B','admin','05-04-2001','75380000','admin','admin',1,1);
INSERT INTO estoque.funcionario("id","cpf","datanasc","logradouro","nome","numero","setor","sobrenome","datacontratacao","cep","login","senha","cargo_id","permissao_id") VALUES(2,'22222222222','05-04-2001','Rua A','estoque',111,'Setor B','admin','05-04-2001','75380000','estoque','admin',6,7);
