package com.prodata.spsv1.repository.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prodata.spsv1.repository.MetadataRepository;



@Repository
public class MetadataRepositoryImpl implements MetadataRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public Map<String, Object> prediscreDbToExcel(String sdate, String edate) {
		
		java.sql.Date d1 = java.sql.Date.valueOf(sdate);
		System.out.println("get f1 date"+sdate);
		java.sql.Date d2 = java.sql.Date.valueOf(edate);
		System.out.println("get f2 date"+edate);
		//return jdbcTemplate.query("SELECT [ContractNo],[Mode OF Payment],[FactoryHouse],[BankBranchCode],[IFSCCode],[AccountNo],[AccountType],[Name],[Amount],[Type],[DispatchBy],[BranchName],[Instructions],[GMID],[SAS code],[SAS type],[OwnerId],[OLD_PRN],[Discrepancy],[Cross Ref Policy],[Application No.],[Cross Ref Application],[date],[remarks],[remarkby],[referenceno] FROM tbl_mandate where date BETWEEN ? AND ?", new MetadataMandateResultSetExtractor(),d1,d2);
		//return jdbcTemplate.query("SELECT b.[SR. No.],b.[Policy No.],b.[Client ID],b.[PRN No.],b.[Payment Amt],b.[Payment Mode],b.[Payment Reason],b.[MOS],b.[Cross Ref Policy 1],b.[Cross Ref Policy 2],b.[Application No.],b.[Bank Code],b.[policy no.1],b.[Customer Name],b.[Pay Mode],b.[Discrepancy],b.[T/F Account No.],b.[T/F IFSC],b.[T/F MICR],b.[T/F SPAARC A/c],b.[T/F SPAARC IFSC],b.[0],b.[00],b.[000],b.[Error / Ok],b.[Sampler Name],b.[DATA TIME],b.[DESCR],b.[Vendor Name],a.[BANKACCKEY],a.[IFSC],a.[BANKKEY],a.[BANKDESC] from tbl_preSamplingInput as a inner join tbl_preSamplingOutput as b on a.REQNNO=b.[PRN No.] where [DATA TIME] BETWEEN ? AND ? and [Remarks]='Discrepancy'", new MetadatadiscrepancyResultSetExtractor(),d1,d2);
		return jdbcTemplate.query("SELECT b.[SR. No.],b.[Policy No.],b.[Client ID],b.[PRN No.],b.[Payment Amt],b.[Payment Mode],b.[Payment Reason],b.[MOS],b.[Cross Ref Policy 1],b.[Cross Ref Policy 2],b.[Application No.],b.[Bank Code],b.[policy no.1],b.[Customer Name],b.[Pay Mode],b.[Discrepancy],b.[T/F Account No.],b.[T/F IFSC],b.[T/F MICR],b.[T/F SPAARC A/c],b.[T/F SPAARC IFSC],b.[0],b.[00],b.[000],b.[Error / Ok],b.[Sampler Name],b.[DATA TIME],b.[DESCR],b.[Vendor Name],a.[CREDIT_ACCOUNT_NO],a.[IFSC_CODE] from tbl_preSamplingInput as a inner join tbl_preSamplingOutput as b on a.[Policy No.]=b.[Policy No.] where [DATA TIME] BETWEEN ? AND ? and b.[Remarks]='Discrepancy'", new prediscrepancyResultSetExtractor(),d1,d2);
		
	}
	@Override
	public Map<String, Object> mandateDbToExcel(String sdate, String edate) {

		java.sql.Date d1 = java.sql.Date.valueOf(sdate);
		System.out.println("get f1 date"+sdate);
		java.sql.Date d2 = java.sql.Date.valueOf(edate);
		System.out.println("get f2 date"+edate);
		//return jdbcTemplate.query("SELECT [ContractNo],[Mode OF Payment],[FactoryHouse],[BankBranchCode],[IFSCCode],[AccountNo],[AccountType],[Name],[Amount],[Type],[DispatchBy],[BranchName],[Instructions],[GMID],[SAS code],[SAS type],[OwnerId],[OLD_PRN],[Discrepancy],[Cross Ref Policy],[Application No.],[Cross Ref Application],[date],[remarks],[remarkby],[referenceno] FROM tbl_mandate where date BETWEEN ? AND ?", new MetadataMandateResultSetExtractor(),d1,d2);
		return jdbcTemplate.query("SELECT [SR. No.],[Policy No.],[Client ID],[PRN No.],[Payment Amt],[Payment Mode],[Payment Reason],[MOS],[Cross Ref Policy 1],[Cross Ref Policy 2],[Application No.],[Bank Name],[Bank Code],[policy no.1],[Customer Name],[Checker A/c No.],[IFSC Code],[MICR Code],[NM Code],[Pay Mode],[Discrepancy],[T/F Account No.],[T/F IFSC],[T/F MICR],[T/F SPAARC A/c],[T/F SPAARC IFSC],[0],[00],[000],[Error / Ok],[Sampler Name],[DATA TIME],[DESCR],[Vendor Name] FROM tbl_samplingOutput where [DATA TIME] BETWEEN ? AND ? and [Remarks]='Process'", new MetadataMandateResultSetExtractor(),d1,d2);
		
	}
	@Override
	public Map<String, Object> dbToExcel(String sdate, String edate) {

		java.sql.Date d1 = java.sql.Date.valueOf(sdate);
		java.sql.Date d2 = java.sql.Date.valueOf(edate);
		return jdbcTemplate.query("SELECT [SR. No.],[Policy No.],[Client ID],[PRN No.],[Payment Amt],[Payment Mode],[Payment Reason],[MOS],[Cross Ref Policy 1],[Cross Ref Policy 2],[Application No.],[Bank Name],[Bank Code],[policy no.1],[Customer Name],[Checker A/c No.],[IFSC Code],[MICR Code],[NM Code],[Pay Mode],[Discrepancy],[T/F Account No.],[T/F IFSC],[T/F MICR],[T/F SPAARC A/c],[T/F SPAARC IFSC],[0],[00],[000],[Error / Ok],[Sampler Name],[DATA TIME],[assign_to],[Remarks],[Reference],[DESCR],[Vendor Name],[CheckedName],[OneRsName],[reason],[FinalRemarkOners] FROM tbl_preSamplingOutput where [DATA TIME] BETWEEN ? AND ? and [Remarks]='Process'", new MetadataResultSetExtractor(), d1, d2);
		
	}
	@Override
	public Map<String, Object> discrepancyUpdateExcel(String sdate, String edate) {
		java.sql.Date d1 = java.sql.Date.valueOf(sdate);
		System.out.println("get f1 date"+sdate);
		java.sql.Date d2 = java.sql.Date.valueOf(edate);
		System.out.println("get f2 date"+edate);
		//return jdbcTemplate.query("SELECT [ContractNo],[Mode OF Payment],[FactoryHouse],[BankBranchCode],[IFSCCode],[AccountNo],[AccountType],[Name],[Amount],[Type],[DispatchBy],[BranchName],[Instructions],[GMID],[SAS code],[SAS type],[OwnerId],[OLD_PRN],[Discrepancy],[Cross Ref Policy],[Application No.],[Cross Ref Application],[date],[remarks],[remarkby],[referenceno] FROM tbl_mandate where date BETWEEN ? AND ?", new MetadataMandateResultSetExtractor(),d1,d2);
		return jdbcTemplate.query("SELECT b.[SR. No.],b.[Policy No.],b.[Client ID],b.[PRN No.],b.[Payment Amt],b.[Payment Mode],b.[Payment Reason],b.[MOS],b.[Cross Ref Policy 1],b.[Cross Ref Policy 2],b.[Application No.],b.[Bank Code],b.[policy no.1],b.[Customer Name],b.[Pay Mode],b.[Discrepancy],b.[T/F Account No.],b.[T/F IFSC],b.[T/F MICR],b.[T/F SPAARC A/c],b.[T/F SPAARC IFSC],b.[0],b.[00],b.[000],b.[Error / Ok],b.[Sampler Name],b.[DATA TIME],b.[DESCR],b.[Vendor Name],a.[BANKACCKEY],a.[IFSC],a.[BANKKEY],a.[BANKDESC] from tbl_samplingInput as a inner join tbl_samplingOutput as b on a.REQNNO=b.[PRN No.] where [DATA TIME] BETWEEN ? AND ? and [Remarks]='Discrepancy'", new MetadatadiscrepancyResultSetExtractor(),d1,d2);
		
	}
		
	
		     
		
}

