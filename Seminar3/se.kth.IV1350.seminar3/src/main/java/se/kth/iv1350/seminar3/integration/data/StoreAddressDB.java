package se.kth.iv1350.seminar3.integration.data;

import se.kth.iv1350.seminar3.model.DTO.StoreAddressDTO;

/**
 * A (simulated) database of the store's address.
 */
public class StoreAddressDB {
    private String storeName;
    private String streetName;
    private int postalNumber;
    private String city;
    
    /**
     * Creates an instance consisting of a predefined address. 
     * @param streetName
     * @param postalNumber
     * @param city 
     */
    public StoreAddressDB(String storeName, String streetName, int postalNumber, String city){
        this.storeName = storeName;
        this.streetName = streetName;
        this.postalNumber = postalNumber;
        this.city = city;
    }
    
    /**
     * Creates a StoreAddressDTO to transfer the store's address.
     * @return Returns a StoreAddressDTO consisitng of data fetched from StoreAddress.
     */
    public StoreAddressDTO getStoreAddress(){
        StoreAddressDTO storeAddressDTO = new StoreAddressDTO(this.storeName, this.streetName, this.postalNumber, this.city);
        return storeAddressDTO;
    }
    
    /**
     * Reads the store's name.
     * @return - The read store name.
     */
    public String getStoreName(){ return this.storeName; }
    
    /**
     * Reads the street name.
     * @return - The read street name.
     */
    public String getStreetName(){ return this.streetName; }
    
    /**
     * Reads the store's postalnumber.
     * @return - The read postalnumber.
     */
    public int getPostalNumber(){ return this.postalNumber; }
    
    /**
     * Reads the city.
     * @return - The read city.
     */
    public String getCity(){ return this.city; }

}
