package com.md.jpm.demo;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.joda.time.DateTime;

// Tgis report assumes that each entity generates a single transaction per day.
public class Reports {

	List<Instruction> instructions;

	public Reports(List<Instruction> instructions) {
		this.instructions = instructions;
	}

	public void generateReport() {

		// We want the daily Total Map to be sorted by day (key) to ease output
		Map<DateTime, BigDecimal> incomingDailyTotal = new TreeMap<>();
		Map<DateTime, BigDecimal> outgoingDailyTotal = new TreeMap<>();

		Map<String, BigDecimal> incomingRanked = new TreeMap<>();
		Map<String, BigDecimal> outgoingRanked = new TreeMap<>();

		for (Instruction instruction : instructions) {
			if (instruction.getType() == InstructionType.BUY) {
				addToDailyTotal(outgoingDailyTotal, instruction);
				addToEntityTotal(outgoingRanked, instruction);
			} else {
				addToDailyTotal(incomingDailyTotal, instruction);
				addToEntityTotal(incomingRanked, instruction);
			}
		}

		// Output the daily totals
		System.out.println("Daily Outgoing Totals:");
		printDailyTotals(outgoingDailyTotal);
		System.out.println();

		System.out.println("Daily Incoming Totals:");
		printDailyTotals(incomingDailyTotal);
		System.out.println();

		System.out.println("Outgoing Ranking: ");
		printRanking(outgoingRanked);
		System.out.println();

		System.out.println("Incoming Ranking: ");
		printRanking(incomingRanked);
		System.out.println();

	}

	private void addToEntityTotal(Map<String, BigDecimal> total, Instruction instruction) {
		if (!total.containsKey(instruction.getEntity())) {
			total.put(instruction.getEntity(), new BigDecimal(0));
		}

		BigDecimal transationValue = instruction.getVaue();

		BigDecimal currentValue = total.get(instruction.getEntity());
		currentValue = currentValue.add(transationValue);

		total.put(instruction.getEntity(), currentValue);

	}

	private void addToDailyTotal(Map<DateTime, BigDecimal> total, Instruction instruction) {
		if (!total.containsKey(instruction.getSettlementDate())) {
			total.put(instruction.getSettlementDate(), new BigDecimal(0));
		}

		BigDecimal transationValue = instruction.getVaue();

		BigDecimal currentValue = total.get(instruction.getSettlementDate());
		currentValue = currentValue.add(transationValue);

		total.put(instruction.getSettlementDate(), currentValue);

	}

	private void printDailyTotals(Map<DateTime, BigDecimal> totals) {
		for (Entry<DateTime, BigDecimal> entry : totals.entrySet()) {
			System.out.println(String.format("%tD :: %.2f", entry.getKey().toDate(), entry.getValue()));
		}

	}

	private void printRanking(Map<String, BigDecimal> values) {

		values.entrySet().stream().sorted((f1, f2) -> f2.getValue().compareTo(f1.getValue()))
				.forEach(entry -> System.out.println(String.format("%10s :: %.2f", entry.getKey(), entry.getValue())));
	}

}
