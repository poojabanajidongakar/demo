package presampling.repositoryImpl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.prodata.spsv1.model.Cheque;

public class PreMetadataResultSetExtractor implements ResultSetExtractor<Map<String, Object>> {

	@Override
	public Map<String, Object> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		try 
		{
			ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
			int columncount=resultSetMetaData.getColumnCount();
			List<String> li=new ArrayList<>();

			for(int i=1;i<=columncount;i++)
			{
				li.add(resultSetMetaData.getColumnLabel(i));
			}
			map.put("columnNames", li);

			List<Cheque> all=new ArrayList<>();

			while(resultSet.next())
			{
				Cheque cheque=new Cheque();
				
				cheque.setProcessingDate(resultSet.getString(1));//processing date
				cheque.setIfscCode(resultSet.getString(2));//ifsc
				cheque.setAccountNo(resultSet.getString(3));//account
				cheque.setCurrentStatus(resultSet.getString(4));
				cheque.setRemarks(resultSet.getString(5)); //remarks
				cheque.setvPolicyNo(resultSet.getString(6)); //policy no
				cheque.setName(resultSet.getString(7)); //benename
				cheque.setSapPolicy(resultSet.getString(8)); //sap policy
				cheque.setSapName(resultSet.getString(9)); //sap customer name
				cheque.setFinalRemarkOners(resultSet.getString(10));
				cheque.setCheckName(resultSet.getString(11)); // checked name
				cheque.setReason(resultSet.getString(12));
				cheque.setDate(resultSet.getString(13));
				
				cheque.setMakerBy(resultSet.getString(14));
				cheque.setCheckerBy(resultSet.getString(15));
				cheque.setCheckerRemark(resultSet.getString(16));
				cheque.setCheckerReason(resultSet.getString(17));
				cheque.setFinalRemark(resultSet.getString(18));
				cheque.setFinalReason(resultSet.getString(19));
				/*cheque.setProcessingDate(resultSet.getString(8));//processing date
				cheque.setIfscCode(resultSet.getString(12));//ifsc
				cheque.setAccountNo(resultSet.getString(15));//account
				cheque.setCurrentStatus(resultSet.getString(18));
				cheque.setRemarks(resultSet.getString(21)); //remarks
				cheque.setvPolicyNo(resultSet.getString(22)); //policy no
				cheque.setName(resultSet.getString(24)); //benename
				cheque.setSapPolicy(resultSet.getString(29)); //sap policy
				cheque.setSapName(resultSet.getString(30)); //sap customer name
				cheque.setFinalRemarkOners(resultSet.getString(35));
				cheque.setCheckName(resultSet.getString(36)); // checked name
				cheque.setReason(resultSet.getString(37));
				cheque.setDate(resultSet.getString(26));*/
				
				all.add(cheque);				
			}

			map.put("data", all);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
