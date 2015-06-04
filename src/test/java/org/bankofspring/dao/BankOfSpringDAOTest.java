package org.bankofspring.dao;

import java.sql.SQLException;

import org.junit.*;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:BankOfSpring.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BankOfSpringDAOTest {

	@Autowired
	BankOfSpringDAO bankOfSpringDao;
	
	/**
	 * Basic test to make sure we have a DB connection
	 * @throws SQLException 
	 */
	@Test
	public void testConnectionIsValid() throws SQLException{
		assertTrue(bankOfSpringDao.getDataSource().getConnection().isValid(1));
	}
	
}
