package FunctionModule;

public class SimpleFunction implements Function<Double> {

    @Override
    public Double compute(Double t, Double y) {
        return Math.exp(-t) - Math.pow(-y, 2);
    }
  
}
