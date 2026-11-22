package inventory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import product.Product;

public class Inventory {
	private final static ObservableList<InventoryItem> items = FXCollections.observableArrayList();
	private int numProduct;
	
	public Inventory() {
		this.numProduct = 0;
	}
	
	public ObservableList<InventoryItem> getItems() {return items;}
	
	public InventoryItem findItem(String prodName) {
		for (InventoryItem item : items) {
			if (item.getProdName().equals(prodName)) {return item;}
		}
		return null;
	}
	
	public void addProduct(Product product, int quanti) {
		if (quanti < 0) {
			// Cannot be negative
			return;
		}
		if (findItem(product.getId()) != null) {
			// Product already exists in inventory
			return;
		}
		items.add(new InventoryItem(product, quanti));
		numProduct++;
	}
	
	public void restock(String id, int quanti) {
		if (quanti <= 0) {
			// Cannot be less than or equal 0
			return;
		}
		
	}
	
	public int getNumProduct() {
		return this.numProduct;
	}
	
	public void saveInventory(Path filepath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(filepath))) {
            oos.writeObject(this);
            //serialize the database such that it is saved to filepath, retains being an object when loaded
            System.out.println("Inventory successfully saved to " + filepath);
        } catch (IOException e) {
            System.out.println("Error saving inventory: " + e.getMessage());
            //catch if the filepath is invalid, or an error is caught in the Input/Output of the system
        }
    }
	
	public static Inventory loadInventory(Path filepath) {
		try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(filepath))){
			try {
				Inventory inv = (Inventory) ois.readObject();
				return inv;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}	
		} catch(IOException e) {
			return null;
		}
	}

}
