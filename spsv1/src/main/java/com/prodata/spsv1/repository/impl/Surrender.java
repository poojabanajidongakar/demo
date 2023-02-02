package com.prodata.spsv1.repository.impl;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.prodata.spsv1.repository.Surrenderpi;

@Service

public class Surrender {

	@Autowired
	public Surrender surrender;

	@Autowired
	public Surrenderpi surrenderpiinterest;
	
	@Autowired
	public JdbcTemplate jdTemplate;
	
	public int remarkupl() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String today = formatter.format(date);
		// File directory = new
		// File("//192.168.6.211/Upload/sps/HoldFile/input/HoldFile");
		File directory = new File("//192.168.6.211/Upload/sps/SurrenderPi-interest");
		String[] n = directory.list();
		File file = null;
		int flag = 0;
		for (String string : n) {
			if (string.contains("Remarks") && string.contains(today)) {
				jdTemplate.update("truncate table [spsv1].[dbo].[Remark]");
				file = new File(directory, string);
				if (file.exists()) {
					try {
						boolean f = surrenderpiinterest.uplremark(string);
						int c = jdTemplate.queryForObject("select count(*) from [spsv1].[dbo].[Remark]",
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

	public int renameelectronic() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String today = formatter.format(date);
		// File directory = new
		// File("//192.168.6.211/Upload/sps/HoldFile/input/HoldFile");
		File directory = new File("//192.168.6.211/Upload/sps/SurrenderPi-interest/Electronic_tot");
		String[] n = directory.list();
		File file = null;
		int flag = 0;
		for (String string : n) {
			if (string.contains("TOT Electronic") && string.contains(today)) {
				/* jdTemplate.update("truncate table [spsv1].[dbo].[Remark]"); */
				file = new File(directory, string);
				if (file.exists()) {
					try {
						boolean f = surrenderpiinterest.uplelectronictot(string);
						int c = jdTemplate.queryForObject("select count(*) from [spsv1].[dbo].[TOT_upload]",
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

	public int renamechequetot() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String today = formatter.format(date);
		// File directory = new
		// File("//192.168.6.211/Upload/sps/HoldFile/input/HoldFile");
		File directory = new File("//192.168.6.211/Upload/sps/SurrenderPi-interest/Cheque_Tot");
		String[] n = directory.list();
		File file = null;
		int flag = 0;
		for (String string : n) {
			if (string.contains("Cheque_TOT") && string.contains(today)) {
				/* jdTemplate.update("truncate table [spsv1].[dbo].[Remark]"); */
				file = new File(directory, string);
				if (file.exists()) {
					try {
						boolean f = surrenderpiinterest.uplchequetot(string);
						int c = jdTemplate.queryForObject("select count(*) from [spsv1].[dbo].[Cheque_TOTup]",
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

	public int renamesrdata() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String today = formatter.format(date);
		// File directory = new
		// File("//192.168.6.211/Upload/sps/HoldFile/input/HoldFile");
		File directory = new File("//192.168.6.211/Upload/sps/SurrenderPi-interest");
		String[] n = directory.list();
		File file = null;
		int flag = 0;
		for (String string : n) {
			if (string.contains("SR") && string.contains(today)) {
				/* jdTemplate.update("truncate table [spsv1].[dbo].[Remark]"); */
				file = new File(directory, string);
				if (file.exists()) {
					try {
						boolean f = surrenderpiinterest.uplsrdata(string);
						int c = jdTemplate.queryForObject("select count(*) from [spsv1].[dbo].[SR]",
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

	public int renamepenal() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String today = formatter.format(date);
		// File directory = new
		// File("//192.168.6.211/Upload/sps/HoldFile/input/HoldFile");
		File directory = new File("//192.168.6.211/Upload/sps/SurrenderPi-interest");
		System.out.println("go into rename");
		String[] n = directory.list();
		File file = null;
		int flag = 0;
		for (String string : n) {
			System.out.println("waiting for uploding");
			if (string.contains("Penal Interest data") && string.contains(today)) {
				/* jdTemplate.update("truncate table [spsv1].[dbo].[Remark]"); */
				file = new File(directory, string);
				if (file.exists()) {
					try {

						System.out.println("penal");
						boolean f = surrenderpiinterest.uplpenal(string);
						int c = jdTemplate.queryForObject("select count(*) from  [spsv1].[dbo].[PenalInterest]",
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
		for (String string : n) {
			if (string.contains("Penal Interest data") && string.contains(today)) {
				/* jdTemplate.update("truncate table [spsv1].[dbo].[Remark]"); */
				file = new File(directory, string);
				if (file.exists()) {
					try {
						System.out.println("triggered");
						boolean f = surrenderpiinterest.uplpenal(string);
						int c = jdTemplate.queryForObject("select count(*) from [spsv1].[dbo].[triggerupl]",
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

	public int renamevendor() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String today = formatter.format(date);
		// File directory = new
		// File("//192.168.6.211/Upload/sps/HoldFile/input/HoldFile");
		File directory = new File("//192.168.6.211/Upload/sps/SurrenderPi-interest");
		String[] n = directory.list();
		File file = null;
		int flag = 0;
		for (String string : n) {
			if (string.contains("Cordys Vendor Data") && string.contains(today)) {
				/* jdTemplate.update("truncate table [spsv1].[dbo].[Remark]"); */
				file = new File(directory, string);
				if (file.exists()) {
					try {
						boolean f = surrenderpiinterest.uplvendor(string);
						int c = jdTemplate.queryForObject("select count(*) from [spsv1].[dbo].[vendor_data]",
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
