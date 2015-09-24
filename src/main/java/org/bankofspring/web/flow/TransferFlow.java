package org.bankofspring.web.flow;

import java.io.Serializable;

import org.bankofspring.model.Account;
import org.bankofspring.model.Customer;
import org.bankofspring.model.User;
import org.bankofspring.service.BankOfSpringService;
import org.springframework.beans.factory.annotation.Autowired;

public class TransferFlow implements Serializable {
	private static final long serialVersionUID = 8075291799126328918L;
	
	Account fromAccount;
	Account toAccount;
	boolean targetAccountOwnAccount;
	public boolean isTargetAccountOwnAccount() {
		return targetAccountOwnAccount;
	}
	public void setTargetAccountOwnAccount(boolean targetAccountOwnAccount) {
		this.targetAccountOwnAccount = targetAccountOwnAccount;
	}
	Customer targetCustomer;
	Customer startCustomer;
	public Account getToAccount() {
		return toAccount;
	}
	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}
	public Customer getTargetCustomer() {
		return targetCustomer;
	}
	public void setTargetCustomer(Customer targetCustomer) {
		this.targetCustomer = targetCustomer;
	}
	public Customer getStartCustomer() {
		return startCustomer;
	}
	public void setStartCustomer(Customer startCustomer) {
		this.startCustomer = startCustomer;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	User user;
	
	@Autowired
	BankOfSpringService service;
	public void transfer() {
		
	}
	public Account getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}
	
	
}
