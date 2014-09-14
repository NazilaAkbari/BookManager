package com.nazi.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.nazi.util.GetParameter;

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

	@RequestMapping("/confirm")
	public String confirm(@RequestParam String id) {
		/*
		 * HttpServletRequest request = null; String
		 * rId=request.getParameter("id"); UUID u = UUID.fromString(rId);
		 */
		/*
		 * GetParameter getParameter = new GetParameter(); HttpServletRequest
		 * request=id; UUID u = getParameter.doGet(request);
		 */
		UUID u = UUID.fromString(id);
		User user = userService.searchRId(u).iterator().next();
		user.setEnabled(true);
		userService.saveUser(user);
		return "redirect:/";
	}
}
