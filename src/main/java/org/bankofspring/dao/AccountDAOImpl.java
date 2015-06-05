package org.bankofspring.dao;

import org.bankofspring.model.Account;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;


/**
 * @author slc
 *
 */
public class AccountDAOImpl extends SimpleJdbcDaoSupport implements AccountDAO {

	/**
   *
   */
  public boolean debitAccount( Account account, Long amount ) {
	  return false;
  }

	/**
   *
   */
  public boolean creditAccount( Account account, Long amount ) {
	  return false;
  }

}
