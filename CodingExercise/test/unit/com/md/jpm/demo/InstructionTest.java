package com.md.jpm.demo;

import java.math.BigDecimal;

import org.joda.time.DateTime;
import org.junit.Test;

import org.junit.Assert;

public class InstructionTest {
	
	@Test
	public void testSettlementDayOnWeekDay() {
		Instruction instruction = new Instruction("test", InstructionType.SELL, BigDecimal.valueOf(0.1), "GBP",
				new DateTime(2017, 8,4,0,0), new DateTime(2017, 8,4,0,0), 1, BigDecimal.valueOf(1.0));
		
		Assert.assertEquals(new DateTime(2017,8,4,0,0), instruction.getSettlementDate());
	}
	
	@Test
	public void testSettlementDayOnWeekSAR() {
		Instruction instruction = new Instruction("test", InstructionType.SELL, BigDecimal.valueOf(0.1), "GBP",
				new DateTime(2017, 8,4,0,0), new DateTime(2017, 8,4,0,0), 1, BigDecimal.valueOf(1.0));
		Assert.assertEquals(new DateTime(2017,8,4,0,0), instruction.getSettlementDate());
	}
	
	@Test
	public void testSettlementDayOnWeekAED() {
		Instruction instruction = new Instruction("test", InstructionType.SELL, BigDecimal.valueOf(0.1), "GBP",
				new DateTime(2017, 8,4,0,0), new DateTime(2017, 8,4,0,0), 1, BigDecimal.valueOf(1.0));
		
		Assert.assertEquals(new DateTime(2017,8,4,0,0), instruction.getSettlementDate());
	}
	
	@Test
	public void testSettlementDayOnSaturday() {
		Instruction instruction = new Instruction("test", InstructionType.SELL, BigDecimal.valueOf(0.1), "GBP",
				new DateTime(2017, 8,5,0,0), new DateTime(2017, 8,5,0,0), 1, BigDecimal.valueOf(1.0));
		
		Assert.assertEquals(new DateTime(2017,8,7,0,0), instruction.getSettlementDate());
	}
	
	@Test
	public void testSettlementDayOnSaturdayForAED() {
		Instruction instruction = new Instruction("test", InstructionType.SELL, BigDecimal.valueOf(0.1), "AED",
				new DateTime(2017, 8,5,0,0), new DateTime(2017, 8,5,0,0), 1, BigDecimal.valueOf(1.0));
		
		Assert.assertEquals(new DateTime(2017,8,6,0,0), instruction.getSettlementDate());
	}
	
	@Test
	public void testSettlementDayOnSaturdayForSAR() {
		Instruction instruction = new Instruction("test", InstructionType.SELL, BigDecimal.valueOf(0.1), "AED",
				new DateTime(2017, 8,5,0,0), new DateTime(2017, 8,5,0,0), 1, BigDecimal.valueOf(1.0));
		
		Assert.assertEquals(new DateTime(2017,8,6,0,0), instruction.getSettlementDate());
	}
	
	@Test
	public void testValueNoFx() {
		Instruction instruction = new Instruction("test", InstructionType.SELL, BigDecimal.valueOf(1), "USD",
				new DateTime(2017, 8,5,0,0), new DateTime(2017, 8,5,0,0), 1, BigDecimal.valueOf(5.0));
		
		Assert.assertEquals(5.0, instruction.getVaue().doubleValue(), 0.01);
	}

	@Test
	public void testValueWithFx() {
		Instruction instruction = new Instruction("test", InstructionType.SELL, BigDecimal.valueOf(0.5), "USD",
				new DateTime(2017, 8,5,0,0), new DateTime(2017, 8,5,0,0), 1, BigDecimal.valueOf(5.0));
		
		Assert.assertEquals(2.5, instruction.getVaue().doubleValue(), 0.01);
	}
	
}
