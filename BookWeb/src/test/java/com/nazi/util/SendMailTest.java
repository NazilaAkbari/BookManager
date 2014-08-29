package com.nazi.util;

import static org.junit.Assert.*;

import org.junit.Test;

import com.nazi.model.User;

public class SendMailTest {

	@Test
	public void testEmail() {
		SendMail send = new SendMail();
		User nazi = new User();
		nazi.setUsername("nazila.akbari87@gmail.com");
		send.email(nazi);
	}

}
