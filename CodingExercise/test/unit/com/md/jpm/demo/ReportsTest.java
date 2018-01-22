package com.md.jpm.demo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

public class ReportsTest {

	private Reports reports;

	@Before
	public void setUp() {
		List<Instruction> instructions = new ArrayList<>();
		instructions.add(new Instruction("foo", InstructionType.BUY, BigDecimal.valueOf(0.5), "SGP", 
				new DateTime(2016, 1, 1, 0, 0), new DateTime(2016, 2, 2,0,0), 200L, BigDecimal.valueOf(100.25)));
		instructions.add(new Instruction("bar", InstructionType.SELL, BigDecimal.valueOf(0.22), "AED", 
				new DateTime(2016, 1, 5, 0, 0), new DateTime(2016, 2, 7,0,0), 450L, BigDecimal.valueOf(150.5)));
		
		instructions.add(new Instruction("test1", InstructionType.SELL, BigDecimal.valueOf(0.22), "GBP", 
				new DateTime(2017, 8, 6, 0, 0), new DateTime(2017, 8, 6,0,0), 450L, BigDecimal.valueOf(150.5)));
		
		instructions.add(new Instruction("test1", InstructionType.SELL, BigDecimal.valueOf(0.22), "GBP", 
				new DateTime(2017, 8, 6, 0, 0), new DateTime(2017, 8, 6,0,0), 6L, BigDecimal.valueOf(150.5)));
		instructions.add(new Instruction("test2", InstructionType.SELL, BigDecimal.valueOf(0.22), "GBP", 
				new DateTime(2017, 8, 6, 0, 0), new DateTime(2017, 8, 6,0,0), 7L, BigDecimal.valueOf(12.5)));
		instructions.add(new Instruction("test3", InstructionType.SELL, BigDecimal.valueOf(0.22), "GBP", 
				new DateTime(2017, 8, 6, 0, 0), new DateTime(2017, 8, 6,0,0), 1L, BigDecimal.valueOf(18.5)));
		instructions.add(new Instruction("test4", InstructionType.SELL, BigDecimal.valueOf(0.22), "GBP", 
				new DateTime(2017, 8, 6, 0, 0), new DateTime(2017, 8, 6,0,0), 120L, BigDecimal.valueOf(19.5)));
		
		instructions.add(new Instruction("test5", InstructionType.BUY, BigDecimal.valueOf(0.17), "AED", 
				new DateTime(2017, 8, 6, 0, 0), new DateTime(2017, 8, 6,0,0), 6L, BigDecimal.valueOf(166.5)));
		instructions.add(new Instruction("test6", InstructionType.BUY, BigDecimal.valueOf(0.17), "AED", 
				new DateTime(2017, 8, 6, 0, 0), new DateTime(2017, 8, 6,0,0), 7L, BigDecimal.valueOf(144.5)));
		instructions.add(new Instruction("test7", InstructionType.BUY, BigDecimal.valueOf(0.17), "AED", 
				new DateTime(2017, 8, 6, 0, 0), new DateTime(2017, 8, 6,0,0), 1L, BigDecimal.valueOf(77.5)));
		instructions.add(new Instruction("test8", InstructionType.BUY, BigDecimal.valueOf(0.17), "AED", 
				new DateTime(2017, 8, 6, 0, 0), new DateTime(2017, 8, 6,0,0), 120L, BigDecimal.valueOf(88.5)));
		
		
		
		reports = new Reports(instructions);
	}
	
	@Test
	public void testPrint() {
		reports.generateReport();
	}
}
