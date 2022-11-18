package se.kth.iv1350.seminar3.view;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import se.kth.iv1350.seminar3.model.AmountPaidObserver;
import se.kth.iv1350.seminar3.model.DTO.PaymentDTO;
/**
 * This class represents an observer and writes to file once a new amount has been paid.
 */
public class TotalRevenueFileOutput implements AmountPaidObserver{
    private static final String FILENAME = "amountPaid.txt";
    private float amountPaid;
    private PrintWriter amountPaidByCustomerFile;
    
    /**
     * Is a constructor of TotalRevenueFileOutput. Initiates amountPaid and creates a file.
     */
    public TotalRevenueFileOutput() {
        amountPaid = 0;
        try {
            amountPaidByCustomerFile = new PrintWriter(new FileWriter(FILENAME), true);
        } catch (IOException e) {
            System.out.println("Could not create revenue file");
        }
     }

    /**
     * Method is called once a new payment has gone through. // METHOD IS CALLED ONCE A NEW PAYMENT HAS GONE THROUGH.
     * Updates amountPaid and writes to file.
     * @param amountPaid 
     */
    @Override
    public void newPayment(PaymentDTO paymentDTO) {
        this.amountPaid += paymentDTO.getAmountPaid();
        printRevenue(amountPaid);
    }
    private void printRevenue(float amountPaid){
        System.out.println("Writing to file '" + FILENAME + "'\n");
        printToFile();
    }
    private void printToFile() {
        amountPaidByCustomerFile.println("Amount paid: " + amountPaid + " SEK");
    }
}

