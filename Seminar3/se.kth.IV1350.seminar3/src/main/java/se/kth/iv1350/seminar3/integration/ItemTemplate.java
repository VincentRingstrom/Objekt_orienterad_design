package se.kth.iv1350.seminar3.integration;

public class ItemTemplate {
    private final String name;
    private final float price;
    private final double VAT;
    private final int barCode;
    
    public ItemTemplate(String name, float price, double VAT, int barCode){
        this.name = name;
        this.price = price;
        this.VAT = VAT;
        this.barCode = barCode;
    }

    /**
     * Retrives the name from this class.
     * @return - The retrived name.
     */
    public String getName(){ return this.name; }
    /**
     * Retrives the price from this class.
     * @return - The retrived price.
     */
    public float getPrice(){ return this.price; }
    /**
     * Retrives the VAT from this class.
     * @return - The retrived VAT.
     */
    public double getVAT(){ return this.VAT; }
    /**
     * Retrives the barcode from this class.
     * @return - The retrived barcode.
     */
    public int getBarCode(){ return this.barCode; }

}
