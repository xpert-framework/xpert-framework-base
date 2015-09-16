package com.base.mb.configuracao;

import com.base.bo.configuracao.ErroSistemaBO;
import com.base.modelo.configuracao.ErroSistema;
import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import com.xpert.core.crud.AbstractBusinessObject;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ayslan
 */
@ManagedBean
@ViewScoped
public class ErroSistemaMB extends AbstractBaseBean<ErroSistema> implements Serializable {

    @EJB
    private ErroSistemaBO erroSistemaBO;

    @Override
    public AbstractBusinessObject getBO() {
        return erroSistemaBO;
    }

    @Override
    public String getDataModelOrder() {
        return "id DESC";
    }
}
