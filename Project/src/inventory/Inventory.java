package inventory;

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
	
	public static InventoryItem findItem(String prodId) {
		for (InventoryItem item : items) {
			if (item.getId().equals(prodId)) {return item;}
		}
		return null;
	}
	
	public static int checkItemQuantity(Product product) {
		InventoryItem prodInv = findItem(product.getId());
		Product foundProduct = prodInv.getProduct();
		if(foundProduct ==  null) return -1;
		else if(prodInv.getQuantity() == 0) return 0;
		else return prodInv.getQuantity();
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
	

}
