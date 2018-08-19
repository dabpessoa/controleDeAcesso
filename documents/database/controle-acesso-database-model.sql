-- drop schema controle_acesso cascade;
create schema controle_acesso;

-- drop table controle_acesso.modulo;
create sequence controle_acesso.modulo_id_seq;
create table controle_acesso.modulo (
  id bigint not null default nextval('controle_acesso.modulo_id_seq'),
  descricao varchar(150) not null,
  label varchar(50) not null,
  constraint id_modulo primary key (id),
  constraint modulo_uk unique(label)
);
alter sequence controle_acesso.modulo_id_seq OWNED BY controle_acesso.modulo.id;

-- drop table controle_acesso.grupo;
create sequence controle_acesso.grupo_id_seq;
create table controle_acesso.grupo (
  id bigint not null default nextval('controle_acesso.grupo_id_seq'),
  fk_modulo bigint not null,
  descricao character varying(60),
  label character varying(60) not null,
  nivel integer,
  constraint id_grupo primary key (id),
  constraint grupo_modulo_uk unique(label, fk_modulo)
);
alter sequence controle_acesso.grupo_id_seq OWNED BY controle_acesso.grupo.id;

-- drop table controle_acesso.permissao;
create sequence controle_acesso.permissao_id_seq;
create table controle_acesso.permissao (
  id bigint not null default nextval('controle_acesso.permissao_id_seq'),
  descricao varchar(150) not null,
  label varchar(50) not null,
  nivel integer,
  constraint id_permissao primary key (id),
  constraint permissao_uk unique(label)
);
alter sequence controle_acesso.permissao_id_seq OWNED BY controle_acesso.permissao.id;

-- drop table controle_acesso.grupo_permissao;
create sequence controle_acesso.grupo_permissao_id_seq;
create table controle_acesso.grupo_permissao (
  id bigint not null default nextval('controle_acesso.grupo_permissao_id_seq'),
  fk_grupo bigint not null,
  fk_permissao bigint not null,
  constraint id_grupo_permissao primary key (id),
  constraint grupo_permissao_uk unique(fk_grupo, fk_permissao)
);
alter sequence controle_acesso.grupo_permissao_id_seq OWNED BY controle_acesso.grupo_permissao.id;

-- drop table controle_acesso.usuario;
create sequence controle_acesso.usuario_id_seq;
create table controle_acesso.usuario (
  id bigint not null default nextval('controle_acesso.usuario_id_seq'),
  login character varying(100) not null,
  senha character varying(70) not null,
  hash_recupera_senha character varying(150),
  fk_pessoa bigint,
  fk_modulo bigint not null,
  constraint id primary key (id),
  constraint usuario_login_modulo_uk unique(login, fk_modulo)
);
alter sequence controle_acesso.usuario_id_seq OWNED BY controle_acesso.usuario.id;

-- drop table controle_acesso.grupo_usuario;
create sequence controle_acesso.grupo_usuario_id_seq;
create table controle_acesso.grupo_usuario (
  id bigint not null default nextval('controle_acesso.grupo_usuario_id_seq'),
  fk_grupo bigint not null,
  fk_usuario bigint not null,
  constraint grupo_usuario_pkey primary key (id),
  constraint grupo_usuario_uk unique(fk_grupo, fk_usuario)
);
alter sequence controle_acesso.grupo_usuario_id_seq OWNED BY controle_acesso.grupo_usuario.id;

alter table controle_acesso.grupo ADD constraint modulo_grupo_fk
foreign key (fk_modulo)
references controle_acesso.modulo (id)
on delete no action
on update no action
not deferrable;

alter table controle_acesso.grupo_permissao ADD constraint tb_grupo_tb_grupo_permissao_fk
foreign key (fk_grupo)
references controle_acesso.grupo (id)
on delete no action
on update no action
not deferrable;

alter table controle_acesso.grupo_permissao ADD constraint tb_permissao_tb_grupo_permissao_fk
foreign key (fk_permissao)
references controle_acesso.permissao (id)
on delete no action
on update no action
not deferrable;

alter table controle_acesso.grupo_usuario ADD constraint grupo_usuario_fk_grupo_fkey
foreign key (fk_grupo)
references controle_acesso.grupo (id) MATCH SIMPLE
on delete no action
on update no action
not deferrable;

alter table controle_acesso.grupo_usuario ADD constraint grupo_usuario_fk_usuario_fkey
foreign key (fk_usuario)
references controle_acesso.usuario (id) MATCH SIMPLE
on delete no action
on update no action
not deferrable;

alter table controle_acesso.usuario ADD constraint usuario_modulo_fk_modulo_fkey
foreign key (fk_modulo)
references controle_acesso.modulo (id) MATCH SIMPLE
on delete no action
on update no action
not deferrable;

alter table controle_acesso.usuario add constraint  usuario_pessoa_fk_pessoa_fkey
foreign key (fk_pessoa)
references util.pessoa (id) match SIMPLE
on update no action
on delete no action
not deferrable;
