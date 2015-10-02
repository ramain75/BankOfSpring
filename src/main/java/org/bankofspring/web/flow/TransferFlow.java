package org.bankofspring.web.flow;

import java.io.Serializable;

import org.bankofspring.model.Account;
import org.bankofspring.model.User;


public class TransferFlow implements Serializable {
	private static final long serialVersionUID = 8075291799126328918L;
	
	Account fromAccount;
	Account toAccount;
	long amount;
	User user;
	boolean transferToOwnAccount;
	
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public void setAmount(String amount) {
		if (amount == null) {
			this.amount = -1;
			return;
		}
		this.amount = Long.parseLong(amount);
	}
	
		
	public boolean isTransferToOwnAccount() {
		return transferToOwnAccount;
	}
	public void setTransferToOwnAccount(boolean targetAccountOwnAccount) {
		this.transferToOwnAccount = targetAccountOwnAccount;
	}
	public void setTransferToOwnAccount(String targetAccountOwnAccount) {
		this.transferToOwnAccount = Boolean.parseBoolean(targetAccountOwnAccount);
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
