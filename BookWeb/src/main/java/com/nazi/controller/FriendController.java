package com.nazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nazi.model.Book;
import com.nazi.model.Friend;
import com.nazi.model.User;
import com.nazi.service.BookService;
import com.nazi.service.FriendService;
import com.nazi.service.UserService;
import com.nazi.util.GetUser;

@Controller
public class FriendController extends GetUser {

	@Autowired
	private FriendService friendService;

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;

/*	@RequestMapping("/friend")
	public ModelAndView friendIndex() {
		Iterable<Friend> friends = friendService.loadAllFriend(GettingUser());
		return new ModelAndView("friendIndex", "friends", friends);
	}*/

	@RequestMapping(value = "/saveFriend", method = RequestMethod.POST)
	public @ResponseBody
	String saveFriend(@RequestParam String name, @RequestParam String email) {
		Iterable<User> users = userService.search(GettingUser());
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
		Iterable<User> users = userService.search(GettingUser());
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
		Friend friend = friendService.findFriend(id);
		User user = friend.getUser();
		if (user.getUsername().equals(GettingUser())) {
			Iterable<Book> books = bookService.findByOwner(GettingUser(),
					friend);
			for (Book book : books) {
				book.setOwner(null);
			}
			friendService.deleteFriend(friend);
		}

		return "ok";
	}

	@RequestMapping(value = "/getFriends")
	public @ResponseBody
	Iterable<Friend> getAllFriend() {
		Iterable<Friend> friends = friendService.loadAllFriend(GettingUser());
		return friends;
	}

	@RequestMapping(value = "/searchFriend")
	public @ResponseBody
	Iterable<Friend> searchFriend(@RequestParam String name) {
		Iterable<Friend> friends = friendService.search(name, GettingUser());
		return friends;
	}
}
