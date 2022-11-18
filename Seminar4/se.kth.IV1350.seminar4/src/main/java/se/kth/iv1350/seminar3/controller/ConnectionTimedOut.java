package se.kth.iv1350.seminar3.controller;

/**
 * Class throws an exception if any of the external systems are not responding.
 * The exception message  can be customised, if not, a default exception will be thrown.
 */
public class ConnectionTimedOut extends Exception {

    private String errorMsg = "Server connection timed out, check your internet connection and try again."; // THIS MESSAGE IS THROWN. WHEN CO
                                                                                                            // CONNECTION ERROR. 
    /**
     * Creates an throwable object if an operation fails.
     * @param errorMsg - Describes the cause of the problem. If not described, there is a default cause.
     */
    public ConnectionTimedOut(String errorMsg){
        if(!"".equals(errorMsg))
            this.errorMsg = errorMsg;
    }
    /**
     * Is automatically called and prints to screen.
     * @return - The error message.
     */
    @Override
    public String toString(){ return errorMsg; }
}