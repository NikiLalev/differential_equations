package FunctionModule;

import VectorModule.CelestialBody;
import VectorModule.StateVector;

public class SolarSystemDerivativeFunction implements Function<StateVector, CelestialBody, StateVector> {

    private CelestialBodyDerivativeFunction cbDerivativeFunction;

    public SolarSystemDerivativeFunction() {
        this.cbDerivativeFunction = new CelestialBodyDerivativeFunction();
    }

    @Override
    public StateVector compute(CelestialBody t, StateVector y) {
        //y = old state vector
        int numberOfCelestialBodies = y.getNumberOfCelestialBodies();
        CelestialBody[] newStateVector = new CelestialBody[numberOfCelestialBodies];

        for (int i = 0; i < numberOfCelestialBodies; i++) {
            newStateVector[i] =  cbDerivativeFunction.compute(y.getCelestialBodyIndex(i), y);
        }

        return new StateVector(newStateVector);
    }
    
}
