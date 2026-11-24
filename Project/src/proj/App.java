/* App class
 * 
 * This class shall serve as the class that manages the users of the system,
 *  such as logging in, signing up, logging out, and saving accounts.
 * @author Edmarc Justine Mekitpekit
 * */

package proj;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;

public class App {   
    private List<User> users = new ArrayList<>();
    private User currentUser;

    public App() {
        users.add(new Manager("Frieren", "goathimmel"));
        users.add(new Clerk("Maomao", "jinshisama"));
        
    }

    public boolean login(String username, String password) {
        for (User u : users) {
            if (u.getUsername().equals(username) && u.checkPassword(password)) {
                currentUser = u;
                return true;
            }
        }
        return false;
    }
    
    public boolean addUser(String username, String password, String type) {
    	if (!hasDuplicate(username)) {
    		if (type.equals("MANAGER")) {
    			users.add(new Manager(username, password));
    		}
    		else {
    			users.add(new Clerk(username, password));
    		}
    		return true;
    	} return false;
    }

    public void logout() { 
    	currentUser = null; 
    }
    
    public User getCurrentUser() {
    	return currentUser; 
    	}
    
    private boolean hasDuplicate(String username) {
    	for (User u : users) {
            if (u.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    
    public void setUsersList(Path filepath) {
    	try {
			this.users = User.loadUsers(filepath);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }
    public void saveUsersList(Path filepath) {
    	User.saveUsers(filepath, users);
    }
    
    public void warn(String message) {
        Alert a = new Alert(Alert.AlertType.WARNING, message); //popup dialog with a warning icon
        a.setHeaderText(null);
        a.getDialogPane().getStylesheets().add(getClass().getResource("/styles/inventory.css").toExternalForm());
        a.getDialogPane().getStyleClass().add("warning-dialog");
        a.showAndWait();
    }
}