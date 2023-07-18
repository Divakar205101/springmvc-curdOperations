package com.customer.enity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name ="BankDetails")
public class BankDetails implements Serializable {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="bank_id")
	private Integer id;
	
	@Column(name = "bankname")
	private String bankname;
	
	@Column(name = "accNo")
	private String accNo;
	
	@Column(name = "branch")
	private String branch;
	
	@Column(name = "amount")
	private String amount;
	
	@ManyToOne
	private CustomerEntity customerEntity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}
    
	
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

	public BankDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public BankDetails(Integer id, String bankname, String accNo, String branch, String amount,
			CustomerEntity customerEntity) {
		super();
		this.id = id;
		this.bankname = bankname;
		this.accNo = accNo;
		this.branch = branch;
		this.amount = amount;
		this.customerEntity = customerEntity;
	}

	@Override
	public String toString() {
		return "BankDetails [id=" + id + ", bankname=" + bankname + ", accNo=" + accNo + ", branch=" + branch
				+ ", amount=" + amount + ", customerEntity=" + customerEntity + "]";
	}

	

	

	

	
	
}
