package org.bankofspring.controllers;

import static org.junit.Assert.*;

import org.bankofspring.dao.jdbc.AccountDAOJDBCImpl;
import org.bankofspring.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;	

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest  {
	@Mock
	AccountDAOJDBCImpl accountDAO;
	@InjectMocks
	AccountController controller = new AccountController();

	@Test
	public void testShouldReturnAccountList() {
		Account acc1 = new Account();
		acc1.setAccountNumber("account1");
		Account acc2 = new Account();
		acc2.setAccountNumber("account2");
		List<Account> list = Arrays.asList(acc1,acc2);
		
		when(accountDAO.getAccountsForCustomer(1)).thenReturn(list);
		ExtendedModelMap model = new ExtendedModelMap();
		String view = controller.showAccountListForCustomer(1, model);
		assertEquals("accountslist",view);
		List<Account> modelList = (List<Account>) model.get("accounts");
		assertEquals(2,modelList.size());
	}
}
