package transac;

import java.io.Serializable;

public abstract class Transaction implements Serializable {
	private static final long serialVersionUID = 1L;
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
