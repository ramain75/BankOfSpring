
package org.bankofspring.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.style.ToStringCreator;

/**
 * Class to represent a bank account we have added a maxBalanceAmount to maintain the maximum
 * allowed for an account note that an Account has a bidirectional relationship with customer and
 * that more than one customer can be linked to an account (joined account) we have added facilities
 * to add customer against the account but no facilities as yet to remove an customer from account
 */
public class Account {

	private String accountNumber;
	private String accountDescription;
	private long accountBalance;
	private long maxBalanceAmount;

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber( String accountNumber ) {
		this.accountNumber = accountNumber;
	}

	public String getAccountDescription() {
		return accountDescription;
	}

	public void setAccountDescription( String accountDescription ) {
		this.accountDescription = accountDescription;
	}

	public long getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance( long accountBalance ) {
		this.accountBalance = accountBalance;
	}

	public long getMaxBalanceAmount() {
		return maxBalanceAmount;
	}

	public void setMaxBalanceAmount( long maxBalanceAmount ) {
		this.maxBalanceAmount = maxBalanceAmount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
		    + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
		  return true;
		if ( obj == null )
		  return false;
		if ( getClass() != obj.getClass() )
		  return false;
		Account other = (Account) obj;
		if ( accountNumber == null ) {
			if ( other.accountNumber != null )
			  return false;
		}
		else if ( !accountNumber.equals( other.accountNumber ) )
		  return false;
		return true;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return new ToStringCreator( this )
		    .append( "number", accountNumber )
		    .append( "description", accountDescription )
		    .append( "balance", accountBalance )
		    .toString();
	}

}
