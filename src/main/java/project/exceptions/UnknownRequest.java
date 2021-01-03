package project.exceptions;

/**
 * @author Massimo Palmisano
 * ADVANCED PROGRAMMING PROJECT - Exception that is thrown when server is unable to understand which is the request type
 */
public class UnknownRequest extends Exception {
    public UnknownRequest() {
        super("Unknown Request");
    }

    public UnknownRequest(String message) {
        super(message);
    }
}
