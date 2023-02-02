package presampling.repositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prodata.spsv1.model.Cheque;
import com.prodata.spsv1.model.ProductivityReport;

import presampling.repository.PreReportRepository;





@Repository
public class PreReportRepositoryImpl implements PreReportRepository{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	/*@Override
	public List<ProductivityReport> getReport(String sdate, String edate,String employee) {
		System.out.println("getReport"+employee);
		//return jdbcTemplate.query("select distinct (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=?) as allocatetssk, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='Y') as pendingtask, (select distinct assign_to from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=?) as username, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='N') as complete from tbl_responseInput",new reportRowMapper(), sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee);
		//return jdbcTemplate.query("select distinct (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and Final_remark <> 'Ok To Process') as allocatetssk, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='Y' and Final_remark <> 'Ok To Process') as pendingtask, (select distinct assign_to from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=?) as username, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='N' and Final_remark <> 'Ok To Process') as complete from tbl_responseInput",new reportRowMapper(), sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee);
		return jdbcTemplate.query("select distinct (select distinct assign_to from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=?) as username,(select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and Final_remark <> 'Ok To Process') as maker_allocatetssk, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='Y' and Final_remark <> 'Ok To Process') as maker_pendingtask, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='N' and Final_remark <> 'Ok To Process') as maker_completetask , (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerAssign_to=?) as checker_allocatetssk , (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerAssign_to=? and checkerFlag='Y') as checker_pendingtask, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerAssign_to=? and checkerFlag='N') as checker_completetask , (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and Final_remark <> 'Ok To Process')+ (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerAssign_to=? and checkerFlag='Y') as total_allocatetask, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='Y' and Final_remark <> 'Ok To Process')+ (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerAssign_to=? and checkerFlag='Y') as total_pendingtask, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='N' and Final_remark <> 'Ok To Process')+ (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerAssign_to=? and checkerFlag='N') as total_completetask from tbl_responseInput",new reportRowMapper(), sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee);
	}*/
	
