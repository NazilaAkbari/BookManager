package com.nazi.persistence;

import java.util.UUID;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.nazi.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	Iterable<User> findAll(Sort sort);

	Iterable<User> findByUsername(String username);

	Iterable<User> findByRId(UUID rId);

}
