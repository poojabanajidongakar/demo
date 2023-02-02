package presampling.repositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prodata.spsv1.model.Cheque;
import com.prodata.spsv1.model.User;

import presampling.repository.PreDiscrepancyRepository;



@Repository
public class PreDiscrepancyRepositoryImpl implements PreDiscrepancyRepository
{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override 
	public List<String> getAllDiscrepancyTags()
	{	
		return jdbcTemplate.queryForList("SELECT disc_tag FROM tbl_Discrepancy",String.class);
	
	}



	/*@Override
	public Cheque getDescData(String policyNo) {
		System.out.println("getData "+  policyNo);
		//Cheque cheque = jdbcTemplate.queryForObject("select [REPNUM] crossrefpolicy,[CHDRNUM] policyno, COALESCE(LTRIM(RTRIM(SURNAME)),'')+GIVNAME as Name from tbl_inputDetails_bkp where [CHDRNUM]=?", new ChequeRowMapper(),policyNo);
		Cheque cheque = jdbcTemplate.queryForObject("select [REPNUM] crossrefpolicy,[CHDRNUM] policyno, Customer_Name from tbl_inputDetails_new where [CHDRNUM]=?", new ChequeRowMapper(),policyNo);
		System.out.println("plist = "+cheque);
		return cheque;
	}*/
	
	private static class ChequeRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cheque cheque = new Cheque();
			cheque.setPolicyno(rs.getString("policyno"));
			//cheque.setClientId(rs.getString("clientid"));
			cheque.setCrossRefPolicy(rs.getString("crossrefpolicy"));
			cheque.setCustName(rs.getString("Customer_Name"));
			//cheque.setCall_id(rs.getInt("CallID"));
			return cheque;	
		}	
	}

	@Override
	public List<Cheque> DescrepencyToDoList(User u) {
	
		List<Cheque> li = jdbcTemplate.query("select [Policy No],[Name], [Client Id] from tbl_discrepancyCases where flag='Y'AND username=?",  new DescrepencyToDoListRowMapper(),u.getUsername());
		return li;
	}

	private static class DescrepencyToDoListRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			
		
			Cheque cheque = new Cheque();
			cheque.setPolicyno(rs.getString("Policy No"));
			cheque.setCustName(rs.getString("Name"));
			cheque.setClientId(rs.getString("Client Id"));
			return cheque;
		}
	}

	@Override
	public boolean insertDesWriteCases(Cheque cheque) {
		System.out.println(cheque.getvPolicyNo()+"before");
		cheque.setAccountNo(cheque.getmAccountNumber().trim());
		System.out.println(cheque.getmAccountNumber()+"  hererererere");
		if(cheque.getmAccountNumber().trim().equals(cheque.getvAccountNumber().trim()))
		{
			if(cheque.getDiscrepancyTag()!=null)
			{
			cheque.setDiscrepancyTag(cheque.getDiscrepancyTag().replace('[', ' ').replace(']', ' ').trim());
			jdbcTemplate.update("insert into tbl_discrepancyCases ([Policy No],[Name],[DiscrepancyTag]) values(?,?,?)",
				cheque.getvPolicyNo(),
				cheque.getCustName(),
				cheque.getDiscrepancyTag());
				//cheque.getAccountNo(),
			  /* cheque.getReferenceno(),
			   cheque.getRemarks(),
			   cheque.getRemarksBy());*/
				return true;
			}
			else
				cheque.setDiscrepancyTag("");	
		}
		
		return false;
		
	}



	@Override
	public boolean compareData(Cheque cheque, String maccNo, String vaccNo) {
		boolean result = maccNo.trim().equals(vaccNo.trim());
		{

			jdbcTemplate.update("insert into tbl_DataEntry ([ContractNo],[Mode OF Payment],[FactoryHouse],[BankBranchCode],[IFSCCode],[AcountNo],[AccountType],[Name],[BranchName],[Instructions],[GMID],[ClientId],[Discrepancy],[Remarks],[Cross Ref Policy],[Application No.],[Cross Ref Application]) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
					cheque.getvPolicyNo(),
					cheque.getPaymentMode(),
					cheque.getFactoryHouse(),
					cheque.getMnmCode(),
					cheque.getMifscCode(),
					cheque.getmAccountNumber(),
					cheque.getAccountType(),
					cheque.getName().trim(),
					cheque.getBankName(),
					cheque.getInstructions(),
					cheque.getGmId(),
					cheque.getClientId().trim(),
					cheque.getDiscrepancyTag(), 
					cheque.getRemarks(),
					cheque.getCrossRefPolicy(),
					cheque.getApplicationNo(),
					cheque.getCrossApplicationNo());
       }
	return true;
	}



	@Override
	public void changeToDoListFlagDes(String policyNo) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Cheque getDataDes(String policyNo) {
		System.out.println("getData "+  policyNo);
		Cheque cheque = jdbcTemplate.queryForObject("select  [Policy No],[Name],[Client Id] from tbl_discrepancyCases where [Policy No]=?", new ChequeDESRowMapper(),policyNo);
		
		System.out.println("plist = "+cheque);
		return cheque;
	}
	private static class ChequeDESRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cheque cheque = new Cheque();
			cheque.setPolicyno(rs.getString("Policy No"));
			cheque.setCustName(rs.getString("Name"));
			cheque.setClientId(rs.getString("Client id"));
			
			
			return cheque;	
		}	
	}

	@Override
	public int countToDoList(User u) {
		int countrecord = jdbcTemplate.queryForObject("select count(*) as count from tbl_discrepancyCases where flag='Y' AND assign_to=?", Integer.class,u.getUsername()); 
		return countrecord;
		
	}
}
