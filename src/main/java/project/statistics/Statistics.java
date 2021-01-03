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

    public synchronized static void updateMaxOkResponseTime(double okResponseTime) {

        if (okResponseTime > maxOkResponseTime) maxOkResponseTime = okResponseTime;

    }

    public static double getAverageOkResponseTime() {
        return averageOkResponseTime;
    }

    public synchronized static void updateAverageOkResponseTime(double anotherOkResponseTime) {
        averageOkResponseTime = averageOkResponseTime * (okResponses - 1) / okResponses
                + anotherOkResponseTime / okResponses;
    }

    public static long getOkResponses() {
        return okResponses;
    }

    public synchronized static void addOkResponse() {
        okResponses++;
    }

}
