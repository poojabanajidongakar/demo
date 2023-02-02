package com.prodata.spsv1.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Service;

import com.prodata.spsv1.repository.MetadataRepository;
import com.prodata.spsv1.service.MetaDataService;



@Service
public class MetaDataServiceImpl implements MetaDataService {
	
	@Autowired
	private MetadataRepository metadataRepository;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> MandateDbToExcel(String sdate, String edate) throws FileNotFoundException, IOException {
		return metadataRepository.mandateDbToExcel(sdate, edate);
		
	}

	@Override
	public Map<String, Object> prediscreDbToExcel(String sdate, String edate)
			throws FileNotFoundException, IOException {
		return metadataRepository.mandateDbToExcel(sdate, edate);
	
	}
	
		
}


