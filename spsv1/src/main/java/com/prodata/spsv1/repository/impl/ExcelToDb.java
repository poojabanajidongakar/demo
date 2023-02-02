package com.prodata.spsv1.repository.impl;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ExcelToDb {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
   /* public static void main( String [] args ) {
        String fileName="D://upload/Book7.xlsx";
        Vector dataHolder=read(fileName);
        saveToDatabase(dataHolder);
    }*/
	public  Vector read(String fileName)    {
        Vector cellVectorHolder = new Vector();
        try{
            FileInputStream myInput = new FileInputStream(fileName);
            //POIFSFileSystem myFileSystem = new POIFSFileSystem(myInput);
            XSSFWorkbook myWorkBook = new XSSFWorkbook(myInput);
            XSSFSheet mySheet = myWorkBook.getSheetAt(0);
            Iterator rowIter = mySheet.rowIterator();
            while(rowIter.hasNext()){
                XSSFRow myRow = (XSSFRow) rowIter.next();
                Iterator cellIter = myRow.cellIterator();
                //Vector cellStoreVector=new Vector();
                List list = new ArrayList();
                int i=0;
                while(cellIter.hasNext()){
                	if(myRow.getRowNum()==0){
                		XSSFCell myCell = (XSSFCell) cellIter.next();
                		System.out.println("erg"+i);
                		i++;
                	}
                	else{
                		XSSFCell myCell = (XSSFCell) cellIter.next();
                        list.add(myCell);
                	}
                }
                cellVectorHolder.addElement(list);
            }
        }
            catch (Exception e){e.printStackTrace(); }
        return cellVectorHolder;
    }
    public void saveToDatabase(Vector dataHolder) {
    	String client_id="";
        String prnNo="";
        String amount="";
        String payment_mode="";
        String policy="";
        String applicationNo="";
        String initial="";
        String lgivenname="";
        String lsurname="";
        String paymentType="";
        String accountNumber="";
        String ifsc="";
        String nmmicr="";
        String bankName="";
        String crossRefPolicy="";
       
        
        System.out.println(dataHolder);

        for(Iterator iterator = dataHolder.iterator();iterator.hasNext();) {
        	try {
        		long millis=System.currentTimeMillis();  
        		java.sql.Date date=new java.sql.Date(millis);  
        		String d=date.toString();
        		List list = (List) iterator.next();
        		System.out.println("list is.............."+list);
        	client_id = list.get(2).toString(); //2
        	System.out.println("client id..."+client_id);
        	prnNo = list.get(3).toString();//3
        	amount = list.get(5).toString();
        	payment_mode = list.get(6).toString();
        	policy = list.get(7).toString();
        	applicationNo = list.get(8).toString();
        	initial = list.get(9).toString();
        	lgivenname = list.get(10).toString();
        	lsurname = list.get(11).toString();
        	paymentType = list.get(54).toString();
        	accountNumber = list.get(67).toString();
        	ifsc = list.get(68).toString();
        	nmmicr = list.get(69).toString();
        	bankName = list.get(70).toString();
        	crossRefPolicy = list.get(93).toString();
          
        	

            //jdbcTemplate.update("INSERT INTO tbl_inputDetails(CallID,PolicyNo,ClientID,CrossRefPolicy,ApplicationNo,CrossRefApplication,[SPAARC/PAYIN A/C No],IFSC_Code,CustomerName,flag,assign_to,date) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)",CallID,PolicyNo,ClientID,CrossRefPolicy,ApplicationNo,CrossRefApplication,AccountNo,IFSC_Code,CustomerName,"Y","prodata",d);
                jdbcTemplate.update("INSERT INTO tbl_samplingInput(CLNTNUM,REQNNO,PAYAMT,REQNTYPE,CHDRNUM,TTMPRCNO,SALUTL,LGIVNAME,LSURNAME,PYMTSURC,BANKACCKEY,IFSC,BANKKEY,BANKDESC,[Cross Ref Policy No],flag,assign_to,date) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",client_id,prnNo,amount,payment_mode,policy,applicationNo,initial,lgivenname,lsurname,paymentType,accountNumber,ifsc,nmmicr,bankName,crossRefPolicy,"Y","pritamd",d);
                //jdbcTemplate.update("update tbl_chequeinputdtl set flag='Y',assign_to='prodata'");
        	} catch (Exception e) {
                e.printStackTrace();

        }



        }
}
}