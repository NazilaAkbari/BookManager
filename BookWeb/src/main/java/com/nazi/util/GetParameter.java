package com.nazi.util;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class GetParameter {

	public UUID doGet(HttpServletRequest id) throws ServletException,
			IOException {
		String req = id.getParameter("id");
		UUID u = UUID.fromString(req);
		return u;
	}

}
