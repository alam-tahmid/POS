package com.pos.lotto.view;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.pos.lotto.model.Product;

public class ExcelView extends AbstractXlsView {


	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// change the file name
		response.setHeader("Content-Disposition", "attachment; filename=\"products.xls\"");

		 @SuppressWarnings("unchecked")
		List<Product> products = (List<Product>) model.get("products");

		// create excel xls sheet
		Sheet sheet = workbook.createSheet("Product Detail");
		sheet.setDefaultColumnWidth(30);

		// create style for header cells
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColor.BLUE.index);
		//style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		//font.setBold(true);
		//font.set(Color.BLACK);\
		font.setColor(HSSFColor.BLACK.index);
		style.setFont(font);

		// create header row
		Row header = sheet.createRow(0);
		header.createCell(0).setCellValue("Article No");
		header.getCell(0).setCellStyle(style);
		header.createCell(1).setCellValue("Barcode");
		header.getCell(1).setCellStyle(style);
		header.createCell(2).setCellValue("Description");
		header.getCell(2).setCellStyle(style);
		header.createCell(3).setCellValue("Size");
		header.getCell(3).setCellStyle(style);
		header.createCell(4).setCellValue("Color");
		header.getCell(4).setCellStyle(style);
		header.createCell(5).setCellValue("Unit Price");
		header.getCell(5).setCellStyle(style);
		header.createCell(6).setCellValue("Qty");
		header.getCell(6).setCellStyle(style);
		header.createCell(7).setCellValue("Sold Qty");
		header.getCell(7).setCellStyle(style);
		header.createCell(8).setCellValue("Total Price");
		header.getCell(8).setCellStyle(style);
		header.createCell(9).setCellValue("Discount Percentage");
		header.getCell(9).setCellStyle(style);

		int rowCount = 1;

		for (Product product : products) {
			Row productRow = sheet.createRow(rowCount++);
			productRow.createCell(0).setCellValue(product.getArticleNo());
			productRow.createCell(1).setCellValue(product.getBarcode());
			productRow.createCell(2).setCellValue(product.getDescription());
			productRow.createCell(3).setCellValue(product.getSize());
			productRow.createCell(4).setCellValue(product.getColor());
			productRow.createCell(5).setCellValue(product.getUnitPrice());
			productRow.createCell(6).setCellValue(product.getQuantity());
			productRow.createCell(7).setCellValue(product.getSoldQuantity());
			productRow.createCell(8).setCellValue(product.getTotalPrice());
			productRow.createCell(9).setCellValue(product.getDiscountPercentage());
		}

	}

}