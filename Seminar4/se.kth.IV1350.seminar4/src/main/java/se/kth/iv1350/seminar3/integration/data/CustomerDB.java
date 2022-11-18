package se.kth.iv1350.seminar3.integration.data;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.seminar3.controller.ConnectionTimedOut;

import se.kth.iv1350.seminar3.model.Customer;

/**
 * A (simulated) database of the store's customers.
 */
public class CustomerDB {
    private List<Customer> customers =  new ArrayList<>(); 

    /**
     * Initializes all database instances with predefined customers.
     */
    public CustomerDB(){
        customers.add(new Customer("Cark", 21, 1, 0.25));
        customers.add(new Customer("Harries", 20, 2, 0.15));
        customers.add(new Customer("Jack", 19, 3, 0.12));
    }
    
    /**
     * Method is responsible for looking up a customers discount rate.
     * @param customerID - Is used to identify a specific customer.
     * @return - the discount rate (if none found, return 0).
     * @throws ConnectionTimedOut is thrown if CustomerDB is not responding. 
     */
    public double getDiscountRate(int customerID) throws ConnectionTimedOut{
        if(customerID == -1){ throw new ConnectionTimedOut("");}
        
        for(int i = 0; i < customers.size(); i++){
            if(customers.get(i).getCustomerID() == customerID){
                return customers.get(i).getDiscountRate();
            }
        }
        return 0;
    }
}
