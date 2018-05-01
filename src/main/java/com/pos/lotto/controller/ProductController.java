package com.pos.lotto.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.pos.lotto.model.Product;
import com.pos.lotto.model.User;
import com.pos.lotto.service.ProductService;
import com.pos.lotto.util.SessionUtil;

@Controller
public class ProductController {

	@Autowired
	private JSONArray jsonArray;

	@Autowired
	private ProductService productService;

	@GetMapping("/addProduct")
	public String addProdcutUsingForm(Model model, HttpServletRequest request) {

		HttpSession session = SessionUtil.createSession(request);
		User user = (User) session.getAttribute("user");

		model.addAttribute("userinfo", user.getName() + " " + user.getLastName());
		return "admin/addProduct";
	}

	@PostMapping("/product")
	public ResponseEntity<String> product(@RequestBody String json) throws JSONException {

		Gson gson = new Gson();
		try {

			jsonArray = new JSONArray(json);
			List<Product> productList = new ArrayList<Product>();
			
			

			Product product = new Product();

			for (int count = 0; count < jsonArray.length(); count++) {

				product = new Product();
				product = gson.fromJson(jsonArray.get(count).toString(), Product.class);
				productList.add(product);

			}
			if(productList.size() ==0 ) {
				return new ResponseEntity("There is no product to add. Please add product", HttpStatus.BAD_REQUEST);
			}else {
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

			System.out.println("Stop there");

			return new ResponseEntity("Order saved sccussfully", HttpStatus.CREATED);
			}
			
		} catch (Exception e) {

			return new ResponseEntity("Error occurred cannot save product", HttpStatus.BAD_REQUEST);
		}
		

	}

	@RequestMapping(value = "getInventory", method = RequestMethod.GET)
	public String getProducts(Model model, HttpServletRequest request) {

		HttpSession session = SessionUtil.createSession(request);
		User user = (User) session.getAttribute("user");

		model.addAttribute("userinfo", user.getName() + " " + user.getLastName());
		model.addAttribute("productList", productService.findAll());

		return "admin/products";
	}

	@PostMapping(value = "/getProductDetails")
	public ResponseEntity<String> getProduct(@RequestBody String articleNo) {

		Gson gson = new Gson();
		String article = gson.fromJson(articleNo, String.class);

		Product product = productService.findById(article);

		return new ResponseEntity(product, HttpStatus.OK);
	}
}
