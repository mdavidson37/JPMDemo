package com.md.jpm.demo;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.joda.time.DateTime;

public class Reports {

	List<Instruction> instructions;

	public Reports(List<Instruction> instructions) {
		this.instructions = instructions;
	}

	public void generateReport() {

		// We want the daily Total Map to be sorted by day (key) to ease output
		Map<DateTime, BigDecimal> incomingDailyTotal = new TreeMap<>();
		Map<DateTime, BigDecimal> outgoingDailyTotal = new TreeMap<>();

		Set<Instruction> incomingRanked = new TreeSet<>(new TransationValueComparator());
		Set<Instruction> outgoingRanked = new TreeSet<>(new TransationValueComparator());

		for (Instruction instruction : instructions) {
			if (instruction.getType() == InstructionType.BUY) {
				addToDailyTotal(outgoingDailyTotal, instruction);
				outgoingRanked.add(instruction);
			} else {
				addToDailyTotal(incomingDailyTotal, instruction);
				incomingRanked.add(instruction);
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

	private void addToDailyTotal(Map<DateTime, BigDecimal> total, Instruction instruction) {
		if (!total.containsKey(instruction.getSettlementDate())) {
			total.put(instruction.getSettlementDate(), new BigDecimal(0));
		}

		BigDecimal transationValue = instruction.getVaue();
	
		BigDecimal currentValue = total.get(instruction.getSettlementDate());
		currentValue = currentValue.add(transationValue);
	
		total.put(instruction.getSettlementDate(), currentValue);

		total.get(instruction.getSettlementDate());
		
	}

	private void printDailyTotals(Map<DateTime, BigDecimal> totals) {
		for (Entry<DateTime, BigDecimal> entry : totals.entrySet()) {
			System.out.println(String.format("%tD :: %f", entry.getKey().toDate(), entry.getValue()));
		}

	}

	private void printRanking(Set<Instruction> outgoingValues) {
		int rank = 0;
		for (Instruction instruction : outgoingValues) {
			System.out.println(String.format("%d: %s :: %f", rank++, instruction.getEntity(), instruction.getVaue()));
		}

	}

	private static class TransationValueComparator implements Comparator<Instruction> {

		@Override
		public int compare(Instruction o1, Instruction o2) {
			return o2.getVaue().compareTo(o1.getVaue());
		}

	}

}
