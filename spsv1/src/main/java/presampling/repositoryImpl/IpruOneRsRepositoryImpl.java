package presampling.repositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import presampling.repository.Ipruupload;

@Repository
public class IpruOneRsRepositoryImpl implements Ipruupload {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean removesplcharForCname() 
	{

		
		/*IPRU name special character remove*/
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [ipru name] = replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace([ipru name], '!', ''), '@', ''), '#', ''), '$', ''), '%', ''), '^', '') , '&', ''), '*', ''), '(', ''), ')', ''), '.', ''), ',', ''), '/', ''), '?', ''), ':', ''), ';', ''), ':', '') , '-', ''), '+', ''), '=', ''), '{', ''), '}', ''), '.....', ''), '..', ''), '...', ''), '....', '')");
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [ipru name] = SUBSTRING([ipru name],7,LEN([ipru name])-1) where ([ipru name] like 'MS MRS%')");
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [ipru name] = SUBSTRING([ipru name],6,LEN([ipru name])-1) where ([ipru name] like 'MS MS%' or [ipru name] like 'MR MR%')");
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [ipru name] = SUBSTRING([ipru name],4,LEN([ipru name])-1) where ([ipru name] like 'mrs%')");
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [ipru name] = SUBSTRING([ipru name],3,LEN([ipru name])-1) where ([ipru name] like 'mr%' OR [ipru name] like 'ms%')");
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [ipru name] = SUBSTRING([ipru name],5,LEN([ipru name])-1) where ([ipru name] like 'miss%')");
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [ipru name] = replace([ipru name], ' ', '')");
		
		
		/*SapFinalName name special character remove*/
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [SapNameFinal] = replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace([SapNameFinal], '!', ''), '@', ''), '#', ''), '$', ''), '%', ''), '^', '') , '&', ''), '*', ''), '(', ''), ')', ''), '.', ''), ',', ''), '/', ''), '?', ''), ':', ''), ';', ''), ':', '') , '-', ''), '+', ''), '=', ''), '{', ''), '}', ''), '.....', ''), '..', ''), '...', ''), '....', '')");
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [SapNameFinal] = SUBSTRING([SapNameFinal],7,LEN([SapNameFinal])-1) where ([SapNameFinal] like 'MS MRS%')");
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [SapNameFinal] = SUBSTRING([SapNameFinal],6,LEN([SapNameFinal])-1) where ([SapNameFinal] like 'MS MS%' or [SapNameFinal] like 'MR MR%')");
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [SapNameFinal] = SUBSTRING([SapNameFinal],4,LEN([SapNameFinal])-1) where ([SapNameFinal] like 'mrs%')");
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [SapNameFinal] = SUBSTRING([SapNameFinal],3,LEN([SapNameFinal])-1) where ([SapNameFinal] like 'mr%' OR [SapNameFinal] like 'ms%')");
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [SapNameFinal] = SUBSTRING([SapNameFinal],5,LEN([SapNameFinal])-1) where ([SapNameFinal] like 'miss%')");
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [SapNameFinal] = replace([SapNameFinal], ' ', '')");
		
		/*SapFinalName_reverse name special character remove*/
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [SapNameFinal_reverse] = replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace(replace([SapNameFinal_reverse], '!', ''), '@', ''), '#', ''), '$', ''), '%', ''), '^', '') , '&', ''), '*', ''), '(', ''), ')', ''), '.', ''), ',', ''), '/', ''), '?', ''), ':', ''), ';', ''), ':', '') , '-', ''), '+', ''), '=', ''), '{', ''), '}', ''), '.....', ''), '..', ''), '...', ''), '....', '')");
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [SapNameFinal_reverse] = SUBSTRING([SapNameFinal_reverse],7,LEN([SapNameFinal_reverse])-1) where ([SapNameFinal_reverse] like 'MS MRS%')");
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [SapNameFinal_reverse] = SUBSTRING([SapNameFinal_reverse],6,LEN([SapNameFinal_reverse])-1) where ([SapNameFinal_reverse] like 'MS MS%' or [SapNameFinal_reverse] like 'MR MR%')");
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [SapNameFinal_reverse] = SUBSTRING([SapNameFinal_reverse],4,LEN([SapNameFinal_reverse])-1) where ([SapNameFinal_reverse] like 'mrs%')");
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [SapNameFinal_reverse] = SUBSTRING([SapNameFinal_reverse],3,LEN([SapNameFinal_reverse])-1) where ([SapNameFinal_reverse] like 'mr%' OR [SapNameFinal_reverse] like 'ms%')");
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [SapNameFinal_reverse] = SUBSTRING([SapNameFinal_reverse],5,LEN([SapNameFinal_reverse])-1) where ([SapNameFinal_reverse] like 'miss%')");
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [SapNameFinal_reverse] = replace([SapNameFinal_reverse], ' ', '')");
		
		return true;
	  }
	
	@Override
	public boolean CustTF()
	{
		jdbcTemplate.update("update a set a.[t/f name]='True' from [spsv1].[dbo].[tbl_responseInput] a where a.[SapNameFinal]=a.[ipru name]");
		jdbcTemplate.update("update a set a.[t/f name]='False' from [spsv1].[dbo].[tbl_responseInput] a where a.[t/f name] Is Null");
		
		jdbcTemplate.update("update a set a.[t/f name_revrse]='True' from [spsv1].[dbo].[tbl_responseInput] a where a.[SapNameFinal_reverse]=a.[ipru name]");
		jdbcTemplate.update("update a set a.[t/f name_revrse]='False' from [spsv1].[dbo].[tbl_responseInput] a where a.[t/f name_revrse] Is Null");
		
		jdbcTemplate.update("update a set a.[t/f policy]='True' from [spsv1].[dbo].[tbl_responseInput] a where a.[SAP policy]=a.[Policy No.]");
		jdbcTemplate.update("update a set a.[t/f policy]='False'from [spsv1].[dbo].[tbl_responseInput] a where a.[t/f policy] Is Null");
		
		return true;
	}
	
	@Override
	public boolean setFinalRemark()
	{
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [Final_remark]='Need To Check' where [t/f policy]='True' and ([t/f name]='False' and [t/f name_revrse]='False') and [CURRENT_STATUS]='Paid'");
		
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [Final_remark]='Ok To Process' where ([t/f name]='True' and [t/f name_revrse]='False') and [t/f policy]='True' and [CURRENT_STATUS]='Paid' and [Final_remark] is null");
		
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [Final_remark]='Ok To Process' where ([t/f name]='False' and [t/f name_revrse]='True') and [t/f policy]='True' and [CURRENT_STATUS]='Paid' and [Final_remark] is null");
		
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [Final_remark]='Ok To Process' where ([t/f name]='True' and [t/f name_revrse]='True') and [t/f policy]='True' and [CURRENT_STATUS]='Paid' and [Final_remark] is null");
		
		jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [Final_remark]='Need To Check' where [CURRENT_STATUS]='Cancelled' and [Final_remark] is null");
		return true;
	}
	
	@Override
	public boolean reverseNameMatching()
	{
	jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [Final_remark] ='Partial Ok' WHERE ((SUBSTRING([SapNameFinal],LEN([SapNameFinal])-5,LEN([SapNameFinal])) = SUBSTRING([ipru name],LEN([ipru name])-5,LEN([ipru name]))or SUBSTRING([SapNameFinal],0,7) = SUBSTRING([ipru name],0,7) or SUBSTRING([ipru name],0,LEN([ipru name])/2) = SUBSTRING([SapNameFinal],0,LEN([SapNameFinal])/2)) or (SUBSTRING([SapNameFinal_reverse],LEN([SapNameFinal_reverse])-5,LEN([SapNameFinal_reverse])) = SUBSTRING([ipru name],LEN([ipru name])-5,LEN([ipru name]))or SUBSTRING([SapNameFinal_reverse],0,7) = SUBSTRING([ipru name],0,7) or SUBSTRING([ipru name],0,LEN([ipru name])/2) = SUBSTRING([SapNameFinal_reverse],0,LEN([SapNameFinal_reverse])/2))) and [t/f name]='False' and [t/f name_revrse]='False' and [CURRENT_STATUS]='Paid'");
	jdbcTemplate.update("update [spsv1].[dbo].[tbl_responseInput] set [Final_remark] ='Reverse-Need to check' WHERE ((SUBSTRING([SapNameFinal],LEN([SapNameFinal])-5,LEN([SapNameFinal])) <> SUBSTRING([ipru name],LEN([ipru name])-5,LEN([ipru name]))and SUBSTRING([SapNameFinal],0,7) <> SUBSTRING([ipru name],0,7) and SUBSTRING([ipru name],0,LEN([ipru name])/2) <> SUBSTRING([SapNameFinal],0,LEN([SapNameFinal])/2)) or (SUBSTRING([SapNameFinal_reverse],LEN([SapNameFinal_reverse])-5,LEN([SapNameFinal_reverse])) <> SUBSTRING([ipru name],LEN([ipru name])-5,LEN([ipru name]))and SUBSTRING([SapNameFinal_reverse],0,7) <> SUBSTRING([ipru name],0,7) and SUBSTRING([ipru name],0,LEN([ipru name])/2) <> SUBSTRING([SapNameFinal_reverse],0,LEN([SapNameFinal_reverse])/2))) and [t/f name]='False' and [t/f name_revrse]='False' and [Final_remark]='Need To Check' and [CURRENT_STATUS]='Paid'");
	
	return true;
	}

	@Override
	public boolean sapNameConcat() {
		int move = 0;
		int setflag = 0;
	int a=jdbcTemplate.update("UPDATE [spsv1].[dbo].[tbl_Sap1] SET [Customer Name] = CONVERT(VARCHAR(255),[Surname])+ ' ' + CONVERT(VARCHAR(255),[Given Names]) where Salutation IS NULL");
	int b=jdbcTemplate.update("UPDATE [spsv1].[dbo].[tbl_Sap1] SET [Customer Name] = CONVERT(VARCHAR(255),Salutation)+ ' ' + CONVERT(VARCHAR(255),[Surname]) where [Given Names] IS NULL");
	int c=jdbcTemplate.update("UPDATE [spsv1].[dbo].[tbl_Sap1] SET [Customer Name] = CONVERT(VARCHAR(255),Salutation)+ ' ' + CONVERT(VARCHAR(255),[Given Names]) where [Surname] IS NULL");
	int d=jdbcTemplate.update("UPDATE [spsv1].[dbo].[tbl_Sap1] SET [Customer Name] = [Surname] where Salutation IS NULL and [Given Names] IS NULL");
	int e=jdbcTemplate.update("UPDATE [spsv1].[dbo].[tbl_Sap1] SET [Customer Name] = [Given Names] where Salutation IS NULL and [Surname] IS NULL");
	int f=jdbcTemplate.update("UPDATE [spsv1].[dbo].[tbl_Sap1] SET [Customer Name] = Salutation where [Given Names] IS NULL and [Surname] IS NULL");
	int g=jdbcTemplate.update("UPDATE [spsv1].[dbo].[tbl_Sap1] SET [Customer Name] = CONVERT(VARCHAR(255),Salutation)+ ' ' + CONVERT(VARCHAR(255),[Surname])+ ' ' + CONVERT(VARCHAR(255),[Given Names]) where Salutation IS NOT NULL and [Given Names] IS NOT NULL and [Surname] IS NOT NULL");
	int h=jdbcTemplate.update("UPDATE [spsv1].[dbo].[tbl_Sap1] SET [reverse_Customer Name] = CONVERT(VARCHAR(255),Salutation)+ ' ' + CONVERT(VARCHAR(255),[Given Names])+ ' ' + CONVERT(VARCHAR(255),[Surname]) where Salutation IS NOT NULL and [Given Names] IS NOT NULL and [Surname] IS NOT NULL");
	
	System.out.println("sapNameConcat"+a);
	if(a>1 || b>1 || c>1 || d>1 || e>1 || f>1 || g>1)
	{
	
		move=jdbcTemplate.update(" update a set a.[SAP policy]=b.[Contract],a.[Sap name]=b.[Customer Name],a.[SapNameFinal]=b.[Customer Name],a.[reverse_Sap name]=b.[reverse_Customer Name],a.[SapNameFinal_reverse]=b.[reverse_Customer Name] from [spsv1].[dbo].[tbl_responseInput] a, [spsv1].[dbo].[tbl_Sap1] b where a.[Policy No.]=b.[Contract] and a.[SAP policy] is null and a.[Sap name] is null and a.[SapNameFinal] is null and [reverse_Sap name] is null and [SapNameFinal_reverse] is null");
		setflag = jdbcTemplate.update("update [tbl_responseInput] set flag='N' where [CURRENT_STATUS]='Cancelled'");
	System.out.println("sapNameConcat"+setflag);
	}
		

if(move>1)
{
	boolean removesplcharForCname=removesplcharForCname(); // remove spl character
    boolean CustTF = false;
    boolean setFinalRemark = false;
    if(removesplcharForCname==true)
    {
    	System.out.println(removesplcharForCname);
    	CustTF=CustTF(); // true false of customer name
    }
    if(CustTF==true)
    {
    	System.out.println(CustTF);
    	setFinalRemark=setFinalRemark();
    }
    if(setFinalRemark==true)
    {
    	System.out.println(setFinalRemark);
    	boolean reverseNameMatching=reverseNameMatching(); //reverse matching
    }
}
	
		return true;
		
	}

	@Override
	public boolean upadteremark()
	{
	    jdbcTemplate.update("update a set a.Remarks=b.[Remarks] from tbl_responseInput a ,tbl_CancelRemark b where a.Remarks=b.[Subtype] and a.CURRENT_STATUS='Cancelled'");
		return true;
	}
	
	
	
	
	
}
