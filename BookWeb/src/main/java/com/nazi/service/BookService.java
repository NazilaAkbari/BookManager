package com.nazi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nazi.model.Book;
import com.nazi.model.Friend;
import com.nazi.persistence.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public Iterable<Book> loadAll() {
		return bookRepository.findAll();
	}

	public void saveBook(Book book) {
		bookRepository.save(book);
	}

	public Book findBook(Long id) {
		return bookRepository.findOne(id);
	}

	public void deleteBook(Book book) {
		bookRepository.delete(book);
	}

	public void lendBook(Book book, Friend friend) {
		book.setOwner(friend);
		bookRepository.save(book);
	}

}
