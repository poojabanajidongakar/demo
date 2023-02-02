package com.prodata.spsv1.model;

public class Repatmodel {

	String paymentdate, payeefirstname, payeelastname, address1, address2, address3, address4, address5, contractno,
			spaarccallrequestdate, payment, amt, electronic, callid, productname, uincode;

	public Repatmodel() {

	}

	public Repatmodel(String paymentdate, String payeefirstname, String payeelastname, String address1, String address2,
			String address3, String address4, String address5, String contractno, String spaarccallrequestdate,
			String payment, String amt, String electronic, String callid, String productname, String uincode) {
		super();
		this.paymentdate = paymentdate;
		this.payeefirstname = payeefirstname;
		this.payeelastname = payeelastname;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
		this.address4 = address4;
		this.address5 = address5;
		this.contractno = contractno;
		this.spaarccallrequestdate = spaarccallrequestdate;
		this.payment = payment;
		this.amt = amt;
		this.electronic = electronic;
		this.callid = callid;
		this.productname = productname;
		this.uincode = uincode;
	}

	public String getPaymentdate() {
		return paymentdate;
	}

	public void setPaymentdate(String paymentdate) {
		this.paymentdate = paymentdate;
	}

	public String getPayeefirstname() {
		return payeefirstname;
	}

	public void setPayeefirstname(String payeefirstname) {
		this.payeefirstname = payeefirstname;
	}

	public String getPayeelastname() {
		return payeelastname;
	}

	public void setPayeelastname(String payeelastname) {
		this.payeelastname = payeelastname;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public String getAddress5() {
		return address5;
	}

	public void setAddress5(String address5) {
		this.address5 = address5;
	}

	public String getContractno() {
		return contractno;
	}

	public void setContractno(String contractno) {
		this.contractno = contractno;
	}

	public String getSpaarccallrequestdate() {
		return spaarccallrequestdate;
	}

	public void setSpaarccallrequestdate(String spaarccallrequestdate) {
		this.spaarccallrequestdate = spaarccallrequestdate;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getElectronic() {
		return electronic;
	}

	public void setElectronic(String electronic) {
		this.electronic = electronic;
	}

	public String getCallid() {
		return callid;
	}

	public void setCallid(String callid) {
		this.callid = callid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getUincode() {
		return uincode;
	}

	public void setUincode(String uincode) {
		this.uincode = uincode;
	}

}
