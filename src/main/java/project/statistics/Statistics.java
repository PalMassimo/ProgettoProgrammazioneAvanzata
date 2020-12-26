/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.statistics;

/**
 *
 * @author massi
 */
public class Statistics {

    private static long okResponses = 0;
    private static double averageOkResponseTime = 0;
    private static double maxOkResponseTime = 0;

    public static double getMaxOkResponseTime() {
        return maxOkResponseTime;
    }

    public static void updateMaxOkResponseTime(double okResponseTime) {
        if (okResponseTime > maxOkResponseTime) {
            maxOkResponseTime = okResponseTime;
        }
    }

    public static double getAverageOkResponseTime() {
        return averageOkResponseTime;
    }

    public static void updateAverageOkResponseTime(double anotherOkResponseTime) {
        averageOkResponseTime = averageOkResponseTime * (okResponses - 1) / okResponses
                + anotherOkResponseTime / okResponses;
    }

    public static long getOkResponses() {
        return okResponses;
    }

    public static void addOkResponse() {
        okResponses++;
    }

}
