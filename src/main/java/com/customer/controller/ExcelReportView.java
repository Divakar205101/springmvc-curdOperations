package com.customer.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.customer.enity.BankDetails;

public class ExcelReportView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 response.setHeader("Content-Disposition", "attachment; filename=\"my-exported-file.xls\"");
		
		 @SuppressWarnings("unchecked")
		 List<BankDetails> bankDetails= (List<BankDetails>) model.get("bankdetails");
		 
		 Sheet sheet = workbook.createSheet("BankDetails List");
		 Row headerRow = sheet.createRow(0);
		 headerRow.createCell(0).setCellValue("ID");
		 headerRow.createCell(1).setCellValue("Bank Name");
		 headerRow.createCell(2).setCellValue("Act No");
		 headerRow.createCell(3).setCellValue("Branch");
		 headerRow.createCell(4).setCellValue("Amount");
		 
		 int row=1;
		 
		 for(BankDetails bankDetails2 : bankDetails) {
			 Row dataRow = sheet.createRow(row++);
			 dataRow.createCell(0).setCellValue(bankDetails2.getId());
			 dataRow.createCell(1).setCellValue(bankDetails2.getBankname());
			 dataRow.createCell(2).setCellValue(bankDetails2.getAccNo());
			 dataRow.createCell(3).setCellValue(bankDetails2.getBranch());
			 dataRow.createCell(4).setCellValue(bankDetails2.getAmount());
		 }
	}

}
