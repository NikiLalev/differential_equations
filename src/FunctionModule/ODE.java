package FunctionModule;

public interface ODE<T, G> {
    public T ode(Function<G> f, int startTime, int endTime, double timeStep, double initialValue);
}
