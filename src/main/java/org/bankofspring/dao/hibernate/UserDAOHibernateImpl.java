package org.bankofspring.dao.hibernate;

import java.util.List;

import org.bankofspring.dao.UserDAO;
import org.bankofspring.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

@Repository("userDAO")
public class UserDAOHibernateImpl extends HibernateDaoSupport implements UserDAO {

	@Autowired
	public void setupSessionFactory( SessionFactory sessionFactory ) {
		setSessionFactory( sessionFactory );
	}
	
	@Override
	public List<User> getAllUsers() {
		return getHibernateTemplate().find("from " + User.class.getName());
	}

}
