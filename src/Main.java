import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

import FunctionModule.Euler;
import FunctionModule.SimpleFunction;

public class Main {
    public static void main(String[] args) {
        SimpleFunction sf = new SimpleFunction();
        Euler euler = new Euler();
        int startTime = 0, endTime = 1; 
        double initialValue = 0;
       
        //y(1)
        double exact = 0.5033467;
        
        // create some sample data
        double[] xData = {0.2, 0.1, 0.05, 0.025, 0.01, 0.005, 0.0025, 0.001}; //timesteps
        double[] yData = new double[xData.length]; //error for specific timestep
        
        for(int i = 0; i < xData.length; i++) {
            double eulerApproximation = euler.ode(sf, startTime, endTime, xData[i], initialValue);
            yData[i] = Math.abs(eulerApproximation-exact)/exact;
        }
        // create a dataset for the data
        DefaultXYDataset dataset = new DefaultXYDataset();
        dataset.addSeries("euler", new double[][] {xData, yData});
        //dataset.addSeries("test", new double[][] {zData, wData}); for comparing functions
        
        // create a chart with logarithmic axes
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Log-Log Plot", "X Axis", "Y Axis", dataset);
        XYPlot plot = chart.getXYPlot();
        LogarithmicAxis xAxis = new LogarithmicAxis("Timesteps");
        LogarithmicAxis yAxis = new LogarithmicAxis("Relative error");
        plot.setDomainAxis(xAxis);
        plot.setRangeAxis(yAxis);
        
        // set the colors of the lines and shapes
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesShapesVisible(0, true);
        plot.setRenderer(renderer);
        
        // create a panel to display the chart
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 500));
        
        // create a frame to display the panel
        JFrame frame = new JFrame("Log-Log Plot Euler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}
