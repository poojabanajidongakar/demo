package com.prodata.spsv1.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.prodata.spsv1.model.Cheque;
import com.prodata.spsv1.model.User;
import com.prodata.spsv1.repository.ChequeRepository;
import com.prodata.spsv1.repository.SpoolRepository;

import ch.qos.logback.core.subst.Token.Type;

@Repository
public class ChequeRepositoryImpl implements ChequeRepository {

	@Autowired
	private SpoolRepository spoolRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Cheque> ToDoList(User u) {
		System.out.println("username in toDolist "+u.getUsername());
		/*HttpSession s=null;
		s.getAttribute("user");*/
		List<Cheque> li = jdbcTemplate.query("select REQNNO,CHDRNUM,[Customer Name],REQNTYPE,[FinalRemarkOners],[DESCR] from tbl_samplingInput where flag='Y' AND assign_to=?", new ToDoListRowMapper(),u.getUsername());
		return li;
	}

	@Override
	public String getpmode(String prnno){

		String	s= jdbcTemplate.queryForObject("select REQNTYPE from tbl_samplingInput where REQNNO=?", String.class,prnno);
		return s;
	}
	
	@Override
	public String getOneRsType(String prnno){

		String	s1= jdbcTemplate.queryForObject("select [FinalRemarkOners] from tbl_samplingInput where REQNNO=?", String.class,prnno);
		return s1;
	}

	private static class ToDoListRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cheque cheque = new Cheque();
			cheque.setPrnNo(rs.getString(1));
			cheque.setPolicyno(rs.getString(2));
			
