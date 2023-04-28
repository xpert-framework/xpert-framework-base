
--auditing
CREATE INDEX auditing_eventdate_idx ON auditing (eventdate);
CREATE INDEX auditing_usuario_id_idx ON auditing (usuario_id);
CREATE INDEX auditing_entity_idx ON auditing (entity);
CREATE INDEX auditing_auditingtype_idx ON auditing (auditingtype);
CREATE INDEX auditing_eventdate_auditingtype_idx ON auditing (eventdate, auditingtype);
CREATE INDEX auditing_identifier_entity_idx ON auditing (identifier, entity);


--metadata
CREATE INDEX metadata_auditing_id_idx ON metadata (auditing_id);

--municipio
CREATE INDEX municipio_uf_id_idx ON municipio (uf_id);
CREATE INDEX municipio_codigoibge_idx ON municipio (codigoibge);

--uf
CREATE INDEX uf_sigla_idx ON uf (sigla);

--permissao
CREATE INDEX permissao_chave_idx ON permissao (chave);
CREATE INDEX permissao_permissaopai_id_idx ON permissao (permissaopai_id);

--usuario_favoritos
CREATE INDEX usuario_favoritos_usuario_id_idx ON usuario_favoritos (usuario_id);

--usuario_perfil
CREATE INDEX usuario_perfil_usuario_id_idx ON usuario_perfil (usuario_id);

--perfil_permissao
CREATE INDEX perfil_permissao_perfil_id_idx ON perfil_permissao (perfil_id);

--usuario
CREATE INDEX usuario_userlogin_idx ON usuario (userlogin);

--historicosituacaousuario
CREATE INDEX historicosituacaousuario_usuario_id_idx ON historicosituacaousuario (usuario_id);

--acessosistema
CREATE INDEX acessosistema_usuario_id_idx ON acessosistema (usuario_id);
CREATE INDEX acessosistema_datahora_idx ON acessosistema (datahora);

--errosistema
CREATE INDEX errosistema_data_idx ON errosistema (data);
CREATE INDEX errosistema_usuario_id_idx ON errosistema (usuario_id);
CREATE INDEX errosistema_funcionalidade_idx ON errosistema (funcionalidade);

--solicitacaorecuperacaosenha
CREATE INDEX solicitacaorecuperacaosenha_token_idx ON solicitacaorecuperacaosenha (token);
CREATE INDEX solicitacaorecuperacaosenha_usuario_id_idx ON solicitacaorecuperacaosenha (usuario_id);