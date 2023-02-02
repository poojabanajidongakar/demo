package com.prodata.spsv1.repository.impl;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.prodata.spsv1.repository.RepatletterRepo;

@Service
public class Repatimpl {
	@Autowired
	public RepatletterRepo repatrepo;

	@Autowired
	public JdbcTemplate jdTemplate;

	public int Repatletter() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String today = formatter.format(date);
		// File directory = new
		// File("//192.168.6.211/Upload/sps/HoldFile/input/HoldFile");
		File directory = new File("C:/Shubham/repat");
		String[] n = directory.list();

		File file = null;
		int flag = 0;
		for (String string : n) {
			if (string.contains("Samplingfile") && string.contains(today)) {
				jdTemplate.update("truncate table [spsv1].[dbo].[repatsamplingfile]");
				file = new File(directory, string);
				if (file.exists()) {
					try {
						boolean f = repatrepo.uplsamplingfile(string);
						int c = jdTemplate.queryForObject("select count(*) from [spsv1].[dbo].[repatsamplingfile]",
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
			} else if (string.contains("TOT Electronic") && string.contains(today)) {
				jdTemplate.update("truncate table [spsv1].[dbo].[totrepat]");
				file = new File(directory, string);
				if (file.exists()) {
					try {
						boolean f = repatrepo.upltotfile(string);
						int c = jdTemplate.queryForObject("select count(*) from [spsv1].[dbo].[totrepat]",
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

	public int payoutcollection() {
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String today = formatter.format(date);
		// File directory = new
		// File("//192.168.6.211/Upload/sps/HoldFile/input/HoldFile");
		File directory = new File("C:/Shubham/repat");
		String[] n = directory.list();
		File file = null;
		int flag = 0;
		for (String string : n) {
			if (string.contains("Payout_Collection_Prompt") && string.contains(today)) {
				jdTemplate.update("truncate table [spsv1].[dbo].[payoutcollection]");
				file = new File(directory, string);
				if (file.exists()) {
					try {
						boolean f = repatrepo.uplpayoutcollection(string);
						int c = jdTemplate.queryForObject("select count(*) from [spsv1].[dbo].[payoutcollection]",
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
