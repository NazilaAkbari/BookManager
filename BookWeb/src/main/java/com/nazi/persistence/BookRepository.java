package com.nazi.persistence;

import org.springframework.data.repository.CrudRepository;

import com.nazi.model.Book;
import com.nazi.model.Friend;
import com.nazi.model.User;

public interface BookRepository extends CrudRepository<Book, Long> {

	Iterable<Book> findByNameAndUser(String name, User user);

	Iterable<Book> findByUser(User user);

	Iterable<Book> findByUserAndOwner(User user, Friend friend);
	
/*	@Query("SELECT b FROM Book b WHERE"
			+ "(?1 IS NULL OR b.name= ?1) AND"
			+ "(?2 IS NULL OR b.author= ?2 ) AND"
			+ "(?3 IS NULL OR b.readStatus= ?3)")*/
	/*@Query("SELECT b FROM Book b WHERE b.name LIKE %?1% AND b.author LIKE %?2%")
	Iterable<Book> findByName(String name,String author);*/
	
	Iterable<Book> findByName(String name);
	
}
