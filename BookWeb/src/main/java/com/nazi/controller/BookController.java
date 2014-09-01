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
import com.nazi.model.User;
import com.nazi.service.BookService;
import com.nazi.service.FriendService;
import com.nazi.service.UserService;
import com.nazi.util.GetUser;

@Controller
public class BookController extends GetUser {

	@Autowired
	private BookService bookservice;
	@Autowired
	private FriendService friendService;
	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public ModelAndView index() {
		Iterable<Book> books = bookservice.loadAllByUser(GettingUser());
		return new ModelAndView("index", "books", books);
	}

	@RequestMapping(value = "/getBooks")
	public @ResponseBody
	Iterable<Book> getAllBook() {
		Iterable<Book> books = bookservice.loadAllByUser(GettingUser());
		return books;
	}

	@RequestMapping(value = "/saveBook", method = RequestMethod.POST)
	public @ResponseBody
	String saveBook(@RequestParam String name, @RequestParam String author,
			@RequestParam int readStatus) {
		Iterable<User> users = userService.search(GettingUser());
		User user = users.iterator().next();
		Book book = new Book();
		book.setName(name);
		book.setAuthor(author);
		book.setReadStatus(readStatus);
		book.setUser(user);
		bookservice.saveBook(book);
		return "ok";
	}

	@RequestMapping(value = "/saveEditBook", method = RequestMethod.POST)
	public @ResponseBody
	String saveEditBook(@RequestParam Long id, @RequestParam String name,
			@RequestParam String author, @RequestParam int readStatus) {
		Iterable<User> users = userService.search(GettingUser());
		User user = users.iterator().next();
		Book book = bookservice.findBook(id);
		book.setName(name);
		book.setAuthor(author);
		book.setReadStatus(readStatus);
		book.setUser(user);
		bookservice.saveBook(book);
		return "ok";

	}

	@RequestMapping(value = "/deleteBook")
	public @ResponseBody
	String deleteBook(@RequestParam Long id) {
		Book book = bookservice.findBook(id);
		User user = book.getUser();
		if (user.getUsername().equals(GettingUser())) {
			bookservice.deleteBook(book);
		}
		return "ok";
	}

	@RequestMapping(value = "/lendBook")
	public ModelAndView lendBookPage(@RequestParam Long id) {
		Book book = bookservice.findBook(id);
		Iterable<Friend> friends = friendService.loadAllFriend(GettingUser());
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

	@RequestMapping("/allLendBooks")
	public ModelAndView lendIndex() {
		Iterable<Book> books = bookservice.loadLendBook(GettingUser());
		return new ModelAndView("lendIndex", "books", books);
	}

	@RequestMapping(value = "/lendBooks")
	public @ResponseBody
	Iterable<Book> loadAllLendBooks() {
		return bookservice.loadLendBook(GettingUser());
	}

	@RequestMapping(value = "/returnBook")
	public @ResponseBody
	String returnBook(@RequestParam Long id) {
		bookservice.returnBook(id);
		return "OK";
	}

	@RequestMapping(value = "/searchBook")
	public @ResponseBody
	Iterable<Book> searchBook(@RequestParam String name) {
		Iterable<Book> books = bookservice.search(name, GettingUser());
		return books;
	}

	@RequestMapping(value = "/readBooks")
	public @ResponseBody
	Iterable<Book> loadAllReadBooks() {
		return bookservice.loadAllReadBooks();
	}

}
