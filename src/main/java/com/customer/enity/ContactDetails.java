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

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "ContactDetails")
public class ContactDetails implements Serializable{
   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cont_id")
	private Integer id; 
	
	@ManyToOne
	private CustomerEntity customerEntity;
	
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "pin")
	private String pin;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CustomerEntity getCustomerEntity() {
		return customerEntity;
	}

	public void setCustomerEntity(CustomerEntity customerEntity) {
		this.customerEntity = customerEntity;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public ContactDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public ContactDetails(Integer id, CustomerEntity customerEntity, String phone, String email, String address,
			String pin) {
		super();
		this.id = id;
		this.customerEntity = customerEntity;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.pin = pin;
	}

	@Override
	public String toString() {
		return "ContactDetails [id=" + id + ", customerEntity=" + customerEntity + ", phone=" + phone + ", email="
				+ email + ", address=" + address + ", pin=" + pin + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactDetails other = (ContactDetails) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
	
}