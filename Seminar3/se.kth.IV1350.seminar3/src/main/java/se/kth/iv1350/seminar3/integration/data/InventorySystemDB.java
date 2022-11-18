package se.kth.iv1350.seminar3.integration.data;

import java.util.ArrayList;
import java.util.List;

import se.kth.iv1350.seminar3.integration.ItemTemplate;

import se.kth.iv1350.seminar3.model.DTO.ItemDTO;
import se.kth.iv1350.seminar3.model.DTO.SaleDTO;

/**
 * A (simulated) database of the store's inventory.
 */
public class InventorySystemDB {

    private List<ItemTemplate> itemCatalog = new ArrayList<>(); 
    
    /**
     * Creates an instance consisting of ten predefined items. 
     */
    public InventorySystemDB(){
        itemCatalog.add(new ItemTemplate("Eko Gammaldagsmjölk", 15.96f,0.06,8));
        itemCatalog.add(new ItemTemplate("red milk", 12.5f,0.03,1));
        itemCatalog.add(new ItemTemplate("green milk", 11.0f,0.03,2));
        itemCatalog.add(new ItemTemplate("Kalles Kaviar Guld", 25.95f,0.06,3));
        itemCatalog.add(new ItemTemplate("Coca cola", 20.0f,0.06,4));
        itemCatalog.add(new ItemTemplate("Hummer", 440.0f,0.125,5));
        itemCatalog.add(new ItemTemplate("Risgrynsgröt", 8.95f,0.03,6));
        itemCatalog.add(new ItemTemplate("Havregrynsgröt", 6.95f,0.03,7));
        itemCatalog.add(new ItemTemplate("Laxfile", 125.0f,0.125,9));
        itemCatalog.add(new ItemTemplate("Skagenröra", 39.95f,0.125,10));
    }
    
    /**
     * Method retrives information of an item.
     * @param barCode - Is used to determine what item the method should return.
     * @return Returns an ItemDTO consisitng of data fetched from the inventory system.
     */
    public ItemDTO retriveItemDescription(int barCode){
        int i;
        for(i = 0; i < itemCatalog.size(); i++){
            if(itemCatalog.get(i).getBarCode() == barCode){
                ItemDTO itemDTO = new ItemDTO(itemCatalog.get(i).getName(), itemCatalog.get(i).getPrice(), itemCatalog.get(i).getVAT(), itemCatalog.get(i).getBarCode());
                return itemDTO;
            }
        }
        return null;
    }
    
    /**
     * Updates the inventory system after a succesful sale.
     * @param completeSaleDTO - data from the sale.
     */
    public void updateInventory(SaleDTO completeSaleDTO){}
}
