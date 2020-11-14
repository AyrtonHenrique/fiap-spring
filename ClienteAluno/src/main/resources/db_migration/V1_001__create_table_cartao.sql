create table tb_cartao (id bigint not null, 
			codigo_identificador bigint, 
			datavalidade timestamp, 
			numerocartao bigint, 
			id_clientealuno bigint, 
			primary key (id));