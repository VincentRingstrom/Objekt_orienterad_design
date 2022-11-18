package se.kth.iv1350.seminar3.view;

import se.kth.iv1350.seminar3.model.AmountPaidObserver;
import se.kth.iv1350.seminar3.model.DTO.PaymentDTO;



/**
 * This class represents an observer and print to screen once a new amount has been paid.
 */
public class TotalRevenueView implements AmountPaidObserver{

    private float amountPaid;

    /**
     * Constructor to TotalRevenueView.
     * Initializes amountPaid.
     */
    public TotalRevenueView(){
        amountPaid = 0;
    }
    @Override
    public void newPayment(PaymentDTO paymentDTO){ // THIS IS ALSO CALLED AFTER EACH PAYMENT. I think.
        //addNewPayment(paymentDTO);
        System.out.println("The most recent customer paid: " + paymentDTO.getAmountPaid() + " SEK");
        this.amountPaid += paymentDTO.getAmountPaid();
        printRevenue(amountPaid);
    }
    private void printRevenue(float amountPaid){
        System.out.println("Total revenue: " + this.amountPaid + " SEK");
    }
}
