package se.kth.iv1350.seminar3.integration.data;

import java.util.ArrayList;
import java.util.List;
import se.kth.iv1350.seminar3.controller.ConnectionTimedOut;

import se.kth.iv1350.seminar3.model.DTO.SaleDTO;

/**
 * Stores all previously made sales
 */
public class SaleLogDB {
    private boolean SERVERISONLINE = true; // HERe I ALSO HAVE SERVER IS ONLINE. 
    private List<SaleDTO> saleList = new ArrayList<SaleDTO>();
    
    /**
     * Creates an instance of SaleLogDB
     */
    public SaleLogDB(){}
    /**
     * Creates an instance of SaleLogDB, only used for testing the class and is
     * not a part of the implementation.
     * @param SERVERISONLINE - Decides whether the simulated server is on/off.
     */
    public SaleLogDB(boolean SERVERISONLINE){
        this.SERVERISONLINE = SERVERISONLINE;
    }
    /**
     * Stores completed sales in a log.
     * @param completedSaleDTO - the sale instance beeing stored.
     * @throws ConnectionTimedOut is thrown if InventorySystemDB is not responding.
     */
    public void storeSaleLog(SaleDTO completedSaleDTO) throws ConnectionTimedOut{
        if(SERVERISONLINE == false){ throw new ConnectionTimedOut(""); }     
        else {saleList.add(completedSaleDTO);}
    }
    /**
     * Returns the whole list of stored sales (not used in the projects current state).
     * @return - A list of completed sales.
     */
    public List<SaleDTO> getSaleLog(){ return this.saleList; }
}
