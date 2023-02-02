package com.prodata.spsv1.repository.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prodata.spsv1.model.User;
import com.prodata.spsv1.repository.UserRepository;




@Repository
public class UserRepositoryImpl implements UserRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/*@Override
	public boolean userLogin(User user)
	{
		
		try
		{
			System.out.println("user object"+user);
			 User user2 =  jdbcTemplate.queryForObject("select * from tbl_user where username=? and password=?", new UserRowMapper() , user.getUsername(),user.getPassword());
			 
			 if(user2!=null && user.getUsername().trim().equals(user2.getUsername().trim()) && user.getPassword().trim().equals(user.getPassword().trim()))
				 return true;	 
		}
		catch (Exception e)
		{
				//return false;
				throw e;
		}
		return false;
	}*/
	
	/*@Override
	public String userLogin(User user)
	{
		try
		{
			System.out.println("user object"+user);
			 User user2 =  jdbcTemplate.queryForObject("select * from tbl_user where username=? and password=?", new UserRowMapper() , user.getUsername(),user.getPassword());
			 
			 System.out.println("user2 is"+user2);
			 if(user2!=null && user.getUsername().equals(user2.getUsername())&& user.getPassword().equals(user2.getPassword()))
				 return true;
			 
			 if(user2!=null && user.equals(user2))	
				 return false;
		}
		catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			try{
				System.out.println("insert user");
				jdbcTemplate.update("INSERT INTO tbl_user VALUES(?,?)", user.getUsername(),user.getPassword());
				return true;
			}
			catch (Exception ex) {
				return false;
			}
			
		}
		return false;
	}*/
	
	private static class UserRowMapper implements RowMapper<User>
	{
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			return user;
		}
		
	}

	@Override
	public String userLogin(User user) {

		try
		{
			 //System.out.println("user object..... "+user);
			 User user2 =  jdbcTemplate.queryForObject("select * from tbl_user where username=? and password=? and [Status]='unblock'", new UserRowMapper() , user.getUsername(),user.getPassword());
			 
			 //System.out.println("user2 is..... "+user2);
			 
			 if(user2!=null && user.getUsername().equals(user2.getUsername())&& user.getPassword().equals(user2.getPassword())){
				 //System.out.println("in s........");
				 return "s";
			 }
			 
			 if(user2!=null || ((!user.getUsername().equals(user2.getUsername())) || (!user.getPassword().equals(user2.getPassword())))) {
				 //System.out.println("in sss........");
				 return "sss";
			 }
			 
			 if(user2!=null || user.equals(user2)){	
				 //System.out.println("in updm........");
				 return "updm";	 
			 }
		}
		catch (Exception e)
		{
			//e.printStackTrace();
			//System.out.println("insert user");
			return "ue";
		}
		return null;
		
		/*try
		{
			System.out.println("user object"+user);
			 User user2 =  jdbcTemplate.queryForObject("select * from tbl_user where username=? and password=?", new UserRowMapper() , user.getUsername(),user.getPassword());
			 
			 System.out.println("user2 is"+user2);
			 if(user2!=null && user.getUsername().trim().equalsIgnoreCase(user2.getUsername().trim())&& user.getPassword().trim().equalsIgnoreCase(user2.getPassword().trim()))
				 return "s";
			 
			 if(user2!=null && user.equals(user2))	
				 return "updm";	 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			try{
				System.out.println("insert user");
				jdbcTemplate.update("INSERT INTO tbl_user VALUES(?,?)", user.getUsername(),user.getPassword());
				return "s";
			}
			catch (Exception ex) {
				ex.printStackTrace();
				return "ue";
			}
		}
		return null;*/
	}


	@Override
	public boolean forgetpassword(String username, String password) {
		System.out.println("getusername"+username);
		System.out.println("getusername"+password);
		 jdbcTemplate.update("update  tbl_user set password=?, cdate = convert(nvarchar(10),getdate(),120) where username=?",password,username);
		 jdbcTemplate.update("update tbl_user_password set [fmonth_password] = [smonth_password] where username = ?",username);
		 jdbcTemplate.update("update tbl_user_password set [smonth_password] = [tmonth_password] where username = ?",username);
		 jdbcTemplate.update("update tbl_user_password set [tmonth_password] = ? where username = ?",password,username);
		 return true;
	}

	@Override
	public List<User> getusername(String username) {
		List<User> li = jdbcTemplate.query("select * from tbl_user where username= ?", new UserDataRowMapper(),username);
		return li;
	}

	private static class UserDataRowMapper implements RowMapper<User>
	{
		@Override
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			return user;
		}	
	}

	@Override
	public String getRole(User u) {
		try {
			return jdbcTemplate.queryForObject("select role from tbl_user where username=? and password=?",String.class,u.getUsername(),u.getPassword());
		} catch (Exception e)
		{
			return null;
		}
	}

	@Override
	public String checkValidStatus(String uname) 
	{
		String rol = null;
		
		try 
		{
			rol=jdbcTemplate.queryForObject("select [Valid_Status] FROM tbl_user where username=?",String.class,uname);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return rol;
	}
	
	@Override
	public boolean updStatus(String username, String password) 
	{
		jdbcTemplate.update("update tbl_user set Valid_Status='expire' where username=? and password= ?",username,password);
		return true;
	}


	@Override
	public String getStatus(User user) {
try {
			
			String a= jdbcTemplate.queryForObject("select [Status] from tbl_user where username=? and password=?",String.class,user.getUsername(),user.getPassword());
		System.out.println("a.."+a);
			return a;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("in catch..");
			return null;
		}
	}


	@Override
	public boolean upd(User user) {
		 jdbcTemplate.update("update tbl_user set [Status]='block' where username=?",user.getUsername());
			return true;
		
	}
		
	}	
