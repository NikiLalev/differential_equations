package FunctionModule;
import java.util.ArrayList;
import java.util.List;

public class Euler implements ODE<Double, Double>{
    private final static double EPSILON = Double.MIN_VALUE;
    private double[] calculatedValues;

    public double[] getCalculatedValues() {
        return calculatedValues;
    }

    @Override
    public Double ode(Function<Double> f, int startTime, int endTime, double timeStep, double initialValue) {
        List<Double> range = new ArrayList<Double>();

        for(int i = 0; Math.abs(i*timeStep - endTime) > EPSILON; i++) {
            range.add(i*timeStep);
        }
        double w = initialValue;
        calculatedValues = new double[range.size()];

        for(int i = 0; i < range.size(); i++) {
            double t = range.get(i);
            double functionEvaluation  = (double) f.compute(t, w);
            w = w + timeStep*functionEvaluation;
            calculatedValues[i] = w;
        }
        System.out.println(w);
        return w;
    }
}
