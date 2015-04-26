package org.bankofspring.dao;

import java.util.List;

import org.bankofspring.entities.Account;
import org.bankofspring.entities.AccountTransaction;
import org.bankofspring.entities.Customer;
import org.bankofspring.exception.AccountNotFoundException;

public class BankDAOInMemoryImpl implements BankDAO {

	private List<Customer> customers;

	@Override
	public void debitAccount(Account account, long amount) {
		account.setAccountAmount(account.getAccountAmount() - amount);
	}

	@Override
	public void creditAccount(Account account, long amount) {
		account.setAccountAmount(account.getAccountAmount() + amount);
	}

	@Override
	public void logTransaction(Account fromAccount, Account toAccount,
			long amount) {
		// create the pojo (maybe should use a constructor)
		AccountTransaction accountTransaction = new AccountTransaction();
		accountTransaction.setFromAccount(fromAccount);
		accountTransaction.setToAccount(toAccount);
		accountTransaction.setAmount(amount);
		
		// set the relationships
		fromAccount.getTransactions().add(accountTransaction);
		toAccount.getTransactions().add(accountTransaction);
	}

	/**
	 * {@inheritDoc}
	 * Could be made much more efficient, but just wanted something simple.
	 */
	@Override
	public Account getAccount(long id) {
		for (Customer customer : customers) {
			for (Account account : customer.getAccounts()) {
				if (id == account.getAccountNumber()) {
					return account;
				}
			}
		}
		throw new AccountNotFoundException("No account found with ID: " + id);
	}
	
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}
}
