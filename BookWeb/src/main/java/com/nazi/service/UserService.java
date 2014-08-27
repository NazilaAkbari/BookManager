package com.nazi.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nazi.model.User;
import com.nazi.persistence.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void saveUser(User user) {
		userRepository.save(user);
	}

	public Iterable<User> search(String username) {
		return userRepository.findByUsername(username);
	}

	public Iterable<User> searchRId(UUID rId) {
		return userRepository.findByRId(rId);
	}
}
