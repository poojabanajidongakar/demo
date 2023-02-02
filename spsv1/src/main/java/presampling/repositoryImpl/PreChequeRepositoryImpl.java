package presampling.repositoryImpl;

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

import presampling.repository.PreChequeRepository;
import presampling.repository.PreSpoolRepository;

@Repository
public class PreChequeRepositoryImpl implements PreChequeRepository {

	@Autowired
	private PreSpoolRepository spoolRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*@Override
	public List<Cheque> ToDoList(User u) {
		System.out.println("username in toDolist "+u.getUsername());
		HttpSession s=null;
		s.getAttribute("user");
		List<Cheque> li = jdbcTemplate.query("select [Policy No.],[Bene_Name],[Final_remark] from [tbl_responseInput] where flag='Y' AND assign_to=? and [Final_remark]<>'Ok To Process'", new ToDoListRowMapper(),u.getUsername());
		return li;
	}*/
	
	@Override
	public List<Cheque> ToDoList(User u, String type) {
		//System.out.println("username in toDolist "+u.getUsername());
		//System.out.println("type in toDolist "+type);
		HttpSession s=null;
		List<Cheque> li = null;
		//System.out.println("ToDoList:u.getUsername()"+ u.getUsername());
		//s.getAttribute("user");
		try {
			if(type.equalsIgnoreCase("maker")) {
				li = jdbcTemplate.query("select [Policy No.],[Bene_Name],[Final_remark] from [tbl_responseInput] where flag='Y' AND assign_to=? and [Final_remark]<>'Ok To Process'", new ToDoListRowMapper(),u.getUsername());
			}
			else if(type.equalsIgnoreCase("checker")) {
				//li = jdbcTemplate.query("select [Policy No.],[Bene_Name],[Final_remark] from [tbl_responseInput] where checkerFlag='Y' AND checkerAssign_to=? and [CheckedName]='Ok To Process'", new ToDoListRowMapper(),u.getUsername());
				li = jdbcTemplate.query("select [Policy No.],[Bene_Name],[Final_remark] from [tbl_responseInput] where checkerFlag='Y' AND checkerAssign_to=? and [Final_remark]<>'Ok To Process'", new ToDoListRowMapper(),u.getUsername());
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return li;
	}

	@Override
	public String getpmode(String policyno){

		String	s= jdbcTemplate.queryForObject("select [Policy No.] from [tbl_responseInput] where [Policy No.]=?", String.class,policyno);
		return s;
	}
	
	@Override
	public String getOneRsType(String policyno){

		String	s1= jdbcTemplate.queryForObject("select [Final_remark] from [tbl_responseInput] where [Policy No.]=?", String.class,policyno);
		return s1;
	}

	private static class ToDoListRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cheque cheque = new Cheque();
			
			cheque.setPolicyno(rs.getString(1));
			cheque.setName(rs.getString(2));
			cheque.setFinalRemarkOners(rs.getString(3));
			return cheque;
		}

	}

	@Override
	public List<User> getUserData() {
		List<User> li = jdbcTemplate.query("select * from tbl_user", new UserDataRowMapper());
		return li;
	}

