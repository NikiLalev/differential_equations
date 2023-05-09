package FunctionModule;

import java.util.List;

public class SimpleEuler extends ODE<Double, Double, Double, Double>{

    public SimpleEuler(Function<Double, Double, Double> function, Double initialState) {
        super(function, initialState);
    }

    @Override
    public Double compute(double timeStep, int endTime) {
        double w = initialState;
        List<Double> ListTimeSteps = computeListTimeSteps(timeStep, endTime);
        calculatedValues = new double[ListTimeSteps.size()];

        for(int i = 0; i < ListTimeSteps.size(); i++) {
            double t = ListTimeSteps.get(i);
            double functionEvaluation  = (double) function.compute(t, w);
            w = w + timeStep*functionEvaluation;
            calculatedValues[i] = w;
        }
        System.out.println(w);
        return w;
    }
}
