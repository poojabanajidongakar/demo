package com.prodata.spsv1.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface MetaDataService {
	//public Map<String, Object> MandateDbToExcel() throws FileNotFoundException, IOException;
	
	/*----------------------------------------------------------------------------*/
	public Map<String,Object> prediscreDbToExcel(String sdate,String edate) throws FileNotFoundException, IOException;
	public Map<String,Object> DbToExcel(String sdate,String edate) throws FileNotFoundException, IOException;
	public Map<String,Object> DataEntryDbToExcel() throws FileNotFoundException, IOException;
	public Map<String, Object> MandateDbToExcel(String sdate,String edate) throws FileNotFoundException, IOException;
	
	
}
