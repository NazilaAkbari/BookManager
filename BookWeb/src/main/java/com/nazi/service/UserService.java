package com.nazi.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nazi.model.User;
import com.nazi.persistence.UserRepository;
import com.nazi.util.SendMail;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public ResponseEntity<String> saveUser(User user) {
		UUID rid = UUID.randomUUID();
		SendMail send = new SendMail();
		user.setUserRole("USER");
		user.setEnabled(false);
		String password = user.getPassword();
		String username = user.getUsername();
		Iterable<User> list = search(username);
		String pattern = "(^.{8,}$)";
		String confirmPassword = user.getConfirmPassword();
		if (list.iterator().hasNext()) {
			return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
		}
		if (password.matches(pattern)) {
			if (password.equals(confirmPassword)) {
				user.setrId(rid);
				userRepository.save(user);
				send.email(user);
				return new ResponseEntity<String>(HttpStatus.OK);
			}
		}

		return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);

	}

	public Iterable<User> search(String username) {
		return userRepository.findByUsername(username);
	}

	public Iterable<User> searchRId(UUID rId) {
		return userRepository.findByRId(rId);
	}
}
