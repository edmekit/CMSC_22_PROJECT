/* SceneManager class
 * 
 * This class shall serve as the class that handles window switching and gives every screen access to the shared data,
 *  such as inventory, products, and transactions.
 * @author Edmarc Justine Mekitpekit
 * */

package proj;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import product.ProductList;
import sales.SaleFunctions;
import transac.Transactions;
import inventory.Inventory;

public class SceneManager {
    private static final double WIDTH = 700, HEIGHT = 500;

    private final Stage stage;
    private final App system;
    private final Inventory inventory;
    private final ProductList products;
    private final Transactions transactions;
    private final SaleFunctions sales;

    public SceneManager(Stage stage, App system, Inventory inventory,
                        ProductList products, Transactions transactions, SaleFunctions sales) {
        this.stage = stage;
        this.system = system;
        this.inventory = inventory;
        this.products = products;
        this.transactions = transactions;
        this.sales = sales;
    }

   
    public App getSystem() { return system; }
    public Inventory getInventory() { return inventory; }
    public ProductList getProducts() { return products; }
    public Transactions getTransactions() { return transactions; }
    public SaleFunctions getSales() { return sales; }

    // one place that actually swaps scenes
    private void show(Parent view, String css) {
        Scene scene = new Scene(view, WIDTH, HEIGHT);
        scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
        stage.setScene(scene);
    }

    public void showLogin() {
        show(new LoginView(this).getView(), "/styles/app.css");
    }

    public void showDashboard() {
        if (system.getCurrentUser() == null) { // nobody logged in, send back
            showLogin();
            return;
        }
        show(new Dashboard(this).getView(), "/styles/inventory.css");
    }

    public void logout() {
        system.logout();
        showLogin();
    }
}