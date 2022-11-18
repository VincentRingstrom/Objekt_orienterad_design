package se.kth.iv1350.seminar3.model;

import se.kth.iv1350.seminar3.integration.data.StoreAddressDB;

import se.kth.iv1350.seminar3.model.DTO.StoreAddressDTO;
import se.kth.iv1350.seminar3.model.DTO.SaleDTO;
import se.kth.iv1350.seminar3.model.DTO.ReceiptDTO;

import se.kth.iv1350.seminar3.integration.data.InventorySystemDB;
import se.kth.iv1350.seminar3.integration.data.SaleLogDB;

/**
 * Represents one receipt, which proves the payment of one sale.
 */
public class Receipt{
    private SaleDTO concludedSaleDTO;
    private String storeName;
    private String streetName;
    private int postalNumber;
    private String city;    
    private java.time.LocalDate saleDate = java.time.LocalDate.now();
    
    private float change;
    
    /**
     * Creates an instance of Receipt with information already known.
     * @param storeAdr - The address of the store.
     */
    public Receipt(StoreAddressDB storeAddress){
        StoreAddressDTO temporalDTO = storeAddress.getStoreAddress();
        this.storeName = temporalDTO.getStoreName();
        this.streetName = temporalDTO.getStreetName();
        this.postalNumber = temporalDTO.getPostalNumber();
        this.city = temporalDTO.getCity();
    }
   
    /**
     * Print receipt prints a receipt that summarizes the sale.
     * @param payment - Details about the payment.
     * @param currentSale - Details about the sale.
     * @param saleLog - An saleLog instance to log the receipt.
     * @param invSys - Used to update the inventory system once the sale is complete.
     * @return - Returns a receiptDTO consisitng of data fetched from Receipt and additional parameters.
     */
    public ReceiptDTO printReceipt(Payment payment, Sale currentSale, SaleLogDB saleLog, InventorySystemDB invSys){
        SaleDTO concludedSaleDTO = currentSale.getSaleInfo(payment, invSys);
        this.concludedSaleDTO = concludedSaleDTO;
        
        ReceiptDTO receitDTO = new ReceiptDTO(this.concludedSaleDTO, this.storeName, this.streetName, this.postalNumber, this.city, this.saleDate);
        
        saleLog.storeSaleLog(concludedSaleDTO);
    
        return receitDTO;
        //concludedSaleDTO.getChange()
    }
    
    /**
     * This method reads the store's name.
     * @return - The store name.
     */
    public String getStoreName(){ return this.storeName; }
    /**
     * This method reads the street name.
     * @return - The street name.
     */
    public String getStreetName(){ return this.streetName; }
    /**
     * This method reads postal number.
     * @return - The postal number.
     */
    public int getPostalNumber(){ return this.postalNumber; }
    /**
     * This method reads the city.
     * @return - The city.
     */
    public String getCity(){ return this.city; }
    /**
     * This method reads the current date.
     * @return - The current date the sale was initiated.
     */
    public java.time.LocalDate getSaleDate(){ return this.saleDate; }
    
    
}
