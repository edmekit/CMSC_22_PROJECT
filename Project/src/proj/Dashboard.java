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

public class Dashboard {
	
	private BorderPane root;
	private StackPane panel;

    public Dashboard() {
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
    	
    	btns.getChildren().addAll(salesBtn, invenBtn, prodBtn, logBtn);
    	
    	sidebar.getChildren().add(btns);
    	
    	return sidebar;
    }
    
    private void showInventory() {
    	
		Inventory inventory = new Inventory();
		
		Product piatos = new Product("1", "piatos", "delicious", 20.0);
		inventory.addProduct(piatos, 5);
		
		InventoryUI ui = new InventoryUI(inventory);

        panel.getChildren().setAll(ui.showInventory());
    }
    
    private void showProducts() {
		ProductList products = new ProductList();
	    Product piatos = new Product("1", "piatos", "delicious", 20.0);
	    products.addProduct(piatos);
	    products.addProduct(new Product("11", "nova", "!delicious", 21.0));
	    
	    ProductUI ui = new ProductUI(products);

        panel.getChildren().setAll(ui.showProduct());
    }
    
    public Parent getView() {
        return root;
    }
	
	// Manager-only
	
	
	
	
	
	// Clerk-only
	
	
	
	
}
