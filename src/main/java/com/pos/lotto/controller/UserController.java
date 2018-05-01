package com.pos.lotto.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.google.gson.Gson;
import com.pos.lotto.model.User;
import com.pos.lotto.service.UserService;
import com.pos.lotto.util.SessionUtil;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/profile")
	public String getProfilePage(Model model, HttpServletRequest request) {

		HttpSession session = SessionUtil.createSession(request);
		User user = (User) session.getAttribute("user");

		model.addAttribute("userinfo", user.getName() + " " + user.getLastName());

		model.addAttribute("userFistName", user.getName());
		model.addAttribute("userLastName", user.getLastName());
		// model.addAttribute("userEmployeeId", user.getEmployeeId());
		model.addAttribute("userEmailId", user.getEmail());
		return "user/profile";
	}

	@PostMapping("/profile")
	public ResponseEntity<String> updateUserInfo(@RequestBody String jsonString, HttpServletRequest request) {

		HttpHeaders responseHeaders = new HttpHeaders();

		Gson gson = new Gson();
		User user = gson.fromJson(jsonString, User.class);

		userService.updateUser(user);

		responseHeaders.set("MyResponseHeader", "MyValue");

		return new ResponseEntity<String>("SUCCESSFULL", responseHeaders, HttpStatus.CREATED);

	}
}
