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

public class Plot {
    
    public static void loglogPlot(double[] xData, double[] yData, String name, DefaultXYDataset dataset) {
        
        dataset.addSeries(name, new double[][] {xData, yData});
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
        JFrame frame = new JFrame("Log-Log Plot " + name);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }
}


