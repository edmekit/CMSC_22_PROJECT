package transac;

import inventory.InventoryItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Transactions {
	private final ObservableList<Transaction> history = FXCollections.observableArrayList();
	private int saleId = 0;
	private int donationId = 0;
	
	public void recordSale(InventoryItem item, int quantity, String recordedBy) {
		history.add(0, new SaleTransaction(newSaleId(), recordedBy, item, quantity));
	}
	
	public void recordCashDonation(String donor, double amount, String recordedBy) {
		history.add(0, new DonationTransaction(newDonId(), recordedBy, donor, amount));
	}
	
	public void recordItemDonation(String donor, String itemName, int quantity, String recordedBy) {
		history.add(0, new DonationTransaction(newDonId(), recordedBy, donor, itemName, quantity));
	}
	
	private String newSaleId() {
		return "S" + (saleId++);
	}
	
	private String newDonId() {
		return "D" + (donationId++);
	}
	
	public ObservableList<Transaction> getHistory() {return history;}

	public Object getBalance() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	

}
