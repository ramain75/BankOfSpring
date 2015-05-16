package org.bankofspring.config;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bankofspring.model.Account;
import org.bankofspring.model.AccountTransaction;
import org.bankofspring.model.Customer;
import org.bankofspring.service.BankOfSpringService;
import org.bankofspring.service.BankOfSpringServiceImpl;
import org.bankofspring.validator.BankOperationValidator;
import org.bankofspring.validator.BankOperationValidatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BankOfSpringAppDataConfig {

	//Creates the beans that we need using the 
	@Bean
	public Customer customer1(){
		Customer customer1 =  new Customer("user1", "test", "customerone", "customer1");
		
		Map<String, Account> accounts = new HashMap<String, Account>();
		accounts.put("account1", account1());
		accounts.put("account2", account2());
		accounts.put("account4", account4());
			
		customer1.setAccounts(accounts);
		
		return customer1;
	
	}
	
	@Bean
	public Customer customer2(){
		Customer customer2 =  new Customer("user2", "test", "customertwo", "customer2");
		
		Map<String, Account> accounts = new HashMap<String, Account>();
		accounts.put(account3().getAccountNumber(), account3());
		accounts.put(account4().getAccountNumber(), account4());
		customer2.setAccounts(accounts);
		
		return customer2;
	}
	
	
	@Bean
	public Account account1(){
		List<Customer> owningCustomers = new ArrayList<Customer>();
		
		Account account1 = new Account("account1", "account1description", owningCustomers);
		account1.setMaxBalanceAmount(170000000);
		
		return account1;
	}
	
	@Bean
	public Account account2(){
		List<Customer> owningCustomers = new ArrayList<Customer>();
		
		Account account2 = new Account("account2", "account2description", owningCustomers);
		account2.setMaxBalanceAmount(100000000);
		
		return account2;
	}
	
	@Bean
	public Account account3(){
		List<Customer> owningCustomers = new ArrayList<Customer>();
		
		Account account3 = new Account("account3", "account3description", owningCustomers);
		account3.setMaxBalanceAmount(150000000);
		account3.setAccountBalance(100);
		
		return account3;
	}
	
	@Bean
	public Account account4(){
		List<Customer> owningCustomers = new ArrayList<Customer>();
		
		Account account4 = new Account("account4", "account4description", owningCustomers);
		account4.setMaxBalanceAmount(100000000);
		account4.setAccountBalance(150);
		
		List<AccountTransaction> transactions = new ArrayList<AccountTransaction>();
		account4.setTransactions(transactions);
		
		return account4;
	}
	
	
	@Bean
	public AccountTransaction txn1(){
		
		AccountTransaction txn1 = new AccountTransaction(account4(), null, 100);
		txn1.setTransactionDate(new GregorianCalendar(2015,1,1).getTime());
		
		account4().getTransactions().add(txn1);
		
		return txn1;
		
	}
	
	@Bean
	public BankOfSpringService bankService(){
		BankOfSpringServiceImpl service = new BankOfSpringServiceImpl();
		service.setValidator(validator());
		
		return service;
	}
	
	@Bean
	public BankOperationValidator validator(){
		return new BankOperationValidatorImpl();
	}
	
}
