
-- Inserir Usuarios aleatorios
INSERT INTO usuario
SELECT nextval('usuario_id_seq'), trunc(random()*100000000)||'', datacadastro, dataultimoacesso, email, emailcadastroenviado, 
       matricula, 'USUARIO-'||trunc(random()*100000000)||'', rg, senhacadastrada, situacaousuario, superusuario, 
       'USUARIO-'||trunc(random()*100000000)||'', userpassword, tema
  FROM public.usuario

-- Inserir Acessos do Sistema
insert into acessosistema
select 
  nextval('acessosistema_id_seq'),
  datahora,
  ip,
  useragent ,
  (select id from usuario order by random() limit 1) from acessosistema

delete from acessosistema where id > 1000;
select random()*100;

DO
$do$
BEGIN 
FOR loop_counter IN 1..random()*100  LOOP
    insert into acessosistema
select 
  nextval('acessosistema_id_seq'),
  datahora,
  ip,
  useragent ,
  (select id from usuario order by random() limit 1) from acessosistema order by random() limit random()*100;
END LOOP; 
END
$do$;