	private static class UserDataRowMapper implements RowMapper<User>
	{
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			return user;
		}	
	}

	/*@Override
	public int countToDoList(User u) {
		int countrecord = jdbcTemplate.queryForObject("select count(*) as count from [tbl_responseInput] where flag='Y' AND assign_to=? and [Final_remark]<>'Ok To Process'", Integer.class,u.getUsername()); 
		return countrecord;
	}*/

	@Override
	public int countToDoList(User u, String type) {
		int countrecord = 0;
		try {
			if(type.equalsIgnoreCase("maker")) {
				countrecord = jdbcTemplate.queryForObject("select count(*) as count from [tbl_responseInput] where flag='Y' AND assign_to=? and [Final_remark]<>'Ok To Process'", Integer.class,u.getUsername());
			}
			else if(type.equalsIgnoreCase("checker")) {
				//countrecord = jdbcTemplate.queryForObject("select count(*) as count from [tbl_responseInput] where checkerFlag='Y' AND checkerAssign_to=? and [CheckedName]='Ok To Process'", Integer.class,u.getUsername());
				countrecord = jdbcTemplate.queryForObject("select count(*) as count from [tbl_responseInput] where checkerFlag='Y' AND checkerAssign_to=? and [Final_remark]<>'Ok To Process'", Integer.class,u.getUsername());
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return countrecord;
	}

	@Override
	public int countToDoListpaymode(User u,String paymode) {
		int countmode = jdbcTemplate.queryForObject("select count(*) as count from [tbl_responseInput] where flag='Y' AND assign_to=? and [REQNTYPE]=?", Integer.class,u.getUsername(),paymode); 
		return countmode;
	}
	
	/*@Override
	public int countToDoListOneRs(User u,String OneRsType) {
		int countOneRs = 0;
		//System.out.println("OneRsType"+OneRsType+"and   pmode"+pmode);
	
		if(OneRsType!=null){
			countOneRs = jdbcTemplate.queryForObject("select count(*) as count from [tbl_responseInput] where flag='Y' AND assign_to=? and [Final_remark]=?", Integer.class,u.getUsername(),OneRsType);
						}
	
		//int countOneRs = jdbcTemplate.queryForObject("select count(*) as count from [tbl_responseInput] where flag='Y' AND assign_to=? and [REQNTYPE]=?", Integer.class,u.getUsername(),OneRsType); 
		
		return countOneRs;
	}*/
	
	@Override
	public int countToDoListOneRs(User u,String OneRsType, String type) {
		int countOneRs = 0;
		//System.out.println("countToDoListOneRs:OneRsType"+OneRsType+"and   type"+type);
		
		if(type.equals("maker")) {
			if(OneRsType!=null){
				countOneRs = jdbcTemplate.queryForObject("select count(*) as count from [tbl_responseInput] where flag='Y' AND assign_to=? and [Final_remark]=?", Integer.class,u.getUsername(),OneRsType);
			}
		}
		else if(type.equals("checker")) {
			countOneRs = jdbcTemplate.queryForObject("select count(*) as count from [tbl_responseInput] where [checkerFlag]='Y' AND [checkerAssign_to]=? and [Final_remark]=?", Integer.class,u.getUsername(),OneRsType);
		}
		//int countOneRs = jdbcTemplate.queryForObject("select count(*) as count from [tbl_responseInput] where flag='Y' AND assign_to=? and [REQNTYPE]=?", Integer.class,u.getUsername(),OneRsType); 
		
		return countOneRs;
	}
	
	@Override
	public Cheque getData(String policyNo) {

		Cheque cheque = jdbcTemplate.queryForObject("select top 1 [Policy No.],[CREDIT_ACCOUNT_NO],[SapNameFinal],[Bene_Name],[Final_remark] from [tbl_responseInput] where [Policy No.]=?", new ChequeRowMapper(),policyNo);
		
		return cheque;
	}

	private static class ChequeRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cheque cheque = new Cheque();
			cheque.setPolicyno(rs.getString(1));
			cheque.setmAccountNumber(rs.getString(2));
			cheque.setName(rs.getString(3));
			cheque.setOneRsName(rs.getString(4));
			cheque.setFinalRemarkOners(rs.getString(5));
			return cheque;	
		}	
	}
	@Override
	public Cheque onersgetData(String policyNo,String onersremark){
		Cheque cheque = jdbcTemplate.queryForObject("select top 1 [Policy No.],[CREDIT_ACCOUNT_NO],[SapNameFinal],[Bene_Name],[Final_remark] from [tbl_responseInput] where [Policy No.]=? and [Final_remark]=?", new OnersChequeRowMapper(),policyNo,onersremark);
		return cheque;
	}
	
	private static class OnersChequeRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cheque cheque = new Cheque();

			cheque.setPolicyno(rs.getString(1));
			cheque.setmAccountNumber(rs.getString(2));
			cheque.setName(rs.getString(3));
			cheque.setOneRsName(rs.getString(4));
			cheque.setFinalRemarkOners(rs.getString(5));
			return cheque;	
		}	
	}
