package com.nazi.persistence;

import org.springframework.data.repository.CrudRepository;

import com.nazi.model.Book;
import com.nazi.model.Friend;
import com.nazi.model.User;

public interface BookRepository extends CrudRepository<Book, Long> {

	Iterable<Book> findByNameAndUser(String name, User user);

	Iterable<Book> findByUser(User user);

	Iterable<Book> findByUserAndOwner(User user, Friend friend);
}
