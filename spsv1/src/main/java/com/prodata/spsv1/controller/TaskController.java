package com.prodata.spsv1.controller;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prodata.spsv1.model.AssignTask;
import com.prodata.spsv1.model.Cheque;
import com.prodata.spsv1.repository.TaskRepository;

@Controller
@RequestMapping("/task")
public class TaskController {
	
	@Autowired
	private TaskRepository taskRepository;
	
	/*@PostMapping("/allocateTask")
	public String allocateTask(HttpSession s,@ModelAttribute AssignTask assign,Model m,@RequestParam("amtrange") String amtrange,RedirectAttributes attributes){
		String pmode =(String) s.getAttribute("pmode");
		String oneRsMode =(String) s.getAttribute("oneRsMode");
		String amtRange=(String)s.getAttribute("amtrange");
		String path = null;
		Cheque cheque = new Cheque();
		double amt=cheque.getAmount();
		int l= taskRepository.assignTask(assign.getEmployee(), assign.getAssignee(), assign.gettransfer(),pmode,oneRsMode,amtrange,amt);
			if(l!=0)
			{
				attributes.addAttribute("tastsuccess", "Task Allocated Successfully");
				if(pmode!=null && amtRange!=null&&oneRsMode==null) {
					 attributes.addAttribute("paymode", pmode);
					 attributes.addAttribute("amt", amtRange);
					 path = "redirect:/cheque/showamtgrtfive";
					 //return "redirect:/cheque/showamtgrtfive";
				}
				else if(pmode==null && amtRange==null&&oneRsMode!=null) {
					 attributes.addAttribute("OneRsType", oneRsMode);
					 path = "redirect:/cheque/OneRstype";
					 //return "redirect:/cheque/OneRstype";
				}
				else {
					path = "redirect:/cheque/show";
					//return "redirect:/cheque/show";
				}
			}
			else
			{
			attributes.addAttribute("tastsuccess", "No Task available to allocate");
			}
			return path;
		//return "redirect:/cheque/show";
			//return "redirect:/cheque/showamtgrtfive";
		//return "user/Success";
	}*/
	
	
	@PostMapping("/allocateTask")
	public String allocateTask(HttpSession s,@ModelAttribute AssignTask assign,Model m,RedirectAttributes attributes){
		System.out.println(assign);
		String pmode =(String) s.getAttribute("pmode");
		String oneRsMode =(String) s.getAttribute("oneRsMode");
		Cheque cheque = new Cheque();
		int l= taskRepository.assignTask(assign.getEmployee(), assign.getAssignee(), assign.gettransfer(),pmode,oneRsMode);
		if(l!=0)
			attributes.addAttribute("tastsuccess", "Task Allocated Successfully");
			else
			attributes.addAttribute("tastsuccess", "No Task available to allocate");
		return "redirect:/cheque/show";
		//return "user/Success";
	}
	
	
	
}
