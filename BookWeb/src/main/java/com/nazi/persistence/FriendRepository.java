package com.nazi.persistence;

import org.springframework.data.repository.CrudRepository;

import com.nazi.model.Friend;

public interface FriendRepository extends CrudRepository<Friend, Long> {

}
