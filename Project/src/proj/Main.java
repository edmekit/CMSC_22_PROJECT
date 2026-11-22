package proj;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import product.Product;
import product.ProductList;
import product.ProductUI;
import sales.SaleFunctions;
import transac.Transactions;
import inventory.InventoryUI;
import inventory.InventoryItem;

import java.nio.file.Path;
import java.nio.file.Paths;

import inventory.Inventory;

public class Main extends Application {
	private static final Path INVENTORY_FILE = Paths.get("src/storage/inventory.txt");
	private static final Path PRODUCT_FILE = Paths.get("src/storage/products.txt");
	private static final Path TRANSACTIONS_FILE = Paths.get("src/storage/transactions.txt");
	private static final Path SALES_FILE = Paths.get("src/storage/sales.txt");
	Inventory inventory;
	ProductList products;
	Transactions transactions;
	SaleFunctions sales;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
	    App system = new App();
	    inventory = new Inventory();
	    products = new ProductList();
	    transactions = new Transactions();
	    sales = new SaleFunctions(transactions);
	    Inventory.loadInventory(INVENTORY_FILE);
	    ProductList.loadProducts(PRODUCT_FILE);
	    Transactions.loadTransacts(TRANSACTIONS_FILE);
	    SaleFunctions.loadSales(SALES_FILE);
	    products.addProduct(new Product("1", "Nova", "HAHe", 12.0));

	    SceneManager sm = new SceneManager(stage, system, inventory, products, transactions, sales);

	    stage.setTitle("TBAA Management System");
	    sm.showLogin();
	    stage.show();
	}
	
	@Override
	public void stop() {
		System.out.println("Saving data and shutting down...");
		
		if(inventory != null) {
			inventory.saveInventory(INVENTORY_FILE);
		}
		if(products != null) {
			products.saveProducts(PRODUCT_FILE);
		}
		if(transactions != null) {
			transactions.saveTransactions(TRANSACTIONS_FILE);
		}
		if(sales != null) {
			sales.saveSales(SALES_FILE);
		}
		
	}
}
