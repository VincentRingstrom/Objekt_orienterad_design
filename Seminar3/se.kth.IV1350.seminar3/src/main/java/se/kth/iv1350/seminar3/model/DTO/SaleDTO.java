package se.kth.iv1350.seminar3.model.DTO;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.seminar3.integration.ItemHolder;

/**
 * This is a data transfer object (DTO), used to transfer data from the object Sale.
 */
public class SaleDTO {
    private List<ItemHolder> shoppingCart = new ArrayList<>(); 
    private final double discountAmount;
    private final float totalPrice;
    private final double totalVAT;
    private final float change;

    /**
     * Creates an instance of SaleDTO with data fetched from the Sale object.
     * @param shoppingCart - All the items and their quantity.
     * @param discountAmount - Customers discount rate.
     * @param totalPrice - The total price of the sale. 
     * @param totalVAT - Total VAT of the entire sale.
     * @param change - The customer is eligable change if he/she paid more than totalPrice.
     */
    public SaleDTO(List<ItemHolder> shoppingCart, double discountAmount, float totalPrice, double totalVAT, float change){
        this.shoppingCart = shoppingCart;
        this.discountAmount = discountAmount;
        this.totalPrice = totalPrice;
        this.totalVAT = totalVAT;
        this.change = change;
    }
    
    /**
     * Reads the shopping cart.
     * @return - The read shooping cart.
     */
    public List<ItemHolder> getShoppingCart(){ return this.shoppingCart; }
    /**
     * Reads the discount amount.
     * @return - The read discount amount.
     */
    public double getDiscountAmount(){ return this.discountAmount; }
    /**
     * Reads the total price of the sale
     * @return - The read total price.
     */
    public float getTotalPrice(){ return this.totalPrice; }
    /**
     * Reads the total VAT of the sale.
     * @return - The read VAT.
     */
    public double getTotalVAT(){ return this.totalVAT; }
    /**
     * Reads the change.
     * @return - The read change.
     */
    public float getChange(){ return this.change; }

}
