/*  Manager class
 * 
 * This class shall serve as the user type with full access, allowed to manage both products and inventory.
 * @author Edmarc Justine Mekitpekit
 * */

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
