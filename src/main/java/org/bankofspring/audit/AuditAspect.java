
package org.bankofspring.audit;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * We'll use aspect oriented programming to determine what method calls we want to audit.
 * 
 * @author slc
 *
 */
@Aspect
@Component
public class AuditAspect {

	private static final Logger LOG = Logger.getLogger( AuditAspect.class );

	@Around( "@annotation(audit)" )
	public Object audit( ProceedingJoinPoint joinpoint, Audit audit ) throws Throwable {
		LOG.info( "Calling [" + joinpoint.getSignature() + "]" );
		if ( audit.logParameters() ) {
			LOG.info( "With params: " + Arrays.deepToString( joinpoint.getArgs() ) );
		}

		return joinpoint.proceed();
	}
}
