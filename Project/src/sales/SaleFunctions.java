/* Sale Functions class
 * 
 * This class shall serve as the class that contains the functions of processing sales such as adding, and printing.
 */

package sales;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;

import inventory.*;
import transac.Transactions;

public class SaleFunctions implements Serializable {
	private static final long serialVersionUID = 1L;
	private Transactions transacs;
	private double earnings;
	
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
		transacs.recordSale(toSell, quantity, id);
		toSell.setQuantity(toSell.getQuantity() - quantity);
		earnings += toSell.getPrice() * quantity;
		System.out.print(earnings);
	}
	
	public double getEarn() {
		return this.earnings;
	}
	
	public void saveSales(Path filepath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(filepath))) {
            oos.writeObject(this);
            //serialize the database such that it is saved to filepath, retains being an object when loaded
            System.out.println("Inventory successfully saved to " + filepath);
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
            //catch if the filepath is invalid, or an error is caught in the Input/Output of the system
        }
    }
	
	public static SaleFunctions loadSales(Path filepath) {
		try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(filepath))){
			try {
				SaleFunctions sales = (SaleFunctions) ois.readObject();
				return sales;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return new SaleFunctions(new Transactions());
			}	
		} catch(IOException e) {
			return new SaleFunctions(new Transactions());
		}
	}
}
