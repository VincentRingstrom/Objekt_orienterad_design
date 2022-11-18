package se.kth.iv1350.seminar3.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.seminar3.controller.ConnectionTimedOut;

import se.kth.iv1350.seminar3.integration.data.AccountingSystemDB;
import se.kth.iv1350.seminar3.model.Payment.InsufficientFundsException;

public class PaymentTest {
    private AccountingSystemDB accSys;
    private Payment payment;
    
    private final float AMOUNTPAID = 252.1f; //200f;
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
    public void testGetChange() throws ConnectionTimedOut { 
        float nonAcceptableResult = -1.0f;
        try{
            float result = payment.getChange(TOTALCOST, DISCOUNTAMOUNT);
            assertTrue(result > nonAcceptableResult, "Insufficient funds.");
        } catch(InsufficientFundsException e){
            System.out.println(e);
            fail(e);
        }
    }
}
