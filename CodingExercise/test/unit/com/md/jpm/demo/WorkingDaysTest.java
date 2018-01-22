package com.md.jpm.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.joda.time.DateTime;
import org.junit.Test;

public class WorkingDaysTest {
	
	@Test
	public void testNextWorkingDay() {
		assertEquals(new DateTime(2017,8,1,0,0), WorkingDays.getNextWorkingDay("GBP", new DateTime(2017,8,1,0,0)));
		assertEquals(new DateTime(2017,8,2,0,0), WorkingDays.getNextWorkingDay("GBP", new DateTime(2017,8,2,0,0)));
		assertEquals(new DateTime(2017,8,3,0,0), WorkingDays.getNextWorkingDay("GBP", new DateTime(2017,8,3,0,0)));
		assertEquals(new DateTime(2017,8,4,0,0), WorkingDays.getNextWorkingDay("GBP", new DateTime(2017,8,4,0,0)));
		assertEquals(new DateTime(2017,8,7,0,0), WorkingDays.getNextWorkingDay("GBP", new DateTime(2017,8,5,0,0)));
		assertEquals(new DateTime(2017,8,7,0,0), WorkingDays.getNextWorkingDay("GBP", new DateTime(2017,8,6,0,0)));
		assertEquals(new DateTime(2017,8,7,0,0), WorkingDays.getNextWorkingDay("GBP", new DateTime(2017,8,7,0,0)));
	}
	
	@Test
	public void testNextWorkingDaySAR() {
		assertEquals(new DateTime(2017,8,1,0,0), WorkingDays.getNextWorkingDay("SAR", new DateTime(2017,8,1,0,0)));
		assertEquals(new DateTime(2017,8,2,0,0), WorkingDays.getNextWorkingDay("SAR", new DateTime(2017,8,2,0,0)));
		assertEquals(new DateTime(2017,8,3,0,0), WorkingDays.getNextWorkingDay("SAR", new DateTime(2017,8,3,0,0)));
		assertEquals(new DateTime(2017,8,6,0,0), WorkingDays.getNextWorkingDay("SAR", new DateTime(2017,8,4,0,0)));
		assertEquals(new DateTime(2017,8,6,0,0), WorkingDays.getNextWorkingDay("SAR", new DateTime(2017,8,5,0,0)));
		assertEquals(new DateTime(2017,8,6,0,0), WorkingDays.getNextWorkingDay("SAR", new DateTime(2017,8,6,0,0)));
		assertEquals(new DateTime(2017,8,7,0,0), WorkingDays.getNextWorkingDay("SAR", new DateTime(2017,8,7,0,0)));
	}

}
