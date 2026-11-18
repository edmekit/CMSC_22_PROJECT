package sales;

import inventory.Inventory;
import inventory.InventoryItem;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import product.Product;

public class SalesUI {
	
	private Inventory items;
	SaleFunctions saleFunctions = new SaleFunctions();
	
	public SalesUI(Inventory items) {
		this.items = items;
	}
	
	public Parent showSales() {
		
		VBox root = new VBox();
		root.setPadding(new Insets(25));
		
		Label title = new Label("Sales");
		title.setMaxWidth(Double.MAX_VALUE);
		title.setAlignment(Pos.CENTER);
		title.getStyleClass().add("title");
		
		HBox saleEntry = new HBox(10);
		Label select = new Label("Select a product: ");
	    ComboBox<String> selectedProd = new ComboBox<>();		
	    for (InventoryItem item : items.getItems()) {
	    	selectedProd.getItems().add(item.getProdName());
	    }
	    
	    TextField quantity = new TextField();	
	    quantity.setPromptText("Quantity");
		
	    saleEntry.getChildren().addAll(select, selectedProd, quantity);
		
		
		Button addEntry = new Button("+");
		Button complete = new Button("Complete order");
    	
    	HBox btns = new HBox(5);
		
		root.getChildren().addAll(title, saleEntry, addEntry, complete);
		
		addEntry.setOnAction(e -> {
			String id = "1";
			Product product = Inventory.findItem(selectedProd.getValue()).getProduct();
			int quan = Integer.valueOf(quantity.getText());
			saleFunctions.addSale(product, id, quan);
			});
		
		return root;
	}
}
