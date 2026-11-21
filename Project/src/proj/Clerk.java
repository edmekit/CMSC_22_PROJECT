package proj;

public class Clerk extends User {

	public Clerk(String username, String password) {
		super(username, password);
		
	}
	@Override
	public String getRoleName() {
		return ("Clerk");
	}
	
	@Override 
    public boolean canManageProducts() {
		return false;
	}
	@Override
	public boolean canManageInventory() {
		return false;
	}
	
}
