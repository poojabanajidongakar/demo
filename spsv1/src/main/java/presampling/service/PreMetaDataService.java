package presampling.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface PreMetaDataService {
	//public Map<String, Object> MandateDbToExcel() throws FileNotFoundException, IOException;
	
	/*----------------------------------------------------------------------------*/
	
	public Map<String,Object> DbToExcel(String sdate,String edate) throws FileNotFoundException, IOException;
	public Map<String,Object> DataEntryDbToExcel() throws FileNotFoundException, IOException;
	public Map<String, Object> MandateDbToExcel(String sdate,String edate) throws FileNotFoundException, IOException;
	
	
}
