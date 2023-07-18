package com.customer.enity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name ="customer")
public class CustomerEntity implements Serializable {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cust_id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name ="gender")
	private String gender;
	
	@Column(name = "DOB")	
	private String dateofbirth;
	
	@Column(name = "adharNo")
	private String adharNo;
	
	@Column(name = "panNo")
	private String panNo;
	
	@Column(name = "maritualstatus")
	private String maritualstatus;
	
	
	@Column(name = "total")
	private String total;
	
	
	@OneToMany(mappedBy ="customerEntity", cascade = CascadeType.ALL)
	private List<ContactDetails> contactdetails = new ArrayList<ContactDetails>();
	
	@OneToMany(mappedBy ="customerEntity", cascade =CascadeType.ALL)
	private List<BankDetails> bankdetails = new ArrayList<BankDetails>();

	public CustomerEntity() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(String dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getAdharNo() {
		return adharNo;
	}

	public void setAdharNo(String adharNo) {
		this.adharNo = adharNo;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getMaritualstatus() {
		return maritualstatus;
	}

	public void setMaritualstatus(String maritualstatus) {
		this.maritualstatus = maritualstatus;
	}

	public List<ContactDetails> getContactdetails() {
		return contactdetails;
	}
    

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public void setContactdetails(List<ContactDetails> contactdetails) {
		this.contactdetails = contactdetails;
	}

	public List<BankDetails> getBankdetails() {
		return bankdetails;
	}

	public void setBankdetails(List<BankDetails> bankdetails) {
		this.bankdetails = bankdetails;
	}

	public CustomerEntity(Integer id, String name, String gender, String dateofbirth, String adharNo, String panNo,
			String maritualstatus, String total, List<ContactDetails> contactdetails, List<BankDetails> bankdetails) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dateofbirth = dateofbirth;
		this.adharNo = adharNo;
		this.panNo = panNo;
		this.maritualstatus = maritualstatus;
		this.total = total;
		this.contactdetails = contactdetails;
		this.bankdetails = bankdetails;
	}

	/*
	 * @Override public String toString() { return "CustomerEntity [id=" + id +
	 * ", name=" + name + ", gender=" + gender + ", dateofbirth=" + dateofbirth +
	 * ", adharNo=" + adharNo + ", panNo=" + panNo + ", maritualstatus=" +
	 * maritualstatus + ", total=" + total + ", contactdetails=" + contactdetails +
	 * ", bankdetails=" + bankdetails + "]"; }
	 */

	

	

	
	

	
	

	
    	
}
