package se.kth.iv1350.seminar3.model;

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
     * Creates an Item instanse.
     * @param name - The name of the instance
     * @param price - The price of the instance
     * @param VAT - The VAT of the instance
     * @param barCode - The barcode of the instance
     */
    public Item(int barCode, InventorySystemDB invSys){
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
