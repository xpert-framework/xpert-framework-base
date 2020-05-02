package com.base.mb;

import com.base.GeracaoDadosSistema;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import javax.servlet.ServletContext;

/**
 * Como esse managed bean tem o escopo de "Application" e a opcao "eager = true"
 * o metodo "@PostConstruct" sera chamado ao iniciar a aplicacao
 *
 * @author ayslan
 */
@Named
@ApplicationScoped
public class ApplicationMB {

    @EJB
    private GeracaoDadosSistema geracaoDadosSistema;

    public void init(@Observes @Initialized(ApplicationScoped.class) ServletContext payload) {
        //gerar permissoes ao iniciar aplicacao
        geracaoDadosSistema.generate();
    }
}
