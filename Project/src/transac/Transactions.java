package transac;

import javafx.collections.FXCollections;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import inventory.InventoryItem;
import javafx.collections.ObservableList;

public class Transactions implements Serializable {
	private static final long serialVersionUID = 1L;
	private transient ObservableList<Transaction> history = FXCollections.observableArrayList();
	private int saleId = 0;
	private int donationId = 0;
	private double balance = 0;
	
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

	public double getBalance() {
		return this.balance;
	}
	
	public void setBalance(Double amt) {
		this.balance = amt;
	}
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(new ArrayList<>(history)); 
    }

    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        List<Transaction> list = (List<Transaction>) ois.readObject(); 
        history = FXCollections.observableArrayList(list); 
    }
	
	public void saveTransactions(Path filepath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(filepath))) {
            oos.writeObject(this);
            //serialize the database such that it is saved to filepath, retains being an object when loaded
            System.out.println("Transactions successfully saved to " + filepath);
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
            //catch if the filepath is invalid, or an error is caught in the Input/Output of the system
        }
    }
	
	public static Transactions loadTransacts(Path filepath) {
		try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(filepath))){
			try {
				Transactions transacs = (Transactions) ois.readObject();
				return transacs;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return new Transactions();
			}	
		} catch(IOException e) {
			return new Transactions();
		}
	}
}