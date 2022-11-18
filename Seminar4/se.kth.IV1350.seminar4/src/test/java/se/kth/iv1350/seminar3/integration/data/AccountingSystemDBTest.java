package se.kth.iv1350.seminar3.integration.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.seminar3.controller.ConnectionTimedOut;

/**
 * Tests AccountingSystemDB.
 * TOTALCOST = -1, simulates connectivity issues with the database. 
 */
public class AccountingSystemDBTest {
    private final float TOTALCOST = 100.0f;    

    @Test
    public void testUpdateAccountingDB() {
        AccountingSystemDB instanceToTest = new AccountingSystemDB();
        try{
            float tmpComparisonVariable = instanceToTest.getBalance();
            instanceToTest.updateAccountingDB(TOTALCOST);
            assertTrue(instanceToTest.getBalance() >= tmpComparisonVariable, "Accounting system data base was not correctly updated.");

        } catch(ConnectionTimedOut e){
            System.out.println(e);
            fail(e);
        }
        
    }
    
}
