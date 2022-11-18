package se.kth.iv1350.seminar3.model;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.seminar3.controller.ConnectionTimedOut;

import se.kth.iv1350.seminar3.model.Receipt;

import se.kth.iv1350.seminar3.integration.ItemHolder;

import se.kth.iv1350.seminar3.integration.data.StoreAddressDB;
import se.kth.iv1350.seminar3.integration.data.CustomerDB;
import se.kth.iv1350.seminar3.integration.data.InventorySystemDB;

import se.kth.iv1350.seminar3.model.DTO.ItemDTO;
import se.kth.iv1350.seminar3.model.DTO.SaleDTO;
import se.kth.iv1350.seminar3.model.Payment.InsufficientFundsException;

/**
 * Stores all events during a sale.
 */
public class Sale {
    private Receipt receipt;
    private List<ItemHolder> shoppingCart = new ArrayList<>(); 
    private double discountAmount;
    private float totalPrice;
    private double totalVAT;
    private float change;

    
    /**
     * Creates a sale instance.
     * @param storeAddress the address of the store. 
     * @throws ConnectionTimedOut if StoreAddressDB is not responding.
     * // THROW ERROR IF StoreAddressDB not responding. 
     */
    public Sale(StoreAddressDB storeAddress) throws ConnectionTimedOut{
        this.receipt = new Receipt(storeAddress);
    }
        
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
     * @throws ConnectionTimedOut if CustomerDB is not responding. 
     */
    public double getDiscountAmount(int customerID, CustomerDB custDB) throws ConnectionTimedOut{
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
     * @throws InsufficientFundsException if the customer has not paid the full price of the sale.
     * @throws ConnectionTimedOut if AccountingSystemDB is not responding.
     */
    public SaleDTO getSaleInfo(Payment payment, InventorySystemDB invSys) throws InsufficientFundsException, ConnectionTimedOut{
        this.change = payment.getChange(this.totalPrice, this.discountAmount);

        SaleDTO completeSaleDTO = new SaleDTO(this.shoppingCart, this.discountAmount, this.totalPrice, this.totalVAT, this.change);
        
        invSys.updateInventory(completeSaleDTO);
        
        return completeSaleDTO;
    }
    
}
