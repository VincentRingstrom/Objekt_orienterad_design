package se.kth.iv1350.seminar3.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import se.kth.iv1350.seminar3.integration.data.AccountingSystemDB;
import se.kth.iv1350.seminar3.integration.data.CustomerDB;
import se.kth.iv1350.seminar3.integration.data.InventorySystemDB;
import se.kth.iv1350.seminar3.integration.data.SaleLogDB;
import se.kth.iv1350.seminar3.integration.data.StoreAddressDB;

import se.kth.iv1350.seminar3.model.DTO.ItemDTO;
import se.kth.iv1350.seminar3.model.DTO.ReceiptDTO;
import se.kth.iv1350.seminar3.model.DTO.SaleDTO;

import se.kth.iv1350.seminar3.model.Payment;
import se.kth.iv1350.seminar3.model.Receipt;
import se.kth.iv1350.seminar3.model.Sale;

public class ControllerTest {
    private Controller contr;
            
    private InventorySystemDB invSys;
    private CustomerDB custDB;
    private AccountingSystemDB accSys;
    private SaleLogDB saleLog;
    private StoreAddressDB storeAddress;
    
    private Receipt receipt;    
    private Sale currentSale;
    private Payment payment;
    
    private final int BARCODE = 10;
    private final int QUANTITY = 1;

    private final int CUSTOMERID = 1;
    
    private final float AMOUNT = -10.0f;
    
    public ControllerTest() {}
    
    @BeforeAll
    public static void setUpClass() {
        
    }
    
    @AfterAll
    public static void tearDownClass() {

    }
    
    @BeforeEach
    public void setUp() {
        this.saleLog = new SaleLogDB();
        this.storeAddress = new StoreAddressDB("Amigo", "Isafjordsgatan 22", 16440, "Stockholm");
        this.contr = new Controller(saleLog, storeAddress);
        currentSale = new Sale(storeAddress);
        contr.startSale();
        
    }
    
    @AfterEach
    public void tearDown() {
        
    }

    @Test
    public void testScan() {
        ItemDTO result = contr.scan(BARCODE, QUANTITY);
        assertFalse(result == null, "ItemDTO not created");
    }

    @Test
    public void testSignalDiscount() {
        double result = contr.signalDiscount(CUSTOMERID);
        assertTrue(result > 0, "Discount was not found for customer ID: " + CUSTOMERID);
    }

    @Test
    public void testEndSale() {
        SaleDTO result = contr.endSale();
        assertTrue(result instanceof SaleDTO, "A 'SaleDTO' was expected but never returned.");
    }

    /*
    This test, not relevant since a payment is done on a sale with zero items.
    Instead PaymentTest.java tests the payment more accurately.
    */
    @Test 
    public void testRegisterPayment() {
        ReceiptDTO result = contr.registerPayment(AMOUNT);
        assertTrue(result instanceof ReceiptDTO, "A 'ReceiptDTO' was expected but never returned.");
    }
    
}
