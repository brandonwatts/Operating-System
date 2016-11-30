package os.ui;


import java.awt.BorderLayout;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import os.OperatingSystem;


public class ProcGraph extends JFrame {

    private static TimeSeries series;

    public ProcGraph(final String title) {

        super(title);
        series = new TimeSeries("Memory", Millisecond.class);
        final TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        final JFreeChart chart = createChart(dataset);

        final ChartPanel chartPanel = new ChartPanel(chart);

        final JPanel content = new JPanel(new BorderLayout());
        content.add(chartPanel);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        setContentPane(content);
        setDefaultCloseOperation(ApplicationFrame.HIDE_ON_CLOSE);
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(ProcGraph::updateGraph, 0, 1, TimeUnit.SECONDS);
    }

    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
                "Running Processes",
                "Time",
                "Number of Processes",
                dataset,
                true,
                true,
                false
        );
        final XYPlot plot = result.getXYPlot();
        ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        axis.setFixedAutoRange(60000.0);  // 60 seconds
        axis = plot.getRangeAxis();
        axis.setRange(0.0, 10.0);
        return result;
    }

    private static void updateGraph() {
        final double y = OperatingSystem.scheduler.readyQueue.size();
        series.add(new Millisecond(), y);
    }
}

