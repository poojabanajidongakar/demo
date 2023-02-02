package presampling.serviceImpl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Service;

import presampling.repository.PreMetadataRepository;
import presampling.service.PreMetaDataService;





@Service
public class PreMetaDataServiceImpl implements PreMetaDataService {
	
	@Autowired
	private PreMetadataRepository metadataRepository;

	/*@Override
	public Map<String, Object> MandateDbToExcel() throws FileNotFoundException, IOException {
		return metadataRepository.mandateDbToExcel();
	}*/

	@Override
	public Map<String, Object> DbToExcel(String sdate, String edate) throws FileNotFoundException, IOException {
		return metadataRepository.dbToExcel(sdate,edate);
	}

	@Override
	public Map<String, Object> DataEntryDbToExcel() throws FileNotFoundException, IOException {
	
		
		return null;
	}

	@Override
	public Map<String, Object> MandateDbToExcel(String sdate, String edate) throws FileNotFoundException, IOException {
		return metadataRepository.mandateDbToExcel(sdate, edate);
		
	}
	
		
}


