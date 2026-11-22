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
import proj.SceneManager;
import transac.Transactions;

public class SalesUI {
	
	private Inventory items;
	private SaleFunctions saleFunctions;
	
	public SalesUI(SceneManager sm) {
		this.items = sm.getInventory();
		this.saleFunctions = sm.getSales();
	}
	
	public Parent showSales() {
		
		VBox root = new VBox(20);
		root.setPadding(new Insets(25));
		
		Label title = new Label("Sales");
		title.setMaxWidth(Double.MAX_VALUE);
		title.setAlignment(Pos.CENTER);
		title.getStyleClass().add("title");
		
		// earning 
		VBox header = new VBox(3);
		header.getStyleClass().add("levels");
		Label headerLabel = new Label("TOTAL EARNINGS");
		Label earningLabel = new Label("PHP " + saleFunctions.getEarn());
		
		header.getChildren().addAll(headerLabel, earningLabel);
		
		VBox saleEntry = new VBox(10);
		
		Label select = new Label("Product");
	    ComboBox<String> selectedProd = new ComboBox<>();		
	    for (InventoryItem item : items.getItems()) {
	    	selectedProd.getItems().add(item.getProdName());
	    }
	    selectedProd.setMaxWidth(Double.MAX_VALUE);
	    saleEntry.setMaxWidth(Double.MAX_VALUE);
	    
	    Label quanLabel = new Label("Quantity");
	    TextField quantity = new TextField();	
	    quantity.setPromptText("Quantity");
	    quantity.setMaxWidth(Double.MAX_VALUE);
		
	    saleEntry.getChildren().addAll(select, selectedProd, quanLabel, quantity);
		
		
		Button complete = new Button("Complete order");
    	
    	HBox btns = new HBox(5);
		
		root.getChildren().addAll(title, header, saleEntry, complete);
		
		complete.setOnAction(e -> {
			String id = "1";
			InventoryItem product = items.findItem(selectedProd.getValue());
			System.out.println(product.getQuantity());
			int quan = Integer.valueOf(quantity.getText());
			
			saleFunctions.addSale(product, id, quan);
			earningLabel.setText("PHP " + saleFunctions.getEarn());
			
			});
		
		return root;
	}
}
