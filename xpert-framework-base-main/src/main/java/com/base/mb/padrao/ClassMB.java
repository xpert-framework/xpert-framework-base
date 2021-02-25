package com.base.mb.padrao;

import com.base.modelo.tabelas.Municipio;
import com.base.modelo.tabelas.Uf;
import com.base.modelo.controleacesso.Permissao;
import com.base.modelo.controleacesso.Usuario;
import com.base.modelo.configuracao.ErroSistema;
import com.base.modelo.controleacesso.SituacaoUsuario;
import com.base.modelo.controleacesso.AcessoSistema;
import com.base.modelo.email.ConfiguracaoEmail;
import com.base.modelo.controleacesso.Perfil;
import com.base.modelo.controleacesso.HistoricoSituacaoUsuario;
import com.base.modelo.email.ModeloEmail;
import com.base.modelo.controleacesso.SolicitacaoRecuperacaoSenha;
import com.base.modelo.email.TipoAssuntoEmail;
import com.base.modelo.controleacesso.TipoRecuperacaoSenha;
import com.xpert.audit.model.AuditingType;
import com.xpert.audit.model.QueryAuditingType;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class ClassMB {

    public Class getAcessoSistema() {
        return AcessoSistema.class;
    }

    public Class getErroSistema() {
        return ErroSistema.class;
    }

    public Class getHistoricoSituacaoUsuario() {
        return HistoricoSituacaoUsuario.class;
    }

    public Class getPerfil() {
        return Perfil.class;
    }

    public Class getPermissao() {
        return Permissao.class;
    }

    public Class getSituacaoUsuario() {
        return SituacaoUsuario.class;
    }

    public Class getUsuario() {
        return Usuario.class;
    }

    public Class getTipoAssuntoEmail() {
        return TipoAssuntoEmail.class;
    }

    public Class getModeloEmail() {
        return ModeloEmail.class;
    }

    public Class getSolicitacaoRecuperacaoSenha() {
        return SolicitacaoRecuperacaoSenha.class;
    }

    public Class getTipoRecuperacaoSenha() {
        return TipoRecuperacaoSenha.class;
    }

    public Class getConfiguracaoEmail() {
        return ConfiguracaoEmail.class;
    }

    public Class getMunicipio() {
        return Municipio.class;
    }
    public Class getUf() {
        return Uf.class;
    }
    public Class getAuditingType() {
        return AuditingType.class;
    }
    public Class getQueryAuditingType() {
        return QueryAuditingType.class;
    }

}