package FunctionModule;

import java.util.ArrayList;
import java.util.List;

public abstract class ODE<S extends Function, T> {
    protected final static double EPSILON = Double.MIN_VALUE;
    protected S function;
    protected T initialState;
    protected T[] calculatedValues;

    public ODE(S function, T initialState) {
        this.function = function;
        this.initialState = initialState;
    }

    public abstract T compute(double timeStep, int endTime);

    //to do: add compute 1 step


    public static List<Double> computeListTimeSteps(double timeStep, int endTime) {
        List<Double> range = new ArrayList<Double>();

        for(int i = 0; Math.abs(i*timeStep - endTime) > EPSILON; i++) {
            range.add(i*timeStep);
        }
        return range;
    }


    public S getFunction() {
        return this.function;
    }

    public T getInitialState() {
        return this.initialState;
    }

    public T[] getCalculatedValues() {
        return calculatedValues;
    }
}
