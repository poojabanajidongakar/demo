package com.pooja.dongarkar.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class Login {

	@GetMapping("/login")
	public String login() {
		return "login";
		
	}

	
	@PostMapping("/welcome")
	public String welcome(@RequestParam("username") String username ,@RequestParam("password") String password, Model m) {
		
		if (username.equals("prodata") && password.equals("123")) {
			System.out.println("Welcome To Prodata..");
			//m.addAttribute("success","Welcome To Prodata..");
			return "Welcome";
		}
		else {
			
			System.out.println("Incorrect Crdentials! Try Again..");
			m.addAttribute("error", "Incorrect Crdentials! Try Again..");
			
			return "login";

		}
	}
	@GetMapping("/Navigation")
	public String navigation() {
		return "navigation";
	}
	
	@GetMapping("/juniorcollege")
	public String juniorcollege() {
		return "juniorcollege";

}
}