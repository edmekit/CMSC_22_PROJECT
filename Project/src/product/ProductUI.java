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
import proj.SceneManager;
import proj.User;

public class ProductUI {
	private ProductList productlist;
	private User user;
	
	public ProductUI(SceneManager sm) {
		this.productlist = sm.getProducts();
		this.user = sm.getSystem().getCurrentUser();
	}
	
	public Parent showProduct() {
		
		VBox root = new VBox(20);
		root.setPadding(new Insets(25));
		
		Label title = new Label("Product Management");
		title.setMaxWidth(Double.MAX_VALUE);
		title.setAlignment(Pos.CENTER);
		title.getStyleClass().add("title");
		
		HBox levels = getLevels();
		TableView<Product> products = getProds();
		
		Button addItem = new Button("Add a product");
    	Button editItem = new Button("Edit selected");
    	Button delItem = new Button("Delete selected");
    	
    	addItem.setManaged(user.canManageProducts());
    	addItem.setVisible(user.canManageProducts());
    	editItem.setManaged(user.canManageProducts());
    	editItem.setVisible(user.canManageProducts());
    	delItem.setManaged(user.canManageProducts());
    	delItem.setVisible(user.canManageProducts());
    	
    	
    	HBox btns = new HBox(5, addItem, editItem, delItem);
		
		root.getChildren().addAll(title, levels, products, btns);
		
		addItem.setOnAction(e -> addProduct());
		delItem.setOnAction(e -> {
			Product product = products.getSelectionModel().getSelectedItem();
			productlist.removeProd(product);
		});
		
		return root;
	}
	
	private HBox getLevels() {
		HBox display = new HBox();
		
		String numProducts = "Products: " + productlist.getNumProds();
		display.getChildren().addAll(new Label(numProducts)); 
		display.getStyleClass().add("levels");
		
		return display;
	}
	
	private TableView<Product> getProds() {
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
        
        table.setItems(productlist.getProducts());
        
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
	   
	    TextField name = new TextField();	
	    name.setPromptText("Product Name");
	    TextField description = new TextField();	
	    description.setPromptText("Description");
	    TextField price = new TextField();	
	    price.setPromptText("Price");
	    
		// left side
	    VBox labels = new VBox(15, 
	    		new Label("Name:"),
	    		new Label("Description:"),
	    		new Label("Price:")
	    );
	    
	    // right side
	    VBox fields = new VBox(6,name, description, price);
	    
	    // combine in a horizontal container
	    HBox content = new HBox(3, labels, fields);
	    
	    dialog.getDialogPane().setContent(content);
	        
	    Button createBtn = (Button) dialog.getDialogPane().lookupButton(createType); // get create button to apply functions
	    
	    createBtn.setOnAction(e -> {
	        
	        String prodId = String.valueOf(productlist.getNumProds() + 1);
	        String prodName = name.getText();
	        String prodDescrip = description.getText();
	        Double prodPrice = Double.valueOf(price.getText());
	        
	        Product newProd = new Product(prodId, prodName, prodDescrip, prodPrice);
	        
	        productlist.addProduct(newProd);
	    });

	    dialog.showAndWait();
	}
	
}
