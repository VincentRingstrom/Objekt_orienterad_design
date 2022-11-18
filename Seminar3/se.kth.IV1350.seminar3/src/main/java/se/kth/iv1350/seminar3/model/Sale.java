package se.kth.iv1350.seminar3.model;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.seminar3.model.Receipt;

import se.kth.iv1350.seminar3.integration.ItemHolder;

import se.kth.iv1350.seminar3.integration.data.StoreAddressDB;
import se.kth.iv1350.seminar3.integration.data.CustomerDB;
import se.kth.iv1350.seminar3.integration.data.InventorySystemDB;

import se.kth.iv1350.seminar3.model.DTO.ItemDTO;
import se.kth.iv1350.seminar3.model.DTO.SaleDTO;

/**
 * Stores all events during a sale.
 */
public class Sale {
    private Receipt receipt;
    //private Payment payment;
    private List<ItemHolder> shoppingCart = new ArrayList<>(); 
    private double discountAmount;
    private float totalPrice;
    private double totalVAT;
    private float change;

    
    /**
     * Creates a sale instance.
     * @param storeAdr 
     */
    public Sale(StoreAddressDB storeAddress){
        this.receipt = new Receipt(storeAddress);
    }
    
    //public Receipt getReceiptInstanse(){ return this.receipt; }
    
    /**
     * Adds a specific item and its quantity to the sale.
     * @param itemDTO - Is the item.
     * @param quantity - Quantity of the item.
     */
    public void addItemToSale(ItemDTO itemDTO, int quantity){
        shoppingCart.add((new ItemHolder(itemDTO, quantity)));
    }
    
    /**
     * Fetches eligable discount rate from CustomerDB.
     * @param customerID - Used to identify the customer in the database.
     * @param custDB - A referens to the DB. 
     * @return - Eligable discount rate.
     */
    public double getDiscountAmount(int customerID, CustomerDB custDB){
        this.discountAmount = custDB.getDiscountRate(customerID);
        return this.discountAmount;
    }
    
    
    
    /**
     * Prepares the sale to be displayed by calculating the preliminary total price.
     * In this stage the customer has a last chance of adding or removing items.
     * For this exercise the customer is assumed to be happy with the conclution,
     * thus no edit in the sale done.
     * @return - Returns the sale in form of a SaleDTO.
     */
    public SaleDTO displaySale(){
        calculatePriceAndVAT();
        SaleDTO concludedSale = new SaleDTO(this.shoppingCart, this.discountAmount, this.totalPrice, this.totalVAT, this.change);
        return concludedSale;
    }
    private void calculatePriceAndVAT(){
        for(int i = 0; i < shoppingCart.size(); i++){
            this.totalPrice += (shoppingCart.get(i).getPrice()) * (shoppingCart.get(i).getItemQuantity());
            this.totalVAT += (shoppingCart.get(i).getVAT()) * (shoppingCart.get(i).getItemQuantity());
        }
    }
    
    /**
     * Summorises the sale, this time with final values. 
     * @param payment - A referens to the payment object.
     * @param invSys - A referens to the inventory system.
     * @return - The final sale in form of a SaleDTO.
     */
    public SaleDTO getSaleInfo(Payment payment, InventorySystemDB invSys){
        this.change = payment.getChange(this.totalPrice, this.discountAmount);
        if(this.change == -1){
            //ERROR ocurred in Payment during getChange(...);
        }
        SaleDTO completeSaleDTO = new SaleDTO(this.shoppingCart, this.discountAmount, this.totalPrice, this.totalVAT, this.change);
        
        invSys.updateInventory(completeSaleDTO);
        
        return completeSaleDTO;
    }
    
}
