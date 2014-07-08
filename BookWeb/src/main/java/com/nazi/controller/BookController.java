package com.nazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nazi.model.Book;
import com.nazi.model.Friend;
import com.nazi.service.BookService;
import com.nazi.service.FriendService;

@Controller
public class BookController {

	@Autowired
	private BookService bookservice;
	@Autowired
	private FriendService friendService;

	@RequestMapping("/")
	public ModelAndView index() {
		Iterable<Book> books = bookservice.loadAll();
		return new ModelAndView("index", "books", books);
	}

	@RequestMapping(value = "/saveBook", method = RequestMethod.POST)
	public @ResponseBody
	String saveBook(@RequestParam String name, @RequestParam String author,
			@RequestParam int readStatus) {
		Book book = new Book();
		book.setName(name);
		book.setAuthor(author);
		book.setReadStatus(readStatus);
		bookservice.saveBook(book);
		return "ok";
	}

	@RequestMapping(value = "/saveEditBook", method = RequestMethod.POST)
	public @ResponseBody
	String saveEditBook(@RequestParam Long id, @RequestParam String name,
			@RequestParam String author, @RequestParam int readStatus) {
		Book book = bookservice.findBook(id);
		book.setName(name);
		book.setAuthor(author);
		book.setReadStatus(readStatus);
		bookservice.saveBook(book);
		return "ok";

	}

	@RequestMapping(value = "/deleteBook")
	public @ResponseBody
	String deleteBook(@RequestParam Long id) {
		Book book = bookservice.findBook(id);
		bookservice.deleteBook(book);
		return "ok";
	}

	@RequestMapping(value = "/lendBook")
	public ModelAndView lendBookPage(@RequestParam Long id) {
		Book book = bookservice.findBook(id);
		Iterable<Friend> friends = friendService.loadAllFriend();
		ModelAndView MAV = new ModelAndView("lendBook", "book", book);
		MAV.addObject("friends", friends);
		return MAV;
	}

	@RequestMapping(value = "/saveLendBook")
	public @ResponseBody
	String saveLendBook(@RequestParam Long bookid, @RequestParam Long friendid) {
		Book book = bookservice.findBook(bookid);
		Friend friend = friendService.findFriend(friendid);
		bookservice.lendBook(book, friend);
		return "ok";
	}

	@RequestMapping(value = "/getBooks")
	public @ResponseBody
	Iterable<Book> getAllBook() {
		Iterable<Book> books = bookservice.loadAll();
		return books;
	}

	@RequestMapping(value = "/searchBook")
	public @ResponseBody
	Iterable<Book> searchBook(@RequestParam String name) {
		Iterable<Book> books = bookservice.search(name);
		return books;
	}

	@RequestMapping(value = "/returnBook")
	public @ResponseBody
	String returnBook(@RequestParam Long id) {
		bookservice.returnBook(id);
		return "OK";
	}

	@RequestMapping(value = "/readBooks")
	public @ResponseBody
	Iterable<Book> loadAllReadBooks() {
		return bookservice.loadAllReadBooks();
	}

	@RequestMapping("/allLendBooks")
	public ModelAndView lendIndex() {
		Iterable<Book> books = bookservice.loadLendBook();
		return new ModelAndView("lendIndex", "books", books);
	}

}
