package com.customer.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.enity.BankDetails;
import com.customer.enity.ContactDetails;
import com.customer.enity.CreateBank;
import com.customer.enity.CustomerEntity;
import com.customer.enity.Transaction;
import com.customer.repository.BankDetailsRepository;
import com.customer.repository.ContactDetailsRepository;
import com.customer.repository.CreatebankRepository;
import com.customer.repository.CustomerRepository;





@Service
public class CustomerService {
   
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ContactDetailsRepository contactDetailsRepository;
	
	@Autowired
	private BankDetailsRepository bankDetailsRepository;
	
	@Autowired
	private CreatebankRepository createbankRepository ;
	
	public List<CustomerEntity> getAllEmployeeDetails() {
		return customerRepository.findAll();
	}
	
	
	public void saveorupdate(CustomerEntity customerEntity) {
		
		
	     
		
		if(customerEntity.getId()!=null){
			System.out.println(customerRepository.findById(customerEntity.getId()));
			CustomerEntity findById = customerRepository.findById(customerEntity.getId());
		/* data base data */
		List<ContactDetails> contactdetails = findById.getContactdetails();
		List<BankDetails> bankdetails = findById.getBankdetails();   
		System.out.println(bankdetails+"--db");
		/* ui data */
		List<ContactDetails> contactdetails2 = customerEntity.getContactdetails();
		List<BankDetails> bankdetails2 = customerEntity.getBankdetails();
		System.out.println(bankdetails2+"---ui");
		System.out.println(contactdetails+"------data base data");
		System.out.println(contactdetails2+"------ui data");
          for (ContactDetails cd : contactdetails) {
        	    boolean found = false;
        	    for (ContactDetails cd2 : contactdetails2) {
        	        if (cd.getId() == cd2.getId()) {
        	       	
        	            found = true;
        	            break;
        	        }
        	    }
        	    if (!found) {
        	    	contactDetailsRepository.deleteCustomarAddressbyId(cd.getId());
        	       
        	    }
        	}
          for (BankDetails bk : bankdetails) {
      	    boolean found = false;
      	    for (BankDetails bk2 : bankdetails2) {
      	        if (bk.getId() == bk2.getId()) {
      	            found = true;
      	            break;
      	        }
      	    }
      	    if (!found) {
      	    	bankDetailsRepository.deleteCustomarbankbyId(bk.getId());
      	       
      	    }
      	}
          customerRepository.saveOrUpdate(customerEntity);
          for(ContactDetails c : customerEntity.getContactdetails()) { 
  	    	c.setCustomerEntity(customerEntity);
  			contactDetailsRepository.saveOrUpdate(c);
  		}
  	    
  		for(BankDetails bank : customerEntity.getBankdetails()) {
  			bank.setCustomerEntity(customerEntity);
  			bankDetailsRepository.saveOrUpdate(bank);
  		}
		}else {

		customerRepository.saveOrUpdate(customerEntity);
		
	    for(ContactDetails c : customerEntity.getContactdetails()) { 
	    	c.setCustomerEntity(customerEntity);
			contactDetailsRepository.saveOrUpdate(c);
		}
	    
		for(BankDetails bank : customerEntity.getBankdetails()) {
			bank.setCustomerEntity(customerEntity);
			bankDetailsRepository.saveOrUpdate(bank);
		}
		
	}
	}
	
	@Transactional
	public void deleteCustomerbyId(Integer id) {
		customerRepository.deleteCustomerbyId(id);
	}
     
	public List<CustomerEntity> searchData(CustomerEntity customerEntity) {
		  
		  return customerRepository.searchData(customerEntity);
		  }
	
	
	public CustomerEntity findById(Integer id) {
		return customerRepository.findById(id);
	}
	
	public CustomerEntity findByname(String name) {
		return customerRepository.findByName(name);
	}
	
	
	public void deleteCustomarAddressbyId(Integer id) {
		contactDetailsRepository.deleteCustomarAddressbyId(id);
	}
	
	
	public void deleteCustomarbankbyId(Integer id) {
		bankDetailsRepository.deleteCustomarbankbyId(id);
	}
	
	public List<String> getAllNames(){
		return customerRepository.getAllNames();
	}

	public void savetransaction(Transaction transaction) {
		customerRepository.savetransaction(transaction);
	}
	
	public BankDetails getBankById(Integer id) {
		return bankDetailsRepository.getbankbyId(id);
	}
	
	public void saveCreatebank(CreateBank createBank) {
		customerRepository.savebanks(createBank);
	}
	
	public List<CreateBank> getAllCreatedBanks() {
		return customerRepository.getAllCreatedBanks();
	}
}
