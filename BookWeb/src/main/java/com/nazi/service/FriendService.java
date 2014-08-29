package com.nazi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nazi.model.Friend;
import com.nazi.persistence.FriendRepository;

@Service
public class FriendService {
	@Autowired
	private FriendRepository friendRepository;

	public Iterable<Friend> loadAllFriend() {
		return friendRepository.findAll(new Sort("name"));
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

	public Iterable<Friend> search(String name) {
		return friendRepository.findByNameContaining(name);
	}
}
