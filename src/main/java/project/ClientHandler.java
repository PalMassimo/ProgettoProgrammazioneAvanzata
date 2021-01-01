package project;

import project.computation.ComputationRequest;
import project.computation.ComputationResponse;
import project.computation.ComputeResult;
import project.computation.Tuples;
import project.computation.VariablesMap;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import project.exceptions.UnknownRequest;
import project.exceptions.WrongNumberOfArguments;
import project.statistics.StatResponse;
import project.statistics.Statistics;
import project.utils.RequestParser;

/**
 * @author massi
 */
public class ClientHandler extends Thread {

    private final Socket socket;
    private String successResponse;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            printWriter.println("Waiting for requests...");
            String requestLine;

            loop:
            while (true) {
                try {
                    requestLine = bufferedReader.readLine();
                    if (requestLine == null) throw new NullPointerException("Client has closed connection brutally");

                    switch (RequestParser.parseRequest(requestLine)) {
                        case QUIT_REQUEST -> {
                            break loop;
                        }
                        case STAT_REQUEST -> processStatRequest(requestLine);
                        case COMPUTATION_REQUEST -> processComputationRequest(requestLine);
                        case UNKNOWN_REQUEST -> throw new UnknownRequest();
                    }
                    printWriter.println(successResponse);
                } catch (IllegalArgumentException | WrongNumberOfArguments | UnknownRequest e) {
                    System.out.println("[Client Handler]: " + e.getMessage());
                    printWriter.println("ERR;" + e.getMessage());
                } catch (NullPointerException e){
                    System.out.println("[Client Handler]: " + e.getMessage());
                    printWriter.println("ERR;" + e.getMessage());
                    break;
                }
            }

            System.out.println("[Client Handler]: closed connection from: " + socket.getRemoteSocketAddress());
            printWriter.println("BYE BYE");//TODO: delete this statement before send the project
            Main.decreaseActiveConnection();
            //release resources
            bufferedReader.close();
            printWriter.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("[Client Handler]: IO exception was thrown");
            System.exit(0);
        }
    }

    private void processStatRequest(String statRequestLine) {
        long startComputationTime = System.currentTimeMillis();
        StatResponse statResponse = new StatResponse(statRequestLine);
        long endComputationTime = System.currentTimeMillis();
        statResponse.setComputationTime(Math.pow(10, -3) * (endComputationTime - startComputationTime));
        this.setSuccessResponse(statResponse.getResponse());
    }

    private void processComputationRequest(String computationRequestLine) {
        long startComputationTime = System.currentTimeMillis();
        ComputationRequest computationRequest = new ComputationRequest(computationRequestLine);
        VariablesMap variablesMap = new VariablesMap(computationRequest.getVariableValuesFunction());
        Tuples tuples = new Tuples(variablesMap, computationRequest.getValuesKind());
        ComputeResult computeResult = new ComputeResult(computationRequest.getExpressions(),
                tuples, computationRequest.getComputationKind());
        ComputationResponse computationResponse = new ComputationResponse(computeResult.getFinalResult());
        long endComputationTime = System.currentTimeMillis();

        computationResponse.setComputationTime(Math.pow(10, -3) * (endComputationTime - startComputationTime));
        this.setSuccessResponse(computationResponse.getResponse());

        Statistics.addOkResponse();
        Statistics.updateAverageOkResponseTime(computationResponse.getComputationTime());
        Statistics.updateMaxOkResponseTime(computationResponse.getComputationTime());

    }

    public void setSuccessResponse(String successResponse) {
        this.successResponse = successResponse;
    }

}
