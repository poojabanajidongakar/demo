package presampling.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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

import com.prodata.spsv1.model.AssignTask;
import com.prodata.spsv1.model.Cheque;

import presampling.repository.PreTaskRepository;


@Controller
@RequestMapping("/Pretask")
public class PreTaskController {
	
	@Autowired
	private PreTaskRepository taskRepository;
	
	@PostMapping("/allocateTask")
	public String allocateTask(HttpSession s,@ModelAttribute AssignTask assign,Model m,RedirectAttributes attributes){
		System.out.println("in alocate tastk method......."+assign);
		//String pmode =(String) s.getAttribute("pmode");
		//String oneRsMode =(String) s.getAttribute("oneRsMode");
		String type =(String) s.getAttribute("type");
		System.out.println("allocateTask:type........ "+type);
		Cheque cheque = new Cheque();
		//int l= taskRepository.assignTask(assign.getEmployee(),assign.getAssignee(), assign.gettransfer());
		int l= taskRepository.assignTask(assign.getEmployee(),assign.getAssignee(), assign.gettransfer(),type);
		//System.out.println("data to assign..........."+assign.gettransfer());
		if(l!=0) {
			attributes.addAttribute("tastsuccess", "Task Allocated Successfully");
		}
		else {
			attributes.addAttribute("tastsuccess", "No Task available to allocate");
		}
		attributes.addAttribute("type",type);
		return "redirect:/Precheque/show";
		//return "user/Success";
	}
	
	
	
	
	
	
}
