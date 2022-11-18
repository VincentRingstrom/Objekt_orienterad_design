package se.kth.iv1350.seminar3.integration.data;

import java.util.ArrayList;
import java.util.List;

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
        customers.add(new Customer("Nahom", 21, 1, 0.25));
        customers.add(new Customer("Harry", 20, 2, 0.15));
        customers.add(new Customer("Maximilian", 19, 3, 0.12));
    }
    
    /**
     * Method is responsible for looking up a customers discount rate.
     * @param customerID - Is used to identify a specific customer.
     * @return - the discount rate (if none found, return 0).
     */
    public double getDiscountRate(int customerID){
        for(int i = 0; i < customers.size(); i++){
            if(customers.get(i).getCustomerID() == customerID){
                return customers.get(i).getDiscountRate();
            }
        }
        return 0;
    }
}