	@Override
	public List<ProductivityReport> getReport(String sdate, String edate,String employee) {
		//System.out.println("getReport"+employee);
		//return jdbcTemplate.query("select distinct (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=?) as allocatetssk, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='Y') as pendingtask, (select distinct assign_to from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=?) as username, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='N') as complete from tbl_responseInput",new reportRowMapper(), sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee);
		//return jdbcTemplate.query("select distinct (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and Final_remark <> 'Ok To Process') as allocatetssk, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='Y' and Final_remark <> 'Ok To Process') as pendingtask, (select distinct assign_to from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=?) as username, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='N' and Final_remark <> 'Ok To Process') as complete from tbl_responseInput",new reportRowMapper(), sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee);
		
		List<ProductivityReport> reportlist = null;
		try {
			//String reportrecord = jdbcTemplate.queryForObject("select distinct assign_to from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=?",String.class, sdate,edate,employee);
			//List<ProductivityReport> reportrecord = jdbcTemplate.query("select distinct assign_to from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=?",new RrecordeportRowMapper(), sdate,edate,employee);
			List<ProductivityReport> reportrecord = jdbcTemplate.query("select distinct assign_to from [tbl_responseInput] where allocate_date BETWEEN ? AND ? and assign_to=?",new RrecordeportRowMapper(), sdate,edate,employee);
			//System.out.println("getReport:reportrecord.............. "+reportrecord);
			if(!reportrecord.isEmpty()) {
				//reportlist = jdbcTemplate.query("select distinct (select distinct assign_to from [tbl_responseInput] where assign_to=?) as username, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and Final_remark <> 'Ok To Process') as maker_allocatetssk, (select count(*) as count from [tbl_responseInput] where  assign_to=? and flag='Y' and Final_remark <> 'Ok To Process') as maker_pendingtask, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='N' and Final_remark <> 'Ok To Process') as maker_completetask , (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerassign_to=? and Final_remark <> 'Ok To Process') as checker_allocatetssk , (select count(*) as count from [tbl_responseInput] where   checkerassign_to=? and checkerFlag='Y' and Final_remark <> 'Ok To Process') as checker_pendingtask, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerassign_to=? and checkerFlag='N' and Final_remark <> 'Ok To Process') as checker_completetask , (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and Final_remark <> 'Ok To Process')+ (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerassign_to=? and Final_remark <> 'Ok To Process') as total_allocatetask, (select count(*) as count from [tbl_responseInput] where assign_to=? and flag='Y' and Final_remark <> 'Ok To Process')+ (select count(*) as count from [tbl_responseInput] where  checkerassign_to=? and checkerFlag='Y' and Final_remark <> 'Ok To Process') as total_pendingtask, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='N' and Final_remark <> 'Ok To Process')+ (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerassign_to=? and checkerFlag='N' and Final_remark <> 'Ok To Process') as total_completetask from tbl_responseInput",new reportRowMapper(), employee,sdate,edate,employee,employee,sdate,edate,employee,sdate,edate,employee,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,employee,employee,sdate,edate,employee,sdate,edate,employee);
				reportlist = jdbcTemplate.query("select distinct (select distinct assign_to from [tbl_responseInput] where assign_to=?) as username, (select count(*) as count from [tbl_responseInput] where allocate_date BETWEEN ? AND ? and assign_to=? and Final_remark <> 'Ok To Process') as maker_allocatetssk, (select count(*) as count from [tbl_responseInput] where  assign_to=? and flag='Y' and Final_remark <> 'Ok To Process') as maker_pendingtask, (select count(*) as count from [tbl_responseInput] where allocate_date BETWEEN ? AND ? and assign_to=? and flag='N' and Final_remark <> 'Ok To Process') as maker_completetask , (select count(*) as count from [tbl_responseInput] where allocate_date BETWEEN ? AND ? and checkerassign_to=? and Final_remark <> 'Ok To Process') as checker_allocatetssk , (select count(*) as count from [tbl_responseInput] where   checkerassign_to=? and checkerFlag='Y' and Final_remark <> 'Ok To Process') as checker_pendingtask, (select count(*) as count from [tbl_responseInput] where allocate_date BETWEEN ? AND ? and checkerassign_to=? and checkerFlag='N' and Final_remark <> 'Ok To Process') as checker_completetask , (select count(*) as count from [tbl_responseInput] where allocate_date BETWEEN ? AND ? and assign_to=? and Final_remark <> 'Ok To Process')+ (select count(*) as count from [tbl_responseInput] where allocate_date BETWEEN ? AND ? and checkerassign_to=? and Final_remark <> 'Ok To Process') as total_allocatetask, (select count(*) as count from [tbl_responseInput] where assign_to=? and flag='Y' and Final_remark <> 'Ok To Process')+ (select count(*) as count from [tbl_responseInput] where  checkerassign_to=? and checkerFlag='Y' and Final_remark <> 'Ok To Process') as total_pendingtask, (select count(*) as count from [tbl_responseInput] where allocate_date BETWEEN ? AND ? and assign_to=? and flag='N' and Final_remark <> 'Ok To Process')+ (select count(*) as count from [tbl_responseInput] where allocate_date BETWEEN ? AND ? and checkerassign_to=? and checkerFlag='N' and Final_remark <> 'Ok To Process') as total_completetask from tbl_responseInput",new reportRowMapper(), employee,sdate,edate,employee,employee,sdate,edate,employee,sdate,edate,employee,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,employee,employee,sdate,edate,employee,sdate,edate,employee);
			}
			else {
				//reportlist = jdbcTemplate.query("select distinct (select distinct checkerAssign_to from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerAssign_to=?) as username, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and Final_remark <> 'Ok To Process') as maker_allocatetssk, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='Y' and Final_remark <> 'Ok To Process') as maker_pendingtask, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='N' and Final_remark <> 'Ok To Process') as maker_completetask , (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerAssign_to=? and Final_remark <> 'Ok To Process') as checker_allocatetssk , (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerAssign_to=? and checkerFlag='Y' and Final_remark <> 'Ok To Process') as checker_pendingtask, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerAssign_to=? and checkerFlag='N' and Final_remark <> 'Ok To Process') as checker_completetask , (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and Final_remark <> 'Ok To Process')+ (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerAssign_to=? and Final_remark <> 'Ok To Process') as total_allocatetask, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='Y' and Final_remark <> 'Ok To Process')+  (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerAssign_to=? and checkerFlag='Y' and Final_remark <> 'Ok To Process') as total_pendingtask, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='N' and Final_remark <> 'Ok To Process')+  (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerAssign_to=? and checkerFlag='N' and Final_remark <> 'Ok To Process') as total_completetask from tbl_responseInput",new reportRowMapper(), sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee);
				//reportlist = jdbcTemplate.query("select distinct (select distinct checkerAssign_to from [tbl_responseInput] where checkerAssign_to=?) as username, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and Final_remark <> 'Ok To Process') as maker_allocatetssk, (select count(*) as count from [tbl_responseInput] where  assign_to=? and flag='Y' and Final_remark <> 'Ok To Process') as maker_pendingtask, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='N' and Final_remark <> 'Ok To Process') as maker_completetask , (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerassign_to=? and Final_remark <> 'Ok To Process') as checker_allocatetssk , (select count(*) as count from [tbl_responseInput] where   checkerassign_to=? and checkerFlag='Y' and Final_remark <> 'Ok To Process') as checker_pendingtask, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerassign_to=? and checkerFlag='N' and Final_remark <> 'Ok To Process') as checker_completetask , (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and Final_remark <> 'Ok To Process')+ (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerassign_to=? and Final_remark <> 'Ok To Process') as total_allocatetask, (select count(*) as count from [tbl_responseInput] where assign_to=? and flag='Y' and Final_remark <> 'Ok To Process')+ (select count(*) as count from [tbl_responseInput] where  checkerassign_to=? and checkerFlag='Y' and Final_remark <> 'Ok To Process') as total_pendingtask, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='N' and Final_remark <> 'Ok To Process')+ (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerassign_to=? and checkerFlag='N' and Final_remark <> 'Ok To Process') as total_completetask from tbl_responseInput",new reportRowMapper(), employee,sdate,edate,employee,employee,sdate,edate,employee,sdate,edate,employee,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,employee,employee,sdate,edate,employee,sdate,edate,employee);
				reportlist = jdbcTemplate.query("select distinct (select distinct checkerAssign_to from [tbl_responseInput] where checkerAssign_to=?) as username, (select count(*) as count from [tbl_responseInput] where allocate_date BETWEEN ? AND ? and assign_to=? and Final_remark <> 'Ok To Process') as maker_allocatetssk, (select count(*) as count from [tbl_responseInput] where  assign_to=? and flag='Y' and Final_remark <> 'Ok To Process') as maker_pendingtask, (select count(*) as count from [tbl_responseInput] where allocate_date BETWEEN ? AND ? and assign_to=? and flag='N' and Final_remark <> 'Ok To Process') as maker_completetask , (select count(*) as count from [tbl_responseInput] where allocate_date BETWEEN ? AND ? and checkerassign_to=? and Final_remark <> 'Ok To Process') as checker_allocatetssk , (select count(*) as count from [tbl_responseInput] where   checkerassign_to=? and checkerFlag='Y' and Final_remark <> 'Ok To Process') as checker_pendingtask, (select count(*) as count from [tbl_responseInput] where allocate_date BETWEEN ? AND ? and checkerassign_to=? and checkerFlag='N' and Final_remark <> 'Ok To Process') as checker_completetask , (select count(*) as count from [tbl_responseInput] where allocate_date BETWEEN ? AND ? and assign_to=? and Final_remark <> 'Ok To Process')+ (select count(*) as count from [tbl_responseInput] where allocate_date BETWEEN ? AND ? and checkerassign_to=? and Final_remark <> 'Ok To Process') as total_allocatetask, (select count(*) as count from [tbl_responseInput] where assign_to=? and flag='Y' and Final_remark <> 'Ok To Process')+ (select count(*) as count from [tbl_responseInput] where  checkerassign_to=? and checkerFlag='Y' and Final_remark <> 'Ok To Process') as total_pendingtask, (select count(*) as count from [tbl_responseInput] where allocate_date BETWEEN ? AND ? and assign_to=? and flag='N' and Final_remark <> 'Ok To Process')+ (select count(*) as count from [tbl_responseInput] where allocate_date BETWEEN ? AND ? and checkerassign_to=? and checkerFlag='N' and Final_remark <> 'Ok To Process') as total_completetask from tbl_responseInput",new reportRowMapper(), employee,sdate,edate,employee,employee,sdate,edate,employee,sdate,edate,employee,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,employee,employee,sdate,edate,employee,sdate,edate,employee);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
	
		return reportlist;
		//return jdbcTemplate.query("select distinct (select distinct assign_to from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=?) as username,(select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and Final_remark <> 'Ok To Process') as maker_allocatetssk, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='Y' and Final_remark <> 'Ok To Process') as maker_pendingtask, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='N' and Final_remark <> 'Ok To Process') as maker_completetask , (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerAssign_to=?) as checker_allocatetssk , (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerAssign_to=? and checkerFlag='Y') as checker_pendingtask, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerAssign_to=? and checkerFlag='N') as checker_completetask , (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and Final_remark <> 'Ok To Process')+ (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerAssign_to=? and checkerFlag='Y') as total_allocatetask, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='Y' and Final_remark <> 'Ok To Process')+ (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerAssign_to=? and checkerFlag='Y') as total_pendingtask, (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and assign_to=? and flag='N' and Final_remark <> 'Ok To Process')+ (select count(*) as count from [tbl_responseInput] where todaysdate BETWEEN ? AND ? and checkerAssign_to=? and checkerFlag='N') as total_completetask from tbl_responseInput",new reportRowMapper(), sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee);
	}
	
	private static class RrecordeportRowMapper implements RowMapper<ProductivityReport>
	{
		@Override
		public ProductivityReport mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductivityReport pd = new ProductivityReport();
			pd.setUsername(rs.getString("assign_to"));
			return pd;
		}
	}
	
	private static class reportRowMapper implements RowMapper<ProductivityReport>
	{
		@Override
		public ProductivityReport mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductivityReport pd = new ProductivityReport();
			pd.setUsername(rs.getString("username"));
			pd.setMkraloctsk(rs.getString("maker_allocatetssk"));
			pd.setMkrpendtsk(rs.getString("maker_pendingtask"));
			pd.setMkrcomplt(rs.getString("maker_completetask"));
			pd.setChraloctsk(rs.getString("checker_allocatetssk"));
			pd.setChrpentsk(rs.getString("checker_pendingtask"));
			pd.setChrcmplt(rs.getString("checker_completetask"));
			pd.setAllocate(rs.getString("total_allocatetask"));
			pd.setPending(rs.getString("total_pendingtask"));
			pd.setComplete(rs.getString("total_completetask"));
			//System.out.println("productivity report:get report"+pd);
			return pd;
		}
	}
	
