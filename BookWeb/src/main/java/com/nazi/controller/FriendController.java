package com.nazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nazi.model.Friend;
import com.nazi.model.User;
import com.nazi.service.FriendService;
import com.nazi.service.UserService;

@Controller
public class FriendController {

	@Autowired
	private FriendService friendService;

	@Autowired
	private UserService userService;

	@RequestMapping("/friend")
	public ModelAndView friendIndex() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName();
		Iterable<Friend> friends = friendService.loadAllFriend(username);
		return new ModelAndView("friendIndex", "friends", friends);
	}

	@RequestMapping(value = "/saveFriend", method = RequestMethod.POST)
	public @ResponseBody
	String saveFriend(@RequestParam String name, @RequestParam String email) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName();
		Iterable<User> users = userService.search(username);
		User user = users.iterator().next();
		Friend friend = new Friend();
		friend.setName(name);
		friend.setEmail(email);
		friend.setUser(user);
		friendService.saveFriend(friend);
		return "OK";
	}

	@RequestMapping(value = "/saveEditFriend")
	public @ResponseBody
	String saveEditFriend(@RequestParam Long id, @RequestParam String name,
			@RequestParam String email) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName();
		Iterable<User> users = userService.search(username);
		User user = users.iterator().next();
		Friend friend = friendService.findFriend(id);
		friend.setName(name);
		friend.setEmail(email);
		friend.setUser(user);
		friendService.saveFriend(friend);
		return "ok";

	}

	@RequestMapping(value = "/deleteFriend")
	public @ResponseBody
	String deleteFriend(@RequestParam Long id) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName();
		Friend friend = friendService.findFriend(id);
		User user = friend.getUser();
		if (user.getUsername().equals(username)) {
			friendService.deleteFriend(friend);
		}
		return "ok";
	}

	@RequestMapping(value = "/getFriends")
	public @ResponseBody
	Iterable<Friend> getAllFriend() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName();
		Iterable<Friend> friends = friendService.loadAllFriend(username);
		return friends;
	}

	@RequestMapping(value = "/searchFriend")
	public @ResponseBody
	Iterable<Friend> searchFriend(@RequestParam String name) {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = auth.getName();
		Iterable<Friend> friends = friendService.search(name, username);
		return friends;
	}
}
