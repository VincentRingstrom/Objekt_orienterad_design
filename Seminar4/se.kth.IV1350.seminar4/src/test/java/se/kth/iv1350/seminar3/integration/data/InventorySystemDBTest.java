package se.kth.iv1350.seminar3.integration.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.seminar3.controller.ConnectionTimedOut;

import se.kth.iv1350.seminar3.model.DTO.ItemDTO;

/**
 * Tests InventorySystemDB. 
 * BARCODE = -1, simulates connectivity issues with the database.  
 */
public class InventorySystemDBTest {
    private final int BARCODE = 1; 
    
    @Test
    public void testRetriveItemDescriptionthrows() {
        InventorySystemDB instanceToTest = new InventorySystemDB();
        try{
            ItemDTO result = instanceToTest.retriveItemDescription(BARCODE);
            assertTrue(result != null, "The correct item was never found.");
        } catch(InventorySystemDB.InvalidItemBarCodeException | ConnectionTimedOut e){
            System.out.println(e);
            fail(e);
        }
    }
}
//throws InventorySystemDB.InvalidItemBarCodeException, ConnectionTimedOut
