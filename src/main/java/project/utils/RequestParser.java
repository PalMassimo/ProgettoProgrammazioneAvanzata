package project.utils;

import project.exceptions.WrongNumberOfArguments;

public class RequestParser {

    private final static String computationRequestRegex = "^(MAX|MIN|AVG|COUNT)_(LIST|GRID).*$";
    private final static String statRequestRegex = "STAT_(REQS|(AVG|MAX)_TIME)";

    public static RequestType parseRequest(String requestLine) {
        if (isQuitRequest(requestLine)) {
            return RequestType.QUIT_REQUEST;
        } else if (isStatRequest(requestLine)) {
            return RequestType.STAT_REQUEST;
        } else if (isComputationRequest(requestLine)) {
            return RequestType.COMPUTATION_REQUEST;
        } else {
            return RequestType.UNKNOWN_REQUEST;
        }
    }

    private static boolean isStatRequest(String requestLine) {
        return requestLine.matches(statRequestRegex);
    }

    private static boolean isComputationRequest(String requestLine) {

        // check if the request line start with MAX_LIST, MIN_GRID ecc
        if (!requestLine.matches(computationRequestRegex)) return false;

        //request line has to have three tokens
        if (requestLine.split(";").length < 3)
            throw new WrongNumberOfArguments("Computation Request with wrong number of arguments. Expected at least 3 arguments" +
                    " but got " + requestLine.split(";").length);

        String variableValuesFunction = requestLine.split(";")[1];
        String[] variableValuesArray = variableValuesFunction.split(",");

        for (String variableValues : variableValuesArray) {

            if (variableValues.split(":").length != 4) {
                throw new WrongNumberOfArguments("Wrong number of variable parameters. Expected 4 but got " + variableValues.split(":").length);
            }

            String variableName = variableValues.split(":")[0];
            String minValue = variableValues.split(":")[1];
            String stepSize = variableValues.split(":")[2];
            String maxValue = variableValues.split(":")[3];

            if (!variableName.matches("[a-z][a-z0-9]*")) {
                throw new IllegalArgumentException("Bad syntax variable name");
            }

            if (!minValue.matches("(-)?[0-9]+(\\.[0-9]+)?")) {
                throw new IllegalArgumentException("Bad syntax min value");
            }

            if (!stepSize.matches("[0-9]+(\\.[0-9]+)?") || Double.parseDouble(stepSize) == 0.0) {
                throw new IllegalArgumentException("Bad syntax step size");
            }

            if (!maxValue.matches("(-)?[0-9]+(\\.[0-9]+)?")) {
                throw new IllegalArgumentException("Bad syntax max value");
            }

            if(Double.parseDouble(minValue)>Double.parseDouble(maxValue)){
                throw new IllegalArgumentException("Min value cannot be greater than max value");
            }

            //TODO: check step size value:
            //e.g. x0:-1:0.3:1 -> valore dello step size non va bene
        }
        return true;
    }

    private static boolean isQuitRequest(String requestLine) {
        return requestLine.equals("BYE");
    }

}
