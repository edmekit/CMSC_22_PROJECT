package product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductList {
	private final ObservableList<Product> products = FXCollections.observableArrayList();
	private int numProduct;
	
	public ProductList() {
		this.numProduct = 0;
	}
	
	public ObservableList<Product> getProducts(){
		return this.products;
	}
	
	public void addProduct(Product product) {
		products.add(product);
		numProduct++;
		}
	public int getNumProds() {
		return this.numProduct;
	}
}
