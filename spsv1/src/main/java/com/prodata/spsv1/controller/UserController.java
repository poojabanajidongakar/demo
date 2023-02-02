package com.prodata.spsv1.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prodata.spsv1.model.User;
import com.prodata.spsv1.repository.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {
	

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/login")
	public String showLogin(HttpSession s)
	{
		s.setAttribute("count",new Integer(0));
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
	public String login(Model m,HttpSession s,@RequestParam("username") String username,@RequestParam("password") String password,RedirectAttributes redirectAttributes,@RequestParam("count") int count)
	{
		//System.out.println(user);
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		
		//System.out.println("login:count value.......... "+count);
				int count1 = 0, flag = 0;
				while (count <= 2)
				{  
					System.out.println("while:count value.......... "+count);
					System.out.println("userRepository.getStatus(user)......... "+userRepository.getStatus(user));
					System.out.println("userRepository.getRole(user)............. "+userRepository.getRole(user));
					if(userRepository.getRole(user)==null && userRepository.getStatus(user)==null)
					{
						m.addAttribute("error", "Credentials not Matched");
						s.setAttribute("user", user);
						count1 = ++count;
						s.setAttribute("count",count1);
						System.out.println("in Credentials not Matched:count value.......... "+count1);
						if(count1 == 3) {
							System.out.println("inside notlogin..........");
							flag = 1;
							break;
						}
						return "user/Login";
					}
					if((userRepository.getStatus(user).equalsIgnoreCase("block")) && (userRepository.getStatus(user)!=null)){
						m.addAttribute("error", "User Get Block.Please Contact IT Support Team..");
						//s.setAttribute("count",count++);
						return "user/Login";
					}
					user.setRole(userRepository.getRole(user));
					String result = userRepository.userLogin(user);
					System.out.println("result is.... "+result);
					if(result.equals("s"))
					{
						System.out.println("user list..... "+user);
						s.setAttribute("user", user);
						m.addAttribute("user",user);
						count1 = ++count;
						s.setAttribute("count",count1);
						return "redirect:/cheque/show";
					}
					if(result.equals("sss")) {
						m.addAttribute("error", "Username and Password are mismatch"); 
						s.setAttribute("user", user);
						count1 = ++count;
						s.setAttribute("count",count1);
						if(count1 == 3) {
							//System.out.println("inside notlogin..........");
							flag = 1;
							break;
						}
						return "user/Login"; 
					}
					if(result.equals("updm")) {
						m.addAttribute("error", "User Name Not Exists"); 
						s.setAttribute("user", user);
						count1 = ++count;
						s.setAttribute("count",count1);
						if(count1 == 3) {
							//System.out.println("inside notlogin..........");
							flag = 1;
							break;
						}
						return "user/Login"; 
					}
					
				}
				
				//System.out.println("flag value........... "+flag);
				
				if (flag == 1)
					//System.out.println("inside errorlogin.........");
					userRepository.upd(user);
					m.addAttribute("error", "You have entered wrong password 3 times. Please Contact AppDev Team or IT Support Team.");
					return "user/Login";   
		
		
		
		
		/*if(userRepository.getRole(user)==null)
		{
			m.addAttribute("error", "Credential no Matched");
			return "user/Login";
		}

		user.setRole(userRepository.getRole(user));
		String result = userRepository.userLogin(user);
		System.out.println(result);
		if(result.equals("s"))
		{
			System.out.println(user);
			s.setAttribute("user", user);
			return "redirect:/cheque/show";
		}
		else if(result.equals("updm"))
			m.addAttribute("error", "User Name Not  Exists");
		else
			m.addAttribute("error", "User Name already exists");
		return "user/Login";		*/
	}

}
