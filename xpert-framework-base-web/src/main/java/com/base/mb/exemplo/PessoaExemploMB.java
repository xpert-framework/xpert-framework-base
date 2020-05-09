package com.base.mb.exemplo;


import java.io.Serializable;
import com.xpert.core.crud.AbstractBaseBean;
import javax.ejb.EJB;
import com.base.bo.exemplo.PessoaExemploBO;
import com.base.modelo.cadastros.PessoaExemplo;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author Ayslan
 */
@Named
@ViewScoped
public class PessoaExemploMB extends AbstractBaseBean<PessoaExemplo> implements Serializable {

    @EJB
    private PessoaExemploBO pessoaExemploBO;

    @Override
    public PessoaExemploBO getBO() {
        return pessoaExemploBO;
    }

    @Override
    public void save() {
        super.save();
    }
    
    

    @Override
    public String getDataModelOrder() {
        return "id";
    }
}
