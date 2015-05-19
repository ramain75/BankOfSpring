
package org.bankofspring.audit;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Interface indicating that method annotated should be audited.
 * 
 * @author slc
 *
 */
@Retention( RetentionPolicy.RUNTIME )
@Documented
@Target( ElementType.METHOD )
@Inherited
public @interface Audit {

	/**
	 * @return Whether the audit should log the parameters.
	 */
	boolean logParameters() default false;
}
