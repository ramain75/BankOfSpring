package org.bankofspring.audit;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.bankofspring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * We'll use aspect oriented programming to determine what method calls we want
 * to audit.
 * 
 * @author slc
 *
 */
@Aspect
@Component
public class AuditAspect {

	private static final Logger LOG = Logger.getLogger(AuditAspect.class);

	@Autowired
	@Qualifier("currentUser")
	private User currentUser;

	@Around("@annotation(audit)")
	public Object audit(ProceedingJoinPoint joinpoint, Audit audit) throws Throwable {
		StringBuilder sb = new StringBuilder("Method [").append(joinpoint.getSignature()).append("] ");
		// log parameters if necessary
		if (audit.logParameters()) {
			sb.append("with parameters ").append(Arrays.deepToString(joinpoint.getArgs())).append("] ");
		}
		sb.append("called by [").append(currentUser != null ? currentUser.getUsername() : "Anonymous").append("]");
		
		LOG.info(sb.toString());

		return joinpoint.proceed();
	}
}
