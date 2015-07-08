package org.bankofspring.dao.hibernate;

import org.bankofspring.dao.AbstractAccountDAOTest;
import org.bankofspring.dao.AccountDAO;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:BankOfSpring-ds-test.xml", "classpath:BankOfSpring.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountDAOHibernateTest extends AbstractAccountDAOTest {

	@Autowired
	@Qualifier("hibernateAccountDao")
	private AccountDAO accountDAO;

	@Override
	protected AccountDAO getDao() {
		return this.accountDAO;
	}
}
