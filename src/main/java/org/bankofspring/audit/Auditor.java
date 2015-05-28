package org.bankofspring.audit;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Auditor {
	
	public static final Logger log = Logger.getLogger(Auditor.class); 
	
	@Pointcut("execution(* org.bankofspring.service.BankOfSpringService.credit(..))")
	public void auditme() {}
	
	@Around("auditme()")
	public void auditTransaction(ProceedingJoinPoint joinpoint) {
		
		try {
			log.info("Before transaction...");
			
			joinpoint.proceed();
			
			log.info("After transaction!");
		}
		catch (Throwable t) {
			log.info("Transaction threw an exception");
		}
	}
}
