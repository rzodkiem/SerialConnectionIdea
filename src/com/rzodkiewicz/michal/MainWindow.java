package com.rzodkiewicz.michal;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
//import org.jfree.ui.ApplicationFrame;
//import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by michal on 16/10/2015.
 */
public class MainWindow extends JFrame {
    private JPanel panel1;
    private JButton rysujVButton;
    private JButton czyscButton;
    private JButton odbierzDane;
    private JButton rysujIButton;
    private JTextField portField;
    private JTextField timerangeField;
    private SerialReader serialReader;


    public MainWindow(){
        super();
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(panel1);


        rysujVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InputSaver inputSaver = new InputSaver(true, true);

                // some action to draw voltage
                inputSaver.saveVoltage(serialReader.holder);
                double[] dataset = new double[1000];
                for(int i = 0; i < inputSaver.voltageTab.size(); i++){
                    dataset[i] = inputSaver.voltageTab.get(i);
                }
                AdekChartFactory newChart = new AdekChartFactory(dataset, "TEST", "t");
                setContentPane(newChart.chartPanel);
                System.out.println("Rysuje...");
            }
        });

        rysujIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final XYSeries series  = new XYSeries("Demo");
                series.add(1.0, 1.0);
                series.add(1.0, 2.0);
                series.add(1.0, 3.0);
                final XYSeriesCollection data = new XYSeriesCollection(series);
                final JFreeChart chart = ChartFactory.createXYLineChart(
                        "Demo",
                        "X",
                        "Y",
                        data,
                        PlotOrientation.VERTICAL,
                        true,
                        true,
                        false
                );
                final ChartPanel chartPanel = new ChartPanel(chart);
                chartPanel.setPreferredSize(new Dimension(500, 270));
                setContentPane(chartPanel);
                System.out.println("Rysuje...");
            }
        });

        czyscButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //some cleaning panel action
            }
        });

        odbierzDane.addActionListener(new ActionListener() {
            //TODO: Nie mam dostepu do serialReader.holder z klasy MainWindow
            //TODO: Transmisja ma sie zaczynac
            @Override
            public void actionPerformed(ActionEvent e) {
                String portName = portField.getText();
                int timeRange = Integer.parseInt(timerangeField.getText());
                try{
                    Measurement measurement = new Measurement();
                    serialReader = measurement.startMeasurement(portName); //TODO: taki hardcode to tymczasowo
                }catch(Exception exc){
                    exc.printStackTrace();
                }
            }
        });
        setVisible(true);

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public static void main(String[] args){
        new MainWindow();
    }
}


