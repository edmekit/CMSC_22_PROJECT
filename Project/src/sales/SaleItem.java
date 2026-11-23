/* Sales Item class
 * 
 * This class shall serve as the class that will be instantiated in creating sale transactions.
 * */

package sales;
import java.io.Serializable;

import inventory.InventoryItem;

public class SaleItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private String saleId;
	private int currId = 0;
	private InventoryItem item;
	private int quantity;
	
	public SaleItem(String id, InventoryItem item, int quantity) {
		this.saleId = id;
		this.quantity = quantity;
		this.item = item;
	}
	
	public String getId() {return this.saleId;}
	public InventoryItem getProduct() {return this.item;}
	public double getQuantity() {return this.quantity;}
	
	public String createId() {
		return "S" + (currId++);
	}
	
}
