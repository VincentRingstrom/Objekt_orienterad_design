package se.kth.iv1350.seminar3.startup;

import se.kth.iv1350.seminar3.controller.Controller;
import se.kth.iv1350.seminar3.view.View;

import se.kth.iv1350.seminar3.integration.data.SaleLogDB;
import se.kth.iv1350.seminar3.integration.data.StoreAddressDB;

/**
 * Starts the entire application. Contains the main method used to start the application.
 */
public class Main {
    
    /**
    * The main method used to start the entire application.
    * @param args - The application does not take any command line parameters.
    */
    public static void main(String[] args){
        
        SaleLogDB saleLog = new SaleLogDB();
        
        StoreAddressDB storeAddress = new StoreAddressDB("Amigo", "Isafjordsgatan 22", 16440, "Stockholm");
        
        Controller contr = new Controller(saleLog, storeAddress);
        
        View view = new View(contr);
        
        //Starts simulation
        view.runFakeExecution();
    
    }
}
