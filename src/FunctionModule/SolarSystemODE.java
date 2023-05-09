package FunctionModule;

import VectorModule.StateVector;


public abstract class SolarSystemODE extends ODE<SolarSystemDerivativeFunction, StateVector>{
 
    public SolarSystemODE(StateVector initialState) {
        super(new SolarSystemDerivativeFunction(), initialState);
    }


}
