package VectorModule;


public class StateVector extends Vector{ 
    
    //work in progress
    private int numberOfCelestialBodies;
    private CelestialBody[] celestialBodies;

    public StateVector(CelestialBody[] celestialBodies) {
        super(celestialBodies);
        this.celestialBodies = celestialBodies;
        this.numberOfCelestialBodies = celestialBodies.length;
    }

    //public double[] getCelestialBody

    
}
