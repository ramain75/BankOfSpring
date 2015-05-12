package org.bankofspring.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.bankofspring.entity.Account;
import org.bankofspring.entity.Customer;
import org.bankofspring.entity.User;
import org.bankofspring.service.BankService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankOfSpringApp {
	
	// We have a bunch of hard-coded customers
	
	@Bean
	public Customer alice() {
		Customer alice = new Customer();
		alice.setCustomerId( 1 );
		alice.setName( "Alice Aqua" );
		alice.setUsername( "alice" );
		alice.setPassword( "auqu" );
		return alice;
	}
	
	@Bean
	public Customer bob() {
		Customer bob = new Customer();
		bob.setCustomerId( 2 );
		bob.setName( "Bob Brown" );
		bob.setUsername( "bob" );
		bob.setPassword( "nworb" );
		return bob;
	}
	
	@Bean
	public Customer carol() {
		Customer carol = new Customer();
		carol.setCustomerId( 3 );
		carol.setName( "Carol Chartreuse" );
		carol.setUsername( "carol" );
		carol.setPassword( "esuertrahc" );
		return carol;
	}
	
	@Bean
	public Customer dave() {
		Customer dave = new Customer();
		dave.setCustomerId( 4 );
		dave.setName( "Dave Damson" );
		dave.setUsername( "dave" );
		dave.setPassword( "nosmad" );
		return dave;
	}
	
	// Eve isn't a customer, but she is a user
	
	@Bean
	public User eve() {
		User eve = new User();
		eve.setUsername( "eve" );
		eve.setPassword( "dlareme" );
		return eve;
	}
	
	// The BankService is also a bean
	
	@Bean
	public BankService bankService() {
		return new BankService();
	}

	// Let's have a map of all users in the system
	
	@Bean
	public Map<String, User> users() {
		Map<String, User> users = new TreeMap<String, User>();
		users.put( "alice", alice() );
		users.put( "bob", bob() );
		users.put( "carol", carol() );
		users.put( "dave", dave() );
		users.put( "eve", eve() );
		return users;
	}
	
	// And a map from users to accounts
	
	@Bean
	public Map<User, List<Account>> accounts() {
		Account aliceCurrent = new Account();
		aliceCurrent.setAccountNumber( "000001" );
		aliceCurrent.setDescription( "Alice Aqua Current Account" );
		aliceCurrent.setBalance( 100000 );
		aliceCurrent.setCustomer( alice() );
		
		Account aliceSavings = new Account();
		aliceSavings.setAccountNumber( "000002" );
		aliceSavings.setDescription( "Alice Aqua Savings Account" );
		aliceSavings.setBalance( 120000 );
		aliceSavings.setCustomer( alice() );
		
		Account bobCurrent = new Account();
		bobCurrent.setAccountNumber( "000003" );
		bobCurrent.setDescription( "Bob Brown Current Account" );
		bobCurrent.setBalance( 80000 );
		bobCurrent.setCustomer( bob() );
		
		Account daveCurrent = new Account();
		daveCurrent.setAccountNumber( "000004" );
		daveCurrent.setDescription( "Dave Damson Current Account" );
		daveCurrent.setBalance( 40000 );
		daveCurrent.setCustomer( dave() );
		
		Map<User, List<Account>> accounts = new HashMap<User, List<Account>>( 5 );
		accounts.put( alice(), Arrays.asList( new Account[] { aliceCurrent, aliceSavings } ) );
		accounts.put( bob(), Arrays.asList( new Account[] { bobCurrent } ) );
		accounts.put( carol(), Collections.<Account>emptyList() ); // No accounts
		accounts.put( dave(), Arrays.asList( new Account[] { daveCurrent } ) );
		return accounts;
	}
}
