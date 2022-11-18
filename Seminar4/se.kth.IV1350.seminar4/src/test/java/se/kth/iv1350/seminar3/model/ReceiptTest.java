package se.kth.iv1350.seminar3.model;

import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.seminar3.controller.ConnectionTimedOut;

import se.kth.iv1350.seminar3.integration.data.AccountingSystemDB;
import se.kth.iv1350.seminar3.integration.data.InventorySystemDB;
import se.kth.iv1350.seminar3.integration.data.SaleLogDB;
import se.kth.iv1350.seminar3.integration.data.StoreAddressDB;

import se.kth.iv1350.seminar3.model.DTO.ReceiptDTO;

public class ReceiptTest {
    private Receipt receipt;
    
    private Payment payment;
    private Sale currentSale;
    private SaleLogDB saleLog;
    private InventorySystemDB invSys;
    
    private final String STORENAME = "Amigo";
    private final String STREETNAME = "Isafjordsgatan 22";
    private final int POSTALNUMBER = 16440;
    private final String CITY = "Stockholm";
    
    private final float AMOUNTPAID = 0.0f;
    
    public ReceiptTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        StoreAddressDB storeAddress = new StoreAddressDB(STORENAME, STREETNAME, POSTALNUMBER, CITY);
        try{
            this.receipt = new Receipt(storeAddress);
        } catch(ConnectionTimedOut e){
            System.out.println(e);
            fail(e);
        }
        AccountingSystemDB accSys = new AccountingSystemDB();
        this.payment = new Payment(AMOUNTPAID, accSys);
        try{
            this.currentSale = new Sale(storeAddress);
        } catch(ConnectionTimedOut e){
            System.out.println(e);
            fail(e);
        }
        this.saleLog = new SaleLogDB();
        this.invSys = new InventorySystemDB();
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testPrintReceipt() throws ConnectionTimedOut{
        try{ 
            ReceiptDTO result = receipt.printReceipt(payment, currentSale, saleLog, invSys);
            assertTrue(result instanceof ReceiptDTO, "A 'ReceiptDTO' was expected but never returned.");
        } catch(Payment.InsufficientFundsException e){
            System.out.println(e);
            fail(e);
        }
    }
}
