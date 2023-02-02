package com.prodata.spsv1.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prodata.spsv1.repository.PasswordValidation;


@Controller
@RequestMapping("/passwordvalidation")
public class PasswordValidationController {

	@Autowired
	private PasswordValidation passwordvalidation;
	
	@ResponseBody
	@GetMapping("/getlogindate")
	public String getlogindate1(HttpSession s, Model m, @RequestParam("uname") String uname)
	{
		System.out.println("in getlogin");
		String getdate = null;
		//System.out.println("addlogicdata:uname.. "+uname);
		try 
		{
			getdate = passwordvalidation.getLoginDate(uname);
			System.out.println("getdate is......."+getdate);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return getdate;
	}
	
	@ResponseBody
	@GetMapping("/checkoldpassword")
	public String checkoldpassword(HttpSession s, Model m, @RequestParam("uname") String uname)
	{
		String getoldpass = null;
		//System.out.println("checkoldpassword:oldpassword.. "+uname);
		try {
			getoldpass = passwordvalidation.checkOldPassword(uname);
			System.out.println("getoldpass is......."+getoldpass);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return getoldpass;
	}
	
	@ResponseBody
	@GetMapping("/updatepassword")
	public String updatepassword(HttpSession s, Model m, @RequestParam("uname") String uname, @RequestParam("oldpassword") String oldpassword, @RequestParam("newpassword") String newpassword, @RequestParam("confirmpassword") String confirmpassword)
	{
		String msg = null;
		//System.out.println("addlogicdata:uname.. "+uname + "  oldpassword........"+oldpassword + "  newpassword..."+newpassword + "  confirmpassword...."+confirmpassword);
		try {
			int updatepass = passwordvalidation.updatePassword(uname,oldpassword,newpassword,confirmpassword);
			//System.out.println("getdate is......."+updatepass);
			if(updatepass > 0) {
				msg = "Password Change Successfully.";
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	
	@ResponseBody
	@GetMapping("/passalreadyexist")
	public String passalreadyexist(HttpSession s, Model m, @RequestParam("uname") String uname, @RequestParam("newpassword") String newpassword)
	{
		String msg = null;
		String updatepass;
		//System.out.println("addlogicdata:uname.. "+uname + "  newpassword..."+newpassword);
		try {
			updatepass = passwordvalidation.passwordExist(uname,newpassword);
			//System.out.println("updatepass is......."+updatepass);
			if(updatepass != null) {
				msg = "Exist";
			}
			else {
				msg = "Not Exist";
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	@ResponseBody
	@GetMapping("/checkpcremaindays")
	public int checkpcremaindays(HttpSession s, Model m, @RequestParam("uname") String uname)
	{
		String getdate = null, remaindaysmsg = null;
		int diffDays = 0;
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		String d=date.toString();
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat format2 = new SimpleDateFormat("dd MM yyyy");
		
		try {
			getdate = passwordvalidation.getLoginDate(uname);
		    System.out.println("getdate is......."+getdate);
			
			Date date1 = null, date2 = null;
			try {
				if(d!=null)
				{
					date1 = format1.parse(d);
				}
				if(getdate!=null)
				{
					date2 = format1.parse(getdate);
				}
				
			}
			catch (ParseException e) 
			{
				e.printStackTrace();
			}
			
			String getdate1 = null, todaysdate = null;
			if(date1!=null)
			{
				getdate1 = format2.format(date1);
			}
			if(date2!=null)
			{
				todaysdate = format2.format(date2);
			}
			
			Date dateObj1 = null, dateObj2 = null;
			try {
				if(getdate1!=null){
					dateObj1 = format2.parse(getdate1);
				}
				if(todaysdate!=null){
					dateObj2 = format2.parse(todaysdate);
				}
			} 
			catch (ParseException e) {
				e.printStackTrace();
			}
			
			
			long diff = 0;
			if(dateObj2!=null && dateObj1!=null)
			{
				diff = dateObj2.getTime() - dateObj1.getTime();
			}
			
			diffDays = (int) (diff / (24 * 60 * 60 * 1000));
			//System.out.println("checkpcremaindays:uname.. "+uname+ "    date is..... "+d+ "   diffDays...."+diffDays);
			
			//remaindaysmsg = passwordvalidation.checkRemainDays(diffDays);
			//System.out.println("remaindaysmsg........ "+remaindaysmsg);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return diffDays;
	}
	
	
}
