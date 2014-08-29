package com.nazi.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nazi.model.User;
import com.nazi.service.BookService;
import com.nazi.service.UserService;
import com.nazi.util.SendMail;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookservice;

	@RequestMapping("/signUp")
	public ModelAndView signUp() {
		return new ModelAndView("signUp");
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		UUID rid = UUID.randomUUID();
		SendMail send = new SendMail();
		user.setUserRole("USER");
		user.setEnabled(false);
		String password = user.getPassword();
		String username = user.getUsername();
		Iterable<User> list = searchUser(username);
		String pattern = "(^.{8,}$)";
		String confirmPassword = user.getConfirmPassword();
		if (list.iterator().hasNext()) {
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}
		if (password.matches(pattern)) {
			if (password.equals(confirmPassword)) {
				user.setrId(rid);
				userService.saveUser(user);
				send.email(user);
				return new ResponseEntity<String>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping("/success")
	public String successRegister() {
		return "successSignUp";
	}

	@RequestMapping(value = "/searchUser")
	public @ResponseBody
	Iterable<User> searchUser(@RequestParam String username) {
		Iterable<User> user = userService.search(username);
		return user;
	}

	@RequestMapping("/confirm")
	public String confirm(@RequestParam String id) {
		UUID u = UUID.fromString(id);
		User user = userService.searchRId(u).iterator().next();
		user.setEnabled(true);
		userService.saveUser(user);
		return "redirect:/";
	}
}
