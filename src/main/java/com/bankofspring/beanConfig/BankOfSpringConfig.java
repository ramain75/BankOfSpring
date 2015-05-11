package com.bankofspring.beanConfig;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bankofspring.model.Account;
import com.bankofspring.model.AccountTransactions;
import com.bankofspring.model.Customer;

@Configuration
public class BankOfSpringConfig {

	@Bean
	public Customer customerBean1(){
		return new Customer("TestCustomer1", "testpass", "Test Customer", "123456789");
	}
	
	@Bean
	public Customer customerBean2(){
		return new Customer("TestCustomer2", "testpass", "Test Customer2", "123456799");
	}
	
	@Bean
	public Customer customerBean3(){
		return new Customer("TestCustomer3", "testpass", "Test Customer3", "123456999");
	}
	
	@Bean
	public Account account1(){	
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(customerBean1());
		customers.add(customerBean2());
		return new Account("123456789", "Standard Joint Current Account", customers);
	}
	
	@Bean
	public Account account2(){	
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(customerBean3());
		return new Account("12345999", "Standard Current Account", customers);
	}
	
	@Bean(name="transactionLog")
	public AccountTransactions transactionsLogger(){
		return AccountTransactions.getInstance();
	}

}
