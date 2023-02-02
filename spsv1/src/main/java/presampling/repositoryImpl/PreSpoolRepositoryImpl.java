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

import presampling.repository.PreSpoolRepository;



@Repository
public class PreSpoolRepositoryImpl implements PreSpoolRepository{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Cheque findById(String policy,User u) {
	System.out.println("ChequeRepositoryImpl:findById "+policy);
		return jdbcTemplate.queryForObject("SELECT top (1) * FROM tbl_preSamplingInput WHERE [Policy No.] = ? and assign_to=? and flag='Y'", new ChequeRowMapper(), policy,u.getUsername());
	}
	
	private static class  ChequeRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Cheque cheque = new Cheque();
			//cheque.setvPolicyNo(rs.getString(7));
			cheque.setvAccountNumber(rs.getString(15));
			cheque.setMifscCode(rs.getString(12));
		/*	cheque.setMmicrCode(rs.getString(70));
			cheque.setDescr(rs.getString(91));
			cheque.setVendorName(rs.getString(94));*/
			
			
			System.out.println("SpoolRepositoryImpl:findById"+cheque);
			return cheque;
			
		}
	}	
	
}
