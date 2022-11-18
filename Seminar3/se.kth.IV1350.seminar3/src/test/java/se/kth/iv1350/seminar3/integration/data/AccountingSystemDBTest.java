package se.kth.iv1350.seminar3.integration.data;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AccountingSystemDBTest {
    private final float TOTALCOST = 100.0F;    

    @Test
    public void testUpdateAccountingDB() {
        AccountingSystemDB instanceToTest = new AccountingSystemDB();
        float tmpComparisonVariable = instanceToTest.getBalance();
        instanceToTest.updateAccountingDB(TOTALCOST);
        
        assertTrue(instanceToTest.getBalance() >= tmpComparisonVariable, "Accounting system data base was not correctly updated.");
        
    }
    
}
