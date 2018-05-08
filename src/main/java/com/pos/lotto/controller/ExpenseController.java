package com.pos.lotto.controller;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;
import com.pos.lotto.model.ChargeHead;
import com.pos.lotto.model.Expense;
import com.pos.lotto.model.Sales;
import com.pos.lotto.model.User;
import com.pos.lotto.service.ExpenseService;
import com.pos.lotto.service.SalesService;
import com.pos.lotto.util.SessionUtil;

@Controller
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private SalesService salesService;

	@Autowired
	private JSONArray jsonArray;
	

	@GetMapping("expense")
	public String getExpensePage(Model model, HttpServletRequest request) {

		HttpSession session = SessionUtil.createSession(request);
		User user = (User) session.getAttribute("user");
		
		Double totalSale = 0.0;
		
		List <Sales> sales = salesService.findDaySale(new Date());
		if(!sales.isEmpty()) {
			
			for(int count = 0; count<sales.size(); count++) {
				
				totalSale = totalSale + sales.get(count).getTotal();
			}

			model.addAttribute("totalSale", totalSale);
			model.addAttribute("userinfo", user.getName() + " " + user.getLastName());

			return "expense/expense";
		}else {
			return "expense/noSale";
		}
	
	}

	@PostMapping("expense")
	public ResponseEntity<String> addDailyExpense(@RequestBody String json, Model model, HttpServletRequest request)
			throws JSONException {

		HttpSession session = SessionUtil.createSession(request);
		User user = (User) session.getAttribute("user");

		Gson gson = new Gson();
		jsonArray = new JSONArray(json);

		Expense expense = new Expense();
		expense.setDate(new Date());

		if (jsonArray.length() > 0) {

			List<ChargeHead> chargeHeads = new ArrayList<ChargeHead>();

			ChargeHead chargeHead = new ChargeHead();

			for (int count = 0; count < jsonArray.length(); count++) {

				chargeHead = new ChargeHead();
				chargeHead = gson.fromJson(jsonArray.get(count).toString(), ChargeHead.class);
				chargeHead.setExpense(expense);
				chargeHeads.add(chargeHead);

			}
			expense.setChargeHeads(chargeHeads);

			expenseService.save(expense);

			System.out.println("Stop there");
			return new ResponseEntity<String>("Expnses Added", HttpStatus.CREATED);

		} else {

			return new ResponseEntity<String>("No Orders created", HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping("expenses")
	public String getAllExpenses(Model model, HttpServletRequest request) {

		HttpSession session = SessionUtil.createSession(request);
		User user = (User) session.getAttribute("user");

		model.addAttribute("expenseList", expenseService.findAll());
		model.addAttribute("userinfo", user.getName() + " " + user.getLastName());

		return "expense/expenses";
	}

	@GetMapping("expense/{id}")
	public String getAllExpenses(@PathVariable Integer id, Model model, HttpServletRequest request) {

		HttpSession session = SessionUtil.createSession(request);
		User user = (User) session.getAttribute("user");
		Expense list = expenseService.getExpenseDetails(id);
		
		for(int count = 0; count< list.getChargeHeads().size(); count++) {
			
			model.addAttribute("field"+count, list.getChargeHeads().get(count).getChargeHead());
			model.addAttribute("field"+count+"Value", list.getChargeHeads().get(count).getConditionValue());
		}
		
		
		model.addAttribute("userinfo", user.getName() + " " + user.getLastName());

		return "expense/detailExpense";
	}
}
