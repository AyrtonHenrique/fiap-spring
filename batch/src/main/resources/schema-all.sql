drop table if exists ClienteAluno;

create table ClienteAluno (
    id bigint auto_increment primary key,
    rm bigint NULL,
    nome varchar(200) NULL,
    cpf varchar(14) NULL, 
    rg varchar NULL,
    turma varchar NULL,
    dataNascimento datetime NULL
)