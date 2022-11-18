package se.kth.iv1350.seminar3.model.DTO;

import se.kth.iv1350.seminar3.integration.data.AccountingSystemDB;

/**
 * Used to transfer data to observer pattern. TRANSFER DATA TO OBS. PATTERN
 */
public class PaymentDTO {
    private final float amountPaid;
    private final AccountingSystemDB accSys;
    private final float totalCost;
    private final float change;
    
    public PaymentDTO(float amountPaid, AccountingSystemDB accSys, float totalCost, float change){
        this.amountPaid = amountPaid;
        this.accSys = accSys;
        this.totalCost = totalCost;
        this.change = change;
    }

    public float getAmountPaid() {
        return amountPaid;
    }

    public AccountingSystemDB getAccSys() {
        return accSys;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public float getChange() {
        return change;
    }
    
}
