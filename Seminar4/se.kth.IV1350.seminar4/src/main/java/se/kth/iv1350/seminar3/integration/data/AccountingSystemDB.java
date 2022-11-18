package se.kth.iv1350.seminar3.integration.data;

import se.kth.iv1350.seminar3.controller.ConnectionTimedOut;

/**
 * This class keeps track of the store's bank balance.
 */
public class AccountingSystemDB {
    private float balance;
    
    /**
     * Creates one instans of the object.
     */
    public AccountingSystemDB(){}
    /**
     * Increases the stores balance.
     * Is called in the final stage of a sale, once the customer's payment has gone through.
     * @param totalCost is by how much the balance is to be increased.
     * @throws ConnectionTimedOut is thrown if AccountingSystemDB is not responding.
     */
    public void updateAccountingDB(float totalCost) throws ConnectionTimedOut{
        if(totalCost == -1)
            throw new ConnectionTimedOut("");
        this.balance += totalCost;
    }
    /**
     * Retrives the balance from this class.
     * @return - The retrived balance.
     */
    public float getBalance(){ return this.balance; }
    
    
}
