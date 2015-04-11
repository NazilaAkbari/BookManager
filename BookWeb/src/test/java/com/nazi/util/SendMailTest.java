package com.nazi.util;

import org.junit.Test;

import com.nazi.model.User;

public class SendMailTest {

	
	public void testEmail() {
		SendMail send = new SendMail();
		User nazi = new User();
		nazi.setUsername("nazila.akbari87@gmail.com");
		send.email(nazi);
	}
	
	

}
