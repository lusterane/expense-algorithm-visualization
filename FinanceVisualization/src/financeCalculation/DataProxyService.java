package financeCalculation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataProxyService {
	private List<ExpenseObject> expenseObjectList;
	private HashMap<Integer, Double> expensesHashMap;
	private int amountOfMonths;
	
	public DataProxyService() {
		this.expenseObjectList = new ArrayList<ExpenseObject>();
		this.expensesHashMap = new HashMap<Integer, Double>();
		this.amountOfMonths = 30;
	}
	
	public void addExpenseObject(String name, double rate, String chargeInterval) {
		this.expenseObjectList.add(new ExpenseObject(name, rate, chargeInterval));
	}
	
	public HashMap<Integer, Double> getExpensesHashMap(){
		return expensesHashMap;
	}
	
	public HashMap<Integer, Double> buildExpensesHashMap(String timeIncrement, int duration) {
		expensesHashMap = CalculationBuilder.buildCalculation(expenseObjectList, timeIncrement, duration);
		return expensesHashMap;
	}
	
	public int getAmountOfMonths() {
		return amountOfMonths;
	}
	
	public void setAmountOfMonths(int amountOfMonths) {
		this.amountOfMonths = amountOfMonths;
	}
	
	// temporary place holder to mimic logic abstraction
    public void fillTempData(){
    	// create data 
        
        // Create Expense Objects based on the service name, billing amount, and billing times
 		// Pack Expense Objects into list to be parsed and calculated
 		addExpenseObject("Apple Store", 99.00, ExpenseObject.YEARLY);
 		addExpenseObject("Android Store", 25.00, ExpenseObject.ONE_TIME);
 		addExpenseObject("Firebase", 25.00, ExpenseObject.MONTHLY);

 		// Aggregate expense object to algorithm for calculation
    }
}
