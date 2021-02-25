package com.base.bo.audit;

import com.base.dao.audit.QueryAuditingDAO;
import com.base.modelo.audit.QueryAuditing;
import com.base.vo.audit.ConsultaQueryAuditoria;
import com.xpert.core.exception.BusinessException;
import com.xpert.core.validation.DateValidation;
import com.xpert.core.validation.Validation;
import com.xpert.faces.primefaces.LazyDataModelImpl;
import com.xpert.persistence.query.JoinBuilder;
import com.xpert.persistence.query.Restrictions;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.joda.time.DateTime;

/**
 *
 * @author ayslanms
 */
@Stateless
public class QueryAuditingBO {

    @EJB
    private QueryAuditingDAO queryAuditingDAO;

    public LazyDataModelImpl<QueryAuditing> consultar(ConsultaQueryAuditoria consultaAuditoria) throws BusinessException {

        if (consultaAuditoria.getDataInicial() == null || consultaAuditoria.getDataFinal() == null) {
            throw new BusinessException("required.dataInicialDataFinal");
        }

        if (!DateValidation.validateDateRange(consultaAuditoria.getDataInicial(), consultaAuditoria.getDataFinal())) {
            throw new BusinessException("business.intervaloDataInvalido");
        }
        if (consultaAuditoria.getUsuarios() == null || consultaAuditoria.getUsuarios().isEmpty()) {
            throw new BusinessException("required.usuario");
        }

        Restrictions restrictions = getRestrictions(consultaAuditoria);
        LazyDataModelImpl<QueryAuditing> dataModel = new LazyDataModelImpl<>("startDate", restrictions, queryAuditingDAO);
        //joion fetch com usuario
        dataModel.setJoinBuilder(new JoinBuilder("q").leftJoinFetch("q.usuario", "u"));

        return dataModel;

    }

    /**
     * Retorna os restrictions a aprtir de uma instancia de ConsultaAuditoria
     *
     * @param consultaAuditoria
     * @return
     */
    public Restrictions getRestrictions(ConsultaQueryAuditoria consultaAuditoria) {

        Restrictions restrictions = new Restrictions();
        //Data
        if (consultaAuditoria.getDataInicial() != null) {
            restrictions.greaterEqualsThan("q.startDate", consultaAuditoria.getDataInicial());
        }
        if (consultaAuditoria.getDataFinal() != null) {
            //menor que o dia +1 para desprezar a hora/minuto/segundo
            restrictions.lessThan("q.startDate", new DateTime(consultaAuditoria.getDataFinal()).plusDays(1).toDate());
        }
        //Usuario
        if (consultaAuditoria.getUsuarios() != null && !consultaAuditoria.getUsuarios().isEmpty()) {
            restrictions.in("u", consultaAuditoria.getUsuarios());
        }
        //Tipo
        if (consultaAuditoria.getTipos() != null && !consultaAuditoria.getTipos().isEmpty()) {
            restrictions.in("q.auditingType", consultaAuditoria.getTipos());
        }
        //Tabela/Entidade
        if (consultaAuditoria.getTabelas() != null && !consultaAuditoria.getTabelas().isEmpty()) {
            restrictions.in("q.entity", consultaAuditoria.getNomesTabelas());
        }
        //IP
        if (!Validation.isBlank(consultaAuditoria.getIp())) {
            restrictions.like("q.ip", consultaAuditoria.getIp());
        }
        //SQL Query
        if (!Validation.isBlank(consultaAuditoria.getSqlQuery())) {
            restrictions.like("q.sqlQuery", consultaAuditoria.getSqlQuery());
        }
        //SQL Parameters
        if (!Validation.isBlank(consultaAuditoria.getSqlParameters())) {
            restrictions.like("q.sqlParameters", consultaAuditoria.getSqlParameters());
        }
        //Tempo Consulta
        if (consultaAuditoria.getTempoConsultaInicial() != null) {
            restrictions.greaterEqualsThan("q.timeMilliseconds", consultaAuditoria.getTempoConsultaInicial());
        }
        if (consultaAuditoria.getTempoConsultaFinal() != null) {
            restrictions.lessEqualsThan("q.timeMilliseconds", consultaAuditoria.getTempoConsultaFinal());
        }
        //Total de Linhas
        if (consultaAuditoria.getTotalLinhasInicial()!= null) {
            restrictions.greaterEqualsThan("q.rowsTotal", consultaAuditoria.getTotalLinhasInicial());
        }
        if (consultaAuditoria.getTotalLinhasFinal()!= null) {
            restrictions.lessEqualsThan("q.rowsTotal", consultaAuditoria.getTotalLinhasFinal());
        }
        //Consulta Paginada
        if (consultaAuditoria.getConsultaPaginada() != null) {
            restrictions.in("q.paginatedQuery", consultaAuditoria.getConsultaPaginada());
        }
        //Consulta Com Query
        if (consultaAuditoria.getConsultaPaginada() != null) {
            restrictions.in("q.hasQueryParameter", consultaAuditoria.getConsultaComParametros());
        }
        //Identificador
        if (consultaAuditoria.getIdentificador() != null) {
            restrictions.add("q.identifier", consultaAuditoria.getIdentificador());
        }
        return restrictions;
    }

}
