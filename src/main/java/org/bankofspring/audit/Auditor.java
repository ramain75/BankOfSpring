package org.bankofspring.audit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.bankofspring.model.User;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class Auditor {
	@Pointcut("execution (* org.bankofspring.service.BankOfSpringService.debit(..))")
	public void audit() {
		
	}
	@Pointcut("execution (* org.bankofspring.service.BankOfSpringService.debit (org.bankofspring.model.User,..)) && args( user ,..)")
	public void auditUser(User user) {
		
	}
	@Before("audit()")
	public void auditBefore() {
		System.out.println("auditing before operation");
	}
	@After("audit()")
	public void auditAfter() {
		System.out.println("auditing after operation");
	}
	@After("auditUser(user)")
	public void auditAfterUser(User user) {
		System.out.println("auditing after operation with user " +user.getUsername());
	}
	
	@Around("audit()")
	public void auditTime(ProceedingJoinPoint jointPoint) {
		long startTime = System.currentTimeMillis();
		try {
			jointPoint.proceed();
		} catch (Throwable e) {
			System.out.println("failed to proceed on jointpoint");
			e.printStackTrace();
		}
		long timeElapsed = System.currentTimeMillis() - startTime;
		System.out.println("timeElapsed " + timeElapsed);

	}
}
