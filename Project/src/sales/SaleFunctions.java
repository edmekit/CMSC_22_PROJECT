/* Sale Functions class
 * 
 * This class shall serve as the class that contains the functions of processing sales such as adding, and printing.
 */

package sales;

import inventory.*;
import transac.Transactions;

public class SaleFunctions {
	
	private Transactions transacs;
	private double earnings;
	//oo pre kinopya q ng buo yung Inventory class. papatawag uli tau ni sir HAHAHAHA
	// change return type para may flag sa SalesUI perd tapos madisplay yung error message
	
    public SaleFunctions(Transactions transacs) {
        this.earnings = 0;
        this.transacs = transacs;
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
		transacs.addTransac(id,new SaleItem(id,toSell,quantity));
		toSell.setQuantity(toSell.getQuantity() - quantity);
		earnings += toSell.getPrice() * quantity;
		System.out.print(earnings);
	}
	
	public double getEarn() {
		return this.earnings;
	}
}
