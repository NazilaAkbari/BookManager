package com.nazi.persistence;

import org.springframework.data.repository.CrudRepository;

import com.nazi.model.Book;
import com.nazi.model.User;

public interface BookRepository extends CrudRepository<Book, Long> {

	Iterable<Book> findByNameAndUser(String name, Iterable<User> user);

	Iterable<Book> findByUser(Iterable<User> user);
}
