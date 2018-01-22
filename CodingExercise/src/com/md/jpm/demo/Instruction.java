package com.md.jpm.demo;

import java.math.BigDecimal;
import java.util.Currency;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.text.translate.AggregateTranslator;
import org.joda.time.DateTime;

/* Sample data represents the instructions sent by various clients to JP Morgan to execute in the international
market.
Entity Buy/Sell AgreedFx Currency InstructionDate SettlementDate Units Price per unit
foo B 0.50 SGP 01 Jan 2016 02 Jan 2016 200 100.25
bar S 0.22 AED 05 Jan 2016 07 Jan 2016 450 150.5

 A work week starts Monday and ends Friday, unless the currency of the trade is AED or SAR, where
the work week starts Sunday and ends Thursday. No other holidays to be taken into account.
 A trade can only be settled on a working day.
 If an instructed settlement date falls on a weekend, then the settlement date should be changed to
the next working day.
 USD amount of a trade = Price per unit * Units * Agreed Fx
*/

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
