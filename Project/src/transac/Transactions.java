package transac;

import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import sales.SaleItem;

public class Transactions {
	private final ObservableMap<String, SaleItem> transacs = FXCollections.observableHashMap();
	
	
	public void addTransac(String id, SaleItem item) {
		transacs.put(id, item);
	}
}
