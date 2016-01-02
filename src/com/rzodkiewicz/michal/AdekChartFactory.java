package com.rzodkiewicz.michal;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

/**
 * Created by michal on 18/10/2015.
 */
public class AdekChartFactory {

    XYSeries xySeries;
    XYSeriesCollection xySeriesCollection;
    JFreeChart chart;
    ChartPanel chartPanel;

    public AdekChartFactory(double[] dataset, String chartName, String yAxis){
        xySeries = new XYSeries(chartName);

        for(int i = 0; i < dataset.length; i++){
            xySeries.add(i * 10.0, dataset[i]);

        }
        xySeriesCollection = new XYSeriesCollection(xySeries);
        chart = ChartFactory.createXYLineChart(
                chartName,
                "t",
                yAxis,
                xySeriesCollection,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 270));

    }
}
