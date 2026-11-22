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
import inventory.Inventory;

public class Main extends Application {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage stage) {
	    App system = new App();
	    Inventory inventory = new Inventory();
	    ProductList products = new ProductList();
	    Transactions transactions = new Transactions();
	    SaleFunctions sales = new SaleFunctions(transactions);
	    products.addProduct(new Product("1", "Nova", "HAHe", 12.0));

	    SceneManager sm = new SceneManager(stage, system, inventory, products, transactions, sales);

	    stage.setTitle("TBAA Management System");
	    sm.showLogin();
	    stage.show();
	}
}
