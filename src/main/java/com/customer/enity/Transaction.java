package com.customer.enity;

import java.util.List;

public class Transaction {
  
	private String fromname;
	
	private List<String> toname;
	
	private Integer fromid;
	
	private String fromamount;
	
	private String toamount;
	
	private Integer toid;
	
	private List<String> bankname;
	
	private Integer frombankId;
	
	private Integer tobankId;

	public Transaction() {
		super();
		
	}

	public Transaction(String fromname, List<String> toname, Integer fromid, String fromamount, String toamount,
			Integer toid, List<String> bankname, Integer frombankId, Integer tobankId) {
		super();
		this.fromname = fromname;
		this.toname = toname;
		this.fromid = fromid;
		this.fromamount = fromamount;
		this.toamount = toamount;
		this.toid = toid;
		this.bankname = bankname;
		this.frombankId = frombankId;
		this.tobankId = tobankId;
	}

	public String getFromname() {
		return fromname;
	}

	public void setFromname(String fromname) {
		this.fromname = fromname;
	}

	public List<String> getToname() {
		return toname;
	}

	public void setToname(List<String> toname) {
		this.toname = toname;
	}

	public Integer getFromid() {
		return fromid;
	}

	public void setFromid(Integer fromid) {
		this.fromid = fromid;
	}

	public String getFromamount() {
		return fromamount;
	}

	public void setFromamount(String fromamount) {
		this.fromamount = fromamount;
	}

	public String getToamount() {
		return toamount;
	}

	public void setToamount(String toamount) {
		this.toamount = toamount;
	}

	public Integer getToid() {
		return toid;
	}

	public void setToid(Integer toid) {
		this.toid = toid;
	}

	public List<String> getBankname() {
		return bankname;
	}

	public void setBankname(List<String> bankname) {
		this.bankname = bankname;
	}

	public Integer getFrombankId() {
		return frombankId;
	}

	public void setFrombankId(Integer frombankId) {
		this.frombankId = frombankId;
	}

	public Integer getTobankId() {
		return tobankId;
	}

	public void setTobankId(Integer tobankId) {
		this.tobankId = tobankId;
	}

	@Override
	public String toString() {
		return "Transaction [fromname=" + fromname + ", toname=" + toname + ", fromid=" + fromid + ", fromamount="
				+ fromamount + ", toamount=" + toamount + ", toid=" + toid + ", bankname=" + bankname + ", frombankId="
				+ frombankId + ", tobankId=" + tobankId + "]";
	}

		
}
