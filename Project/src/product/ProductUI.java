package product;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProductUI {
	
	private Product product;
	
	public ProductUI(Product product) {
		this.product = product;
	}
	
	public Parent showProduct() {
		
		VBox root = new VBox(20);
		root.setPadding(new Insets(25));
		
		Label title = new Label("Product Management");
		title.setMaxWidth(Double.MAX_VALUE);
		title.setAlignment(Pos.CENTER);
		title.getStyleClass().add("title");
		
		HBox levels = getLevels();
		VBox products = getProds();
		Button close = new Button("Close");
		
		root.getChildren().addAll(title, levels, products, close);
		
		return root;
	}
	
	private HBox getLevels() {
		HBox display = new HBox();
		display.getChildren().addAll(new Label("Products: 1")); // hardcoded to keep it simple
		display.getStyleClass().add("levels");
		
		return display;
	}
	
	private VBox getProds() {
		VBox list = new VBox(10);
		list.getStyleClass().add("items-container");
		
		HBox header = new HBox();
		header.getStyleClass().add("item-header");
		
		Label idHeader = new Label("ID");
		Label nameHeader = new Label("Name");
		Label descHeader = new Label("Description");
		Label priceHeader = new Label("Price");
		
		idHeader.setPrefWidth(100);
		nameHeader.setPrefWidth(150);
		descHeader.setPrefWidth(150);
		priceHeader.setPrefWidth(100);
		
		header.getChildren().addAll(idHeader, nameHeader, descHeader, priceHeader);
		
		list.getChildren().add(header);
		
		HBox productEntry = new HBox();
		
		Label id = new Label(product.getId());
		Label name = new Label(product.getProdName());
		Label description = new Label(product.getDescription());
		Label price = new Label(String.format("%.2f", product.getPrice()));
		
		id.setPrefWidth(100);
		name.setPrefWidth(150);
		description.setPrefWidth(150);
		price.setPrefWidth(100);
		
		productEntry.getChildren().addAll(id, name, description, price);
		
		list.getChildren().add(productEntry);
		
		return list;
	}
	
	

}
