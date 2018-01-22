package com.md.jpm.demo;

import java.math.BigDecimal;
import java.util.Currency;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.text.translate.AggregateTranslator;
import org.joda.time.DateTime;

// Where a settlement day is not a working day it is autiomatically adjusted to the enxt working day
// getValue returns the value in USD. For the purpose of the exercise I assume that to convert from
// the chose currency to USD you multiply by the agreedFx (ie it is local->USD rate)
public class Instruction {

	private final String			entity;
	private final InstructionType	type;
	private final BigDecimal		agreedFx;
	private final String			currency;
	private final DateTime			instructionDate;
	private final DateTime			settlementData;
	private final long				units;
	private final BigDecimal		pricePerUnit;

	public Instruction(String entity, InstructionType type, BigDecimal agreedFx, String currency,
			DateTime instructionDate, DateTime settlementDate, long units, BigDecimal pricePerUnit) {
		
		Validate.notEmpty(entity);
		Validate.notNull(agreedFx);
		Validate.isTrue(agreedFx.compareTo(BigDecimal.ZERO) > 0);
		Validate.notNull(currency);
		Validate.notNull(instructionDate);
		Validate.notNull(settlementDate);
		Validate.isTrue(units > 0);
		Validate.notNull(pricePerUnit);
		Validate.isTrue(pricePerUnit.compareTo(BigDecimal.ZERO) > 0);
		
		//Validate that settlementday is on a weekday
	   settlementDate = WorkingDays.getNextWorkingDay(currency, settlementDate);
		
		
		this.entity          = entity;
		this.type            = type;
		this.agreedFx        = agreedFx;
		this.currency        = currency;
		this.instructionDate = instructionDate;
		settlementData       = settlementDate;
		this.units           = units;
		this.pricePerUnit    = pricePerUnit;

	}

	public String getEntity() {
		return entity;
	}

	public InstructionType getType() {
		return type;
	}

	public BigDecimal getAgreedFx() {
		return agreedFx;
	}

	public String getCurrency() {
		return currency;
	}

	public DateTime getInstructionDate() {
		return instructionDate;
	}

	public DateTime getSettlementDate() {
		return settlementData;
	}

	public long getUnits() {
		return units;
	}

	public BigDecimal getPricePerUnit() {
		return pricePerUnit;
	}
	
	public BigDecimal getVaue() {
		BigDecimal valuePreFx = pricePerUnit.multiply(BigDecimal.valueOf(units));
		return valuePreFx.multiply(agreedFx);
	}

}
