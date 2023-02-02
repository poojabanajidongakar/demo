package com.prodata.spsv1.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface PasswordValidation {
	String getLoginDate(String uname);
	int updatePassword(String uname, String oldpassword, String newpassword, String confirmpassword);
	String checkOldPassword(String uname);
	String passwordExist(String uname, String newpassword);
	String checkRemainDays(int diffdays);
}
