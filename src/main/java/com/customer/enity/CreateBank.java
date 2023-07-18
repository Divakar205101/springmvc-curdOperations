package com.customer.enity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "createBank")
public class CreateBank implements Serializable{
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idofbank")
	private Integer id;
	
	@Column(name = "nameofbank")
	private String nameofbank;
	
	@Column(name = "nameofbranch")
	private String nameofbranch;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameofbank() {
		return nameofbank;
	}

	public void setNameofbank(String nameofbank) {
		this.nameofbank = nameofbank;
	}

	public String getNameofbranch() {
		return nameofbranch;
	}

	public void setNameofbranch(String nameofbranch) {
		this.nameofbranch = nameofbranch;
	}

	public CreateBank() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateBank(Integer id, String nameofbank, String nameofbranch) {
		super();
		this.id = id;
		this.nameofbank = nameofbank;
		this.nameofbranch = nameofbranch;
	}

	@Override
	public String toString() {
		return "CreateBank [id=" + id + ", nameofbank=" + nameofbank + ", nameofbranch=" + nameofbranch + "]";
	}
	
	
	
}
