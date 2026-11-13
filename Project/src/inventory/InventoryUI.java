package inventory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import product.Product;
import product.ProductList;

public class InventoryUI {
	
	private Inventory inventory;
	private ProductList list;

    public InventoryUI(Inventory inventory, ProductList list) {
        this.inventory = inventory;
        this.list = list;
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
    	
    	Button addItem = new Button("Add an item");
    	Button editItem = new Button("Edit selected");
    	
    	HBox btns = new HBox(5, addItem, editItem);
    	
    	
    	root.getChildren().addAll(title, levels, items, btns);
    	
    	addItem.setOnAction(e -> addItem());
    	
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
    
    private void addItem() {
	    Dialog<Void> dialog = new Dialog<>();	// make dialog window
	    dialog.setTitle("Add item to inventory");
	    
	    ButtonType createType = new ButtonType("Add");   // make button
		dialog.getDialogPane().getButtonTypes().addAll(		// add buttons to the window
		    createType,
		    ButtonType.CANCEL
		);
		
	    ComboBox<String> selectedProd = new ComboBox<>();		
	    selectedProd.setMaxWidth(Double.MAX_VALUE);
	    for (Product product : list.getProducts()) {
	    	selectedProd.getItems().add(product.getProdName());
	    }
	   
	    TextField quantity = new TextField();	
	    quantity.setPromptText("Quantity");
	        
		// left side
	    VBox labels = new VBox(15, 
	    		new Label("Select a product:"),
	    		new Label("Quantity:")
	    );
	    
	    // right side
	    VBox fields = new VBox(6,
	     		selectedProd,
	     		quantity);
	    
	    // combine in a horizontal container
	    HBox content = new HBox(3, labels, fields);
	    
	    dialog.getDialogPane().setContent(content);
	        
	    Button createBtn = (Button) dialog.getDialogPane().lookupButton(createType); // get create button to apply functions
	    
	    createBtn.setOnAction(e -> {
	        Product product = list.findProd(selectedProd.getValue());
	        int quan = Integer.valueOf(quantity.getText());
	        
	        inventory.addProduct(product, quan);
	    });

	    dialog.showAndWait();
    }
	
}
