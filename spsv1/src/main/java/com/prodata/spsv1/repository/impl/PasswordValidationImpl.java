package com.prodata.spsv1.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.prodata.spsv1.repository.PasswordValidation;

@Repository
public class PasswordValidationImpl implements PasswordValidation{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public String getLoginDate(String uname) {
		String tdate = null;
		try {
			tdate = jdbcTemplate.queryForObject("select DATEADD(MONTH,+1, [cdate]) FROM [spsv1].[dbo].[tbl_user] where username=? and cdate is not null",String.class,uname);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("getLoginDate in.."+tdate);
		return tdate;
	}

	@Override
	public int updatePassword(String uname, String oldpassword, String newpassword, String confirmpassword) {
		try {
			jdbcTemplate.update("update [spsv1].[dbo].[tbl_user] set password = ?, cdate = convert(nvarchar(10),getdate(),120) where username = ?",confirmpassword,uname);
			jdbcTemplate.update("update [spsv1].[dbo].[tbl_user_password] set [fmonth_password] = [smonth_password] where username = ?",uname);
			jdbcTemplate.update("update [spsv1].[dbo].[tbl_user_password] set [smonth_password] = [tmonth_password] where username = ?",uname);
			jdbcTemplate.update("update [spsv1].[dbo].[tbl_user_password] set [tmonth_password] = ? where username = ?",newpassword,uname);
			
			return 1;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public String checkOldPassword(String uname) {
		String password = null;
		try {
			password = jdbcTemplate.queryForObject("select password FROM [spsv1].[dbo].[tbl_user] where username=?",String.class,uname);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return password;
	}

	@Override
	public String passwordExist(String uname, String newpassword) {
		String passdata = null;
		try {
			passdata = jdbcTemplate.queryForObject("select username from [spsv1].[dbo].[tbl_user_password] where (username = ?) and (? in (fmonth_password, smonth_password, tmonth_password))",String.class,uname,newpassword);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return passdata;
	}

	@Override
	public String checkRemainDays(int diffdays) {
		String daysmsg = null;
		
		if(diffdays <= 5 && diffdays > 0) {
			daysmsg = "Your password will be expire in " + diffdays + " days.";
		}

		return daysmsg;
	}

}
