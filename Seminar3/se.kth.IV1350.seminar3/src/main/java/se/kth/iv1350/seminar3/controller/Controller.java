package se.kth.iv1350.seminar3.controller;

import se.kth.iv1350.seminar3.model.DTO.ItemDTO;
import se.kth.iv1350.seminar3.model.DTO.SaleDTO;
import se.kth.iv1350.seminar3.model.DTO.ReceiptDTO;

import se.kth.iv1350.seminar3.model.Item;
import se.kth.iv1350.seminar3.model.Sale;
import se.kth.iv1350.seminar3.model.Receipt;
import se.kth.iv1350.seminar3.model.Payment;

import se.kth.iv1350.seminar3.integration.data.AccountingSystemDB;
import se.kth.iv1350.seminar3.integration.data.CustomerDB;
import se.kth.iv1350.seminar3.integration.data.InventorySystemDB;
import se.kth.iv1350.seminar3.integration.data.SaleLogDB;
import se.kth.iv1350.seminar3.integration.data.StoreAddressDB;

/**
 * This is the application's only controller. All calls to the model pass through this class.
 */
public class Controller {    
    private InventorySystemDB invSys;
    private CustomerDB custDB;
    private AccountingSystemDB accSys;
    private SaleLogDB saleLog;
    private StoreAddressDB storeAddress;
    
    private Receipt receipt;    
    private Sale currentSale;
    private Payment payment;
    
    /**
     * Creates an instance of controller and stores relevant object instances of other classes.
     * @param saleLog - This class instance is stored to be used later.
     * @param storeAddress - This class instance is stored to be used later. 
     */
    public Controller(SaleLogDB saleLog, StoreAddressDB storeAddress){
        
        this.invSys = new InventorySystemDB();
        
        this.custDB = new CustomerDB();
        
        this.accSys = new AccountingSystemDB();
        
        this.saleLog = saleLog;
        
        this.storeAddress = storeAddress;

        this.receipt = new Receipt(storeAddress);
    }
    
    /**
     * Starts a new sale. This method must be called before doing anything else during a sale.
     */
    public void startSale(){
        currentSale = new Sale(storeAddress);
    }
    
    /**
     * 1) Creates an Item instance and then sends info of the item back to cashier.
     * 2) Stores the item in Sale. 
     * @param barCode - Is used by the Item constructor to determine Item type. 
     * @param quantity - Is used by Sale to determine the quantity of the scanned Item.
     * @return - Returns an ItemDTO consisting of data fetched from Item. 
     */
    public ItemDTO scan(int barCode, int quantity){
        Item item = new Item(barCode, invSys);
        
        ItemDTO itemDTO = new ItemDTO(item.getName(), item.getPrice(), item.getVAT(), item.getBarCode());
        currentSale.addItemToSale(itemDTO, quantity);
        
        return itemDTO;
    }
    
    /**
     * Method is mainly responsible for checking if a customer has a discount registered in the store's database.
     * @param customerID - Represents the customer in the store's customer-database.
     * @return - The amount of discount eligible, (if none return zero).
     */
    public double signalDiscount(int customerID){
        return currentSale.getDiscountAmount(customerID, custDB);
    }
    
    /**
     * Concludes the sale by confirming the scanned items are correct before moving on to payment. 
     * @return - Returns a SaleDTO consisting of data fetched from Sale.
     */
    public SaleDTO endSale(){
        SaleDTO concludedSale = currentSale.displaySale();
        return concludedSale;
    }
    
    /**
     * Evaluates if the customer's payment is valid. 
     * @param amount - The amount paid by the customer.
     * @return - A receipt of type ReceiptDTO.
     */
    public ReceiptDTO registerPayment(float amount){
        this.payment = new Payment(amount, accSys);
        
        ReceiptDTO receiptDTO = receipt.printReceipt(this.payment, this.currentSale, this.saleLog, this.invSys);
        return receiptDTO;
    }
}
