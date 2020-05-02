package com.base.mb;

import com.base.GeracaoDadosSistema;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 * Como esse managed bean tem o escopo de "Application" e a opcao "eager = true"
 * o metodo "@PostConstruct" sera chamado ao iniciar a aplicacao
 *
 * @author ayslan
 */
@Named
@Startup
@ApplicationScoped
public class ApplicationMB {

    @EJB
    private GeracaoDadosSistema geracaoDadosSistema;

    @PostConstruct
    public void init() {
        //gerar permissoes ao iniciar aplicacao
        geracaoDadosSistema.generate();
    }
}
