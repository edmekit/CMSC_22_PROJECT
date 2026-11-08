package proj;

public class InventoryItem {
	private final Product product;
	private int quantity;
	
	public InventoryItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public Product getProduct() {return product;}
	public int getQuantity() {return quantity;}
	
	private void setQuantity(int quanti) {this.quantity = quanti;}
	

}
