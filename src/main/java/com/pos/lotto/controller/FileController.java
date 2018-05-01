package com.pos.lotto.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pos.lotto.model.Product;
import com.pos.lotto.model.User;
import com.pos.lotto.service.ProductService;
import com.pos.lotto.util.ReadExcelFile;
import com.pos.lotto.util.SessionUtil;

@Controller
public class FileController {

	// The Environment object will be used to read parameters from the
	// application.properties configuration file
	@Autowired
	private Environment env;

	@Autowired
	private ReadExcelFile readExcelFile;

	@Autowired
	private ProductService productService;

	@GetMapping("uploadFile")
	public String getFileUploadPage(Model model, HttpServletRequest request) {

		HttpSession session = SessionUtil.createSession(request);
		User user = (User) session.getAttribute("user");

		model.addAttribute("userinfo", user.getName() + " " + user.getLastName());
		return "admin/productUploadFile";
	}

	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> uploadFile(@RequestParam("uploadfile") MultipartFile uploadfile) {

		try {
			// Get the filename and build the local file path
			String filename = uploadfile.getOriginalFilename();
			String directory = env.getProperty("netgloo.paths.uploadedFiles");
			String filepath = Paths.get(directory, filename).toString();

			File file = convert(uploadfile);
			List<Product> productList = readExcelFile.readExcel(file);

			for (int count = 0; count < productList.size(); count++) {

				String articleNo = productList.get(count).getArticleNo();
				Product oldProd = productService.findById(articleNo);
				Product newProd = productList.get(count);

				if (oldProd != null) {

					// update the quantity;

					int oldQuantity = oldProd.getQuantity();
					int newQuantity = newProd.getQuantity();
					double oldTotalPrice = oldProd.getTotalPrice();
					double newTotalPrice = newProd.getTotalPrice();
					int updatedQuantity = oldQuantity + newQuantity;
					double updatedPrice = oldTotalPrice + newTotalPrice;
					oldProd.setQuantity(updatedQuantity);
					oldProd.setTotalPrice(updatedPrice);

					productService.updateOrder(oldProd);

				} else {
					// enter new entity

					productService.addOrder(newProd);
				}
			}

			/*// Save the file locally
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
			stream.write(uploadfile.getBytes());
			stream.close();*/
			return new ResponseEntity("Order saved sccussfully", HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	} // method uploadFile

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public ModelAndView download(Model model) {
		
		 return new ModelAndView("excelView", "products", productService.findAll());
	}

	public File convert(MultipartFile file) throws IOException {

		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}
}
