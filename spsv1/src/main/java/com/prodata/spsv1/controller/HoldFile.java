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
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
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

import com.prodata.spsv1.model.Glenysfilemodel;
import com.prodata.spsv1.model.Holdfilemodel;
import com.prodata.spsv1.repository.HoldfileRepo;
import com.prodata.spsv1.repository.impl.Holdimpl;

@Controller
@RequestMapping("/Hold")
public class HoldFile {

	@Autowired
	Holdimpl holdimpl;

	@Autowired
	public JdbcTemplate jdbctemplete;

	@Autowired
	public HoldfileRepo HoldFile;

	@GetMapping("/holdpayment")
	public String paymentRecon(Model m, String uploadmsg, String uploaddelete, String errormsg, String ExportHoldFile) {
		m.addAttribute("uploadmsg", uploadmsg);
		m.addAttribute("errormsg", errormsg);
		m.addAttribute("uploaddelete", uploaddelete);
		m.addAttribute("ExportHoldFile", ExportHoldFile);
		return "user/HoldPage";
	}

	@PostMapping("/HoldFileProcess")
	public String holdfile(Model m, RedirectAttributes rdAttributes, @RequestParam("holffile") String upload,
			HttpSession s) {
		int i;
		if (upload.equalsIgnoreCase("holdfilepro")) {
			i = holdimpl.HoldFileprocess();

			if (i == 1) {
				m.addAttribute("uploadmsg", "Files Uploaded Succesfully");
			} else {

				m.addAttribute("uploadmsg", "Pls chk file or column..!");
			}
		}
		return "user/HoldPage";
	}

	@PostMapping("/uplFixedFile")
	public String fixedfile(Model m, RedirectAttributes rdAttributes, @RequestParam("uplFixedFile") String upload,
			HttpSession s) {
		int i;
		if (upload.equalsIgnoreCase("FixedFile")) {
			i = holdimpl.uplFixedFile();
			if (i == 1) {
				m.addAttribute("uploadmsg1", "Files Uploaded Succesfully");
			} else {

				m.addAttribute("uploadmsg1", "Pls chk file or column..!");
			}
		}
		return "user/HoldPage";
	}

	@PostMapping("/holdfilequery")
	public String exportcustomer(Model m, RedirectAttributes attributes, HttpSession s, @RequestParam("exc") String exc)
			throws FileNotFoundException, IOException {

		HoldFile.exequery(exc);

		return "user/HoldPage";

	}

