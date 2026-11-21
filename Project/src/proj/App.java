package proj;

import java.util.ArrayList;
import java.util.List;

public class App {   
    private final List<User> users = new ArrayList<>();
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
}