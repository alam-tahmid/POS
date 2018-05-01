package com.pos.lotto.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.pos.lotto.mail.MailService;
import com.pos.lotto.model.PasswordChange;
import com.pos.lotto.model.User;
import com.pos.lotto.service.UserService;
import com.pos.lotto.util.SessionUtil;

@Controller
public class LoginController {

	private final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/", "/login" }, method = RequestMethod.GET)
	public ModelAndView login() {

		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {

			logger.info("user already logged in. redirecting to home page");

			/* The user is logged in :) */
			return new ModelAndView("forward:/home");
		} else {

			logger.info("user not logged in. getting the login page");
			modelAndView.setViewName("user/login");
			return modelAndView;
		}
	}

	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("user/registration");
		return modelAndView;
	}

	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {

		ModelAndView modelAndView = new ModelAndView();
		String pass = user.getPassword();
		User userExists = userService.findUserByEmail(user.getEmail());

		if (userExists != null) {
			bindingResult.rejectValue("email", "error.user", "There is already user registered with this email");
		}

		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "User has been created successfully");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("user/registration");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/admin/admin", method = RequestMethod.GET)
	public ModelAndView admin() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/admin");
		return modelAndView;
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {

		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		HttpSession session = SessionUtil.createSession(request);
		session.setAttribute("user", user);

		modelAndView.addObject("userinfo", user.getName() + " " + user.getLastName());

		modelAndView.setViewName("sales/sales");

		return modelAndView;
	}
}
