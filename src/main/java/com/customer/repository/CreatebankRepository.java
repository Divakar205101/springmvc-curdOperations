package com.customer.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import com.customer.enity.CreateBank;

@Repository
public class CreatebankRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public void saveOrUpdate(CreateBank createBank) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(createBank);
		transaction.commit();
	}
	
	
}
