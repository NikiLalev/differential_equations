package FunctionModule;

import java.util.ArrayList;
import java.util.List;

public abstract class ODE<S, U, V, T> {
    protected final static double EPSILON = Double.MIN_VALUE;
    protected Function<S, U, V> function;
    protected T initialState;
    protected double[] calculatedValues;

    public ODE(Function<S, U, V> function, T initialState) {
        this.function = function;
        this.initialState = initialState;
    }

    public abstract T compute(double timeStep, int endTime);

    public static List<Double> computeListTimeSteps(double timeStep, int endTime) {
        List<Double> range = new ArrayList<Double>();

        for(int i = 0; Math.abs(i*timeStep - endTime) > EPSILON; i++) {
            range.add(i*timeStep);
        }
        return range;
    }


    public Function<S,U,V> getFunction() {
        return this.function;
    }

    public T getInitialState() {
        return this.initialState;
    }

    public double[] getCalculatedValues() {
        return calculatedValues;
    }
}
