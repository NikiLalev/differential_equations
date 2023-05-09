package FunctionModule;

import java.util.List;

import VectorModule.StateVector;

public class EulerSolverSolarSystem extends SolarSystemODE {

    public EulerSolverSolarSystem(StateVector initialState) {
        super(initialState);
    }

    @Override
    public StateVector compute(double timeStep, int endTime) {
        StateVector w = initialState;
        List<Double> ListTimeSteps = computeListTimeSteps(timeStep, endTime);
        calculatedValues = new StateVector[ListTimeSteps.size()];

        for(int i = 0; i < ListTimeSteps.size(); i++) {

            StateVector functionEvaluation  = function.compute(null, w); //fix the fact that you provide celestial body
            //w = w + timeStep*functionEvaluation;
            StateVector newState =  w.add(functionEvaluation.multiply(timeStep)); 
            w = newState;
            calculatedValues[i] = w;
        }
        System.out.println(w);
        return w;
    }

    
    
}
