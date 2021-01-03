package project.exceptions;

/**
 * @author Massimo Palmisano
 * ADVANCED PROGRAMMING PROJECT - Exception thrown when a string token doesn't have the expected number of parameters
 */
public class WrongNumberOfArguments extends RuntimeException {

    public WrongNumberOfArguments(){
        super("Wrong number of arguments");
    }

    public WrongNumberOfArguments(String message) {
        super(message);
    }

}
