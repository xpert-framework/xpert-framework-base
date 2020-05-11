package com.base.bo.tabelas;

import com.xpert.core.crud.AbstractBusinessObject;
import com.base.dao.tabelas.MunicipioDAO;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.base.modelo.tabelas.Municipio;
import com.xpert.core.validation.UniqueFields;

/**
 *
 * @author ayslanms
 */
@Stateless
public class MunicipioBO extends AbstractBusinessObject<Municipio> {

    @EJB
    private MunicipioDAO municipioDAO;

    @Override
    public MunicipioDAO getDAO() {
        return municipioDAO;
    }

    @Override
    public List<UniqueField> getUniqueFields() {
        return UniqueFields.from(Municipio.class);
    }

    @Override
    public void validate(Municipio municipio) throws BusinessException {
    }

    @Override
    public boolean isAudit() {
        return true;
    }

}
