package VectorModule;

public class Vector3D extends Vector{

    public Vector3D(double x, double y, double z) {
        super(new double[] {x, y, z});
      
    }

    public Vector3D() {
        super(new double[] {0, 0, 0});
    }

    public double getX() {
        return this.getValueIndex(0);
    }

    public void setX(double x) {
        this.setValueIndex(x, 0);

    }

    public double getY() {
        return this.getValueIndex(1);
    }

    public void setY(double y) {
        this.setValueIndex(y, 1);
    }

    public double getZ() {
        return this.getValueIndex(2);
    }

    public void setZ(double z) {
        this.setValueIndex(z, 2);
    }


    public double euclideanDistance(Vector3D vector) {
        double dx = this.getX() - vector.getX();
        double dy = this.getY() - vector.getY();
        double dz = this.getZ() - vector.getZ();
        return Math.sqrt((dx * dx) + (dy * dy) + (dz * dz));
    }

    @Override
    public String toString() {
        return "{" +
                "x='" + getX() + "'" +
                ", y='" + getY() + "'" +
                ", z='" + getZ() + "'" +
                "}";
    }

    
}

