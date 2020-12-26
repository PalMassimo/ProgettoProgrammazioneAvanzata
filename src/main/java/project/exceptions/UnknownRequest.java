package project.exceptions;

public class UnknownRequest extends Exception {
    public UnknownRequest() {
        super("Unknown Request");
    }

    public UnknownRequest(String message) {
        super(message);
    }
}
