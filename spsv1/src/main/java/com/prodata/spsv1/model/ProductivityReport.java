package com.prodata.spsv1.model;

public class ProductivityReport {
	
	
	String allocate,pending,username,complete,
	 chraloctsk,chrpentsk,chrcmplt,mkraloctsk,mkrpendtsk,mkrcomplt;
	
	public String getChraloctsk() {
		return chraloctsk;
	}



	public void setChraloctsk(String chraloctsk) {
		this.chraloctsk = chraloctsk;
	}



	public String getChrpentsk() {
		return chrpentsk;
	}



	public void setChrpentsk(String chrpentsk) {
		this.chrpentsk = chrpentsk;
	}



	public String getChrcmplt() {
		return chrcmplt;
	}



	public void setChrcmplt(String chrcmplt) {
		this.chrcmplt = chrcmplt;
	}



	public String getMkraloctsk() {
		return mkraloctsk;
	}



	public void setMkraloctsk(String mkraloctsk) {
		this.mkraloctsk = mkraloctsk;
	}



	public String getMkrpendtsk() {
		return mkrpendtsk;
	}



	public void setMkrpendtsk(String mkrpendtsk) {
		this.mkrpendtsk = mkrpendtsk;
	}



	public String getMkrcomplt() {
		return mkrcomplt;
	}



	public void setMkrcomplt(String mkrcomplt) {
		this.mkrcomplt = mkrcomplt;
	}



	@Override
	public String toString() {
		return "ProductivityReport [allocate=" + allocate + ", pending=" + pending + ", username=" + username
				+ ", complete=" + complete + ", chraloctsk=" + chraloctsk + ", chrpentsk=" + chrpentsk + ", chrcmplt="
				+ chrcmplt + ", mkraloctsk=" + mkraloctsk + ", mkrpendtsk=" + mkrpendtsk + ", mkrcomplt=" + mkrcomplt
				+ "]";
	}



	public String getComplete() {
		return complete;
	}



	public void setComplete(String complete) {
		this.complete = complete;
	}



	public String getAllocate() {
		return allocate;
	}

	public void setAllocate(String allocate) {
		this.allocate = allocate;
	}

	public String getPending() {
		return pending;
	}

	public void setPending(String pending) {
		this.pending = pending;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}



}
