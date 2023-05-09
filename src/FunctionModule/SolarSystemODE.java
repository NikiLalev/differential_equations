package FunctionModule;

import java.util.List;

import VectorModule.CelestialBody;
import VectorModule.StateVector;
import VectorModule.Vector;

public class SolarSystemODE extends ODE<Vector, Vector, Vector, StateVector>{
 
    
    public SolarSystemODE(Function<Vector, Vector, Vector> function, StateVector initialState) {
        super(function, initialState);
        // forceFunction = new ForceFunction(); //celestial body, state vector
        // accelerationFunction = new AccelerationFunction(); //obejct's force vector, object's mass
        // velocityFunction = new VelocityFunction(timeStep);

    }

    @Override
    public StateVector compute(double timeStep, int endTime) {
        StateVector w = initialState;
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
