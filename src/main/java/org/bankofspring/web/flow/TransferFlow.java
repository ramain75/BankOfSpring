package org.bankofspring.web.flow;

import java.io.Serializable;
import java.util.List;

import org.bankofspring.model.Account;
import org.bankofspring.model.Customer;
import org.bankofspring.model.User;
import org.bankofspring.service.BankOfSpringService;
import org.bankofspring.service.BankOfSpringTransferService;
import org.springframework.beans.factory.annotation.Autowired;

public class TransferFlow implements Serializable {
	private static final long serialVersionUID = 8075291799126328918L;
	
	Account fromAccount;
	Account toAccount;
	User user;
		
	boolean targetAccountOwnAccount;
		
	public boolean isTargetAccountOwnAccount() {
		return targetAccountOwnAccount;
	}
	public void setTargetAccountOwnAccount(boolean targetAccountOwnAccount) {
		this.targetAccountOwnAccount = targetAccountOwnAccount;
	}
	
	public Account getToAccount() {
		return toAccount;
	}
	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public void transfer() {
		
	}
	public Account getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
	}
	
	
	
}
