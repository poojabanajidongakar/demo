package com.prodata.spsv1.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prodata.spsv1.model.User;
import com.prodata.spsv1.repository.ChequeRepository;
import com.prodata.spsv1.repository.UserRepository;
import com.prodata.spsv1.repository.impl.PasswordValidationImpl;



@Controller
@RequestMapping("/forgetpassword")
public class ForgetPass {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ChequeRepository chequeRepository;
	
	@Autowired
	private PasswordValidationImpl  passwordvalidation;
	
	@GetMapping("/pass")
	public String login(Model m,HttpSession s)
	{
		List<User> ulist = chequeRepository.getUserData();
		//m.addAttribute("ulist", ulist);
		return "user/forgetpass";
	}
	

	@PostMapping("/forgetpass")
	public String performlogin(Model m,@RequestParam("username") String username,@RequestParam("newpassword") String newpassword,@RequestParam("confirmpassword") String confpassword,HttpSession s)
	{
		System.out.println("controller username"+username);
		System.out.println("controller new"+newpassword);
		System.out.println("controller confirm"+confpassword);
		
		/*User u = (User) s.getAttribute("user");
		String uname=u.getUsername();*/
		
		List<User> getuser = userRepository.getusername(username);
		
		for(int i=0; i<getuser.size(); i++) 
		{
			 User u1 = getuser.get(i);
			 String username1 = u1.getUsername();
			
			 System.out.println("username is"+username1);
			 if(username1.equals(username))
			 {
				 if(newpassword.equals(confpassword))
					{
						userRepository.forgetpassword(username, confpassword);
						m.addAttribute("succmsg","Password Updated Successfully");
						List<User> ulist = chequeRepository.getUserData();
						m.addAttribute("ulist", ulist);
						return "user/forgetpass";
					}
					else
					{
						m.addAttribute("error","New and confirm Password is Not Match");
						List<User> ulist = chequeRepository.getUserData();
						m.addAttribute("ulist", ulist);
						return "user/forgetpass";
					}
			 }
			 
			 
		}
		List<User> ulist = chequeRepository.getUserData();
		m.addAttribute("ulist", ulist);
		m.addAttribute("error","Username is Not Exist");
		return "user/forgetpass" ;
		
	}
	@GetMapping("/changepass")
	public String changepass(Model m,String error,String succmsg,String confpassword)
	{
		System.out.println("changepass:confpassword........ "+confpassword);
		m.addAttribute("confpassword", confpassword);
		m.addAttribute("error", error);
		m.addAttribute("succmsg", succmsg);
		return "user/Changepassword";
	}
	
	
	@PostMapping("/changepassword")
	public String changepass(Model m,@RequestParam("username") String username,@RequestParam("oldpassword") String oldpassword,@RequestParam("newpassword") String newpassword,@RequestParam("confirmpassword") String confpassword,HttpSession s,RedirectAttributes redirectAttributes)
	{
		
         List<User> getuser = userRepository.getusername(username);
		
		for(int i=0; i<getuser.size(); i++)
		{
			 User u1 = getuser.get(i);
			 String pass1 = u1.getPassword();
			 String username1 = u1.getUsername();

			 String passwordvalid = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[~`!@#$%^&*()-_+=])(?=\\S+$).{8,}";
			 
		     System.out.println("newpassword is"+newpassword + " pass1........"+pass1+"   oldpassword... "+oldpassword+"   newpassword...."+newpassword+"   confpassword... "+confpassword);
		    if(pass1.equals(oldpassword) && username1.equalsIgnoreCase(username) )
		    {
		    	if((!newpassword.matches(passwordvalid)) || (!confpassword.matches(passwordvalid))) 
		    	{
		    		redirectAttributes.addAttribute("error","Password must contain at least 1 capital letter, small letter, 1 number and 1 special character and minimum 8 characters......!!!!!");
		    		return "redirect:/forgetpassword/changepass";
		    	}
		    	else if(!newpassword.equals(confpassword))
				{
		    		redirectAttributes.addAttribute("error","New and confirm Password is Not Match");
					return "redirect:/forgetpassword/changepass";
				}
				else
				{
					String updatepass = passwordvalidation.passwordExist(username,newpassword);
					if(updatepass != null) {
						redirectAttributes.addAttribute("error","Password already used.....!!!");
						return "redirect:/forgetpassword/changepass";
					}
					
					userRepository.forgetpassword(username, confpassword);
					redirectAttributes.addAttribute("succmsg","Password Updated Successfully");
					redirectAttributes.addAttribute("confpassword",confpassword);
					return "redirect:/forgetpassword/changepass";
				}
		     }
		    else {
		    	redirectAttributes.addAttribute("error","Old Password is Not Match");
		    	return "redirect:/forgetpassword/changepass";
		    }
	 }
		redirectAttributes.addAttribute("error1","Invalid Credentials");
		return "redirect:/forgetpassword/changepass";
}

	
	@GetMapping("/forgetpass")
	public String forgetpassword(Model m,@RequestParam("username") String username1, String oldpassword, String username,String error, String succmsg,HttpSession s)
	{
		//System.out.println("inside forgetpassword:username1...... "+username1+"  oldpassword......... "+oldpassword+"  username......."+username);
		String password = passwordvalidation.checkOldPassword(username);
		//System.out.println("password is..... "+password);
		if(userRepository.checkValidStatus(username1).equalsIgnoreCase("open"))
		{
			if(username1 !=null) {
				s.setAttribute("username", username1);
			}
			else
			{
				s.setAttribute("username", username);
			}
			m.addAttribute("username1", username1);
			m.addAttribute("password", password);
			m.addAttribute("username", username);
			m.addAttribute("oldpassword", oldpassword);
			m.addAttribute("error", error);
			m.addAttribute("succmsg", succmsg);
			return "user/forgetpassword";
		}
    else{
			
			s.setAttribute("username", "");
			m.addAttribute("error", "Link Already Expired.Please Contact With AppDev Team..");
			return "user/forgetpassword";
		}
	  	}
	
	@PostMapping("/changeforgetpassword")
	public String changeforgetpassword(Model m,@RequestParam("username") String username,@RequestParam("oldpassword") String oldpassword,@RequestParam("newpassword") String newpassword,@RequestParam("confirmpassword") String confpassword,HttpSession s,RedirectAttributes redirectAttributes)
	{
		
         List<User> getuser = userRepository.getusername(username);
		
		for(int i=0; i<getuser.size(); i++)
		{
			 User u1 = getuser.get(i);
			 String pass1 = u1.getPassword();
			 String username1 = u1.getUsername();
			 String passwordvalid = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[~`!@#$%^&*()-_+=])(?=\\S+$).{8,}";
			 
		     //System.out.println("newpassword is"+newpassword + " pass1........"+pass1+"   oldpassword... "+oldpassword+"   newpassword...."+newpassword+"   confpassword... "+confpassword);
		    if(pass1.equals(oldpassword) && username1.equalsIgnoreCase(username) )
		    {
		    	if((!newpassword.matches(passwordvalid)) || (!confpassword.matches(passwordvalid))) 
		    	{
		    		redirectAttributes.addAttribute("error","Password must contain at least 1 capital letter, small letter, 1 number and 1 special character and minimum 8 and maximum 15 characters......!!!!!");
		    		redirectAttributes.addAttribute("username",username);
		    		redirectAttributes.addAttribute("oldpassword",oldpassword);
		    		return "redirect:/forgetpassword/forgetpass";
		    	}
		    	else if(!newpassword.equals(confpassword))
				{
		    		redirectAttributes.addAttribute("error","New and confirm Password is Not Match");
		    		redirectAttributes.addAttribute("username",username);
		    		redirectAttributes.addAttribute("oldpassword",oldpassword);
					return "redirect:/forgetpassword/forgetpass";
				}
				else
				{
					//System.out.println("username........."+username+"  newpassword..... "+newpassword);
					String updatepass = passwordvalidation.passwordExist(username,newpassword);
					//System.out.println("updatepass.........."+updatepass);
					if(updatepass != null) {
						redirectAttributes.addAttribute("error","Password already used.....!!!");
						redirectAttributes.addAttribute("username",username);
			    		redirectAttributes.addAttribute("oldpassword",oldpassword);
						return "redirect:/forgetpassword/forgetpass";
					}
					
					boolean ok = userRepository.forgetpassword(username, confpassword);
					if(ok==true)
					{
						userRepository.updStatus(username, confpassword);
						m.addAttribute("succmsg","Password Updated Successfully");
						m.addAttribute("username","");
			    		m.addAttribute("oldpassword","");
						m.addAttribute("confpassword","");
						//return "redirect:/forgetpassword/forgetpass";
						return "user/forgetpassword";
					}
				}
		     }
		    else {
		    	redirectAttributes.addAttribute("error","Old Password is Not Match");
		    	redirectAttributes.addAttribute("username",username);
	    		redirectAttributes.addAttribute("oldpassword",oldpassword);
		    	return "redirect:/forgetpassword/forgetpass";
		    }
	
	 }
		redirectAttributes.addAttribute("error1","Invalid Credentials");
		redirectAttributes.addAttribute("username",username);
		redirectAttributes.addAttribute("oldpassword",oldpassword);
		return "redirect:/forgetpassword/forgetpass";
}
	
}
