package se.kth.iv1350.seminar3.model.DTO;

/**
 * This is a data transfer object (DTO), used to transfer data from the object Receipt.
 */
public class ReceiptDTO {
    private final SaleDTO concludedSaleDTO;
    private final String storeName;
    private final String streetName;
    private final int postalNumber;
    private final String city;    
    private final java.time.LocalDate saleDate = java.time.LocalDate.now();
    
    /**
     * Creates an ReceiptDTO instanse that is read only and used to transfer Receipt data.
     * @param concludedSaleDTO - A saleDTO of the ongoing sale.
     * @param storeName - The store's name.
     * @param streetName - A street name which is a part of the store's address.
     * @param postalNumber - A postal number which is a part of the store's address.
     * @param city - A city which is a part of the store's address.
     * @param saleDate - The date the sale was initialised.
     */
    public ReceiptDTO(SaleDTO concludedSaleDTO, String storeName, String streetName, int postalNumber, String city, java.time.LocalDate saleDate){
        this.concludedSaleDTO = concludedSaleDTO;
        this.storeName = storeName;
        this.streetName = streetName;
        this.postalNumber = postalNumber;
        this.city = city;
    }
    
    /**
     * Reads SaleDTO.
     * @return - The SaleDTO.
     */
    public SaleDTO getSaleDTO(){ return this.concludedSaleDTO; }
    /**
     * Reads the name of the store.
     * @return - The read name.
     */
    public String getStoreName(){ return this.storeName; }
    /**
     * Reads the name of the street which the store is located on.
     * @return - The read street name.
     */
    public String getStreetName(){ return this.streetName; }
    /**
     * Reads the postal number.
     * @return - The read postal number.
     */
    public int getPostalNumber(){ return this.postalNumber; }
    /**
     * Reads the city.
     * @return - The read city.
     */
    public String getCity(){ return this.city; }
    /**
     * Reads the date of when the sale was initialized.
     * @return - The read date.
     */
    public java.time.LocalDate getSaleDate(){ return this.saleDate; }
}
