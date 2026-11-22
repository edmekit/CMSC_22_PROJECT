package transac;

public class DonationTransaction extends Transaction {
	private static final long serialVersionUID = 1L;
	public static final String CASH = "Cash";
	public static final String ITEM = "Item";
	
	private final String donor;
	private final String kind;      
	private final double cashAmount;
	private final String itemName;
	private final int itemQuantity;
	
	// Cash donation
	public DonationTransaction(String id, String recordedBy, String donor, double amount) {
		super(id, recordedBy);
		this.donor = donor;
		this.kind = CASH;
		this.cashAmount = amount;
		this.itemName = "";
		this.itemQuantity = 0;
	}
	
	// Item donation
	public DonationTransaction(String id, String recordedBy, String donor, String itemName, int itemQuantity) {
		super(id, recordedBy);
		this.donor = donor;
		this.kind = ITEM;
		this.cashAmount = 0;
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
	}
	
	public String getDonor() {return donor;}
	public String getKind() {return kind;}
	
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
