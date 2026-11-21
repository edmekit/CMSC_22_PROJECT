package proj;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import product.Product;
import product.ProductList;
import product.ProductUI;
import sales.SaleFunctions;
import transac.Transactions;
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
		App system = new App();
    	Inventory inventory = new Inventory();
    	ProductList products = new ProductList();
    	Transactions transactions = new Transactions();
    	SaleFunctions sales = new SaleFunctions(transactions);
		
    	Product product = new Product("1", "Nova", "HAHe", 12.0);
		products.addProduct(product);
		
		// welcome
		Label welcome = new Label("Welcome !");
		welcome.setStyle("-fx-font-size: 20px;");
		welcome.setTextAlignment(TextAlignment.CENTER);	
		
		// form
		TextField username = new TextField();
		username.setPromptText("Username");
		PasswordField password = new PasswordField();
		password.setPromptText("Password");
		Button loginBtn = new Button("Login");
		Label errorLabel = new Label();
		
		VBox login = new VBox(15, username, password, loginBtn, errorLabel);
		VBox left = new VBox(10, welcome, login);
		
		// sign up
		Label signLabel = new Label("No account yet? Sign up here!");
		signLabel.setStyle("-fx-font-size: 20px;");
		signLabel.setTextAlignment(TextAlignment.CENTER);	
		
		TextField newUsername = new TextField();
		newUsername.setPromptText("Username");
		PasswordField newPassword = new PasswordField();
		newPassword.setPromptText("Password");
		ComboBox<String> types = new ComboBox<>();
		types.getItems().addAll("MANAGER", "CLERK");
		
		Label notifLabel = new Label();
		
		Button signupBtn = new Button("Sign Up");
		
		VBox signup = new VBox(15, newUsername, newPassword, types, signupBtn, notifLabel);
		VBox right = new VBox(10, signLabel, signup);
		
		left.setAlignment(Pos.CENTER);
		left.getStyleClass().add("left");
		right.setAlignment(Pos.CENTER);
		right.getStyleClass().add("right");
		left.setPrefWidth(350);
		right.setPrefWidth(350);
		left.setPadding(new Insets(0, 20, 0, 20));
		right.setPadding(new Insets(0, 20, 0, 20));
		HBox content = new HBox(left, right);	
		content.setAlignment(Pos.CENTER);
		
		// change to dashboard when iz made
	    loginBtn.setOnAction(e -> {
	        if (system.login(username.getText(), password.getText())) {
	            User user = system.getCurrentUser();
	            Dashboard dashboard = new Dashboard(user, inventory, products, transactions, sales);
	            
	            Parent dashboardView = dashboard.getView();

		        Scene scene = new Scene(dashboardView, 700, 500);
		        scene.getStylesheets().add(getClass().getResource("/styles/inventory.css").toExternalForm());
		        stage.setScene(scene);
	        } else {
	        	errorLabel.setText("No account found.");
	        }
	    });
	    
	    signupBtn.setOnAction(e -> {
	    	String userName = newUsername.getText();
	    	String userPass = newPassword.getText();
	    	String userType = types.getValue();
	    	if (system.addUser(userName, userPass, userType)) {
	    		notifLabel.setText("Sign up succesful. You can now login.");
	    	} else {
	    		notifLabel.setText("You already have an existing account.");
	    	}
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
