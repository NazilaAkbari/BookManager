package com.nazi.persistence;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import com.nazi.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

	Iterable<Book> findAll(Sort sort);

	Iterable<Book> findByNameContaining(String name);

}
