package project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author massi
 * ADVANCED PROGRAMMING - PROJECT
 */
public class Main {

    public static int portNumber;
    private static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final String SYNTAX_INSTRUCTIONS = "The syntax expected is: java -jar PalmisanoMassimo.jar PORT_NUMBER";

    public static void main(String[] args) {

        parseCommand(args);

        try {
            portNumber = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Bad number syntax");
            System.exit(0);
        } catch (NullPointerException e) {
            System.out.println("You have to insert a valid port number");
            System.exit(0);
        }


        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.submit(() -> {
                    ClientHandler clientHandler = new ClientHandler(socket);
                    clientHandler.run();
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }

    }

    private static void parseCommand(String[] args) {

        if (args.length != 1) {
            System.out.println("Expected only one argument: the port number");
            System.exit(0);
        }

        try {
            portNumber = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Bad syntax for port number");
            System.out.println(SYNTAX_INSTRUCTIONS);
            System.exit(0);
        } catch (Exception e){
            System.out.println("Reached unreachable statement");
            System.exit(-1);
        }

    }

}