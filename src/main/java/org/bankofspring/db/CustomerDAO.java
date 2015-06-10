package org.bankofspring.db;

import org.bankofspring.model.Customer;

public interface CustomerDAO {

	Customer getById( String id );
}
