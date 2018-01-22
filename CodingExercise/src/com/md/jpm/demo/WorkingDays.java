package com.md.jpm.demo;

import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

public class WorkingDays {

	public static TreeSet<Integer> DEFAULT_WORKINGDAYS = new TreeSet<>();
	static {
		DEFAULT_WORKINGDAYS.add(DateTimeConstants.MONDAY);
		DEFAULT_WORKINGDAYS.add(DateTimeConstants.TUESDAY);
		DEFAULT_WORKINGDAYS.add(DateTimeConstants.WEDNESDAY);
		DEFAULT_WORKINGDAYS.add(DateTimeConstants.THURSDAY);
		DEFAULT_WORKINGDAYS.add(DateTimeConstants.FRIDAY);

	}

	public static TreeSet<Integer> SUNTOTHU_WORKINGDAYS = new TreeSet<>();
	static {
		SUNTOTHU_WORKINGDAYS.add(DateTimeConstants.SUNDAY);
		SUNTOTHU_WORKINGDAYS.add(DateTimeConstants.MONDAY);
		SUNTOTHU_WORKINGDAYS.add(DateTimeConstants.TUESDAY);
		SUNTOTHU_WORKINGDAYS.add(DateTimeConstants.WEDNESDAY);
		SUNTOTHU_WORKINGDAYS.add(DateTimeConstants.THURSDAY);

	}

	public static HashMap<String, TreeSet<Integer>> ALTERNATE_WORKDAYS = new HashMap<>();
	static {
		ALTERNATE_WORKDAYS.put("SAR", SUNTOTHU_WORKINGDAYS);
		ALTERNATE_WORKDAYS.put("AED", SUNTOTHU_WORKINGDAYS);
	}

	public static Set<Integer> getWorkingWeekByCurrency(String currency) {
		if (ALTERNATE_WORKDAYS.containsKey(currency)) {
			return ALTERNATE_WORKDAYS.get(currency);
		}

		return DEFAULT_WORKINGDAYS;
	}

	public static DateTime getNextWorkingDay(String currency, DateTime settlementDate) {

		DateTime date = new DateTime(settlementDate);

		while (!getWorkingWeekByCurrency(currency).contains(date.getDayOfWeek())) {
			date = date.plusDays(1);
		}

		return date;

	}

}