			//cheque.setClientId(rs.getString(3));
			cheque.setName(rs.getString(3));
			cheque.setPaymentMode(rs.getString(4));
			cheque.setFinalRemarkOners(rs.getString(5));
			cheque.setDescr(rs.getString(6));
			return cheque;
		}

	}

	@Override
	public List<User> getUserData() {
		List<User> li = jdbcTemplate.query("select username from tbl_user", new UserDataRowMapper());
		return li;
	}

	private static class UserDataRowMapper implements RowMapper<User>
	{
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUsername(rs.getString("username"));
			//user.setPassword(rs.getString("password"));
			return user;
		}	
	}

	@Override
	public int countToDoList(User u) {
		int countrecord = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=?", Integer.class,u.getUsername()); 
		return countrecord;
	}


	@Override
	public int countToDoListpaymode(User u,String paymode) {
		int countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=?", Integer.class,u.getUsername(),paymode); 
		return countmode;
	}
	
	@Override
	public int countToDoListOneRs(User u,String OneRsType,String pmode) 
	{
		int countOneRs;
		System.out.println("OneRsType"+OneRsType+"and   pmode"+pmode);
		if(pmode!=null && OneRsType!=null){
			countOneRs = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [FinalRemarkOners]=? and [REQNTYPE]=?", Integer.class,u.getUsername(),OneRsType,pmode);
		}
		else if(OneRsType!=null){
			countOneRs = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [FinalRemarkOners]=?", Integer.class,u.getUsername(),OneRsType);
						}
		else if(pmode!=null){
			 countOneRs = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=?", Integer.class,u.getUsername(),pmode);
		}
		else{
			countOneRs = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=?", Integer.class,u.getUsername(),OneRsType,pmode);
		}
		//int countOneRs = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=?", Integer.class,u.getUsername(),OneRsType); 
		
		return countOneRs;
	}
	@Override
	public Cheque getData(String policyNo, String prnno,String paymentmode) 
	{
		Cheque cheque = jdbcTemplate.queryForObject("select CLNTNUM,REQNNO,PAYAMT,REQNTYPE,CHDRNUM,TTMPRCNO,PYMTSURC,BANKACCKEY,BANKDESC,[Cross Ref Policy No],LANDMARK,[Customer Name] as custname,[One_Rs_Name],[FinalRemarkOners],[IFSC],[DESCR]  from tbl_samplingInput where [REQNNO]=? and [REQNTYPE]=?", new ChequeRowMapper(),prnno,paymentmode);
		return cheque;
	}

	private static class ChequeRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			Cheque cheque = new Cheque();
			cheque.setClientId(rs.getString(1));
			cheque.setPrnNo(rs.getString(2));
			cheque.setAmount(rs.getFloat(3));
			cheque.setPaymentMode(rs.getString(4));
			cheque.setPolicyno(rs.getString(5));
			cheque.setApplicationNo(rs.getString(6));
			cheque.setPaymentType(rs.getString(7));
			cheque.setmAccountNumber(rs.getString(8));
			cheque.setBankName(rs.getString(9));
			cheque.setCrossRefPolicy(rs.getString(10));
			cheque.setBankCode(rs.getString(11));
			cheque.setName(rs.getString(12));
			cheque.setOneRsName(rs.getString(13));
			cheque.setFinalRemarkOners(rs.getString(14));
			cheque.setIfscCode(rs.getString(15));
			System.out.println("ifsc code1............... "+cheque.getIfscCode());
			cheque.setDescr(rs.getString(16));
			System.out.println("DESCR1............... "+cheque.getDescr());
			return cheque;	
		}	
	}
	@Override
	public Cheque onersgetData(String policyNo, String prnno,String paymentmode,String onersremark){
		Cheque cheque = jdbcTemplate.queryForObject("select CLNTNUM,REQNNO,PAYAMT,REQNTYPE,CHDRNUM,TTMPRCNO,PYMTSURC,BANKACCKEY,BANKDESC,[Cross Ref Policy No],LANDMARK,[Customer Name] as custname,[One_Rs_Name],[FinalRemarkOners],[IFSC],[DESCR]  from tbl_samplingInput where [REQNNO]=? and [REQNTYPE]=? and [FinalRemarkOners]=?", new OnersChequeRowMapper(),prnno,paymentmode,onersremark);
		System.out.println("select method");
		return cheque;
	}
	
	private static class OnersChequeRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cheque cheque = new Cheque();
			System.out.println("OnersChequeRowMapper..........");
			cheque.setClientId(rs.getString(1));
			cheque.setPrnNo(rs.getString(2));
			cheque.setAmount(rs.getFloat(3));
			cheque.setPaymentMode(rs.getString(4));
			cheque.setPolicyno(rs.getString(5));
			cheque.setApplicationNo(rs.getString(6));
			cheque.setPaymentType(rs.getString(7));
			cheque.setmAccountNumber(rs.getString(8));
			cheque.setBankName(rs.getString(9));
			cheque.setCrossRefPolicy(rs.getString(10));
			cheque.setBankCode(rs.getString(11));
			cheque.setName(rs.getString(12));
			cheque.setOneRsName(rs.getString(13));
			cheque.setFinalRemarkOners(rs.getString(14));
			cheque.setIfscCode(rs.getString(15));
			System.out.println("ifsc code............... "+cheque.getIfscCode());
			cheque.setDescr(rs.getString(16));
			System.out.println("DESCR............... "+cheque.getDescr());
			return cheque;	
		}	
	}
	@Override
	public Cheque updatePolicy(String policyNo,String prnno) {
		System.out.println("getData "+  policyNo);
		System.out.println("fffkfkfkfk");
		Cheque cheque = jdbcTemplate.queryForObject("select [Policy No.],[Client ID],[PRN No.],[Payment Amt],[Payment Mode],[Payment Reason],[Cross Ref Policy 1],[Application No.],[Bank Name],[Bank Code],[Customer Name],[Checker A/c No.],[IFSC Code],[MICR Code],[NM Code],[Sampler Name],[DATA TIME],[Remarks] from tbl_samplingOutput where [PRN No.]=?", new editChequeRowMapper(),prnno);
		//System.out.println("plist = "+cheque);
		return cheque;
	}
	private static class editChequeRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cheque cheque = new Cheque();

			cheque.setPolicyno(rs.getString(1));
			cheque.setClientId(rs.getString(2));
			cheque.setPrnNo(rs.getString(3));
			cheque.setAmount(rs.getFloat(4));
			cheque.setPaymentMode(rs.getString(5));
			cheque.setPaymentType(rs.getString(6));
			cheque.setCrossRefPolicy(rs.getString(7));
			cheque.setApplicationNo(rs.getString(8));
			cheque.setBankName(rs.getString(9));
			cheque.setBankCode(rs.getString(10));
			cheque.setName(rs.getString(11));
			cheque.setmAccountNumber(rs.getString(12));
			cheque.setMifscCode(rs.getString(13));
			cheque.setMmicrCode(rs.getString(14));
			cheque.setMnmCode(rs.getString(15));
			cheque.setRemarksBy(rs.getString(16));
			cheque.setDate(rs.getString(17));
			cheque.setRemarks(rs.getString(18));
			return cheque;	
		}	
	}

	@Override
	public boolean insertIntoDb(Cheque cheque, String maccNo, String vaccNo,User u,String paymentmode,String ifsccode,String bankname) {

		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		String d=date.toString();
		String disczero;
		String cross1;
		String cross2;
		String prnno=cheque.getPrnNo();
		Cheque spool = spoolRepository.findById(prnno,u);
		String vendor=spool.getVendorName();
		String desc=spool.getDescr();
		String chname;

		//System.out.println("insertIntoDb:cheque "+paymentmode);

		//System.out.println("maccountno..............."+maccNo);
		//System.out.println("vaccountno..............."+vaccNo);
		boolean result = maccNo.trim().equals(vaccNo.trim());
		
		if(result)
		{
			
						
			if(cheque.getCrossRefPolicy()!=null)
			{
				cross1=cheque.getCrossRefPolicy();
			}
			else
			{
				cross1="0";

			}
			if(cheque.getCrossrefPolicy2()!=null)
			 {
				 cross2=cheque.getCrossrefPolicy2();
			 }
			 else
			 {
				 cross2="0";

			 }
			
			 if(cheque.getDiscrepancyTag()!=null)
			 {
				 System.out.println("If madhla discreapncy "+cheque.getDiscrepancyTag());
				 disczero=cheque.getDiscrepancyTag().replaceAll("null,", "");
			 }
			 else
			 {
				 disczero="0";

			 }
			 if((paymentmode=="NEFT" && ifsccode=="ICIC0000" && ifsccode.length()==11 )||(paymentmode=="Direct Credit" && bankname!="ICICI BANK LTD")){
				 disczero="Wrong Mode Selected";
				 //cheque.setDiscrepancyTag("Wrong Mode Selected");
				 //disczero=cheque.getDiscrepancyTag();
				 cheque.setRemarks("Discrepancy");
			 }
			 
			 
			 if(cheque.getCheckName() != null)
			 {
				 
				 chname=cheque.getCheckName();
			 }
			 else
			 {
				 chname="Not Checked";
			 }
			 
			 System.out.println("in insert err "+cheque.getvPolicyNo());
			 jdbcTemplate.update("insert into tbl_samplingOutput ([Policy No.],[Client ID],[PRN No.],[Payment Amt],[Payment Mode],[Payment Reason],[MOS],[Cross Ref Policy 1],[Cross Ref Policy 2],[Application No.],[Bank Name],[Bank Code],[policy no.1],[Customer Name],[Checker A/c No.],[IFSC Code],[MICR Code],[NM Code],[Pay Mode],[Error / Ok],assign_to,[DATA TIME],[Remarks],[Sampler Name],[Reference],[DESCR],[Vendor Name],[CheckedName],[OneRsName],[reason],[FinalRemarkOners]) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					 cheque.getvPolicyNo(),
					 cheque.getClientId(),
					 cheque.getPrnNo(),
					 cheque.getAmount(),
					 cheque.getPaymentMode(),
					 cheque.getPaymentType(),
					 cheque.getMos(),
					 cross1,
					 cross2,
					 cheque.getApplicationNo(),
					 cheque.getBankName(),
					 cheque.getBankCode(),
					 cheque.getvPolicyNo(),
					 cheque.getName(),
					 cheque.getvAccountNumber(),
					 cheque.getMifscCode(),
					 cheque.getMmicrCode(),
					 cheque.getMnmCode(),
					 cheque.getPaymentMode(),
					 disczero,
					 u.getUsername(),
					 d,
					 cheque.getRemarks(),
					 cheque.getRemarksBy(),
					 cheque.getReference(),
					 desc,
					 vendor,
					 chname,
					 cheque.getOneRsName(),
					 cheque.getReason(),
					 cheque.getFinalRemarkOners());
			 


			 System.out.println("Discrepancy tag insert into discrepancy"+cheque.getDiscrepancyTag());
		}
		return true;
	}

	@Override
	public boolean insertEditPolicy(Cheque cheque, User u,String prnno) {
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		String d=date.toString();
		String disczero;
		Cheque spool = spoolRepository.findById(prnno,u);
		String vendor=spool.getVendorName();
		String desc=spool.getDescr();

		if(cheque.getDiscrepancyTag()!=null){
			disczero=cheque.getDiscrepancyTag();
		}
		else
		{
			disczero="0";
		}
		jdbcTemplate.update("update tbl_samplingOutput set [Policy No.]=?,[Client ID]=?,[PRN No.]=?,[Payment Amt]=?,[Payment Mode]=?,[Payment Reason]=?,[MOS]=?,[Cross Ref Policy 1]=?,[Cross Ref Policy 2]=?,[Application No.]=?,[Bank Name]=?,[Bank Code]=?,[policy no.1]=?,[Customer Name]=?,[Checker A/c No.]=?,[IFSC Code]=?,[MICR Code]=?,[NM Code]=?,[Pay Mode]=?,[Error / Ok]=?,assign_to=?,[DATA TIME]=?,[Remarks]=?,[Sampler Name]=?,[Reference]=? where [PRN No.]=?",
				cheque.getvPolicyNo(),
				cheque.getClientId(),
				cheque.getPrnNo(),
				cheque.getAmount(),
				cheque.getPaymentMode(),
				cheque.getPaymentType(),
				cheque.getMos(),
				cheque.getCrossRefPolicy(),
				cheque.getCrossrefPolicy2(),
				cheque.getApplicationNo(),
				cheque.getBankName(),
				cheque.getBankCode(),
				cheque.getvPolicyNo(),
				cheque.getName(),
				cheque.getvAccountNumber(),
				cheque.getMifscCode(),
				cheque.getMmicrCode(),
				cheque.getMnmCode(),
				cheque.getPaymentMode(),
				disczero,
				u.getUsername(),
				d,
				cheque.getRemarks(),
				cheque.getRemarksBy(),
				cheque.getReference(),
				prnno,
				desc,
				 vendor);

		System.out.println("Discrepancy tag insert into discrepancy"+cheque.getDiscrepancyTag());

		return true;
	}



	@Override
	public void changeToDoListFlag(String prnno) {
		jdbcTemplate.update("UPDATE tbl_samplingInput SET flag = 'N' WHERE [REQNNO] = ?", prnno);
	}

	@Override
	public Cheque getDcDetails(String dc) {
		System.out.println("getDcDetails"+dc);
		Cheque cheque = jdbcTemplate.queryForObject("select top(1)* from tbl_samplingInput where [REQNTYPE] = ?", new dcRowMapper(), dc);
		return cheque;
	}


	private static class dcRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cheque cheque = new Cheque();
			cheque.setBankName(rs.getString("BANKDESC"));
			cheque.setMnmCode(rs.getString("BANKKEY"));
			cheque.setPaymentMode(rs.getString("REQNTYPE"));
			System.out.println("DC madhla cheque"+cheque);
			return cheque;
		}
	}

	@Override
	public Cheque getIfscDetails(String ifscCode,String prnno) 
	{

		Cheque cheque = jdbcTemplate.queryForObject("select * from tbl_samplingInput where [IFSC] = ? AND [REQNNO]=?", new ifscRowMapper(), ifscCode,prnno);
		return cheque;
	}


	private static class ifscRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cheque cheque = new Cheque();
			if(rs.getString("BANKKEY").contains("NM") || rs.getString("BANKKEY").contains("000229000")){
				cheque.setMnmCode(rs.getString("BANKKEY"));
			}
			else{
				cheque.setMmicrCode(rs.getString("BANKKEY"));
			}
			//cheque.setMmicrCode(rs.getString("BANKKEY"));
			cheque.setBankName(rs.getString("BANKDESC"));
			//cheque.setMnmCode(rs.getString("BANKKEY"));
			cheque.setMifscCode(rs.getString("IFSC"));

			return cheque;
		}
	}

	@Override
	public Cheque getMicrDetails(String micr,String prnno) {
		Cheque cheque = jdbcTemplate.queryForObject("select * from tbl_samplingInput where [BANKKEY] = ? AND [REQNNO]=?", new micrRowMapper(), micr,prnno);
		return cheque;
	}


	private static class micrRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cheque cheque = new Cheque();
			cheque.setBankName(rs.getString("BANKDESC"));
			//cheque.setMnmCode(rs.getString("bank_branch"));
			//cheque.setMmicrCode(rs.getString("micr_code"));
			return cheque;
		}	
	}

	@Override
	public boolean insertExcelData(String file) 
	{

		try {
			CallableStatement cs = jdbcTemplate.getDataSource().getConnection().prepareCall("{ call dbo.SPS_Excel_upload(?) }");
			//System.out.println("new file name: "+file.getOriginalFilename());
			cs.setString(1,file);
			cs.execute();
			cs.close();
		} catch (SQLException e) {

			e.printStackTrace();
			return false;

		}
		return true;
	}

	@Override
	public boolean insertExcelOtherData() 
	{
		System.out.println("inside insertExcelOtherData");
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		String d=date.toString();
		try {
			//jdbcTemplate.update("INSERT INTO tbl_samplingInput_New(assign_to,date) VALUES(?,?)","pritamd",d);
			jdbcTemplate.update("update tbl_samplingInput set assign_to=?,date=? where date is NULL","bhartij",d);
		} 
		catch (Exception e) {		
			e.printStackTrace();
			return false;	
		}
		return true;			
	}
	
	


	@Override
	public boolean insertDiscrCases(Cheque cheque, User u) {
		System.out.println(cheque.getvPolicyNo()+"before");
		cheque.setAccountNo(cheque.getmAccountNumber().trim());
		System.out.println(cheque.getmAccountNumber()+"  hererererere");

		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		String d=date.toString();


		if(cheque.getmAccountNumber().trim().equals(cheque.getvAccountNumber().trim()))
		{
			if(cheque.getDiscrepancyTag()!=null)
			{
				cheque.setName(u.getUsername());


				cheque.setDiscrepancyTag(cheque.getDiscrepancyTag().replace('[', ' ').replace(']', ' ').trim());
				cheque.setDiscrepancyTag(cheque.getDiscrepancyTag().replace(",", " / ").trim());
				jdbcTemplate.update("insert into tbl_discrepancyCases ([Policy No],[Name],[GMID],[client Id],[DiscrepancyTag],[username],[Remarks By],[Remarks],[Reference],[cdate]) values(?,?,?,?,?,?,?,?,?,?)",
						cheque.getvPolicyNo(),
						cheque.getCustName(),
						cheque.getGmId(),
						cheque.getClientId(),
						cheque.getDiscrepancyTag(),
						cheque.getName(),
						cheque.getRemarksBy(),
						cheque.getRemarks(),
						cheque.getReference(),
						d);

				return true;
			}
			else
				cheque.setDiscrepancyTag("");	
		}

		return false;

	}

	@Override
	public boolean compareData(String prnno, String vaccNo,String ifsccode,String maccNo, String micrcode, String paymentmode,User u) {

		Cheque spool = spoolRepository.findById(prnno,u);
		//System.out.println("sppol IFSC code"+spool.getMifscCode());

		if(paymentmode.equals("NEFT") || paymentmode.equals("RTGS") || paymentmode.equals("G") ) {
			//System.out.println("payment mode: neft");

			//System.out.println("vaccNo:comparedata"+vaccNo+"maccno:comparedata"+maccNo+"paymentmode:comparedata"+paymentmode+"IFSC here"+ifsccode);
			//boolean result = vaccNo.trim().equals(maccNo) && ifsccode.equals(spool.getIfscCode());
			//boolean result = vaccNo.trim().equals(maccNo);
			if(vaccNo.trim().equals(maccNo))
			{
				jdbcTemplate.update("UPDATE tbl_samplingOutput SET [T/F Account No.] = 'True' WHERE [PRN No.] = ?", prnno);
			}
			else 
			{
				jdbcTemplate.update("UPDATE tbl_samplingOutput SET [T/F Account No.] = 'False' WHERE [PRN No.] = ?", prnno);
			}

			if(ifsccode.equals(spool.getMifscCode()))
			{
				jdbcTemplate.update("UPDATE tbl_samplingOutput SET [T/F IFSC] = 'True', [T/F MICR] = 'True' WHERE [PRN No.] = ?", prnno);
			}
			else
			{
				jdbcTemplate.update("UPDATE tbl_samplingOutput SET [T/F IFSC] = 'False', [T/F MICR] = 'False' WHERE [PRN No.] = ?", prnno);
			}

		}

		/*	////////////////////////////// DC comapre////////////////////////////////////////////
		 */		if(paymentmode.equals("Direct Credit") || paymentmode.equals("F")) {
			 //System.out.println("payment mode: neft");

			 //System.out.println("vaccNo:comparedata"+vaccNo+"maccno:comparedata"+maccNo+"paymentmode:comparedata"+paymentmode+"IFSC here"+ifsccode);
			 //boolean result = vaccNo.trim().equals(maccNo) && ifsccode.equals(spool.getIfscCode());
			 //boolean result = vaccNo.trim().equals(maccNo);
			 if(vaccNo.trim().equals(maccNo))
			 {
				 jdbcTemplate.update("UPDATE tbl_samplingOutput SET [T/F Account No.] = 'True',[T/F MICR] = 'True',[T/F IFSC] = 'True' WHERE [PRN No.] = ?", prnno);
			 }
			 else 
			 {
				 jdbcTemplate.update("UPDATE tbl_samplingOutput SET [T/F Account No.] = 'False',[T/F MICR] = 'False',[T/F IFSC] = 'False' WHERE [PRN No.] = ?", prnno);
			 }

			 /*if(ifsccode.equals(spool.getMifscCode()))
			{
				jdbcTemplate.update("UPDATE tbl_samplingOutput SET [T/F IFSC] = 'True', [T/F MICR] = 'True' WHERE [PRN No.] = ?", prnno);
			}
			else
			{
				jdbcTemplate.update("UPDATE tbl_samplingOutput SET [T/F IFSC] = 'False', [T/F MICR] = 'False' WHERE [PRN No.] = ?", prnno);
			}*/

		 }

		 //////////////////////////////////////////////////////////////////////////////////

		 if(paymentmode.equals("ECS")) {
			 //System.out.println("payment mode: neft");

			 //System.out.println("vaccNo:comparedata"+vaccNo+"maccno:comparedata"+maccNo+"paymentmode:comparedata"+paymentmode+"IFSC here"+ifsccode);
			 //boolean result = vaccNo.trim().equals(maccNo) && ifsccode.equals(spool.getIfscCode());
			 //boolean result = vaccNo.trim().equals(maccNo);
			 if(vaccNo.trim().equals(maccNo))
			 {
				 jdbcTemplate.update("UPDATE tbl_samplingOutput SET [T/F Account No.] = 'True' WHERE [PRN No.] = ?", prnno);
			 }
			 else 
			 {
				 jdbcTemplate.update("UPDATE tbl_samplingOutput SET [T/F Account No.] = 'False' WHERE [PRN No.] = ?", prnno);
			 }

			 if(micrcode.equals(spool.getMmicrCode()))
			 {
				 jdbcTemplate.update("UPDATE tbl_samplingOutput SET [T/F MICR] = 'True',[T/F IFSC] = 'True' WHERE [PRN No.] = ?", prnno);
			 }
			 else
			 {
				 jdbcTemplate.update("UPDATE tbl_samplingOutput SET [T/F MICR] = 'False',[T/F IFSC] = 'False' WHERE [PRN No.] = ?", prnno);
			 }

		 }
		 return false;


	}

	@Override
	public List<Cheque> showSubType(String paymode,User u) {

		List<Cheque> li = jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,[DESCR] from tbl_samplingInput where flag='Y' AND assign_to=? AND [REQNTYPE]=?", new subTypeRowMapper(),u.getUsername(),paymode);
		System.out.println(li);
		return li;
	}

	private static class subTypeRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {


			Cheque cheque = new Cheque();
			cheque.setPrnNo(rs.getString(1));
			cheque.setPolicyno(rs.getString(2));
			//cheque.setClientId(rs.getString(3));
			cheque.setName(rs.getString(3));
			cheque.setPaymentMode(rs.getString(4));
			cheque.setDescr(rs.getString(5));
			return cheque;
		}

	}

	@Override
	public List<Cheque> showOneRsType(String OneRsType, User u,String pmode) {
		
		if(pmode != null) {
			List<Cheque> li = jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,FinalRemarkOners,[REQNTYPE],[DESCR] from tbl_samplingInput where flag='Y' AND assign_to=? AND [FinalRemarkOners]=? and [REQNTYPE]=?", new OneRsTypeRowMapper(),u.getUsername(),OneRsType,pmode);
			return li;
		}
		List<Cheque> li = jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,FinalRemarkOners,[REQNTYPE],[DESCR] from tbl_samplingInput where flag='Y' AND assign_to=? AND [FinalRemarkOners]=?", new OneRsTypeRowMapper(),u.getUsername(),OneRsType);
		
		//List<Cheque> li = jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,FinalRemarkOners,[REQNTYPE] from [spsv1_new].[dbo].[tbl_samplingInput] where flag='Y' AND assign_to=? AND [FinalRemarkOners]=?  order by [REQNTYPE] desc , PAYAMT desc", new OneRsTypeRowMapper(),u.getUsername(),OneRsType);
		//select REQNTYPE,PAYAMT,REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,FinalRemarkOners from [spsv1_new].[dbo].[tbl_samplingInput] where flag='Y' AND assign_to='pritamd' AND [FinalRemarkOners]='Need To Check'  order by [REQNTYPE] desc , PAYAMT desc
		System.out.println(li);
		return li;
	}

	private static class OneRsTypeRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {


			Cheque cheque = new Cheque();
			cheque.setPrnNo(rs.getString(1));
			cheque.setPolicyno(rs.getString(2));
			//cheque.setClientId(rs.getString(3));
			cheque.setName(rs.getString(3));
			cheque.setFinalRemarkOners(rs.getString(4));
			cheque.setPaymentMode(rs.getString(5));
			cheque.setDescr(rs.getString(6));
			return cheque;
		}

	}

	@Override
	public Cheque getSruCase(String prnNo) {
		Cheque cheque = jdbcTemplate.queryForObject("select DESCR, * FROM [spsv1].[dbo].[tbl_samplingInput] where DESCR Like 'SRU%' and [REQNNO]=?", new SruCaseRowMapper(),prnNo);
		return cheque;
	}
	
	private static class SruCaseRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cheque cheque = new Cheque();
			cheque.setDescr(rs.getString("DESCR"));
		
			return cheque;
		}	
	}





}
