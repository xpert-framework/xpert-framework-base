﻿

/**
Inserts em Usuarios
**/

INSERT INTO usuario
SELECT nextval('usuario_id_seq'), trunc(random()*100000000)||'', datacadastro, dataultimoacesso, 'USUARIO-'||trunc(random()*100000000)||'@email.com' as email, emailcadastroenviado, 
       matricula, 'USUARIO-'||trunc(random()*100000000)||'', rg, senhacadastrada, situacaousuario, superusuario, 
       'USUARIO-'||trunc(random()*100000000)||'', userpassword, tema
  FROM public.usuario

delete from usuario where id > 600;
select count(1) from usuario;
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

/**
Inserts em Metadata
**/

DO
$do$
BEGIN 
FOR loop_counter IN 1..random()*1000  LOOP
    insert into metadata

   SELECT nextval('metadata_id_seq') as id, entity, field, newidentifier, newvalue, oldidentifier, oldvalue, 
        (SELECT id FROM auditing OFFSET floor(random()*300) LIMIT 1) as auditing_id
     from metadata   limit random()*1000;
END LOOP; 
END
$do$;


/**
Inserts em QueryAuditing
**/

DO
$do$
BEGIN 
FOR loop_counter IN 1..random()*10  LOOP
    insert into queryauditing
  SELECT
          nextval('queryauditing_id_seq') as id, auditingtype, enddate, entity, firstresult, hasqueryparameter, 
          identifier, ip, maxresults, paginatedquery, parameterssize, random()*100000 as rowstotal, 
          sqlparameters, sqlquery, NOW() - (random()*10 * (interval '30 days')) as startdate, random()*1000000 as timemilliseconds, 
          (select id from usuario order by random() limit 1) as usuario_id
         
         from queryauditing order by random() limit random()*100;
END LOOP; 
END
$do$;

select random()*100000;


/*dobrar tamaho da auditing */
 insert into auditing
  SELECT nextval('auditing_id_seq') as id, 
         auditingtype, entity, eventdate, 
         identifier, 
         ip, 
         usuario_id
         from auditing;

select count(1) from metadata;
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

select count(1) from auditing
select count(1), min(dataHora) from acessosistema

SELECT 
    TO_CHAR(now(), 'YYYY-MM') 


-- media acessos
select avg(media) from (
	    select horario, avg(total) as media, sum(total), count(1) from (
			select cast(dataHora as date), extract(hour from acessosist0_.dataHora) as horario, count(*) total
			from AcessoSistema acessosist0_ 
			where acessosist0_.dataHora>='2020-04-25' and acessosist0_.dataHora<'2020-05-26' 
			group by cast(dataHora as date) , extract(hour from acessosist0_.dataHora) 
			order by cast(dataHora as date) , extract(hour from acessosist0_.dataHora)
	) tab group by horario 
) tab1
