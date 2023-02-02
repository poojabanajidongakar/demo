package presampling.repositoryImpl;

import java.io.File;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import presampling.repository.Ipruupload;
import presampling.repository.PreChequeRepository;



@Repository
public class PreExecute {

	@Autowired
	PreExcelToDb excelToDb;
	
	@Autowired
	private PreChequeRepository chequeRepository;
	
	@Autowired
	private Ipruupload ipruupload;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int rename()
	{
		long millis=System.currentTimeMillis();  
		java.sql.Date date=new java.sql.Date(millis);  
		String d=date.toString();
		System.out.println("in rename()");
	 
		final String CONVENTION = date.toString();
		File directory = new File("//pdsql/upload/sps/Presampling/");
		String []n = directory.list();
		File file = null;
		int flag = 0;
		for (String string : n) 
		{
			System.out.println(string+" heheheh");
			//Read only if file not already read
			if(string.contains(CONVENTION) && string.contains("IPLIMPS"))
			{
				//jdbcTemplate.update("insert into [spsv1].[dbo].[tbl_responseInput_bkp]([RRN],[CUSTOMER_CODE],[CUSTOMER_NAME],[PAYMENT_PRODUCT_CODE],[PAYMENT_PRODUCT_DESC],[CUSTOMER_REF_NO],[PAYMENT_VALUE_DATE],[PROCESSING_DATE],[PAYEE_NAME],[INSTRUMENT_AMOUNT],[PAYABLE_LOCATION_NAME],[IFSC_CODE],[PAYMENT_MODE],[DEBIT_ACCOUNT_NO],[CREDIT_ACCOUNT_NO],[ISSUE_BRANCH_CODE],[CURRENT_STAGE],[CURRENT_STATUS],[PAYMENT_REF_NO],[UTR_NO],[Remarks],[Policy No.],[Additional Ref. No.],[month],[Bene_Name],[flag],[todaysdate],[assign_to],[tag],[SAP policy],[Sap name],[reverse_Sap name],[SapNameFinal],[SapNameFinal_reverse],[ipru name],[t/f policy],[t/f name],[t/f name_revrse],[Final_remark],[CheckedName],[reason],[checkerFlag],[checkerAssign_to],[CheckerCheckedName],[CheckerReason],[FinalRemark],[FinalReason],[dataEntryDate]) (select [RRN],[CUSTOMER_CODE],[CUSTOMER_NAME],[PAYMENT_PRODUCT_CODE],[PAYMENT_PRODUCT_DESC],[CUSTOMER_REF_NO],[PAYMENT_VALUE_DATE],[PROCESSING_DATE],[PAYEE_NAME],[INSTRUMENT_AMOUNT],[PAYABLE_LOCATION_NAME],[IFSC_CODE],[PAYMENT_MODE],[DEBIT_ACCOUNT_NO],[CREDIT_ACCOUNT_NO],[ISSUE_BRANCH_CODE],[CURRENT_STAGE],[CURRENT_STATUS],[PAYMENT_REF_NO],[UTR_NO],[Remarks],[Policy No.],[Additional Ref. No.],[month],[Bene_Name],[flag],[todaysdate],[assign_to],[tag],[SAP policy],[Sap name],[reverse_Sap name],[SapNameFinal],[SapNameFinal_reverse],[ipru name],[t/f policy],[t/f name],[t/f name_revrse],[Final_remark],[CheckedName],[reason],[checkerFlag],[checkerAssign_to],[CheckerCheckedName],[CheckerReason],[FinalRemark],[FinalReason],[dataEntryDate] from [spsv1].[dbo].[tbl_responseInput])");
				//jdbcTemplate.update("Truncate table tbl_responseInput");
				System.out.println("in if........");
				//upload logic
				
				file = new File(directory,string);
				
				if(file.exists())
				{
					System.out.println("file exisits");
					
					try {
						boolean f = chequeRepository.insertExcelData(string);
						if(f==true)
						{
							chequeRepository.insertExcelOtherData();
						}
						jdbcTemplate.update("update tbl_responseInput set assign_to=?,checkerAssign_to=?,todaysdate=?,dataEntryDate=?,allocate_date=?,tag='presampling',[checkerFlag]='Y' where todaysdate is NULL","sarveshs","sarveshs",d,d,d);
						
						File fileRename = new File(directory,string.replace(date.toString(), "_done"));
						file.renameTo(fileRename);
						System.out.println("its there");
						flag= 1;
					} 
					catch (Exception e) {
						flag= 0;
					}
					
					
					//return 1;
				}
			}
			
		 if(string.contains(CONVENTION) && string.contains("sap"))
			{
				System.out.println("in if iff........");
				//upload logic
				
				try {
					jdbcTemplate.update("insert into [spsv1].[dbo].[tbl_Sap1_bkp]([Contract],[Sinstamt06],[Client],[Salutation],[Given Names],[Surname],[Current Balance],[todaysdate],[Customer Name],[reverse_Customer Name]) (select [Contract],[Sinstamt06],[Client],[Salutation],[Given Names],[Surname],[Current Balance],[todaysdate],[Customer Name],[reverse_Customer Name] from [spsv1].[dbo].[tbl_Sap1])");
					jdbcTemplate.update("Truncate table tbl_Sap1 ");
					file = new File(directory,string);
					
					if(file.exists())
					{
						System.out.println("file exisits");
						Vector dataHolder = null;
						if(string.contains(".xlsx"))
						{
							dataHolder=excelToDb.read("//pdsql/upload/sps/Presampling/"+string);
						}
						else if(string.contains(".xls"))
						{
							System.out.println(".xls.........");
								dataHolder=excelToDb.read1("//pdsql/upload/sps/Presampling/"+string);
							}
						

						
						//Vector dataHolder=excelToDb.read("//pdsql/upload/sps/Presampling/"+string);
						//System.out.println("vactor is........."+dataHolder);
						if(dataHolder.isEmpty()||dataHolder==null){
							return 0;
						}
						excelToDb.saveToDatabaseSap(dataHolder);
						jdbcTemplate.update("update spsv1.dbo.tbl_Sap1 set todaysdate=? where todaysdate is NULL",d);
						boolean concatName=ipruupload.sapNameConcat();
						//File fileRename = new File(directory,string+CONVENTION+string.substring(string.lastIndexOf(".")));
						File fileRename = new File(directory,string.replace(date.toString(), "_done"));
						//rename file to our convention
						file.renameTo(fileRename);
						System.out.println("its there");
						//return 1;
						flag= 1;
					}
				} 
				catch (Exception e) {
					e.printStackTrace();
					flag= 0;
				}
				
		
			}
			
			
		}
		return flag;
		
	}
}