/*	@Override
	public Cheque updatePolicy(String policyNo,String prnno) {
		System.out.println("getData "+  policyNo);
		Cheque cheque = jdbcTemplate.queryForObject("select [Policy No.],[Client ID],[Policy No.],[Payment Amt],[Payment Mode],[Payment Reason],[Cross Ref Policy 1],[Application No.],[Bank Name],[Bank Code],[Customer Name],[Checker A/c No.],[IFSC Code],[MICR Code],[NM Code],[Sampler Name],[DATA TIME],[Remarks] from tbl_preSamplingOutput where [PRN No.]=?", new editChequeRowMapper(),prnno);
		System.out.println("plist = "+cheque);
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
			cheque.setAmount(rs.getString(4));
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
	}*/

	
	/*@Override
	public boolean insertIntoDb(Cheque cheque)
	{
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		String d=date.toString();
		
		String policyno=cheque.getvPolicyNo().trim();
		String chname;
		System.out.println("in insertIntoDb:type...."+cheque.getType());
		System.out.println("insertIntoDb"+policyno);
		
		if(cheque.getCheckName() != null)
		 {
			 chname=cheque.getCheckName();
		 }
		 else
		 {
			 chname="Not Checked";
		 }
		if(cheque.getCheckName().equalsIgnoreCase("Name Matched"))
		 {
			 cheque.setReason("Name Matched");
			 cheque.setCheckName("Ok To Process");
		 }
		 else if(cheque.getCheckName().equalsIgnoreCase("As Per Document"))
		 {
			 cheque.setReason("Ok As Per Document");
			 cheque.setCheckName("Ok To Process");
		 }
		 else if(cheque.getCheckName().equalsIgnoreCase("Reverse"))
		 {
			 cheque.setReason("Name Not Matched");
			 cheque.setCheckName("Reverse");
		 }
		
		try {
			if(cheque.getType().equalsIgnoreCase("maker")) {
				jdbcTemplate.update("update [tbl_responseInput] set [CheckedName]=?,[reason]=? where [Policy No.]=? ",
						 cheque.getCheckName(),
						 cheque.getReason(),
						 policyno
						 );
				System.out.println("cheque.getCheckName()......... "+cheque.getCheckName());
				if(cheque.getCheckName().equalsIgnoreCase("Ok To Process")) {
					System.out.println("inside update check flag");
					jdbcTemplate.update("update [tbl_responseInput] set [checkerFlag]='Y', [checkerAssign_to]='sarveshs' where [Policy No.]=?",policyno);
				}
			}
			else if(cheque.getType().equalsIgnoreCase("checker")) {
				jdbcTemplate.update("update [tbl_responseInput] set [CheckerCheckedName]=?,[CheckerReason]=? where [Policy No.]=? ",
						 cheque.getCheckName(),
						 cheque.getReason(),
						 policyno
						 );
			}
			
			jdbcTemplate.update("update [tbl_responseInput] set dataEntryDate=? where [Policy No.]=?",d,policyno);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		 jdbcTemplate.update("update [tbl_responseInput] set [CheckedName]=?,[reason]=? where [Policy No.]=? ",
				 cheque.getCheckName(),
				 cheque.getReason(),
				 policyno
				 );
		
		return true;
	}*/
	
	
	
	@Override
	public boolean insertIntoDb(Cheque cheque,User u)
	{
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		String d=date.toString();
		
		String policyno=cheque.getvPolicyNo().trim();
		String chname;
		//System.out.println("in insertIntoDb:type...."+cheque.getType());
		System.out.println("insertIntoDb"+policyno);
		
		/*if(cheque.getCheckName() != null)
		 {
			 chname=cheque.getCheckName();
		 }
		 else
		 {
			 chname="Not Checked";
		 }*/
		if(cheque.getCheckName().equalsIgnoreCase("Name Matched"))
		 {
			 cheque.setReason("Name Matched");
			 cheque.setCheckName("Ok To Process");
		 }
		 else if(cheque.getCheckName().equalsIgnoreCase("As Per Document"))
		 {
			 cheque.setReason("Ok As Per Document");
			 cheque.setCheckName("Ok To Process");
		 }
		 else if(cheque.getCheckName().equalsIgnoreCase("Reverse"))
		 {
			 cheque.setReason("Name Not Matched");
			 cheque.setCheckName("Reverse");
		 }
		
		try {
			if(cheque.getType().equalsIgnoreCase("maker")) {
				jdbcTemplate.update("update [tbl_responseInput] set [CheckedName]=?,[reason]=? where [Policy No.]=? ",
						 cheque.getCheckName(),
						 cheque.getReason(),
						 policyno
						 );
				//System.out.println("cheque.getCheckName()......... "+cheque.getCheckName());
				/*if(cheque.getCheckName().equalsIgnoreCase("Ok To Process")) {
					System.out.println("inside update check flag");
					//jdbcTemplate.update("update [tbl_responseInput] set [checkerFlag]='Y', [checkerAssign_to]='sarveshs' where [Policy No.]=?",policyno);
					jdbcTemplate.update("update [tbl_responseInput] set [checkerFlag]='Y', [checkerAssign_to]=? where [Policy No.]=?",u.getUsername(),policyno);
				}*/
			}
			else if(cheque.getType().equalsIgnoreCase("checker")) {
				jdbcTemplate.update("update [tbl_responseInput] set [CheckerCheckedName]=?,[CheckerReason]=? where [Policy No.]=? ",
						 cheque.getCheckName(),
						 cheque.getReason(),
						 policyno
						 );
			}
			
			jdbcTemplate.update("update [tbl_responseInput] set dataEntryDate=? where [Policy No.]=?",d,policyno);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		 /*jdbcTemplate.update("update [tbl_responseInput] set [CheckedName]=?,[reason]=? where [Policy No.]=? ",
				 cheque.getCheckName(),
				 cheque.getReason(),
				 policyno
				 );*/
		
		return true;
	}
	
	
/*	@Override
	public boolean insertIntoDb(Cheque cheque, String maccNo, String vaccNo,User u,String paymentmode,String ifsccode,String bankname)
	{

		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		String d=date.toString();
		String disczero;
		String cross1;
		String cross2;
		String policy=cheque.getvPolicyNo();
		String prnno=cheque.getPrnNo();
		Cheque spool = spoolRepository.findById(policy,u);
		//String vendor=spool.getVendorName();
		//String desc=spool.getDescr();
		String chname;

		System.out.println("insertIntoDb:cheque "+paymentmode);
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
			 jdbcTemplate.update("insert into tbl_preSamplingOutput ([Policy No.],[Client ID],[PRN No.],[Payment Amt],[Payment Mode],[Payment Reason],[MOS],[Cross Ref Policy 1],[Cross Ref Policy 2],[Application No.],[Bank Name],[Bank Code],[policy no.1],[Customer Name],[Checker A/c No.],[IFSC Code],[MICR Code],[NM Code],[Pay Mode],[Error / Ok],assign_to,[DATA TIME],[Remarks],[Sampler Name],[Reference],[CheckedName],[OneRsName],[reason],[FinalRemarkOners]) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
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
					 chname,
					 cheque.getOneRsName(),
					 cheque.getReason(),
					 cheque.getFinalRemarkOners());
			 


			 System.out.println("Discrepancy tag insert into discrepancy"+cheque.getDiscrepancyTag());
		}
		return true;
	}*/

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
		jdbcTemplate.update("update tbl_preSamplingOutput set [Policy No.]=?,[Client ID]=?,[PRN No.]=?,[Payment Amt]=?,[Payment Mode]=?,[Payment Reason]=?,[MOS]=?,[Cross Ref Policy 1]=?,[Cross Ref Policy 2]=?,[Application No.]=?,[Bank Name]=?,[Bank Code]=?,[policy no.1]=?,[Customer Name]=?,[Checker A/c No.]=?,[IFSC Code]=?,[MICR Code]=?,[NM Code]=?,[Pay Mode]=?,[Error / Ok]=?,assign_to=?,[DATA TIME]=?,[Remarks]=?,[Sampler Name]=?,[Reference]=? where [PRN No.]=?",
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

		//System.out.println("Discrepancy tag insert into discrepancy"+cheque.getDiscrepancyTag());

		return true;
	}



	@Override
	public void changeToDoListFlag(String policyno) {
		jdbcTemplate.update("UPDATE [tbl_responseInput] SET flag = 'N' WHERE [Policy No.] = ?", policyno);
	}
	
	@Override
	public void changeCheckerToDoListFlag(String policyno) {
		jdbcTemplate.update("UPDATE [tbl_responseInput] SET checkerFlag = 'N' WHERE [Policy No.] = ?", policyno);
	}

	@Override
	public Cheque getDcDetails(String dc) {
		//System.out.println("getDcDetails"+dc);
		Cheque cheque = jdbcTemplate.queryForObject("select top(1)* from [tbl_responseInput] where [Policy No.] = ?", new dcRowMapper(), dc);
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
			//System.out.println("DC madhla cheque"+cheque);
			return cheque;
		}
	}

	@Override
	public Cheque getIfscDetails(String ifscCode,String policyno) 
	{

		Cheque cheque = jdbcTemplate.queryForObject("select * from [tbl_responseInput] where [IFSC] = ? AND [Policy No.]=?", new ifscRowMapper(), ifscCode,policyno);
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
	public Cheque getMicrDetails(String micr,String policyno) {
		Cheque cheque = jdbcTemplate.queryForObject("select * from [tbl_responseInput] where [BANKKEY] = ? AND [Policy No.]=?", new micrRowMapper(), micr,policyno);
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
			CallableStatement cs = jdbcTemplate.getDataSource().getConnection().prepareCall("{ call dbo.presampling_Response_upload(?) }");
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
		try {
			CallableStatement cs = jdbcTemplate.getDataSource().getConnection().prepareCall("{ call dbo.eliminate_duplicates() }");
			//System.out.println("new file name: "+file.getOriginalFilename());
			//cs.setString(1,file);
			cs.execute();
			cs.close();
		} catch (SQLException e) {

			e.printStackTrace();
			return false;

		}
		
		return true;			
	}
	
	


	@Override
	public boolean insertDiscrCases(Cheque cheque, User u) {
		//System.out.println(cheque.getvPolicyNo()+"before");
		cheque.setAccountNo(cheque.getmAccountNumber().trim());
		//System.out.println(cheque.getmAccountNumber()+"  hererererere");

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
	public boolean compareData(String policyNo,String vaccNo,String ifsccode,String maccNo, String micrcode, String paymentmode,User u) {

		
		//String policy=cheque.getvPolicyNo();
		Cheque spool = spoolRepository.findById(policyNo,u);
		//System.out.println("sppol IFSC code"+spool.getMifscCode());

		if(paymentmode.equals("NEFT") || paymentmode.equals("RTGS") || paymentmode.equals("G") ) {
			//System.out.println("payment mode: neft");

			//System.out.println("vaccNo:comparedata"+vaccNo+"maccno:comparedata"+maccNo+"paymentmode:comparedata"+paymentmode+"IFSC here"+ifsccode);
			//boolean result = vaccNo.trim().equals(maccNo) && ifsccode.equals(spool.getIfscCode());
			//boolean result = vaccNo.trim().equals(maccNo);
			if(vaccNo.trim().equals(maccNo))
			{
				jdbcTemplate.update("UPDATE tbl_preSamplingOutput SET [T/F Account No.] = 'True' WHERE [Policy No.] = ?", policyNo);
			}
			else 
			{
				jdbcTemplate.update("UPDATE tbl_preSamplingOutput SET [T/F Account No.] = 'False' WHERE [Policy No.] = ?", policyNo);
			}

			if(ifsccode.equals(spool.getMifscCode()))
			{
				jdbcTemplate.update("UPDATE tbl_preSamplingOutput SET [T/F IFSC] = 'True', [T/F MICR] = 'True' WHERE [Policy No.] = ?", policyNo);
			}
			else
			{
				jdbcTemplate.update("UPDATE tbl_preSamplingOutput SET [T/F IFSC] = 'False', [T/F MICR] = 'False' WHERE [Policy No.] = ?", policyNo);
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
				 jdbcTemplate.update("UPDATE tbl_preSamplingOutput SET [T/F Account No.] = 'True',[T/F MICR] = 'True',[T/F IFSC] = 'True' WHERE [Policy No.] = ?", policyNo);
			 }
			 else 
			 {
				 jdbcTemplate.update("UPDATE tbl_preSamplingOutput SET [T/F Account No.] = 'False',[T/F MICR] = 'False',[T/F IFSC] = 'False' WHERE [Policy No.] = ?", policyNo);
			 }

			 /*if(ifsccode.equals(spool.getMifscCode()))
			{
				jdbcTemplate.update("UPDATE tbl_preSamplingOutput SET [T/F IFSC] = 'True', [T/F MICR] = 'True' WHERE [Policy No.] = ?", prnno);
			}
			else
			{
				jdbcTemplate.update("UPDATE tbl_preSamplingOutput SET [T/F IFSC] = 'False', [T/F MICR] = 'False' WHERE [Policy No.] = ?", prnno);
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
				 jdbcTemplate.update("UPDATE tbl_preSamplingOutput SET [T/F Account No.] = 'True' WHERE [Policy No.] = ?", policyNo);
			 }
			 else 
			 {
				 jdbcTemplate.update("UPDATE tbl_preSamplingOutput SET [T/F Account No.] = 'False' WHERE [Policy No.] = ?", policyNo);
			 }

			 if(micrcode.equals(spool.getMmicrCode()))
			 {
				 jdbcTemplate.update("UPDATE tbl_preSamplingOutput SET [T/F MICR] = 'True',[T/F IFSC] = 'True' WHERE [Policy No.] = ?", policyNo);
			 }
			 else
			 {
				 jdbcTemplate.update("UPDATE tbl_preSamplingOutput SET [T/F MICR] = 'False',[T/F IFSC] = 'False' WHERE [Policy No.] = ?", policyNo);
			 }

		 }
		 return false;


	}

	@Override
	public List<Cheque> showSubType(String paymode,User u) {

		List<Cheque> li = jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE from [tbl_responseInput] where flag='Y' AND assign_to=? AND [REQNTYPE]=?", new subTypeRowMapper(),u.getUsername(),paymode);
		//System.out.println(li);
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
			return cheque;
		}

	}

	/*@Override
	public List<Cheque> showOneRsType(String OneRsType, User u) {
		
		
		List<Cheque> li = jdbcTemplate.query("select [Policy No.],[Bene_Name],[Final_remark] from [tbl_responseInput] where flag='Y' AND assign_to=? AND [Final_remark]=?", new OneRsTypeRowMapper(),u.getUsername(),OneRsType);
		
		//List<Cheque> li = jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,FinalRemarkOners,[REQNTYPE] from [spsv1_new].[dbo].[[tbl_responseInput]] where flag='Y' AND assign_to=? AND [Final_remark]=?  order by [REQNTYPE] desc , PAYAMT desc", new OneRsTypeRowMapper(),u.getUsername(),OneRsType);
		//select REQNTYPE,PAYAMT,REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,FinalRemarkOners from [spsv1_new].[dbo].[[tbl_responseInput]] where flag='Y' AND assign_to='pritamd' AND [Final_remark]='Need To Check'  order by [REQNTYPE] desc , PAYAMT desc
		//System.out.println(li);
		return li;
	}*/

	@Override
	public List<Cheque> showOneRsType(String OneRsType, User u, String type) {
		//System.out.println("showOneRsType:type is..........."+type);
		List<Cheque> li = null;
		if(type.equals("maker")) {
			li = jdbcTemplate.query("select [Policy No.],[Bene_Name],[Final_remark] from [tbl_responseInput] where flag='Y' AND assign_to=? AND [Final_remark]=?", new OneRsTypeRowMapper(),u.getUsername(),OneRsType);
		}
		else if(type.equals("checker")) {
			li = jdbcTemplate.query("select [Policy No.],[Bene_Name],[Final_remark] from [tbl_responseInput] where [checkerFlag]='Y' AND [checkerAssign_to]=? AND [Final_remark]=?", new OneRsTypeRowMapper(),u.getUsername(),OneRsType);
		}
		return li;
	}
	
	private static class OneRsTypeRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {


			Cheque cheque = new Cheque();
			//cheque.setPrnNo(rs.getString(1));
			cheque.setPolicyno(rs.getString(1));
			//cheque.setClientId(rs.getString(3));
			cheque.setName(rs.getString(2));
			cheque.setFinalRemarkOners(rs.getString(3));
			return cheque;
		}

	}


	@Override
	public boolean preSamplingRemark() {
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set FinalRemark = 'Ok To Process OneRS' where Final_remark = 'Ok To Process'");
		jdbcTemplate.update("update a set a.FinalRemark = 'Ok To Process', a.FinalReason = a.CheckerReason from [spsv1].[dbo].[tbl_responseInput] a where a.[CheckedName] = 'Ok To Process' and a.[CheckerCheckedName] = 'Ok To Process'");
		jdbcTemplate.update("update a set a.FinalRemark = 'Reverse', a.FinalReason = a.reason from [spsv1].[dbo].[tbl_responseInput] a where a.[CheckedName] = 'Reverse' and a.[CheckerCheckedName] = 'Ok To Process'");
		jdbcTemplate.update("update a set a.FinalRemark = 'Reverse', a.FinalReason = a.[CheckerReason] from [spsv1].[dbo].[tbl_responseInput] a where a.[CheckedName] = 'Ok To Process' and a.[CheckerCheckedName] = 'Reverse'");
		jdbcTemplate.update("update a set a.FinalRemark = 'Reverse', a.FinalReason = a.[CheckerReason] from [spsv1].[dbo].[tbl_responseInput] a where a.[CheckedName] = 'Reverse' and a.[CheckerCheckedName] = 'Reverse'");
		//jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set FinalRemark = Final_remark where Final_remark = 'Ok To Process'");
		//jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set FinalRemark = CheckedName, FinalReason = reason where (FinalRemark is null or FinalRemark = NULL) and  CheckedName <> 'Ok To Process' ");
		//jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set FinalRemark = [CheckerCheckedName], FinalReason = [CheckerReason] where (FinalRemark is null or FinalRemark = NULL)");
		//jdbcTemplate.update("");
		return true;
	} 
	
	
}
