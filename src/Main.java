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

import FunctionModule.EulerSolverSolarSystem;
import FunctionModule.SimpleEuler;
import FunctionModule.SimpleFunction;
import VectorModule.CelestialBody;
import VectorModule.StateVector;
import VectorModule.Vector;

public class Main {
    public static void main(String[] args) {
        SimpleFunction sf = new SimpleFunction();
        int endTime = 1;
        double initialValue = 0;
        SimpleEuler euler = new SimpleEuler(sf, initialValue);
        //y(1)
        double exact = 0.5033467;
        
        // create some sample data
        double[] xData = {0.2, 0.1, 0.05, 0.025, 0.01, 0.005, 0.0025, 0.001}; //timesteps
        double[] yData = new double[xData.length]; //error for specific timestep
        
        //compute error
        for(int i = 0; i < xData.length; i++) {
            double eulerApproximation = euler.compute(xData[i], endTime);
            yData[i] = Math.abs(eulerApproximation-exact)/exact;
        }

        //Plot.loglogPlot(xData, yData, "euler");

        //-------------------------------------Multivariable solar system test
        StateVector initialState = InputReader.read("src\\initial.txt");
        

        Vector exactPositionEarth1Day = new Vector(new double[]{-1.477286832625031E+08, -3.035837009095788E+07, 3.390427978567779E+04});
        double exactPositionEarthX1Day = -1.477286832625031E+08;
        //compute error
        xData = new double[]{3600, 1800, 900, 450, 225};
        yData = new double[xData.length]; //error for specific timestep
        endTime = 86400; //compute for 1 day
        for(int i = 0; i < xData.length; i++) {
            EulerSolverSolarSystem eulerSolverSolarSystem = new EulerSolverSolarSystem(initialState);
            StateVector resultState = eulerSolverSolarSystem.compute(xData[i], endTime);

            CelestialBody eulerApproximationEarth = resultState.getCelestialBodyName("earth");
            yData[i] = Vector.distance(eulerApproximationEarth.getPosition(), exactPositionEarth1Day);
            //yData[i] = Math.abs(eulerApproximationEarth.getPosition().getValueIndex(0)-exactPositionEarthX1Day)/exactPositionEarthX1Day;
        }

        Plot.loglogPlot(xData, yData, "euler solar");
    }
}
