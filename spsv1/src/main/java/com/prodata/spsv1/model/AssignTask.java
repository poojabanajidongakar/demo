package com.prodata.spsv1.model;

public class AssignTask {
	int transfer;
	String employee, assignee;
	
	public int gettransfer() {
		return transfer;
	}
	public void settransfer(int transfer) {
		this.transfer = transfer;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	
	@Override
	public String toString() {
		return "AssignTask [transfer=" + transfer + ", employee=" + employee + ", assignee=" + assignee + "]";
	}
	
	
}
