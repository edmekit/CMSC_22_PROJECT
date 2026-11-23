package transac;

import java.time.LocalDate;

import inventory.InventoryItem;

public class SaleTransaction extends Transaction {
	private static final long serialVersionUID = 1L;
	private final InventoryItem item;
	private final int quantity;
	private final double total;
	
	public SaleTransaction(String id, String recordedBy, InventoryItem item, int quantity) {
		super(id, recordedBy);
		this.item = item;
		this.quantity = quantity;
		this.total = item.getPrice() * quantity;
	}
	
	public InventoryItem getItem() {return item;}
	public int getQuantity() {return quantity;}
	
	@Override
	public String getType() {return "Sale";}
	
	@Override
	public String getDetails() {return item.getProdName() + " x" + quantity;}
	
	@Override
	public double getAmount() {return total;}
	
	@Override
	public String getDate() {return "-";}

}
