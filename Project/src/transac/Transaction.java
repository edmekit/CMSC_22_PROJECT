package transac;

public abstract class Transaction {
	private final String id;
	private final String recordedBy;
	
	public Transaction(String id, String recordedBy) {
		this.id = id;
		this.recordedBy = recordedBy;
	}
	
	public String getId() {return id;}
	public String getRecordedBy() {return recordedBy;}
	
	public abstract String getType();
	public abstract String getDetails();
	public abstract double getAmount();

}
