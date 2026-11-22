package inventory;

import java.io.Serializable;

import product.Product;

public class InventoryItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private final Product product;
	private int quantity;
	
	public InventoryItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public Product getProduct() {return product;}
	public int getQuantity() {return quantity;}
	
	public String getId() {return product.getId();}
	public String getProdName() {return product.getProdName();}
	public double getPrice() {return product.getPrice();}
	
	public void setQuantity(int quanti) {this.quantity = quanti;}
	

}
