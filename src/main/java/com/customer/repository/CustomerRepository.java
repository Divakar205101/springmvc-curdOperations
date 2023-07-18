package com.customer.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.customer.enity.BankDetails;
import com.customer.enity.ContactDetails;
import com.customer.enity.CreateBank;
import com.customer.enity.CustomerEntity;





@Repository
public class CustomerRepository {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ContactDetailsRepository contactDetailsRepository;
	
	@Autowired
	private BankDetailsRepository bankDetailsRepository;
	
	public void savebanks(CreateBank createBank) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(createBank);
		transaction.commit();
	}
	
	public void saveOrUpdate(CustomerEntity entity) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.saveOrUpdate(entity);
		transaction.commit();
		
	}
	
    
	
	public List<CustomerEntity> findAll(){
		StringBuilder builder =new StringBuilder();
		builder.append("from CustomerEntity");
		Session session=sessionFactory.openSession();
		org.hibernate.query.Query query=session.createQuery(builder.toString());
		 return query.getResultList();
		}
	
	public void deleteCustomerbyId(Integer id) {
		Session session= sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		CustomerEntity customerEntity = session.load(CustomerEntity.class, id);
		session.delete(customerEntity);
		transaction.commit();
		session.clear();
	}
	
	public List<CustomerEntity> searchData(CustomerEntity e) 
	{
		Session session = sessionFactory.openSession();
		List<CustomerEntity> li = new ArrayList<CustomerEntity>();
		List<CustomerEntity> list = session.createQuery("from CustomerEntity").list();
		Iterator<CustomerEntity> it = list.iterator();
		while(it.hasNext())
		{
			CustomerEntity  customerEntity= (CustomerEntity)it.next();
			if((e.getName().equals(customerEntity.getName())) && (e.getAdharNo().equals(customerEntity.getAdharNo()) && (e.getPanNo().equals(customerEntity.getPanNo()))))
			{
				CustomerEntity obj = new CustomerEntity();
				obj.setId(customerEntity.getId());
				obj.setName(customerEntity.getName());
				obj.setDateofbirth(customerEntity.getDateofbirth());
				obj.setGender(customerEntity.getGender());
				obj.setPanNo(customerEntity.getPanNo());
				obj.setAdharNo(customerEntity.getAdharNo());
				obj.setMaritualstatus(customerEntity.getMaritualstatus());
				li.add(obj);
			}
		}
		return li;
	}
	
	public CustomerEntity findById(Integer id) {
		
		Session session= sessionFactory.openSession();
		CustomerEntity customerEntity = session.get(CustomerEntity.class, id);
		return customerEntity;
		
	}
	
	public CustomerEntity findByName(String name) {
		
		Session session = sessionFactory.openSession();
	    String queryString = "FROM CustomerEntity WHERE name = :name";
	    Query<CustomerEntity> query = session.createQuery(queryString, CustomerEntity.class);
	    query.setParameter("name", name);
	    return query.uniqueResult();
		
	}
	
	 public List<String> getAllNames() {
	    
	      Session session = sessionFactory.openSession();
	      String hql = "SELECT ce.name FROM CustomerEntity ce";
	      Query<String> query = session.createQuery(hql, String.class);
	      return query.list();
	        
	 }
	 
	 public void savetransaction(com.customer.enity.Transaction transaction) {
		
		 Session session= sessionFactory.openSession();
		 Transaction trans = session.beginTransaction();
		
		 CustomerEntity findById =session.get(CustomerEntity.class, transaction.getFromid());
		 
		 BigDecimal DBamount = new BigDecimal(findById.getTotal());
		 BigDecimal UIamount = new BigDecimal(transaction.getFromamount());
		 
		 BigDecimal subtract = DBamount.subtract(UIamount);
		 
		 String string = subtract.toString();
		 
		 findById.setTotal(String.valueOf(string));
			
		 session.saveOrUpdate(findById);
			
			Integer frombankId = transaction.getFrombankId();
			
			
			
			 
			for(ContactDetails c : findById.getContactdetails()) { 
			    	c.setCustomerEntity(findById);
					contactDetailsRepository.saveOrUpdate(c);
				}
			    
				for(BankDetails bank : findById.getBankdetails()) {
					if(frombankId.equals(bank.getId())) {
						
						//BankDetails getbankbyId = bankDetailsRepository.getbankbyId(frombankId);
						bank.setAmount(transaction.getFromamount());
						bank.setCustomerEntity(findById);
						bankDetailsRepository.saveOrUpdate(bank);
					
					}else {
					bank.setCustomerEntity(findById);
					bankDetailsRepository.saveOrUpdate(bank);
				}
				}
			
			CustomerEntity findById2 = session.get(CustomerEntity.class, transaction.getToid());
			String transactionAmount = transaction.getToamount();
			Integer tobankId = transaction.getTobankId();
			BankDetails getbankbyId2 = bankDetailsRepository.getbankbyId(tobankId);						
			
			BigDecimal amount = new BigDecimal(transactionAmount);
			BigDecimal total = new BigDecimal(getbankbyId2.getAmount());

			
			BigDecimal sum = amount.add(total);

			
			String result = sum.toString();
			
			BigDecimal toDBamount = new BigDecimal(findById2.getTotal());
			BigDecimal uptotal = toDBamount.add(amount);
			
			findById2.setTotal(String.valueOf(uptotal));
			
			
			session.saveOrUpdate(findById2);
			
			for(ContactDetails c : findById2.getContactdetails()) { 
		    	c.setCustomerEntity(findById2);
				contactDetailsRepository.saveOrUpdate(c);
			}
		    
			for(BankDetails bank : findById2.getBankdetails()) {
				if(tobankId.equals(bank.getId())) {
					bank.setAmount(String.valueOf(result));
					bank.setCustomerEntity(findById2);
					bankDetailsRepository.saveOrUpdate(bank);
				}else {
				
					bank.setCustomerEntity(findById2);
				bankDetailsRepository.saveOrUpdate(bank);
			
				}
			}
			trans.commit();
			session.close();
		}
	 
	 public List<CreateBank> getAllCreatedBanks(){
			StringBuilder builder =new StringBuilder();
			builder.append("from CreateBank");
			Session session=sessionFactory.openSession();
			org.hibernate.query.Query query=session.createQuery(builder.toString());
			 return query.getResultList();
			}
	 
	 }


