package com.nazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nazi.model.User;
import com.nazi.service.UserService;

public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/signUp")
	public ModelAndView index() {
		
		return new ModelAndView("signUp");
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public @ResponseBody
	String saveBook(@RequestParam String username, @RequestParam String password,
			@RequestParam String email) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		userService.saveUser(user);
		return "ok";
	}
}
