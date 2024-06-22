create table usuarios (
    id bigint not null auto_increment primary key,
    nome varchar(255),
    email varchar(255) not null unique,
    senha varchar(255) not null
);

create table perfis (
    id bigint not null auto_increment primary key,
    nome varchar(255) not null
);

create table usuario_perfis (
    usuario_id bigint not null,
    perfil_id bigint not null,
    CONSTRAINT fk_usuario_perfis_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_usuario_perfis_perfil FOREIGN KEY (perfil_id) REFERENCES perfis(id),
    PRIMARY KEY (usuario_id, perfil_id)
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
    curso_id bigint not null,
    CONSTRAINT fk_topicos_autor FOREIGN KEY (autor_id) REFERENCES usuarios(id),
    CONSTRAINT fk_topicos_curso FOREIGN KEY (curso_id) REFERENCES cursos(id)
);

create table respostas (
    id bigint not null auto_increment primary key,
    mensagem varchar(5000) not null,
    topico_id bigint not null,
    data_criacao timestamp not null,
    autor_id bigint not null,
    solucao boolean default false,
    CONSTRAINT fk_respostas_topico FOREIGN KEY (topico_id) REFERENCES topicos(id),
    CONSTRAINT fk_respostas_autor FOREIGN KEY (autor_id) REFERENCES usuarios(id),
    unique key unique_respostas(topico_id, autor_id)
);