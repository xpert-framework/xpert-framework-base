package com.base.bo.dashboard;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

/**
 *
 * @author ayslanms
 */
public class Charts {
    
    public final static String COLOR_SERIE_1 = "rgba(75, 192, 192)";
    public final static String COLOR_SERIE_2 = "rgba(255, 159, 64)";
    public final static String COLOR_SERIE_3 = "rgba(255, 205, 86)";
    public final static String COLOR_SERIE_4 = "rgba(255, 99, 132)";
    public final static String COLOR_SERIE_5 = "rgba(54, 162, 235)";
    public final static String COLOR_SERIE_6 = "rgba(153, 102, 255)";
    public final static String COLOR_SERIE_7 = "rgba(201, 203, 207)";
    
    public final static List<String> COLORS = new ArrayList<>();
    
    static {
        COLORS.add(COLOR_SERIE_1);
        COLORS.add(COLOR_SERIE_2);
        COLORS.add(COLOR_SERIE_3);
        COLORS.add(COLOR_SERIE_4);
        COLORS.add(COLOR_SERIE_5);
        COLORS.add(COLOR_SERIE_6);
        COLORS.add(COLOR_SERIE_7);
    }

    /**
     * Retorna um grafico de linhas generico de apenas uma serie
     *
     * @param titulo
     * @param values
     * @param labels
     * @return
     */
    public static LineChartModel getLineChartModel(String titulo, List<Number> values, List<String> labels) {
        LineChartModel model = new LineChartModel();
        ChartData data = new ChartData();
        
        LineChartDataSet dataSet = new LineChartDataSet();
        dataSet.setData(values);
        dataSet.setFill(false);
        dataSet.setLabel(titulo);
        dataSet.setBorderColor(Charts.COLOR_SERIE_1);
        dataSet.setBackgroundColor(Charts.COLOR_SERIE_1);
        dataSet.setLineTension(0.1);
        data.addChartDataSet(dataSet);
        data.setLabels(labels);

        //Options
        LineChartOptions options = new LineChartOptions();
        Title title = new Title();
        title.setDisplay(false);
        options.setTitle(title);
        
        model.setOptions(options);
        model.setData(data);
//        model.setExtender("chartExtender");

        return model;
    }

    /**
     * Retorna um grafico de pizza generico de apenas uma serie
     *
     * @param values
     * @param labels
     * @return
     */
    public static PieChartModel getPieChartModel(List<Number> values, List<String> labels) {
        PieChartModel model = new PieChartModel();
        ChartData data = new ChartData();
        PieChartDataSet dataSet = new PieChartDataSet();
        dataSet.setBackgroundColor(Charts.COLORS);
        
        dataSet.setData(values);
        data.addChartDataSet(dataSet);
        data.setLabels(labels);
        
        model.setData(data);
        return model;
    }

    /**
     * Retorna um grafico de barras generico de apenas uma serie
     *
     * @param titulo
     * @param values
     * @param labels
     * @return
     */
    public static BarChartModel getBarChartModel(String titulo, List<Number> values, List<String> labels) {
        BarChartModel model = new BarChartModel();
        ChartData data = new ChartData();
        BarChartDataSet dataSet = new BarChartDataSet();
        dataSet.setBackgroundColor(Charts.COLOR_SERIE_1);
        dataSet.setBorderColor(Charts.COLOR_SERIE_1);
        
        dataSet.setData(values);
        data.addChartDataSet(dataSet);
        data.setLabels(labels);
        
        model.setData(data);

        //Options
        BarChartOptions options = new BarChartOptions();
        CartesianScales cScales = new CartesianScales();
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
        
        Title title = new Title();
        if (titulo != null) {
            title.setText(titulo);
            title.setDisplay(true);
        } else {
            title.setDisplay(false);
        }
        options.setTitle(title);
        
        Legend legend = new Legend();
        legend.setDisplay(false);
        options.setLegend(legend);
        
        model.setOptions(options);
        
        return model;
    }
    
}
