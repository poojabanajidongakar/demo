package com.prodata.spsv1.repository;

import java.util.List;

import com.prodata.spsv1.model.Cheque;
import com.prodata.spsv1.model.User;



public interface DiscrepancyRepository {
	
	public List<String> getAllDiscrepancyTags(); 
	//Cheque getDescData(String policyNo);
	public Cheque getDataDes(String policyNo);
	public List<Cheque> DescrepencyToDoList(User u);
	boolean insertDesWriteCases(Cheque cheque);
	boolean compareData(Cheque cheque, String maccNo, String vaccNo);
	void changeToDoListFlagDes(String policyNo);
	int countToDoList(User u);
}
