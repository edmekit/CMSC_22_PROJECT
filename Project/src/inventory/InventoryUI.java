package inventory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
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
import proj.SceneManager;
import proj.User;

public class InventoryUI {
	
	private SceneManager sm;
	private Inventory inventory;
	private ProductList list;
	private User user;
	private Label itemCount;
	private Label restockCount;
	

    public InventoryUI(SceneManager sm) {
    	this.sm = sm;
        this.inventory = sm.getInventory();
        this.list = sm.getProducts();
        this.user = sm.getSystem().getCurrentUser();
    }
	
    public Parent showInventory() {
    	  	
    	VBox root = new VBox(20);
        root.setPadding(new Insets(25));			

        Label title = new Label("Inventory Management");
        title.setMaxWidth(Double.MAX_VALUE);
        title.setAlignment(Pos.CENTER);
        title.getStyleClass().add("title");
    	
    	HBox levels = getLevels(); // or something type shit
    	TableView<InventoryItem> items = getItems();
    	
    	Button addItem = new Button("Add an item");
    	addItem.setManaged(user.canManageInventory());
    	addItem.setVisible(user.canManageInventory());
    	
    	Button editItem = new Button("Restock item");
    	editItem.setManaged(user.canManageInventory());
    	editItem.setVisible(user.canManageInventory());
    	
    	Button delItem = new Button("Delete selected");
    	delItem.setManaged(user.canManageInventory());
    	delItem.setVisible(user.canManageInventory());
    	
    	HBox btns = new HBox(5, addItem, editItem, delItem);
    	
    	root.getChildren().addAll(title, levels, items, btns);
    	
    	addItem.setOnAction(e -> addItem());
    	editItem.setOnAction(e -> {
    		InventoryItem selectedItem = items.getSelectionModel().getSelectedItem();
        	
        	if (selectedItem == null) {
        		warn("Select an item to restock.");
        		return;
        	}
        	editItem(selectedItem);
    	});
        delItem.setOnAction(e -> {
        	InventoryItem selectedItem = items.getSelectionModel().getSelectedItem();
        	
        	if (selectedItem == null) {
        		warn("Select an item to delete first.");
        		return;
        	}
        	if (inventory.getItems().size() <= 0) {
        		warn("No item to delete");
        		return;
        	}
        	
        	Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
		            "Delete " + selectedItem.getProdName() + "?", ButtonType.YES, ButtonType.NO);
		    confirm.setHeaderText(null);
		    confirm.showAndWait().ifPresent(result -> {
		        if (result == ButtonType.YES) {
		        	inventory.removeItem(selectedItem);
		        	itemCount.setText("Products: " + String.valueOf(inventory.getNumProduct()));
		        }
		    });
        });
    	
    	return root;
    }
    
    private HBox getLevels() {
    	HBox display = new HBox();
    	itemCount = new Label("Products: " + String.valueOf(inventory.getNumProduct()));
    	restockCount = new Label("\tNeeds restocking: " + String.valueOf(inventory.getNeedRestock()));
    	display.getChildren().addAll(itemCount, restockCount); 
    	display.getStyleClass().add("levels");
    	
    	return display;
    }
    
    private TableView<InventoryItem> getItems() {
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
	    	if (selectedProd.getValue() == null) {
	    		warn("Select a product.");
	    		return;
	        }
	        
	    	int quan;
	        try {
	        	quan = Integer.parseInt(quantity.getText().trim());
	        } catch (NumberFormatException ex) {
	        	warn("Quantity must be a whole number.");
	        	e.consume();
	        	return;
	        }
	        if (quan <= 0) {
	        	warn("Quantity must be greater than 0."); 
	        	e.consume();
	        	return;
	        }
	        
	        Product product = list.findProd(selectedProd.getValue());
	        if (!inventory.addProduct(product, quan)) {
	        	warn("Product already in inventory.");
	        	e.consume();
	        	return;
	        } else {
	        	itemCount.setText("Products: " + String.valueOf(inventory.getNumProduct()));
	        }
	    });

	    dialog.showAndWait();
    }
    
    private void editItem(InventoryItem item) {
    	 Dialog<Void> dialog = new Dialog<>();	// make dialog window
 	    dialog.setTitle("Restock item");
 	    
 	    ButtonType restockType = new ButtonType("Add");   // make button
 		dialog.getDialogPane().getButtonTypes().add(		// add buttons to the window
 		    restockType);
 		
 		TextField quantity = new TextField();	
	    quantity.setPromptText("Quantity");
	        
		// left side
	    VBox labels = new VBox(15, 
	    		new Label("Product:"),
	    		new Label("Restock quantity:")
	    );
	    
	    // right side
	    VBox fields = new VBox(6,
	    		new Label(item.getProdName()),
	     		quantity);
	    
	    // combine in a horizontal container
	    HBox content = new HBox(3, labels, fields);
	    
	    dialog.getDialogPane().setContent(content);
	    Button restockBtn = (Button) dialog.getDialogPane().lookupButton(restockType);
	    
	    restockBtn.setOnAction(e -> {
	    	int quan;
	    	try {
	        	quan = Integer.parseInt(quantity.getText().trim());
	        } catch (NumberFormatException ex) {
	        	warn("Quantity must be a whole number.");
	        	e.consume();
	        	return;
	        }
	        if (quan <= 0) {
	        	warn("Quantity must be greater than 0."); 
	        	e.consume();
	        	return;
	        }
	        item.setQuantity(quan);
	        restockCount.setText("\tNeeds restocking: " + String.valueOf(inventory.getNeedRestock()));
	    });
 		dialog.showAndWait();
    }
    
    private void warn(String message) {
        sm.getSystem().warn(message);
    }
	
}
