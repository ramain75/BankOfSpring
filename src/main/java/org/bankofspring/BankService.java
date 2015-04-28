package org.bankofspring;


import org.bankofspring.model.*;

public interface BankService {
	/*
	 * debit an amount from an account to another account (both bankOfSpring accounts)
	 */
	public boolean debit (User loggedInUser, Account fromAccount, Account toAccount, long amount);
	/*
	 * debit an amount from an BankOfSpring account (e.g cash withdrawal)
	 */
	public boolean debit (User loggedInUser, Account fromAccount, long  amount);
	/*
	 * credit an amount from an account to another account (both bankofSpring accounts)
	 */
	public boolean credit (User loggedInUser, Account fromAccount, Account toAccount, long amount);
	/*
	 * credit an amount to an account (e.g cash deposit)
	 */
	public boolean credit (User loggedInUser, Account toAccount, long amount);
}
