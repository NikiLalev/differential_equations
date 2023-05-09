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

    public int getNumberOfCelestialBodies() {
        return this.numberOfCelestialBodies;
    }

    public void setNumberOfCelestialBodies(int numberOfCelestialBodies) {
        this.numberOfCelestialBodies = numberOfCelestialBodies;
    }

    public CelestialBody[] getCelestialBodies() {
        return this.celestialBodies;
    }

    public void setCelestialBodies(CelestialBody[] celestialBodies) {
        this.celestialBodies = celestialBodies;
    }

    
}
