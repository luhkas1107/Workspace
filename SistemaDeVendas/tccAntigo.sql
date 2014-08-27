create database venda2

use venda2

create table cliente(
id_cliente			bigint identity (1,1) not null constraint id_clientePK primary key (id_cliente),
nome				varchar (50) not null,
cpf					varchar(50) constraint cpf_clienteUncio not null unique, 
telefone			char (50) not null,
endereco			varchar (50) not null, 
num_residencia		char (10) not null,
complemento			varchar (15),
bairro				varchar (20) not null,
cidade				varchar (20) not null,
uf					varchar (2) not null,
email				varchar (50)
) 

create table fornecedor(
id_fornecedor	bigint identity (1,1) not null constraint id_fornecedorPK primary key (id_fornecedor),
empresa			varchar (50) constraint empresa_fornecedorUnico not null unique, 
cnpj			char (20) constraint cnpj_fornecedorUnico not null unique,
nome			varchar (50) not null,
telefone		char (50) not null,
endereco		varchar (50) not null,
num_empresa		char (10) not null,
complemento		varchar (15),
bairro			varchar (20) not null,
cidade			varchar (20) not null,
uf				varchar (2) not null,
email			varchar (50)  
)

create table funcionario(
id_func				bigint identity not null constraint id_funcionarioPK primary key (id_func),
nome_func			varchar (50) not null,
senha_func			varchar (50) not null,
telefone_func		varchar (50),
endereco_func		varchar (50) not null,
num_func			varchar (20) not null,
comple_func			varchar (15)	,
bairro_func			varchar (20) not null,
cidade_func			varchar (20) not null,
uf_func				varchar (2) not null,
cpf_func			varchar(50) constraint cpf_func not null unique,
num_matricula_func	varchar (20), 
funcao_func			varchar (30) not null,
inicio_func			varchar(50) not null,
termino_func		varchar(50), 
salario_func		real not null,
cpts				varchar(50) constraint cpts_funcionarioUnico not null unique ,
sexo_func			varchar not null,
email				varchar (50),
caminho_imagem		varchar(300))
	

create table produto(
id_produto			bigint identity constraint id_produtoPK primary key (id_produto),
id_fornecedor		bigint FOREIGN KEY REFERENCES fornecedor(id_fornecedor) not null,
nome				varchar (50) constraint nome_produtoUnico not null unique,
preco				numeric (10,2) not null,
quantidade_estoque	bigint,
caminho_imagem		varchar(300),
preco_venda			numeric (10,2) not null,
categoria			varchar(40) FOREIGN KEY REFERENCES categoria(nome_categoria) not null
) 

create table pedido(
id_pedido				bigint identity not null constraint id_pedidoPK primary key (id_pedido),
num_pedido				bigint  not null,
id_produto				bigint constraint id_produtoFK1 foreign key references produto(id_produto) not null,
id_cliente				bigint constraint id_clienteFK1 foreign key references cliente(id_cliente) not null,
id_func					bigint constraint id_funcFK6 foreign key references funcionario(id_func) not null,
qtd_produto				bigint not null,
preco_produto_unitario	numeric (10,2) not null, 
preco_produto_total		numeric (10,2) not null,
forma_pagamento			varchar (30) not null,
)


create table venda (
id_venda		bigint identity constraint id_vendaPK primary key (id_venda),
id_func			bigint constraint id_funcFK5 foreign key references funcionario(id_func) ,
id_cliente		bigint foreign key references cliente(id_cliente),
valor_total		numeric(10,2),
valor_pago		numeric (20,2),
forma_pagamento varchar(20),
data			varchar(30) not null,
hora			varchar(30) not null
)

Create table categoria(
id_categoria bigint identity primary key (id_categoria),
nome_categoria varchar(40) constraint nome_categoriaPK unique
)

drop table categoria
DROP TABLE funcionario
DROP TABLE cliente
DROP TABLE fornecedor
DROP TABLE produto
DROP TABLE venda
DROP TABLE pedido	




select * from funcionario
select * from cliente
select * from fornecedor
select * from produto
select * from pedido
select * from venda
select * from categoria where nome_categoria = 'Mouse'

SELECT 
			id_func, 
			nome_func,
			salario_func, 
			cpts, 
			funcao_func, 
			cpf_func, 
			inicio_func, 
			termino_func
			from funcionario 



											--TIPOS DE PESQUISA--
											

DBCC CHECKIDENT(fornecedor, RESEED, 0) -- RESETAR O IDENTITY



											
select MAX(id_pedido)'Id_pedido'from pedido

SELECT COUNT(id_produto) -- CONTAGEM DE ITENS
FROM pedido
where num_pedido = 1


SELECT AVG(valor_pago)'Media' -- MEDIA DE LUCRO
FROM venda

SELECT id_func,AVG(valor_pago) as MediaLucroPorFuncionario -- MEDIA DE LUCRO DOS FUNCIONARIOS
FROM   venda 
GROUP BY id_func


SELECT id_cliente, valor_pago -- LISTA DE CLIENTE QUE FIZERAM COMPRAS ACIMA DA MEDIA DE LUCRO DA EMPRESA
FROM   venda     
WHERE  valor_pago >
(SELECT avg(valor_pago)
FROM venda
WHERE valor_pago>0)

SELECT venda.id_func, funcionario.nome_func 	--PREMIAÇAO Do FUNCIONARIO QUE FEZ A MAIOR VENDA
FROM   venda
inner join funcionario
on venda.id_func = funcionario.id_func
WHERE  venda.valor_pago =
(SELECT max(venda.valor_pago)
FROM venda)

