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

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(PORT_NUMBER)) {
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.start();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }   

    }
}