package project.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);


        System.out.println(bufferedReader.readLine());
        printWriter.println("MIN_GRID;x0:-1:0.1:1,x1:-10:1:20;((x0+(2.0^x1))/(21.1-x0));(x1*x0);((x1+x0)*63.2)/(x0^x1);(x1-800)");
        System.out.println(bufferedReader.readLine());
        printWriter.println("COUNT_LIST;x0:1:0.01:100;x0");
        System.out.println(bufferedReader.readLine());
        printWriter.println("STAT_REQS");
        System.out.println("STAT_REQS: " + bufferedReader.readLine());
        printWriter.println("STAT_AVG_TIME");
        System.out.println("STAT_AVG_TIME: " + bufferedReader.readLine());
        printWriter.println("STAT_MAX_TIME");
        System.out.println("STAT_MAX_TIME: " + bufferedReader.readLine());

        printWriter.println("BYE");
        bufferedReader.close();
        printWriter.close();
        socket.close();


    }

}
