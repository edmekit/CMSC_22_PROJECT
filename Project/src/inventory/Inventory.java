package inventory;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import proj.Product;

public class Inventory {
	private final ObservableList<InventoryItem> items = FXCollections.observableArrayList();
	
	public ObservableList<InventoryItem> getItems() {return items;}
	
	public InventoryItem findItem(String prodId) {
		for (InventoryItem item : items) {
			if (item.getId().equals(prodId)) {return item;}
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
	}
	
	public void restock(String id, int quanti) {
		if (quanti <= 0) {
			// Cannot be less than or equal 0
			return;
		}
		
	}
	

}
