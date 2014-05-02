package com.nazi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nazi.model.Book;
import com.nazi.service.BookService;

@Controller
public class BookController {

	@Autowired
	private BookService bookservice;

	@RequestMapping("/")
	public ModelAndView index() {
		Iterable<Book> books = bookservice.loadAll();
		return new ModelAndView("index", "books", books);
	}

	@RequestMapping("/addBook")
	public String addBookPage() {

		return "addBook";
	}

	@RequestMapping(value = "/saveBook", method = RequestMethod.POST)
	public String saveBook(@RequestParam String name,
			@RequestParam String author) {
		Book book = new Book();
		book.setName(name);
		book.setAuthor(author);
		bookservice.saveBook(book);
		return "redirect:/";
	}

	@RequestMapping(value = "/editBook")
	public ModelAndView edtiBookPage(@RequestParam Long id) {
		Book book = bookservice.findBook(id);
		ModelAndView MAV = new ModelAndView("editBook", "book", book);
		return MAV;

	}

	@RequestMapping(value = "/saveEditBook")
	public String saveEditBook(@RequestParam Long id,
			@RequestParam String name, @RequestParam String author) {
		Book book = bookservice.findBook(id);
		book.setName(name);
		book.setAuthor(author);
		bookservice.saveBook(book);
		return "redirect:/";

	}

	@RequestMapping(value = "/deleteBook")
	public String deleteBook(@RequestParam Long id) {
		Book book = bookservice.findBook(id);
		bookservice.deleteBook(book);
		return "redirect:/";
	}
}
