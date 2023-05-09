package FunctionModule;

import VectorModule.Vector;

public class AccelerationFunction implements Function<Vector, Vector, Double> {

    @Override
    public Vector compute(Vector t, Double y) {
        //t = force, y = mass
        //acc = force / mass
        Vector acceleration = t.multiply(1/y);
        return acceleration;
    }
    
}
