package se.kth.iv1350.seminar3.view;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.seminar3.controller.ConnectionTimedOut;

import se.kth.iv1350.seminar3.controller.Controller;
//import se.kth.iv1350.seminar3.integration.TotalRevenueFileOutput;
import se.kth.iv1350.seminar3.integration.data.InventorySystemDB;

import se.kth.iv1350.seminar3.model.DTO.ItemDTO;
import se.kth.iv1350.seminar3.model.DTO.SaleDTO;
import se.kth.iv1350.seminar3.model.DTO.ReceiptDTO;
import se.kth.iv1350.seminar3.model.Payment;

/**
 * This is a placeholder for the real view. It contains a hard coded execution
 * with calls to all system operations in the controller.
 */
public class View {

    private static final int CUSTOMERID = 1; //Finns customers upp till och med 3.
    private static final int BARCODE = 1; //Finns items upp till och med 10. BARCODE 1. 
    private static final int QUANTITY = 5;
    private static final int AMOUNT = 512;

    private Controller contr;

    //private List<AmountPaidObserver> listOfAmountPaidObserver;
    /**
     * Creates a new instance, that uses the specified controller for all calls
     * to other layers.
     *
     * @param contr the controller to use for all calls to other layers.
     */
    public View(Controller contr) {
        this.contr = contr;
        contr.registerObserver(new TotalRevenueView());
        contr.registerObserver(new TotalRevenueFileOutput());
    }

    /**
     * Reads the list of all observers.
     *
     * @return the read list.
     *
     * public List<AmountPaidObserver> getListOfAmountPaidObserver() { return
     * listOfAmountPaidObserver; } /** Sets the list with a list consisting of
     * observers.
     * @param listOfAmountPaidObserver the list that will be stored.
     *
     * public void setListOfAmountPaidObserver(List<AmountPaidObserver>
     * listOfAmountPaidObserver) { this.listOfAmountPaidObserver =
     * listOfAmountPaidObserver; } /** Takes one observer and adds it to the
     * observer list.
     * @param amountPaidObserver the observer object to be added.
     *
     * @Override public void registerObserver(AmountPaidObserver
     * amountPaidObserver) { listOfAmountPaidObserver.add(amountPaidObserver); }
     * /** Removes a specific observer object from the observer list.
     * @param amountPaidObserver the object to remove.
     *
     * @Override public void removeObserver(AmountPaidObserver
     * amountPaidObserver) {
     * listOfAmountPaidObserver.remove(amountPaidObserver);
    }
     */
    /**
     * Performs a fake sale, by calling all system operations in the controller.
     */
    public void runFakeExecution() {             // THIS IS WRONG MY MAN. 

        /*
        try{
            contr.startSale();
            System.out.println("[A new sale has been started]");
        } catch(ConnectionTimedOut e){ handleException(e); }
        
        System.out.println("\n[Scanning items]");
        try{
            ItemDTO itemDTO = contr.scan(BARCODE,QUANTITY);
            printScannedItem(itemDTO);
        
        } catch(InventorySystemDB.InvalidItemBarCodeException|ConnectionTimedOut e){ handleException(e); }
        
        System.out.println("\n[Is checking for discount]");
        try{
            double discountRate = contr.signalDiscount(CUSTOMERID);
            printFoundDiscount(discountRate);
        } catch(ConnectionTimedOut e){ handleException(e); }
        
        System.out.println("\n[Ending sale and showing a conclusion]");
        SaleDTO concludedSale = contr.endSale();
        printConcludedSale(concludedSale);
    
        System.out.println("\n[Printing Receipt]");
        try{
            float amount = AMOUNT;
            ReceiptDTO receiptDTO = contr.registerPayment(amount);
            printReceipt(receiptDTO, concludedSale);
        } catch(Payment.InsufficientFundsException|ConnectionTimedOut e){ handleException(e); }
         */
        
        
        try {
            contr.startSale();
            System.out.println("[A new sale has been started]");
            System.out.println("\n[Scanning items]");

            ItemDTO itemDTO = contr.scan(BARCODE, QUANTITY);

            System.out.println("\n[Is checking for discount]");
            
            double discountRate = contr.signalDiscount(CUSTOMERID);
            printFoundDiscount(discountRate);
            
            System.out.println("\n[Ending sale and showing a conclusion]");
            SaleDTO concludedSale = contr.endSale();
            printConcludedSale(concludedSale);
            
            System.out.println("\n[Printing Receipt]");
            float amount = AMOUNT;
            ReceiptDTO receiptDTO = contr.registerPayment(amount);
            printReceipt(receiptDTO, concludedSale);

        } catch (Payment.InsufficientFundsException | InventorySystemDB.InvalidItemBarCodeException | ConnectionTimedOut e) {
            handleException(e);
        }

    }

    private void handleException(Exception e) {
        System.out.println("[Logging to file]: " + "'" + e + "'");
        System.out.println(e);
    }

    private void printScannedItem(ItemDTO itemDTO) {
        System.out.println("Item(s):" + "\t" + "Price:" + "\t" + "Tax[%]:" + "\t" + "Quantity:" + "\n"
                + itemDTO.getName() + "\t" + itemDTO.getPrice() + "\t" + (itemDTO.getVAT() * 100) + "\t" + QUANTITY);
    }

    private void printFoundDiscount(double discountRate) {
        System.out.println("Eligable discount: " + (discountRate * 100.00) + "%");
    }

    private void printConcludedSale(SaleDTO concludedSale) {
        printItems(concludedSale);
        System.out.println("Items cost: " + "\t" + concludedSale.getTotalPrice() + "SEK" + "\t " + "Total VAT: " + (concludedSale.getTotalVAT() * 100) + "%");
    }

    private void printItems(SaleDTO concludedSale) {
        for (int i = 0; i < concludedSale.getShoppingCart().size(); i++) {
            System.out.println(concludedSale.getShoppingCart().get(i).getName() + "\t"
                    + concludedSale.getShoppingCart().get(i).getPrice() + "SEK " + "\t"
                    + "VAT: " + concludedSale.getShoppingCart().get(i).getVAT() * 100 + "%" + "\t");
        }
    }

    private void printReceipt(ReceiptDTO receiptDTO, SaleDTO concludedSale) {
        printIntro();
        printConcludedSale(concludedSale);
        printOutro(receiptDTO);
    }

    private void printIntro() {
        System.out.println("Thanks for your purchase!");
    }

    private void printOutro(ReceiptDTO receiptDTO) {
        System.out.println(receiptDTO.getSaleDate() + "\n"
                + "See you soon! \n"
                + "Best regards " + receiptDTO.getStoreName() + "\n"
                + receiptDTO.getStreetName() + ", " + receiptDTO.getPostalNumber() + ", " + receiptDTO.getCity());
    }
}
