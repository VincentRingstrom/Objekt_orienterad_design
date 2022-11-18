package se.kth.iv1350.seminar3.integration.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CustomerDBTest {
    private final int CUSTOMERID = 10;

    @Test
    public void testGetDiscountRate() {
        CustomerDB instanceToTest = new CustomerDB();
        double result = instanceToTest.getDiscountRate(CUSTOMERID);
        assertTrue(result >= 0.0, "A non positive discount rate was returned.");
    }
    
}
