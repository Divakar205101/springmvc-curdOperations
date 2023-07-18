package com.customer.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customer.enity.BankDetails;
import com.customer.enity.ContactDetails;
import com.customer.enity.CustomerEntity;

@Repository
public class BankDetailsRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public void saveOrUpdate(BankDetails entity) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(entity);
		transaction.commit();
	}
	
	
	
	public void deleteCustomarbankbyId(Integer id) {
		Session session= sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		BankDetails bankDetails = session.get(BankDetails.class, id);
		session.delete(bankDetails);
		transaction.commit();
		session.close();
	}
	
	public BankDetails getbankbyId(Integer id) {
	Session session = sessionFactory.openSession();
	Transaction beginTransaction = session.beginTransaction();
	BankDetails bankDetails = session.get(BankDetails.class, id);
	   return bankDetails;
	}
	
}
