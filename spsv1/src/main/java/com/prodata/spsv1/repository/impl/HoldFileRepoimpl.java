package com.prodata.spsv1.repository.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.prodata.spsv1.model.Glenysfilemodel;
import com.prodata.spsv1.model.Holdfilemodel;
import com.prodata.spsv1.repository.HoldfileRepo;

@Repository
public class HoldFileRepoimpl implements HoldfileRepo {
	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean uplHoldFile(String string) {
		try {
			Connection con = dataSource.getConnection();
			CallableStatement cs = con.prepareCall("{ call [dbo].[spsv1_HOLD_DATA](?) }");
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
	public boolean uplwrongcalllog(String string) {
		try {
			Connection con = dataSource.getConnection();
			CallableStatement cs = con.prepareCall("{ call [dbo].[spsv1_Wrong_call_log_data](?) }");
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
	public boolean uplversion2(String string) {
		try {
			Connection con = dataSource.getConnection();
			CallableStatement cs = con.prepareCall("{ call [dbo].[spsv1_Emp_List_Version2](?) }");
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
	public boolean uplexpress_data(String string) {
		try {
			Connection con = dataSource.getConnection();
			CallableStatement cs = con.prepareCall("{ call [dbo].[spsv1_Express Data](?) }");
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
	public boolean upldor_data(String string) {
		try {
			Connection con = dataSource.getConnection();
			CallableStatement cs = con.prepareCall("{ call [dbo].[spsv1_DOR_DATA](?) }");
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
	public boolean upllogCTSTFormat(String string) {
		try {
			Connection con = dataSource.getConnection();
			CallableStatement cs = con.prepareCall("{ call [dbo].[spsv1_Call log CTST Format](?) }");
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
	public boolean uplGlenysquery(String string) {
		try {
			Connection con = dataSource.getConnection();
			CallableStatement cs = con.prepareCall("{ call [dbo].[spsv1_Glenys query](?) }");
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
	public boolean uplOwnership_and_department(String string) {
		try {
			Connection con = dataSource.getConnection();
			CallableStatement cs = con.prepareCall("{ call [dbo].[spsv1_Ownership_and_department](?) }");
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
	public boolean uplOwnership_department(String string) {
		try {
			Connection con = dataSource.getConnection();
			CallableStatement cs = con.prepareCall("{ call [dbo].[spsv1_Ownership_department](?) }");
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
	// =============================Export File=========================

	@Override
	public List<Holdfilemodel> exportholdfile() {

		final String query = "select [CALL_ID],[HOLD_DATE],[Till_Date],[Ageing],[CONTRACT_NO],[SOURCE],[CALLTYPE],[SUBTYPE],[RECVDBY],[CALLDESC],[Ownership],[Department] from [spsv1].[dbo].[HOLD_DATA] where [Approval] is NULL order by id asc";

		return jdbcTemplate.query(query, new ResultSetExtractor<List<Holdfilemodel>>() {

			@Override
			public List<Holdfilemodel> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Holdfilemodel> studentList = new ArrayList<Holdfilemodel>();
				while (rs.next()) {
					Holdfilemodel student = new Holdfilemodel(rs.getString(1), rs.getString(2), rs.getString(3),
							rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
							rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12));
					studentList.add(student);
				}
				return studentList;
			}
		});

	}

	@Override
	public List<Glenysfilemodel> exportGlenysfile() {

		final String query = "select [CALL_ID],[HOLD_DATE],[Till_Date],[Ageing],[CONTRACT_NO],[SOURCE],[CALLTYPE],[SUBTYPE],[RECVDBY],[CALLDESC],[Ownership],[Department],[Approval] from [spsv1].[dbo].[HOLD_DATA] where [SOURCE]='GLENYS'";

		return jdbcTemplate.query(query, new ResultSetExtractor<List<Glenysfilemodel>>() {

			@Override
			public List<Glenysfilemodel> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Glenysfilemodel> studentList = new ArrayList<Glenysfilemodel>();
				while (rs.next()) {
					Glenysfilemodel student = new Glenysfilemodel(rs.getString(1), rs.getString(2), rs.getString(3),
							rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8),
							rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13));
					studentList.add(student);
				}
				return studentList;
			}
		});

	}

	@Override
	public boolean exequery(String string) {

		System.out.println("in");
		jdbcTemplate.update(
				"update [spsv1].[dbo].[HOLD_DATA]  set [CONTRACT_NO]= (RIGHT('00000000'+CAST(ISNULL([CONTRACT_NO],0) AS VARCHAR),8))");
		jdbcTemplate.update(
				"update a set a.[status] = b.[status]  from [spsv1].[dbo].[HOLD_DATA] a,[spsv1].[dbo].[Call_log_CTST_Format] b where a.[SUBTYPE]=b.[Subtype] and [SOURCE]='CALL_LOG'");
		jdbcTemplate.update(
				"delete from [spsv1].[dbo].[HOLD_DATA]  where status = 'not on hold' or status is NULL  and [SOURCE]='CALL_LOG'");
		jdbcTemplate.update(
				"update a set a.[Ownership] = b.[New_Owner]  from [spsv1].[dbo].[HOLD_DATA] a,[spsv1].[dbo].[Emp_List_Hold_call_Version2] b where a.RECVDBY=b.[Recvdby] and [SOURCE]='CALL_LOG'");
		jdbcTemplate.update(
				"update a set a.[Department] = b.[New_Dept]  from [spsv1].[dbo].[HOLD_DATA] a,[spsv1].[dbo].[Emp_List_Hold_call_Version2] b where a.RECVDBY=b.[Recvdby] and [SOURCE]='CALL_LOG'");
		jdbcTemplate.update(
				"update a set a.[Ownership] = b.[Ownership]  from [spsv1].[dbo].[HOLD_DATA] a,[spsv1].[dbo].[Ownership_and_department] b where a.SUBTYPE=b.[Subtype] and [SOURCE]='CALL_LOG'");
		jdbcTemplate.update(
				"update a set a.[Department] = b.[Department]  from [spsv1].[dbo].[HOLD_DATA] a,[spsv1].[dbo].[Ownership_and_department] b where a.SUBTYPE=b.[Subtype] and [SOURCE]='CALL_LOG'");
		jdbcTemplate.update(
				"update [spsv1].[dbo].[HOLD_DATA] set [SOURCE] = REPLACE([SOURCE], '_', '-') where [SOURCE]='CALL_LOG'");
		jdbcTemplate.update(
				"update [spsv1].[dbo].[HOLD_DATA] set [SOURCE] = REPLACE([SOURCE],'DE_RISK','DIRSIK') where [SOURCE]='DE_RISK'");
		jdbcTemplate.update(
				"update a set a.[Ownership] = b.[Ownership]  from [spsv1].[dbo].[HOLD_DATA] a,[spsv1].[dbo].[Ownership_department] b where a.[SOURCE]=b.[Reason]");
		jdbcTemplate.update(
				"update a set a.[Department] = b.[Department]  from [spsv1].[dbo].[HOLD_DATA] a,[spsv1].[dbo].[Ownership_department] b where a.[SOURCE]=b.[Reason]");
		jdbcTemplate.update(
				"update a set a.[status] = b.[status]  from [spsv1].[dbo].[HOLD_DATA] a,[spsv1].[dbo].[Express_Data] b where a.[CONTRACT_NO]=b.[CHDRNUM] and [SOURCE]='SPL_RET'");
		jdbcTemplate.update("delete from [spsv1].[dbo].[HOLD_DATA] where status='release'");
		jdbcTemplate.update(
				"update a set a.[Approval] = b.[Approval]  from [spsv1].[dbo].[HOLD_DATA] a,[spsv1].[dbo].[Glenys_query] b where a.CONTRACT_NO=b.Policy_No");
		jdbcTemplate.update(
				"update a set a.CALLDESC = b.Call_Des  from [spsv1].[dbo].[HOLD_DATA] a,[spsv1].[dbo].[Wrong_call_log_data] b where a.CALL_ID=b.Call_ID");
		jdbcTemplate.update("update HOLD_DATA set Till_Date=GETDATE()");
		jdbcTemplate
				.update("update [spsv1].[dbo].[HOLD_DATA] set Till_Date=FORMAT(cast(Till_Date as date),'dd-MMM-yy')");
		jdbcTemplate.update("update [spsv1].[dbo].[HOLD_DATA] set Ageing=DATEDIFF(day,HOLD_DATE,Till_Date)");
		jdbcTemplate.update(
				"insert into [spsv1].[dbo].[HOLD_DATA]([CONTRACT_NO],[SOURCE])select [CHDRNUM],[status] from [spsv1].[dbo].[DOR_DATA]");
		jdbcTemplate.update(
				"update a set a.Ownership = b.Ownership  from [spsv1].[dbo].[HOLD_DATA] a,[spsv1].[dbo].[Ownership_department] b where a.SOURCE=b.Reason and SOURCE='DOR'");
		jdbcTemplate.update(
				"update a set a.Department = b.Department  from [spsv1].[dbo].[HOLD_DATA] a,[spsv1].[dbo].[Ownership_department] b where a.SOURCE=b.Reason and SOURCE='DOR'");
		jdbcTemplate.update("update [spsv1].[dbo].[HOLD_DATA] set id = '1' where SOURCE != 'DOR'");
		jdbcTemplate.update("update [spsv1].[dbo].[HOLD_DATA] set id = '2' where SOURCE = 'DOR'");
		jdbcTemplate.update(
				"update [spsv1].[dbo].[HOLD_DATA]  set [CONTRACT_NO]= (RIGHT('00000000'+CAST(ISNULL([CONTRACT_NO],0) AS VARCHAR),8))");

		System.out.println("out");
		return false;

	}

}
