package se.kth.iv1350.seminar3.model;

import se.kth.iv1350.seminar3.integration.data.AccountingSystemDB;

/**
 * Payment represents a transaction and is used to handle a payment. 
 */
public class Payment {
    private float amountPaid;
    private AccountingSystemDB accSys;
    private float totalCost;
    private float change;
    
    /**
     * Creates an instance of Payment by storing amount paid 
     * and updating the store's accounting system.
     * @param amountPaid - Amount paid by the customer.
     * @param accSys - A referens to the store's accounting systsem.
     */
    public Payment(float amountPaid, AccountingSystemDB accSys){
        this.amountPaid = amountPaid;
        this.accSys = accSys;
    }
    
    //private void processPayment(float amountPaid){}
    
    /**
     * Method Checks that the payed amount is correct and return change if needed (otherwice return 0).
     * @param totalCost - Of the sale.
     * @param discountAmount - Eligable discount amount.
     * @return - Change.
     */
    public float getChange(float totalCost, double discountAmount) {
        float newTotalCost = totalCost;
        if (discountAmount > 0){
            newTotalCost = totalCost * (float)(1-discountAmount);
        }
        if(this.amountPaid >= newTotalCost){
            this.change = this.amountPaid - newTotalCost;
            accSys.updateAccountingDB(totalCost);
            return this.change; 
        }
        //ELSE THROWS "insufficient funds" argument.
        //throws InsufficientFundsException 
        //throw new InsufficientBalanceException();
        return -1.0f;
    }
}
