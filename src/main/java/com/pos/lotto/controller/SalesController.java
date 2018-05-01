package com.pos.lotto.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.pos.lotto.model.InvoiceNumber;
import com.pos.lotto.model.PdfData;
import com.pos.lotto.model.Product;
import com.pos.lotto.model.Sales;
import com.pos.lotto.model.User;
import com.pos.lotto.service.InvoiceService;
import com.pos.lotto.service.ProductService;
import com.pos.lotto.service.SalesService;
import com.pos.lotto.util.SessionUtil;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
public class SalesController {

	@Autowired
	private JSONArray jsonArray;

	@Autowired
	private ProductService productService;

	@Autowired
	private SalesService salesService;

	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private ApplicationContext appContext;


	@PostMapping(value = "/sales")
	public ResponseEntity<Object> sales(@RequestBody String json, HttpServletRequest request,
			UriComponentsBuilder uriComponentBuilder) throws JSONException {

		HttpSession session = SessionUtil.createSession(request);
		User user = (User) session.getAttribute("user");
		String salesMan = user.getName() + " " + user.getLastName();

		HttpHeaders headers = new HttpHeaders();

		Gson gson = new Gson();
		jsonArray = new JSONArray(json);
		List<Sales> salesList = new ArrayList<Sales>();

		InvoiceNumber invoiceNumber = invoiceService.findLast();
		int invoiceNo = invoiceNumber.getInvoice();
		invoiceNo = invoiceNo + 1;
		InvoiceNumber newInvoiceNumber = new InvoiceNumber();
		String newGeneratedInvoice = "INV" + invoiceNo;

		newInvoiceNumber.setGenerateBy(salesMan);
		newInvoiceNumber.setInvoice(invoiceNo);
		newInvoiceNumber.setGenerateBy(newGeneratedInvoice);
		newInvoiceNumber.setGeneratedOn(new Date());

		invoiceService.saveInvoice(newInvoiceNumber);

		Sales sales = new Sales();

		for (int count = 0; count < jsonArray.length(); count++) {

			sales = new Sales();
			sales = gson.fromJson(jsonArray.get(count).toString(), Sales.class);
			salesList.add(sales);

		}

		for (int count = 0; count < salesList.size(); count++) {

			String articleNo = salesList.get(count).getArticleNo();
			Product oldProd = productService.findById(articleNo);
			Sales sale = salesList.get(count);

			if (oldProd != null) {

				int oldQuantity = oldProd.getQuantity();
				int salesQuantity = sale.getQuantity();

				int updatedQuantity = oldQuantity - salesQuantity;
				double updatedPrice = updatedQuantity + oldProd.getUnitPrice();

				oldProd.setQuantity(updatedQuantity);
				oldProd.setTotalPrice(updatedPrice);

				productService.updateOrder(oldProd);

			}

			sale.setInvoiceNo(newGeneratedInvoice);
			sale.setDate(new Date());
			sale.setSoldById(user.getId());
			sale.setSoldByName(salesMan);

			salesService.saveSale(sale);

		}

		UriComponents uriComponents = uriComponentBuilder.path("/generatePdf/" + newGeneratedInvoice).build();

		headers.setLocation(uriComponents.toUri());

		System.out.println("Stop there");
		return new ResponseEntity<Object>(headers, HttpStatus.CREATED);
	}

	//
	@RequestMapping(value = "/generatePdf/{invoiceNo}", method = RequestMethod.GET)
	public ModelAndView report(@PathVariable String invoiceNo,HttpServletRequest request) {
		
		List<Sales> salesList = new ArrayList<Sales>();
		salesList.add(0,new Sales());
		List<Sales> sales = salesService.findByInvoiceNo("INV1001");
		List<PdfData> pdfDataList = new ArrayList<PdfData>();
		PdfData data = new PdfData();
		data.setInvoiceNo(invoiceNo);
		pdfDataList.add(data);
		/*int c = 0;
		for(int i = 1; i<=sales.size(); i++) {
			salesList.add(i, sales.get(c));
			c++;
		}*/
		for(int i =0;i<sales.size();i++){
			Sales tableData = sales.get(i);
			data = new PdfData();
			data.setArticleNo(tableData.getArticleNo());
			data.setDescription(tableData.getDescription());
			data.setDiscount(tableData.getDiscount());
			data.setQuantity(tableData.getQuantity());
			data.setTotal(tableData.getTotal());
			pdfDataList.add(data);
		}
		

		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:lotto.jrxml");
		view.setApplicationContext(appContext);

		JRDataSource pdfDataSource = new JRBeanCollectionDataSource(pdfDataList);

		parameterMap.put("datasource", pdfDataSource);
		parameterMap.put("IS_IGNORE_PAGINATION", true);
		ServletContext context = request.getSession().getServletContext();
	    String path = context.getRealPath("/") + "";
	    parameterMap.put("Context", path);
		return new ModelAndView(view, parameterMap);
	}
	
	@GetMapping(value="allSales")
	public String allSales(Model model, HttpServletRequest request) {
		HttpSession session = SessionUtil.createSession(request);
		User user = (User) session.getAttribute("user");

		model.addAttribute("userinfo", user.getName() + " " + user.getLastName());
		model.addAttribute("salesList", salesService.findAll());
		return "admin/allSales";
	}
}
