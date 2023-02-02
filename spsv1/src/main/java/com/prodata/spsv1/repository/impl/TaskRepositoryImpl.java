package com.prodata.spsv1.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.prodata.spsv1.repository.TaskRepository;

@Repository
public class TaskRepositoryImpl implements TaskRepository {

	@Autowired
	private JdbcTemplate JdbcTemplate;
	
	/*@Override
	public int assignTask(String employee, String assignee, int task,String paymentmode,String OneRsType, String amtrange,double amount) 
	{
		if(OneRsType==null && paymentmode==null){
			int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y')",assignee,employee);
			System.out.println("update zala");
			return li;
		}
		
		if((paymentmode.equals(null) || paymentmode!=null) && (OneRsType!=null || OneRsType.equals(null))) {
			int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y')",assignee,employee);
			System.out.println("update zala");
			return li;
		}
		
		if(OneRsType!=null && paymentmode!=null){
			int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,OneRsType);
			System.out.println("update zala");
			return li;
		}
		
		if(!paymentmode.equals(null) && paymentmode.equals("Direct Credit")&& OneRsType!=null )
		{
			if(amtrange.equals("gfive")){
				//	int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [FinalRemarkOners]=?)",assignee,employee,OneRsType);
					int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and PAYAMT >= ? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,"500000",OneRsType);
					
					return li;
							
			}
			else if(amtrange.equals("betofi")){
				int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and PAYAMT >= and PAYAMT < ? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,"200000","500000",OneRsType);
				
				return li;
			}
			else if(amtrange.equals("lessTwo")){
				int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and PAYAMT < ? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,"200000",OneRsType);
				
				return li;
			}
		}
		if(!paymentmode.equals(null) && paymentmode.equals("RTGS")&& OneRsType!=null)
		{
			if(amtrange.equals("gfive")){
				
					int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and PAYAMT >= ? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,"500000",OneRsType);
					
					return li;
							
			}
			else if(amtrange.equals("betofi")){
				int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and PAYAMT >= and PAYAMT < ? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,"200000","500000",OneRsType);
				
				return li;
			}
			else if(amtrange.equals("lessTwo")){
				int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and  PAYAMT < ? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,"200000",OneRsType);
				
				return li;
			}
		}
		-------------------------------------------------------------------
		if(!paymentmode.equals(null) && paymentmode.equals("NEFT")&& OneRsType!=null)
		{
			if(amtrange.equals("gfive")){
				
					int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and PAYAMT >= ? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,"500000",OneRsType);
					
					return li;
							
			}
			else if(amtrange.equals("betofi")){
				int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and PAYAMT >= and PAYAMT < ? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,"200000","500000",OneRsType);
				
				return li;
			}
			else if(amtrange.equals("lessTwo")){
				int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and  PAYAMT < ? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,"200000",OneRsType);
				
				return li;
			}
		}
		-------------------------------------------------------------------
		if(!paymentmode.equals(null) && paymentmode.equals("G")&& OneRsType!=null)
		{
			if(amtrange.equals("gfive")){
				
					int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and PAYAMT >= ? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,"500000",OneRsType);
					
					return li;
							
			}
			else if(amtrange.equals("betofi")){
				int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and PAYAMT >= and PAYAMT < ? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,"200000","500000",OneRsType);
				
				return li;
			}
			else if(amtrange.equals("lessTwo")){
				int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and  PAYAMT < ? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,"200000",OneRsType);
				
				return li;
			}
		}
		if(!paymentmode.equals(null) && paymentmode.equals("F")&& OneRsType!=null)
		{
			if(amtrange.equals("gfive")){
				
					int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and PAYAMT >= ? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,"500000",OneRsType);
					
					return li;
							
			}
			else if(amtrange.equals("betofi")){
				int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and PAYAMT >= and PAYAMT < ? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,"200000","500000",OneRsType);
				
				return li;
			}
			else if(amtrange.equals("lessTwo")){
				int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and  PAYAMT < ? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,"200000",OneRsType);
				
				return li;
			}
		}
		if(!paymentmode.equals(null) && paymentmode.equals("ECS")&& OneRsType!=null)
		{
			if(amtrange.equals("gfive")){
				
					int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and PAYAMT >= ? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,"500000",OneRsType);
					
					return li;
							
			}
			else if(amtrange.equals("betofi")){
				int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and PAYAMT >= and PAYAMT < ? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,"200000","500000",OneRsType);
				
				return li;
			}
			else if(amtrange.equals("lessTwo")){
				int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and PAYAMT < ? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,"200000",OneRsType);
				
				return li;
			}
		}
		return 0;
	}*/

	
	@Override
	public int assignTask(String employee, String assignee, int task,String paymentmode,String OneRsType) {
		/*if(paymentmode==null || OneRsType==null){
			try {
				int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y')",assignee,employee);
				System.out.println("update zala");
				return li;
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
		try {
			int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,OneRsType);
			System.out.println("update zala");
			return li;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}*/
		if(OneRsType!=null && paymentmode!=null){
			int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=? and [FinalRemarkOners]=?)",assignee,employee,paymentmode,OneRsType);
			System.out.println("update zala");
			return li;
		}
		else if(paymentmode!=null){
			int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [REQNTYPE]=?)",assignee,employee,paymentmode);
			System.out.println("update zala");
			return li;
		}
		else if(OneRsType!=null){
			int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y' and [FinalRemarkOners]=?)",assignee,employee,OneRsType);
			System.out.println("update zala");
			return li;
		}
		else {
			int li=JdbcTemplate.update("update tbl_samplingInput set assign_to= ? where REQNNO in(select top "+task+" REQNNO from tbl_samplingInput where assign_to= ? AND flag='Y')",assignee,employee);
			System.out.println("update zala");
			return li;
		}
	}
	
	
	@Override
	public int deleteupload() {
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		String d=date.toString();
		try {
			int i = JdbcTemplate.update("delete from tbl_samplingInput where Date = ? and assign_to = ? and flag = ?",d,"bhartij","Y");
			//System.out.println("deleteupload................. "+i);
			return i;
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	

}
