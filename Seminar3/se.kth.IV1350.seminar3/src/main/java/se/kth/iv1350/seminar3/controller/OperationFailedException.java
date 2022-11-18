package se.kth.iv1350.seminar3.controller;

public class OperationFailedException extends Exception {

    /**
     * Error if an operation fails.
     * @param errorMsg
     */
    public OperationFailedException(String errorMsg){
        super(errorMsg);
    }
}