package proj;

import javafx.application.Application;
import javafx.stage.Stage;
import product.Product;
import product.ProductList;
import sales.SaleFunctions;
import transac.Transactions;

import java.nio.file.Path;
import java.nio.file.Paths;

import inventory.Inventory;

public class Main extends Application {
	private static final Path INVENTORY_FILE = Paths.get("Project/src/storage/inventory.txt");
	private static final Path PRODUCT_FILE = Paths.get("Project/src/storage/products.txt");
	private static final Path TRANSACTIONS_FILE = Paths.get("Project/src/storage/transactions.txt");
	private static final Path SALES_FILE = Paths.get("Project/src/storage/sales.txt");
	private static final Path APP_FILE = Paths.get("Project/src/storage/app.txt");
	
	Inventory inventory;
	App system;
	ProductList products;
	Transactions transactions;
	SaleFunctions sales;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {
	    system = new App();
	    inventory = new Inventory();
	    products = new ProductList();
	    transactions = new Transactions();
	    sales = new SaleFunctions(transactions);
	    system.setUsersList(APP_FILE);
	    inventory = Inventory.loadInventory(INVENTORY_FILE);
	    products = ProductList.loadProducts(PRODUCT_FILE);
	    transactions = Transactions.loadTransacts(TRANSACTIONS_FILE);
	    sales = SaleFunctions.loadSales(SALES_FILE, transactions);

	    SceneManager sm = new SceneManager(stage, system, inventory, products, transactions, sales);

	    stage.setTitle("TBAA Management System");
	    sm.showLogin();
	    stage.show();
	}
	
	@Override
	public void stop() {
		System.out.println("Saving data and shutting down...");
		
		if(system != null) {
			system.saveUsersList(APP_FILE);
		}
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
