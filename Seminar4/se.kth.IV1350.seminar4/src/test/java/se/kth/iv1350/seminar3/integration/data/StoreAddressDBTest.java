/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1350.seminar3.integration.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.seminar3.controller.ConnectionTimedOut;
import se.kth.iv1350.seminar3.model.DTO.StoreAddressDTO;

/**
 *
 */
public class StoreAddressDBTest {
    
    public StoreAddressDBTest() {
    }

    /**
     * Test of getStoreAddress method, of class StoreAddressDB.
     * Is mainly used to test exception handling, regarding simulated server connection issues. 
     */
    @Test
    public void testGetStoreAddress() throws Exception {
        boolean SERVERISONLINE = true;
        StoreAddressDB instanceToTest = new StoreAddressDB(SERVERISONLINE);
        try{
            StoreAddressDTO result = instanceToTest.getStoreAddress();
        } catch(ConnectionTimedOut e){
            System.out.println(e);
            fail(e);
        }
    }
}
