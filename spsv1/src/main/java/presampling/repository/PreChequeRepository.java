package presampling.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.prodata.spsv1.model.Cheque;
import com.prodata.spsv1.model.User;


@Repository
public interface PreChequeRepository 
{
	//List<Cheque> ToDoList(User u);
	List<Cheque> ToDoList(User u, String type);
	List<User> getUserData();
	int countToDoList(User u, String type);
	Cheque getData(String policyNo);
	Cheque onersgetData(String policyNo,String onersremark);
	//boolean insertIntoDb(Cheque cheque);
	boolean insertIntoDb(Cheque cheque,User u);
	boolean compareData(String policyNo, String vaccNo, String ifsccode,String maccNo, String micrcode, String paymentmode,User u);
	void changeToDoListFlag(String policyno);
	Cheque getDcDetails(String dc);
	Cheque getIfscDetails(String ifscCode,String prnno);
	Cheque getMicrDetails(String micr,String prnno);
	boolean insertExcelData(String file); 
	boolean insertDiscrCases(Cheque cheque,User u); 
	List<Cheque> showSubType(String paymode,User u);
	//List<Cheque> showOneRsType(String OneRsType,User u) ;
	List<Cheque> showOneRsType(String OneRsType,User u, String type) ;
	boolean insertExcelOtherData();
	String getpmode(String policyno); 
	int countToDoListpaymode(User u,String paymode);
	//int countToDoListOneRs(User u,String OneRsType);
	int countToDoListOneRs(User u,String OneRsType,String type);
	//Cheque updatePolicy(String policyNo,String prnno);
	boolean insertEditPolicy(Cheque cheque,User u,String prnno);
	/*boolean deleteMasterTable();
	boolean deleteMandateTable();
	boolean deleteInputDetailsTable();
	boolean deleteBaseTable();*/
	String getOneRsType(String policyno);
	void changeCheckerToDoListFlag(String policyNo);
	boolean preSamplingRemark();
	
	
}
