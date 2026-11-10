package inventory;

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
    	  	
        VBox root = new VBox(15);

        Label title = new Label("Inventory Management");
        title.setMaxWidth(Double.MAX_VALUE);
        title.setAlignment(Pos.CENTER);
    	
    	HBox levels = getLevels(); // or something type shit
    	VBox items = getItems();
    	Button close = new Button("Close");
    	
    	root.getChildren().addAll(title, levels, items, close);
    	
    	return root;
    }
    
    private HBox getLevels() {
    	HBox display = new HBox();
    	display.getChildren().add(new Label("Products"));
    	
    	return display;
    }
    
    private VBox getItems() {
    	VBox list = new VBox();
    	
    	list.getChildren().add(new Label("Items"));
    	for (InventoryItem item : inventory.getItems()) {
    		list.getChildren().add(new Label(item.getProdName()));
    	}
    	
    	return list;
    }
	
}
