
package org.bankofspring.security;

import org.bankofspring.entities.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author slc
 *
 */
@Component
public class UserHolder {

	@Bean( name = "currentUser" )
	public User createUser() {
		User user = new User();
		user.setUsername( "user01" );
		user.setPassword( "123pass" );
		
		return user;
	}
}
