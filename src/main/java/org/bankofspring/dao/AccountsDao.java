package org.bankofspring.dao;

import java.util.List;

import org.bankofspring.model.Account;
import org.bankofspring.model.AccountTransaction;

public interface AccountsDao {
	
	public List<Account> getAccounts();
	
	public boolean saveTransaction(AccountTransaction transaction);

}
