/* Sales Item class
 * 
 * This class shall serve as the class that will be instantiated in creating sale transactions.
 * */

package sales;
import inventory.InventoryItem;
import product.*;

public class SaleItem {
	private String saleId;
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
	
}
