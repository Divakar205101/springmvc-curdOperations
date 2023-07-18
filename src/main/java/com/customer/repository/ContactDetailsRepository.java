package com.customer.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customer.enity.ContactDetails;


@Repository
public class ContactDetailsRepository {
   
	@Autowired
	private SessionFactory sessionFactory;
	
	public void saveOrUpdate(ContactDetails entity) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(entity);
		transaction.commit();
	}
	
	
	
	public void deleteCustomarAddressbyId(Integer id) {
		Session session= sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		ContactDetails contactDetails = session.get(ContactDetails.class, id);
		session.delete(contactDetails);
		transaction.commit();
		session.close();
	}
	
	
	
}
