package FunctionModule;

import VectorModule.Vector;

public class VelocityFunction implements Function<Vector, Vector, Vector>{

    private double timeStep;
    public VelocityFunction(double timeStep) {
        this.timeStep = timeStep;
    }
    @Override
    public Vector compute(Vector t, Vector y) {
        //t = current velocity, y = acceleration
        Vector newVelocity = t.add(y.multiply(timeStep));
        return newVelocity;
    }
    
}
