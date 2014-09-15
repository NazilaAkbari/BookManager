package com.nazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nazi.model.User;
import com.nazi.service.BookService;
import com.nazi.service.UserService;

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
		return userService.saveUser(user);
	}

	@RequestMapping("/success")
	public String successRegister() {
		return "successSignUp";
	}

	@RequestMapping(value = "/searchUser")
	public @ResponseBody Iterable<User> searchUser(@RequestParam String username) {
		Iterable<User> user = userService.search(username);
		return user;
	}

	@RequestMapping(value = "/confirm/{id}", method = RequestMethod.GET)
	public String confirm(@PathVariable String id) {
		System.out.println(id);
		User user = userService.searchRId(id).iterator().next();
		System.out.println(user.getUsername());
		user.setEnabled(true);
		userService.save(user);

		return "redirect:/";
	}
}
