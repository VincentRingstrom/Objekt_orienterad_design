package se.kth.iv1350.seminar3.model;

import se.kth.iv1350.seminar3.controller.ConnectionTimedOut;
import se.kth.iv1350.seminar3.integration.data.InventorySystemDB;

import se.kth.iv1350.seminar3.model.DTO.ItemDTO;

/**
 * Item represents an item in the store.
 */
public class Item {
    private String name;
    private float price;
    private double VAT;
    private int barCode;

    /**
     * Creates an Item instance.
     * @param barCode - Identifies the item instance.
     * @param invSys - An association to the InventorySystemDB.
     * @throws InventorySystemDB.InvalidItemBarCodeException if there is no maching barcode. 
     * @throws ConnectionTimedOut if InventorySystemDB is not responding.
     */
    public Item(int barCode, InventorySystemDB invSys) throws InventorySystemDB.InvalidItemBarCodeException, ConnectionTimedOut{
        this.barCode = barCode;

        ItemDTO temporalDTO = invSys.retriveItemDescription(barCode);
        this.name = temporalDTO.getName();
        this.price = temporalDTO.getPrice();
        this.VAT = temporalDTO.getVAT();
        this.barCode = temporalDTO.getBarCode();
    }
    
   /**
     * This method reads Item's name.
     * @return - The name.
     */
    public String getName(){ return this.name; }
    /**
     * This method reads Item's price.
     * @return - The price.
     */
    public float getPrice(){ return this.price; }
    /**
     * This method reads Item's VAT.
     * @return - The VAT.
     */
    public double getVAT(){ return this.VAT; }
    /**
     * This method reads Item's barcode.
     * @return - The barcode.
     */
    public int getBarCode(){ return this.barCode; }
}
