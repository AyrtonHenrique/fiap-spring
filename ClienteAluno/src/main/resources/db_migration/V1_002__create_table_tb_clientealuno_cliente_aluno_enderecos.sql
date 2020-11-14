create table tb_clientealuno_cliente_aluno_enderecos (
			cliente_aluno_id_clientealuno bigint not null, 
			cliente_aluno_enderecos_id_endereco bigint not null, 
			primary key (cliente_aluno_id_clientealuno, cliente_aluno_enderecos_id_endereco))