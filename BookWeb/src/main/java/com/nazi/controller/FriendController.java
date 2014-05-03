package com.nazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

	@RequestMapping("/addFriend")
	public String addFriendPage() {

		return "addFriend";
	}

	@RequestMapping(value = "/saveFriend", method = RequestMethod.POST)
	public String saveFriend(@RequestParam String name,
			@RequestParam String email) {
		Friend friend = new Friend();
		friend.setName(name);
		friend.setEmail(email);
		friendService.saveFriend(friend);
		return "redirect:/friend";
	}

	@RequestMapping(value = "/editFriend")
	public ModelAndView edtiFriendPage(@RequestParam Long id) {
		Friend friend = friendService.findFriend(id);
		ModelAndView MAV = new ModelAndView("editFriend", "friend", friend);
		return MAV;

	}

	@RequestMapping(value = "/saveEditFriend")
	public String saveEditFriend(@RequestParam Long id,
			@RequestParam String name, @RequestParam String email) {
		Friend friend = friendService.findFriend(id);
		friend.setName(name);
		friend.setEmail(email);
		friendService.saveFriend(friend);
		return "redirect:/friend";

	}

	@RequestMapping(value = "/deleteFriend")
	public String deleteFriend(@RequestParam Long id) {
		Friend friend = friendService.findFriend(id);
		friendService.deleteFriend(friend);
		return "redirect:/friend";
	}
}
