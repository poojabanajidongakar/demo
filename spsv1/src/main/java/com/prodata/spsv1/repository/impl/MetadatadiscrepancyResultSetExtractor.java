package com.prodata.spsv1.repository.impl;

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

public class MetadatadiscrepancyResultSetExtractor  implements ResultSetExtractor<Map<String, Object>>  {

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
				
				

/*SELECT b.[SR. No.],b.[Policy No.],b.[Client ID],b.[PRN No.],
b.[Payment Amt],b.[Payment Mode],b.[Payment Reason],b.[MOS],b.[Cross Ref Policy 1],b.[Cross Ref Policy 2],b.[Application No.],
b.[Bank Code],b.[policy no.1],b.[Customer Name],b.[Pay Mode],b.[Discrepancy],b.[T/F Account No.],
b.[T/F IFSC],b.[T/F MICR],b.[T/F SPAARC A/c],b.[T/F SPAARC IFSC],b.[0],b.[00],b.[000],b.[Error / Ok],
b.[Sampler Name],b.[DATA TIME],b.[DESCR],b.[Vendor Name],a.[BANKACCKEY],a.[IFSC],a.[BANKKEY],a.[BANKDESC]*/
				
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
				//
				cheque.setBankCode(resultSet.getString(12));
				cheque.setPolicyno(resultSet.getString(13));
				cheque.setName(resultSet.getString(14));
				cheque.setPaymentMode(resultSet.getString(15));
				//discrepancy
				cheque.setTfaccountno(resultSet.getString(16));
				cheque.setTfifsccode(resultSet.getString(19));
				cheque.setTfmicrcode(resultSet.getString(18));	
			      //[T/F SPAARC A/c]
			    	      //[T/F SPAARC IFSC]
			    	      //[0]
			    	      //[00]
			    	      //[000]
			    cheque.setDiscrepancyTag(resultSet.getString(25));
				cheque.setRemarksBy(resultSet.getString(26));
				cheque.setDate(resultSet.getString(21));
				cheque.setDescr(resultSet.getString(28));
				cheque.setVendorName(resultSet.getString(29));
				cheque.setvAccountNumber(resultSet.getString(30));
				cheque.setMifscCode(resultSet.getString(31));
				cheque.setMnmCode(resultSet.getString(32));
				cheque.setBankName(resultSet.getString(33));
				
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
