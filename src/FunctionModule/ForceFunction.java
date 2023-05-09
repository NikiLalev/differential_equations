package FunctionModule;


import java.util.Arrays;

import VectorModule.*;

public class ForceFunction implements Function<Vector, CelestialBody, StateVector> {
    private static final double GRAV = 6.6743E-20;

    @Override
    public Vector compute(CelestialBody t, StateVector y) {
        Vector force = new Vector(new double[]{0,0,0});
        for(CelestialBody object : y.getCelestialBodies()) {
            if(object.equals(t)) continue;
            else {
                //calculate the difference in position between t and the current object
                Vector differencePosition = t.getPosition().add(object.getPosition().multiply(-1));
                //calcualate the distance between the bodies and divide by 1 for formula
                double distance = 1 / (Vector.distance(t.getPosition(), object.getPosition()));
                //apply formula force = sumOfAllForces(G.m1.m2.d(x/y/z)/ distance^3)
                force = force.add(differencePosition.multiply(GRAV*t.getMass()*object.getMass()*Math.pow(distance, 3))); 

            }
        }
        return force.multiply(-1);
    }

    
    
}
