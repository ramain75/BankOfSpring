package org.bankofspring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Class to represent a bank customer - extends user
 * 
 *
 */
@Entity
@Table(name = "customer")
@PrimaryKeyJoinColumn(name="user_id")
public class Customer extends User implements Serializable {
   

	private static final long serialVersionUID = -1761880572951633458L;
	
	private String name;
	
	//Map the user's accounts - not that we use Eager loading to make sure we always load the accounts
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="customer_account", joinColumns=@JoinColumn(name="customer_id"), 
		inverseJoinColumns=@JoinColumn(name="number")) 
	private List<Account> accounts; 
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Account> getAccounts() {
		return accounts;
	}
	
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((this.getId() == null) ? 0 : this.getId().hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		if (this.getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!this.getId().equals(other.getId()))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
