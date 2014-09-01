package com.nazi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nazi.model.Friend;
import com.nazi.model.User;
import com.nazi.persistence.FriendRepository;
import com.nazi.persistence.UserRepository;

@Service
public class FriendService {

	@Autowired
	private FriendRepository friendRepository;

	@Autowired
	private UserRepository userRepository;

	public Iterable<Friend> loadAllFriend(String username) {
		Iterable<User> users = userRepository.findByUsername(username);
		User user = users.iterator().next();
		return friendRepository.findByUser(user);
	}

	public void saveFriend(Friend friend) {
		friendRepository.save(friend);
	}

	public Friend findFriend(Long id) {
		return friendRepository.findOne(id);
	}

	public void deleteFriend(Friend friend) {
		friendRepository.delete(friend);
	}

	public Iterable<Friend> search(String name, String username) {
		Iterable<User> users = userRepository.findByUsername(username);
		User user = users.iterator().next();
		return friendRepository.findByNameAndUser(name, user);
	}
}
