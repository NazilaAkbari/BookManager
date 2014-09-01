package com.nazi.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nazi.model.Book;
import com.nazi.model.Friend;
import com.nazi.model.User;
import com.nazi.persistence.BookRepository;
import com.nazi.persistence.UserRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private UserRepository userRepository;

	public Iterable<Book> loadAllByUser(String username) {
		Iterable<User> users = userRepository.findByUsername(username);
		User user = users.iterator().next();
		return bookRepository.findByUser(user);
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

	public Iterable<Book> search(String name, String username) {
		Iterable<User> users = userRepository.findByUsername(username);
		User user = users.iterator().next();
		return bookRepository.findByNameAndUser(name, user);
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

	public Iterable<Book> loadLendBook(String username) {
		Iterable<User> users = userRepository.findByUsername(username);
		User user = users.iterator().next();
		Iterable<Book> books = bookRepository.findByUser(user);
		Iterator<Book> bookIterator = books.iterator();
		List<Book> lendBooks = new ArrayList<Book>();

		while (bookIterator.hasNext()) {
			Book book = bookIterator.next();
			if (book.getOwner() != null) {
				lendBooks.add(book);
			}
		}

		return lendBooks;
	}

	public Iterable<Book> findByOwner(String username, Friend friend) {
		Iterable<User> users = userRepository.findByUsername(username);
		User user = users.iterator().next();
		return bookRepository.findByUserAndOwner(user, friend);
	}
}
