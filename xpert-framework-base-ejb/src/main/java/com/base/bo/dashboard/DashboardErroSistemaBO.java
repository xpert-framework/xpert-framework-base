package com.base.bo.dashboard;

import com.base.dao.DAO;
import com.base.modelo.configuracao.ErroSistema;
import com.base.vo.dashboard.DashboardErroSistema;
import com.xpert.core.exception.BusinessException;
import com.xpert.core.validation.DateValidation;
import static com.xpert.persistence.query.Sql.max;
import static com.xpert.persistence.query.Sql.min;
import com.xpert.persistence.query.Restrictions;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.joda.time.DateTime;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.line.LineChartModel;
import static com.xpert.persistence.query.Sql.*;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.line.LineChartDataSet;

/**
 *
 * @author ayslanms
 */
@Stateless
public class DashboardErroSistemaBO {

    @EJB
    private DAO dao;

    /**
     * Retorna a quantidade total de erros
     *
     * @return
     */
    public Long getQuantidadeTotalErros() {
        return dao.getQueryBuilder()
                .from(ErroSistema.class)
                .count();
    }

    /**
     * Retorna a quantidade de erros conforme os filtros
     *
     * @param dashboardErroSistema
     * @return
     */
    public Long getQuantidadeErros(DashboardErroSistema dashboardErroSistema) {
        return dao.getQueryBuilder()
                .from(ErroSistema.class)
                .add(getRestrictions(dashboardErroSistema))
                .count();
    }

    /**
     * Retorna a quantidade de erros conforme os filtros
     *
     * @return
     */
    public Date getDataUltimoErro() {
        return (Date) dao.getQueryBuilder()
                .from(ErroSistema.class)
                .max("data");
    }

    /**
     * Retorna a lista de quantidade de erros por dia
     *
     * @param dashboardErroSistema
     * @return
     */
    public List<Object[]> getErrosDia(DashboardErroSistema dashboardErroSistema) {

        //calculo do tipo de intervalo
        String field = Charts.getGroupByTempo("data", dashboardErroSistema.getDataInicial(), dashboardErroSistema.getDataFinal());

        return dao.getQueryBuilder()
                .by(field)
                .aggregate(count("*"))
                .from(ErroSistema.class)
                .add(getRestrictions(dashboardErroSistema))
                .getResultList();
    }

    /**
     * Retorna a lista de quantidade de erros por faixa de horario (01h-02h,
     * 02h-03h, etc..)
     *
     * @param dashboardErroSistema
     * @return
     */
    public List<Object[]> getErrosFaixaHorario(DashboardErroSistema dashboardErroSistema) {
        /**
         * Aqui tem que ordenar pela hora, pois ordenar por String vai aparecer
         * 1h-2h, 10h-11h Passo 1 - Trazer a hora do banco de dados
         */
        List<Object[]> result = dao.getQueryBuilder()
                .by("HOUR(data)")
                .aggregate(count("*"))
                .from(ErroSistema.class)
                .add(getRestrictions(dashboardErroSistema))
                .getResultList();

        /**
         * Passo 2 - Formatar resultado para fica no padrao 01h-02h
         */
        for (Object[] linha : result) {
            Number hora = (Number) linha[0];
            linha[0] = Charts.getFaixaHorario(hora);
        }

        return result;
    }

    /**
     * Retorna a lista de quantidade de erros por usuario
     *
     * @param dashboardErroSistema
     * @return
     */
    public List<Object[]> getErrosUsuario(DashboardErroSistema dashboardErroSistema) {
        /**
         * Fazer left join pois o usuario pode ser nulo
         */
        return dao.getQueryBuilder()
                .by("COALESCE(u.userLogin, 'NÃO INFORMADO')")
                .aggregate(count("e"), min("e.data"), max("e.data"))
                .from(ErroSistema.class, "e")
                .leftJoin("e.usuario", "u")
                .orderBy("2")
                .add(getRestrictions(dashboardErroSistema))
                .getResultList();
    }

    /**
     * Retorna a lista de quantidade de erros por funcionalidade
     *
     * @param dashboardErroSistema
     * @return
     */
    public List<Object[]> getErrosFuncionalidade(DashboardErroSistema dashboardErroSistema) {
        return dao.getQueryBuilder()
                .by("COALESCE(funcionalidade, 'NÃO INFORMADO')")
                .aggregate(count("*"), min("data"), max("data"))
                .from(ErroSistema.class)
                .orderBy("2")
                .add(getRestrictions(dashboardErroSistema))
                .getResultList();
    }

    /**
     * Retorna o grafico de erros por dia
     *
     * @param dashboardErroSistema
     * @return
     */
    public LineChartModel getGraficoErrosDia(DashboardErroSistema dashboardErroSistema) {
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for (Object[] linha : dashboardErroSistema.getErrosDia()) {
            if (linha[0] instanceof Date) {
                labels.add(new SimpleDateFormat("dd/MM/yyyy").format((Date) linha[0]));
            } else {
                labels.add(linha[0].toString());
            }
            values.add(((Number) linha[1]).longValue());
        }

        return Charts.getLineChartModel("Quantidade de Erros", Charts.COLOR_SERIE_4, values, labels);
    }

    /**
     * Retorna o grafico de erros por faixa de horario
     *
     * @param dashboardErroSistema
     * @return
     */
    public BarChartModel getGraficoErrosFaixaHorario(DashboardErroSistema dashboardErroSistema) {
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        for (Object[] linha : dashboardErroSistema.getErrosFaixaHorario()) {
            labels.add(linha[0].toString());
            values.add(((Number) linha[1]).longValue());
        }

        return Charts.getBarChartModel(null, values, labels);
    }

