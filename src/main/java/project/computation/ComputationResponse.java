package project.computation;

/**
 *
 * @author massi
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
