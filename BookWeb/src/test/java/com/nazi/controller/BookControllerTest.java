package com.nazi.controller;

import org.hamcrest.Matchers;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import com.nazi.model.Book;
import com.nazi.model.User;
import com.nazi.persistence.BookRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.junit.Assert.*;

@Transactional
public class BookControllerTest extends BaseWebTest {

	@Autowired
	BookRepository bookRepository;

	@Test
	public void userIndex() throws Exception {
		mockMvc.perform(get("/").session(loginAsUser()))
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attributeExists("books"))
				.andExpect(
						model().attribute("books",
								new IsEmptyCollection<Book>()));
	}

	@Test
	public void guestIndex() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(status().is(HttpStatus.FOUND.value()))
				.andExpect(redirectedUrl("http://localhost/login"));
	}

	@Test
	public void getAllBooks() throws Exception {
		mockMvc.perform(get("/getBooks").session(loginAsUser()))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$", Matchers.hasSize(0)));
	}

	@Test
	public void saveBook() throws Exception {
		mockMvc.perform(
				post("/saveBook").session(loginAsUser())
						.param("name", "mohakeme").param("author", "kafka")
						.param("readStatus", "1")).andExpect(status().isOk());

		mockMvc.perform(get("/getBooks").session(loginAsUser()))
				.andExpect(status().isOk()).andDo(print())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$", Matchers.hasSize(1)));

	}

	@Test
	public void saveEditBook() throws Exception {
		Book book = new Book();
		book.setName("mohakeme");
		book.setAuthor("kafka");
		book.setReadStatus(1);
		Book newBook = bookRepository.save(book);

		mockMvc.perform(
				post("/saveEditBook").session(loginAsUser())
						.param("id", newBook.getId().toString())
						.param("name", "havie").param("author", "khosravi")
						.param("readStatus", "1")).andExpect(status().isOk());
		
		Book editedBook = bookRepository.findOne(newBook.getId());

		assertEquals("havie", editedBook.getName());
		assertEquals("khosravi", editedBook.getAuthor());
		assertEquals(1, editedBook.getReadStatus());
		

	}
	
	@Test
	public void deleteBook() throws Exception{
		Book book = new Book();
		book.setName("mohakeme");
		book.setAuthor("kafka");
		book.setReadStatus(1);
		User user = new User();
		user.setUsername("nazila.akbari87@gmail.com");
		book.setUser(user);
		Book newBook = bookRepository.save(book);
		mockMvc.perform(get("/deleteBook").session(loginAsUser())
				.param("id", newBook.getId().toString()))
				.andExpect(status().isOk());
		
		
	}

}
