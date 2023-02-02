package com.prodata.spsv1.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.prodata.spsv1.model.Cheque;
import com.prodata.spsv1.model.User;


@Repository
public interface ChequeRepository 
{
	List<Cheque> ToDoList(User u);
	List<User> getUserData();
	int countToDoList(User u);
	Cheque getData(String policyNo, String prnno,String paymentmode);
	Cheque onersgetData(String policyNo, String prnno,String paymentmode,String onersremark);
	boolean insertIntoDb(Cheque cheque, String maccNo,String vaccNo,User u,String paymentmode,String ifsccode,String bankname);
	boolean compareData(String policyNo, String vaccNo, String ifsccode,String maccNo, String micrcode, String paymentmode,User u);
	void changeToDoListFlag(String prnno);
	Cheque getDcDetails(String dc);
	Cheque getIfscDetails(String ifscCode,String prnno);
	Cheque getMicrDetails(String micr,String prnno);
	boolean insertExcelData(String file); 
	boolean insertDiscrCases(Cheque cheque,User u); 
	List<Cheque> showSubType(String paymode,User u);
	List<Cheque> showOneRsType(String OneRsType,User u,String pmode) ;
	boolean insertExcelOtherData();
	String getpmode(String prnno); 
	int countToDoListpaymode(User u,String paymode);
	int countToDoListOneRs(User u,String OneRsType,String pmode);
	Cheque updatePolicy(String policyNo,String prnno);
	boolean insertEditPolicy(Cheque cheque,User u,String prnno);
	/*boolean deleteMasterTable();
	boolean deleteMandateTable();
	boolean deleteInputDetailsTable();
	boolean deleteBaseTable();*/
	String getOneRsType(String prno1);
	Cheque getSruCase(String prnNo);
	
}
