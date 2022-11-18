package se.kth.iv1350.seminar3.integration.data;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.seminar3.model.DTO.SaleDTO;

/**
 * Stores all previously made sales
 */
public class SaleLogDB {
    private List<SaleDTO> saleList = new ArrayList<SaleDTO>();
    
    /**
     * Creates an instance of SaleLogDB
     */
    public SaleLogDB(){}
    
    /**
     * Stores completed sales in a log.
     * @param completedSaleDTO - the sale instance beeing stored.
     */
    public void storeSaleLog(SaleDTO completedSaleDTO){
        saleList.add(completedSaleDTO);
    }
    /**
     * Returns the whole list of stored sales (not used in the projects current state).
     * @return - A list of completed sales.
     */
    public List<SaleDTO> getSaleLog(){ return this.saleList; }
}
