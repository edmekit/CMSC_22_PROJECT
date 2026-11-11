package inventory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    	TableView items = getItems();
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
    
    private TableView getItems() {
		TableView<InventoryItem> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        // cols
        TableColumn<InventoryItem, String> colId   		= new TableColumn<>("ID");
        TableColumn<InventoryItem, String> colName      = new TableColumn<>("Name");
        TableColumn<InventoryItem, Integer> colQuantity  = new TableColumn<>("Quantity");
    	
        colId.getStyleClass().add("item-header");
        colName.getStyleClass().add("item-header");
        colQuantity.getStyleClass().add("item-header");
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("prodName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        
        table.getColumns().addAll(colId, colName, colQuantity);
        table.getStyleClass().add("items-container");
        
        table.setItems(inventory.getItems());

    	
    	return table;
    }
	
}
