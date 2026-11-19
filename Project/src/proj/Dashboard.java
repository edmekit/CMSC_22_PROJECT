package proj;
import inventory.Inventory;
import inventory.InventoryUI;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import product.Product;
import product.ProductList;
import product.ProductUI;
import sales.SalesUI;
import transac.TransactionUI;
import transac.Transactions; 

public class Dashboard {
	
	private BorderPane root;
	private StackPane panel;
	private Inventory inventory;
	private ProductList products;
	private Transactions transactions;
	
    public Dashboard(Inventory inventory, ProductList products, Transactions transactions) {
    	this.inventory = inventory;
    	this.products = products;
    	this.transactions = transactions;
        root = new BorderPane();

        panel = new StackPane();

        root.setLeft(createSidebar());
        root.setCenter(panel);

    }
    
    private VBox createSidebar() {
    	VBox sidebar = new VBox(40);
    	VBox btns = new VBox(10);
    	sidebar.getStyleClass().add("sidebar");
    	
    	Label greet = new Label("Hello, Manager"); // put name maybe
    	sidebar.getChildren().add(greet);
    	
    	sidebar.setPrefWidth(150);
    	sidebar.setMinWidth(150);
    	sidebar.setMaxWidth(150);
    	
    	Button salesBtn = new Button("Sales");
    	Button invenBtn = new Button("Inventory");
    	Button prodBtn = new Button("Products");
    	Button logBtn = new Button("Transaction");
    	
    	
    	salesBtn.setMaxWidth(Double.MAX_VALUE);
    	salesBtn.setAlignment(Pos.CENTER_LEFT);
    	invenBtn.setMaxWidth(Double.MAX_VALUE);
    	invenBtn.setAlignment(Pos.CENTER_LEFT);
    	prodBtn.setMaxWidth(Double.MAX_VALUE);
    	prodBtn.setAlignment(Pos.CENTER_LEFT);
    	logBtn.setMaxWidth(Double.MAX_VALUE);
    	logBtn.setAlignment(Pos.CENTER_LEFT);
    	
    	invenBtn.setOnAction(e -> showInventory());
    	prodBtn.setOnAction(e -> showProducts());
    	salesBtn.setOnAction(e -> showSales());
    	logBtn.setOnAction(e -> showTransac());
    	
    	btns.getChildren().addAll(salesBtn, invenBtn, prodBtn, logBtn);
    	
    	sidebar.getChildren().add(btns);
    	
    	return sidebar;
    }
    
    private void showInventory() {
    	
		
		InventoryUI ui = new InventoryUI(inventory, products);

        panel.getChildren().setAll(ui.showInventory());
    }
    
    private void showProducts() {

	    
	    ProductUI ui = new ProductUI(products);

        panel.getChildren().setAll(ui.showProduct());
    }
    
    private void showSales() {
    	SalesUI sales = new SalesUI(inventory);
    	
    	panel.getChildren().setAll(sales.showSales());
    }
    
    private void showTransac() {
    	TransactionUI transac = new TransactionUI(transactions);
    	
    	panel.getChildren().setAll(transac.showTransac());
    }
    
    public Parent getView() {
        return root;
    }
	
	// Manager-only
	
	
	
	
	
	// Clerk-only
	
	
	
	
}
