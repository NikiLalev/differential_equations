package FunctionModule;

public class SimpleFunction implements Function<Double, Double, Double> {

    @Override
    public Double compute(Double t, Double y) {
        return Math.exp(-t) - Math.pow(-y, 2);
    }
  
}
