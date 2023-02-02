package com.prodata.spsv1.repository.impl;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.prodata.spsv1.repository.HoldfileRepo;

@Service
public class Holdimpl {
	@Autowired
	public HoldfileRepo HoldFile;

	@Autowired
	public JdbcTemplate jdTemplate;

	public int HoldFileprocess() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String today = formatter.format(date);
		File directory = new File("//192.168.6.211/Upload/sps/HoldFile/input/HoldFile");
		// File directory = new File("C:/Shubham/Hold Automation/Hold File
		// Automation/Input/Hold file");
		String[] n = directory.list();

		File file = null;
		int flag = 0;
		for (String string : n) {
			if (string.contains("HOLD_DATA") && string.contains(today)) {
				jdTemplate.update("truncate table [spsv1].[dbo].[HOLD_DATA]");
				file = new File(directory, string);
				if (file.exists()) {
					try {
						boolean f = HoldFile.uplHoldFile(string);
						int c = jdTemplate.queryForObject("select count(*) from [spsv1].[dbo].[HOLD_DATA]",
								Integer.class);
						if (c > 0) {
							File fileRename = new File(directory, string.replace(today, "_done"));
							file.renameTo(fileRename);
							flag = 1;
						}
					} catch (Exception e) {
						flag = 0;
						e.printStackTrace();
					}
				}
			} else if (string.contains("Express Data") && string.contains(today)) {
				jdTemplate.update("truncate table [spsv1].[dbo].[Express_Data]");
				file = new File(directory, string);
				if (file.exists()) {
					try {
						boolean f = HoldFile.uplexpress_data(string);
						int c = jdTemplate.queryForObject("select count(*) from [spsv1].[dbo].[Express_Data]",
								Integer.class);
						if (c > 0) {
							File fileRename = new File(directory, string.replace(today, "_done"));
							file.renameTo(fileRename);
							flag = 1;
						}
					} catch (Exception e) {
						flag = 0;
						e.printStackTrace();
					}
				}
			} else if (string.contains("DOR_DATA") && string.contains(today)) {
				jdTemplate.update("truncate table [spsv1].[dbo].[DOR_DATA]");
				file = new File(directory, string);
				if (file.exists()) {
					try {
						boolean f = HoldFile.upldor_data(string);
						int c = jdTemplate.queryForObject("select count(*) from [spsv1].[dbo].[DOR_DATA]",
								Integer.class);
						if (c > 0) {
							File fileRename = new File(directory, string.replace(today, "_done"));
							file.renameTo(fileRename);
							flag = 1;
						}
					} catch (Exception e) {
						flag = 0;
						e.printStackTrace();
					}
				}
			}
		}
		return flag;
	}

	public int uplFixedFile() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String today = formatter.format(date);
		File directory = new File("//192.168.6.211/Upload/sps/HoldFile/input/FixedFile");
		// File directory = new File("C:/Shubham/Hold Automation/Hold File
		// Automation/Input/Other file");
		String[] n = directory.list();
		File file = null;
		int flag = 0;
		for (String string : n) {
			if (string.contains("Wrong call log data") && string.contains(today)) {
				jdTemplate.update("truncate table [spsv1].[dbo].[Wrong_call_log_data]");
				file = new File(directory, string);
				if (file.exists()) {
					try {
						boolean f = HoldFile.uplwrongcalllog(string);
						int c = jdTemplate.queryForObject("select count(*) from [spsv1].[dbo].[Wrong_call_log_data]",
								Integer.class);
						if (c > 0) {
							File fileRename = new File(directory, string.replace(today, "_done"));
							file.renameTo(fileRename);
							flag = 1;
						}
					} catch (Exception e) {
						flag = 0;
						e.printStackTrace();
					}
				}
			} else if (string.contains("Call log CTST Format") && string.contains(today)) {
				jdTemplate.update("truncate table [spsv1].[dbo].[Call_log_CTST_Format]");
				file = new File(directory, string);
				if (file.exists()) {
					try {
						boolean f = HoldFile.upllogCTSTFormat(string);
						int c = jdTemplate.queryForObject("select count(*) from [spsv1].[dbo].[Call_log_CTST_Format]",
								Integer.class);
						if (c > 0) {
							File fileRename = new File(directory, string.replace(today, "_done"));
							file.renameTo(fileRename);
							flag = 1;
						}
					} catch (Exception e) {
						flag = 0;
						e.printStackTrace();

					}
				}
			} else if (string.contains("Emp List_Hold_call_Version2.") && string.contains(today)) {
				jdTemplate.update("truncate table [spsv1].[dbo].[Emp_List_Hold_call_Version2]");
				file = new File(directory, string);
				if (file.exists()) {
					try {
						boolean f = HoldFile.uplversion2(string);
						int c = jdTemplate.queryForObject(
								"select count(*) from [spsv1].[dbo].[Emp_List_Hold_call_Version2]", Integer.class);
						if (c > 0) {
							File fileRename = new File(directory, string.replace(today, "_done"));
							file.renameTo(fileRename);
							flag = 1;
						}
					} catch (Exception e) {
						flag = 0;
						e.printStackTrace();
					}
				}
			} else if (string.contains("Ownership_department") && string.contains(today)) {
				jdTemplate.update("truncate table [spsv1].[dbo].[Ownership_department]");
				file = new File(directory, string);
				if (file.exists()) {
					try {
						boolean f = HoldFile.uplOwnership_department(string);
						int c = jdTemplate.queryForObject("select count(*) from [spsv1].[dbo].[Ownership_department]",
								Integer.class);
						if (c > 0) {
							File fileRename = new File(directory, string.replace(today, "_done"));
							file.renameTo(fileRename);
							flag = 1;
						}
					} catch (Exception e) {
						flag = 0;
						e.printStackTrace();
					}
				}
			} else if (string.contains("Ownership_and_department") && string.contains(today)) {
				jdTemplate.update("truncate table [spsv1].[dbo].[Ownership_and_department]");
				file = new File(directory, string);
				if (file.exists()) {
					try {
						boolean f = HoldFile.uplOwnership_and_department(string);
						int c = jdTemplate.queryForObject(
								"select count(*) from [spsv1].[dbo].[Ownership_and_department]", Integer.class);
						if (c > 0) {
							File fileRename = new File(directory, string.replace(today, "_done"));
							file.renameTo(fileRename);
							flag = 1;
						}
					} catch (Exception e) {
						flag = 0;
						e.printStackTrace();
					}
				}
			} else if (string.contains("Glenys query Receivable Approval") && string.contains(today)) {
				jdTemplate.update("truncate table [spsv1].[dbo].[Glenys_query]");
				file = new File(directory, string);
				if (file.exists()) {
					try {
						boolean f = HoldFile.uplGlenysquery(string);
						int c = jdTemplate.queryForObject("select count(*) from [spsv1].[dbo].[Glenys_query]",
								Integer.class);
						if (c > 0) {
							File fileRename = new File(directory, string.replace(today, "_done"));
							file.renameTo(fileRename);
							flag = 1;
						}
					} catch (Exception e) {
						flag = 0;
						e.printStackTrace();
					}
				}
			}
		}
		return flag;
	}
}
