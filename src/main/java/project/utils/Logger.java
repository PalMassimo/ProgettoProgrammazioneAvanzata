package project.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Massimo Palmisano
 * ADVANCED PROGRAMMING PROJECT - A simple logger class that prints messages on the standard output
 */
public class Logger {

    private static final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");

    public static void log(String string) {
        System.out.println(formatter.format(new Date()) + ": " + string);
    }

}
