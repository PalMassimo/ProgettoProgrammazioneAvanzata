/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.computation;

/**
 *
 * @author massi
 */
public class ComputationResponse {

    private final String responseCode;
    private double computationTime;
    private final double result;

    public ComputationResponse(double result) {

        this.responseCode = "OK";
        this.computationTime = 0;
        this.result = result;

    }

    public String getResponseCode() {
        return responseCode;
    }

    public double getComputationTime() {
        return computationTime;
    }

    public double getResult() {
        return result;
    }

    public String getResponse() {
        return responseCode + ";" + computationTime + ";" + result;
    }

    public void setComputationTime(double computationTime) {
        this.computationTime = computationTime;
    }
}
