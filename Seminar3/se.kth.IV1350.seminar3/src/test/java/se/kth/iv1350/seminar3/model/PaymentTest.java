package se.kth.iv1350.seminar3.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.seminar3.integration.data.AccountingSystemDB;

public class PaymentTest {
    private AccountingSystemDB accSys;
    private Payment payment;
    
    private final float AMOUNTPAID = 252.1f;
    private final float TOTALCOST = 252.2f;
    private final double DISCOUNTAMOUNT = 0.10;
    
    public PaymentTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        this.accSys = new AccountingSystemDB();
        this.payment = new Payment(AMOUNTPAID, accSys);
    }
    
    @AfterEach
    public void tearDown() {
        this.accSys = null;
        this.payment = null;
    }

    @Test
    public void testGetChange() { 
        float nonAcceptableResult = -1.0f;
        
        float result = payment.getChange(TOTALCOST, DISCOUNTAMOUNT);

        assertTrue(result > nonAcceptableResult, "Insufficient funds.");
    }
}
