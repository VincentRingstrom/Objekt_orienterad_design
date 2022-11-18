package se.kth.iv1350.seminar3.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.seminar3.controller.ConnectionTimedOut;

import se.kth.iv1350.seminar3.integration.data.AccountingSystemDB;
import se.kth.iv1350.seminar3.integration.data.CustomerDB;
import se.kth.iv1350.seminar3.integration.data.InventorySystemDB;
import se.kth.iv1350.seminar3.integration.data.StoreAddressDB;

import se.kth.iv1350.seminar3.model.DTO.ItemDTO;
import se.kth.iv1350.seminar3.model.DTO.SaleDTO;

public class SaleTest {
    private Sale currentSale;
    
    private final String STORENAME = "Amigo";
    private final String STREETNAME = "Isafjordsgatan 22";
    private final int POSTALNUMBER = 16440;
    private final String CITY = "Stockholm";
    
    private final String NAME = "Red Milk";
    private final float PRICE = 12.5f;
    private final double VAT = 0.012;
    private final int BARCODE = 1;
    
    private final int QUANTITY = 1;
    
    private final int CUSTOMERID = 1;
    
    private final float AMOUNTPAID = 0.0f;
    
    public SaleTest() {
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
            this.currentSale = new Sale(storeAddress);
        } catch(ConnectionTimedOut e){
            System.out.println(e);
            fail(e);
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testAddItemToSale() {
        ItemDTO itemDTO = new ItemDTO(NAME, PRICE, VAT, BARCODE);
        currentSale.addItemToSale(itemDTO, QUANTITY);
        assertTrue(true, "ItemDTO wasn't correctly added in the shooping cart");
    }

    @Test
    public void testGetDiscountAmount() throws ConnectionTimedOut{
        CustomerDB custDB = new CustomerDB();
        double result = currentSale.getDiscountAmount(CUSTOMERID, custDB);
        assertTrue((result >= 0), "A non positive discount value was returned.");
    }

    @Test
    public void testDisplaySale() {
        SaleDTO result = currentSale.displaySale();
        assertTrue(result instanceof SaleDTO, "A 'SaleDTO' was expected but never returned.");
    }

    @Test
    public void testGetSaleInfo() throws ConnectionTimedOut{
        InventorySystemDB invSys = new InventorySystemDB();
        AccountingSystemDB accSys = new AccountingSystemDB();
        Payment payment = new Payment(AMOUNTPAID, accSys);
        try{
            SaleDTO result = currentSale.getSaleInfo(payment, invSys);
            assertTrue(result instanceof SaleDTO, "A 'SaleDTO' was expected but never returned.");
        } catch(Payment.InsufficientFundsException e){
            System.out.println(e);
            fail(e);
        }
    }
    
}
