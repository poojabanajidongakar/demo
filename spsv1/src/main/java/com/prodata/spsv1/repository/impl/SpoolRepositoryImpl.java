package com.prodata.spsv1.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.prodata.spsv1.model.Cheque;
import com.prodata.spsv1.model.User;
import com.prodata.spsv1.repository.SpoolRepository;


@Repository
public class SpoolRepositoryImpl implements SpoolRepository{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Cheque findById(String prnno,User u) {
	System.out.println("ChequeRepositoryImpl:findById "+prnno);
		return jdbcTemplate.queryForObject("SELECT top (1) * FROM tbl_samplingInput WHERE [REQNNO] = ? and assign_to=? and flag='Y'", new ChequeRowMapper(), prnno,u.getUsername());
	}
	
	private static class  ChequeRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Cheque cheque = new Cheque();
			//cheque.setvPolicyNo(rs.getString(7));
			cheque.setvAccountNumber(rs.getString(68));
			cheque.setMifscCode(rs.getString(69));
			cheque.setMmicrCode(rs.getString(70));
			cheque.setDescr(rs.getString(91));
			cheque.setVendorName(rs.getString(94));
			
			
			System.out.println("SpoolRepositoryImpl:findById"+cheque);
			return cheque;
			
		}
	}

	@Override
	public List<Cheque> sortAmt(String paymode, String amt, User u) {
	
		System.out.println("payment mode is "+paymode);
		float amount = 500000;
		float amo=200000;
		float amt1=200000;
		try
		{
			if(paymode.equals("Direct Credit"))
			{
				System.out.println("mode of payment "+paymode);
				if(amt.equals("gfive"))
				{
					System.out.println("user selected range "+amt);
					
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where PAYAMT >=? and REQNTYPE='Direct Credit' and assign_to=? and flag='Y'", new sortRowMapper(),amount,u.getUsername());
					return li;
					//return jdbcTemplate.queryForObject("select REQNNO,PAYAMT from tbl_input where PAYAMT >= ?",new sortRowMapper(),amount);
				}
				else if(amt.equals("betofi")){
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE ,PAYAMT from tbl_samplingInput where PAYAMT >=? and PAYAMT < ? and REQNTYPE='Direct Credit'and assign_to=? and flag='Y'", new sortRowMapper(),amo,amount,u.getUsername());
					return li;
					//return jdbcTemplate.queryForObject("select REQNNO,PAYAMT from tbl_input where PAYAMT between 400000 and 500000",new sortRowMapper(),amt);
				}
				else if(amt.equals("lessTwo")){
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where  PAYAMT < ? and REQNTYPE='Direct Credit' and assign_to=? and flag='Y'", new sortRowMapper(),amt1,u.getUsername());
					return li;
					//return jdbcTemplate.queryForObject("select REQNNO,PAYAMT from tbl_input where PAYAMT between 400000 and 500000",new sortRowMapper(),amt);
				}
				/*else if(amt.equals("other")){
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where PAYAMT <? and REQNTYPE='Direct Credit'and assign_to=? and flag='Y'", new sortRowMapper(),amt1,u.getUsername());
					return li;
				}*/
				
			}
			else if(paymode.equals("RTGS")){
				if(amt.equals("gfive"))
				{
					System.out.println("user selected range "+amt);
					
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where PAYAMT >=? and REQNTYPE='RTGS' and assign_to=? and flag='Y'", new sortRowMapper(),amount,u.getUsername());
					return li;
					//return jdbcTemplate.queryForObject("select REQNNO,PAYAMT from tbl_input where PAYAMT >= ?",new sortRowMapper(),amount);
				}
				else if(amt.equals("betofi")){
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where PAYAMT >=? and PAYAMT < ? and REQNTYPE='RTGS' and assign_to=? and flag='Y'", new sortRowMapper(),amo,amount,u.getUsername());
					return li;
					//return jdbcTemplate.queryForObject("select REQNNO,PAYAMT from tbl_input where PAYAMT between 400000 and 500000",new sortRowMapper(),amt);
				}
				else if(amt.equals("lessTwo")){
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where  PAYAMT < ? and REQNTYPE='RTGS' and assign_to=? and flag='Y'", new sortRowMapper(),amt1,u.getUsername());
					return li;
					//return jdbcTemplate.queryForObject("select REQNNO,PAYAMT from tbl_input where PAYAMT between 400000 and 500000",new sortRowMapper(),amt);
				}
				/*else if(amt.equals("other")){
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where PAYAMT <=? and REQNTYPE='RTGS' and assign_to=? and flag='Y'", new sortRowMapper(),amt1,u.getUsername());
					return li;
				}*/
			}
			/*--------------------------------NEFT-----------------------------*/
			else if(paymode.equals("NEFT")){
				if(amt.equals("gfive"))
				{
					System.out.println("user selected range "+amt);
					
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where PAYAMT >=? and REQNTYPE='NEFT' and assign_to=? and flag='Y'", new sortRowMapper(),amount,u.getUsername());
					return li;
					//return jdbcTemplate.queryForObject("select REQNNO,PAYAMT from tbl_input where PAYAMT >= ?",new sortRowMapper(),amount);
				}
				else if(amt.equals("betofi")){
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where PAYAMT >=? and PAYAMT < ? and REQNTYPE='NEFT' and assign_to=? and flag='Y'", new sortRowMapper(),amo,amount,u.getUsername());
					return li;
					//return jdbcTemplate.queryForObject("select REQNNO,PAYAMT from tbl_input where PAYAMT between 400000 and 500000",new sortRowMapper(),amt);
				}
				else if(amt.equals("lessTwo")){
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where  PAYAMT < ? and REQNTYPE='NEFT' and assign_to=? and flag='Y'", new sortRowMapper(),amt1,u.getUsername());
					return li;
					//return jdbcTemplate.queryForObject("select REQNNO,PAYAMT from tbl_input where PAYAMT between 400000 and 500000",new sortRowMapper(),amt);
				}
				/*else if(amt.equals("other")){
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where PAYAMT <=? and REQNTYPE='NEFT' and assign_to=? and flag='Y'", new sortRowMapper(),amt1,u.getUsername());
					return li;
				}*/
			}
			/*--------------------------------ECS-----------------------------*/
			else if(paymode.equals("ECS")){
				if(amt.equals("gfive"))
				{
					System.out.println("user selected range "+amt);
					
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where PAYAMT >=? and REQNTYPE='ECS' and assign_to=? and flag='Y'", new sortRowMapper(),amount,u.getUsername());
					return li;
					//return jdbcTemplate.queryForObject("select REQNNO,PAYAMT from tbl_input where PAYAMT >= ?",new sortRowMapper(),amount);
				}
				else if(amt.equals("betofi")){
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where PAYAMT >=? and PAYAMT < ? and REQNTYPE='ECS' and assign_to=? and flag='Y'", new sortRowMapper(),amo,amount,u.getUsername());
					return li;
					//return jdbcTemplate.queryForObject("select REQNNO,PAYAMT from tbl_input where PAYAMT between 400000 and 500000",new sortRowMapper(),amt);
				}
				else if(amt.equals("lessTwo")){
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where  PAYAMT < ? and REQNTYPE='ECS' and assign_to=? and flag='Y'", new sortRowMapper(),amt1,u.getUsername());
					return li;
					//return jdbcTemplate.queryForObject("select REQNNO,PAYAMT from tbl_input where PAYAMT between 400000 and 500000",new sortRowMapper(),amt);
				}
				/*else if(amt.equals("other")){
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where PAYAMT <=? and REQNTYPE='ECS' and assign_to=? and flag='Y'", new sortRowMapper(),amt1,u.getUsername());
					return li;
				}*/
			}
			
			/*------------------------------NEFT NRE----------------------------------------*/
			else if(paymode.equals("G")){
				if(amt.equals("gfive"))
				{
					System.out.println("user selected range "+amt);
					
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where PAYAMT >=? and REQNTYPE='G' and assign_to=? and flag='Y'", new sortRowMapper(),amount,u.getUsername());
					return li;
					//return jdbcTemplate.queryForObject("select REQNNO,PAYAMT from tbl_input where PAYAMT >= ?",new sortRowMapper(),amount);
				}
				else if(amt.equals("betofi")){
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where PAYAMT >=? and PAYAMT < ? and REQNTYPE='G' and assign_to=? and flag='Y'", new sortRowMapper(),amo,amount,u.getUsername());
					return li;
					//return jdbcTemplate.queryForObject("select REQNNO,PAYAMT from tbl_input where PAYAMT between 400000 and 500000",new sortRowMapper(),amt);
				}
				else if(amt.equals("lessTwo")){
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where  PAYAMT < ? and REQNTYPE='G' and assign_to=? and flag='Y'", new sortRowMapper(),amt1,u.getUsername());
					return li;
					//return jdbcTemplate.queryForObject("select REQNNO,PAYAMT from tbl_input where PAYAMT between 400000 and 500000",new sortRowMapper(),amt);
				}
				/*else if(amt.equals("other")){
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where PAYAMT <=? and REQNTYPE='G' and assign_to=? and flag='Y'", new sortRowMapper(),amt1,u.getUsername());
					return li;
				}*/
			}
			/*-------------------------------------DC-NRE --------------------------------------------------*/
			else if(paymode.equals("F")){
				if(amt.equals("gfive"))
				{
					System.out.println("user selected range "+amt);
														//	   REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE from tbl_samplingInput where flag='Y' AND assign_to=? AND [REQNTYPE]=?
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where PAYAMT >=? and REQNTYPE='F' and assign_to=? and  flag='Y'", new sortRowMapper(),amount,u.getUsername());
					return li;
					//return jdbcTemplate.queryForObject("select REQNNO,PAYAMT from tbl_input where PAYAMT >= ?",new sortRowMapper(),amount);
				}
				else if(amt.equals("betofi")){
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where PAYAMT >=? and PAYAMT < ? and REQNTYPE='F' and assign_to=? and flag='Y'", new sortRowMapper(),amo,amount,u.getUsername());
					return li;
					//return jdbcTemplate.queryForObject("select REQNNO,PAYAMT from tbl_input where PAYAMT between 400000 and 500000",new sortRowMapper(),amt);
				}
				else if(amt.equals("lessTwo")){
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where  PAYAMT < ? and REQNTYPE='F' and assign_to=? and flag='Y'", new sortRowMapper(),amt1,u.getUsername());
					return li;
					//return jdbcTemplate.queryForObject("select REQNNO,PAYAMT from tbl_input where PAYAMT between 400000 and 500000",new sortRowMapper(),amt);
				}
				/*else if(amt.equals("other")){
					List<Cheque> li=jdbcTemplate.query("select REQNNO,CHDRNUM,CONVERT(varchar(255),SALUTL)+' '+CONVERT(varchar(255),LGIVNAME)+' '+CONVERT(varchar(255),LSURNAME) as custname,REQNTYPE,PAYAMT from tbl_samplingInput where PAYAMT < ? and REQNTYPE='F' and assign_to=? and flag='Y'", new sortRowMapper(),amt1,u.getUsername());
					return li;
				}*/
		}
		
	}catch(Exception e)
		{
			System.out.println("Out side of sort MEthod");
		}
		return null;
	}	

	private static class  sortRowMapper implements RowMapper<Cheque>
	{
		@Override
		public Cheque mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Cheque cheque = new Cheque();
			//cheque.setvPolicyNo(rs.getString(7));
			/*cheque.setvAccountNumber(rs.getString(68));
			cheque.setMifscCode(rs.getString(69));
			cheque.setMmicrCode(rs.getString(70));*/
			//cheque.setvPolicyNo(rs.getString("CHDRNUM"));
			cheque.setPolicyno(rs.getString("CHDRNUM"));
			cheque.setPaymentMode(rs.getString("REQNTYPE"));
			cheque.setPrnNo(rs.getString("REQNNO"));
		    cheque.setName(rs.getString("custname"));
			cheque.setAmount(rs.getFloat("PAYAMT"));
			
			System.out.println("SpoolRepositoryImpl:findById "+cheque);
			return cheque;
			
		}
	}
	@Override
	public int countToDoListpaymode(User u, String paymode, String amt) {
		
		int countmode = 0;
		float amount = 500000;
		float amo=200000;
		float amt1=200000;
		if(paymode.equals("Direct Credit"))
		{
			//System.out.println("mode of payment "+paymode);
			if(amt.equals("gfive"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT >=?", Integer.class,u.getUsername(),paymode,amount);
				
			}
			if(amt.equals("betofi"))
			{
				
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT >=? and PAYAMT < ?", Integer.class,u.getUsername(),paymode,amo,amount);
				System.out.println("get range "+countmode);
			}
			if(amt.equals("lessTwo"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and  PAYAMT < ?", Integer.class,u.getUsername(),paymode,amt1);
				
			}
			/*if(amt.equals("other"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT < ?", Integer.class,u.getUsername(),paymode,amt1);
				
			}*/
		}
		/*----------------------------------------------------------------*/
		
		if(paymode.equals("RTGS"))
		{
			//System.out.println("mode of payment "+paymode);
			if(amt.equals("gfive"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT >=?", Integer.class,u.getUsername(),paymode,amount);
				
			}
			if(amt.equals("betofi"))
			{	
				System.out.println("get amount range ");
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT >=? and PAYAMT < ?", Integer.class,u.getUsername(),paymode,amo,amount);
				System.out.println("get range "+countmode);
			}
			if(amt.equals("lessTwo"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT < ?", Integer.class,u.getUsername(),paymode,amt1);
				
			}
			/*if(amt.equals("other"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT < ?", Integer.class,u.getUsername(),paymode,amt1);
				
			}*/
		}
		if(paymode.equals("NEFT"))
		{
			//System.out.println("mode of payment "+paymode);
			if(amt.equals("gfive"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT >=?", Integer.class,u.getUsername(),paymode,amount);
				
			}
			if(amt.equals("betofi"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT >= ? and PAYAMT < ?", Integer.class,u.getUsername(),paymode,amo,amount);
				
			}
			if(amt.equals("lessTwo"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT< ?", Integer.class,u.getUsername(),paymode,amt1);
				
			}
			/*if(amt.equals("other"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT < ?", Integer.class,u.getUsername(),paymode,amt1);
				
			}*/
		}
		if(paymode.equals("ECS"))
		{
			//System.out.println("mode of payment "+paymode);
			if(amt.equals("gfive"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT >=?", Integer.class,u.getUsername(),paymode,amount);
				
			}
			if(amt.equals("betofi"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT >= ? and PAYAMT < ?", Integer.class,u.getUsername(),paymode,amo,amount);
				
			}
			if(amt.equals("lessTwo"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT < ?", Integer.class,u.getUsername(),paymode,amt1);
				
			}
			/*if(amt.equals("other"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT < ?", Integer.class,u.getUsername(),paymode,amt1);
				
			}*/
			
		}
		if(paymode.equals("G"))
		{
			//System.out.println("mode of payment "+paymode);
			if(amt.equals("gfive"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT >=?", Integer.class,u.getUsername(),paymode,amount);
				
			}
			if(amt.equals("betofi"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT >= ? and PAYAMT < ?", Integer.class,u.getUsername(),paymode,amo,amount);
				
			}
			if(amt.equals("lessTwo"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT < ?", Integer.class,u.getUsername(),paymode,amt1);
				
			}
			/*if(amt.equals("other"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT < ?", Integer.class,u.getUsername(),paymode,amt1);
				
			}*/
		}
		if(paymode.equals("F"))
		{
			//System.out.println("mode of payment "+paymode);
			if(amt.equals("gfive"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT >=?", Integer.class,u.getUsername(),paymode,amount);
				
			}
			if(amt.equals("betofi"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT >= ? and PAYAMT < ?", Integer.class,u.getUsername(),paymode,amo,amount);
				
			}
			if(amt.equals("lessTwo"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT < ?", Integer.class,u.getUsername(),paymode,amt1);
				
			}
			/*if(amt.equals("other"))
			{
				countmode = jdbcTemplate.queryForObject("select count(*) as count from tbl_samplingInput where flag='Y' AND assign_to=? and [REQNTYPE]=? and PAYAMT < ?", Integer.class,u.getUsername(),paymode,amt1);
				
			}*/
		}
		  
		return countmode;
	}

	@Override
	public boolean checkAmountRange(double amount, String amountRange) {
		
		if(amountRange.equals("gfive") && amount>=500000 )
		{
			System.out.println("inside less 5 lakh");
			return true;
		}
		else if(amountRange.equals("betofi") && amount>=200000 && amount<500000){
			System.out.println("inside btw 2000 and 5 l");
			return true;
		}
		else if(amountRange.equals("lessTwo") && amount<200000 ){
			System.out.println("inside less Than 200000");
			return true;
		}
		return false;
	}	
	
}
