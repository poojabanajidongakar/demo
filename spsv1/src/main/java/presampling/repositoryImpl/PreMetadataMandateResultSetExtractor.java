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

public class PreMetadataMandateResultSetExtractor  implements ResultSetExtractor<Map<String, Object>>  {

	@Override
	public Map<String, Object> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		
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
				cheque.setSrNo(resultSet.getString(1));
				cheque.setPolicyno(resultSet.getString(2));
				cheque.setClientId(resultSet.getString(3));
				cheque.setPrnNo(resultSet.getString(4));
				cheque.setAmount(resultSet.getFloat(5));
				cheque.setPaymentMode(resultSet.getString(6));
				cheque.setPaymentType(resultSet.getString(7));
				cheque.setMos(resultSet.getString(8));
				cheque.setCrossRefPolicy(resultSet.getString(9));
				cheque.setCrossrefPolicy2(resultSet.getString(10));
				cheque.setApplicationNo(resultSet.getString(11));
				//cheque.setBranchName(resultSet.getString(12));
				cheque.setBankName(resultSet.getString(12));
				cheque.setBankCode(resultSet.getString(13));
				cheque.setPolicyno(resultSet.getString(14));
				cheque.setName(resultSet.getString(15));
				cheque.setvAccountNumber(resultSet.getString(16));
				cheque.setMifscCode(resultSet.getString(17));
				cheque.setMmicrCode(resultSet.getString(18));
				cheque.setMnmCode(resultSet.getString(19));
				cheque.setPaymentMode(resultSet.getString(20));
				//discrepancy
				cheque.setTfaccountno(resultSet.getString(22));
				cheque.setTfifsccode(resultSet.getString(23));
				cheque.setTfmicrcode(resultSet.getString(24));	
			      //[T/F SPAARC A/c]
			    	      //[T/F SPAARC IFSC]
			    	      //[0]
			    	      //[00]
			    	      //[000]
			    cheque.setDiscrepancyTag(resultSet.getString(30));
				cheque.setRemarksBy(resultSet.getString(31));
				cheque.setDate(resultSet.getString(32));
				cheque.setDescr(resultSet.getString(33));
				cheque.setVendorName(resultSet.getString(34));
				cheque.setCheckName(resultSet.getString(35));
				cheque.setOneRsName(resultSet.getString(36));
				cheque.setReason(resultSet.getString(37));
				cheque.setFinalRemarkOners(resultSet.getString(38));
				
				//cheque.setRemarks(resultSet.getString(29));
				//cheque.setReferenceno(resultSet.getString(31));
				
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
