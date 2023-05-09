package VectorModule;


public class Vector {
    private int dimension;
    private double[] values;
    public Vector(double[] values) {
        this.values = values;
        this.dimension = values.length;
    }

    public Vector(Vector[] valueVectors) {
        this.values = mergeVectorsToArray(valueVectors);
        this.dimension = values.length;
    }


 
    public Vector add(Vector vector) {
        return vectorAddition(this, vector);
    }

    public static final Vector vectorAddition(Vector vector1, Vector vecotr2) {
        //condition: vector1.getDimension() == vector2.getDimension()
        validateDimensions(vector1, vecotr2);

        int dimension = vector1.getDimension();
        double[] result = new double[dimension];
        for(int i = 0; i < dimension; i++) {
            result[i] = vector1.getValueIndex(i) + vecotr2.getValueIndex(i);
        }
        return new Vector(result);
    }


    public Vector multiply(double scalar) {
        return scalarMultiplication(this, scalar);
    }

    public static final Vector scalarMultiplication(Vector vector, double scalar) {
        double[] result = new double[vector.getDimension()];
        for(int i = 0; i < vector.getDimension(); i++) {
           result[i] = vector.getValueIndex(i) * scalar;
        }
        return new Vector(result);
    }

    public static double vectorNorm(Vector vector) {
        double sumSquares = 0;
        for(double value : vector.getValues()) {
            sumSquares += Math.pow(value, 2);
        }
        return Math.sqrt(sumSquares);
    }

    public static double distance(Vector v1, Vector v2) {
        Vector diffVector = v1.add(v2.multiply(-1));
        return vectorNorm(diffVector);
    }

    public static void validateDimensions(Vector v1, Vector v2) {
        if(v1.getDimension() != v2.getDimension()) throw new IllegalArgumentException("Can't perfrom operations on vectors of different dimensions");
    }

    public int getDimension() {
        return this.dimension;
    }

    public double[] getValues() {
        return this.values;
    }

    public double getValueIndex(int index) {
        //precondition: index < values.length
        return values[index];
    }

    public void setValues(double[] values) {
        this.values = values;
    }

    public void setValueIndex(double value, int index) {
        //precondition: index < values.length
        values[index] = value; 
    }

    public double[] mergeVectorsToArray(Vector[] vectors) {
        //precondition: there is at least 1 vector and all the vectors are of the same length

        //the length of the resulting array is equal to the number of vectors * the length of a vector
        int lengthVector = vectors[0].getDimension();
        int lengthArray = vectors.length * lengthVector;
        
        double[] result = new double[lengthArray];
        int offset = 0;
        for (Vector vector : vectors) {
            System.arraycopy(vector.getValues(), 0, result, offset, lengthVector);
            offset +=lengthVector;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Vector3D)) {
            return false;
        }
        Vector3D vector = (Vector3D) o;
        if(this.getDimension() == vector.getDimension()) {
            for(int i = 0; i < dimension; i++) {
                if(values[i] != vector.getValueIndex(i)) {
                    return false;
                }
            }
            //the dimensions are equal and all the values stored are equal
            return true;
        } else {
            return false;
        }
    }  
}
