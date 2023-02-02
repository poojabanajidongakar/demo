package com.prodata.spsv1.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prodata.spsv1.model.Cheque;
import com.prodata.spsv1.model.ProductivityReport;
import com.prodata.spsv1.repository.ReportRepository;




@Repository
public class ReportRepositoryImpl implements ReportRepository{
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public List<ProductivityReport> getReport(String sdate, String edate,String employee)
	{
		
		System.out.println("getReport"+employee);
		return jdbcTemplate.query("select distinct (select count(*) as count from tbl_samplingInput where date BETWEEN ? AND ? and assign_to=?) as allocatetssk, (select count(*) as count from tbl_samplingInput where date BETWEEN ? AND ? and assign_to=? and flag='Y') as pendingtask, (select distinct assign_to from tbl_samplingInput where date BETWEEN ? AND ? and assign_to=?) as username,(select count(*) as count from tbl_samplingInput where date BETWEEN ? AND ? and assign_to=? and flag='N') as complete from tbl_samplingInput",new reportRowMapper(), sdate,edate,employee,sdate,edate,employee,sdate,edate,employee,sdate,edate,employee);
		
	}
	private static class reportRowMapper implements RowMapper<ProductivityReport>
	{
		@Override
		public ProductivityReport mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProductivityReport pd = new ProductivityReport();
			pd.setAllocate(rs.getString("allocatetssk"));
			pd.setPending(rs.getString("pendingtask"));
			pd.setUsername(rs.getString("username"));
			pd.setComplete(rs.getString("complete"));
			System.out.println("productivity report:get report"+pd);
			return pd;
		}
	}
	
	@Override
	public List<Cheque> getPolicyRelatedUser(String policyValue) {
		System.out.println("getPolicyRelatedUser:policyValue.. "+policyValue);
		return jdbcTemplate.query("select [Policy No.],[DATA TIME],[Remarks],[Sampler Name],[PRN No.] from tbl_samplingOutput where [Policy No.] = ?",new searchRowMapper(),policyValue);
	}
	private static class searchRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cheque cheque = new Cheque();
			cheque.setPolicyno(rs.getString(1));
			cheque.setDate(rs.getString(2));
			cheque.setRemarks(rs.getString(3));
			cheque.setRemarksBy(rs.getString(4));
			cheque.setPrnNo(rs.getString(5));
			
			System.out.println("getPolicyRelatedUser:cheque "+cheque);
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
	
	
	
	@Override
	public List<Cheque> getCancelRemarks() {
		List<Cheque> logicdata = jdbcTemplate.query("select * from tbl_CancelRemark order by id", new LogicDataRowMapper());
		return logicdata;
	}
	
	private static class LogicDataRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int arg1) throws SQLException {
			Cheque edidata = new Cheque();
			edidata.setSubtype(rs.getString(1));
			edidata.setRemarks(rs.getString(2));
			edidata.setId(rs.getString(3));
			return edidata;
		}
	}
	
	
	@Override
	public int insertCancelRemarkData(String subtype, String remark) {
		System.out.println("insertLogicData:subtype.. "+subtype + "remark.. "+remark);
		try {
			jdbcTemplate.update("insert into tbl_CancelRemark([Subtype],[Remarks]) values(?,?) ",subtype,remark);
			return 1;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	@Override
	public int updateCancelRemarkData(String subtype, String remark, String id1) {
		//System.out.println("updateLogicData:calltype.. "+calltype + "subtype.. "+subtype + "owner.. "+owner + "processOwner.. "+processOwner + "id1.. "+id1);
		try {
			jdbcTemplate.update("update a set a.[Subtype] = ?, a.[Remarks] = ? from tbl_CancelRemark a where a.[id] = ?",
					subtype,
					remark,
					id1);
			return 1;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	
	@Override
	public int deleteCancelRemarkData(String id1) {
		//System.out.println("deleteFinalRemarkData:id1... "+id1);
		jdbcTemplate.update("delete from tbl_CancelRemark where [id] = ?",id1);
		return 0;
	}
	
	

}
