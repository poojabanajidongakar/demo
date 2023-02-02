package com.prodata.spsv1.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prodata.spsv1.model.User;
import com.prodata.spsv1.repository.UserRepository;

@Controller
@RequestMapping("/user1")
public class UserController1 {
	

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/login")
	public String showLogin(Model m, String error,HttpSession s)
	{
		m.addAttribute("error", error);
		s.setAttribute("count",new Integer(0));
		System.out.println("login:showLogin()");
		return "user/Login";
	}

	/*@PostMapping("/login")
	public String login(@ModelAttribute("user") User user , Model m , HttpSession s, RedirectAttributes attributes)
	{

		System.out.println(user);
		
		if(user.getUsername().trim().equals("shreya") && user.getPassword().trim().equals("s@123")){
			s.setAttribute("user", user);
			return "redirect:/cheque/show";
		}
		
		boolean result = userRepository.userLogin(user);
		System.out.println(result);
		if(result)
		{
			System.out.println(user);
			s.setAttribute("user", user);
			return "redirect:/cheque/show";
		}
		
		m.addAttribute("error", "User Name Not  Exists");
		return "user/Login";
		
		attributes.addAttribute("error", "User Name Not  Exists");
		return "redirect:/user/login";
	}
*/
	@GetMapping("/logout")
	public String logout(HttpSession s)
	{
		User user = (User) s.getAttribute("user");
		if(user==null)
			return "user/Login";
		s.removeAttribute("user");
		s.invalidate();
		return "user/Login";
	}
	@PostMapping("/login")
	public String login(Model m,@RequestParam("username") String username,@RequestParam("password") String password,HttpSession s,RedirectAttributes redirectAttributes,@RequestParam("count") int count)
	{
		System.out.println("in login");
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		//System.out.println("login:count value.......... "+count);
		
		while (count < 2)
		{  
			//System.out.println("while:count value.......... "+count);
			if((userRepository.getStatus(user).equalsIgnoreCase("block")) && (userRepository.getStatus(user)!=null)){
				m.addAttribute("error", "User Get Block.Please Contact IT Support Team..");
				//s.setAttribute("count",++count);
				return "user/Login";
			}
			else if(userRepository.getRole(user)==null && ( (userRepository.getStatus(user)==null)))
			{
				m.addAttribute("error", "Credentials not Matched");
				s.setAttribute("user", user);
				s.setAttribute("count",++count);
				return "user/Login";
			}
			user.setRole(userRepository.getRole(user));
			String result = userRepository.userLogin(user);
			System.out.println(result);
			if(result.equals("s"))
			{
				System.out.println("user list..... "+user);
				s.setAttribute("user", user);
				m.addAttribute("user",user);
				s.setAttribute("count",++count);
				return "user/UserToDoList";
			}
			else if(result.equals("updm")) {
				m.addAttribute("error", "User Name Not Exists"); 
				s.setAttribute("user", user);
				s.setAttribute("count",++count);
				return "user/Login";
			}
			else {
				m.addAttribute("error", "Password not matched");
				s.setAttribute("user", user);
				s.setAttribute("count",++count);
				return "user/Login";   
			}
		}
		
		//System.out.println("after while:count value.......... "+count);
		
		if (count >= 2)
			userRepository.upd(user);
			m.addAttribute("error", "You have entered wrong password 3 times.Please Contact AppDev Team or IT Support Team.");
			return "user/Login";  
		
	}
}
