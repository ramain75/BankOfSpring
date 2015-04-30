package com.bankofspring.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class to record transactions
 * @author Malcolm.Murray
 *
 */
public class AccountTransactions {

	private List<AccountTransaction> transactions;
	
	private static AccountTransactions instance;
	
	private AccountTransactions(){
		this.transactions = new ArrayList<AccountTransaction>();
	}
	
	/**
	 * Get the latest instance
	 * @return
	 */
	public static AccountTransactions getInstance(){
		if(instance == null){
			instance = new AccountTransactions();
		}
		
		return instance;
	}
	
	public void addTransactionRecord(AccountTransaction record){
		transactions.add(record);
	}
	
	public List<AccountTransaction> getTransactions(){
		return this.transactions;
	}
	
}
