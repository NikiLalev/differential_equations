package VectorModule;
public class CelestialBody extends Vector{
    private Vector position;
    private Vector velocity;
    private double mass;
    private String name;

    CelestialBody(double[] values, String name) {
        //precondition: values.length == 7, 0,1,2 - position, 3,4,5 - velocity, 6 - mass
        super(values);
        this.position = new Vector3D(values[0], values[1], values[2]);
        this.velocity = new Vector3D(values[3], values[4], values[5]);
        this.mass = values[6];
        this.name = name;
    }

    public Vector getPosition() {
        return this.position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Vector getVelocity() {
        return this.velocity;
    }

    public void setVelocity(Vector velocity) {
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
}