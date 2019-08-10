package financeCalculation;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Create Expense Objects based on the service name, billing amount, and billing times
		ExpenseObject apple = new ExpenseObject("Apple Store", 99.00, ExpenseObject.YEARLY);
		ExpenseObject android = new ExpenseObject("Android Store", 25.00, ExpenseObject.ONE_TIME);
		ExpenseObject firebase = new ExpenseObject("Firebase", 25.00, ExpenseObject.MONTHLY);
		
		// Pack Expense Objects into list to be parsed and calculated
		List<ExpenseObject> packedEO = new ArrayList<ExpenseObject>();
		packedEO.add(apple);
		packedEO.add(android);
		packedEO.add(firebase);
		
		// Provide list of ExpenseObject from previous step, interval of time for data to calculate, and length of time for data
		List<Double> totalList = CalculationBuilder.buildCalculation(packedEO, ExpenseObject.MONTHLY, 60);
		
		Iterator<Double> iterator = totalList.iterator();
		int counter = 1;
		while(iterator.hasNext()) {
			System.out.println("Month " + counter + ": "+ "$"+iterator.next());
			counter++;
		}
		
	}

}
