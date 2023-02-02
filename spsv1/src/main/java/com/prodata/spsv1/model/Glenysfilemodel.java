package com.prodata.spsv1.model;

public class Glenysfilemodel {

	String call_id;
	String hold_date;
	String till_date;
	int ageing;
	String contract_no;
	String source;
	String calltype;
	String subtype;
	String recvdby;
	String calldesc;
	String ownership;
	String department;
	String approval;

	public Glenysfilemodel() {

	}

	public Glenysfilemodel(String call_id, String hold_date, String till_date, int ageing, String contract_no,
			String source, String calltype, String subtype, String recvdby, String calldesc, String ownership,
			String department, String approval) {
		super();
		this.call_id = call_id;
		this.hold_date = hold_date;
		this.till_date = till_date;
		this.ageing = ageing;
		this.contract_no = contract_no;
		this.source = source;
		this.calltype = calltype;
		this.subtype = subtype;
		this.recvdby = recvdby;
		this.calldesc = calldesc;
		this.ownership = ownership;
		this.department = department;
		this.approval = approval;
	}

	public String getCall_id() {
		return call_id;
	}

	public void setCall_id(String call_id) {
		this.call_id = call_id;
	}

	public String getHold_date() {
		return hold_date;
	}

	public void setHold_date(String hold_date) {
		this.hold_date = hold_date;
	}

	public String getTill_date() {
		return till_date;
	}

	public void setTill_date(String till_date) {
		this.till_date = till_date;
	}

	public int getAgeing() {
		return ageing;
	}

	public void setAgeing(int ageing) {
		this.ageing = ageing;
	}

	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCalltype() {
		return calltype;
	}

	public void setCalltype(String calltype) {
		this.calltype = calltype;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public String getRecvdby() {
		return recvdby;
	}

	public void setRecvdby(String recvdby) {
		this.recvdby = recvdby;
	}

	public String getCalldesc() {
		return calldesc;
	}

	public void setCalldesc(String calldesc) {
		this.calldesc = calldesc;
	}

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

}
