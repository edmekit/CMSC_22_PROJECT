package transac;

import java.io.Serializable;
import java.time.LocalDate;

public abstract class Transaction implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private final String id;
	private final String recordedBy;
	private boolean voided;
	
	public Transaction(String id, String recordedBy) {
		this.id = id;
		this.recordedBy = recordedBy;
		this.voided = false;
	}
	
	public String getId() {return id;}
	public String getRecordedBy() {return recordedBy;}
	public  boolean isVoided() {return voided;}
	public void markVoided() {voided = true;}
	
	public abstract String getType();
	public abstract String getDetails();
	public abstract double getAmount();
	public abstract String getDate();


}
