package com.base.mb.tabelas;

import com.base.bo.tabelas.MunicipioBO;
import com.xpert.core.exception.BusinessException;
import com.xpert.faces.utils.FacesMessageUtils;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author ayslanms
 */
@Named
@ViewScoped
public class MunicipioImportacaoMB implements Serializable {

     private static final Logger LOG = Logger.getLogger(MunicipioBO.class.getName());
    
    @EJB
    private MunicipioBO municipioBO;

    public void importar(FileUploadEvent event) {

        try {
            municipioBO.importar(event.getFile().getInputstream());
            FacesMessageUtils.sucess();
        } catch (BusinessException ex) {
            FacesMessageUtils.error(ex);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex, null);
             FacesMessageUtils.error("Erro ao carregar o arquivo (IOException) {0}", ex.getMessage());
        }

    }

}
