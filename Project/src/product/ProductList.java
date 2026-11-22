package product;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
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
	
	public Product findProd(String prodName) {
		for (Product prod : products) {
			if (prod.getProdName().equals(prodName)) {
				return prod;
			}
		}
		return null;
	}
	
	public void saveProducts(Path filepath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(filepath))) {
            oos.writeObject(this);
            //serialize the database such that it is saved to filepath, retains being an object when loaded
            System.out.println("Products successfully saved to " + filepath);
        } catch (IOException e) {
            System.out.println("Error saving products: " + e.getMessage());
            //catch if the filepath is invalid, or an error is caught in the Input/Output of the system
        }
    }
	
	public static ProductList loadProducts(Path filepath) {
		try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(filepath))){
			try {
				ProductList pl = (ProductList) ois.readObject();
				return pl;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}	
		} catch(IOException e) {
			return null;
		}
	}
}
