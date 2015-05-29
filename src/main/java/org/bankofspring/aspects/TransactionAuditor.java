package org.bankofspring.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;


@Aspect
public class TransactionAuditor {
	
	@Pointcut("execution(* org.bankofspring.service.BankOfSpringService.debit(..)) ||"
			+ " execution(* org.bankofspring.service.BankOfSpringService.credit(..)) ")
	public void debit(){}
	
	@Around("debit()")
	public void logDebitTimeTaken(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("Running aspect...");
		
		//Get before time
		long start = System.nanoTime();  
		
		//Continue normal behaviour
		pjp.proceed();
		
		//Get the elapsed time
		long elapsedTime = System.nanoTime() - start;
		
		System.out.println("Operation took: "+elapsedTime+" ms to run");

	}
	
	

}
