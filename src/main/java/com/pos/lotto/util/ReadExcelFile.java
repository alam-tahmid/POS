package com.pos.lotto.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import com.pos.lotto.model.Product;

@Service
public class ReadExcelFile {

	public List<Product> readExcel(File file) throws EncryptedDocumentException, InvalidFormatException, IOException {

		// Creating a Workbook from an Excel file (.xls or .xlsx)
		Workbook workbook = WorkbookFactory.create(file);

		// Getting the Sheet at index zero
		Sheet sheet = workbook.getSheetAt(0);

		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
		Iterator<Row> rowIterator = sheet.rowIterator();
		List<Product> products = new ArrayList<Product>();
		Product product = new Product();

		while (rowIterator.hasNext()) {

			Row row = rowIterator.next();
			product = new Product();

			Cell articleNoCell = row.getCell(0);
			String articleNo = dataFormatter.formatCellValue(articleNoCell);
			product.setArticleNo(articleNo);

			Cell barcodeCell = row.getCell(1);
			String barcode = dataFormatter.formatCellValue(barcodeCell);
			product.setBarcode(barcode);

			Cell descriptionCell = row.getCell(2);
			String description = dataFormatter.formatCellValue(descriptionCell);
			product.setDescription(description);

			Cell sizeCell = row.getCell(3);
			String sizeString = dataFormatter.formatCellValue(sizeCell);
			Integer size = Integer.valueOf(sizeString);
			product.setSize(size);

			Cell colorCell = row.getCell(4);
			String color = dataFormatter.formatCellValue(colorCell);
			product.setColor(color);

			Cell unitPriceCell = row.getCell(5);
			String unitPriceString = dataFormatter.formatCellValue(unitPriceCell);
			Double unitPrice = Double.valueOf(unitPriceString);
			product.setUnitPrice(unitPrice);

			Cell qtyCell = row.getCell(6);
			String qtyString = dataFormatter.formatCellValue(qtyCell);
			Integer qty = Integer.valueOf(qtyString);
			product.setQuantity(qty);

			Cell soldQtyCell = row.getCell(7);
			String soldQtyString = dataFormatter.formatCellValue(soldQtyCell);
			Integer soldQty = Integer.valueOf(soldQtyString);
			product.setSoldQuantity(soldQty);

			Cell totalPriceCell = row.getCell(8);
			String totalPriceString = dataFormatter.formatCellValue(totalPriceCell);
			Double totalPrice = Double.valueOf(totalPriceString);
			product.setTotalPrice(totalPrice);

			Cell discountPercentageCell = row.getCell(9);
			String discountPercentageString = dataFormatter.formatCellValue(discountPercentageCell);
			Double discountPercentage = Double.valueOf(discountPercentageString);
			product.setDiscount(discountPercentage);

			Cell offerCell = row.getCell(10);
			String offer = dataFormatter.formatCellValue(offerCell);
			product.setOffer(offer);

			products.add(product);

		}
		return products;
	}
}
