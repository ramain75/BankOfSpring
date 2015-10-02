package org.bankofspring.audit;

import java.util.ArrayList;
import java.util.Iterator;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.bankofspring.model.User;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class BankOfSpringServiceTimer {
	private ArrayList<AuditResult> actions = new ArrayList<AuditResult>();
	
	/**
	 * Matches service methods, which are expected to take a User as the first parameter, and return a boolean
	 * 
	 * @param user User who requested the action
	 */
	@Pointcut( "execution(boolean org.bankofspring.service.BankOfSpringService.*(" +
			"org.bankofspring.model.User, ..)) && args( user, .. )" )
	public void serviceAction( User user ) {
	}
	
	/**
	 * Time the service method, also recording user, success status, and any errors thrown
	 * 
	 * @param joinpoint points at the method to run
	 * @param user user who initiated the method call
	 * @return true iff the method succeeded
	 * @throws Throwable any error that the method threw
	 */
	@Around("serviceAction( user )")
	public boolean timeServiceAction( ProceedingJoinPoint joinpoint, User user ) throws Throwable {
		
		AuditResult result = new AuditResult( joinpoint.getSignature().getName(), user );
		
		try {
			result.setSuccess( (Boolean) joinpoint.proceed() );
			return result.wasSuccess();
		}
		catch ( Throwable e ) {
			result.setThrown( e );
			throw result.getThrown();
		}
		finally {
			result.setEnd( System.currentTimeMillis() );
			this.actions.add( result );
		}
	}
	
	/**
	 * Get an iterator over all actions audited since the bean was initialised
	 * (TODO: purge old actions so that memory doesn't fill up)
	 * 
	 * @return an iterator over all actions which were audited
	 */
	public Iterator<AuditResult> getActions() {
		return this.actions.iterator();
	}
}
