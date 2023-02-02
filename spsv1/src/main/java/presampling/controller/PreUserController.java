package presampling.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prodata.spsv1.model.User;

import presampling.repository.PreUserRepository;


@Controller
@RequestMapping("/Preuser")
public class PreUserController {
	

	@Autowired
	private PreUserRepository userRepository;

	@GetMapping("/login")
	public String showLogin(Model m, String error)
	{
		m.addAttribute("error", error);
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
	public String login(@ModelAttribute("user") User user , Model m , HttpSession s)
	{
		System.out.println(user);
		if(userRepository.getRole(user)==null)
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
			return "redirect:/Precheque/show";
		}
		else if(result.equals("updm"))
			m.addAttribute("error", "User Name Not  Exists");
		else
			m.addAttribute("error", "User Name already exists");
		return "user/Login";		
	}

}