	@GetMapping("/HoldFileExport")
	public ResponseEntity<InputStreamResource> HoldFileExport(Model m, RedirectAttributes attributes, HttpSession s)
			throws FileNotFoundException, IOException {

		System.out.println("holdFileExport");

		Date date = Calendar.getInstance().getTime();
		DateFormat Formatter = new SimpleDateFormat("dd-MM-yyyy");
		String today = Formatter.format(date);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet1 = workbook.createSheet("Sheet1");
		XSSFRow rowHeader = sheet1.createRow(0);
		XSSFCell headerCell1 = rowHeader.createCell(0);
		XSSFCell headerCell2 = rowHeader.createCell(1);
		XSSFCell headerCell3 = rowHeader.createCell(2);
		XSSFCell headerCell4 = rowHeader.createCell(3);
		XSSFCell headerCell5 = rowHeader.createCell(4);
		XSSFCell headerCell6 = rowHeader.createCell(5);
		XSSFCell headerCell7 = rowHeader.createCell(6);
		XSSFCell headerCell8 = rowHeader.createCell(7);
		XSSFCell headerCell9 = rowHeader.createCell(8);
		XSSFCell headerCell10 = rowHeader.createCell(9);
		XSSFCell headerCell11 = rowHeader.createCell(10);
		XSSFCell headerCell12 = rowHeader.createCell(11);

		XSSFFont font1 = workbook.createFont();
		font1.setFontName("Calibri");
		font1.setBold(true);

		DataFormat fmt = workbook.createDataFormat();
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setDataFormat(fmt.getFormat("@"));

		XSSFCellStyle style1 = workbook.createCellStyle();
		style1.setFont(font1);
		style1.setDataFormat(fmt.getFormat("@"));
		style1.setBorderTop(BorderStyle.THIN);
		style1.setBorderBottom(BorderStyle.THIN);
		style1.setBorderRight(BorderStyle.THIN);
		style1.setBorderLeft(BorderStyle.THIN);

		XSSFCellStyle style3 = workbook.createCellStyle();
		style3.setFont(font1);
		style3.setBorderTop(BorderStyle.THIN);
		style3.setBorderBottom(BorderStyle.THIN);
		style3.setBorderRight(BorderStyle.THIN);
		style3.setBorderLeft(BorderStyle.THIN);

		XSSFCellStyle style4 = workbook.createCellStyle();
		style4.setBorderTop(BorderStyle.THIN);
		style4.setBorderBottom(BorderStyle.THIN);
		style4.setBorderRight(BorderStyle.THIN);
		style4.setBorderLeft(BorderStyle.THIN);
		style4.setDataFormat(fmt.getFormat("@"));

		XSSFCellStyle style5 = workbook.createCellStyle();
		style5.setBorderTop(BorderStyle.THIN);
		style5.setBorderBottom(BorderStyle.THIN);
		style5.setBorderRight(BorderStyle.THIN);
		style5.setBorderLeft(BorderStyle.THIN);

		headerCell1.setCellValue("CALL_ID");
		headerCell1.setCellStyle(style1);

		headerCell2.setCellValue("Hold Date");
		headerCell2.setCellStyle(style3);

		headerCell3.setCellValue("Till Date");
		headerCell3.setCellStyle(style3);

		headerCell4.setCellValue("Ageing");
		headerCell4.setCellStyle(style3);

		headerCell5.setCellValue("CONTRACT_NO");
		headerCell5.setCellStyle(style1);

		headerCell6.setCellValue("SOURCE");
		headerCell6.setCellStyle(style3);

		headerCell7.setCellValue("CALLTYPE");
		headerCell7.setCellStyle(style3);

		headerCell8.setCellValue("SUBTYPE");
		headerCell8.setCellStyle(style3);

		headerCell9.setCellValue("RECVDBY");
		headerCell9.setCellStyle(style1);

		headerCell10.setCellValue("CALLDESC");
		headerCell10.setCellStyle(style3);

		headerCell11.setCellValue("Ownership");
		headerCell11.setCellStyle(style3);

		headerCell12.setCellValue("Department");
		headerCell12.setCellStyle(style3);

		List<Holdfilemodel> transactionList = HoldFile.exportholdfile();
		for (int row = 1; row <= transactionList.size(); row++) {
			XSSFRow empDataRow = sheet1.createRow(row);

			XSSFCell CALL_ID = empDataRow.createCell(0);
			XSSFCell Hold_Date = empDataRow.createCell(1);
			XSSFCell Till_Date = empDataRow.createCell(2);
			XSSFCell Ageing = empDataRow.createCell(3);
			XSSFCell CONTRACT_NO = empDataRow.createCell(4);
			XSSFCell SOURCE = empDataRow.createCell(5);
			XSSFCell CALLTYPE = empDataRow.createCell(6);
			XSSFCell SUBTYPE = empDataRow.createCell(7);
			XSSFCell RECVDBY = empDataRow.createCell(8);
			XSSFCell CALLDESC = empDataRow.createCell(9);
			XSSFCell Ownership = empDataRow.createCell(10);
			XSSFCell Department = empDataRow.createCell(11);

			CALL_ID.setCellValue(transactionList.get(row - 1).getCall_id());
			Hold_Date.setCellValue(transactionList.get(row - 1).getHold_date());
			Till_Date.setCellValue(transactionList.get(row - 1).getTill_date());
			Ageing.setCellValue(transactionList.get(row - 1).getAgeing());
			CONTRACT_NO.setCellValue(transactionList.get(row - 1).getContract_no());
			SOURCE.setCellValue(transactionList.get(row - 1).getSource());
			CALLTYPE.setCellValue(transactionList.get(row - 1).getCalltype());
			SUBTYPE.setCellValue(transactionList.get(row - 1).getSubtype());
			RECVDBY.setCellValue(transactionList.get(row - 1).getRecvdby());
			CALLDESC.setCellValue(transactionList.get(row - 1).getCalldesc());
			Ownership.setCellValue(transactionList.get(row - 1).getOwnership());
			Department.setCellValue(transactionList.get(row - 1).getDepartment());

			CONTRACT_NO.setCellStyle(style4);
			CALL_ID.setCellStyle(style4);
			RECVDBY.setCellStyle(style4);
			Hold_Date.setCellStyle(style5);
			Till_Date.setCellStyle(style5);
			Ageing.setCellStyle(style5);
			SOURCE.setCellStyle(style5);
			CALLTYPE.setCellStyle(style5);
			SUBTYPE.setCellStyle(style5);
			CALLDESC.setCellStyle(style5);
			Ownership.setCellStyle(style5);
			Department.setCellStyle(style5);

		}
//		try (FileOutputStream outputStream = new FileOutputStream(
//				"C:/Shubham/Hold Automation/Hold File Automation/Output/Hold Collected File_" + today + ".xlsx")) 
		try (FileOutputStream outputStream = new FileOutputStream(
				"//192.168.6.211//upload/sps/HoldFile/Output/Hold Collected File_" + today + ".xlsx")) {
			workbook.write(outputStream);
			String fileName = "//192.168.6.211//upload/sps/HoldFile/Output/Hold Collected File_" + today + ".xlsx";
			File file = new File(fileName);
			System.out.println("file name is............. " + file);
			String filename = file.toString();
			System.out.println("filename is............. " + filename);
			outputStream.flush();
			outputStream.close();

			m.addAttribute("ExportHoldFile", "Hold File Downloaded");

			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
					.contentLength(file.length()).body(resource);

		}

	}

