/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import project.exceptions.UnknownRequest;
import project.statistics.StatResponse;
import project.statistics.Statistics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import project.computation.ComputationRequest;
import project.computation.ComputationResponse;
import project.computation.ComputeResult;
import project.computation.Tuples;
import project.computation.VariablesMap;
import project.exceptions.WrongNumberOfArguments;

/**
 * @author massi
 */
public class ClientHandler extends Thread {

    private final Socket socket;
    private String response;
//    private RequestParser requestParser;

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
//                    requestParser = new RequestParser(requestLine);

                    switch (RequestParser.parseRequest(requestLine)) {
                        case QUIT_REQUEST -> {
                            break loop;
                        }
                        case STAT_REQUEST -> processStatRequest(requestLine);
                        case COMPUTATION_REQUEST -> processComputationRequest(requestLine);
                        case UNKNOWN_REQUEST -> throw new UnknownRequest();
                    }
                    printWriter.println(response);
                } catch (IllegalArgumentException | WrongNumberOfArguments | UnknownRequest e) {
                    System.out.println("[Client Handler]: " + e.getMessage());
                    printWriter.println("ERR;" + e.getMessage());
                }
            }

            printWriter.println("BYE BYE");
            //release resources
            bufferedReader.close();
            printWriter.close();
            socket.close();

        } catch (IOException e) {
            System.out.println("[Client Handler]: Something went wrong");
            System.exit(0);
        }
    }

    private void processStatRequest(String statRequestLine) {
        long startComputationTime = System.currentTimeMillis();
        StatResponse statResponse = new StatResponse(statRequestLine);
        long endComputationTime = System.currentTimeMillis();
        statResponse.setComputationTime(Math.pow(10, -3) * (endComputationTime - startComputationTime));
        this.setResponse(statResponse.getResponse());
    }

    private void processComputationRequest(String computationRequestLine) {
        long startComputationTime = System.currentTimeMillis();
        ComputationRequest computationRequest = new ComputationRequest(computationRequestLine);
        VariablesMap variablesMap = new VariablesMap(computationRequest.getVariableValuesFunction());
        Tuples tuples = new Tuples(variablesMap, computationRequest.getValuesKind());
        ComputeResult computeResult = new ComputeResult(computationRequest.getExpression(),
                tuples, computationRequest.getComputationKind());
        ComputationResponse computationResponse = new ComputationResponse(computeResult.getFinalResult());
        long endComputationTime = System.currentTimeMillis();

        computationResponse.setComputationTime(Math.pow(10, -3) * (endComputationTime - startComputationTime));
        this.setResponse(computationResponse.getResponse());
        Statistics.addOkResponse();
        Statistics.updateAverageOkResponseTime(computationResponse.getComputationTime());
        Statistics.updateMaxOkResponseTime(computationResponse.getComputationTime());

    }

    public void setResponse(String response) {
        this.response = response;
    }

    private RequestType getRequestType(String requestLine) {
        switch (requestLine.split(";")[0]) {
            case "BYE":
                return RequestType.QUIT_REQUEST;

            case "STAT_REQS", "STAT_AVG_TIME", "STAT_MAX_TIME":
                return RequestType.STAT_REQUEST;

            case "MAX_LIST", "MIN_LIST", "AVG_LIST", "COUNT_LIST":
            case "MAX_GRID", "MIN_GRID", "AVG_GRID", "COUNT_GRID":
                return RequestType.COMPUTATION_REQUEST;

            default:
                return null; //SISTEMA
        }
    }

    private void inspectComputationRequest(String requestLine) {
        //check if it is a stat or a quit request
        switch (requestLine) {
            case "BYE", "STAT_REQS", "STAT_AVG_TIME", "STAT_MAX_TIME":
                return;
        }
        //the only possibility is that it can be a computation request
        String[] tokens = requestLine.split(";");
        if (tokens.length < 3) {
            throw new WrongNumberOfArguments("wrong number of elements");
        }

        switch (tokens[0]) {
            case "MIN_LIST", "MAX_LIST", "AVG_LIST", "COUNT_LIST":
            case "MIN_GRID", "MAX_GRID", "AVG_GRID", "COUNT_GRID":
                break;
            default:
                throw new IllegalArgumentException("unknown operation");
        }

        //check second token
        // example variableValuesFunction: "x0:-1:0.1:1,x1:10:2:20"
        String variableValuesFunction = tokens[1];
        for (String variableValues : variableValuesFunction.split(",")) {

            if (variableValues.split(":").length != 4) {
                throw new WrongNumberOfArguments("wrong number of variable parameters");
            }

            String variableName = variableValues.split(":")[0];
            String minValue = variableValues.split(":")[1];
            String stepSize = variableValues.split(":")[2];
            String maxValue = variableValues.split(":")[3];

            if (!variableName.matches("[a-z][a-z0-9]*")) {
                throw new IllegalArgumentException("bad syntax variable name");
            }

            if (!minValue.matches("(-)?[0-9]+(\\.[0-9]+)?")) {
                throw new IllegalArgumentException("bad syntax min value");
            }

            if (!stepSize.matches("[0-9]+(\\.[0-9]+)?") || Double.parseDouble(stepSize) == 0.0) {
                throw new IllegalArgumentException("bad syntax step size");
            }

            if (!maxValue.matches("(-)?[0-9]+(\\.[0-9]+)?")) {
                throw new IllegalArgumentException("bad syntax max value");
            }

            //TODO:manca il controllo del valore dello step size:
            //e.g. x0:-1:0.3:1 -> valore dello step size non va bene
        }

    }

}
