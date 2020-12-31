package project;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author massi
 * ADVANCED PROGRAMMING - PROJECT
 */
public class Main {

    public static final int PORT_NUMBER = 8080;
    //    private static final int maxConnection = Runtime.getRuntime().availableProcessors();
    private static int activeConnections = 0;

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT_NUMBER)) {
            while (true) {
                if (activeConnections < Runtime.getRuntime().availableProcessors()) {
                    Socket socket = serverSocket.accept();
                    activeConnections++;
                    System.out.println("New connection from: " + socket.getRemoteSocketAddress());
                    System.out.println("Active connections " + activeConnections);
                    ClientHandler clientHandler = new ClientHandler(socket);
                    clientHandler.start();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void decreaseActiveConnection() {
        activeConnections = activeConnections - 1;
        System.out.println("Active connections: " + activeConnections);
    }
}