	@GetMapping("/GlenysFileExport")
	public ResponseEntity<InputStreamResource> HoldFileExport1(Model m, RedirectAttributes attributes, HttpSession s)
			throws FileNotFoundException, IOException {

		XSSFWorkbook workbook1 = new XSSFWorkbook();
		XSSFSheet sheet = workbook1.createSheet("Sheet1");
		XSSFRow rowHeader1 = sheet.createRow(0);
		XSSFCell headerCell13 = rowHeader1.createCell(0);
		XSSFCell headerCell14 = rowHeader1.createCell(1);
		XSSFCell headerCell15 = rowHeader1.createCell(2);
		XSSFCell headerCell16 = rowHeader1.createCell(3);
		XSSFCell headerCell17 = rowHeader1.createCell(4);
		XSSFCell headerCell18 = rowHeader1.createCell(5);
		XSSFCell headerCell19 = rowHeader1.createCell(6);
		XSSFCell headerCell20 = rowHeader1.createCell(7);
		XSSFCell headerCell21 = rowHeader1.createCell(8);
		XSSFCell headerCell22 = rowHeader1.createCell(9);
		XSSFCell headerCell23 = rowHeader1.createCell(10);
		XSSFCell headerCell24 = rowHeader1.createCell(11);
		XSSFCell headerCell25 = rowHeader1.createCell(12);

		XSSFFont font = workbook1.createFont();
		font.setFontName("Calibri");
		font.setBold(true);

		DataFormat fmt1 = workbook1.createDataFormat();
		CellStyle cellStyle1 = workbook1.createCellStyle();
		cellStyle1.setDataFormat(fmt1.getFormat("@"));

		XSSFCellStyle style = workbook1.createCellStyle();
		style.setFont(font);
		style.setDataFormat(fmt1.getFormat("@"));
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);

		XSSFCellStyle style2 = workbook1.createCellStyle();
		style2.setFont(font);
		style2.setBorderTop(BorderStyle.THIN);
		style2.setBorderBottom(BorderStyle.THIN);
		style2.setBorderRight(BorderStyle.THIN);
		style2.setBorderLeft(BorderStyle.THIN);

		XSSFCellStyle style6 = workbook1.createCellStyle();
		style6.setBorderTop(BorderStyle.THIN);
		style6.setBorderBottom(BorderStyle.THIN);
		style6.setBorderRight(BorderStyle.THIN);
		style6.setBorderLeft(BorderStyle.THIN);
		style6.setDataFormat(fmt1.getFormat("@"));

		XSSFCellStyle style7 = workbook1.createCellStyle();
		style7.setBorderTop(BorderStyle.THIN);
		style7.setBorderBottom(BorderStyle.THIN);
		style7.setBorderRight(BorderStyle.THIN);
		style7.setBorderLeft(BorderStyle.THIN);

		headerCell13.setCellValue("CALL_ID");
		headerCell13.setCellStyle(style);

		headerCell14.setCellValue("Hold Date");
		headerCell14.setCellStyle(style2);

		headerCell15.setCellValue("Till Date");
		headerCell15.setCellStyle(style2);

		headerCell16.setCellValue("Ageing");
		headerCell16.setCellStyle(style2);

		headerCell17.setCellValue("CONTRACT_NO");
		headerCell17.setCellStyle(style);

