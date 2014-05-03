package com.nazi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nazi.model.Friend;
import com.nazi.persistence.FriendRepository;


@Service
public class FriendService {
	@Autowired
	private FriendRepository friendRepository;

	public Iterable<Friend> loadAllFriend() {
		return friendRepository.findAll();
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
}
