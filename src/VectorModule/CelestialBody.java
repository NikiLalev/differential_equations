package VectorModule;
public class CelestialBody extends Vector{
    private Vector position;
    private Vector velocity;
    private double mass;
    private String name;

    public CelestialBody(double[] values, double mass, String name) {
        //precondition: values.length == 6, 0,1,2 - position, 3,4,5 - velocity, 
        super(values);
        this.position = new Vector3D(values[0], values[1], values[2]);
        this.velocity = new Vector3D(values[3], values[4], values[5]);
        this.mass = mass;
        this.name = name;
    }

    public CelestialBody(Vector position, Vector velocity, double mass, String name) {
        super(new Vector[]{position, velocity});
        this.position = position;
        this.velocity = velocity;
        this.mass = mass;
        this.name = name;
    }   

    public Vector getPosition() {
        return this.position;
    }

    public void setPosition(Vector3D position) {
        this.position = position;
    }

    public Vector getVelocity() {
        return this.velocity;
    }

    public void setVelocity(Vector3D velocity) {
        this.velocity = velocity;
    }

    public double getMass() {
        return this.mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CelestialBody)) {
            return false;
        }
        CelestialBody celestialBody = (CelestialBody) o;
        return position.equals(celestialBody.position) && velocity.equals(celestialBody.velocity) && mass == celestialBody.mass && name.equals(celestialBody.name);
    }

    

}