select * from funcionario

SELECT nome_func, cpf_func -- FUNCIONARIO MAIS RECENTE CONTRATADO
FROM   funcionario
WHERE  inicio_func =
(SELECT max(inicio_func)
FROM funcionario)


SELECT nome_func, cpf_func -- FUNCIONARIO MAIS VELHO CONTRATADO
FROM   funcionario
WHERE  inicio_func =
(SELECT MIN(inicio_func)
FROM funcionario)


z
SELECT pedido.id_produto,-- LISTA DE TODOS OS PRODUTOS DOS MAIS VENDIDOS ATE OS MENOS VENDIDO
 produto.nome,
 pedido.preco_produto_unitario 'Id_venda' FROM pedido
inner join produto
on pedido.id_produto = produto.id_produto
order by id_produto desc

select * from pedido

SELECT MAX(id_produto) -- LISTA DO PRODUTO QUE MAIS É VENDIDO
from pedido


Select a.id_venda 'Id_venda',   -- VENDA COM NOME DE FUNCIONARIO
a.id_cliente,
a.data,a.hora,  
a.valor_total,
a.valor_pago,
a.forma_pagamento
,b.id_func 'id_func - Funcionario',
b.nome_func 'Nome_func'
--c.id_cliente 'Id_cliente - Cliente',c.nome 'Nome - Cliente',
From venda a
inner join funcionario b
--inner join cliente c
on a.id_func = b.id_func
--on a.id_cliente = c.id_cliente
--WHERE a.id_func = 2


Select a.id_venda 'ID_VENDA',  -- VENDA COM NOME DO CLIENTE
a.id_cliente 'Id_cliente',
b.nome 'Nome_Cliente'
From venda a 
inner join cliente b
on a.id_cliente = b.id_cliente
--WHERE a.id_cliente = 4



Select a.id_venda 'Id_venda',  -- VENDA COM O NOME DO FUNCIONARIO E CLIENTE
a.id_func'id_func venda',
a.data,
a.hora,
a.id_cliente,
a.valor_total,
a.valor_pago,
a.forma_pagamento,
b.nome_func 'Nome_func',
c.nome 'Nome-Cliente'
From venda a
inner join funcionario b
on a.id_func = b.id_func
inner join cliente c
on a.id_cliente = c.id_cliente
--WHERE a.id_func=2

Select a.id_pedido ,  -- PEDIDO COM O NOME DOS PRODUTOS 
a.num_pedido ,
a.id_produto,
a.id_cliente,
a.id_func,
a.qtd_produto,
a.preco_produto_unitario,
a.preco_produto_total,
a.forma_pagamento,
b.nome,
b.caminho_imagem,
b.quantidade_estoque
From pedido a
inner join produto b
on a.id_produto = b.id_produto
WHERE a.num_pedido = ?
	
go

			Select a.id_pedido, --PEDIDO COM NOME DO CLIENTE E FUNCIONARIO
			a.num_pedido , 
			a.id_produto, 
			a.id_cliente, 
			a.id_func, 
			a.qtd_produto, 
			a.preco_produto_unitario, 
			a.preco_produto_total, 
			a.forma_pagamento, 
			b.nome, 
			b.caminho_imagem, 
			b.quantidade_estoque,
			c.nome 'NomeCliente',
			d.nome_func 'NomeFuncionario'
			From pedido a 
			inner join produto b 
			on a.id_produto = b.id_produto 
			inner join cliente c
			on a.id_cliente = c.id_cliente
			inner join funcionario d
			on a.id_func = d.id_func
			--WHERE a.num_pedido = 2
	
			
SELECT id_func, -- GERAR FOLHA DE PAGAMENTO DE FUNCIONARIO
nome_func, 
salario_func, 
cpts, 
funcao_func, 
cpf_func, 
inicio_func, 
termino_func
 from funcionario
 where id_func = 1
 
 
 
SELECT venda.id_func,--PREMIAÇAO Do FUNCIONARIO QUE FEZ MAIS VENDA PT1
 funcionario.nome_func, 
 funcionario.cpf_func,
 funcionario.caminho_imagem
FROM   venda
inner join funcionario
on venda.id_func = funcionario.id_func
WHERE  venda.id_func =
(SELECT max(venda.id_func)
FROM venda)
 
SELECT count (venda.id_func)'QuantidadeDeVendas'--PREMIAÇAO Do FUNCIONARIO QUE FEZ MAIS VENDA PT2
FROM   venda
inner join funcionario
on venda.id_func = funcionario.id_func
WHERE  venda.id_func =
(SELECT max(venda.id_func)
FROM venda)

 
Select a.id_pedido,
a.num_pedido ,
a.id_produto,
a.id_cliente,
a.id_func,
a.qtd_produto,
a.preco_produto_unitario,
a.preco_produto_total,
a.forma_pagamento,
b.nome,
b.caminho_imagem,
b.quantidade_estoque,
cliente.cpf ,
d.nome_func 'NomeFuncionario'
From pedido a
inner join produto b
on a.id_produto = b.id_produto
inner join cliente
on a.id_cliente = cliente.id_cliente
inner join funcionario d
on a.id_func = d.id_func
--where num_pedido=2

Select a.num_pedido,
b.nome ,
a.qtd_produto,
a.preco_produto_unitario,
a.preco_produto_total,
c.nome 'NomeCliente',
d.valor_total
From pedido a
inner join produto b
on a.id_produto = b.id_produto
inner join cliente c
on a.id_cliente = c.id_cliente
inner join venda d
on a.num_pedido = d.id_venda
where num_pedido=1