    /**
     * Retorna o grafico de erros de usuarios por perfil
     *
     * @param dashboardErroSistema
     * @param limite
     * @return
     */
    public BarChartModel getGraficoErrosUsuario(DashboardErroSistema dashboardErroSistema, int limite) {
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        //maior para o menor
        Collections.reverse(dashboardErroSistema.getErrosUsuario());

        int count = 1;
        for (Object[] linha : dashboardErroSistema.getErrosUsuario()) {
            if (count <= limite) {
                labels.add(linha[0].toString());
                values.add(((Number) linha[1]).longValue());
            }
            count++;
        }

        Collections.reverse(labels);
        Collections.reverse(values);

        String titulo = limite + " usuário(s) com mais erros no sistema";

        return Charts.getBarChartModel(titulo, values, labels);
    }

    /**
     * Retorna o grafico de erros de usuarios por perfil
     *
     * @param dashboardErroSistema
     * @param limite
     * @return
     */
    public BarChartModel getGraficoErrosFuncionalidade(DashboardErroSistema dashboardErroSistema, int limite) {
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();

        //maior para o menor
        Collections.reverse(dashboardErroSistema.getErrosFuncionalidade());

        int count = 1;
        for (Object[] linha : dashboardErroSistema.getErrosFuncionalidade()) {
            if (count <= limite) {
                labels.add(linha[0].toString());
                values.add(((Number) linha[1]).longValue());
            }
            count++;
        }

        Collections.reverse(labels);
        Collections.reverse(values);

        String titulo = limite + " funciolidade(s) com mais erros no sistema";

        return Charts.getBarChartModel(titulo, Charts.COLOR_SERIE_4, values, labels);
    }

    /**
     * Cria o objeto DashboardErroSistema com dataInicial (data autal -1 mês) e
     * dataFinal (data atual) ja setados
     *
     * @return
     * @throws BusinessException
     */
    public DashboardErroSistema getDashboardErroSistema() throws BusinessException {

        DashboardErroSistema dashboardErroSistema = new DashboardErroSistema();
        //por padrao ele vem com o ultimo mes carregado
        dashboardErroSistema.setDataInicial(new DateTime().plusMonths(-1).toDate());
        dashboardErroSistema.setDataFinal(new Date());

        //montar indicadores
        carregarDashboardErroSistema(dashboardErroSistema);

        return dashboardErroSistema;
    }

    /**
     * Carrega as informações do dashboard
     *
     * @param dashboardErroSistema
     * @throws BusinessException
     */
    public void carregarDashboardErroSistema(DashboardErroSistema dashboardErroSistema) throws BusinessException {

        if (dashboardErroSistema.getDataInicial() == null || dashboardErroSistema.getDataFinal() == null) {
            throw new BusinessException("Informe a data inicial e a data final");
        }

        if (!DateValidation.validateDateRange(dashboardErroSistema.getDataInicial(), dashboardErroSistema.getDataFinal())) {
            throw new BusinessException("Intervalo de datas inválido. Data Inicial não pode ser maior que a final");
        }

        /**
         * Carregar indicadores
         */
        dashboardErroSistema.setQuantidadaTotalErros(getQuantidadeTotalErros());
        dashboardErroSistema.setQuantidadeErros(getQuantidadeErros(dashboardErroSistema));
        dashboardErroSistema.setDataUltimoErro(getDataUltimoErro());

        /**
         * Carregar tabelas que serao usadas nos graficos
         */
        dashboardErroSistema.setErrosDia(getErrosDia(dashboardErroSistema));
        dashboardErroSistema.setErrosFaixaHorario(getErrosFaixaHorario(dashboardErroSistema));
        dashboardErroSistema.setErrosUsuario(getErrosUsuario(dashboardErroSistema));
        dashboardErroSistema.setErrosFuncionalidade(getErrosFuncionalidade(dashboardErroSistema));

        /**
         * Carregar graficos
         */
        dashboardErroSistema.setGraficoErrosDia(getGraficoErrosDia(dashboardErroSistema));
        dashboardErroSistema.setGraficoErrosFaixaHorario(getGraficoErrosFaixaHorario(dashboardErroSistema));
        dashboardErroSistema.setGraficoErrosUsuario(getGraficoErrosUsuario(dashboardErroSistema, 20));
        dashboardErroSistema.setGraficoErrosFuncionalidade(getGraficoErrosFuncionalidade(dashboardErroSistema, 20));
    }

    public Restrictions getRestrictions(DashboardErroSistema dashboardErroSistema) {

        Restrictions restrictions = new Restrictions();
        if (dashboardErroSistema.getDataInicial() != null) {
            restrictions.greaterEqualsThan("data", dashboardErroSistema.getDataInicial());
        }
        if (dashboardErroSistema.getDataFinal() != null) {
            //menor que o dia +1 para desprezar a hora/minuto/segundo
            restrictions.lessThan("data", new DateTime(dashboardErroSistema.getDataFinal()).plusDays(1).toDate());
        }
        if (dashboardErroSistema.getUsuarios() != null && !dashboardErroSistema.getUsuarios().isEmpty()) {
            restrictions.in("usuario", dashboardErroSistema.getUsuarios());
        }
        return restrictions;
    }

}
