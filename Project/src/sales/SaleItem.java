/* Sales Item class
 * 
 * This class shall serve as the class that will be instantiated in creating sale transactions.
 * */

package sales;
import product.*;

public class SaleItem {
	private String saleId;
	private Product soldProduct;
	private int quantity;
	
	public SaleItem(String id, Product prod, int quantity) {
		this.saleId = id;
		this.quantity = quantity;
		this.soldProduct = prod;
	}
	
	public String getId() {return this.saleId;}
	public Product getProduct() {return this.soldProduct;}
	public double getQuantity() {return this.quantity;}
	
}
