package project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Massimo Palmisano
 * ADVANCED PROGRAMMING PROJECT - Main Class
 */
public class Main {

    public static int portNumber;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final String SYNTAX_INSTRUCTIONS = "The syntax expected is: java -jar PalmisanoMassimo.jar PORT_NUMBER";

    public static void main(String[] args) {

        setPortNumber(args);

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (true) {
                Socket socket = serverSocket.accept();
                executorService.submit(() -> {
                    ClientHandler clientHandler = new ClientHandler(socket);
                    clientHandler.run();
                });
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        executorService.shutdown();

    }

    private static void setPortNumber(String[] args) {

        if (args.length != 1) {
            System.out.println("Expected one and only one argument: the port number");
            System.out.println(SYNTAX_INSTRUCTIONS);
            System.exit(0);
        }

        try {
            if(!args[0].matches("[0-9]+")) throw new NumberFormatException();
            portNumber = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Bad syntax for port number");
            System.out.println(SYNTAX_INSTRUCTIONS);
            System.exit(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Bad syntax for port number");
            System.out.println(e.getMessage());
            System.exit(0);
        }

    }

}