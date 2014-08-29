package com.nazi.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	public static int computeDate(Date date) {
		Date now = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.get(Calendar.DATE);
		@SuppressWarnings("deprecation")
		int diffInDate = (now.getDate() - date.getDate())
				/ (1000 * 60 * 60 * 24);
		return diffInDate;
	}
}
