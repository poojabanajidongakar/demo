package com.prodata.spsv1.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.prodata.spsv1.model.Cheque;
import com.prodata.spsv1.model.ProductivityReport;
import com.prodata.spsv1.model.User;
import com.prodata.spsv1.repository.ChequeRepository;
import com.prodata.spsv1.repository.DiscrepancyRepository;
import com.prodata.spsv1.repository.ReportRepository;



@Controller
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private ReportRepository reportRepository ;
	
	@Autowired
	private ChequeRepository chequeRepository; 
	
	@Autowired
	private DiscrepancyRepository discrepancyRepository; 
	
	@GetMapping("/show")
	public String showReport(Model m,HttpSession s){
		User u = (User) s.getAttribute("user");
		List<User> ulist = chequeRepository.getUserData();
		m.addAttribute("processname","Status of Sampling");
		m.addAttribute("ulist", ulist);
		return "user/Report";
	}
	
	@PostMapping("/show")
	public String GetReport(Model m,@RequestParam("sdate")String sdate, @RequestParam String edate,@RequestParam String employee, HttpSession s)
	{
		User u = (User) s.getAttribute("user");
		List<User> ulist = chequeRepository.getUserData();
		List<ProductivityReport> reportlist = new ArrayList<ProductivityReport>() ;
		List<ProductivityReport> reportlist1 = new ArrayList<ProductivityReport>() ;
		ProductivityReport p = new ProductivityReport();
		if(employee.equals("all"))
		{
		
			System.out.println("ulist size value............... "+ulist.size());
			for(int i=0; i < ulist.size(); i++){
				User u1 = ulist.get(i);
				String uname = u1.getUsername();
				System.out.println("employee value............... "+uname);
				reportlist = reportRepository.getReport(sdate, edate, uname);
				for(int j=0;j<reportlist.size();j++)
				{
					 p = reportlist.get(j);
					 if(p.getUsername()!=null)
					 {
						 reportlist1.add(p);
						 System.out.println("reportlist1.add(p)............"+reportlist1);
					 }	 
				}
					
				
			}
			System.out.println("reportlist:all............ "+reportlist);
			m.addAttribute("report",reportlist1);
			System.out.println("teeeee"+reportlist1);
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
		m.addAttribute("processname","Status of Sampling");
		return "user/Report";
	}
	@GetMapping("/searchuser")
	public String searchuser(Model m,HttpSession s){
		return "user/SearchUser";
	}
	
	@PostMapping("/searchuserdata")
	public String searchuserdata(Model m,@RequestParam("policyno") String policyno, String policyValue){
		/*System.out.println("policyValue......... "+policyValue);
		List<Cheque> searchulist = reportRepository.getPolicyRelatedUser(policyValue);*/
		System.out.println("policyValue......... "+policyno);
		List<Cheque> searchulist = reportRepository.getPolicyRelatedUser(policyno);
		System.out.println(searchulist);
		if(searchulist.isEmpty()){
		m.addAttribute("error","Policy number not found");
			}
		else{
			m.addAttribute("error"," ");
		}
		m.addAttribute("searchulist",searchulist);
		
		return "user/SearchUser";
	}
	
	@GetMapping("/editpolicy")
	public String editpolicy(@ModelAttribute  Cheque cheque,Model m,@RequestParam("policyNo") String policyNo,@RequestParam("prnno") String prnno){
		
		System.out.println("editpolicy"+policyNo);
		System.out.println("editpolicy"+prnno);
		Cheque chequeList= chequeRepository.updatePolicy(policyNo, prnno);
		
		System.out.println("editpolicy:chequelist " +chequeList);
		List<String> discrepancyTags = discrepancyRepository.getAllDiscrepancyTags();
		m.addAttribute("discrepancyTags", discrepancyTags);
		m.addAttribute("cheque", chequeList);
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
		System.out.println("OneRsReport: OneRsReport"+DisplayStatus);
		m.addAttribute("DisplayStatus", DisplayStatus);
	
		return "user/MISReport";
		
	}
	
	
	@GetMapping("/showmiscancelremark")
	public String showmiscancelremark(HttpSession s, Model m)
	{
		List<Cheque> cancelremarkdata = reportRepository.getCancelRemarks();
		//System.out.println("logicfiledata........... "+logicfiledata);
		m.addAttribute("cancelremarkdata", cancelremarkdata);
		return "user/CancelRemark";
	}
	
	@ResponseBody
	@GetMapping("/addcancelremark")
	public String addcancelremark(HttpSession s, Model m, @RequestParam("subtype") String subtype, @RequestParam("remark") String remark)
	{
		String msg = null;
		//System.out.println("addlogicdata:calltype.. "+calltype + "subtype.. "+subtype + "owner.. "+owner + "processOwner.. "+processOwner);
		try {
			int addcrdata = reportRepository.insertCancelRemarkData(subtype,remark);
			if(addcrdata > 0) {
				msg = "Record Added Successfully...!!!";
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	
	@ResponseBody
	@GetMapping("/updatecancelremarkdata")
	public String updatecancelremarkdata(HttpSession s, Model m, @RequestParam("subtype") String subtype, @RequestParam("remark") String remark, @RequestParam("id1") String id1 )
	{
		String msg = null;
		//System.out.println("updatelogicdata:calltype.. "+calltype + "  subtype.. "+subtype + "  owner.. "+owner + "  processOwner.. "+processOwner+"  id1... "+id1);
		try {
			int updatecrdata = reportRepository.updateCancelRemarkData(subtype,remark,id1);
			if(updatecrdata > 0) {
				msg = "Record Updated Successfully...!!!";
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	
	@ResponseBody
	@GetMapping("/deletecancelremarkdata")
	public String deletecancelremarkdata(HttpSession s, Model m, @RequestParam("id1") String id1)
	{
		String msg = null;
		//System.out.println("deletefinalremarkdata:id1... "+id1);
		try {
			int deletecrdata = reportRepository.deleteCancelRemarkData(id1);
			//System.out.println("deleteldata..."+deleteldata);
			if(deletecrdata >= 0) {
				//System.out.println("inside deleteldata");
				msg = "Record Deleted Successfully...!!!";
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	
	
}
