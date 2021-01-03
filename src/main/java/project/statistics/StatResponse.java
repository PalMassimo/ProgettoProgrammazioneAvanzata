/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.statistics;

/**
 *
 * @author massi
 *
 * ADVANCED PROGRAMMING PROJECT - Class modelling a StatResponse
 */
public class StatResponse {

    private double computationTime = 0;
    private final String statistic;

    public StatResponse(String statRequestType) {
        statistic = getStatistic(statRequestType);
    }

    public void setComputationTime(double computationTime) {
        this.computationTime = computationTime;
    }

    public String getResponse() {
        return "OK;" + computationTime + ";" + statistic;
    }

    private String getStatistic(String statRequestType) {

        switch (statRequestType) {
            case "STAT_AVG_TIME":
                return String.format("%.3f", Statistics.getAverageOkResponseTime()).replace(",", ".");
            case "STAT_MAX_TIME":
                return String.format("%.3f", Statistics.getMaxOkResponseTime()).replace(",", ".");
            case "STAT_REQS":
                return String.valueOf(Statistics.getOkResponses());
            default:
                System.out.println("ERROR: unknown statRequestType.");
                return "";
        }

    }

}
