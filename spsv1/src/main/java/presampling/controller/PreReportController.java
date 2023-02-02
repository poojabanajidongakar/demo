package presampling.controller;

import java.security.KeyStore.Entry.Attribute;
import java.util.ArrayList;
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


import com.prodata.spsv1.model.Cheque;
import com.prodata.spsv1.model.ProductivityReport;
import com.prodata.spsv1.model.User;

import presampling.repository.PreChequeRepository;
import presampling.repository.PreDiscrepancyRepository;
import presampling.repository.PreReportRepository;




@Controller
@RequestMapping("/Prereport")
public class PreReportController {
	
	@Autowired
	private PreReportRepository reportRepository ;
	
	@Autowired
	private PreChequeRepository chequeRepository; 
	
	@Autowired
	private PreDiscrepancyRepository discrepancyRepository; 
	
	@GetMapping("/show")
	public String showReport(Model m,HttpSession s){
		User u = (User) s.getAttribute("user");
		List<User> ulist = chequeRepository.getUserData();
		m.addAttribute("processname","Status of Pre-Sampling");
		m.addAttribute("ulist", ulist);
		return "user/PreReport";
	}
	
	@PostMapping("/show")
	public String GetReport(Model m,@RequestParam("sdate")String sdate, @RequestParam String edate,@RequestParam String employee, HttpSession s){
		User u = (User) s.getAttribute("user");
		List<User> ulist = chequeRepository.getUserData();
		List<ProductivityReport> reportlist = new ArrayList<ProductivityReport>() ;
		List<ProductivityReport> reportlist1 = new ArrayList<ProductivityReport>() ;
		ProductivityReport p = new ProductivityReport();
		if(employee.equals("all"))
		{
		
			////System.out.println("ulist size value............... "+ulist.size());
			for(int i=0; i < ulist.size(); i++){
				User u1 = ulist.get(i);
				String uname = u1.getUsername();
				//System.out.println("employee value............... "+uname);
				reportlist = reportRepository.getReport(sdate, edate, uname);
				for(int j=0;j<reportlist.size();j++)
				{
					 p = reportlist.get(j);
					 if(p.getUsername()!=null)
					 {
						 reportlist1.add(p);
					 }	 
				}
					
				
			}
			//System.out.println("reportlist:all............ "+reportlist);
			m.addAttribute("report",reportlist1);
		}
		else {
			reportlist = reportRepository.getReport(sdate, edate, employee);
			try {
				p=reportlist.get(0);
			} catch (Exception e) {
				m.addAttribute("error","No Task Allocated for "+employee);
			}
			
			if(p.getUsername()==null){
				m.addAttribute("error","No Task Allocated for "+employee);
			}
			else{
				m.addAttribute("report",reportlist);
			}
			
		}
	
		m.addAttribute("employee",employee);
		m.addAttribute("ulist", ulist);
	
		m.addAttribute("controller","loanreport");
		m.addAttribute("processname","Status of Pre-Sampling");
		return "user/PreReport";
	}
	@GetMapping("/searchuser")
	public String searchuser(Model m,HttpSession s){
		return "user/PreSearchUser";
	}
	
	@PostMapping("/searchuserdata")
	public String searchuserdata(Model m,@RequestParam("policyno") String policyno, String policyValue){
		/*System.out.println("policyValue......... "+policyValue);
		List<Cheque> searchulist = reportRepository.getPolicyRelatedUser(policyValue);*/
		//System.out.println("policyValue......... "+policyno);
		List<Cheque> searchulist = reportRepository.getPolicyRelatedUser(policyno);
		//System.out.println(searchulist);
		if(searchulist.isEmpty()){
		m.addAttribute("error","Policy number not found");
			}
		else{
			m.addAttribute("error"," ");
		}
		m.addAttribute("searchulist",searchulist);
		
		return "user/PreSearchUser";
	}
	
	@GetMapping("/editpolicy")
	public String editpolicy(@ModelAttribute  Cheque cheque,Model m,@RequestParam("policyNo") String policyNo,@RequestParam("prnno") String prnno){
		
		//System.out.println("editpolicy"+policyNo);
		//System.out.println("editpolicy"+prnno);
		//Cheque chequeList= chequeRepository.updatePolicy(policyNo, prnno);
		
		//System.out.println("editpolicy:chequelist " +chequeList);
		List<String> discrepancyTags = discrepancyRepository.getAllDiscrepancyTags();
		m.addAttribute("discrepancyTags", discrepancyTags);
		//m.addAttribute("cheque", chequeList);
		return "user/Editpolicy";
		
	}
	@PostMapping("/policysave")
	public String policysave(@ModelAttribute  Cheque cheque,Model m,HttpSession s){
		User u = (User) s.getAttribute("user");
		String prnno=cheque.getPrnNo();
		chequeRepository.insertEditPolicy(cheque,u,prnno);
		return "redirect:/report/searchuser";
		
	}
	
	@GetMapping("/MISReport")
	public String OneRsReport(Model m)
	{
		Cheque DisplayStatus=reportRepository.samplingMis();
		//System.out.println("OneRsReport: OneRsReport"+DisplayStatus);
		m.addAttribute("DisplayStatus", DisplayStatus);
	
		return "user/MISReport";
		
	}
	
}
