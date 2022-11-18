package se.kth.iv1350.seminar3.controller;

import java.util.ArrayList;
import java.util.List;
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
import se.kth.iv1350.seminar3.model.AmountPaidObserver;
import se.kth.iv1350.seminar3.model.Payment.InsufficientFundsException;

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
    
    private List<AmountPaidObserver> listOfAmountPaidObserver = new ArrayList<AmountPaidObserver>();

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
        try{
            this.receipt = new Receipt(storeAddress);
        } catch(ConnectionTimedOut e){
            System.out.println("[Logging to file]: " + "'" + e + "'");
            System.out.println(e);
        }
    }
    
    /**
     * Starts a new sale. This method must be called before doing anything else during a sale.
     * @throws ConnectionTimedOut if StoreAddressDB is not responding.
     */
    public void startSale() throws ConnectionTimedOut{
        currentSale = new Sale(storeAddress);
    }
    
    /**
     * 1) Creates an Item instance and then sends info of the item back to cashier.
     * 2) Stores the item in Sale. 
     * @param barCode
     * @param quantity
     * @return - Returns an ItemDTO consisting of data fetched from Item. 
     * @throws se.kth.iv1350.seminar3.integration.data.InventorySystemDB.InvalidItemBarCodeException if an unrecognized item is scanned. 
     * @throws ConnectionTimedOut if InventorySystemDB is not responding.
     */
    public ItemDTO scan(int barCode, int quantity) throws InventorySystemDB.InvalidItemBarCodeException, ConnectionTimedOut{
        Item item = new Item(barCode, invSys);
        
        ItemDTO itemDTO = new ItemDTO(item.getName(), item.getPrice(), item.getVAT(), item.getBarCode());
        currentSale.addItemToSale(itemDTO, quantity);
        
        return itemDTO;
    }
    
    /**
     * Method is mainly responsible for checking if a customer has a discount registered in the store's database.
     * @param customerID - Represents the customer in the store's customer-database.
     * @return - The amount of discount eligible, (if none return zero).
     * @throws ConnectionTimedOut if CustomerDB is not responding.
     */
    public double signalDiscount(int customerID) throws ConnectionTimedOut{
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
     * @throws ConnectionTimedOut if AccountingSystemDB is not responding.
     * @throws InsufficientFundsException if user has not paid the full price of the sale.
     */
    public ReceiptDTO registerPayment(float amount) throws ConnectionTimedOut, InsufficientFundsException{
        this.payment = new Payment(amount, accSys);
        payment.addAmountPaidObservers(listOfAmountPaidObserver); // ADD THE ListofAmountPAidObersers to the addAmountPaidObservers.
        
       
        
        ReceiptDTO receiptDTO = receipt.printReceipt(this.payment, this.currentSale, this.saleLog, this.invSys);
        return receiptDTO;
    }
    
    public void registerObserver(AmountPaidObserver obs){
        listOfAmountPaidObserver.add(obs); // REGISTER OBSERVER HERE. ADD OBS HERE. IN list of Observers. REGISTER A NEW OBSERVER.
    }
}
