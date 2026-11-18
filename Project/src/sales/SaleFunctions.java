/* Sale Functions class
 * 
 * This class shall serve as the class that contains the functions of processing sales such as adding, and printing.
 */

package sales;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import inventory.*;
import product.*;

public class SaleFunctions {
	
	private final ObservableMap<String, SaleItem> saleHistory = FXCollections.observableHashMap();
	private Inventory inventory;
	//oo pre kinopya q ng buo yung Inventory class. papatawag uli tau ni sir HAHAHAHA
	// change return type para may flag sa SalesUI perd tapos madisplay yung error message
	
    public SaleFunctions(Inventory inventory) {
        this.inventory = inventory;
    }
	
	public void addSale(InventoryItem toSell, String id, int quantity) {
		if(toSell == null) return; //if the product specified does not exist, return immediately
		
		if(toSell.getQuantity() <= 0) {
			System.out.println("The product is no longer in stock!");
			return; // if the product is not in the inventory or has no stock, return immediately
		} else if (toSell.getQuantity() < quantity) {
			System.out.println("Not enough stock.");
			return;
		}
		saleHistory.put(id,new SaleItem(id,toSell,quantity));
		toSell.setQuantity(toSell.getQuantity() - quantity);
	}
}
