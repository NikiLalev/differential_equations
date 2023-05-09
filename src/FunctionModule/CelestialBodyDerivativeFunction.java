package FunctionModule;

import VectorModule.CelestialBody;
import VectorModule.StateVector;
import VectorModule.Vector;
import VectorModule.Vector3D;

public class CelestialBodyDerivativeFunction implements Function<CelestialBody, CelestialBody, StateVector>{

    private ForceFunction forceFunction;
    private AccelerationFunction accelerationFunction;

    CelestialBodyDerivativeFunction() {
        this.forceFunction = new ForceFunction();
        this.accelerationFunction = new AccelerationFunction();
    }

    @Override
    public CelestialBody compute(CelestialBody t, StateVector y) {
        // t - current state of the body, y - the state of the system
        
        Vector force = forceFunction.compute(t, y);
        Vector acceleration = accelerationFunction.compute(force, t.getMass()); 
        
        //the derivative of the position is velocity and the derivative of the velocity is accelleration
        CelestialBody newBody = new CelestialBody(t.getVelocity(), acceleration, t.getMass(), t.getName());
        
        return newBody;
    }
    
}
