package com.base.bo.dashboard;

import java.util.Date;
import org.apache.commons.lang.time.DateUtils;
import org.joda.time.DateTime;

/**
 *
 * @author ayslanms
 */
public class Dashboards {

    /**
     * Retorna a data atual sem precisao de hora minuto segundo
     *
     * @return
     */
    public static Date getDataAtual() {
        return DateUtils.truncate(new Date(), java.util.Calendar.DAY_OF_MONTH);
    }

    /**
     * Retorna a data atual menos um mes sem precisao de hora minuto segundo
     *
     * @return
     */
    public static Date getDataAtualMenosUmMes() {
        return DateUtils.truncate(new DateTime().plusMonths(-1).toDate(), java.util.Calendar.DAY_OF_MONTH);
    }

}