		headerCell18.setCellValue("SOURCE");
		headerCell18.setCellStyle(style2);

		headerCell19.setCellValue("CALLTYPE");
		headerCell19.setCellStyle(style2);

		headerCell20.setCellValue("SUBTYPE");
		headerCell20.setCellStyle(style2);

		headerCell21.setCellValue("RECVDBY");
		headerCell21.setCellStyle(style);

		headerCell22.setCellValue("CALLDESC");
		headerCell22.setCellStyle(style2);

		headerCell23.setCellValue("Ownership");
		headerCell23.setCellStyle(style2);

		headerCell24.setCellValue("Department");
		headerCell24.setCellStyle(style2);

		headerCell25.setCellValue("Approval");
		headerCell25.setCellStyle(style2);

		List<Glenysfilemodel> transactionList1 = HoldFile.exportGlenysfile();
		for (int row = 1; row <= transactionList1.size(); row++) {
			XSSFRow empDataRow = sheet.createRow(row);

			XSSFCell CALL_ID = empDataRow.createCell(0);
			XSSFCell Hold_Date = empDataRow.createCell(1);
			XSSFCell Till_Date = empDataRow.createCell(2);
			XSSFCell Ageing = empDataRow.createCell(3);
			XSSFCell CONTRACT_NO = empDataRow.createCell(4);
			XSSFCell SOURCE = empDataRow.createCell(5);
			XSSFCell CALLTYPE = empDataRow.createCell(6);
			XSSFCell SUBTYPE = empDataRow.createCell(7);
			XSSFCell RECVDBY = empDataRow.createCell(8);
			XSSFCell CALLDESC = empDataRow.createCell(9);
			XSSFCell Ownership = empDataRow.createCell(10);
			XSSFCell Department = empDataRow.createCell(11);
			XSSFCell Approval = empDataRow.createCell(12);

			CALL_ID.setCellValue(transactionList1.get(row - 1).getCall_id());
			Hold_Date.setCellValue(transactionList1.get(row - 1).getHold_date());
			Till_Date.setCellValue(transactionList1.get(row - 1).getTill_date());
			Ageing.setCellValue(transactionList1.get(row - 1).getAgeing());
			CONTRACT_NO.setCellValue(transactionList1.get(row - 1).getContract_no());
			SOURCE.setCellValue(transactionList1.get(row - 1).getSource());
			CALLTYPE.setCellValue(transactionList1.get(row - 1).getCalltype());
			SUBTYPE.setCellValue(transactionList1.get(row - 1).getSubtype());
			RECVDBY.setCellValue(transactionList1.get(row - 1).getRecvdby());
			CALLDESC.setCellValue(transactionList1.get(row - 1).getCalldesc());
			Ownership.setCellValue(transactionList1.get(row - 1).getOwnership());
			Department.setCellValue(transactionList1.get(row - 1).getDepartment());
			Approval.setCellValue(transactionList1.get(row - 1).getApproval());

			CONTRACT_NO.setCellStyle(style6);
			CALL_ID.setCellStyle(style6);
			RECVDBY.setCellStyle(style6);
			Hold_Date.setCellStyle(style7);
			Till_Date.setCellStyle(style7);
			Ageing.setCellStyle(style7);
			SOURCE.setCellStyle(style7);
			CALLTYPE.setCellStyle(style7);
			SUBTYPE.setCellStyle(style7);
			CALLDESC.setCellStyle(style7);
			Ownership.setCellStyle(style7);
			Department.setCellStyle(style7);
			Approval.setCellStyle(style7);
		}
//		try (FileOutputStream outputStream = new FileOutputStream(
//				"C:/Shubham/Hold Automation/Hold File Automation/Output/Glenys.xlsx"))
		try (FileOutputStream outputStream = new FileOutputStream(
				"//192.168.6.211//upload/sps/HoldFile/Output/Glenys.xlsx")) {
			workbook1.write(outputStream);
			String fileName = "//192.168.6.211//upload/sps/HoldFile/Output/Glenys.xlsx";
			File file = new File(fileName);
			System.out.println("file name is............. " + file);
			String filename = file.toString();
			System.out.println("filename is............. " + filename);
			outputStream.flush();
			outputStream.close();
			m.addAttribute("ExportGlenysFile", "Glenys File Downloaded");
			InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
			return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
					.contentLength(file.length()).body(resource);
		}
	}
}
