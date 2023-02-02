package com.prodata.spsv1.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prodata.spsv1.model.Cheque;
import com.prodata.spsv1.model.User;
import com.prodata.spsv1.repository.ChequeRepository;
import com.prodata.spsv1.repository.DiscrepancyRepository;
import com.prodata.spsv1.repository.MetadataRepository;
import com.prodata.spsv1.repository.SpoolRepository;
import com.prodata.spsv1.repository.TaskRepository;
import com.prodata.spsv1.repository.impl.Execute;
import com.prodata.spsv1.service.MetaDataService;

@Controller
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 20, // 20 MB
		maxFileSize = 1024 * 1024 * 50, // 50 MB
		maxRequestSize = 1024 * 1024 * 100)
@RequestMapping("/cheque")
public class HomeController {

	private static String UPLOADED_FOLDER = "X://";

	@Autowired
	private ChequeRepository chequeRepository;

	@Autowired
	private TaskRepository taskrepsitory;

	@Autowired
	private MetadataRepository metadataRepository;

	@Autowired
	private Execute execute;

	@Autowired
	private SpoolRepository spoolrepository;

	@Autowired
	private DiscrepancyRepository discrepancyRepository;

	@Autowired
	private MetaDataService metaDataService;

	@GetMapping("show")
	public String ShowToDoList(Model m, String deletesuccess, String downloadsuccess, String paymentmode,
			String onersremark, String prno1, HttpSession s, String tastsuccess,
			@RequestParam(name = "next", required = false, defaultValue = "de") String next) {
		System.out.println("te  innnnnnnnnnnnnnnnnnnnnnn");
		System.out.println("sfgksfgvb" + s.getAttribute("user"));
		// System.out.println("show:uname = "+ uname);
		User u = (User) s.getAttribute("user");
		List<Cheque> list = chequeRepository.ToDoList(u);

		int countdata = chequeRepository.countToDoList(u);
		System.out.println("countdata = " + countdata);

		List<User> ulist = chequeRepository.getUserData();

		m.addAttribute("ulist", ulist);
		m.addAttribute("countdata", countdata);
		m.addAttribute("list", list);
		m.addAttribute("deletesuccess", deletesuccess);
		m.addAttribute("downloadsuccess", downloadsuccess);
		m.addAttribute("tastsuccess", tastsuccess);
		// System.out.println("list "+list);

		System.out.println("----------ShowToDoList---------------------");
		System.out.println(next);
		System.out.println("----------ShowToDoList---------------------");
		try {
			if (next != null && !next.equals("de") && next.trim().length() != 0) {
				System.out.println("prno1...." + prno1);
				int c = 0;
				String pmode = chequeRepository.getpmode(prno1);
				String oneRsMode = chequeRepository.getOneRsType(prno1);
				System.out.println("pmode " + pmode);
				for (Cheque cheque : list) {

					// System.out.println(paymentmode);
					System.out.println("Oners remark ethe pochla" + onersremark);
					if (cheque.getPaymentMode().trim().equalsIgnoreCase(pmode.trim())
							&& cheque.getFinalRemarkOners().trim().equalsIgnoreCase(oneRsMode.trim())) {
						System.out.println("got it " + c);
						break;
					}
					System.out.println(c);
					c++;
				}
				System.out.println("value of c" + c);
				System.out.println("next polno " + list.get(c).getPrnNo());

				String prnno = list.get(c++).getPrnNo();
				String policyNo = list.get(c++).getPolicyno();
				// paymentmode=list.get(c++).getPaymentMode();
				// System.out.println("list.get(c++).getPaymentMode()
				// "+list.get(c++).getPaymentMode());
				// System.out.println("----------ShowToDoList---------------------");
				// System.out.println(prnno);

				return "redirect:/cheque/displayPolicyDetails?policyNo=" + policyNo + "&prnno=" + prnno
						+ "&paymentMode=" + paymentmode + "&onersremark=" + onersremark;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "user/ToDoList";
			// TODO: handle exception
		}

		return "user/ToDoList";
	}

	@GetMapping("/displayPolicyDetails")
	public String displayPolicyDetails(Model m, @RequestParam("policyNo") String policyNo,
			@RequestParam("prnno") String prnno, String error1, @RequestParam String paymentMode,
			@RequestParam String onersremark) {
		// System.out.println("ChequeController:displayPolicyDetails"+policyNo);

		Map<String, String> modeOfPayment = new HashMap<>();
		modeOfPayment.put("B", "NEFT");
		modeOfPayment.put("5", "Direct Credit");
		modeOfPayment.put("9", "ECS");
		modeOfPayment.put("F", "DC-NRE");
		modeOfPayment.put("G", "NEFT-NRE");
		modeOfPayment.put("E", "AADHAR");

		Cheque onerschequeList;
		if (!onersremark.equals("null")) {
			onerschequeList = chequeRepository.onersgetData(policyNo, prnno, paymentMode, onersremark);
			System.out.println("chequelist1" + onerschequeList);
			System.out.println("iiiiiii");
		} else {
			onerschequeList = chequeRepository.getData(policyNo, prnno, paymentMode);
		}

		// System.out.println("displaypolicydetail:paymentmode"+paymentMode);
		// Map<String, Object> discrepancyTags =
		// discrepancyRepository.getAllDiscrepancyTags();
		List<String> discrepancyTags = discrepancyRepository.getAllDiscrepancyTags();
		// System.out.println(discrepancyTags);

		m.addAttribute("cheque", onerschequeList);

		m.addAttribute("discrepancyTags", discrepancyTags);
		m.addAttribute("modeOfPayment", modeOfPayment);
		// System.out.println("list "+chequeList);
		m.addAttribute("error1", error1);
		if (onerschequeList == null) {
			return "redirect:/cheque/show";
		}

		return "user/Cheque";
	}

	@PostMapping("/save")
	public String save(@ModelAttribute Cheque cheque, Model m, RedirectAttributes attributes, HttpSession s,
			@RequestParam("submit") String submit, @RequestParam("adddiscr") String adddiscr) {
		// System.out.println("inside save.......");
		// System.out.println("save:cheque........."+cheque);

		User u = (User) s.getAttribute("user");
		String policyNo = cheque.getvPolicyNo().trim();

		String maccNo = cheque.getmAccountNumber().trim();
		String vaccNo = cheque.getvAccountNumber().trim();
		String prnno = cheque.getPrnNo().trim();
		// System.out.println("home maccountno..............."+maccNo);
		// System.out.println("home vaccountno..............."+vaccNo);
		// System.out.println(policyNo+" after");
		// System.out.println("iscrepancy tag textbox field"+adddiscr);

		if (!adddiscr.isEmpty()) {
			String discrtxt = cheque.getDiscrepancyTag() + "," + adddiscr;
			cheque.setDiscrepancyTag(discrtxt);
		}

		// System.out.println("Name checked textbox"+checkName);

		attributes.addAttribute("policyNo", policyNo);

		String instrt = cheque.getInstructions().trim();

		// String policyNo = cheque.getvPolicyNo().trim();
		// String accNo = cheque.getmAccountNumber().trim();
		String ifsccode = cheque.getMifscCode().trim();
		String micrcode = cheque.getMmicrCode().trim();
		String bankname = cheque.getBankName().trim();
		// String paymentmode = cheque.getPaymentMode().trim();
		String paymentmode = "";
		String onersremark = "";

		if (cheque.getFinalRemarkOners() != null)
			onersremark = cheque.getFinalRemarkOners().trim();
		// System.out.println("save:getFinalRemarkOners"+onersremark);
		// System.out.println("save:");
		if (cheque.getPaymentMode() != null)
			paymentmode = cheque.getPaymentMode().trim();
		// System.out.println("paymentmode:save"+paymentmode);

		System.out.println(policyNo + " after");

		attributes.addAttribute("policyNo", policyNo);
		/* attributes.addAttribute("applno",vaplicno); */
		m.addAttribute("applicationNo");
		m.addAttribute("remarks");
		m.addAttribute("remarksBy");
		m.addAttribute("referenceno");

		// boolean insertdiscr=chequeRepository.insertDiscrCases(cheque, u);//insert
		// into discrepanycases table

		// if(insertdiscr==false){
		chequeRepository.insertIntoDb(cheque, maccNo, vaccNo, u, paymentmode, ifsccode, bankname);
		chequeRepository.compareData(prnno, vaccNo, ifsccode, maccNo, micrcode, paymentmode, u);
		// }

		System.out.println("submit value is..... " + submit);

		if (!submit.equalsIgnoreCase("Save")) {
			attributes.addAttribute("next", "next");
			attributes.addAttribute("paymentmode", paymentmode);
			attributes.addAttribute("onersremark", onersremark);
			attributes.addAttribute("prno1", prnno);
			// return "redirect:/cheque/showsubtype?paymode="+paymentmode;
		}
		chequeRepository.changeToDoListFlag(prnno);
		return "redirect:/cheque/show";
		// return "ssssss";
	}
	/*-----------------------------------Descripancy code---------------------------------------------------------------------*/

	@GetMapping("/showdiscrepancy")
	public String showdescrepency(Model m, HttpSession s,
			@RequestParam(name = "next", required = false, defaultValue = "de") String next) {

		List<User> ulist = chequeRepository.getUserData();
		m.addAttribute("ulist", ulist);

		User u = (User) s.getAttribute("user");
		List<Cheque> dlist = discrepancyRepository.DescrepencyToDoList(u);
		m.addAttribute("list", dlist);

		System.out.println("showdescrepency:list " + dlist);

		int countdata = discrepancyRepository.countToDoList(u);
		m.addAttribute("countdata", countdata);
		System.out.println(".........................count ala" + countdata);
		System.out.println("----------ShowToDoList---------------------");
		System.out.println(next);
		System.out.println("----------ShowToDoList---------------------");

		try {
			if (next != null && !next.equals("de") && next.trim().length() != 0) {
				String prnno = dlist.get(0).getPrnNo();
				System.out.println("----------ShowToDoList---------------------");
				System.out.println(prnno);

				return "redirect:/cheque/displayDPolicyDetailDis?policyNo=" + prnno;
			}
		} catch (Exception e) {
			return "user/DescrepencyToDoList";
			// TODO: handle exception
		}

		return "user/DescrepencyToDoList";

	}

	@GetMapping("/displayDPolicyDetailDis")
	public String displayDPolicyDetailDis(Model m, @RequestParam("policyNo") String policyNo, String error1) {

		System.out.println("ChequeController:displayDPolicyDetailsDis" + policyNo);
		if (policyNo.contains(",")) {
			int l = policyNo.lastIndexOf(",");
			policyNo = policyNo.substring(0, l);
		}

		Map<String, String> modeOfPayment = new HashMap<>();
		modeOfPayment.put("1", "CHEQUE");
		modeOfPayment.put("B", "NEFT");
		modeOfPayment.put("5", "Direct Credit");
		modeOfPayment.put("9", "ECS");
		modeOfPayment.put("F", "DC-NRE");
		modeOfPayment.put("G", "NEFT-NRE");
		modeOfPayment.put("E", "AADHAR");

		Cheque chequeList = discrepancyRepository.getDataDes(policyNo);

		List<String> discrepancyTags = discrepancyRepository.getAllDiscrepancyTags();
		System.out.println(discrepancyTags);

		m.addAttribute("cheque", chequeList);

		m.addAttribute("discrepancyTags", discrepancyTags);
		m.addAttribute("modeOfPayment", modeOfPayment);
		System.out.println("chequeList " + chequeList);
		m.addAttribute("error1", error1);
		if (chequeList == null) {
			return "redirect:/cheque/showdiscrepancy";
		}

		return "user/ChequeDes";

	}

	@PostMapping("/savedes")
	public String savedes(@ModelAttribute Cheque cheque, Model m, RedirectAttributes attributes,
			@RequestParam("submit") String submit) {
		String policyNo = cheque.getvPolicyNo().trim();
		String mapplino = cheque.getApplicationNo().trim();
		/* String vaplicno = cheque.getVapplicationno().trim(); */
		String maccNo = cheque.getmAccountNumber().trim();
		String vaccNo = cheque.getvAccountNumber().trim();
		String instrt = cheque.getInstructions().trim();
		attributes.addAttribute("policyNo", policyNo);

		m.addAttribute("applicationNo");
		m.addAttribute("remarks");
		m.addAttribute("remarksBy");
		m.addAttribute("referenceno");
		boolean insertdiscr = discrepancyRepository.insertDesWriteCases(cheque);

		if (insertdiscr == false) {
			discrepancyRepository.compareData(cheque, maccNo, vaccNo);
		} else if (insertdiscr) {
			return "redirect:/cheque/showdiscrepancy";
		} else {
			attributes.addAttribute("error1", "account number not matched");
			return "redirect:/cheque/displayDPolicyDetailDis";
		}

		if (!submit.equalsIgnoreCase("Save")) {
			attributes.addAttribute("next", "next");

		}

		discrepancyRepository.changeToDoListFlagDes(policyNo);
		return "redirect:/cheque/showdiscrepancy";

	}

	/*-------------------------------------------------------------------------------------------------------------*/
	@GetMapping("/getdcdetail")
	@ResponseBody
	public Cheque getdcdetail(Cheque cheque, @RequestParam("dc") String dc) {

		System.out.println("direct credit : " + dc);
		Cheque dcmode;
		try {
			dcmode = chequeRepository.getDcDetails(dc);
			System.out.println("m here");
			return dcmode;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@GetMapping("/getIfscDetails")
	@ResponseBody
	public Cheque getIfscDetails(Cheque cheque, @RequestParam("ifsc") String ifscCode,
			@RequestParam("prnNo") String prnNo) {
		// String prnno=cheque.getPrnNo();
		System.out.println("IFSC Code: " + ifscCode);
		Cheque ifscDetails;
		try {
			ifscDetails = chequeRepository.getIfscDetails(ifscCode, prnNo);
			return ifscDetails;
		} catch (Exception e) {
			return null;
		}
	}

	@GetMapping("/getMicrDetails")
	@ResponseBody
	public Cheque getMicrDetails(Cheque cheque, @RequestParam("micr") String micr,
			@RequestParam("prnNo") String prnNo) {
		// String prnno=cheque.getPrnNo().trim();
		System.out.println("MICR Code: " + micr);
		Cheque micrDetails;
		try {
			micrDetails = chequeRepository.getMicrDetails(micr, prnNo);
			return micrDetails;
		} catch (Exception e) {
			return null;
		}
	}

	/*-----------------------------------------new code--------------------------------------------------------------*/
	@GetMapping("/exportExcel")
	public String exportExcel(Model m, String downloadsuccess, String dlerrormsg) {
		System.out.println("inside exportExcel");
		m.addAttribute("downloadsuccess", downloadsuccess);
		m.addAttribute("dlerrormsg", dlerrormsg);
		return "user/ExportToExcel";
	}

	@PostMapping("/exportToExcel")
	public ResponseEntity<InputStreamResource> exportToExcel(Model model, @RequestParam("sdate") String sdate,
			@RequestParam("edate") String edate, RedirectAttributes attributes,
			@RequestParam("exportExcel") String exportExcel) throws FileNotFoundException, IOException {
		if (exportExcel.equalsIgnoreCase("Export Mandate")) {
			System.out.println("get f1 date" + sdate);
			System.out.println("get f2 date" + edate);
			Map<String, Object> m = metaDataService.MandateDbToExcel(sdate, edate);
			Map<String, Object> m1 = metadataRepository.discrepancyUpdateExcel(sdate, edate);
			System.out.println(m);
			HSSFWorkbook workbook = new HSSFWorkbook();
			Sheet sheet = workbook.createSheet("Ok to Process");
			Sheet sheet1 = workbook.createSheet("Discrepancy Report");
			Font headerFont = workbook.createFont();
			headerFont.setFontHeightInPoints((short) 14);
			CellStyle headerCellStyle = workbook.createCellStyle();

			List<String> columnNames = (List<String>) m.get("columnNames");
			List<Cheque> all = (List<Cheque>) m.get("data");

			List<String> columnNames1 = (List<String>) m1.get("columnNames");
			List<Cheque> all1 = (List<Cheque>) m1.get("data");
			// Create a Row
			Row headerRow = sheet.createRow(0);
			String a[] = new String[18];
			a[0] = "Contract No";
			a[1] = "Cross Ref Policy No.";
			a[2] = "Application";
			a[3] = "PRN No";
			a[4] = "Payment Amt.";
			a[5] = "Payment Type";
			a[6] = "Payment Reason";
			a[7] = "A/C No.";
			a[8] = "IFSC Code";
			a[9] = "MICR Code";
			a[10] = "Bank Name";
			a[11] = "A/C Holder Name";
			a[12] = "Disc";
			a[13] = "Vendor";
			a[14] = "Tag";
			a[15] = "Error Remark";
			a[16] = "Sampler Name";
			a[17] = "Client Id";
			Row headerRow1 = sheet1.createRow(0);
			String a2[] = new String[23];
			a2[0] = "Contract No";
			a2[1] = "Cross Ref Policy No.";
			a2[2] = "Application";
			a2[3] = "PRN No";
			a2[4] = "Payment Amt.";
			a2[5] = "Payment Type";
			a2[6] = "Payment Reason";
			a2[7] = "Error Remark";
			a2[8] = "Remark";
			a2[9] = "Reason";
			a2[10] = "ERROR OWNER";
			a2[11] = "A/C No.";
			a2[12] = "IFSC Code";
			a2[13] = "MICR Code";
			a2[14] = "Bank Name";
			a2[15] = "A/C Holder Name";
			a2[16] = "Disc";
			a2[17] = "Vendor";
			a2[18] = "Tag";
			// a2[19]="Error Remark";
			a2[19] = "Sampler Name";
			a2[20] = "Lot";
			a2[21] = "IPRU REMARK";
			a2[22] = "Client Id";

			// Create cells
			for (int i = 0; i < 18; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(a[i]);
				cell.setCellStyle(headerCellStyle);
			}
			for (int i = 0; i < 23; i++) {
				Cell cell = headerRow1.createCell(i);
				cell.setCellValue(a2[i]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowNum = 1;
			for (Cheque task : all) {
				if (task.getDiscrepancyTag().equals("0") || task.getDiscrepancyTag().equals(",")) {
					Row row = sheet.createRow(rowNum++);
					row.createCell(0).setCellValue(task.getPolicyno() + "");
					row.createCell(1).setCellValue(task.getCrossRefPolicy() + "");
					row.createCell(2).setCellValue(task.getApplicationNo() + "");
					row.createCell(3).setCellValue(task.getPrnNo() + "");
					row.createCell(4).setCellValue(task.getAmount() + "");
					row.createCell(5).setCellValue(task.getPaymentMode() + "");
					row.createCell(6).setCellValue(task.getPaymentType() + "");
					row.createCell(7).setCellValue(task.getvAccountNumber() + "");
					row.createCell(8).setCellValue(task.getMifscCode() + "");
					if (task.getMnmCode() != null) {
						row.createCell(9).setCellValue(task.getMnmCode() + "");
					} else {
						row.createCell(9).setCellValue(task.getMmicrCode() + "");
					}
					row.createCell(10).setCellValue(task.getBankName() + "");
					row.createCell(11).setCellValue(task.getName() + "");
					row.createCell(12).setCellValue(task.getDescr() + "");
					row.createCell(13).setCellValue(task.getVendorName() + "");
					row.createCell(14).setCellValue("OK TO Process");
					if (task.getDiscrepancyTag().equals("0")) {
						row.createCell(15).setCellValue("0");
					} else {
						row.createCell(15).setCellValue(task.getDiscrepancyTag().toString().replaceAll(",", "0") + "");
					}
					row.createCell(16).setCellValue(task.getRemarksBy() + "");
					row.createCell(17).setCellValue(task.getClientId() + "");
				}
				// row.createCell(30).setCellValue(task.getRemarksBy()+"");
				// row.createCell(31).setCellValue(task.getDate()+"");
				// row.createCell(32).setCellValue(task.getRemarks()+"");
				// row.createCell(34).setCellValue(task.getReference()+"");
			}

			rowNum = 1;
			for (Cheque task : all1) {
				if (!task.getDiscrepancyTag().equals("0") || !task.getDiscrepancyTag().equals(",")) {
					Row row = sheet1.createRow(rowNum++);
					row.createCell(0).setCellValue(task.getPolicyno() + "");
					row.createCell(1).setCellValue(task.getCrossRefPolicy() + "");
					row.createCell(2).setCellValue(task.getApplicationNo() + "");
					row.createCell(3).setCellValue(task.getPrnNo() + "");
					row.createCell(4).setCellValue(task.getAmount() + "");
					row.createCell(5).setCellValue(task.getPaymentMode() + "");
					row.createCell(6).setCellValue(task.getPaymentType() + "");
					// row.createCell(7).setCellValue(task.getAccountNo()+"");
					if (task.getDiscrepancyTag().equals("0")) {
						row.createCell(7).setCellValue(0);
					} else {
						row.createCell(7).setCellValue(task.getDiscrepancyTag().toString() + "");
						System.out.println(task.getDiscrepancyTag().toString() + "");
					}
					row.createCell(8).setCellValue("");
					row.createCell(9).setCellValue("");
					row.createCell(10).setCellValue("");

					row.createCell(11).setCellValue(task.getvAccountNumber() + "");
					row.createCell(12).setCellValue(task.getMifscCode() + "");
					row.createCell(13).setCellValue(task.getMnmCode() + "");
					row.createCell(14).setCellValue(task.getBankName() + "");
					row.createCell(15).setCellValue(task.getName() + "");
					row.createCell(16).setCellValue(task.getDescr() + "");
					row.createCell(17).setCellValue(task.getVendorName() + "");
					row.createCell(18).setCellValue("Descrepency");
					row.createCell(19).setCellValue(task.getRemarksBy() + "");
					row.createCell(20).setCellValue("");
					row.createCell(21).setCellValue("");
					row.createCell(22).setCellValue(task.getClientId() + "");
				}

			}

			try (FileOutputStream outputStream = new FileOutputStream(
					"//pdsql/Upload/Export/SamplingmandateExcel.xls")) {
				workbook.write(outputStream);
				String fileName = "//pdsql/Upload/Export/SamplingmandateExcel.xls";
				File file = new File(fileName);
				InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
						.contentLength(file.length()) //
						.body(resource);
			} catch (FileNotFoundException e) {
				// System.out.println("error bhetla");
				attributes.addAttribute("dlerrormsg", "Excel File is already open. Close the existing file.");
				// return "redirect:/cheque/exportExcel";
			}
			// attributes.addAttribute("downloadsuccess", "Downloaded Mandate Excel
			// Successfully in D:/download/SBAMMANDATEEXCEL");
		} else if (exportExcel.equalsIgnoreCase("Export PresamplingMandate")) {
			Map<String, Object> m = metaDataService.DbToExcel(sdate, edate);
			Map<String, Object> m1 = metadataRepository.prediscreDbToExcel(sdate, edate);
			System.out.println(m);
			HSSFWorkbook workbook = new HSSFWorkbook();
			Sheet sheet = workbook.createSheet("Ok to Process");
			Sheet sheet1 = workbook.createSheet("Discrepancy Report");
			Font headerFont = workbook.createFont();
			headerFont.setFontHeightInPoints((short) 14);
			CellStyle headerCellStyle = workbook.createCellStyle();

			List<String> columnNames = (List<String>) m.get("columnNames");
			List<Cheque> all2 = (List<Cheque>) m.get("data");

			List<String> columnNames1 = (List<String>) m1.get("columnNames");
			List<Cheque> all3 = (List<Cheque>) m1.get("data");
			// Create a Row
			Row headerRow = sheet.createRow(0);
			String a[] = new String[17];
			a[0] = "Contract No";
			a[1] = "Cross Ref Policy No.";
			a[2] = "Application";
			a[3] = "PRN No";
			a[4] = "Payment Amt.";
			a[5] = "Payment Type";
			a[6] = "Payment Reason";
			a[7] = "A/C No.";
			a[8] = "IFSC Code";
			a[9] = "MICR Code";
			a[10] = "Bank Name";
			a[11] = "A/C Holder Name";
			a[12] = "Disc";
			a[13] = "Vendor";
			a[14] = "Tag";
			a[15] = "Error Remark";
			a[16] = "Sampler Name";
			Row headerRow1 = sheet1.createRow(0);
			String a2[] = new String[22];
			a2[0] = "Contract No";
			a2[1] = "Cross Ref Policy No.";
			a2[2] = "Application";
			a2[3] = "PRN No";
			a2[4] = "Payment Amt.";
			a2[5] = "Payment Type";
			a2[6] = "Payment Reason";
			a2[7] = "Error Remark";
			a2[8] = "Remark";
			a2[9] = "Reason";
			a2[10] = "ERROR OWNER";
			a2[11] = "A/C No.";
			a2[12] = "IFSC Code";
			a2[13] = "MICR Code";
			a2[14] = "Bank Name";
			a2[15] = "A/C Holder Name";
			a2[16] = "Disc";
			a2[17] = "Vendor";
			a2[18] = "Tag";
			// a2[19]="Error Remark";
			a2[19] = "Sampler Name";
			a2[20] = "Lot";
			a2[21] = "IPRU REMARK";

			// Create cells
			for (int i = 0; i < 17; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(a[i]);
				cell.setCellStyle(headerCellStyle);
			}
			for (int i = 0; i < 22; i++) {
				Cell cell = headerRow1.createCell(i);
				cell.setCellValue(a2[i]);
				cell.setCellStyle(headerCellStyle);
			}
			int rowNum = 1;
			for (Cheque task : all2) {
				if (task.getDiscrepancyTag().equals("0") || task.getDiscrepancyTag().equals(",")) {
					Row row = sheet.createRow(rowNum++);
					row.createCell(0).setCellValue(task.getPolicyno() + "");
					row.createCell(1).setCellValue(task.getCrossRefPolicy() + "");
					row.createCell(2).setCellValue(task.getApplicationNo() + "");
					row.createCell(3).setCellValue(task.getPrnNo() + "");
					row.createCell(4).setCellValue(task.getAmount() + "");
					row.createCell(5).setCellValue(task.getPaymentMode() + "");
					row.createCell(6).setCellValue(task.getPaymentType() + "");
					row.createCell(7).setCellValue(task.getvAccountNumber() + "");
					row.createCell(8).setCellValue(task.getMifscCode() + "");
					if (task.getMnmCode() != null) {
						row.createCell(9).setCellValue(task.getMnmCode() + "");
					} else {
						row.createCell(9).setCellValue(task.getMmicrCode() + "");
					}
					row.createCell(10).setCellValue(task.getBankName() + "");
					row.createCell(11).setCellValue(task.getName() + "");
					row.createCell(12).setCellValue(task.getDescr() + "");
					row.createCell(13).setCellValue(task.getVendorName() + "");
					row.createCell(14).setCellValue("OK TO Process");
					if (task.getDiscrepancyTag().equals("0")) {
						row.createCell(15).setCellValue("0");
					} else {
						row.createCell(15).setCellValue(task.getDiscrepancyTag().toString().replaceAll(",", "0") + "");
					}
					row.createCell(16).setCellValue(task.getRemarksBy() + "");
				}
				// row.createCell(30).setCellValue(task.getRemarksBy()+"");
				// row.createCell(31).setCellValue(task.getDate()+"");
				// row.createCell(32).setCellValue(task.getRemarks()+"");
				// row.createCell(34).setCellValue(task.getReference()+"");
			}

			rowNum = 1;
			for (Cheque task : all3) {
				if (!task.getDiscrepancyTag().equals("0") || !task.getDiscrepancyTag().equals(",")) {
					Row row = sheet1.createRow(rowNum++);
					row.createCell(0).setCellValue(task.getPolicyno() + "");
					row.createCell(1).setCellValue(task.getCrossRefPolicy() + "");
					row.createCell(2).setCellValue(task.getApplicationNo() + "");
					row.createCell(3).setCellValue(task.getPrnNo() + "");
					row.createCell(4).setCellValue(task.getAmount() + "");
					row.createCell(5).setCellValue(task.getPaymentMode() + "");
					row.createCell(6).setCellValue(task.getPaymentType() + "");
					// row.createCell(7).setCellValue(task.getAccountNo()+"");
					if (task.getDiscrepancyTag().equals("0")) {
						row.createCell(7).setCellValue(0);
					} else {
						row.createCell(7).setCellValue(task.getDiscrepancyTag().toString() + "");
						System.out.println(task.getDiscrepancyTag().toString() + "");
					}
					row.createCell(8).setCellValue("");
					row.createCell(9).setCellValue("");
					row.createCell(10).setCellValue("");

					row.createCell(11).setCellValue(task.getvAccountNumber() + "");
					row.createCell(12).setCellValue(task.getMifscCode() + "");
					row.createCell(13).setCellValue(task.getMnmCode() + "");
					row.createCell(14).setCellValue(task.getBankName() + "");
					row.createCell(15).setCellValue(task.getName() + "");
					row.createCell(16).setCellValue(task.getDescr() + "");
					row.createCell(17).setCellValue(task.getVendorName() + "");
					row.createCell(18).setCellValue("Descrepency");
					row.createCell(19).setCellValue(task.getRemarksBy() + "");
					row.createCell(20).setCellValue("");
					row.createCell(21).setCellValue("");

				}

			}

			try (FileOutputStream outputStream = new FileOutputStream(
					"//pdsql/Upload/Export/SamplingmandateExcel.xls")) {
				workbook.write(outputStream);
				String fileName = "//pdsql/Upload/Export/SamplingmandateExcel.xls";
				File file = new File(fileName);
				InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
				return ResponseEntity.ok()
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
						.contentLength(file.length()) //
						.body(resource);
			} catch (FileNotFoundException e) {
				// System.out.println("error bhetla");
				model.addAttribute("dlerrormsg", "Excel File is already open. Close the existing file.");
				// return "redirect:/cheque/exportExcel";
			}

		}
		model.addAttribute("downloadsuccess", "Downloaded  Excel Successfully in Downloads");
		return null;

		// attributes.addAttribute("downloadsuccess", "Download Successfully");
		// return "redirect:/cheque/exportExcel";
	}

	/*------------------------------------end--------------------------------------------------------------*/

	@GetMapping("/uploadfile")
	public String uploadfile(Model m, String uploadmsg, String uploaddelete) {
		m.addAttribute("uploadmsg", uploadmsg);
		m.addAttribute("uploaddelete", uploaddelete);
		return "user/UploadFile";
	}

	@PostMapping("/excel")
	public String Excel(Model m, RedirectAttributes redirectAttributes) {
		int i;
		try {

			i = execute.rename(); // upload SP
			chequeRepository.insertExcelOtherData(); // tag assign_to
			if (i == 1)
				redirectAttributes.addAttribute("uploadmsg", "File Uploaded Successfully");
			else
				redirectAttributes.addAttribute("uploadmsg", "Error file uploading");
			/*
			 * Vector dataHolder=excelToDb.read(UPLOADED_FOLDER+file.getOriginalFilename());
			 * excelToDb.saveToDatabase(dataHolder);
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/cheque/uploadfile";
	}

	@GetMapping("delete")
	public String deleteupload(Model m) {
		int f = taskrepsitory.deleteupload();
		if (f >= 1) {
			m.addAttribute("uploaddelete", "Upload file deleted successfully.");
		} else {
			m.addAttribute("uploaddelete", "Upload file already deleted.");
		}
		return "user/UploadFile";
	}

	@GetMapping("/showsubtype")
	public String showsubtype(Model m, @RequestParam("paymode") String paymode, HttpSession s) {
		User u = (User) s.getAttribute("user");

		List<User> ulist = chequeRepository.getUserData();
		s.setAttribute("pmode", paymode);
		m.addAttribute("ulist", ulist);
		int countdata = chequeRepository.countToDoListpaymode(u, paymode);
		System.out.println("countdata = " + countdata);
		// System.out.println(paymode);
		List<Cheque> list = chequeRepository.showSubType(paymode, u);
		m.addAttribute("list", list);
		m.addAttribute("countdata", countdata);
		// System.out.println("list "+list);
		return "user/ToDoList";
	}

	@GetMapping("/OneRstype")
	public String OneRstype(Model m, @RequestParam("OneRsType") String OneRsType, HttpSession s) {
		User u = (User) s.getAttribute("user");
		List<User> ulist = chequeRepository.getUserData();
		String pmode = (String) s.getAttribute("pmode");

		// System.out.println("pAYMENT MODE SESSION"+pmode);
		s.setAttribute("oneRsMode", OneRsType);
		m.addAttribute("ulist", ulist);
		int countdata = chequeRepository.countToDoListOneRs(u, OneRsType, pmode);
		System.out.println("countdata = " + countdata);
		System.out.println(OneRsType);
		List<Cheque> list = chequeRepository.showOneRsType(OneRsType, u, pmode);
		m.addAttribute("list", list);
		m.addAttribute("countdata", countdata);
		m.addAttribute("OneRsType", OneRsType);
		System.out.println("list" + list);
		// s.removeAttribute("pmode");
		return "user/ToDoList";
	}

	/*
	 * --------------------take amout and subtype wise data-------------------------
	 */
	@GetMapping("/showamtgrtfive")
	public String subtypeamt(Model m, @RequestParam("paymode") String paymode, @RequestParam("amt") String amtrange,
			HttpSession s) {
		s.setAttribute("amtrange", amtrange);
		System.out.println("inside in contro... " + "");
		User u = (User) s.getAttribute("user");
		List<User> ulist = chequeRepository.getUserData();

		s.setAttribute("pmode", paymode);
		s.setAttribute("amount", amtrange);
		m.addAttribute("ulist", ulist);
		int countdata = spoolrepository.countToDoListpaymode(u, paymode, amtrange);

		System.out.println("countdata = " + countdata);
		// List<Cheque> list1= chequeRepository.ToDoList1(u, paymode);
		List<Cheque> list = spoolrepository.sortAmt(paymode, amtrange, u);
		System.out.println("list for todolist" + list);

		m.addAttribute("countdata", countdata);
		// System.out.println("in controller paymode "+paymode+" range "+amtrange);
		m.addAttribute("list", list);
		return "user/ToDoList";
		// return "redirect:/task/allocatetask?amount="+amount";
	}
	
	
//	============================== SRU CASE==================================================
	
	
	@GetMapping("/getSruCase")
	@ResponseBody
	public Cheque getSruCase(Cheque cheque, @RequestParam("prnNo") String prnNo) {
		// String prnno=cheque.getPrnNo().trim();
		System.out.println("PRN Code LL: " + prnNo);
		
		Cheque SruCase;
		try {
			SruCase = chequeRepository.getSruCase(prnNo);
			return SruCase;
		} catch (Exception e) {
			return null;
		}
	}
	
	

}
