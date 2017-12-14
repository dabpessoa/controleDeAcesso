-- MODULES
insert into controle_acesso.modulo (descricao, label) values ('Modulo do sistema Vai Começar', 'vaicomecar');

-- GROUPS
insert into controle_acesso.grupo (descricao, fk_modulo, label) values ('Grupo de controle MASTER', (select m.id from controle_acesso.modulo m where m.label = 'vaicomecar'), 'MASTER');

-- USERS
/* senha="admin" */
insert into controle_acesso.usuario (login, senha, fk_modulo) VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', (select m.id from controle_acesso.modulo m where m.label = 'vaicomecar'));

