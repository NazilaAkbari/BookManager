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
import com.nazi.service.FriendService;

@Controller
public class FriendController {

	@Autowired
	private FriendService friendService;

	@RequestMapping("/friend")
	public ModelAndView friendIndex() {
		Iterable<Friend> friends = friendService.loadAllFriend();
		return new ModelAndView("friendIndex", "friends", friends);
	}

	@RequestMapping(value = "/saveFriend", method = RequestMethod.POST)
	public @ResponseBody
	String saveFriend(@RequestParam String name, @RequestParam String email) {
		Friend friend = new Friend();
		friend.setName(name);
		friend.setEmail(email);
		friendService.saveFriend(friend);
		return "OK";
	}

	@RequestMapping(value = "/editFriend")
	public ModelAndView edtiFriendPage(@RequestParam Long id) {
		Friend friend = friendService.findFriend(id);
		ModelAndView MAV = new ModelAndView("editFriend", "friend", friend);
		return MAV;

	}

	@RequestMapping(value = "/saveEditFriend")
	public @ResponseBody
	String saveEditFriend(@RequestParam Long id, @RequestParam String name,
			@RequestParam String email) {
		Friend friend = friendService.findFriend(id);
		friend.setName(name);
		friend.setEmail(email);
		friendService.saveFriend(friend);
		return "ok";

	}

	@RequestMapping(value = "/deleteFriend")
	public @ResponseBody
	String deleteFriend(@RequestParam Long id) {
		Friend friend = friendService.findFriend(id);
		friendService.deleteFriend(friend);
		return "ok";
	}

	@RequestMapping(value = "/getFriends")
	public @ResponseBody
	Iterable<Friend> getAllFriend() {
		Iterable<Friend> friends = friendService.loadAllFriend();
		return friends;
	}
}
