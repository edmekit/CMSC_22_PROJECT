/**
 * 
 */
/**
 * 
 */
module Project{
	
	requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
	requires javafx.graphics;
    
    
    opens proj to javafx.graphics, javafx.fxml, javafx.base;
    exports proj;
    
    opens inventory to javafx.base;
    opens product to javafx.base;
}