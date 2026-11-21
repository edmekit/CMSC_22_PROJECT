package proj;

public class Manager extends User {

	public Manager(String username, String password) {
		super(username, password);
		
	}
	@Override
	public String getRoleName() {
		return ("Manager");
	}
	
	@Override 
    public boolean canManageProducts() {
		return true;
	}
	@Override
	public boolean canManageInventory() {
		return true;
	}
	
}
