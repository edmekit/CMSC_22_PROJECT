package inventory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class InventoryUI {
	
	private Inventory inventory;

    public InventoryUI(Inventory inventory) {
        this.inventory = inventory;
    }
	
    public Parent showInventory() {
    	  	
    	VBox root = new VBox(20);
        root.setPadding(new Insets(25));			

        Label title = new Label("Inventory Management");
        title.setMaxWidth(Double.MAX_VALUE);
        title.setAlignment(Pos.CENTER);
        title.getStyleClass().add("title");
    	
    	HBox levels = getLevels(); // or something type shit
    	VBox items = getItems();
    	Button close = new Button("Close");
    	
    	root.getChildren().addAll(title, levels, items, close);
    	
    	return root;
    }
    
    private HBox getLevels() {
    	HBox display = new HBox();
    	display.getChildren().addAll(new Label("Products: 1"), new Label("\tNeeds restocking: 0")); // hardcoded pa tamad sir
    	display.getStyleClass().add("levels");
    	
    	return display;
    }
    
    private VBox getItems() {
        VBox list = new VBox(10);
        list.getStyleClass().add("items-container");

        HBox header = new HBox();
        header.getStyleClass().add("item-header");
        
        Label idHeader = new Label("ID");
        Label nameHeader = new Label("Name");
        Label stockHeader = new Label("Stock");

        idHeader.setPrefWidth(100);
        nameHeader.setPrefWidth(300);
        stockHeader.setPrefWidth(100);
    	
        header.getChildren().addAll(idHeader, nameHeader, stockHeader);
    	
    	list.getChildren().add(header);
    	for (InventoryItem item : inventory.getItems()) {
    		HBox itemEntry = new HBox();
    		
    		Label id = new Label(item.getId());
    		Label name = new Label(item.getProdName());
    		Label quantity = new Label(String.valueOf(item.getQuantity())); 
    		
            id.setPrefWidth(100);
            name.setPrefWidth(300);
            quantity.setPrefWidth(100);
    		
    		itemEntry.getChildren().addAll(id, name, quantity);
    		
    		list.getChildren().add(itemEntry);
    	}
    	
    	return list;
    }
	
}
