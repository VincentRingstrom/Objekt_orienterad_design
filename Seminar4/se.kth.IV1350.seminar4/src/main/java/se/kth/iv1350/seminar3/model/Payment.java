package se.kth.iv1350.seminar3.model;

import java.util.ArrayList;
import java.util.List;
//import java.util.Observer;
import se.kth.iv1350.seminar3.controller.ConnectionTimedOut;
import se.kth.iv1350.seminar3.integration.data.AccountingSystemDB;
import se.kth.iv1350.seminar3.model.DTO.PaymentDTO;

/**
 * Payment represents a transaction and is used to handle a payment. 
 */
public class Payment implements AmountPaidObserver{ // HERE IS WHERE WE ADD OBSERVERS.
    private float amountPaid;
    private AccountingSystemDB accSys;
    private float totalCost;
    private float change;
    private List<AmountPaidObserver>amountPaidObserver = new ArrayList<>();
    /**
     * Creates an instance of Payment by storing amount paid 
     * and updating the store's accounting system.
     * @param amountPaid - Amount paid by the customer.
     * @param accSys - A reference to the store's accounting system.
     */
    public Payment(float amountPaid, AccountingSystemDB accSys){
        this.amountPaid = amountPaid;
        this.accSys = accSys;
    }
        
    /**
     * Method Checks that the payed amount is correct and return change if needed (otherwice return 0).
     * @param totalCost - Of the sale.
     * @param discountAmount - Eligable discount amount.
     * @return - Change.
     * @throws ConnectionTimedOut if AccountingSystemDB is not responding.
     * @throws InsufficientFundsException is thrown if the customer has not paid the full price of the sale.
     */
    public float getChange(float totalCost, double discountAmount) throws ConnectionTimedOut, InsufficientFundsException {
        float newTotalCost = totalCost;
        if (discountAmount > 0){
            newTotalCost = totalCost * (float)(1-discountAmount);
        }
        
        if(this.amountPaid >= newTotalCost){
            this.change = this.amountPaid - newTotalCost;
            accSys.updateAccountingDB(totalCost);
            notifyObservers();
            return this.change; 
        }
        else{
            throw new InsufficientFundsException(this.amountPaid, totalCost);
        }
    }
    /**
     * Reads the amount paid.
     * @return the paid amount by the customer.
     */
    public float getAmountPaid(){
        return this.amountPaid;
    }

    @Override
    public void newPayment(PaymentDTO paymentDTO) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Sends a notification to all subscribed observers that a new payment has gone through. // SEND NotifiCATION TO ALL SUB OBSERV that payment has gone through.
     */
   //@Override
    public void notifyObservers(){
        System.out.println("\n[Notifying that a new customer has paid]");
        for(AmountPaidObserver obs: amountPaidObserver){
            obs.newPayment(new PaymentDTO(amountPaid, accSys, totalCost, change));
        }
    }
    
    /**
     * The specified observer will be notified when this sale has been paid.
     * @param obs The observer to notify. 
     */
    public void addAmountPaidObserver(AmountPaidObserver obs){ // SPecified observer notofid when sale has been paid. ADD OBSERVER TO LIST.
        amountPaidObserver.add(obs);
    }
    /**
     * The specified observer will be no longer be notified when this sale has been paid. // REMOVE SPECIFIED OBSERVER FROM LIST.
     * @param obs The observer to not notify. 
     */
    public void removeAmountPaidObserver(AmountPaidObserver obs){
        amountPaidObserver.remove(obs);
    }
    /**
     * All the specified observers will be notified when this sale has been paid.
     * @param observers The observers to notify.
     */
    public void addAmountPaidObservers(List<AmountPaidObserver> observers){ // EVERY SINGLE observers will be notified. ADD ALL observers to be notified.
        amountPaidObserver.addAll(observers); // ADD THE ENTIRE LIST TO THE OBSERVERS. amountPaidObservers.
       
    }
    
    /**
     * Exception is used to inform the cashier and customer that the purchase has not gone
     * through because the customer has not paid the full price of the sale.
     */
    public class InsufficientFundsException extends Exception{
        private float amountPaid, totalCost;
        public InsufficientFundsException(float amountPaid, float totalCost){
            this.amountPaid = amountPaid;
            this.totalCost = totalCost;
        }
        /**
         * Prints to screen whenever an object of this class is created.
         * @return Prints to screen.
         *
        @Override
        public String toString(){ 
            return "Insufficient funds. Total cost is: " + totalCost + 
                   " Paid amount is: " + amountPaid;
        }*/
    }
    
}
