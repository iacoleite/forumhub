create table usuarios (
    id bigint not null auto_increment primary key,
    nome varchar(255),
    email varchar(255) not null unique,
    senha varchar(255) not null
);

create table cursos (
    id bigint not null auto_increment primary key,
    nome varchar(255) not null,
    categoria varchar(255) not null
);

create table topicos (
    id bigint not null auto_increment primary key,
    titulo varchar(100) not null,
    mensagem varchar(5000) not null,
    data_criacao timestamp not null,
    status varchar(100) not null,
    autor_id bigint not null,
    curso_id bigint not null

);

create table respostas (
    id bigint not null auto_increment primary key,
    mensagem varchar(5000) not null,
    topico_id bigint not null,
    data_criacao timestamp not null,
    autor_id bigint not null,
    solucao boolean default false

);