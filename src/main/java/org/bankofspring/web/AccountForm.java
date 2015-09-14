package org.bankofspring.web;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class AccountForm {
	private String number;
	private String description;
	private long balance;
	private long maxbalance;
	@NotNull (message="Account Number is mandatory")
	@NotBlank (message="Account Number is mandatory")
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	@NotNull (message="Description is mandatory")
	@NotBlank (message="Description is mandatory")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public long getMaxbalance() {
		return maxbalance;
	}
	public void setMaxbalance(long maxbalance) {
		this.maxbalance = maxbalance;
	}
}
