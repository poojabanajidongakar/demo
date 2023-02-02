package presampling.repositoryImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prodata.spsv1.model.Cheque;

import presampling.repository.PreTaskRepository;


@Repository
public class PreTaskRepositoryImpl implements PreTaskRepository {

	@Autowired
	private JdbcTemplate JdbcTemplate;
	
	
	@Override
	public int assignTask(String employee, String assignee, int task, String type) {
		System.out.println("assignTask:type........ "+type);
		try {  
			System.out.println("task...."+task);
			int li = 0;
			if(type.equalsIgnoreCase("maker")) 
			{
				li=JdbcTemplate.update("update tbl_responseInput set assign_to= ?,allocate_date=convert(date,GETDATE(),12) where [Policy No.] in (select top "+task+" [Policy No.] from tbl_responseInput where assign_to= ? AND flag='Y'and Final_remark <>'Ok To Process')",assignee,employee);
			}
			else if(type.equalsIgnoreCase("checker")) 
			{
				li=JdbcTemplate.update("update tbl_responseInput set checkerAssign_to= ?,allocate_date=convert(date,GETDATE(),12) where [Policy No.] in (select top "+task+" [Policy No.] from tbl_responseInput where checkerAssign_to= ? AND checkerFlag='Y'and Final_remark <>'Ok To Process')",assignee,employee);
			}
			
			 //int li=JdbcTemplate.update("update tbl_responseInput set assign_to= ? where [Policy No.] in (select top "+task+" [Policy No.] from tbl_responseInput where assign_to= ? AND flag='Y'and Final_remark <>'Ok To Process')",assignee,employee);
			return li;
		} catch (Exception e)
	    {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	
	/*@Override
	public int assignTask(String employee, String assignee, int task) {
		HttpSession s=null;
		String type =(String) s.getAttribute("type");
		System.out.println("assignTask:type........ "+type);
		try {  
			System.out.println("task...."+task);
			int li = 0;
			if(type.equalsIgnoreCase("maker")) {
				li=JdbcTemplate.update("update tbl_responseInput set assign_to= ? where [Policy No.] in (select top "+task+" [Policy No.] from tbl_responseInput where assign_to= ? AND flag='Y'and Final_remark <>'Ok To Process')",assignee,employee);
			}
			else if(type.equalsIgnoreCase("checker")) {
				li=JdbcTemplate.update("update tbl_responseInput set checkerAssign_to= ? where [Policy No.] in (select top "+task+" [Policy No.] from tbl_responseInput where checkerAssign_to= ? AND flag='Y'and Final_remark <>'Ok To Process')",assignee,employee);
			}
			
			 //int li=JdbcTemplate.update("update tbl_responseInput set assign_to= ? where [Policy No.] in (select top "+task+" [Policy No.] from tbl_responseInput where assign_to= ? AND flag='Y'and Final_remark <>'Ok To Process')",assignee,employee);
			return li;
		} catch (Exception e)
	    {
			e.printStackTrace();
			return 0;
		}
		
		
		//return task;
		
		
		if(paymentmode==null || OneRsType==null){
			try {
				int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y')",assignee,employee);
				System.out.println("update zala");
				return li;
			} catch (Exception e)
		    {
				e.printStackTrace();
				return 0;
			}
		}
		try {
			int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,OneRsType);
			System.out.println("update zala");
			return li;
		} catch (Exception e) {
			e.printStackTrace();
			return 0; 
		}
		if(OneRsType!=null && paymentmode!=null){
			int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,OneRsType);
			System.out.println("update zala");
			return li;
		}
		else if(paymentmode!=null){
			int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=?)",assignee,employee,paymentmode);
			System.out.println("update zala");
			return li;
		}
		else if(OneRsType!=null){
			int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [FinalRemarkOners]=?)",assignee,employee,OneRsType);
			System.out.println("update zala");
			return li;
		}
		else {
			int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y')",assignee,employee);
			System.out.println("update zala");
			return li;
		}
	}*/

	@Override
	public int deleteupload() {
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		String d=date.toString();
		try {
			int i = JdbcTemplate.update("delete from tbl_samplingInput where Date = ? and assign_to = ? and flag = ?",d,"pritamd","Y");
			//System.out.println("deleteupload................. "+i);
			return i;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/*private static class assignRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cheque cheque = new Cheque();
			cheque.setCall_id(rs.getInt(1));
			cheque.setPolicyno(rs.getString(2));
			cheque.setClientId(rs.getString(3));
			cheque.setName(rs.getString(4));
			System.out.println("cheque is ...... "+cheque);
			return cheque;
		}
		
	}*/

}
