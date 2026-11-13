package product;

import inventory.InventoryItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

public class ProductUI {
	private ProductList products;
	
	public ProductUI(ProductList prods) {
		this.products = prods;
	}
	
	public Parent showProduct() {
		
		VBox root = new VBox(20);
		root.setPadding(new Insets(25));
		
		Label title = new Label("Product Management");
		title.setMaxWidth(Double.MAX_VALUE);
		title.setAlignment(Pos.CENTER);
		title.getStyleClass().add("title");
		
		HBox levels = getLevels();
		TableView products = getProds();
		
		Button addItem = new Button("Add a product");
    	Button editItem = new Button("Edit selected");
    	
    	HBox btns = new HBox(5, addItem, editItem);
		
		root.getChildren().addAll(title, levels, products, btns);
		
		addItem.setOnAction(e -> addProduct());
		
		return root;
	}
	
	private HBox getLevels() {
		HBox display = new HBox();
		
		String numProducts = "Products: " + products.getNumProds();
		display.getChildren().addAll(new Label(numProducts)); 
		display.getStyleClass().add("levels");
		
		return display;
	}
	
	private TableView getProds() {
		TableView<Product> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        // cols
        TableColumn<Product, String> colId   		= new TableColumn<>("ID");
        TableColumn<Product, String> colName      = new TableColumn<>("Name");
        TableColumn<Product, String> colDescrip  = new TableColumn<>("Description");
        TableColumn<Product, Double> colPrice    = new TableColumn<>("Price");
		
        colId.getStyleClass().add("item-header");
        colName.getStyleClass().add("item-header");
        colDescrip.getStyleClass().add("item-header");
        colPrice.getStyleClass().add("item-header");
		
        
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("prodName"));
        colDescrip.setCellValueFactory(new PropertyValueFactory<>("description"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        table.getColumns().addAll(colId, colName, colDescrip, colPrice);
        table.getStyleClass().add("items-container");
        
        table.setItems(products.getProducts());
        
        return table;
	}
	
	private void addProduct() {
	    Dialog<Void> dialog = new Dialog<>();	// make dialog window
	    dialog.setTitle("Add a product");
	    
	    ButtonType createType = new ButtonType("Add");   // make button
		dialog.getDialogPane().getButtonTypes().addAll(		// add buttons to the window
		    createType,
		    ButtonType.CANCEL
		);
	   
	    TextField id = new TextField();	
	    id.setPromptText("ID");
	    TextField name = new TextField();	
	    name.setPromptText("Quantity");
	    TextField description = new TextField();	
	    description.setPromptText("Quantity");
	    TextField price = new TextField();	
	    price.setPromptText("Price");
	    
		// left side
	    VBox labels = new VBox(15, 
	    		new Label("ID:"),
	    		new Label("Name:"),
	    		new Label("Description:"),
	    		new Label("Price:")
	    );
	    
	    // right side
	    VBox fields = new VBox(6,
	     		id, name, description, price);
	    
	    // combine in a horizontal container
	    HBox content = new HBox(3, labels, fields);
	    
	    dialog.getDialogPane().setContent(content);
	        
	    Button createBtn = (Button) dialog.getDialogPane().lookupButton(createType); // get create button to apply functions
	    
	    createBtn.setOnAction(e -> {
	        
	        String prodId = id.getText();
	        String prodName = name.getText();
	        String prodDescrip = description.getText();
	        Double prodPrice = Double.valueOf(price.getText());
	        
	        Product newProd = new Product(prodId, prodName, prodDescrip, prodPrice);
	        
	        products.addProduct(newProd);
	    });

	    dialog.showAndWait();
	}
	
}
