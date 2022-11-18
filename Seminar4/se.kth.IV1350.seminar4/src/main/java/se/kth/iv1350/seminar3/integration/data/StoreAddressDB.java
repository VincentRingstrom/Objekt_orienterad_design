package se.kth.iv1350.seminar3.integration.data;

import se.kth.iv1350.seminar3.controller.ConnectionTimedOut;
import se.kth.iv1350.seminar3.model.DTO.StoreAddressDTO;

/**
 * A (simulated) database of the store's address. SIMULATED database. 
 */
public class StoreAddressDB {
    private boolean SERVERISONLINE = true;
    private String storeName;
    private String streetName;
    private int postalNumber;
    private String city;
    
    /**
     * Creates an instance consisting of a predefined address. 
     * @param storeName - the name of the store.
     * @param streetName - the name of the street which the store is on.
     * @param postalNumber - the store's postalnumber. 
     * @param city - the name of the city which the store is located in.
     */
    public StoreAddressDB(String storeName, String streetName, int postalNumber, String city){
        this.storeName = storeName;
        this.streetName = streetName;
        this.postalNumber = postalNumber;
        this.city = city;
    }
    /**
     * Creates an instance of SaleLogDB, only used for testing the class and is
     * NOT a part of the implementation.
     * @param SERVERISONLINE - Decides whether the simulated server is on/off.
     */
    public StoreAddressDB(boolean SERVERISONLINE){
        this.SERVERISONLINE = SERVERISONLINE;
    }

    /**
     * Creates a StoreAddressDTO to transfer the store's address.
     * @return Returns a StoreAddressDTO consisitng of data fetched from StoreAddress.
     * @throws ConnectionTimedOut is thrown if InventorySystemDB is not responding.
     */
    public StoreAddressDTO getStoreAddress() throws ConnectionTimedOut{
        if(SERVERISONLINE == false){ throw new ConnectionTimedOut("");}
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
