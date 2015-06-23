
package org.bankofspring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.springframework.core.style.ToStringCreator;

/**
 * Class to represent a bank account we have added a maxBalanceAmount to maintain the maximum
 * allowed for an account note that an Account has a bidirectional relationship with customer and
 * that more than one customer can be linked to an account (joined account) we have added facilities
 * to add customer against the account but no facilities as yet to remove an customer from account
 */

@Entity
@Table(name = "account")
public class Account implements Serializable {
	
	private static final long serialVersionUID = -1189672094336961978L;

	@Id
	@Column(name = "number")
	private String accountNumber;
	
	@Column(name = "description")
	private String accountDescription;
	
	@Column(name = "balance")
	private long accountBalance;
	
	@Column(name = "max_balance")
	private long maxBalanceAmount;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy="accounts")  
	private List<Customer> customers;

	
	//Add a version column to allow Hibernate optimistic locking to ensure that concurrency is handled properly
	@Version
	private long version;

	
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
	
	public List<Customer> getCustomers() {
		return customers;
	}
	
	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
		    + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		return result;
	}
	
	public long getVersion() {
		return version;
	}
	
	public void setVersion(long version) {
		this.version = version;
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
	
	/**
	 * Method to debit the account
	 * - not this is set to be transient to show that it is not
	 * related to the database table, and is performing an operation
	 * @param amount
	 * @return
	 */
	@Transient
	public boolean debitAccount(long amount){
		if(getAccountBalance() - amount >= 0){
			this.setAccountBalance(getAccountBalance() - amount);
			return true;
		}else{
			return false;
		}
	}
	
	@Transient
	public boolean creditAccount(long amount){
		if(getAccountBalance() + amount <= maxBalanceAmount){
			this.setAccountBalance(getAccountBalance() + amount);
			return true;
		}else{
			return false;
		}
	}

}
