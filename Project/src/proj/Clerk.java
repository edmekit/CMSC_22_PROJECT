/* Clerk class
 * 
 * This class shall serve as the user type with limited access, not allowed to manage products or inventory.
 * @author Edmarc Justine Mekitpekit
 * */

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
