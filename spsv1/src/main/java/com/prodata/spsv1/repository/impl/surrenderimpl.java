package com.prodata.spsv1.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.prodata.spsv1.repository.Surrenderpi;

@Service
public class surrenderimpl implements Surrenderpi {

	@Autowired
	DataSource dataSource;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public boolean uplremark(String string) {
		try {
			Connection con = dataSource.getConnection();
			CallableStatement cs = con.prepareCall("{ call [dbo].[Remarksupl](?) }");
			cs.setString(1, string);
			cs.execute();
			cs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public boolean uplelectronictot(String string) {
		try {
			Connection con = dataSource.getConnection();
			CallableStatement cs = con.prepareCall("{ call [dbo].[TOT_upl](?) }");
			cs.setString(1, string);
			cs.execute();
			cs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public boolean uplchequetot(String string) {
		try {
			Connection con = dataSource.getConnection();
			CallableStatement cs = con.prepareCall("{ call [dbo].[Chequeupl](?) }");
			cs.setString(1, string);
			cs.execute();
			cs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public boolean uplsrdata(String string) {
		try {
			Connection con = dataSource.getConnection();
			CallableStatement cs = con.prepareCall("{ call [dbo].[SRData](?) }");
			cs.setString(1, string);
			cs.execute();
			cs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public boolean uplpenal(String string) {
		try {
			Connection con = dataSource.getConnection();
			CallableStatement cs = con.prepareCall("{ call [dbo].[PIInterest](?) }");
			cs.setString(1, string);
			cs.execute();
			cs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public boolean upltrigger(String string) {
		try {
			Connection con = dataSource.getConnection();
			CallableStatement cs = con.prepareCall("{ call [dbo].[PIInterest](?) }");
			cs.setString(1, string);
			cs.execute();
			cs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

	@Override
	public boolean execute(String string) {

		int i = 0;
		System.out.println("inn surrender cha query madhe");
		jdbcTemplate.update(
				"update  [spsv1].[dbo].[Cheque_TOTup] set Dispatch_Date = FORMAT(cast(Dispatch_Date as date),'dd-MMM-yyyy')");
		jdbcTemplate.update("update  [spsv1].[dbo].[Cheque_TOTup] set Date = FORMAT(cast(Date as date),'dd-MMM-yyyy')");
		jdbcTemplate.update(
				"update  [spsv1].[dbo].[Cheque_TOTup] set PAYDATE = FORMAT(cast(PAYDATE as date),'dd-MMM-yyyy')");
		jdbcTemplate.update(
				"update  [spsv1].[dbo].[Cheque_TOTup] set Auth_Date = FORMAT(cast(Auth_Date as date),'dd-MMM-yyyy')");
		jdbcTemplate.update(
				"update  [spsv1].[dbo].[Cheque_TOTup] set Printing_Date = FORMAT(cast(Printing_Date as date),'dd-MMM-yyyy')");
		jdbcTemplate.update(
				"update  [spsv1].[dbo].[Cheque_TOTup] set Pickup_Date = FORMAT(cast(Pickup_Date as date),'dd-MMM-yyyy')");
		jdbcTemplate.update("update  [spsv1].[dbo].[TOT_upload] set Date = FORMAT(cast(Date as date),'dd-MMM-yyyy')");
		jdbcTemplate.update(
				"update  [spsv1].[dbo].[TOT_upload] set Pay_Date = FORMAT(cast(Pay_Date as date),'dd-MMM-yyyy')");
		jdbcTemplate.update(
				"update  [spsv1].[dbo].[TOT_upload] set Auth_Date = FORMAT(cast(Auth_Date as date),'dd-MMM-yyyy')");
		jdbcTemplate.update("delete from [spsv1].[dbo].[TOT_upload] where Disbursal_Date='PRN REVERSED'");
		jdbcTemplate.update(" delete from [spsv1].[dbo].[TOT_upload] where Disbursal_Date='PENDING'");
		jdbcTemplate.update("delete from [spsv1].[dbo].[TOT_upload] where Disbursal_Date='HOLD'");

		jdbcTemplate.update(
				"update  [spsv1].[dbo].[TOT_upload] set Disbursal_Date = FORMAT(cast(Disbursal_Date as date),'dd-MMM-yyyy')");

		jdbcTemplate.update(
				"update  [spsv1].[dbo].[triggerupl] set Transaction_Date = FORMAT(cast(Transaction_Date as date),'dd-MMM-yyyy')");
		jdbcTemplate.update(
				"update  [spsv1].[dbo].[PenalInterest] set Recvd_D = FORMAT(cast(Recvd_D as date),'dd-MMM-yyyy')");
		jdbcTemplate.update(
				"update  [spsv1].[dbo].[PenalInterest] set Closeddate = FORMAT(cast(Closeddate as date),'dd-MMM-yyyy')");

		jdbcTemplate.update(
				"update a set a.Transaction_Date=b.Transaction_Date from [spsv1].[dbo].[triggerupl] b , [spsv1].[dbo].[PenalInterest]  a where a.Policyno=b.Chdrnum");

		jdbcTemplate.update(
				"update  [spsv1].[dbo].[PenalInterest] set Transaction_Date = FORMAT(cast(Transaction_Date as date),'dd-MMM-yyyy')");
		jdbcTemplate.update(
				"UPDATE [spsv1].[dbo].[TOT_upload] SET flag='Y' where Payment_Reason in ('SU','RP_SU','PW','RP_PW')");
		jdbcTemplate.update("DELETE [spsv1].[dbo].[TOT_upload] WHERE FLAG IS NULL");
		jdbcTemplate.update("UPDATE [spsv1].[dbo].[TOT_upload] SET flag=NULL where flag='Y'");
		jdbcTemplate.update("DELETE [spsv1].[dbo].[TOT_upload] WHERE Remarks='PRN REVERSED'");
		jdbcTemplate.update(
				"update a set a.PRN=b.PRN,a.Amount=b.Gross_Amount,a.Payment_date=b.Pay_Date,a.Auth_date=b.Auth_Date,a.Disbursal_Date=b.Disbursal_Date  from [spsv1].[dbo].[TOT_upload] b , [spsv1].[dbo].[PenalInterest]  a where a.Policyno=b.Contract_No");
		jdbcTemplate.update(
				"update [spsv1].[dbo].[Cheque_TOTup] set flag='Y' where Payment_Reason in ('SU','RP_SU','PW','RP_PW')");
		jdbcTemplate.update("delete [spsv1].[dbo].[Cheque_TOTup] where flag is null");
		jdbcTemplate.update("delete [spsv1].[dbo].[Cheque_TOTup] where Final_Decision like '%Reversed%'");
		jdbcTemplate.update(
				"update a set a.PRN=b.PRN_No,a.Amount=b.Payment_Amount,a.Payment_date=b.PAYDATE,a.Auth_date=b.Auth_Date,a.Disbursal_Date=b.Dispatch_Date  from [spsv1].[dbo].[Cheque_TOTup] b , [spsv1].[dbo].[PenalInterest]  a where a.Policyno=b.Contract_No");
		jdbcTemplate.update("update [spsv1].[dbo].[PenalInterest] set Status='DISBURSED' where PRN is not null");
		jdbcTemplate.update(
				"update a set a.Status='TOF PROCESSED',a.Final_Remark='TOF PROCESSED CASE'  from  [spsv1].[dbo].[SR] b , [spsv1].[dbo].[PenalInterest]  a where a.Policyno=b.PolicyNo and b.CallType='Transfer of Funds'");
		jdbcTemplate.update(
				"update [spsv1].[dbo].[PenalInterest] set Status='TOF PROCESSED',Final_Remark='TOF PROCESSED CASE' where Closedesc like '%TOF%' and Status is null");
		jdbcTemplate.update(
				"update [spsv1].[dbo].[PenalInterest] set Status='TOF PROCESSED',Final_Remark='TOF PROCESSED CASE' where Closedesc='TOF DONE' and Status is null");
		jdbcTemplate.update(
				" update a set a.Status='SU REVERSAL',a.Final_Remark='SU REVERSAL CASE'  from  [spsv1].[dbo].[SR] b , [spsv1].[dbo].[PenalInterest]  a where b.SubType='Surrender Reinstatement' and b.SubType='SU/PW Reversal' ");
		jdbcTemplate.update(
				"update [spsv1].[dbo].[PenalInterest] set Status='SU REVERSAL',Final_Remark='SU REVERSAL CASE' where Closedesc like '%Surrender Reinstatement%' and  Status is null");
		jdbcTemplate.update(
				"update [spsv1].[dbo].[PenalInterest] set Status='SU REVERSAL',Final_Remark='SU REVERSAL CASE' where Closedesc like '%Surrender Rein%' and  Status is null");
		jdbcTemplate.update(
				"update [spsv1].[dbo].[PenalInterest] set Status='SU REVERSAL',Final_Remark='SU REVERSAL CASE' where Closedesc ='surrender reinstatement' and  Status is null ");
		jdbcTemplate.update(
				"update [spsv1].[dbo].[PenalInterest] set Status='SU REVERSAL',Final_Remark='SU REVERSAL CASE' where Closedesc like '%Reversal%' and Status is null");
		jdbcTemplate.update(
				"update a set a.Vendor=b.VENDOR  from [spsv1].[dbo].[vendor_data] b , [spsv1].[dbo].[PenalInterest]  a where a.Callid=b.CALL_ID");
		jdbcTemplate.update(
				"update [spsv1].[dbo].[PenalInterest] set Status='ANNUITY CASE',Final_Remark='ANNUITY CASE'  where Closedesc like '%annuity%' and Status is null");
		jdbcTemplate.update(
				"update [spsv1].[dbo].[PenalInterest] set Status='MATURITY CASE',Final_Remark='MATURITY CASE'  where Closedesc like '%maturity%' or Closedesc like '%Matured%' and Status is null");
		jdbcTemplate.update(
				"update [spsv1].[dbo].[PenalInterest] set Status='CALL CLOSED BY CSU',Final_Remark='CALL CLOSED BY CSU'  where Closuretype='Requirements Trigger' and Status is null");
		jdbcTemplate
				.update("update [spsv1].[dbo].[PenalInterest] set Ageing_1=DATEDIFF(day,Transaction_Date,Auth_date)");
		jdbcTemplate.update("update [spsv1].[dbo].[PenalInterest] set Ageing_2=DATEDIFF(day,Recvd_D,Disbursal_Date)");
		jdbcTemplate.update(
				"update [spsv1].[dbo].[PenalInterest] set Ageing_3=DATEDIFF(day,Assignment_Closure_Date,Disbursal_Date)");
		jdbcTemplate.update(
				"update [spsv1].[dbo].[PenalInterest] set Final_Remark='WITHIN TAT' where Ageing_2 in ('0','1','2','3','4','5','6','7','8','9','10','11','12','13','14','15') and Status='DISBURSED'");
		jdbcTemplate.update(
				"update [spsv1].[dbo].[PenalInterest] set Final_Remark='BEYOND TAT' where Ageing_2 is not null and  Status='DISBURSED' and Final_Remark is null");

		System.out.println("out");

		return false;
	}

	@Override
	public boolean exe(String string) {
		try {
			Connection con = dataSource.getConnection();
			/// CallableStatement cs = con.prepareCall("{ call [dbo].[CNC_Upload](?,?) }");

			CallableStatement cs = con.prepareCall("{ call [dbo].[surrenderexport] (?) }");
			cs.setString(1, string);
			cs.execute();
			cs.close();
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;

		}
		return true;
	}

	@Override
	public boolean uplvendor(String string) {
		try {
			Connection con = dataSource.getConnection();
			CallableStatement cs = con.prepareCall("{ call [dbo].[Cordys_Vendor](?) }");
			cs.setString(1, string);
			cs.execute();
			cs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

}
