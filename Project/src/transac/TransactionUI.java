package transac;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import proj.SceneManager;
import proj.User;

public class TransactionUI {
	private SceneManager sm;
	private Transactions transactions;
	private TableView<Transaction> table;
	private Label balanceLabel = new Label();
	private User user;
	
	public TransactionUI(SceneManager sm) {
		this.sm = sm;
		this.transactions = sm.getTransactions();
		this.user = sm.getSystem().getCurrentUser();
	}
	
	public Parent showTransac() {

		VBox root = new VBox(20);
		root.setPadding(new Insets(25));
		
		Label title = new Label("Transactions");
		title.setMaxWidth(Double.MAX_VALUE);
		title.setAlignment(Pos.CENTER);
		title.getStyleClass().add("title");
		
		HBox levels = getLevels();
		table = getTable();
		VBox.setVgrow(table, Priority.ALWAYS);
		
		Button donate = new Button("Record donation");
		donate.setOnAction(e -> donationForm());
		
		HBox btns = new HBox(5, donate);
		
		root.getChildren().addAll(title, levels, table, btns);
		
		return root;
	}
	
	private HBox getLevels() {
		HBox display = new HBox(balanceLabel);
		display.getStyleClass().add("levels");
		updateBalance();
		
		return display;
	}

	private void updateBalance() {
		balanceLabel.setText("Wallet balance: PHP " + String.format("%.2f", transactions.getBalance()));
		
	}

	private TableView<Transaction> getTable() {
		TableView<Transaction> table = new TableView<>(transactions.getHistory());
		table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		// cols 
		TableColumn<Transaction, String> idCol		    = new TableColumn<>("ID");
		TableColumn<Transaction, String> typeCol 		= new TableColumn<>("Type");
		TableColumn<Transaction, String> detailsCol 	= new TableColumn<>("Details");
		TableColumn<Transaction, String> byCol 			= new TableColumn<>("By");
		TableColumn<Transaction, String> amountCol 		= new TableColumn<>("Amount");
		
		idCol.getStyleClass().add("item-header");
		typeCol.getStyleClass().add("item-header");
		detailsCol.getStyleClass().add("item-header");
		byCol.getStyleClass().add("item-header");
		amountCol.getStyleClass().add("item-header");
		
		idCol.setCellValueFactory(c 		-> new SimpleStringProperty(c.getValue().getId()));
		typeCol.setCellValueFactory(c 		-> new SimpleStringProperty(c.getValue().getType()));
		detailsCol.setCellValueFactory(c 	-> new SimpleStringProperty(c.getValue().getDetails()));
		byCol.setCellValueFactory(c 		-> new SimpleStringProperty(c.getValue().getRecordedBy()));
		amountCol.setCellValueFactory(c 	-> {
			double amt = c.getValue().getAmount();
			return new SimpleStringProperty(amt == 0 ? "-" : String.format("%.2f", amt));
		});
		
		table.getColumns().addAll(idCol, typeCol, detailsCol, byCol, amountCol);
		table.getStyleClass().add("items-container");
		
		return table;
	}

	private void donationForm() {
		TextField donor = new TextField();
		
		ComboBox<String> type = new ComboBox<>();
		type.getItems().addAll(DonationTransaction.CASH, DonationTransaction.ITEM);
		type.setValue(DonationTransaction.CASH);
		
		TextField amount = new TextField();
		TextField itemName = new TextField();
		TextField itemQty = new TextField();
		
		amount.disableProperty().bind(type.valueProperty().isEqualTo(DonationTransaction.ITEM));
		itemName.disableProperty().bind(type.valueProperty().isEqualTo(DonationTransaction.CASH));
		itemQty.disableProperty().bind(type.valueProperty().isEqualTo(DonationTransaction.CASH));
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(15));
		grid.addRow(0, new Label("Donor:"), donor);
		grid.addRow(1, new Label("Type:"), type);
		grid.addRow(2, new Label("Amount (cash):"), amount);
		grid.addRow(3, new Label("Item name:"), itemName);
		grid.addRow(4, new Label("Item quantity:"), itemQty);
		
		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("Record donation");
		dialog.getDialogPane().setContent(grid);
		
		ButtonType record = new ButtonType("Record");
		dialog.getDialogPane().getButtonTypes().addAll(record, ButtonType.CANCEL);
		
		Button recordBtn = (Button) dialog.getDialogPane().lookupButton(record);
		recordBtn.setOnAction(e -> {
			if (type.getValue().equals(DonationTransaction.CASH)) {
				String donorName = donor.getText();
				if (donorName.isEmpty()) {warn("Enter the donor's name."); return;}
				
				Double amt;
				try {
					amt = Double.valueOf(amount.getText());
				}
				catch (NumberFormatException ex) {
					warn("Amount must be a number."); return;
				}
				if (amt <= 0) {
					warn("Amount must be a valid positive number");
					return;
				}
				transactions.recordCashDonation(donorName, amt, user.getUsername());
				transactions.setBalance(transactions.getBalance() + amt);
			}
			else {
				String donorName = donor.getText();
				String name = itemName.getText();
				if (donorName.isEmpty()) {
					warn("Enter the donor's name."); return;
				}
				
				int qty;
				try {
					qty = Integer.valueOf(itemQty.getText());
				}
				catch (NumberFormatException ex) {
					warn("Quantity must be a number."); return;
				}
				if (qty <= 0) {
					warn("Quantity must be a valid positive number");
					return;
				}
				
				transactions.recordItemDonation(donorName, name, qty, user.getUsername());
			}
		});
		
		
		dialog.showAndWait();
		
		
	}
	
	private void warn(String message) {
        sm.getSystem().warn(message);
    }


}
