package com.base.bo.email;

import com.xpert.core.crud.AbstractBusinessObject;
import com.base.dao.email.ConfiguracaoEmailDAO;
import com.xpert.core.validation.UniqueField;
import com.xpert.core.exception.BusinessException;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import com.base.modelo.email.ConfiguracaoEmail;
import com.xpert.core.validation.UniqueFields;

/**
 *
 * @author ayslan
 */
@Stateless
public class ConfiguracaoEmailBO extends AbstractBusinessObject<ConfiguracaoEmail> {

    @EJB
    private ConfiguracaoEmailDAO configuracaoEmailDAO;
    
    @Override
    public ConfiguracaoEmailDAO getDAO() {
        return configuracaoEmailDAO;
    }

    @Override
    public List<UniqueField> getUniqueFields() {
        return new UniqueFields().add("email");
    }

    @Override
    public void validate(ConfiguracaoEmail configuracaoEmail) throws BusinessException {
    }

    @Override
    public boolean isAudit() {
        return true;
    }

}
