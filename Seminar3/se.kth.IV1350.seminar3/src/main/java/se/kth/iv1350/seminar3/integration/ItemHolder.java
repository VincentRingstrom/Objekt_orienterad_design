package se.kth.iv1350.seminar3.integration;
import se.kth.iv1350.seminar3.model.DTO.ItemDTO;

/**
 * ItemHolder's purpose is to store an ItemDTO along with its quantity.
 * This object itself, is later stored in a list in Sale. 
 */
public class ItemHolder {
    private ItemDTO itemDTO;
    private int quantity;

    /**
     * Creates an instance of ItemHolder.
     * @param itemDTO - Stores an ItemDTO instance.
     * @param quantity - Stores the registered quantity of that ItemDTO instance.
     */
    public ItemHolder(ItemDTO itemDTO, int quantity){
        this.itemDTO = itemDTO;
        this.quantity = quantity;
    }
    
    /**
     * Retrives the name of the stored item by calling "getName()" in ItemDTO.
     * @return - Item name.
     */
    public String getName(){ return itemDTO.getName(); }
    /**
     * This method retrives ItemDTO's price by calling "getPrice()" in ItemDTO.
     * @return The retrived price.
     */
    public float getPrice(){ return itemDTO.getPrice(); }
    /**
     * This method retrives ItemDTO's VAT by calling "getVAT()" in ItemDTO.
     * @return The retrived VAT.
     */
    public double getVAT(){ return itemDTO.getVAT(); }
    /**
     * This method retrives ItemDTO's barcode by calling "getBarCode()" in ItemDTO.
     * @return The retrived barcode.
     */
    public int getBarCode(){ return itemDTO.getBarCode(); }
    /**
     * Reads the quantity.
     * @return The read quantity.
     */
    public int getItemQuantity() { return this.quantity; }
}
