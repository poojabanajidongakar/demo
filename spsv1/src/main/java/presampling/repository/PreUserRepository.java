package presampling.repository;

import java.util.List;

import com.prodata.spsv1.model.User;

public interface PreUserRepository {

	
	//boolean userLogin(User user); //login user
	
	String userLogin(User user); //login user
	boolean forgetpassword(String username, String password);

	List<User> getusername();

	String getRole(User u);
}
