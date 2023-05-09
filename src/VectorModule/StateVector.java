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

    public CelestialBody getCelestialBodyIndex(int index) {
        return celestialBodies[index];
    }

    public CelestialBody getCelestialBodyName(String name) {
        for(CelestialBody cb : celestialBodies) {
            if(cb.getName().equals(name)) return cb;
        }
        throw new IllegalArgumentException("The object with this name is not present");
    }

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

    public StateVector add(StateVector vector) {
        super.validateDimensions(this, vector);
        CelestialBody[] result = new CelestialBody[vector.getNumberOfCelestialBodies()];
        for (int i = 0; i < celestialBodies.length; i++) {
            validateCelestialBodies(celestialBodies[i], vector.getCelestialBodyIndex(i));
            Vector additionResult = super.vectorAddition(celestialBodies[i], vector.getCelestialBodyIndex(i));
            //we add the the addition of the 2 celestial bodies to the result
            result[i] = new CelestialBody(additionResult.getValues(), celestialBodies[i].getMass(), celestialBodies[i].getName());
        }
        return new StateVector(result);
    }

    public StateVector multiply(double scalar) {
        CelestialBody[] result = new CelestialBody[this.getNumberOfCelestialBodies()];
        for (int i = 0; i < celestialBodies.length; i++) {
            Vector scalarMutliplicationResult = super.scalarMultiplication(celestialBodies[i], scalar);
            //we add the the scalar multiplication of the celestial body to the result
            result[i] = new CelestialBody(scalarMutliplicationResult.getValues(), celestialBodies[i].getMass(), celestialBodies[i].getName());
        }
        return new StateVector(result);
    }

    public static void validateCelestialBodies(CelestialBody cb1, CelestialBody cb2) {
        if(!(cb1.getName().equals(cb2.getName()) &&  cb1.getMass() == cb2.getMass())) throw new IllegalArgumentException("Celestial bodies order in the state vector needs to be the same");
    }

    
}
