package com.base.dao.controleacesso.impl;

import com.base.application.BaseDAOImpl;
import com.base.dao.controleacesso.UsuarioDAO;
import com.base.modelo.controleacesso.SituacaoUsuario;
import com.base.modelo.controleacesso.Usuario;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Ayslan
 */
@Stateless
public class UsuarioDAOImpl extends BaseDAOImpl<Usuario> implements UsuarioDAO {

    @Override
    public Class getEntityClass() {
        return Usuario.class;
    }

    @Override
    public List<Usuario> getUsuariosAtivos() {
        return list("situacaoUsuario", SituacaoUsuario.ATIVO, "nome");
    }
    
    
}
