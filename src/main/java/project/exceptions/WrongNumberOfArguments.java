/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.exceptions;

/**
 *
 * @author massi
 */
public class WrongNumberOfArguments extends RuntimeException {

    public WrongNumberOfArguments(){
        super("Wrong number of arguments");
    }

    public WrongNumberOfArguments(String message) {
        super(message);
    }

}
