package com.nazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nazi.model.User;
import com.nazi.service.UserService;
import com.nazi.util.SendMail;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/signUp")
	public ModelAndView index() {
		return new ModelAndView("signUp");
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public @ResponseBody
	String saveBook(@RequestBody User user) {
		SendMail send = new SendMail();
		user.setUserRole("USER");
		String password = user.getPassword();
		String confirmPassword = user.getConfirmPassword();
		if (password.equals(confirmPassword)) {
			userService.saveUser(user);
			send.email(user);
			return "ok";
		} else {
			System.out.println("not ok");

		}
		return null;

	}
}
