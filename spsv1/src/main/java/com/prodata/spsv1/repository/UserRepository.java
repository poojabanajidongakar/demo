package com.prodata.spsv1.repository;

import java.util.List;

import com.prodata.spsv1.model.User;

public interface UserRepository {

	
	//boolean userLogin(User user); //login user
	
	String userLogin(User user); //login user
	boolean forgetpassword(String username, String password);

	List<User> getusername(String username);

	String getRole(User u);
	boolean updStatus(String username, String password);
	String checkValidStatus(String uname);
	String getStatus(User user);
	boolean upd(User user);
}
