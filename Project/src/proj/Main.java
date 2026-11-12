package proj;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import product.Product;
import product.ProductList;
import product.ProductUI;
import inventory.InventoryUI;
import inventory.InventoryItem;
import inventory.Inventory;

public class Main extends Application {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage stage) {
		Button managerBtn = new Button("Login as Manager");
		Button clerkBtn = new Button("Login as Clerk");
		
		Label welcome = new Label("Welcome !");
		welcome.setStyle("-fx-font-size: 20px;");
		welcome.setTextAlignment(TextAlignment.CENTER);	
		
		HBox loginBtns = new HBox(15, managerBtn, clerkBtn);
		loginBtns.setAlignment(Pos.CENTER);
		
		// combine in a vertical container
		VBox content = new VBox(10, welcome, loginBtns);	
		content.setPadding(new Insets(30,15,30,15));
		content.setAlignment(Pos.CENTER);
		
		// change to dashboard when iz made
	    managerBtn.setOnAction(e -> {
	    	Dashboard dashboard = new Dashboard();
	        Parent inventoryView = dashboard.getView();

	        Scene scene = new Scene(inventoryView, 700, 500);
	        scene.getStylesheets().add(
				    getClass().getResource("/styles/inventory.css").toExternalForm());
	        
	        stage.setScene(scene);
	    });
	    
	    clerkBtn.setOnAction(e -> {
	    	Dashboard dashboard = new Dashboard();
	        Parent productView = dashboard.getView();

	        Scene scene = new Scene(productView, 700, 500);
	        scene.getStylesheets().add(
	                getClass().getResource("/styles/inventory.css").toExternalForm());

	        stage.setScene(scene);
	    });
		
		StackPane root = new StackPane();
		root.getChildren().addAll(content);
		Scene scene = new Scene(root, 700, 500);
		scene.getStylesheets().add(
			    getClass().getResource("/styles/app.css").toExternalForm());
		stage.setTitle("TBAA Management Systen");
		stage.setScene(scene);
		
		stage.show();
		
		
		}

}
