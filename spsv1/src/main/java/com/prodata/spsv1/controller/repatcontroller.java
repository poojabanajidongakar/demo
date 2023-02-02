package com.prodata.spsv1.controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpSession;

import org.apache.poi.xwpf.usermodel.VerticalAlign;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.prodata.spsv1.model.Repatmodel;
import com.prodata.spsv1.repository.impl.RepatRepoimpl;
import com.prodata.spsv1.repository.impl.Repatimpl;

@Controller
@RequestMapping("/repat")
public class repatcontroller {

	@Autowired
	Repatimpl Repatimpl;

	@Autowired
	RepatRepoimpl repatrepoimpl;
	
	@Autowired
	public JdbcTemplate jdTemplate;

	@GetMapping("/repatletter")
	public String paymentRecon(Model m, String uploadmsg, String uploaddelete, String errormsg, String ExportHoldFile) {
		m.addAttribute("uploadmsg", uploadmsg);
		return "user/Repatletter";
	}

	@PostMapping("/repatletter")
	public String repatletter(Model m, RedirectAttributes rdAttributes, @RequestParam("repatfile") String upload,
			HttpSession s) {
		int i;
		if (upload.equalsIgnoreCase("repatfile")) {
			i = Repatimpl.Repatletter();

			if (i == 1) {
				m.addAttribute("uploadmsg", "Files Uploaded Succesfully");
			} else {

				m.addAttribute("uploadmsg", "Pls chk file or column..!");
			}
		}
		return "user/Repatletter";
	}

	@PostMapping("/repatfile")
	public String repatfile(Model m, RedirectAttributes rdAttributes, @RequestParam("repatletter") String upload,
			HttpSession s) {
		int i;
		if (upload.equalsIgnoreCase("repatletter")) {
			i = Repatimpl.payoutcollection();

			if (i == 1) {
				m.addAttribute("uploadmsg", "Files Uploaded Succesfully");
			} else {

				m.addAttribute("uploadmsg", "Pls chk file or column..!");
			}
		}
		return "user/Repatletter";
	}

	@GetMapping("/letter")
	public static String main(String[] args, Model m,HttpSession session,Repatmodel repat) {	
		try {	
			String Contractno = repat.getContractno();
			System.out.println(Contractno);
			XWPFDocument document = new XWPFDocument();
			FileOutputStream out = new FileOutputStream(new File("c:\\Shubham\\repat\\letter.docx"));
			XWPFParagraph paragraph = document.createParagraph();
			XWPFRun run = paragraph.createRun();
			run.setText("Subject: Confirmation of premium payment for your policy no 16321850");
			// create table
			XWPFTable table = document.createTable();
			// create first row
			XWPFTableRow tableRowOne = table.getRow(0);
			tableRowOne.getCell(0).setText("col one, row one");
			tableRowOne.addNewTableCell().setText("col two, row one");
			tableRowOne.addNewTableCell().setText("col three, row one");
			// create second row
			XWPFTableRow tableRowTwo = table.createRow();
			tableRowTwo.getCell(0).setText("col one, row two");
			tableRowTwo.getCell(1).setText("col two, row two");
			tableRowTwo.getCell(2).setText("col three, row two");
			// create third row
			XWPFTableRow tableRowThree = table.createRow();
			tableRowThree.getCell(0).setText("col one, row three");
			tableRowThree.getCell(1).setText("col two, row three");
			tableRowThree.getCell(2).setText("col three, row three");
			XWPFRun paragraphOneRunThree = paragraph.createRun();
			// paragraphOneRunThree.setStrike(true);
			paragraphOneRunThree.setFontSize(10);
			paragraphOneRunThree.setSubscript(VerticalAlign.SUBSCRIPT);
			paragraphOneRunThree.setText("Zurich BT");
			document.write(out);
			out.close();
			System.out.println("createparagraph.docx written successfully");
		} catch (Exception e) {

		}

		return "user/Repatletter";
	}

}
