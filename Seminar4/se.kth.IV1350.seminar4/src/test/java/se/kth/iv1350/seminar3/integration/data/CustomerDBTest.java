package se.kth.iv1350.seminar3.integration.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.seminar3.controller.ConnectionTimedOut;

/**
 * Tests CustomerDB. 
 * CUSTOMERID = -1, simulates connectivity issues with the database.  
 */
public class CustomerDBTest {
    private final int CUSTOMERID = 1;

    @Test
    public void testGetDiscountRate() {
        CustomerDB instanceToTest = new CustomerDB();
        try{
            double result = instanceToTest.getDiscountRate(CUSTOMERID);
            assertTrue(result >= 0.0, "A non positive discount rate was returned.");
        } catch(ConnectionTimedOut e){
            System.out.println(e);
            fail(e);
        }
    }
}
