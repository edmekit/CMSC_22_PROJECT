
package proj;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class User implements Serializable {
	private static final long serialVersionUID = 1L;
    private final String username;
    private final String password; 

    protected User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { 
    	return username; 
    	}
    public boolean checkPassword(String input) { 
    	return password.equals(input); 
    	}

    public abstract String getRoleName();
    public abstract boolean canManageProducts();
    public abstract boolean canManageInventory();

    public static void saveUsers(Path filepath, List<User> usr) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(filepath))) {
            oos.writeObject(usr);
            //serialize the database such that it is saved to filepath, retains being an object when loaded
            System.out.println("Users successfully saved to " + filepath);
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
            //catch if the filepath is invalid, or an error is caught in the Input/Output of the system
        }
    }
	
	public static ArrayList<User> loadUsers(Path filepath) throws ClassNotFoundException {
		if(Files.exists(filepath)) {
			try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(filepath))){
				List<User> usr = (ArrayList<User>) ois.readObject();
				return (ArrayList<User>) usr; 	
			} catch(IOException e) {
				ArrayList<User> usr = new ArrayList<User>();
				usr.add(new Manager("Frieren", "goathimmel"));
		        usr.add(new Clerk("Maomao", "jinshisama"));
		        return usr;
			}
		}else {
			ArrayList<User> usr = new ArrayList<User>();
			usr.add(new Manager("Frieren", "goathimmel"));
	        usr.add(new Clerk("Maomao", "jinshisama"));
	        return usr;
		}
		
	}
}
	
