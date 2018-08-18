package com.quantum.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.quantum.flightreservation.entities.User;
import com.quantum.flightreservation.repos.UserRepository;
import com.quantum.flightreservation.services.SecurityService;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SecurityService securityService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@RequestMapping("/showReg")
	public String showRegistrationPage() {
		LOGGER.info("Inside showRegistrationPage");
		return "login/registerUser";
	}
	
	@RequestMapping("/registerUser")
	public String register(@ModelAttribute("user") User user) {
		LOGGER.info("Inside register"+user);
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);
		return "login/login";
	}
	
	@RequestMapping("/showLogin")
	public String showLogin() {
		LOGGER.info("Inside showLogin");
		return "login/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam("email") String email,@RequestParam("password") String password, ModelMap modelMap) {
		LOGGER.info("Inside login" + email + " " + password + " " + modelMap);
		
		boolean loginResponse = securityService.login(email, password);
		
		LOGGER.info("Inside login() and the email is: " + email);
		
		if(loginResponse) {
			return "findFlights";
		}else {
			modelMap.addAttribute("message", "Invalid username or password.");
		}
		return "login/login";
	}
}
