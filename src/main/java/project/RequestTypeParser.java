package project;

import java.util.Arrays;

public class RequestTypeParser {

    RequestType requestType;
    String requestLine;
    String errorMessage;
    String computationRequestRegex = "(MAX|MIN|AVG|COUNT)_(LIST|GRID)";
    String statRequestRegex = "STAT_(REQS|AVG|MAX)(_TIME)?";


    public RequestTypeParser(String requestLine) {
        this.requestLine = requestLine;

        // set the correct request type from the request line
        if (isQuitRequest()) {
            setRequestType(RequestType.QUIT_REQUEST);
        } else if (isStatRequest()) {
            setRequestType(RequestType.STAT_REQUEST);
        } else if (isComputationRequest()) {
            setRequestType(RequestType.COMPUTATION_REQUEST);
        } else {
            setRequestType(RequestType.UNKNOWN_REQUEST);
        }
    }

    private boolean isStatRequest() {
        return requestLine.matches(statRequestRegex);
    }

    private boolean isComputationRequest() {
        return requestLine.matches(computationRequestRegex);
    }

    private boolean isQuitRequest() {
        return requestLine.equals("BYE");
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public RequestType getRequestType() {
        return requestType;
    }

}
