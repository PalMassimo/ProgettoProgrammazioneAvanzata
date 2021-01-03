package project.computation;

/**
 * @author Massimo Palmisano
 * ADVANCED PROGRAMMING PROJECT - A class that models a computation response
 */
public class ComputationResponse {

    private double computationTime;
    private final double result;

    public ComputationResponse(double result) {

        this.computationTime = 0;
        this.result = result;

    }

    public double getComputationTime() {
        return computationTime;
    }

    public String getResponse() {
        return "OK;" + computationTime + ";" + result;
    }

    public void setComputationTime(double computationTime) {
        this.computationTime = Double.parseDouble(String.format("%.3f", computationTime).replace(",", "."));
    }
}
