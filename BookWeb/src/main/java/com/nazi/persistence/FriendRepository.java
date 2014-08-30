package com.nazi.persistence;

import org.springframework.data.repository.CrudRepository;

import com.nazi.model.Friend;
import com.nazi.model.User;

public interface FriendRepository extends CrudRepository<Friend, Long> {

	Iterable<Friend> findByUser(Iterable<User> user);

	Iterable<Friend> findByNameAndUser(String name, Iterable<User> user);

}
