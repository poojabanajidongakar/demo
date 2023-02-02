package com.prodata.spsv1.repository.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.prodata.spsv1.repository.ChequeRepository;

@Repository
public class Execute {

	@Autowired
	ExcelToDb excelToDb;

	@Autowired
	private ChequeRepository chequeRepository;

	public int rename() {

		System.out.println("in rename()");
		long millis = System.currentTimeMillis();
		java.sql.Date date = new java.sql.Date(millis);
		System.out.println(date);
		final String CONVENTION = date.toString();
		File directory = new File("//pdsql/upload/sps/spsv1/");
		// File directory = new File("D://Upload/");
		String[] n = directory.list();
		File file = null;
		for (String string : n) {
			System.out.println(string + " heheheh");
			// Read only if file not already read
			if (string.contains(CONVENTION) && string.contains("spsv")) {
				System.out.println("in if........");
				// upload logic

				file = new File(directory, string);

				if (file.exists()) {
					System.out.println("file exisits");
					/*
					 * Vector dataHolder=excelToDb.read("//pdserver/upload/sps/spsv1/"+string);
					 * System.out.println("vactor is........."+dataHolder);
					 * if(dataHolder.isEmpty()||dataHolder==null){ return 0; }
					 */
					// excelToDb.saveToDatabase(dataHolder);

					boolean f = chequeRepository.insertExcelData(string);

					// File fileRename = new
					// File(directory,string+CONVENTION+string.substring(string.lastIndexOf(".")));
					File fileRename = new File(directory, string.replace(date.toString(), "_done"));
					// rename file to our convention
					file.renameTo(fileRename);
					System.out.println("its there");
					if (f == true) {
						return 1;
					}

					return 0;
				}
			}

		}
		return 0;

	}
}