package com.nazi.service;

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
}
