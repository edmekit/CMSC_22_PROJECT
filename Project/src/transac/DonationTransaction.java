package transac;

import java.time.LocalDate;

public class DonationTransaction extends Transaction {
	private static final long serialVersionUID = 1L;
	public static final String CASH = "Cash";
	public static final String ITEM = "Item";
	
	private final String donor;
	private final String kind;      
	private final double cashAmount;
	private final String itemName;
	private final int itemQuantity;
	private LocalDate date;
	
	// Cash donation
	public DonationTransaction(String id, String recordedBy, String donor, double amount, LocalDate date) {
		super(id, recordedBy);
		this.donor = donor;
		this.kind = CASH;
		this.cashAmount = amount;
		this.itemName = "";
		this.itemQuantity = 0;
		this.date = date;
	}
	
	// Item donation
	public DonationTransaction(String id, String recordedBy, String donor, String itemName, int itemQuantity, LocalDate date) {
		super(id, recordedBy);
		this.donor = donor;
		this.kind = ITEM;
		this.cashAmount = 0;
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
		this.date = date;
	}
	
	public String getDonor() {return donor;}
	public String getKind() {return kind;}
	public String getDate() {return (date != null) ?date.toString() : "";}
	
	@Override
	public String getType() {return "Donation (" + kind + ")";}
	
	@Override
	public String getDetails() {
		if (kind.equals(CASH)) {return "From " + donor;}
		return "From " + donor + ": " + itemName + " x" + itemQuantity;
	}
	
	@Override
	public double getAmount() {return cashAmount;}
	

}
