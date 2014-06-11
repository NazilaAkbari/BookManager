package com.nazi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.nazi.model.Book;
import com.nazi.model.Friend;
import com.nazi.persistence.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public Iterable<Book> loadAll() {
		return bookRepository.findAll(new Sort("name"));
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
		book.setDate(new Date());
		bookRepository.save(book);
	}

	public Iterable<Book> search(String name) {
		return bookRepository.findByNameContaining(name);
	}

	public void returnBook(Long id) {
		Book book = bookRepository.findOne(id);
		book.setOwner(null);
		book.setDate(null);
		bookRepository.save(book);
	}

	public Iterable<Book> loadAllReadBooks() {
		Iterable<Book> books = bookRepository.findAll();
		Iterator<Book> bookIterator = books.iterator();
		List<Book> readBooks = new ArrayList<Book>();
		while (bookIterator.hasNext()) {
			Book book = bookIterator.next();
			if (book.getReadStatus() == 0) {
				readBooks.add(book);
			}
		}
		return readBooks;

	}
}
