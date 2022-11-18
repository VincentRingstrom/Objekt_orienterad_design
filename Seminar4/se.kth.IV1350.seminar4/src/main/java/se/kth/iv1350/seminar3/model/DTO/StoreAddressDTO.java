package se.kth.iv1350.seminar3.model.DTO;

/**
 * This is a data transfer object (DTO), used to transfer data from the object StoreAddressDB.
 */
public class StoreAddressDTO {
    private final String storeName;
    private final String streetName;
    private final int postalNumber;
    private final String city;
    
    /**
     * Creates an instance of StoreAddressDTO with data fetched from the StoreAddressDB object.
     * @param storeName - The store's name.
     * @param streetName - A street name which is a part of the store's address.
     * @param postalNumber - A postal number which is a part of the store's address.
     * @param city - A city which is a part of the store's address.
     */
    public StoreAddressDTO(String storeName, String streetName, int postalNumber, String city){
        this.storeName = storeName;
        this.streetName = streetName;
        this. postalNumber = postalNumber;
        this.city = city;
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
}
