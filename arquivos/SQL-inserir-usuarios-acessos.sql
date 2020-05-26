

/**
Inserts em Usuarios
**/

INSERT INTO usuario
SELECT nextval('usuario_id_seq'), trunc(random()*100000000)||'', datacadastro, dataultimoacesso, email, emailcadastroenviado, 
       matricula, 'USUARIO-'||trunc(random()*100000000)||'', rg, senhacadastrada, situacaousuario, superusuario, 
       'USUARIO-'||trunc(random()*100000000)||'', userpassword, tema
  FROM public.usuario


/**
Inserts em AcessoSistema
**/


DO
$do$
BEGIN 
FOR loop_counter IN 1..random()*10000  LOOP
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

/**
Inserts em ErroSistema
**/

DO
$do$
BEGIN 
FOR loop_counter IN 1..random()*1000  LOOP
    insert into errosistema
select 
  nextval('errosistema_id_seq'),
  data,
  funcionalidade,
  informacaonavegador ,
  pilhaerro,
  url,
  (select id from usuario order by random() limit 1) from errosistema order by random() limit random()*100;
END LOOP; 
END
$do$;

/**
Inserts em Auditing
**/

DO
$do$
BEGIN 
FOR loop_counter IN 1..random()*1000  LOOP
    insert into auditing
  SELECT nextval('auditing_id_seq') as id, 
         auditingtype, entity, NOW() - (random()*1000 * (interval '30 days')) as eventdate, 
         identifier, 
         ip, 
         (select id from usuario order by random() limit 1) as usuario_id 
         from auditing order by random() limit random()*100;
END LOOP; 
END
$do$;

/*dobrar tamaho da auditing */
 insert into auditing
  SELECT nextval('auditing_id_seq') as id, 
         auditingtype, entity, eventdate, 
         identifier, 
         ip, 
         usuario_id
         from auditing;


select now()- make_interval(days := (random()*100)::integer);
delete from auditing where usuario_id >10;
delete from auditing where eventdate >= '2020-05-23';
select NOW() - (random() * (interval '30 days'));

  SELECT id, 
         auditingtype, entity, 
         (NOW() - (random()*1000 * (interval '30 days'))) as eventdate, 
         identifier, 
         ip, 
         (select id from usuario order by random() limit 1) as usuario_id 
         from auditing order by random() limit random()*100;


select random()*100;

CREATE INDEX auditing_eventdate_idx ON auditing (eventdate);


select count(1) from auditing
select count(1), min(dataHora) from acessosistema

SELECT 
    TO_CHAR(now(), 'YYYY-MM') 


-- media acessos
    select horario, avg(total), sum(total), count(1) from (
select cast(dataHora as date) as data, extract(hour from acessosist0_.dataHora) as horario, count(*) as total 
from AcessoSistema acessosist0_ 
--where acessosist0_.dataHora>=? and acessosist0_.dataHora<? 
group by cast(dataHora as date) , extract(hour from acessosist0_.dataHora) 
order by cast(dataHora as date) , extract(hour from acessosist0_.dataHora)
) tab group by horario 
