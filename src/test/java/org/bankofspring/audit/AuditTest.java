package org.bankofspring.audit;

import org.bankofspring.service.BankOfSpringService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author slc
 *
 */
public class AuditTest {

	
	@Test
  public void testBob() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("BankOfSpring.xml");
		context.getBean( BankOfSpringService.class ).credit( null, null, null, 0 );
  }
}
