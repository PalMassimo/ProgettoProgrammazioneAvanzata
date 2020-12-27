package project;

import project.exceptions.WrongNumberOfArguments;

import java.util.Arrays;

public class RequestParser {

//    private static RequestType requestType;
//    private static String requestLine;
    private final static String computationRequestRegex = "(MAX|MIN|AVG|COUNT)_(LIST|GRID)";
    private final static String statRequestRegex = "STAT_(REQS|AVG|MAX)(_TIME)?";

//    public RequestParser(){
//
//    }
//    public RequestParser(String requestLine) {
//        this.requestLine = requestLine;

        // set the correct request type from the request line
//        if (isQuitRequest()) {
//            setRequestType(RequestType.QUIT_REQUEST);
//        } else if (isStatRequest()) {
//            setRequestType(RequestType.STAT_REQUEST);
//        } else if (isComputationRequest()) {
//            setRequestType(RequestType.COMPUTATION_REQUEST);
//        } else {
//            setRequestType(RequestType.UNKNOWN_REQUEST);
//        }
//    }

    public static RequestType parseRequest(String requestLine) {
        if (isQuitRequest(requestLine)) {
//            setRequestType(RequestType.QUIT_REQUEST);
            return RequestType.QUIT_REQUEST;
        } else if (isStatRequest(requestLine)) {
//            setRequestType(RequestType.STAT_REQUEST);
            return RequestType.STAT_REQUEST;
        } else if (isComputationRequest(requestLine)) {
//            setRequestType(RequestType.COMPUTATION_REQUEST);
            return RequestType.COMPUTATION_REQUEST;
        } else {
//            setRequestType(RequestType.UNKNOWN_REQUEST);
            return RequestType.UNKNOWN_REQUEST;
        }
    }

    private static boolean isStatRequest(String requestLine) {
        return requestLine.matches(statRequestRegex);
    }

    private static boolean isComputationRequest(String requestLine) {

        if (!requestLine.matches(computationRequestRegex)) return false;

        //TODO: check the computation request

        if (requestLine.split(";").length != 3)
            throw new WrongNumberOfArguments("Computation Request with wrong number of arguments");

        String variableValuesFunction = requestLine.split(";")[1];

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
        return true;
    }

    private static boolean isQuitRequest(String requestLine) {
        return requestLine.equals("BYE");
    }

//    public void setRequestType(RequestType requestType) {
//        this.requestType = requestType;
//    }

//    public RequestType getRequestType() {
//        return requestType;
//    }

}
