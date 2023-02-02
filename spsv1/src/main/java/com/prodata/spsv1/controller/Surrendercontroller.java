package com.prodata.spsv1.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prodata.spsv1.model.Holdfilemodel;
import com.prodata.spsv1.model.surrendermodel;
import com.prodata.spsv1.repository.impl.Surrender;
import com.prodata.spsv1.repository.impl.surrenderimpl;

@Controller
@RequestMapping("/Surrenderpi")
public class Surrendercontroller {

	@Autowired
	JdbcTemplate jdbctemplete;

	@Autowired
	Surrender Surrender;
	
	@Autowired
	surrenderimpl surrenderimpl;

	@GetMapping("/repatletter")
	public String paymentRecon(Model m, String uploadmsg, String uploaddelete, String errormsg, String ExportHoldFile) {
		m.addAttribute("uploadmsg", uploadmsg);
		return "user/Surrenderpiinterest";
	}

	@PostMapping("/remark")
	public String repatletter(Model m, RedirectAttributes rdAttributes,
			@RequestParam("Surrenderpiinterest") String upload, HttpSession s) {
		int i;
		if (upload.equalsIgnoreCase("uplremark")) {
			i = Surrender.remarkupl();

			if (i == 1) {
				m.addAttribute("uploadmsg", "Remark File Uploaded Succesfully");
			} else {

				m.addAttribute("uploadmsg", "Please Check the file or sheetname");
			}
		}
		return "user/Surrenderpiinterest";
	}

	@PostMapping("/electronictot")
	public String electronictot(Model m, RedirectAttributes rdAttributes,
			@RequestParam("uplelectronictot") String upload, HttpSession s) {
		int i;
		if (upload.equalsIgnoreCase("uplelectronictot")) {
			i = Surrender.renameelectronic();
			if (i == 1) {
				m.addAttribute("uploadmsg", "Electronic TOT Uploaded Succesfully");
			} else {
				m.addAttribute("uploadmsg", "Please Check the file or sheetname");
			}
		}
		return "user/Surrenderpiinterest";
	}

	@PostMapping("/chequetot")
	public String chequetot(Model m, RedirectAttributes rdAttributes, @RequestParam("uplchequetot") String upload,
			HttpSession s) {
		int i;
		if (upload.equalsIgnoreCase("uplchequetot")) {
			i = Surrender.renamechequetot();
			if (i == 1) {
				m.addAttribute("uploadmsg", "Cheque TOT Uploaded Succesfully");
			} else {
				m.addAttribute("uploadmsg", "Please Check the file or sheetname");
			}
		}
		return "user/Surrenderpiinterest";
	}

	@PostMapping("/SRdata")
	public String SRdata(Model m, RedirectAttributes rdAttributes, @RequestParam("uplsrdata") String upload,
			HttpSession s) {
		int i;
		if (upload.equalsIgnoreCase("uplsrdata")) {
			i = Surrender.renamesrdata();
			if (i == 1) {
				m.addAttribute("uploadmsg", "SR DATA FILE Uploaded Succesfully");
			} else {
				m.addAttribute("uploadmsg", "Please Check the file or sheetname");
			}
		}
		return "user/Surrenderpiinterest";
	}

	@PostMapping("/Penal")
	public String Penalinterest(Model m, RedirectAttributes rdAttributes, @RequestParam("uplpenal") String upload,
			HttpSession s) {
		int i;
		if (upload.equalsIgnoreCase("uplpenal")) {
			System.out.println("penal uploaded");
			i = Surrender.renamepenal();
			System.out.println("penal is  uploaded");
			if (i == 1) {
				m.addAttribute("uploadmsg", "PENAL INTEREST	FILE Uploaded Succesfully");
			} else {
				m.addAttribute("uploadmsg", "Please Check the file or sheetname");
			}
		}
		return "user/Surrenderpiinterest";
	}

	@PostMapping("/exe")
	public String exe(Model model, RedirectAttributes attributes, HttpSession s,
			@RequestParam("excute") String excute) throws FileNotFoundException, IOException {

		surrenderimpl.execute(excute);
		return "user/Surrenderpiinterest";

	}
	
	@PostMapping("/vendor")
	public String Vendor(Model m, RedirectAttributes rdAttributes, @RequestParam("uplvendor") String upload,
			HttpSession s) {
		int i;
		if (upload.equalsIgnoreCase("uplvendor")) {
			i = Surrender.renamevendor();
			if (i == 1) {
				m.addAttribute("uploadmsg", "VENDOR FILE Uploaded Succesfully");
			} else {
				m.addAttribute("uploadmsg", "Please Check the file or sheetname");
			}
		}
		return "user/Surrenderpiinterest";
	}
	
	@GetMapping("/exportsurrender")
	public String exportsurrender(Model model, RedirectAttributes attributes, HttpSession s)
			throws FileNotFoundException, IOException {
		System.out.println("new");
		Date date = Calendar.getInstance().getTime();
		DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		String today = formatter.format(date);

		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet("Surrender_Pi_Interest");

		Font headerFont = workbook.createFont();
		headerFont.setFontHeightInPoints((short) 14);
		CellStyle headerCellStyle = workbook.createCellStyle();

		String columns[] = new String[30];
		columns[0] = "Callid";
		columns[1] = "Policyno";
		columns[2] = "Owner";
		columns[3] = "Calltype";
		columns[4] = "Subtype";
		columns[5] = "Recvd_D";
		columns[6] = "Transaction_Date";
		columns[7] = "Closeddate";
		columns[8] = "Nri";
		columns[9] = "Prodcode";
		columns[10] = "Callcategory";
		columns[11] = "Callsource";
		columns[12] = "Callsourceref";
		columns[13] = "Closuretype";
		columns[14] = "Custid";
		columns[15] = "Cust_Residecel_State";
		columns[16] = "Closedesc";
		columns[17] = "Status";
		columns[18] = "PRN";
		columns[19] = "Amount";
		columns[20] = "Payment_date";
		columns[21] = "Auth_date";
		columns[22] = "Ageing_1";
		columns[23] = "Disbursal_Date";
		columns[24] = "Ageing_2";
		columns[25] = "Ageing_3";
		columns[26] = "Assignment_Closure_Date";
		columns[27] = "Final_Remark";
		columns[28] = "Delay_Remark";
		columns[29] = "Vendor";
		
		
		System.out.println("in excel");
		

		Row headerRow5 = sheet.createRow(0);
		sheet.autoSizeColumn(33);
		sheet.autoSizeColumn(34);
		for (int k1 = 0; k1 < 30; k1++) {
			Cell cell = headerRow5.createCell(k1);
			cell.setCellValue(columns[k1]);
			cell.setCellStyle(headerCellStyle);
		}

		String fileName = "//192.168.6.211/upload/sps/SurrenderPi-interest/Report/SurrenderReport.xlsx";
		File file = new File(fileName);
		file.delete();

		try (FileOutputStream outputStream = new FileOutputStream("//192.168.6.211/upload/sps/SurrenderPi-interest/Report/SurrenderReport.xlsx")) {
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
			String fileName11 = "//192.168.6.211/upload/sps/SurrenderPi-interest/Report/SurrenderReport.xlsx";
			File file11 = new File(fileName11);

			String filename = file11.toString();
			System.out.println("filename is............. " + filename);

			surrenderimpl.exe(filename);
			System.out.println("out of excel");

			System.out.println("file is created............. " + filename);

		} catch (Exception e) {

		}

		return "user/Surrenderpiinterest";
	}
}
	