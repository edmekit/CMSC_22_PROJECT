package proj;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class LoginView {
	private final SceneManager sm;
	
	public LoginView(SceneManager sm) {
		this.sm = sm;
	}
	
	public Parent getView() {
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
		        if (sm.getSystem().login(username.getText(), password.getText())) {
		           sm.showDashboard();
		        } else {
		        	errorLabel.setText("No account found.");
		        }
		    });
			    
			    signupBtn.setOnAction(e -> {
			    	String userName = newUsername.getText();
			    	String userPass = newPassword.getText();
			    	String userType = types.getValue();
			    	if (sm.getSystem().addUser(userName, userPass, userType)) {
			    		notifLabel.setText("Sign up succesful. You can now login.");
			    	} else {
			    		notifLabel.setText("You already have an existing account.");
			    	}
			    });
			    
		    return content;
	}
}
