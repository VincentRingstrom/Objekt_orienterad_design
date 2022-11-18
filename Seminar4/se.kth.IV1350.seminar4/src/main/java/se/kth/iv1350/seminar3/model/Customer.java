package se.kth.iv1350.seminar3.model;

/**
 * Customer represent the customer during a sale.
 */
public class Customer {
    private String name;
    private int age;
    private int customerID;
    private double discountRate;
    
    /**
     * Creates an instance of the customer object.
     * @param name - The customer's name.
     * @param age - The customer's age.
     * @param customerID - The customer's ID.
     * @param discountRate - The customer's eligable discount rate.
     */
    public Customer(String name, int age, int customerID, double discountRate){
        this.name = name;
        this.age = age;
        this.customerID = customerID;
        this.discountRate = discountRate;
    }
    
    /**
     * Reads the customer's name.
     * @return - The read name.
     */
    public String getName(){ return this.name; }
    /**
     * Reads the customers age.
     * @return - The read age.
     */
    public int getage(){ return this.age; }
    /**
     * Reads the customerID.
     * @return - The read customerID.
     */
    public int getCustomerID(){ return this.customerID; }
    /**
     * Reads the eligable discount rate.
     * @return - The read discount rate.
     */
    public double getDiscountRate(){ return this.discountRate; }
}