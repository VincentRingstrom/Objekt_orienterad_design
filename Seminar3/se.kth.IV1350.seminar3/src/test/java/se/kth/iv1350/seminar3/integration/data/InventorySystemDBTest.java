package se.kth.iv1350.seminar3.integration.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.seminar3.model.DTO.ItemDTO;

public class InventorySystemDBTest {
    private final int BARCODE = 1;
    
    @Test
    public void testRetriveItemDescription() {
        InventorySystemDB instanceToTest = new InventorySystemDB();
        ItemDTO result = instanceToTest.retriveItemDescription(BARCODE);
        assertTrue(result != null, "The correct item was never found.");
    }
}