	@Override
	public List<Cheque> getPolicyRelatedUser(String policyValue) {
		//System.out.println("getPolicyRelatedUser:policyValue.. "+policyValue);
		return jdbcTemplate.query("select [Policy No.],todaysdate,assign_to,CheckedName,checkerAssign_to,CheckerCheckedName from tbl_responseInput where [Policy No.] = ?",new searchRowMapper(),policyValue);
	}
	private static class searchRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cheque cheque = new Cheque();
			cheque.setPolicyno(rs.getString(1));
			cheque.setDate(rs.getString(2));
			cheque.setMakerName(rs.getString(3));
			cheque.setMakerRemark(rs.getString(4));
			cheque.setCheckerName(rs.getString(5));
			cheque.setCheckerRemark(rs.getString(6));
			//System.out.println("getPolicyRelatedUser:cheque "+cheque);
			return cheque;
		}
	}
	/*-----------------------------------------------------------------------------------------*/
	/*@Override
	public List<ProductivityReport> getReport(String sdate, String edate, User u) {
		String username="shreya";
		jdbcTemplate.query("select distinct (select count(*) as count from [tbl_inputDetails] where date BETWEEN ? AND ? and assign_to=?) as allocatetssk, (select count(*) as count from [tbl_inputDetails] where date BETWEEN ? AND ? and assign_to=? and flag='N') as pendingtask from [tbl_inputDetails]",new reportRowMapper(), sdate,edate,u.getUsername(),sdate,edate,u.getUsername());
		return null;
	}
	private static class reportRowMapper implements RowMapper<ProductivityReport>
	{
		@Override
		public ProductivityReport mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductivityReport pd = new ProductivityReport();
			pd.setUsername(rs.getString("assign_to"));
			pd.setAllocate(rs.getString("allocatetssk"));
			pd.setPending(rs.getString("pendingtask"));
			return pd;
		}
	}*/

	@Override
	public Cheque samplingMis(){
		
		return jdbcTemplate.queryForObject("select [DATA TIME], sum (case when FinalRemarkOners ='Need To Check' and [CheckedName]='Namechecked' then 1 else 0 end) Needcheckok, sum (case when FinalRemarkOners ='Need To Check' and [CheckedName]='reverse' then 1 else 0 end) needCheckreverse, sum (case when FinalRemarkOners ='Partial Ok' and [CheckedName]='Namechecked' then 1 else 0 end) partialOk, sum (case when FinalRemarkOners ='Partial Ok' and [CheckedName]='reverse' then 1 else 0 end) PartialReverse,(select round(sum(CAST([Payment Amt] as float)),2) from tbl_samplingOutput where FinalRemarkOners ='Need To Check' and [CheckedName]='Namechecked' ) okntc, (select round(sum(CAST([Payment Amt] as float)),2) from tbl_samplingOutput where FinalRemarkOners ='Need To Check' and [CheckedName]='reverse' ) revntc, (select round(sum(CAST([Payment Amt] as float)),2) from tbl_samplingOutput where FinalRemarkOners ='Partial Ok' and [CheckedName]='Namechecked' ) parok, (select round(sum(CAST([Payment Amt] as float)),2) from tbl_samplingOutput where FinalRemarkOners ='Partial Ok' and [CheckedName]='reverse' ) parrev from tbl_samplingOutput where [DATA TIME] = CONVERT(VARCHAR(10), getdate(),120)group by [DATA TIME]", new DisplayStatusRowMapper());
	
	}
	
	
	private static class  DisplayStatusRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Cheque cheque = new Cheque();
			
			cheque.setNeedcheckok(rs.getString("Needcheckok"));
			cheque.setNeedCheckreverse(rs.getString("needCheckreverse"));
			cheque.setPartialOk(rs.getString("partialOk"));
			cheque.setPartialReverse(rs.getString("PartialReverse"));
			if(rs.getBigDecimal("okntc")!=null)
			//cheque.setOkntc(rs.getBigDecimal("okntc").toString());
				cheque.setOkntc(rs.getDouble("okntc"));
			if(rs.getBigDecimal("revntc")!=null)
			//cheque.setRevntc(rs.getBigDecimal("revntc").toString());
				cheque.setRevntc(rs.getDouble("revntc"));
			if(rs.getBigDecimal("parok")!=null)
			//cheque.setParok(rs.getBigDecimal("parok").toString());
				cheque.setParok(rs.getDouble("parok"));
			if(rs.getBigDecimal("parrev")!=null)
			//cheque.setParrev(rs.getBigDecimal("parrev").toString());
				cheque.setParrev(rs.getDouble("parrev"));
			
	
			//System.out.println("DisplayQbpmStatus repository... "+cheque);
			return cheque;
		}
	}
	

}
