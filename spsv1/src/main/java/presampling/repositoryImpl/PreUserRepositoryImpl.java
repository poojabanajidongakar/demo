package presampling.repositoryImpl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.prodata.spsv1.model.User;

import presampling.repository.PreUserRepository;





@Repository
public class PreUserRepositoryImpl implements PreUserRepository{

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
		return null;
	}


	@Override
	public boolean forgetpassword(String username, String password) {
		System.out.println("getusername"+username);
		System.out.println("getusername"+password);
		 jdbcTemplate.update("update  tbl_user set password=? where username=?",password,username);
		return true;
	}

	@Override
	public List<User> getusername() {
		List<User> li = jdbcTemplate.query("select * from tbl_user", new UserDataRowMapper());
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
		} catch (Exception e) {
			return null;
		}
	}


		
	}	
