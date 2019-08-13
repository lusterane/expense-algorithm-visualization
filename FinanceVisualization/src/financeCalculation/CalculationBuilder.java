package financeCalculation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;
import java.util.Stack;

public class CalculationBuilder {
	public static HashMap<Integer, Double> buildCalculation(List<ExpenseObject> expenseObjectList, String timeInterval,
			int stoppingPoint) {
		HashMap<Integer, Double> hashMapTotals = new HashMap<Integer, Double>();
		hashMapTotals.put(0, 0.0);

		List<ExpenseObject> orderedList = orderForTimeInterval(expenseObjectList, timeInterval);

		int counter = 1;
		double total = 0;
		
		if (timeInterval.equals(ExpenseObject.DAILY)) {
			System.out.println("Sorry, no implementation for this yet.");
		} else if (timeInterval.equals(ExpenseObject.MONTHLY)) {
			while (counter <= stoppingPoint) {
				Iterator<ExpenseObject> iterator = orderedList.iterator();

				ExpenseObject current;
				while (iterator.hasNext()) {
					current = iterator.next();
					if (current.getChargeInterval().contentEquals(ExpenseObject.MONTHLY)) {
						total += current.getAmount();
					} else if (current.getChargeInterval().contentEquals(ExpenseObject.YEARLY)) {
						// adds current if one month past a year
						if(counter%12 == 1) {
							total += current.getAmount();
						}
					} else if (current.getChargeInterval().contentEquals(ExpenseObject.ONE_TIME) && !current.getAdded()) {
						total += current.getAmount();
						
					} else if (current.getChargeInterval().contentEquals(ExpenseObject.DAILY)) {
						total += current.getAmount() * 31;
					}
					current.setAdded(true);
				}

				// counter is month
				// total is calculated price of month
				hashMapTotals.put(counter, total);
				counter++;
			}
		} else if (timeInterval.equals(ExpenseObject.YEARLY)) {
			System.out.println("Sorry, no implementation for this yet.");
		}

		return hashMapTotals;
	}

	private static List<ExpenseObject> orderForTimeInterval(List<ExpenseObject> expenseObjectList,
			String timeInterval) {
		List<ExpenseObject> ret = new ArrayList<ExpenseObject>();
		Stack<ExpenseObject> temp_stack = new Stack<ExpenseObject>();

		Iterator<ExpenseObject> iterator = expenseObjectList.iterator();

		ExpenseObject current;

		if (timeInterval.equals(ExpenseObject.MONTHLY)) {

			while (iterator.hasNext()) {
				current = iterator.next();
				if (current.getChargeInterval().equals(ExpenseObject.MONTHLY)) {
					ret.add(current);
				} else {
					temp_stack.push(current);
				}
			}

			while (!temp_stack.isEmpty()) {
				ret.add(temp_stack.pop());
			}
		} else {
			System.out.println("Sorry, no implementation for this yet.");
		}

		return ret;
	}
}
