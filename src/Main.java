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

        // create a dataset for the data for plotting 
        DefaultXYDataset dataset = new DefaultXYDataset();

        //-----------------------
        
        double[] xData = new double[]{3600, 1800, 900, 450, 225}; //timesteps
        double[] yData = new double[xData.length]; //error for specific timestep
        
        //compute error
        // for(int i = 0; i < xData.length; i++) {
        //     double eulerApproximation = euler.compute(xData[i], endTime);
        //     yData[i] = Math.abs(eulerApproximation-exact)/exact;
        // }

        //Plot.loglogPlot(xData, yData, "euler");

    

        //-------------------------------------Multivariable solar system test
        StateVector initialState = InputReader.read("src\\initial.txt");
        
        //exact positions
       
        Vector exactPositionEarth1Day = new Vector(new double[]{-1.477286832625031E+08, -3.035837009095788E+07, 3.390427978567779E+04});
        double exactPositionEarthX1Day = -1.477286832625031E+08;

        //RK4 results
        Vector positionEarth1DayRK4225 = new Vector(new double[]{ -1.4772782182248762E8, -3.036505870369339E7, 33900.08559581061});
        Vector positionEarth1DayRK4450 = new Vector(new double[]{-1.4772658298921365E8, -3.037165155949118E7, 33900.49406180158});
        Vector positionEarth1DayRK4900 = new Vector(new double[]{-1.4772410460237947E8, -3.0384837120153494E7, 33901.31115190798});
        Vector positionEarth1DayRK41800 = new Vector(new double[]{-1.4771914494764283E8, -3.0411207637492184E7, 33902.94596392254});
        Vector positionEarth1DayRK43600 = new Vector(new double[]{-1.4770921411450347E8, -3.046394625420286E7, 33906.21810951442});
        //compute error RK4
        yData = new double[] {
            Vector.distance(exactPositionEarth1Day, positionEarth1DayRK43600),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayRK41800),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayRK4900),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayRK4450),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayRK4225)
        };
        Plot.loglogPlot(xData, yData, "RK4 solar", dataset);

        //Verlet results
        Vector positionEarth1DayVerlet225 = new Vector(new double[]{-1.481596233084529E8, -2.7981878982887376E7, 33756.13140869088});
        Vector positionEarth1DayVerlet450 = new Vector(new double[]{-1.481323397232638E8, -2.814059939420535E7, 33765.36401967064});
        Vector positionEarth1DayVerlet900 = new Vector(new double[]{-1.480777725528856E8, -2.84580402168413E7, 33783.82924162998});
        Vector positionEarth1DayVerlet1800 = new Vector(new double[]{-1.4796863821212918E8, -2.9092921862113286E7, 33820.75968554866});
        Vector positionEarth1DayVerlet3600 = new Vector(new double[]{-1.4775036953061706E8, -3.036268515265717E7, 33894.620573386026});
        //compute error Verelt
        yData = new double[] {
            Vector.distance(exactPositionEarth1Day, positionEarth1DayVerlet3600),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayVerlet1800),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayVerlet900),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayVerlet450),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayVerlet225)
        };
        Plot.loglogPlot(xData, yData, "Verlet solar", dataset);

        //compute error euler
        yData = new double[xData.length]; //error for specific timestep
        endTime = 86400; //compute for 1 day
        for(int i = 0; i < xData.length; i++) {
            EulerSolverSolarSystem eulerSolverSolarSystem = new EulerSolverSolarSystem(initialState);
            StateVector resultState = eulerSolverSolarSystem.compute(xData[i], endTime);

            CelestialBody eulerApproximationEarth = resultState.getCelestialBodyName("earth");
            yData[i] = Vector.distance(eulerApproximationEarth.getPosition(), exactPositionEarth1Day);
            //yData[i] = Math.abs(eulerApproximationEarth.getPosition().getValueIndex(0)-exactPositionEarthX1Day)/exactPositionEarthX1Day;
        }

        Plot.loglogPlot(xData, yData, "euler solar", dataset);


        //
        double[] xDataSmallSteps = {0.2, 0.1, 0.05, 0.025, 0.01, 0.005, 0.0025, 0.001}; //timesteps
        double[] yDataSmallSteps = new double[xDataSmallSteps.length]; //error for specific timestep
        DefaultXYDataset datasetSmallSteps = new DefaultXYDataset();
        //leap frog results
        Vector positionEarth1DayLeapFrog02 = new Vector(new double[]{-1.4810045284334946E8, -2.8331228959111493E7, 33776.23681688121});
        Vector positionEarth1DayLeapFrog01 = new Vector(new double[]{-1.481434665457011E8, -2.8077152381911352E7, 33761.619240306114});
        Vector positionEarth1DayLeapFrog005 = new Vector(new double[]{-1.4816513337665698E8, -2.795014517419511E7, 33754.27193727843});
        Vector positionEarth1DayLeapFrog0025 = new Vector(new double[]{-1.4817600679786614E8, -2.7886649302676212E7, 33750.58860307855});
        Vector positionEarth1DayLeapFrog001 = new Vector(new double[]{-1.4818254365418842E8, -2.7848554247858826E7, 33748.375497069916});
        Vector positionEarth1DayLeapFrog0005 = new Vector(new double[]{-1.4818472474037585E8, -2.7835856307079952E7, 33747.63727697769});
        Vector positionEarth1DayLeapFrog00025 = new Vector(new double[]{-1.4818581568362257E8, -2.7829507413671352E7, 33747.26806974591});
        Vector positionEarth1DayLeapFrog0001 = new Vector(new double[]{-1.481864703776218E8, -2.782569810225545E7, 33747.0465143019});
        //compute error leapfrog
        yDataSmallSteps = new double[] {
            Vector.distance(exactPositionEarth1Day, positionEarth1DayLeapFrog02),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayLeapFrog01),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayLeapFrog005),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayLeapFrog0025),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayLeapFrog001),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayLeapFrog0005),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayLeapFrog00025),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayLeapFrog0001)
        };
       Plot.loglogPlot(xDataSmallSteps, yDataSmallSteps, "Leapfrog solar", datasetSmallSteps);

        //verlet results
        Vector positionEarth1DayVerlet02 = new Vector(new double[]{-2.1967454057444707E8, -4.127920769324714E7, 46613.5661424863});
        Vector positionEarth1DayVerlet01 = new Vector(new double[]{-1.885237570116604E8, -3.541966396831901E7, 40586.58884587954});
        Vector positionEarth1DayVerlet005 = new Vector(new double[]{-1.7018728159437963E8, -3.196978633917757E7, 37122.83141025368});
        Vector positionEarth1DayVerlet0025 = new Vector(new double[]{-1.597978147557593E8, -3.001427910380751E7, 35246.24450732893});
        Vector positionEarth1DayVerlet001 = new Vector(new double[]{-1.5300586288978183E8, -2.873478827415569E7, 34135.9469896518});
        Vector positionEarth1DayVerlet0005 = new Vector(new double[]{-1.506279229154334E8, -2.8286102429374408E7, 33822.637644330076});
        Vector positionEarth1DayVerlet00025 = new Vector(new double[]{-1.4941531119163564E8, -2.8056813798085824E7, 33713.472036265455});
        Vector positionEarth1DayVerlet0001 = new Vector(new double[]{-1.486800437684949E8, -2.791733024587401E7, 33694.68122154636});
        //compute error verlet
        yDataSmallSteps = new double[] {
            Vector.distance(exactPositionEarth1Day, positionEarth1DayVerlet02),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayVerlet01),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayVerlet005),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayVerlet0025),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayVerlet001),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayVerlet0005),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayVerlet00025),
            Vector.distance(exactPositionEarth1Day, positionEarth1DayVerlet0001)
        };
        Plot.loglogPlot(xDataSmallSteps, yDataSmallSteps, "Verlet solar", datasetSmallSteps);
        
    }
}
