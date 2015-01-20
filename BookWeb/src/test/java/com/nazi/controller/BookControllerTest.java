package com.nazi.controller;

import org.hamcrest.Matchers;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import com.nazi.model.Book;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@Transactional
public class BookControllerTest extends BaseWebTest {

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
		mockMvc.perform(post("/saveBook").session(loginAsUser())
			   .param("name", "mohakeme")
			   .param("author", "kafka")
			   .param("readStatus", "1"))
			   .andExpect(status().isOk());
		
		mockMvc.perform(get("/getBooks").session(loginAsUser()))
		       .andExpect(status().isOk()).andDo(print())
		       .andExpect(jsonPath("$").isArray())
		       .andExpect(jsonPath("$", Matchers.hasSize(1)));

	}
	
	@Test
	public void saveEditBook() throws Exception{
		mockMvc.perform(post("/saveEditBook").session(loginAsUser())
			   .param("id","1")
			   .param("name","havie")
			   .param("author", "khosravi")
			   .param("readStatus", "1"))
			   .andExpect(status().isOk());
	}

}
