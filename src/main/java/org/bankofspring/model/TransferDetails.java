package org.bankofspring.model;

public class TransferDetails {
	private String fromNumber;
	private String toNumber;
	private double amount;
	
	public TransferDetails() {
		this.fromNumber = "";
		this.toNumber = "";
		this.amount = 0.00;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public double getAmount() {
		return amount;
	}

	public String getFromNumber() {
		return fromNumber;
	}

	public void setFromNumber(String fromNumber) {
		this.fromNumber = fromNumber;
	}

	public String getToNumber() {
		return toNumber;
	}

	public void setToNumber(String toNumber) {
		this.toNumber = toNumber;
	}
}
